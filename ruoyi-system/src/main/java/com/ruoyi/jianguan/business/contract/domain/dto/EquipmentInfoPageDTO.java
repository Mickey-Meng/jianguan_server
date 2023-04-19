package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备信息分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/8/13 10:44
 */
@Data
@ApiModel(value = "EquipmentInfoPageDTO", description = "设备信息分页查询dto")
public class EquipmentInfoPageDTO extends PageDTO {

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
     * 进场设备id
     */
    @ApiModelProperty(value = "进场设备id")
    private Long enterId;

}
