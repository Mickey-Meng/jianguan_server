package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 退场设备报验单列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:10
 */
@Data
@ApiModel(value = "EquipmentEnterPageVo", description = "退场设备报验单列表返回VO")
public class EquipmentExitPageVo extends NewBaseVo {


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

    private String constructdpts;
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
    private String supervisordpts;
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
}
