package com.ruoyi.common.core.domain;

import java.util.List;

public class BaseTree {
    private int id;
    private String name;
    private int pid ;
    private String pname;
    private String conponetcode;
    private String x;
    private String y;
    private String z;
    private String mouldid;
    private List<BaseTree> child;

    public List<BaseTree> getChild() {
        return child;
    }

    public void setChild(List<BaseTree> child) {
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getConponetcode() {
        return conponetcode;
    }

    public void setConponetcode(String conponetcode) {
        this.conponetcode = conponetcode;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
    }
}
