package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 劳务合同分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/20 15:23
 */
@Data
@ApiModel(value = "LaborContractPageVo", description = "劳务合同分页列表返回VO")
public class LaborContractPageVo extends NewBaseVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 信息填报
     */
    @ApiModelProperty(value = "信息填报")
    private String information;

    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractCode;

    /**
     * 拟劳务合作工程名称
     */
    @ApiModelProperty(value = "拟劳务合作工程名称")
    private Set<String> laborContractProjectName;

    /**
     * 承包人
     */
    @ApiModelProperty(value = "承包人")
    private String contractUser;

    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间")
    private LocalDate startDate;

    /**
     * 状态 0 进行中 1 已完成 2 驳回
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成 2驳回")
    private Integer status;
}
