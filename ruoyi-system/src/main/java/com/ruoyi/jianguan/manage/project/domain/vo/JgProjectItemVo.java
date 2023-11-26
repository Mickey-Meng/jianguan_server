package com.ruoyi.jianguan.manage.project.domain.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 项目详情视图对象 jg_project_item
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "项目详情", description = "项目详情VO")
public class JgProjectItemVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 项目主键
     */
    @ExcelProperty(value = "项目主键")
    @ApiModelProperty(value = "项目主键", required = true)
    private Long projectId;

    /**
     * 管理单位
     */
    @ExcelProperty(value = "管理单位")
    @ApiModelProperty(value = "管理单位", required = true)
    private String manageDept;

    /**
     * 建造单位
     */
    @ExcelProperty(value = "建造单位")
    @ApiModelProperty(value = "建造单位", required = true)
    private String buildDept;

    /**
     * 设计单位
     */
    @ExcelProperty(value = "设计单位")
    @ApiModelProperty(value = "设计单位", required = true)
    private String desginDept;

    /**
     * 施工单位
     */
    @ExcelProperty(value = "施工单位")
    @ApiModelProperty(value = "施工单位", required = true)
    private String constructDept;

    /**
     * 监理单位
     */
    @ExcelProperty(value = "监理单位")
    @ApiModelProperty(value = "监理单位", required = true)
    private String supervisorDept;

    /**
     * 工程规模
     */
    @ExcelProperty(value = "工程规模")
    @ApiModelProperty(value = "工程规模", required = true)
    private String projectScale;

    /**
     * 合同工期
     */
    @ExcelProperty(value = "合同工期")
    @ApiModelProperty(value = "合同工期", required = true)
    private String projectDuration;

    /**
     * 投资规模
     */
    @ExcelProperty(value = "投资规模")
    @ApiModelProperty(value = "投资规模", required = true)
    private String inputsCale;

    /**
     * 开工日期
     */
    @ExcelProperty(value = "开工日期")
    @ApiModelProperty(value = "开工日期", required = true)
    private Date startTime;

    /**
     * 项目编码
     */
    @ExcelProperty(value = "项目编码")
    @ApiModelProperty(value = "项目编码", required = true)
    private String projectCode;

    /**
     * 审计单位
     */
    @ExcelProperty(value = "审计单位")
    @ApiModelProperty(value = "审计单位", required = true)
    private String auditUnit;

    /**
     * 项目名称
     */
    @ExcelProperty(value = "项目名称")
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

    /**
     * 投资项目概述
     */
    @ExcelProperty(value = "投资项目概述")
    @ApiModelProperty(value = "投资项目概述", required = true)
    private String investmentProjectOverview;

    /**
     * 压实责任
     */
    @ExcelProperty(value = "压实责任")
    @ApiModelProperty(value = "压实责任", required = true)
    private String compactionResponsibility;

    /**
     * 落实保障
     */
    @ExcelProperty(value = "落实保障")
    @ApiModelProperty(value = "落实保障", required = true)
    private String implementGuarantee;

    /**
     * 抓实进度
     */
    @ExcelProperty(value = "抓实进度")
    @ApiModelProperty(value = "抓实进度", required = true)
    private String graspProgress;

    /**
     * 第一季度
     */
    @ExcelProperty(value = "第一季度")
    @ApiModelProperty(value = "第一季度", required = true)
    private String firstQuarter;

    /**
     * 第二季度
     */
    @ExcelProperty(value = "第二季度")
    @ApiModelProperty(value = "第二季度", required = true)
    private String secondQuarter;

    /**
     * 第三季度
     */
    @ExcelProperty(value = "第三季度")
    @ApiModelProperty(value = "第三季度", required = true)
    private String thirdQuarter;

    /**
     * 第四季度
     */
    @ExcelProperty(value = "第四季度")
    @ApiModelProperty(value = "第四季度", required = true)
    private String fourthQuarter;

    /**
     * 第一季度实际进度
     */
    @ExcelProperty(value = "第一季度实际进度")
    @ApiModelProperty(value = "第一季度实际进度", required = true)
    private String firstQuarterActuality;

    /**
     * 第二季度实际进度
     */
    @ExcelProperty(value = "第二季度实际进度")
    @ApiModelProperty(value = "第二季度实际进度", required = true)
    private String secondQuarterActuality;

    /**
     * 第三季度实际进度
     */
    @ExcelProperty(value = "第三季度实际进度")
    @ApiModelProperty(value = "第三季度实际进度", required = true)
    private String thirdQuarterActuality;

    /**
     * 第四季度实际进度
     */
    @ExcelProperty(value = "第四季度实际进度")
    @ApiModelProperty(value = "第四季度实际进度", required = true)
    private String fourthQuarterActuality;

    /**
     * 工程布局图
     */
    @ExcelProperty(value = "工程布局图")
    @ApiModelProperty(value = "工程布局图", required = true)
    private String engineeringLayoutImage;

    /**
     * 付款进度
     */
    @ExcelProperty(value = "付款进度")
    @ApiModelProperty(value = "付款进度")
    private float attachmentProgress;


    /**
     * 产值进度
     */
    @ExcelProperty(value = "产值进度")
    @ApiModelProperty(value = "产值进度")
    private float productionProgress;
    /**
     * 产值进度
     */
    @ExcelProperty(value = "宣传视频")
    @ApiModelProperty(value = "宣传视频")
    private String videoUrl;

}
