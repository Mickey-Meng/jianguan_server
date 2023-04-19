package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface StLogin {
    String value() default "";
}
