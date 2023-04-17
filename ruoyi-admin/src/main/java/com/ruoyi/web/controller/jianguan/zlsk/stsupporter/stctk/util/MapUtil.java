package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import java.util.*;

public class MapUtil extends StSupporterBase
{
    public static final String getString(final Map<String, Object> map, final String s, final String s2) {
        final Object a;
        if ((a = a(map, s)) == null) {
            return s2;
        }
        return a.toString();
    }

    public static final boolean getBoolean(final Map<String, Object> map, final String s, final boolean b) {
        return getValue(map, s, Boolean.valueOf(b));
    }

    public static final int getInt(final Map<String, Object> map, final String s, final int n) {
        final Object a;
        if ((a = a(map, s)) == null) {
            return n;
        }
        return Integer.parseInt(a.toString());
    }

    public static final double getDouble(final Map<String, Object> map, final String s, final double n) {
        final Object a;
        if ((a = a(map, s)) == null) {
            return n;
        }
        return Double.parseDouble(a.toString());
    }

    public static final <T> T getValue(final Map<String, Object> map, final String s, final T t) {
        final Object a;
        if ((a = a(map, s)) == null) {
            return t;
        }
        return (T)a;
    }

    public static final <K, V> V getValue(final Map<K, Object> map, final K k, final V v) {
        if (map == null) {
            return v;
        }
        final V value;
        if ((value = (V) map.get(k)) == null) {
            return v;
        }
        return value;
    }

    private static final Object a(final Map<String, Object> map, final String s) {
        if (map == null) {
            return null;
        }
        return map.get(s);
    }
}
