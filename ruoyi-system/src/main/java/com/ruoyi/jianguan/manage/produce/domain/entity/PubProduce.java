package com.ruoyi.jianguan.manage.produce.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序信息对象 pub_produce
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_produce")
public class PubProduce extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 构建类型ID
     */
    private Long componentTypeId;
    /**
     * 构建类型编码
     */
    private String componentTypeCode;
    /**
     * 工序名称
     */
    private String name;

    /**
     * 工序顺序
     */
    @TableField("rangee")
    private Integer orderNum;

    /**
     * 是否有效
     */
    @TableField("isvaild")
    private Integer isEffect;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否关联构建类型，默认否-null
     */
    @TableField(exist = false)
    private String relatedComponentType;

}