package com.ruoyi.common.config.zjrw;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.annotation.CheckRepeatCommit;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/13 11:09
 * @Version : 1.0
 * @Description : 防止频繁请求、重复提交aop
 **/
@Aspect
@Component
public class CheckRepeatCommitAspect {

    Logger log = LoggerFactory.getLogger(CheckRepeatCommitAspect.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.ruoyi.common.annotation.CheckRepeatCommit)")
    private void checkRepeatCommit() {

    }

    @Around("checkRepeatCommit()")
    public Object checkRepeatCommit(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        //得到拦截的方法
        Method method = getMethodByClassAndName(target.getClass(), methodName);

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();

        String channel = "";
        int expireTime = 0;
        String bizKey = method.getName();

        // 获取当前请求方法的注解，根据注解配置获取参数
        CheckRepeatCommit checkRepeatCommit = method.getAnnotation(CheckRepeatCommit.class);

        String userNo = JwtUtil.getRequest().getHeader("Authorization");
        String key;

        if (checkRepeatCommit != null) {
            //注解上的描述
            channel = checkRepeatCommit.channel();
            expireTime = checkRepeatCommit.expireTime();

            key = getRepeatCommitLock(channel, className, bizKey, userNo, expireTime);

            if (StringUtils.isBlank(key)) {
                return new ResponseBase(200, "请勿短时间内重复提交请求！");
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 根据类和方法名得到方法
     */
    public Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    public String getRepeatCommitLock(String channel, String module, String bizKey, String userNo, int expireTime) {

        String redisKey = channel + StrUtil.COLON + module + StrUtil.COLON + bizKey + StrUtil.COLON + userNo;

        long count = redisTemplate.opsForValue().increment(redisKey, 1);

        if (count == 1) {
            if (expireTime == 0) {
                expireTime = 60;
            }
            redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
            return redisKey;
        } else {
            return null;
        }
    }

}
