package com.topica.tienduy.apt_ann_processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
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
	// check chu cai dau tien trong ten class la chu hoa
	private Filer filer;
	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		filer = env.getFiler();
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		for (TypeElement ann : annotations) {
			Set<? extends Element> e2s = env.getElementsAnnotatedWith(ann);
			for (Element e2 : e2s) {

				if (e2.getKind() == ElementKind.METHOD && e2.getKind() == ElementKind.FIELD) {
					messager.printMessage(Kind.ERROR, "@Uppercase using for class only ", e2);
				} else {
					String methodName = e2.getSimpleName().toString();
					if (!Character.isUpperCase(methodName.charAt(0))) {
						messager.printMessage(Kind.ERROR, "Class using @Uppercase must start by Uppercase", e2);
					}
				}
			}
		}
		return true;
	}

}
