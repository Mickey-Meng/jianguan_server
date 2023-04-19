package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 ss_f_projects
 *
 * @author ruoyi
 * @date 2023-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("jg_project_info")
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
    private String projectName;
    /**
     * 编码
     */
    private String projectCode;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 部门类型
     */
    private String projectArea;
    /**
     * 级别
     */
    private Long groupLevel;
    /**
     * 状态（0正常 1停用）|-1: 删除, 0: 冻结, 1: 正常
     */
    private Integer status;
    /**
     * 是否显示
     */
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
    private String projectPic;
    /**
     * 合同号
     */
    private String contractNum;
    /**
     * 坐标
     */
    private String coordinate;
    /**
     * 投资金额（万元）
     */
    private Long investment;
    /**
     * 项目类型（交通类、市政类、房建类、其他类）
     */
    private String projectType;
    /**
     * 项目点
     */
    private String projectPoint;
    /**
     * 项目线
     */
    private String projectLine;
    /**
     * 项目面
     */
    private String projectSurface;
    /**
     * 项目简介
     */
    private String introduction;
    /**
     * 备注
     */
    private String remark;

}
