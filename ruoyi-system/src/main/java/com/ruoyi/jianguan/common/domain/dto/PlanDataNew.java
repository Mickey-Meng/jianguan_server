package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/11 上午11:57
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class PlanDataNew {
    private String projecttype;
    private String conponenttype;
    private String conponenttypename;
    private int plan;
    private float plannum;
    private int allcount;
    private float allcountnum;

    public float getPlannum() {
        return plannum;
    }

    public void setPlannum(float plannum) {
        this.plannum = plannum;
    }

    public float getAllcountnum() {
        return allcountnum;
    }

    public void setAllcountnum(float allcountnum) {
        this.allcountnum = allcountnum;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }



    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }
}
