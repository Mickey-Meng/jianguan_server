package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import java.util.*;

public class ListUtil extends StSupporterBase
{
    public static final <T> boolean isNullOrEmpty(final List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> T find(final List<T> list, final StFilter<T> stFilter) {
        if (isNullOrEmpty(list)) {
            return null;
        }
        for (int i = 0; i < list.size(); ++i) {
            final T value = list.get(i);
            if (stFilter.check(value)) {
                return value;
            }
        }
        return null;
    }

    public static <T> List<T> findAll(final List<T> list, final StFilter<T> stFilter) {
        if (list == null) {
            return null;
        }
        final ArrayList<T> list2 = new ArrayList<T>();
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final T value = list.get(i);
                if (stFilter.check(value)) {
                    list2.add(value);
                }
            }
        }
        return list2;
    }

    public static <T> List<T> remove(final List<T> list, final StFilter<T> stFilter) {
        if (list == null) {
            return null;
        }
        final ArrayList<T> list2 = new ArrayList<T>();
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final T value = list.get(i);
                if (stFilter.check(value)) {
                    list.remove(value);
                    --i;
                    list2.add(value);
                }
            }
        }
        return list2;
    }

    public static <T> boolean exists(final List<T> list, final StFilter<T> stFilter) {
        if (isNullOrEmpty(list)) {
            return false;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (stFilter.check(list.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static <T> void forEach(final List<T> list, final StFilter<T> stFilter) {
        if (isNullOrEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (!stFilter.check(list.get(i))) {
                return;
            }
        }
    }
}
