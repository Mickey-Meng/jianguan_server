package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理通知分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 17:00
 */
@Data
@ApiModel(value = "SupervisionNoticePageVo", description = "监理通知分页列表返回VO")
public class SupervisionNoticePageVo extends NewBaseVo {


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
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;


    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;



    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private String statusStr;


}
