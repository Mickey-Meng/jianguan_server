package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.util;

import java.lang.reflect.*;
import java.util.*;
import com.alibaba.fastjson.*;

public class ZListUtil
{
    public static boolean isEmpty(final List<?> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isEmpty(final T[] array) {
        return array == null || array.length == 0;
    }

    public static <T extends Comparable<? super T>> void sort(final List<T> list, final boolean isAscending) {
        Collections.sort(list);
        if (!isAscending) {
            Collections.reverse(list);
        }
    }

    public static <T> List<T> toList(final T[] array) {
        final List<T> result = new ArrayList<T>();
        Collections.addAll(result, array);
        return result;
    }

    public static <T> List<T> singleToList(final T object) {
        final List<T> result = new ArrayList<T>();
        result.add(object);
        return result;
    }

    public static <T> T[] toArray(final List<T> list, final T[] target) {
        return list.toArray(target);
    }

    public static <T> List<T> deepCopy(final List<T> list) {
        final List<T> result = new ArrayList<T>();
        for (final T item : list) {
            result.add(item);
        }
        return result;
    }

    public static <T> List<T> deduplicate(final List<T> list) {
        final Set<T> set = new HashSet<T>();
        final List<T> newList = new ArrayList<T>();
        for (final T element : list) {
            if (set.add(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static <T> List<T> deduplicate(final T[] array) {
        return deduplicate((List<T>)toList((T[])array));
    }

    public static <T> ArrayList<T> deduplicate(final List<T> list, final String filedName) {
        final Set<T> set = new TreeSet<T>(new Comparator<T>() {
            @Override
            public int compare(final T o1, final T o2) {
                final String object1 = ZListUtil.getValueByFiledName(o1, filedName);
                final String object2 = ZListUtil.getValueByFiledName(o2, filedName);
                return object1.compareTo(object2);
            }
        });
        set.addAll((Collection<? extends T>)list);
        return new ArrayList<T>((Collection<? extends T>)set);
    }

    public static <T> List<T> deduplicate(final T[] array, final String name) {
        return (List<T>)deduplicate((List<Object>)toList((T[])array), name);
    }

    public static String getValueByFiledName(final Object object, final String name) {
        String value = "";
        try {
            final Class objectClass = object.getClass();
            final Field field = objectClass.getDeclaredField(name);
            field.setAccessible(true);
            value = String.valueOf(field.get(object));
        }
        catch (Exception exc) {
            value = "";
        }
        return value;
    }

    public static ArrayList<Map<String, Object>> deduplicateMap(final List<Map<String, Object>> list, final String filedName) {
        final Set<Map<String, Object>> set = new TreeSet<Map<String, Object>>(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                final Integer object1 = (Integer) o1.get(filedName);
                final Integer object2 = (Integer) o2.get(filedName);
                return object1.compareTo(object2);
            }
        });
        set.addAll(list);
        return new ArrayList<Map<String, Object>>(set);
    }

    public static List<Map<String, Object>> getTreeByList(final String idField, final String pIdField, final String childrenField, final List<Map<String, Object>> mapList, final int pId) {
        final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (final Map<String, Object> map : mapList) {
            final int parentId = Integer.parseInt(String.valueOf(map.get(pIdField)));
            final int id = Integer.parseInt(String.valueOf(map.get(idField)));
            checkMenuConfig(map);
            if (pId == parentId) {
                final List<Map<String, Object>> temp = getTreeByList(idField, pIdField, childrenField, mapList, id);
                if (temp.size() != 0) {
                    if (map.containsKey(childrenField)) {
                        final List<Map<String, Object>> children = (List<Map<String, Object>>) map.get(childrenField);
                        children.addAll(temp);
                    }
                    else {
                        map.put(childrenField, temp);
                    }
                }
                result.add(map);
            }
        }
        return result;
    }

    public static void checkMenuConfig(final Map<String, Object> mapItem) {
        final String defaultValue = String.valueOf(mapItem.getOrDefault("MENUCONFIG", ""));
        if ("".equals(defaultValue)) {
            return;
        }
        final Map<String, Object> val = new HashMap<String, Object>();
        try {
            final JSONObject jo = new JSONObject(mapItem.get("MENUCONFIG").toString().isEmpty());
            final Iterator<String> ki = (Iterator<String>)jo.entrySet();
            while (ki.hasNext()) {
                final String fn = ki.next();
                final Object ov = jo.get(fn);
                String fv = (ov == null) ? "${NULL}" : ov.toString();
                if (fv.equals("${NULL}")) {
                    fv = "null";
                }
                else if (fv.startsWith("{") && fv.endsWith("}")) {
                    val.put(fn, JSONObject.parse(fv));
                }
                else if (fv.startsWith("[") && fv.endsWith("]")) {
                    final JSONArray jsonArray = JSONArray.parseArray(fv);
                    val.put(fn, jsonArray);
                }
                else {
                    val.put(fn, fv);
                }
            }
            mapItem.put("MENUCONFIG", val);
        }
        catch (JSONException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
}
