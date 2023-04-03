package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/24 11:17 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class TypeProject {
    private String projecttype;
    private String conponenttypename;
    private String conponenttype;

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }
}
