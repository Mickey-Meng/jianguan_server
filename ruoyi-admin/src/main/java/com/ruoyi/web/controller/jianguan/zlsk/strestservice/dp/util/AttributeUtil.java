package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.util;

import java.lang.reflect.*;

public class AttributeUtil
{
    public static String[] getFiledName(final Object o) {
        final Field[] fields = o.getClass().getDeclaredFields();
        final String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    public static Object getFieldValueByName(final String fieldName, final Object o) {
        try {
            final String firstLetter = fieldName.substring(0, 1).toUpperCase();
            final String getter = "get" + firstLetter + fieldName.substring(1);
            final Method method = o.getClass().getMethod(getter, (Class<?>[])new Class[0]);
            final Object value = method.invoke(o, new Object[0]);
            return value;
        }
        catch (Exception e) {
            return null;
        }
    }
}
