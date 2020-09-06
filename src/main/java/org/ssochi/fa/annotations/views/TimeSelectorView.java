package org.ssochi.fa.annotations.views;

import org.ssochi.fa.views.TimeSelectorFAView;

import java.lang.annotation.*;
/**
 * 这是一个 时间选择器 View
 * 可以注解在 String 类型的字段上
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = TimeSelectorFAView.class)
public @interface TimeSelectorView {

}
