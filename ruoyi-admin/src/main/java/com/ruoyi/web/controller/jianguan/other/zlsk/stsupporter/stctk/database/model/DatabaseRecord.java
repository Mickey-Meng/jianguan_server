package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.database.model;

import java.util.*;

public class DatabaseRecord
{
    private String a;
    private Map<String, Object> b;

    public DatabaseRecord() {
        this.a = "";
        this.b = new HashMap<String, Object>();
    }

    public String getTableName() {
        return this.a;
    }

    public void setTableName(final String a) {
        this.a = a;
    }

    public Map<String, Object> getRecord() {
        return this.b;
    }

    public void setRecord(final Map<String, Object> b) {
        this.b = b;
    }
}
