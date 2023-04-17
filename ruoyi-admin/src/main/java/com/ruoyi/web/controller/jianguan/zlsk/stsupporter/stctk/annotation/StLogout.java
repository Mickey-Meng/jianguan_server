package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface StLogout {
    String value() default "";
}
