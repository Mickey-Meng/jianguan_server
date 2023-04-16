package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.config;

import org.springframework.context.*;

public class AppListener implements ApplicationListener
{
    public void onApplicationEvent(final ApplicationEvent applicationEvent) {
        System.out.println("on app event");
    }
}
