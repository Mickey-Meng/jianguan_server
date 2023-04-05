package com.ruoyi.jianguan.contract.domain.vo;

import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 退场设备报验单详情返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:02
 */
@Data
@ApiModel(value = "BuildTechBottomPageVo", description = "退场设备报验单详情返回VO")
public class EquipmentExitDetailVo extends NewBaseEntity {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 进场设备id
     */
    @ApiModelProperty(value = "进场设备id")
    private Long enterId;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 标段id
     */
    @ApiModelProperty(value = "标段id")
    private Integer buildSection;

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 监理办
     */
    @ApiModelProperty(value = "监理办")
    private String supervisionBan;


    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private List<EquipmentInfo> equipmentInfo;


    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private String statusStr;


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
