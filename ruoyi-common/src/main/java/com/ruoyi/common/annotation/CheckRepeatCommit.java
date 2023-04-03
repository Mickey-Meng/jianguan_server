package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/13 11:06
 * @Version : 1.0
 * @Description : 防止频繁请求、重复提交
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRepeatCommit {

    /**
     * channel 当前访问的系统，redis中key的前缀。可不填
     * @return
     */
    String channel() default "APP";

    /**
     * 添加了此注解的方法多久之内不允许同一用户重复请求，默认3秒
     * @return
     */
    int expireTime() default 3;
}
