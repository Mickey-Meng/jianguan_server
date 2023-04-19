package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.config;

import java.util.*;

public interface IConfigTool
{
    Object loadConfig(final String p0);

    void refreshConfig();

    Object getConfigByKey(final String p0, final String p1);

    Object getCustomConfig(final String p0);

    Object getCustomConfigByKey(final String p0);

    String getCurrentConfigKey();

    Map<String, Object> getConfig(final String p0);

    Map<String, String> getTableKeyMap();

    Map<String, String> getTableKeyMap(final String p0);

    List<Map<String, Object>> getTableList();

    List<Map<String, Object>> getTableList(final String p0);

    List<Map<String, Object>> getTableFields(final String p0);

    List<Map<String, Object>> getTableFields(final String p0, final String p1);
}
