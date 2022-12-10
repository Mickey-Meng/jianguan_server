package com.ruoyi.project.flowidatenfo.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作流单数据对象 mea_flow_data_info
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_flow_data_info")
public class MeaFlowDataInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 业务类型
     */
    private String bussinessKey;
    /**
     * 业务数据  
     */
    private String bussinessData;
    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
