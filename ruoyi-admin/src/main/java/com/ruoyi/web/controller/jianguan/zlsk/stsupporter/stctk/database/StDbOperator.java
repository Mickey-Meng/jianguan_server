package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.mapper.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.common.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.model.*;
import java.util.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.*;

public class StDbOperator extends StSupporterBase
{
    @Autowired
    private ICommonMapper commonMapper;
    private String a;
    private IDbCommand b;

    public StDbOperator() {
        this.a = "mysql";
        this.a();
    }

    public StDbOperator(final String a) {
        this.a = a;
        this.a();
    }

    public IDbCommand getDbCommand() {
        if (this.b == null) {
            this.b = StCommandManager.getDbCommand(this.a);
        }
        return this.b;
    }

    public int insert(final String s) {
        return this.commonMapper.insert(s, null);
    }

    public int insert(final DatabaseTable databaseTable, final Map<String, Object> map) {
        if (map == null) {
            return -3001;
        }
        if (databaseTable == null) {
            return -1018;
        }
        return this.commonMapper.insertRow(this.a, databaseTable, map);
    }

    public int insert(final String s, final Map<String, Object> map) {
        if (s == null || s.isEmpty() || map == null) {
            return -3001;
        }
        return this.commonMapper.insertRowUc(this.a, s, map);
    }

    public int delete(final String s) {
        return this.commonMapper.delete(s, null);
    }

    public int delete(final DatabaseTable databaseTable, final String s, final Map<String, Object> map, final boolean b, final String s2) {
        if (databaseTable == null) {
            return -1018;
        }
        return this.commonMapper.deleteRows(this.a, databaseTable, s, map, b, s2);
    }

    public int delete(final String s, final String s2, final Map<String, Object> map, final boolean b, final String s3) {
        if (s == null) {
            return -3001;
        }
        return this.commonMapper.deleteRowsUc(this.a, s, s2, map, b, s3);
    }

    public int update(final String s) {
        return this.commonMapper.update(s, null);
    }

    public int update(final DatabaseTable databaseTable, final Map<String, Object> map, final String s, final Map<String, Object> map2) {
        if (map == null) {
            return -3001;
        }
        if (databaseTable == null) {
            return -1018;
        }
        return this.commonMapper.updateRows(this.a, databaseTable, map, s, map2);
    }

    public int update(final String s, final Map<String, Object> map, final String s2, final Map<String, Object> map2) {
        if (s == null || s.isEmpty() || map == null) {
            return -3001;
        }
        return this.commonMapper.updateRowsUc(this.a, s, map, s2, map2);
    }

    public List<Map<String, Object>> select(final String s) {
        return this.commonMapper.select(s, null);
    }

    public List<Map<String, Object>> select(final String s, final Object... array) {
        return this.commonMapper.selectWithParams(s, array);
    }

    public List<Map<String, Object>> selectWithParam(final String s, final Object[] array) {
        return this.commonMapper.selectWithArrayParam(this.a, null, s, array);
    }

    public List<Map<String, Object>> selectWithParam(final String s, final Map<String, Object> map) {
        return this.commonMapper.selectWithMapParam(this.a, null, s, map);
    }

    public List<Map<String, Object>> selectWithParam(final List<DatabaseTable> list, final String s, final Object[] array) {
        return this.commonMapper.selectWithArrayParam(this.a, list, s, array);
    }

    public List<Map<String, Object>> selectWithParam(final List<DatabaseTable> list, final String s, final Map<String, Object> map) {
        return this.commonMapper.selectWithMapParam(this.a, list, s, map);
    }

    public List<Map<String, Object>> selectTableWithMapParam(final DatabaseTable databaseTable, final String s, final Map<String, Object> map) {
        return this.commonMapper.selectTableWithMapParam(this.a, databaseTable, s, map);
    }

    public List<Map<String, Object>> executeSql(final String s) {
        return this.select(s);
    }

    public List<Map<String, Object>> executeSql(final String s, final Object[] array) {
        return this.selectWithParam(s, array);
    }

    public List<Map<String, Object>> executeSql(final String s, final Map<String, Object> map) {
        return this.selectWithParam(s, map);
    }

    public int executeNonQuery(final String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        final String lowerCase;
        if ((lowerCase = s.trim().toLowerCase()).startsWith("insert")) {
            return this.insert(s);
        }
        if (lowerCase.startsWith("delete")) {
            return this.delete(s);
        }
        if (lowerCase.startsWith("update") || lowerCase.startsWith("create") || lowerCase.startsWith("drop") || lowerCase.startsWith("truncate")) {
            return this.update(s);
        }
        return -1;
    }

    public int executeNonQuery(final String s, final Map<String, Object> map) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        final String lowerCase;
        if ((lowerCase = s.trim().toLowerCase()).startsWith("insert")) {
            return this.commonMapper.insert(s, map);
        }
        if (lowerCase.startsWith("delete")) {
            return this.commonMapper.delete(s, map);
        }
        if (lowerCase.startsWith("update") || lowerCase.startsWith("create") || lowerCase.startsWith("drop") || lowerCase.startsWith("truncate")) {
            return this.commonMapper.update(s, map);
        }
        return -1;
    }

    public Object executeScalar(final String s) {
        return this.executeScalar(s, null);
    }

    public Object executeScalar(final String s, final Map<String, Object> map) {
        final List<Map<String, Object>> selectWithParam;
        if ((selectWithParam = this.selectWithParam(s, map)) == null || selectWithParam.size() == 0) {
            return null;
        }
        final Iterator<Object> iterator;
        if ((iterator = selectWithParam.get(0).values().iterator()).hasNext()) {
            return iterator.next();
        }
        return null;
    }

    public STStatus createTable(final DatabaseTable databaseTable) {
        final STStatus stStatus = new STStatus();
        if (databaseTable == null) {
            stStatus.setMeow(-3001);
            stStatus.setMsg("\u672a\u6307\u5b9a\u76ee\u6807\u8868\u7ed3\u6784");
            return stStatus;
        }
        final IDbCommand dbCommand;
        if ((dbCommand = this.getDbCommand()) == null) {
            stStatus.setMeow(-3005);
            stStatus.setMsg("\u4e0d\u652f\u6301\u7684\u6570\u636e\u5e93\u7c7b\u578b");
            return stStatus;
        }
        final String table;
        if ((table = dbCommand.createTable(databaseTable)) == null || table.isEmpty()) {
            stStatus.setMeow(-5002);
            stStatus.setMsg("\u8868\u7ed3\u6784\u975e\u6cd5");
            return stStatus;
        }
        if (this.update(table) < 0) {
            stStatus.setMeow(-1020);
            stStatus.setMsg("\u5efa\u8868\u5931\u8d25");
        }
        stStatus.setMeow(0);
        return stStatus;
    }

    public STStatus createTables(final List<DatabaseTable> list) {
        final STStatus stStatus = new STStatus();
        if (list == null || list.size() == 0) {
            stStatus.setMeow(-3001);
            stStatus.setMsg("\u672a\u6307\u5b9a\u76ee\u6807\u8868");
            return stStatus;
        }
        final IDbCommand dbCommand;
        if ((dbCommand = this.getDbCommand()) == null) {
            stStatus.setMeow(-3005);
            stStatus.setMsg("\u4e0d\u652f\u6301\u7684\u6570\u636e\u5e93\u7c7b\u578b");
            return stStatus;
        }
        final String tables;
        if ((tables = dbCommand.createTables(list)) == null || tables.isEmpty()) {
            stStatus.setMeow(-5002);
            stStatus.setMsg("\u8868\u7ed3\u6784\u975e\u6cd5");
            return stStatus;
        }
        if (this.update(tables) < 0) {
            stStatus.setMeow(-1020);
            stStatus.setMsg("\u5efa\u8868\u5931\u8d25");
        }
        stStatus.setMeow(0);
        return stStatus;
    }

    private void a() {
        // this.commonMapper = SpringContextUtil.getBean(ICommonMapper.class);
    }
}
