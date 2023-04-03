package com.ruoyi.czjg.zjrw.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/17 16:40
 * @Version : 1.0
 * @Description :
 **/
public class SsFProjectCompany {

    /**
     * 单位id
     */
    private Integer companyid;
    /**
     * 项目id
     */
    private Integer projectid;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sttime;

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }
}
