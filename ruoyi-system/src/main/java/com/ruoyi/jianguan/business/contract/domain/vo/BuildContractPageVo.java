package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工专业分包合同分页列表返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "BuildContractPageVo", description = "施工专业分包合同分页列表返回VO")
public class BuildContractPageVo extends NewBaseVo {

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
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;


    /**
     * 承包人
     */
    @ApiModelProperty(value = "承包人")
    private String contractUser;


}
