package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/6 11:34 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
