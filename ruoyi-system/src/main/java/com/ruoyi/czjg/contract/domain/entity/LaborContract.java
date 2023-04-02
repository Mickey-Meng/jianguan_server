package com.ruoyi.czjg.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 劳务分包合同
 *
 * @author qiaoxulin
 * @since 2022-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_labor_contract")
@ApiModel(value = "LaborContract对象", description = "劳务分包合同")
public class LaborContract extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


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
    private String attachment;


    /**
     * 信息
     */
    @ApiModelProperty(value = "信息")
    private String information;


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

    /**
     * 状态 0 进行中 1已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1已完成")
    private Integer status;
}
