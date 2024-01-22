package com.ruoyi.jianguan.business.onlineForms.domain;

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
 * 评定填报关联信息对象 pub_check_report
 *
 * @author mickey
 * @date 2024-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_check_report")
public class PubCheckReport extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 评定构建ID
     */
    private Long checkComponentId;
    /**
     * 评定工序ID
     */
    private Long checkProduceId;
    /**
     * 填报构建ID
     */
    private Long reportComponentId;
    /**
     * 填报工序ID
     */
    private Long reportProduceId;
    /**
     * 实测项目是否合格
     */
    private Long scxmStatus;
    /**
     * 外观质量
     */
    private Long wgzlStatus;
    /**
     * 资料完整性
     */
    private Long zlwzxStatus;
    /**
     * 评定结果
     */
    private Long checkResult;
    /**
     * 审批状态
     */
    private Long status;
    /**
     * 填报时间
     */
    private Date reportTime;

    /**
     * 申请人
     */
    private String reportUser;

    /**
     * 审核人
     */
    private String checkUser;
    /**
     * 备注
     */
    private String remark;

}
