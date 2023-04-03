package com.ruoyi.czjg.zjrw.domain.dto;

import java.util.List;

public class GqFirst {
    private int total;
    private int finish;
    private String gongqucode;
    private String gongquname;
    private String projectcode;
    private String projectname;
    private List<GqFirst> list;


    public List<GqFirst> getList() {
        return list;
    }

    public void setList(List<GqFirst> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public String gongqu(){
        return this.gongqucode+"_"+this.gongquname;
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

    public String getGongqucode() {
        return gongqucode;
    }

    public void setGongqucode(String gongqucode) {
        this.gongqucode = gongqucode;
    }

    public String getGongquname() {
        return gongquname;
    }

    public void setGongquname(String gongquname) {
        this.gongquname = gongquname;
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
}
