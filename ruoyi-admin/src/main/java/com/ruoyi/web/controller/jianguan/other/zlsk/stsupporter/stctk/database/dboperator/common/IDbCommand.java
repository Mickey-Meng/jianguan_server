package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.dboperator.common;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model.DatabaseField;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model.DatabaseTable;
import java.util.*;

public interface IDbCommand
{
    String tableName(final String p0);

    String columnName(final String p0);

    String columnType(final DatabaseField p0);

    String convertToDate(final Date p0);

    String createTable(final DatabaseTable p0);

    String createTables(final List<DatabaseTable> p0);
}
