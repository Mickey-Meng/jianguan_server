package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.contract.domain.dto.LaborContractSaveDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * 劳务分包合同详情返回VO
 * @author qiaoxulin
 * @date : 2022/5/20 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LaborContractDetailVo", description = "劳务分包合同详情返回VO")
public class LaborContractDetailVo extends NewBaseEntity {

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
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间")
    private LocalDate startDate;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;


    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractCode;


    /**
     * 承包人
     */
    @ApiModelProperty(value = "承包人")
    private String contractUser;


    /**
     * 合同附件
     */
    @ApiModelProperty(value = "合同附件")
    private List<FileModel> attachment;


    /**
     * 信息
     */
    @ApiModelProperty(value = "信息")
    private List<LaborContractSaveDTO.Information> information;


    /**
     * 项目经理
     */
    @ApiModelProperty(value = "项目经理")
    private Integer projectManageUser;


    /**
     * 专监
     */
    @ApiModelProperty(value = "专监")
    private Integer specialUser;


    /**
     * 总监
     */
    @ApiModelProperty(value = "总监")
    private Integer directorUser;


    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 1不是")
    private Integer draftFlag;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

}
