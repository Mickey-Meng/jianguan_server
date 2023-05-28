package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目详情对象 jg_project_item
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@Data
@TableName("item")
public class JgProjectItem {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 项目主键
     */
    @TableField(exist = false)
    private Long projectId;
    /**
     * 管理单位
     */
    @TableField(value = "managedpt")
    private String manageDept;
    /**
     * 建造单位
     */
    @TableField(value = "builddpt")
    private String buildDept;
    /**
     * 设计单位
     */
    @TableField(value = "desgindpt")
    private String desginDept;
    /**
     * 施工单位
     */
    @TableField(value = "constructdpt")
    private String constructDept;
    /**
     * 监理单位
     */
    @TableField(value = "supervisordpt")
    private String supervisorDept;
    /**
     * 工程规模
     */
    @TableField(value = "projectscale")
    private String projectScale;
    /**
     * 合同工期
     */
    @TableField(value = "projectduration")
    private String projectDuration;
    /**
     * 投资规模
     */
    @TableField(value = "inputscale")
    private String inputsCale;
    /**
     * 开工日期
     */
    @TableField(value = "starttime")
    private Date startTime;
    /**
     * 项目编码
     */
    @TableField(value = "projectcode")
    private String projectCode;
    /**
     * 审计单位
     */
    @TableField(value = "auditUnit")
    private String auditUnit;
    /**
     * 项目名称
     */
    @TableField(value = "projectName")
    private String projectName;
    /**
     * 投资项目概述
     */
    @TableField(value = "investmentProjectOverview")
    private String investmentProjectOverview;
    /**
     * 压实责任
     */
    @TableField(value = "compactionResponsibility")
    private String compactionResponsibility;
    /**
     * 落实保障
     */
    @TableField(value = "implementGuarantee")
    private String implementGuarantee;
    /**
     * 抓实进度
     */
    @TableField(value = "graspTheProgress")
    private String graspProgress;
    /**
     * 第一季度
     */
    @TableField(value = "firstQuarter")
    private String firstQuarter;
    /**
     * 第二季度
     */
    @TableField(value = "secondQuarter")
    private String secondQuarter;
    /**
     * 第三季度
     */
    @TableField(value = "thirdQuarter")
    private String thirdQuarter;
    /**
     * 第四季度
     */
    @TableField(value = "fourthQuarter")
    private String fourthQuarter;

    /**
     * 第一季度实际进度
     */
    @TableField(value = "first_quarter_actuality")
    private String firstQuarterActuality;

    /**
     * 第二季度实际进度
     */
    @TableField(value = "second_quarter_actuality")
    private String secondQuarterActuality;

    /**
     * 第三季度实际进度
     */
    @TableField(value = "third_quarter_actuality")
    private String thirdQuarterActuality;

    /**
     * 第四季度实际进度
     */
    @TableField(value = "fourth_quarter_actuality")
    private String fourthQuarterActuality;

    /**
     * 工程布局图
     */
    @TableField(value = "engineeringLayoutImageUrl")
    private String engineeringLayoutImage;


    /**
     * 付款进度
     */
    @TableField("attachment_progress")
    private Long attachmentProgress;


    /**
     * 产值进度
     */
    @TableField("production_progress")
    private Long productionProgress;
}
