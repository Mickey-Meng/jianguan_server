package com.ruoyi.czjg.contract.domain.vo;

import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.czjg.contract.domain.dto.BuildContractSaveDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
/**
 * 施工专业分包合同详情返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "BuildContractDetailVo", description = "施工专业分包合同详情返回VO")
public class BuildContractDetailVo extends NewBaseEntity {
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
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;


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

    /**
     * 承包人名称
     */
    @ApiModelProperty(value = "承包人名称")
    private String contractUserName;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


    /**
     * 合同信息
     */
    @ApiModelProperty(value = "合同信息")
    private List<BuildContractSaveDTO.ContractInfo> contractInfo;


    /**
     * 项目经理
     */
    @ApiModelProperty(value = "项目经理")
    private Integer projectManageUser;


    /**
     * 监理办
     */
    @ApiModelProperty(value = "监理办")
    private Integer supervisionUser;


    /**
     * 指挥部
     */
    @ApiModelProperty(value = "指挥部")
    private Integer commandUser;


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
