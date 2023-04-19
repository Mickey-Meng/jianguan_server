package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model;

public class DatabaseType
{
    public static final String DEFAULT = "mysql";
    public static final String MYSQL = "mysql";

    public static String[] getAvailableType() {
        return new String[] { "mysql" };
    }

    public static boolean isAvailableType(final String s) {
        String[] availableType;
        for (int length = (availableType = getAvailableType()).length, i = 0; i < length; ++i) {
            if (availableType[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
}
