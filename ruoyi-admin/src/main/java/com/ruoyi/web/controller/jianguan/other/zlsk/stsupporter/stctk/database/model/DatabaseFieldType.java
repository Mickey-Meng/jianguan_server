package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model;

public class DatabaseFieldType
{
    public static final String TINYINT = "tinyint";
    public static final String SMALLINT = "smallint";
    public static final String INT = "int";
    public static final String BIGINT = "bigint";
    public static final String DECIMAL = "decimal";
    public static final String CHAR = "char";
    public static final String VARCHAR = "varchar";
    public static final String STRING = "varchar";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String DATETIME = "datetime";
    public static final String TIMESTAMP = "timestamp";
    public static final String BINARY = "binary";
    public static final String VARBINARY = "varbinary";
    public static final String GEOMETRY = "geometry";

    public static final int getTypeFlag(String lowerCase) {
        if (lowerCase == null) {
            return -1;
        }
        lowerCase = (lowerCase = lowerCase.toLowerCase());
        int n2 = 0;
        switch (lowerCase) {
            case "tinyint":
            case "smallint":
            case "int":
            case "bigint":
            case "decimal": {
                n2 = 1;
                break;
            }
            case "char":
            case "varchar": {
                n2 = 2;
                break;
            }
            case "date":
            case "time":
            case "datetime":
            case "timestamp": {
                n2 = 3;
                break;
            }
            case "binary":
            case "varbinary": {
                n2 = 4;
                break;
            }
            case "geometry": {
                n2 = 5;
                break;
            }
            default: {
                n2 = -1;
                break;
            }
        }
        return n2;
    }

    public static final boolean isAvailableType(final String s) {
        return getTypeFlag(s) > 0;
    }
}
