package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import org.springframework.stereotype.*;
import org.springframework.context.*;
import java.io.*;

@Component
public class SpringContextUtil extends StSupporterBase implements ApplicationContextAware
{
    private static ApplicationContext a;

    public void setApplicationContext(final ApplicationContext a) {
        SpringContextUtil.a = a;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringContextUtil.a;
    }

    public static Object getBean(final String s) {
        try {
            return SpringContextUtil.a.getBean(s);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static <T> T getBean(final Class<T> clazz) {
        try {
            return (T)SpringContextUtil.a.getBean((Class)clazz);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static <T> T getBean(final String s, final Class<T> clazz) {
        try {
            return (T)SpringContextUtil.a.getBean(s, (Class)clazz);
        }
        catch (Exception ex) {
            return null;
        }
    }

    static {
        new StringBuilder("service").append(File.separator);
    }
}
