package com.ruoyi.jianguan.manage.produce.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 构建类型对象 pub_conponent_type
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_component_type")
public class PubComponentType extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 工序库id
     */
    private String libraryId;
    /**
     * 构建类型名称
     */
    private String name;
    /**
     * 构建类型编号
     */
    private String code;
    /**
     * 备注
     */
    private String remark;

}
