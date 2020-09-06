package org.ssochi.fa.annotations;

import org.ssochi.fa.enums.PreConditionAction;

import java.lang.annotation.*;

/**
 * 前置条件
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreCondition {
    /**
     * @return 关联的前置View名称,也就是前置字段名
     */
    String previous();

    /**
     * 可以是vue能解析的任意表达式
     * @return 匹配条件
     */
    String condition();

    /**
     * @return 条件匹配后的行为
     */
    PreConditionAction action();
}
