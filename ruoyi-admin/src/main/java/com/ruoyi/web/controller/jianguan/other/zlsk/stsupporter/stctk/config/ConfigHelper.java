package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.config;

import java.util.*;

public class ConfigHelper
{
    private IConfigTool a;

    public ConfigHelper() {
        this.a = null;
    }

    public Object loadConfig(final String s) {
        return this.a.loadConfig(s);
    }

    public void refreshConfig() {
        this.a.refreshConfig();
    }

    public Object getConfigByKey(final String s, final String s2) {
        return this.a.getConfigByKey(s, s2);
    }

    public Object getCustomConfig(final String s) {
        return this.a.getCustomConfig(s);
    }

    public Object getCustomConfigByKey(final String s) {
        return this.a.getCustomConfigByKey(s);
    }

    public String getCustomConfigString(final String s) {
        final Object customConfig;
        if ((customConfig = this.getCustomConfig(s)) == null) {
            return "";
        }
        return customConfig.toString();
    }

    public Map<String, String> getTableKeyMap() {
        return this.a.getTableKeyMap();
    }

    public List<Map<String, Object>> getTableList() {
        return this.a.getTableList();
    }

    public String[] getTableNameList() {
        try {
            final List<Map<String, Object>> tableList = this.a.getTableList();
            final ArrayList<String> list = new ArrayList<String>();
            if (tableList != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    list.add(tableList.get(i).get("key").toString());
                }
            }
            return list.toArray(new String[list.size()]);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String[] getTableNameList(final String s) {
        try {
            final List<Map<String, Object>> tableList = this.a.getTableList();
            final ArrayList<String> list = new ArrayList<String>();
            if (tableList != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    final Map<String, Object> map;
                    if ((map = tableList.get(i)).get("type").toString().equals(s)) {
                        list.add(map.get("key").toString());
                    }
                }
            }
            return list.toArray(new String[list.size()]);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String[] getDisplayTableNameList() {
        try {
            final List<Map<String, Object>> tableList = this.a.getTableList();
            final ArrayList<String> list = new ArrayList<String>();
            if (tableList != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    final Map<String, Object> map;
                    if (!(map = tableList.get(i)).containsKey("state") || !map.get("state").toString().equals("0")) {
                        list.add(map.get("key").toString());
                    }
                }
            }
            return list.toArray(new String[list.size()]);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public Map<String, Object> getTableInfo(final String s) {
        try {
            final List<Map<String, Object>> tableList;
            if ((tableList = this.a.getTableList()) != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    final Map<String, Object> map;
                    if ((map = tableList.get(i)).get("key").toString().equals(s)) {
                        return map;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }

    public String getTableType(final String s) {
        try {
            final List<Map<String, Object>> tableList;
            if ((tableList = this.a.getTableList()) != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    final Map<String, Object> map;
                    if ((map = tableList.get(i)).get("key").toString().equals(s)) {
                        return map.get("type").toString();
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }

    public List<Map<String, Object>> getTableFields(final String s) {
        return this.a.getTableFields(s);
    }

    public List<Map<String, Object>> getDisplayFields(final String s) {
        final List<Map<String, Object>> tableFields;
        if ((tableFields = this.a.getTableFields(s)) != null) {
            final ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < tableFields.size(); ++i) {
                final Map<String, Object> map;
                if ((Integer.parseInt((map = tableFields.get(i)).get("state").toString()) & 0x1) == 0x1) {
                    list.add(map);
                }
            }
            return list;
        }
        return null;
    }

    public String[] getTableKey(final String s, final String s2) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            final List<Map<String, Object>> tableList = this.a.getTableList();
            final ArrayList<String> list = new ArrayList<String>();
            if (tableList != null) {
                for (int i = 0; i < tableList.size(); ++i) {
                    final Map<String, Object> map;
                    if ((map = tableList.get(i)).get("group").toString().equals(s)) {
                        if (s2 == null || s2.equals("")) {
                            list.add(map.get("key").toString());
                        }
                        else if (map.get("type").toString().equals(s2)) {
                            list.add(map.get("key").toString());
                        }
                    }
                }
            }
            return list.toArray(new String[tableList.size()]);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public Map<String, Object> getFieldInfo(final String s, final String s2) {
        if (s == null || s.equals("") || s2 == null || s2.equals("")) {
            return null;
        }
        try {
            final List<Map<String, Object>> tableFields;
            if ((tableFields = this.a.getTableFields(s)) != null) {
                for (int i = 0; i < tableFields.size(); ++i) {
                    final Map<String, Object> map;
                    if ((map = tableFields.get(i)).get("key").toString().equals(s2)) {
                        return map;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }

    public String getFieldName(final String s, final String s2) {
        final Map<String, Object> fieldInfo;
        if ((fieldInfo = this.getFieldInfo(s, s2)) != null) {
            return fieldInfo.get("field").toString();
        }
        return null;
    }

    public final Map<String, Object> getConfig(final String s) {
        return this.a.getConfig(s);
    }
}
