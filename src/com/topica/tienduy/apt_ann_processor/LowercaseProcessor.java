package com.topica.tienduy.apt_ann_processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes({ "com.topica.tienduy.apt_annotation.Lowercase" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class LowercaseProcessor extends AbstractProcessor {
	private static final String notifiNotMF = "@Lowercase using for method and field only ";
	private static final String notifiNotLower = "Method or Field using @Lowercase must start by LowerCasse";
	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		for (TypeElement listAnno : annotations) {
			Set<? extends Element> element = env.getElementsAnnotatedWith(listAnno);
			for (Element e : element) {
				if (e.getKind() != ElementKind.METHOD && e.getKind() != ElementKind.FIELD) {
					messager.printMessage(Kind.ERROR, notifiNotMF, e);
				} else {
					String nameMethodField = e.getSimpleName().toString();
					if (!Character.isLowerCase(nameMethodField.charAt(0))) {
						messager.printMessage(Kind.ERROR, notifiNotLower, e);
					}
				}
			}
		}
		return true;
	}

}
