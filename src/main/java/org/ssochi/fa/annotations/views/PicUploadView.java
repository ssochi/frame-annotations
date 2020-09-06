package org.ssochi.fa.annotations.views;

import org.ssochi.fa.views.PicUploadFAView;

import java.lang.annotation.*;
/**
 * 图片上传 VIEW
 * 标记的类型可以是 String[] 或 Collection<String
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@View(bind = PicUploadFAView.class)
public @interface PicUploadView {
    /**
     * @return 最多能上传的图片数
     * 当 maxPictureCount = -1 可以上传无限张
     */
    int maxPictureCount() default 1;
}
