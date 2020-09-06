package org.ssochi.fa.annotations.views;

import org.ssochi.fa.core.FAView;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = FAView.class)
public @interface ClientVersionView {
}
