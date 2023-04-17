package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.sqlprovider;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.common.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.*;
import liquibase.pro.packaged.V;

import java.util.*;
import java.text.*;

public class StSqlProvider extends StSupporterBase
{
    public String insert(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final DatabaseTable databaseTable;
            if ((databaseTable = (DatabaseTable) map.get("ts")) == null) {
                return "";
            }
            final String name = databaseTable.getName();
            final Map map2 = (Map)map.get("row");
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final StringBuilder sb3 = new StringBuilder();
            final Date date = new Date();
            final Iterator<Map.Entry<String, V>> iterator = map2.entrySet().iterator();
            while (iterator.hasNext()) {
                final Object o;
                if (((Map.Entry<String, Object>)(o = iterator.next())).getValue() != null) {
                    final String s = ((Map.Entry<String, Object>)o).getKey();
                    final DatabaseField fieldByKey;
                    if ((fieldByKey = databaseTable.getFieldByKey(s)) == null) {
                        continue;
                    }
                    final String s2;
                    String s3;
                    if ((s2 = ((((Map.Entry<String, Object>)o).getValue() == null) ? "${NULL}" : ((Map.Entry<String, Object>)o).getValue().toString())).equals("${NULL}")) {
                        s3 = "null";
                    }
                    else if (s2.equals("${NOW}")) {
                        s3 = dbCommand.convertToDate(date);
                    }
                    else {
                        s3 = "#{row." + s + "}";
                    }
                    sb2.append(dbCommand.columnName(fieldByKey.getName())).append(',');
                    sb3.append(s3).append(',');
                }
            }
            if (sb2.length() == 0) {
                return "";
            }
            sb2.deleteCharAt(sb2.length() - 1);
            sb3.deleteCharAt(sb3.length() - 1);
            sb.append("insert into ").append(dbCommand.tableName(name)).append(" (").append((CharSequence)sb2).append(") values(").append((CharSequence)sb3).append(')');
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String insertUc(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final String string = map.get("tableName").toString();
            final Map<String, V> map2 = (Map<String, V>) map.get("row");
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final StringBuilder sb3 = new StringBuilder();
            final Date date = new Date();
            final Iterator<Map.Entry<String, V>> iterator = map2.entrySet().iterator();
            while (iterator.hasNext()) {
                final Object o;
                if (((Map.Entry<String, Object>)(o = iterator.next())).getValue() != null) {
                    final String s = ((Map.Entry<String, Object>)o).getKey();
                    final String s2;
                    String s3;
                    if ((s2 = ((((Map.Entry<String, Object>)o).getValue() == null) ? "${NULL}" : ((Map.Entry<String, Object>)o).getValue().toString())).equals("${NULL}")) {
                        s3 = "null";
                    }
                    else if (s2.equals("${NOW}")) {
                        s3 = dbCommand.convertToDate(date);
                    }
                    else {
                        s3 = "#{row." + s + "}";
                    }
                    sb2.append(dbCommand.columnName(s)).append(',');
                    sb3.append(s3).append(',');
                }
            }
            if (sb2.length() == 0) {
                return "";
            }
            sb2.deleteCharAt(sb2.length() - 1);
            sb3.deleteCharAt(sb3.length() - 1);
            sb.append("insert into ").append(dbCommand.tableName(string)).append(" (").append((CharSequence)sb2).append(") values(").append((CharSequence)sb3).append(')');
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String delete(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final DatabaseTable databaseTable;
            if ((databaseTable = (DatabaseTable) map.get("ts")) == null) {
                return "";
            }
            final String tableName = dbCommand.tableName(databaseTable.getName());
            final StringBuilder sb = new StringBuilder();
            if (map.containsKey("fd") && map.get("fd") != null && Boolean.parseBoolean(map.get("fd").toString())) {
                sb.append("delete from ").append(tableName);
            }
            else {
                sb.append("update ").append(tableName).append(" set ").append(dbCommand.columnName(databaseTable.getFieldByKey(MapUtil.getString(map, "lf", "ststate")).getName())).append(" = -1");
            }
            final String string;
            if (map.containsKey("wc") && map.get("wc") != null && !(string = map.get("wc").toString()).trim().isEmpty()) {
                sb.append(" where ").append(this.a(string, databaseTable, dbCommand));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String deleteUc(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final String tableName = dbCommand.tableName(map.get("tableName").toString());
            final StringBuilder sb = new StringBuilder();
            if (map.containsKey("fd") && map.get("fd") != null && Boolean.parseBoolean(map.get("fd").toString())) {
                sb.append("delete from ").append(tableName);
            }
            else {
                sb.append("update ").append(tableName).append(" set ").append(dbCommand.columnName(MapUtil.getString(map, "lf", "ststate"))).append(" = -1");
            }
            final String string;
            if (map.containsKey("wc") && map.get("wc") != null && !(string = map.get("wc").toString()).trim().isEmpty()) {
                sb.append(" where ").append(string);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String update(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final DatabaseTable databaseTable;
            if ((databaseTable = (DatabaseTable) map.get("ts")) == null) {
                return "";
            }
            final String name = databaseTable.getName();
            final Map map2 = (Map)map.get("row");
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final Date date = new Date();
            final Iterator<Map.Entry<String, V>> iterator = map2.entrySet().iterator();
            while (iterator.hasNext()) {
                final Object o;
                if (((Map.Entry<String, Object>)(o = iterator.next())).getValue() != null) {
                    final String s = ((Map.Entry<String, Object>)o).getKey();
                    final DatabaseField fieldByKey;
                    if ((fieldByKey = databaseTable.getFieldByKey(s)) == null) {
                        continue;
                    }
                    final String s2;
                    String s3;
                    if ((s2 = ((((Map.Entry<String, Object>)o).getValue() == null) ? "${NULL}" : ((Map.Entry<String, Object>)o).getValue().toString())).equals("${NULL}")) {
                        s3 = "null";
                    }
                    else if (s2.equals("${NOW}")) {
                        s3 = dbCommand.convertToDate(date);
                    }
                    else {
                        s3 = "#{row." + s + "}";
                    }
                    sb2.append(dbCommand.columnName(fieldByKey.getName())).append(" = ").append(s3).append(',');
                }
            }
            if (sb2.length() == 0) {
                return "";
            }
            sb.append("update ").append(dbCommand.tableName(name)).append(" set ").append((CharSequence)sb2.deleteCharAt(sb2.length() - 1));
            final String string;
            if (map.containsKey("wc") && map.get("wc") != null && !(string = map.get("wc").toString()).trim().isEmpty()) {
                sb.append(" where ").append(this.a(string, databaseTable, dbCommand));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String updateUc(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final String string = map.get("tableName").toString();
            final Map<String, V> map2 = (Map<String, V>) map.get("row");
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final Date date = new Date();
            final Iterator<Map.Entry<String, V>> iterator = map2.entrySet().iterator();
            while (iterator.hasNext()) {
                final Object o;
                if (((Map.Entry<String, Object>)(o = iterator.next())).getValue() != null) {
                    final String s = ((Map.Entry<String, Object>)o).getKey();
                    final String s2;
                    String s3;
                    if ((s2 = ((((Map.Entry<String, Object>)o).getValue() == null) ? "${NULL}" : ((Map.Entry<String, Object>)o).getValue().toString())).equals("${NULL}")) {
                        s3 = "null";
                    }
                    else if (s2.equals("${NOW}")) {
                        s3 = dbCommand.convertToDate(date);
                    }
                    else {
                        s3 = "#{row." + s + "}";
                    }
                    sb2.append(dbCommand.columnName(s)).append(" = ").append(s3).append(',');
                }
            }
            if (sb2.length() == 0) {
                return "";
            }
            sb.append("update ").append(dbCommand.tableName(string)).append(" set ").append((CharSequence)sb2.deleteCharAt(sb2.length() - 1));
            final String string2;
            if (map.containsKey("wc") && map.get("wc") != null && !(string2 = map.get("wc").toString()).trim().isEmpty()) {
                sb.append(" where ").append(string2);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String select(final Map<String, Object> map) {
        try {
            return map.get("sql").toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String selectTable(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            return this.a(map.get("sql").toString(), (DatabaseTable) map.get("ts"), dbCommand);
        }
        catch (Exception ex) {
            return "";
        }
    }

    public String selectTables(final Map<String, Object> map) {
        try {
            final IDbCommand dbCommand;
            if ((dbCommand = StCommandManager.getDbCommand(map.get("dbType").toString())) == null) {
                return "";
            }
            final List<DatabaseTable> list = (List<DatabaseTable>) map.get("tsl");
            final String string = map.get("sql").toString();
            final List<DatabaseTable> list2 = list;
            final IDbCommand dbCommand2 = dbCommand;
            final List<DatabaseTable> list3 = list2;
            final String s = string;
            if (list3 == null) {
                return s;
            }
            final Map<Object, Object> hashMap = new HashMap<Object, Object>();
            final Iterator<DatabaseTable> iterator = list3.iterator();
            while (iterator.hasNext()) {
                final DatabaseTable databaseTable;
                final String key = (databaseTable = iterator.next()).getKey();
                final String tableName = dbCommand2.tableName(databaseTable.getName());
                hashMap.put(key, tableName);
                final List<DatabaseField> fields;
                if ((fields = databaseTable.getFields()) != null) {
                    for (final DatabaseField databaseField : fields) {
                        hashMap.put(key + "." + databaseField.getKey(), tableName + "." + dbCommand2.columnName(databaseField.getName()));
                    }
                }
            }
            //return a(s, hashMap);
            return s;
        }
        catch (Exception ex) {
            return "";
        }
    }

    private String a(final String s, final DatabaseTable databaseTable, final IDbCommand dbCommand) {
        final List<DatabaseField> fields;
        if ((fields = databaseTable.getFields()) == null) {
            return s;
        }
        final Map<Object, Object> hashMap = new HashMap<Object, Object>();
        for (final DatabaseField databaseField : fields) {
            hashMap.put(databaseField.getKey(), dbCommand.columnName(databaseField.getName()));
        }
        hashMap.put(databaseTable.getKey(), dbCommand.tableName(databaseTable.getName()));
        return s;
    }

    private static String a(final String s, final Map<String, Object> map) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(500);
        final StringBuilder sb2 = new StringBuilder(50);
        final StringBuilder sb3 = new StringBuilder(10);
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1;
            if ((char1 = s.charAt(i)) == '&') {
                if (sb3.length() > 0) {
                    sb.append((CharSequence)sb3);
                    sb3.delete(0, sb3.length());
                }
                if (sb2.length() > 0) {
                    sb.append((CharSequence)sb2);
                    sb2.delete(0, sb2.length());
                }
                sb3.append(char1);
                n = 1;
                n2 = 0;
            }
            else if (char1 == '{') {
                if (n != 0) {
                    if (n2 != 0) {
                        if (sb3.length() > 0) {
                            sb.append((CharSequence)sb3);
                            sb3.delete(0, sb3.length());
                        }
                        if (sb2.length() > 0) {
                            sb.append((CharSequence)sb2);
                            sb2.delete(0, sb2.length());
                        }
                        sb.append(char1);
                        n = 0;
                        n2 = 0;
                    }
                    else {
                        sb3.append(char1);
                        n2 = 1;
                    }
                }
                else {
                    if (sb3.length() > 0) {
                        sb.append((CharSequence)sb3);
                        sb3.delete(0, sb3.length());
                    }
                    if (sb2.length() > 0) {
                        sb.append((CharSequence)sb2);
                        sb2.delete(0, sb2.length());
                    }
                    sb.append(char1);
                    n = 0;
                    n2 = 0;
                }
            }
            else if (char1 == '}') {
                if (n != 0 && n2 != 0) {
                    final String string = sb2.toString();
                    if (map.containsKey(string)) {
                        sb.append(map.get(string).toString());
                    }
                    else {
                        sb.append(string);
                    }
                    sb2.delete(0, sb2.length());
                    sb3.delete(0, sb3.length());
                }
                else {
                    if (sb3.length() > 0) {
                        sb.append((CharSequence)sb3);
                        sb3.delete(0, sb3.length());
                    }
                    if (sb2.length() > 0) {
                        sb.append((CharSequence)sb2);
                        sb2.delete(0, sb2.length());
                    }
                    sb.append(char1);
                }
                n = 0;
                n2 = 0;
            }
            else if (n != 0 && n2 != 0) {
                sb2.append(char1);
            }
            else {
                if (sb3.length() > 0) {
                    sb.append((CharSequence)sb3);
                    sb3.delete(0, sb3.length());
                }
                sb.append(char1);
            }
        }
        if (sb3.length() > 0) {
            sb.append((CharSequence)sb3);
            sb3.delete(0, sb3.length());
        }
        if (sb2.length() > 0) {
            sb.append((CharSequence)sb2);
            sb2.delete(0, sb2.length());
        }
        return sb.toString();
    }

    static {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
