package org.ssochi.fa.annotations.views;

import org.ssochi.fa.views.InputFAView;

import java.lang.annotation.*;

/**
 * 这是一个输入框
 * 可以注解在 基本类型即包装类 和 String 字段上
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = InputFAView.class)
public @interface InputView {
    /**
     * @return 是否无法输入
     */
    boolean disable() default false;

    /**
     * 当InputView标记的Field为Number类型 则该值永远为false
     * @return 是否使用 textArea 即可变大小的文本框
     */
    boolean textArea() default false;

    /**
     * @return 默认文本
     */
    String defaultText() default "";
}