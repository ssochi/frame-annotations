package org.ssochi.fa.annotations.views;

import java.lang.annotation.*;

/**
 * View的基本配置
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ViewProperties {
    /**
     * 通过vue的v-if实现一个View的可见性
     * @return 是否可见
     */
    boolean visible() default true;

    /**
     * @return 一个表单项的标题
     */
    String title() default "";

    /**
     * 除了显示在第一个的View,其他的View该字段必须填写
     * {@link org.ssochi.fa.core.engine.interfaces.FAEngine} 会通过该字段为View排序
     * @return 一个view的前置view名称,也就是字段名
     */
    String prev() default "";
}
