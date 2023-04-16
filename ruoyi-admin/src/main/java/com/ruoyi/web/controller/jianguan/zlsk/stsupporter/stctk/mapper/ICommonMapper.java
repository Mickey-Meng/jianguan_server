package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.model.DatabaseTable;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.sqlprovider.StSqlProvider;
import org.apache.ibatis.annotations.*;

public interface ICommonMapper
{
    @Insert({ "${sql}" })
    int insert(@Param("sql") final String p0, @Param("params") final Map<String, Object> p1);

    @Delete({ "${sql}" })
    int delete(@Param("sql") final String p0, @Param("params") final Map<String, Object> p1);

    @Update({ "${sql}" })
    int update(@Param("sql") final String p0, @Param("params") final Map<String, Object> p1);

    @Select({ "${sql}" })
    List<Map<String, Object>> select(@Param("sql") final String p0, @Param("params") final Map<String, Object> p1);

    @InsertProvider(type = StSqlProvider.class, method = "insert")
    int insertRow(@Param("dbType") final String p0, @Param("ts") final DatabaseTable p1, @Param("row") final Map<String, Object> p2);

    @InsertProvider(type = StSqlProvider.class, method = "insertUc")
    int insertRowUc(@Param("dbType") final String p0, @Param("tableName") final String p1, @Param("row") final Map<String, Object> p2);

    @DeleteProvider(type = StSqlProvider.class, method = "delete")
    int deleteRows(@Param("dbType") final String p0, @Param("ts") final DatabaseTable p1, @Param("wc") final String p2, @Param("params") final Map<String, Object> p3, @Param("fd") final boolean p4, @Param("lf") final String p5);

    @DeleteProvider(type = StSqlProvider.class, method = "deleteUc")
    int deleteRowsUc(@Param("dbType") final String p0, @Param("tableName") final String p1, @Param("wc") final String p2, @Param("params") final Map<String, Object> p3, @Param("fd") final boolean p4, @Param("lf") final String p5);

    @UpdateProvider(type = StSqlProvider.class, method = "update")
    int updateRows(@Param("dbType") final String p0, @Param("ts") final DatabaseTable p1, @Param("row") final Map<String, Object> p2, @Param("wc") final String p3, @Param("params") final Map<String, Object> p4);

    @UpdateProvider(type = StSqlProvider.class, method = "updateUc")
    int updateRowsUc(@Param("dbType") final String p0, @Param("tableName") final String p1, @Param("row") final Map<String, Object> p2, @Param("wc") final String p3, @Param("params") final Map<String, Object> p4);

    @SelectProvider(type = StSqlProvider.class, method = "select")
    List<Map<String, Object>> selectWithParams(@Param("sql") final String p0, final Object... p1);

    @SelectProvider(type = StSqlProvider.class, method = "selectTables")
    List<Map<String, Object>> selectWithArrayParam(@Param("dbType") final String p0, @Param("tsl") final List<DatabaseTable> p1, @Param("sql") final String p2, @Param("params") final Object[] p3);

    @SelectProvider(type = StSqlProvider.class, method = "selectTables")
    List<Map<String, Object>> selectWithMapParam(@Param("dbType") final String p0, @Param("tsl") final List<DatabaseTable> p1, @Param("sql") final String p2, @Param("params") final Map<String, Object> p3);

    @SelectProvider(type = StSqlProvider.class, method = "selectTable")
    List<Map<String, Object>> selectTableWithMapParam(@Param("dbType") final String p0, @Param("ts") final DatabaseTable p1, @Param("sql") final String p2, @Param("params") final Map<String, Object> p3);
}
