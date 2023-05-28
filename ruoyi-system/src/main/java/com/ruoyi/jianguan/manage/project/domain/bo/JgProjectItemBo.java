package com.ruoyi.jianguan.manage.project.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目详情业务对象 jg_project_item
 *
 * @author ruoyi
 * @date 2023-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class JgProjectItemBo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 项目主键
     */
    private Long projectId;

    /**
     * 管理单位
     */
    private String manageDept;

    /**
     * 建造单位
     */
    private String buildDept;

    /**
     * 设计单位
     */
    private String desginDept;

    /**
     * 施工单位
     */
    private String constructDept;

    /**
     * 监理单位
     */
    private String supervisorDept;

    /**
     * 工程规模
     */
    private String projectScale;

    /**
     * 合同工期
     */
    private String projectDuration;

    /**
     * 投资规模
     */
    private String inputsCale;

    /**
     * 开工日期
     */
    private Date startTime;

    /**
     * 项目编码
     */
    @NotBlank(message = "项目编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectCode;

    /**
     * 审计单位
     */
    private String auditUnit;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 投资项目概述
     */
    private String investmentProjectOverview;

    /**
     * 压实责任
     */
    private String compactionResponsibility;

    /**
     * 落实保障
     */
    private String implementGuarantee;

    /**
     * 抓实进度
     */
    private String graspProgress;

    /**
     * 第一季度
     */
    private String firstQuarter;

    /**
     * 第二季度
     */
    private String secondQuarter;

    /**
     * 第三季度
     */
    private String thirdQuarter;

    /**
     * 第四季度
     */
    private String fourthQuarter;

    /**
     * 第一季度实际进度
     */
    private String firstQuarterActuality;

    /**
     * 第二季度实际进度
     */
    private String secondQuarterActuality;

    /**
     * 第三季度实际进度
     */
    private String thirdQuarterActuality;

    /**
     * 第四季度实际进度
     */
    private String fourthQuarterActuality;

    /**
     * 工程布局图
     */
    private String engineeringLayoutImage;

    /**
     * 付款进度
     */
    private Long attachmentProgress;


    /**
     * 产值进度
     */
    private Long productionProgress;
}
