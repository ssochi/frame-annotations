package org.ssochi.fa.annotations.views;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ssochi.fa.views.FormSubmitButton;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = FormSubmitButton.class)
public @interface FormSubmitButtonView {
}
