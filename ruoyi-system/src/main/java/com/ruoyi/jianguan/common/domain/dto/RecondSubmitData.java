package com.ruoyi.jianguan.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/14 2:22 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Data
public class RecondSubmitData {

    @ApiModelProperty(value = "记录id",required=true)
    private Integer recondid;

    @ApiModelProperty(value = "现场图片",required=true)
    private String url;
    @ApiModelProperty(value = "检查状态 1通过，2驳回",required=true)
    private Integer result ;
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
    private Integer produceId;

    @ApiModelProperty(value = "需要抄送的用户id(字符串表示,','隔开)")
    private String copyUsers;
}
