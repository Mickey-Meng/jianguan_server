package com.ruoyi.jianguan.common.domain.dto;

import java.util.List;

public class PjGirst {
    private int total;
    private int finish;
    private String projectcode;
    private String projectname;
    private String sxtype;
    private String sxname;
    private List<PjGirst> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getSxtype() {
        return sxtype;
    }
    public String gettype() {
        return this.projectcode+"_"+this.projectname;
    }

    public void setSxtype(String sxtype) {
        this.sxtype = sxtype;
    }

    public String getSxname() {
        return sxname;
    }

    public void setSxname(String sxname) {
        this.sxname = sxname;
    }

    public List<PjGirst> getList() {
        return list;
    }

    public void setList(List<PjGirst> list) {
        this.list = list;
    }
}
