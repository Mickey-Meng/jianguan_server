package com.ruoyi.jianguan.business.onlineForms.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 工序附件信息对象 pub_produce_document
 *
 * @author mickey
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_produce_document")
public class PubProduceDocument extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 构建ID
     */
    private Long componentId;
    /**
     * 工序ID
     */
    private Long produceId;
    /**
     * 文档名称
     */
    private String documentName;
    /**
     * 文档路径
     */
    private String documentUrl;
    /**
     * 文档状态
     */
    private Long documentStatus;

    /**
     * 文档类型(1-填报;2-评定)
     */
    private Long documentType;
    /**
     * 备注
     */
    private String remark;

}
