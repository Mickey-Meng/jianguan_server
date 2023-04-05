package com.ruoyi.jianguan.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 目标管理分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/13 14:45
 */
@Data
@ApiModel(value = "ManageTargetPageVo", description = "目标管理分页列表返回VO")
public class ManageTargetPageVo extends NewBaseVo {

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
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDate publishDate;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 登记人名称
     */
    @ApiModelProperty(value = "登记人名称")
    private String createName;

    /**
     * 登记人id
     */
    @ApiModelProperty(value = "登记人id")
    private Integer createUserId;

}
