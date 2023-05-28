package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 隐蔽工程验收记录分页列表返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-12
 */
@Data
@ApiModel(value = "HiddenProjectAcceptPageVo", description = "隐蔽工程验收记录分页列表返回VO")
public class HiddenProjectAcceptPageVo extends NewBaseVo {

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
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private Set<String> buildUnit;

    private String constructdpts;
    private String supervisorDepts;

    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;
    /**
     * 监理单位
     */
    @ApiModelProperty(value = "监理单位")
    private Set<String> supervisorUnit;

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 施工标段
     */
    @ApiModelProperty(value = "施工标段")
    private String buildSectionName;


    /**
     * 监理标段
     */
    @ApiModelProperty(value = "监理标段")
    private LocalDate supervisorSection;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态描述
     */
    @ApiModelProperty(value = "状态描述")
    private String statusStr;

    /**
     * 分项工程
     */
    @ApiModelProperty(value = "分项工程")
    private String subProject;


    /**
     * 单位、分部工程
     */
    @ApiModelProperty(value = "单位、分部工程")
    private String unit;


    /**
     * 隐蔽工程项目
     */
    @ApiModelProperty(value = "隐蔽工程项目")
    private String hiddenProject;

}
