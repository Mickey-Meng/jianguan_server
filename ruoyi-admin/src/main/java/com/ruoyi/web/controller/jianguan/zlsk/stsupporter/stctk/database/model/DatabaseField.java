package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.model;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.*;
import java.util.*;

public class DatabaseField
{
    private String a;
    private String b;
    private String c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private String h;
    private String i;
    private boolean j;

    public DatabaseField() {
        this.b = "";
        this.c = "";
        this.d = -1;
        this.e = -1;
        this.f = true;
        this.g = 0;
        this.h = null;
        this.i = null;
        this.j = false;
    }

    public DatabaseField(final String b, String lowerCase) {
        this();
        this.b = b;
        this.c = lowerCase;
        if (this.c != null) {
            lowerCase = (lowerCase = this.c.toLowerCase());
            switch (lowerCase) {
                case "tinyint":
                case "smallint":
                case "int":
                case "bigint": {
                    this.d = -1;
                    this.e = 0;
                }
                case "decimal": {
                    this.d = 18;
                    this.e = 3;
                }
                case "char":
                case "varchar": {
                    this.d = 255;
                    this.e = 0;
                }
                case "date":
                case "time":
                case "datetime": {
                    this.d = 0;
                    this.e = 0;
                }
                case "timestamp": {
                    this.d = 0;
                    this.e = 3;
                    break;
                }
            }
        }
    }

    public static DatabaseField fromMap(final Map<String, Object> map) {
        return fromMap(map, false);
    }

    public static DatabaseField fromMap(final Map<String, Object> map, final boolean b) {
        if (map == null) {
            return null;
        }
        final DatabaseField databaseField = new DatabaseField();
        final String string = MapUtil.getString(map, "name", null);
        databaseField.setKey(MapUtil.getString(map, "key", string));
        databaseField.setName(string);
        databaseField.setType(MapUtil.getString(map, "type", null));
        databaseField.setSize(MapUtil.getInt(map, "size", -1));
        databaseField.setPrecision(MapUtil.getInt(map, "precision", -1));
        databaseField.setNullable(MapUtil.getBoolean(map, "nullable", true));
        databaseField.setGenerated(MapUtil.getInt(map, "generated", 0));
        databaseField.setDefaultValue(MapUtil.getString(map, "dv", null));
        if (b) {
            databaseField.setComment(MapUtil.getString(map, "comment", null));
        }
        databaseField.setPrimaryKey(MapUtil.getBoolean(map, "pk", false));
        return databaseField;
    }

    public String getKey() {
        return this.a;
    }

    public void setKey(final String a) {
        this.a = a;
    }

    public String getName() {
        return this.b;
    }

    public void setName(final String b) {
        this.b = b;
    }

    public String getType() {
        return this.c;
    }

    public void setType(final String c) {
        this.c = c;
    }

    public int getSize() {
        return this.d;
    }

    public void setSize(final int d) {
        this.d = d;
    }

    public int getPrecision() {
        return this.e;
    }

    public void setPrecision(final int e) {
        this.e = e;
    }

    public boolean isNullable() {
        return this.f;
    }

    public void setNullable(final boolean f) {
        this.f = f;
    }

    public int getGenerated() {
        return this.g;
    }

    public void setGenerated(final int g) {
        this.g = g;
    }

    public String getDefaultValue() {
        return this.h;
    }

    public void setDefaultValue(final String h) {
        this.h = h;
    }

    public String getComment() {
        return this.i;
    }

    public void setComment(final String i) {
        this.i = i;
    }

    public boolean isPrimaryKey() {
        return this.j;
    }

    public void setPrimaryKey(final boolean j) {
        this.j = j;
    }

    public DatabaseField clone() {
        final DatabaseField databaseField;
        (databaseField = new DatabaseField()).setKey(this.a);
        databaseField.setName(this.b);
        databaseField.setType(this.c);
        databaseField.setSize(this.d);
        databaseField.setPrecision(this.e);
        databaseField.setNullable(this.f);
        databaseField.setGenerated(this.g);
        databaseField.setDefaultValue(this.h);
        databaseField.setComment(this.i);
        databaseField.setPrimaryKey(this.j);
        return databaseField;
    }

    public Map<String, Object> toMap() {
        final HashMap<String, Object> hashMap;
        (hashMap = new HashMap<String, Object>()).put("key", this.a);
        hashMap.put("name", this.getName());
        hashMap.put("type", this.getType());
        hashMap.put("size", (Boolean)(Object)Integer.valueOf(this.getSize()));
        hashMap.put("precision", (Boolean)(Object)Integer.valueOf(this.getPrecision()));
        hashMap.put("nullable", Boolean.valueOf(this.isNullable()));
        hashMap.put("generated", Integer.valueOf(this.getGenerated()));
        hashMap.put("dv", this.getDefaultValue());
        hashMap.put("comment", this.getComment());
        hashMap.put("pk", Boolean.valueOf(this.j));
        return (Map<String, Object>)hashMap;
    }
}
