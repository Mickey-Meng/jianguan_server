package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 到场设备报验单保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/26 17:41
 */
@Data
@ApiModel(value = "EquipmentEnterSaveDTO", description = "到场设备报验单保存dto")
public class EquipmentEnterSaveDTO extends SaveDTO {


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
    private List<EquipmentInfo> equipmentInfo;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


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

//    /**
//     * 设备信息
//     */
//    @Data
//    public static class EquipmentInfo {
//
//        /**
//         * 设备类型
//         */
//        @ApiModelProperty(value = "设备类型")
//        private String equipmentType;
//
//        /**
//         * 设备名称
//         */
//        @ApiModelProperty(value = "设备名称")
//        private String equipmentName;
//
//        /**
//         * 规格型号
//         */
//        @ApiModelProperty(value = "规格型号")
//        private String specification;
//
//        /**
//         * 数量
//         */
//        @ApiModelProperty(value = "数量")
//        private Integer num;
//
//        /**
//         * 进场日期
//         */
//        @ApiModelProperty(value = "进场日期")
//        private LocalDate enterDate;
//
//        /**
//         * 技术状况
//         */
//        @ApiModelProperty(value = "技术状况")
//        private String techCondition;
//
//        /**
//         * 拟何用处
//         */
//        @ApiModelProperty(value = "拟何用处")
//        private String useWhere;
//        /**
//         * 备注
//         */
//        @ApiModelProperty(value = "备注")
//        private String remark;
//    }


}
