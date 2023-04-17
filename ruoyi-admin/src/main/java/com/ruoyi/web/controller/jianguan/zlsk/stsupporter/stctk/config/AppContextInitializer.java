package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.config;

import org.springframework.context.*;

public class AppContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>
{
    public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("app context initialize");
    }
}
