package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface StUseDataSource {
    String value() default "";
}
