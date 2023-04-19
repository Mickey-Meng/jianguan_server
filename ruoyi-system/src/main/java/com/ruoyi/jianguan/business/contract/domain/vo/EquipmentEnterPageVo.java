package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 到场设备报验单列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/26 18:26
 */
@Data
@ApiModel(value = "EquipmentEnterPageVo", description = "到场设备报验单列表返回VO")
public class EquipmentEnterPageVo extends NewBaseVo {

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
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private Set<String> buildUnits;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;
    /**
     * 监理单位
     */
    @ApiModelProperty(value = "监理单位")
    private Set<String> supervisorUnits;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String statusStr;

    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private List<EquipmentInfo> equipmentInfos;

}
