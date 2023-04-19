package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.dboperator.mysql;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.dboperator.common.IDbCommand;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model.DatabaseField;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model.DatabaseTable;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import java.text.*;
import java.util.*;

public class MysqlDbCommand extends StSupporterBase implements IDbCommand
{
    private static SimpleDateFormat a;

    @Override
    public String tableName(final String s) {
        return "`" + s + "`";
    }

    @Override
    public String columnName(final String s) {
        return "`" + s + "`";
    }

    @Override
    public String columnType(DatabaseField databaseField) {
        if (databaseField == null) {
            return null;
        }
        final String type = databaseField.getType();
        final int size = databaseField.getSize();
        databaseField.getPrecision();
        final String s = type;
        String s2 = null;
        switch (s) {
            case "tinyint": {
                s2 = ((size < 0) ? "tinyint" : ("tinyint(" + size + ')'));
                break;
            }
            case "smallint": {
                s2 = ((size < 0) ? "smallint" : ("smallint(" + size + ')'));
                break;
            }
            case "int": {
                s2 = ((size < 0) ? "int" : ("int(" + size + ')'));
                break;
            }
            case "bigint": {
                s2 = ((size < 0) ? "bigint" : ("bigint(" + size + ')'));
                break;
            }
            case "decimal": {
                final int precision;
                s2 = "decimal(" + a(databaseField, 18) + ", " + (((precision = (databaseField = databaseField).getPrecision()) < 0) ? 3 : precision) + ")";
                break;
            }
            case "char": {
                s2 = "char(" + a(databaseField, 255) + ")";
                break;
            }
            case "varchar": {
                s2 = "varchar(" + a(databaseField, 255) + ")";
                break;
            }
            case "date": {
                s2 = "date";
                break;
            }
            case "time": {
                s2 = "time(" + a(databaseField, 0) + ")";
                break;
            }
            case "datetime": {
                s2 = "datetime(" + a(databaseField, 0) + ")";
                break;
            }
            case "timestamp": {
                s2 = "timestamp(" + a(databaseField, 3) + ")";
                break;
            }
            case "binary": {
                s2 = "binary(" + a(databaseField, 1) + ")";
                break;
            }
            case "varbinary": {
                s2 = "varbinary(" + a(databaseField, 1) + ")";
                break;
            }
            case "geometry": {
                s2 = null;
                break;
            }
            default: {
                s2 = null;
                break;
            }
        }
        return s2;
    }

    @Override
    public String convertToDate(final Date date) {
        if (date == null) {
            return "null";
        }
        return "'" + MysqlDbCommand.a.format(date) + "'";
    }

    @Override
    public String createTable(final DatabaseTable databaseTable) {
        if (databaseTable == null || databaseTable.getFields() == null || databaseTable.getFields().size() == 0) {
            return "";
        }
        final String name;
        if ((name = databaseTable.getName()).contains("{")) {
            return "";
        }
        final String tableName = this.tableName(name);
        final StringBuilder sb = new StringBuilder();
        final StringBuilder sb2 = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
        sb.append("CREATE TABLE ").append(tableName).append(" (\n");
        for (int i = 0; i < databaseTable.getFields().size(); ++i) {
            final DatabaseField databaseField = databaseTable.getFields().get(i);
            sb.append(this.columnName(databaseField.getName())).append(" ").append(this.columnType(databaseField));
            sb.append(databaseField.isNullable() ? " NULL" : " NOT NULL");
            sb.append((databaseField.getGenerated() > 0) ? " AUTO_INCREMENT" : "");
            if (databaseField.getDefaultValue() != null) {
                sb.append(" DEFAULT '").append(databaseField.getDefaultValue()).append("'");
            }
            if (databaseField.getComment() != null) {
                sb.append(" COMMENT '").append(databaseField.getComment()).append("'");
            }
            sb.append(",\n");
            if (databaseField.isPrimaryKey()) {
                sb2.append(this.columnName(databaseField.getName())).append(',');
            }
        }
        if (sb2.length() > 0) {
            sb2.deleteCharAt(sb2.length() - 1);
            sb.append(" PRIMARY KEY (").append((CharSequence)sb2).append(')');
        }
        else {
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.append(") ");
        if (databaseTable.getComment() != null) {
            sb.append(" COMMENT = '").append(databaseTable.getComment()).append("'");
        }
        sb.append(";\n");
        return sb.toString();
    }

    @Override
    public String createTables(final List<DatabaseTable> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            final String table;
            if ((table = this.createTable(list.get(i))) != null && !table.isEmpty()) {
                sb.append(table).append("\n");
            }
        }
        return sb.toString();
    }

    private static int a(final DatabaseField databaseField, final int n) {
        final int size;
        if ((size = databaseField.getSize()) < 0) {
            return n;
        }
        return size;
    }

    static {
        MysqlDbCommand.a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
