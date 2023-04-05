package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 退场设备报验单保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 16:52
 */
@Data
@ApiModel(value = "EquipmentExitSaveDTO", description = "退场设备报验单保存dto")
public class EquipmentExitSaveDTO extends SaveDTO {

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
     * 设备信息
     */
    @Data
    public static class EquipmentNum {
        /**
         * 设备信息id
         */
        @ApiModelProperty(value = "设备信息id")
        private Long equipmentId;

        /**
         * 设备数量
         */
        @ApiModelProperty(value = "设备数量")
        private Integer num;
    }

}
