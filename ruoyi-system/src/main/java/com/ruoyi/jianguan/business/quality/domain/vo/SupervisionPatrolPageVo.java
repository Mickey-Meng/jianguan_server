package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理巡视分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/10 17:26
 */
@Data
@ApiModel(value = "SupervisionPatrolPageVo", description = "监理巡视分页列表返回VO")
public class SupervisionPatrolPageVo extends NewBaseVo {

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
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 巡视地点
     */
    @ApiModelProperty(value = "巡视地点")
    private String patrolPlace;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createName;

}
