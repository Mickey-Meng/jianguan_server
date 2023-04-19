package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 管理制度分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/13 15:36
 */
@Data
@ApiModel(value = "ManageRegimePageVo", description = "管理制度分页列表返回VO")
public class ManageRegimePageVo extends NewBaseVo {

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
     * 编制人
     */
    @ApiModelProperty(value = "编制人")
    private String compileUser;

    /**
     * 编制人名称
     */
    @ApiModelProperty(value = "编制人名称")
    private String compileUserName;

    /**
     * 编制单位名称
     */
    @ApiModelProperty(value = "编制单位名称")
    private Integer compileDeptName;

    /**
     * 编制日期
     */
    @ApiModelProperty(value = "编制日期")
    private LocalDate compileDate;

    /**
     * 制度内容
     */
    @ApiModelProperty(value = "制度内容")
    private String regimeContent;

    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

    /**
     * 数据状态
     */
    @ApiModelProperty(value = "数据状态 ")
    private String deletedFlagStr;
}
