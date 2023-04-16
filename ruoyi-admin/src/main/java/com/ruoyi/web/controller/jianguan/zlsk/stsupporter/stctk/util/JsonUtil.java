package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import java.util.*;

import liquibase.pro.packaged.K;
import liquibase.pro.packaged.V;

public class JsonUtil extends StSupporterBase
{
    public static JSONObject mapToJsonObject(final Map<String, Object> map) {
        return mapToJsonObject(map, 0);
    }

    public static JSONObject mapToJsonObject(final Map<String, Object> map, final int n) {
        if (map == null) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject();
        final Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> entry;
            String s = (entry = iterator.next()).getKey();
            if (n > 0) {
                s = s.toUpperCase();
            }
            else if (n < 0) {
                s = s.toLowerCase();
            }
            jsonObject.put(s, entry.getValue());
        }
        return jsonObject;
    }

    public static JSONArray listToJsonArray(final List<Map<String, Object>> list) {
        return listToJsonArray(list, 0);
    }

    public static JSONArray listToJsonArray(final List<Map<String, Object>> list, final int n) {
        if (list == null) {
            return null;
        }
        final JSONArray jsonArray = new JSONArray();
        for (final Map<String, Object> map : list) {
            final JSONObject jsonObject = new JSONObject();
            final Iterator<Map.Entry<String, Object>> iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                final Object o;
                String s = ((Map.Entry<String, Object>)(o = iterator2.next())).getKey();
                if (n > 0) {
                    s = s.toUpperCase();
                }
                else if (n < 0) {
                    s = s.toLowerCase();
                }
                jsonObject.put(s, ((Map.Entry<K, Object>)o).getValue());
            }
            jsonArray.put((Object)jsonObject);
        }
        return jsonArray;
    }
}
