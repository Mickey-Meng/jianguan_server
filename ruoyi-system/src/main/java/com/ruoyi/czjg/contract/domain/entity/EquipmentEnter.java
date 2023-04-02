package com.ruoyi.czjg.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 设备进场报验
* @author qiaoxulin
* @since 2022-05-26
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
@TableName("zz_equipment_enter")
@ApiModel(value="EquipmentEnter对象", description="设备进场报验")
public class EquipmentEnter extends NewBaseEntity {

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
    private String equipmentInfo;


    /**
    * 附件
    */
    @ApiModelProperty(value = "附件")
    private String attachment;


    /**
    * 状态 0 进行中 1 已完成
    */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;


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
