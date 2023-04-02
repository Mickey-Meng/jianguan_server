package com.ruoyi.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @author qiaoxulin
 * @date : 2022/5/26 17:24
 */
@Data
@ApiModel(value = "CompanyInfo", description = "单位合同信息")
public class CompanyInfo {
    /**
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private Set<String> sgdws;

    /**
     * 监理单位
     */
    @ApiModelProperty(value = "监理单位")
    private Set<String> jldws;
    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractCode;

    /**
     * 管理单位
     */
    @ApiModelProperty(value = "管理单位")
    private Set<String> gldws;

    /**
     * 建设单位
     */
    @ApiModelProperty(value = "建设单位")
    private Set<String> jsdws;
    /**
     * 全过程咨询单位
     */
    @ApiModelProperty(value = "全过程咨询单位")
    private Set<String> qgczxdws;
    /**
     * 设计单位
     */
    @ApiModelProperty(value = "设计单位")
    private Set<String> sjdws;
}
