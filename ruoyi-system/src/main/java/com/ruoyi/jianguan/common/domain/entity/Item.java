package com.ruoyi.jianguan.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Item implements Serializable {
    /**
     * 项目编号
     */
    private Integer id;

    /**
     * 管理单位
     */
    private String managedpt;

    /**
     * 建造单位
     */
    private String builddpt;

    /**
     * 设计单位
     */
    private String desgindpt;

    /**
     * 施工单位
     */
    private String constructdpt;

    /**
     * 监理单位
     */
    private String supervisordpt;

    /**
     * 工程规模
     */
    private String projectscale;

    /**
     * 合同工期
     */
    private String projectduration;

    /**
     * 投资规模
     */
    private String inputscale;

    /**
     * 开工日期
     */
    private Date starttime;
    //审计单位
    private String auditUnit;
    //项目名称
    private String projectName;
    //投资项目概述
    private String investmentProjectOverview;
    //压实责任
    private String compactionResponsibility;
    //落实保障
    private String implementGuarantee;
    //抓实进度
    private String graspTheProgress;

    private String fourthQuarter;
    private String thirdQuarter;
    private String secondQuarter;
    private String firstQuarter;
    //工程布置图
    private String engineeringLayoutImageUrl;


    /**
     * 付款进度
     */
    private float attachmentProgress;


    /**
     * 产值进度
     */
    private float productionProgress;
    /**
     * 项目code
     */
    private String projectcode;
    /**
     * 宣传视频
     */
    private String videoUrl;
    private String projectType;
    private String projectTypeAbbr;
    private String monitorAppkey;
    private String monitorSecret;


}
