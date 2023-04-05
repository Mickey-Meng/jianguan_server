package com.ruoyi.jianguan.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/14 2:22 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class RecondSubmitData {

    @ApiModelProperty(value = "记录id",required=true)
    private Integer recondid;

    @ApiModelProperty(value = "现场图片",required=true)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty(value = "检查状态 1通过，2驳回",required=true)
    private Integer result ;

    public Integer getRecondid() {
        return recondid;
    }

    public void setRecondid(Integer recondid) {
        this.recondid = recondid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getStandbyrecode() {
        return standbyrecode;
    }

    public void setStandbyrecode(String standbyrecode) {
        this.standbyrecode = standbyrecode;
    }


    @ApiModelProperty(value = "施工上传举牌照片",required=false)
    private String accrecodeurl;
    @ApiModelProperty(value = "监理上传举牌照片",required=false)
    private String testurl;
    @ApiModelProperty(value = "附件",required=false)
    private String remark;
    @ApiModelProperty(value = "监理审核上传附件",required = false)
    private String standbyrecode;

    //新增构件进度完成逻辑
    @ApiModelProperty(value = "构件类型编码", required = false)
    private String conponenttype;
    @ApiModelProperty(value = "工序id", required = false)
    private Integer produceid;


    @ApiModelProperty(value = "需要抄送的用户id(字符串表示,','隔开)")
    private String copyUsers;

    public String getAccrecodeurl() {
        return accrecodeurl;
    }

    public void setAccrecodeurl(String accrecodeurl) {
        this.accrecodeurl = accrecodeurl;
    }

    public String getTesturl() {
        return testurl;
    }

    public void setTesturl(String testurl) {
        this.testurl = testurl;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
    }

    public String getCopyUsers() {
        return copyUsers;
    }

    public void setCopyUsers(String copyUsers) {
        this.copyUsers = copyUsers;
    }
}
