package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 省市区对象 ql_area
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_area")
public class QlArea extends TreeEntity<QlArea> {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "Id")
    private Long Id;
    /**
     *
     */
    private String Name;
    /**
     *
     */
    private Long Pid;

}
