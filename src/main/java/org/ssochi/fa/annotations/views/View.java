package org.ssochi.fa.annotations.views;

import org.ssochi.fa.core.FAView;

import java.lang.annotation.*;

/**
 * View注解能加在别的注解上
 * 它能定义一个注解是一个View类型的注解
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface View {
    /**
     * 关联的{@link FAView}的实现类
     * {@link org.ssochi.fa.core.engine.interfaces.FAEngine} 会通过bind类自动将View初始化
     * @return 关联的FAView的实现类
     */
    Class<? extends FAView> bind();
}
