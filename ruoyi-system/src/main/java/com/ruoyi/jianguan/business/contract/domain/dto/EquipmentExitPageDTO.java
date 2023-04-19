package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 退场设备报验单分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:08
 */
@Data
@ApiModel(value = "EquipmentExitPageDTO", description = "退场设备报验单分页查询dto")
public class EquipmentExitPageDTO extends PageDTO {

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

}
