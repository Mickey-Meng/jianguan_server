package com.ruoyi.jianguan.manage.produce.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import liquibase.pro.packaged.L;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序库对象 pub_produce_library
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_produce_library")
public class PubProduceLibrary extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 工序库名称
     */
    private String name;
    /**
     * 工序库编号
     */
    private String code;

    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;

}
