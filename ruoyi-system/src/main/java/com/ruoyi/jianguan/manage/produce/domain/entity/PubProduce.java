package com.ruoyi.jianguan.manage.produce.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
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
     * 构建类型编码
     */
    private String componentTypeCode;
    /**
     * 工序名称
     */
    private String name;
    /**
     * 
     */
    private Integer rangee;
    /**
     * 
     */
    private Integer isvaild;
    /**
     * 备注
     */
    private String remark;

}
