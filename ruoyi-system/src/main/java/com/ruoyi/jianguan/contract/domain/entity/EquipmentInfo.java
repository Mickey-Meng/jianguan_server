package com.ruoyi.jianguan.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设备信息
 *
 * @author qiaoxulin
 * @since 2022-08-13
 */
@Data
@Accessors(chain = true)
@TableName("zz_equipment_info")
@ApiModel(value = "EquipmentInfo对象", description = "设备信息")
public class EquipmentInfo {

    private static final long serialVersionUID = 1L;


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
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;


    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;


    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    private String specification;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer num;


    /**
     * 进场日期
     */
    @ApiModelProperty(value = "进场日期")
    private String enterDate;


    /**
     * 技术状况
     */
    @ApiModelProperty(value = "技术状况")
    private String techCondition;


    /**
     * 拟用何处
     */
    @ApiModelProperty(value = "拟用何处")
    private String useWhere;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 拟退场日期
     */
    @ApiModelProperty(value = "拟退场日期")
    private String exitDate;

    /**
     * 退场原因
     */
    @ApiModelProperty(value = "退场原因")
    private String exitReason;

}
