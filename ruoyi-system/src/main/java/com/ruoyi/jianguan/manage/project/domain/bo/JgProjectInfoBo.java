package com.ruoyi.jianguan.manage.project.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息业务对象 jg_project_info
 *
 * @author ruoyi
 * @date 2023-04-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class JgProjectInfoBo extends BaseEntity {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectName;

    /**
     * 编码
     */
    @NotBlank(message = "项目编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectCode;

    /**
     * 父级ID
     */
    @NotNull(message = "项目父级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;

    /**
     * 部门类型
     */
    @NotBlank(message = "项目区域不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectArea;

    /**
     * 级别
     */
    private Integer groupLevel;

    /**
     * 状态（0正常 1停用）|-1: 删除, 0: 冻结, 1: 正常
     */
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 是否显示
     */
    @NotBlank(message = "是否显示不能为空", groups = { AddGroup.class, EditGroup.class })
    private String visible;

    /**
     * 顺序
     */
    private Long orderNum;

    /**
     * 组织ID
     */
    private String groupId;

    /**
     * 是否自管：0-否，1-是
     */
    private Long isAuto;

    /**
     * 项目照片
     */
   // @NotBlank(message = "项目照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectPic;

    /**
     * 合同号
     */
   // @NotBlank(message = "合同号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractNum;

    /**
     * 坐标
     */
   // @NotBlank(message = "坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String coordinate;

    /**
     * 投资金额（万元）
     */
   // @NotNull(message = "投资金额（万元）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long investment;

    /**
     * 项目类型（交通类、市政类、房建类、其他类）
     */
   // @NotBlank(message = "项目类型（交通类、市政类、房建类、其他类）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectType;

    /**
     * 项目点
     */
   // @NotBlank(message = "项目点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectPoint;

    /**
     * 项目线
     */
   // @NotBlank(message = "项目线不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectLine;

    /**
     * 项目面
     */
   // @NotBlank(message = "项目面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectSurface;

    /**
     * 项目简介
     */
   // @NotBlank(message = "项目简介不能为空", groups = { AddGroup.class, EditGroup.class })
    private String introduction;

    /**
     * 备注
     */
    private String remark;


}
