package com.ruoyi.jianguan.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 管理制度
 *
 * @author qiaoxulin
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_manage_regime")
@ApiModel(value = "ManageRegime对象", description = "管理制度")
public class ManageRegime extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


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
     * 编制人
     */
    @ApiModelProperty(value = "编制人")
    private String compileUser;


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
     * 制度文件
     */
    @ApiModelProperty(value = "制度文件")
    private String attachment;


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


}
