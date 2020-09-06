package org.ssochi.fa.annotations.views;


import org.ssochi.fa.views.VisibleMidFAView;

import java.lang.annotation.*;

/**
 * 这是 可见小米ID配置器 View
 * 可以注解在String类型字段上
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = VisibleMidFAView.class)
public @interface VisibleMidView {
}
