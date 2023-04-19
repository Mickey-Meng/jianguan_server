package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 jg_project_info
 *
 * @author ruoyi
 * @date 2023-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ss_f_projects")
public class JgProjectInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 自增主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    @TableField("`NAME`")
    private String projectName;
    /**
     * 编码
     */
    @TableField("`CODE`")
    private String projectCode;
    /**
     * 父级ID
     */
    @TableField("PARENTID")
    private Long parentId;
    /**
     * 部门类型
     */
    @TableField("TYPE")
    private String projectArea;
    /**
     * 级别
     */
    @TableField("GROUPLEVEL")
    private Long groupLevel;
    /**
     * 状态（0正常 1停用）|-1: 删除, 0: 冻结, 1: 正常
     */
    @TableField("STSTATE")
    private Integer status;
    /**
     * 是否显示
     */
    @TableField("VISIBLE")
    private String visible;
    /**
     * 顺序
     */
    @TableField("STORDER")
    private Long orderNum;
    /**
     * 组织ID
     */
    @TableField("GROUPID")
    private String groupId;
    /**
     * 是否自管：0-否，1-是
     */
    @TableField("ISAUTO")
    private Long isAuto;
    /**
     * 项目照片
     */
    @TableField("PROJECTPIC")
    private String projectPic;
    /**
     * 合同号
     */
    @TableField("CONTRACTNUM")
    private String contractNum;
    /**
     * 坐标
     */
    @TableField("COORDINATE")
    private String coordinate;
    /**
     * 投资金额（万元）
     */
    @TableField("INVESTMENT")
    private Long investment;
    /**
     * 项目类型（交通类、市政类、房建类、其他类）
     */
    @TableField("PROJECTTYPE")
    private String projectType;
    /**
     * 项目点
     */
    @TableField("PROJECTPOINT")
    private String projectPoint;
    /**
     * 项目线
     */
    @TableField("PROJECTLINE")
    private String projectLine;
    /**
     * 项目面
     */
    @TableField("PROJECTSURFACE")
    private String projectSurface;
    /**
     * 项目简介
     */
    @TableField("INTRODUCTION")
    private String introduction;
    /**
     * 备注
     */
    private String remark;

}
