package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase;

import org.aspectj.lang.annotation.*;

public class StPointCuts
{
    @Pointcut("execution(* com.zlsk..*Controller.*(..))")
    public void zlskControllerMethod() {
    }

    @Pointcut("@target(org.springframework.stereotype.Controller)")
    public void springController() {
    }

    @Pointcut("within(com.zlsk..*) && (@within(org.springframework.stereotype.Controller) || @within( org.springframework.web.bind.annotation.RestController))")
    public void zlskController() {
    }

    @Pointcut("@annotation(com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.annotation.StLogin)")
    public void stLogin() {
    }

    @Pointcut("@annotation(com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.annotation.StLogout)")
    public void stLogout() {
    }

    @Pointcut("within(com.zlsk..*) && (@within(org.springframework.stereotype.Controller) || @within( org.springframework.web.bind.annotation.RestController))")
    public void sta() {
    }

    @Pointcut("(execution(* com.zlsk..*(..)) && @annotation(com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.annotation.StUseDataSource)) || (within(com.zlsk..*) && @within(com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.annotation.StUseDataSource))")
    public void useDataSource() {
    }
}
