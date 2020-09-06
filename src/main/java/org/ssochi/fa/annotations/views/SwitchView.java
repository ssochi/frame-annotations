package org.ssochi.fa.annotations.views;

import org.ssochi.fa.views.SwitchFAView;

import java.lang.annotation.*;

/**
 * 这是一个 开关 View
 * 可以注解在 boolean or Boolean 类型的字段上
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = SwitchFAView.class)
public @interface SwitchView {
}
