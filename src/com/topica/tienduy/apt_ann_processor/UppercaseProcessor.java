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

@SupportedAnnotationTypes({ "com.topica.tienduy.apt_annotation.Uppercase" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class UppercaseProcessor extends AbstractProcessor {
	private static final String notifiNotMF = "@Uppercase using for class only ";
	private static final String notifiNotUpper = "Class using @Uppercase must start by Uppercase";
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
				if (e.getKind() == ElementKind.METHOD && e.getKind() == ElementKind.FIELD) {
					messager.printMessage(Kind.ERROR, notifiNotMF, e);
				} else {
					String nameMothodField = e.getSimpleName().toString();
					if (!Character.isUpperCase(nameMothodField.charAt(0))) {
						messager.printMessage(Kind.ERROR, notifiNotUpper, e);
					}
				}
			}
		}
		return true;
	}

}
