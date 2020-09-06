package org.ssochi.fa.annotations;

import java.lang.annotation.*;

/**
 * 一个ViewModel类需要用该字段进行注解
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ViewModel {
    /**
     * @return 展示页面的标题
     */
    String title() default "";
}
