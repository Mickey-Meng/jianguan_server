package com.ruoyi.jianguan.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/24 11:49 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class Census {

    @ApiModelProperty(value = "项目类型，桥梁QL，隧道SD,道路LM",required=true)
    private  String projectCode;
    @ApiModelProperty(value = "构件的类型 ,不传就是所有",required=false)
    private  String conponentType;
    @ApiModelProperty(value = "周传week，月传month，季传season",required=true)
    private String type;
    @ApiModelProperty(value = "projectId")
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getConponentType() {
        return conponentType;
    }

    public void setConponentType(String conponentType) {
        this.conponentType = conponentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
