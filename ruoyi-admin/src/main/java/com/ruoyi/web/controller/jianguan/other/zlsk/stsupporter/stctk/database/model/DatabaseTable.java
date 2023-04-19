package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.MapUtil;
import java.util.*;

public class DatabaseTable
{
    private String a;
    private String b;
    private List<DatabaseField> c;
    private String d;

    public DatabaseTable() {
        this.b = "";
        this.c = new ArrayList<DatabaseField>();
    }

    public static DatabaseTable fromMap(final Map<String, Object> map) {
        return fromMap(map, false);
    }

    public static DatabaseTable fromMap(final Map<String, Object> map, final boolean b) {
        if (map == null) {
            return null;
        }
        final DatabaseTable databaseTable = new DatabaseTable();
        final String string = MapUtil.getString(map, "name", null);
        databaseTable.setKey(MapUtil.getString(map, "key", string));
        databaseTable.setName(string);
        final List<Map> list;
        if ((list = (List<Map>) map.get("fields")) != null) {
            final Iterator<Map> iterator = list.iterator();
            while (iterator.hasNext()) {
                final DatabaseField fromMap;
                if ((fromMap = DatabaseField.fromMap(iterator.next(), b)) != null) {
                    databaseTable.c.add(fromMap);
                }
            }
        }
        if (b) {
            databaseTable.setComment(MapUtil.getString(map, "comment", null));
        }
        return databaseTable;
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

    public List<DatabaseField> getFields() {
        return this.c;
    }

    public void setFields(final List<DatabaseField> c) {
        this.c = c;
    }

    public String getComment() {
        return this.d;
    }

    public void setComment(final String d) {
        this.d = d;
    }

    public DatabaseField getFieldByKey(final String s) {
        if (this.c == null || s == null) {
            return null;
        }
        for (final DatabaseField databaseField : this.c) {
            if (s.equals(databaseField.getKey())) {
                return databaseField;
            }
        }
        return null;
    }

    public DatabaseField getFieldByName(final String s) {
        if (this.c == null || s == null) {
            return null;
        }
        for (final DatabaseField databaseField : this.c) {
            if (s.equals(databaseField.getName())) {
                return databaseField;
            }
        }
        return null;
    }

    public DatabaseTable clone(final boolean b) {
        final DatabaseTable databaseTable;
        (databaseTable = new DatabaseTable()).setKey(this.a);
        databaseTable.setName(this.b);
        databaseTable.setComment(this.d);
        if (b) {
            for (int i = 0; i < this.c.size(); ++i) {
                databaseTable.getFields().add(this.c.get(i).clone());
            }
        }
        else {
            databaseTable.setFields(this.c);
        }
        return databaseTable;
    }

    public Map<String, Object> toMap() {
        final HashMap<String, Object> hashMap;
        (hashMap = new HashMap<String, Object>()).put("key", this.a);
        hashMap.put("name", this.getName());
        final ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        final Iterator<DatabaseField> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().toMap());
        }
        hashMap.put("fields", list);
        hashMap.put("comment", this.d);
        return (Map<String, Object>)hashMap;
    }
}
