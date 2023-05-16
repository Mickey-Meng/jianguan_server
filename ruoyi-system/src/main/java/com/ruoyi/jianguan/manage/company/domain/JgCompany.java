package com.ruoyi.jianguan.manage.company.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司信息对象 jg_company
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ss_f_company")
public class JgCompany extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 公司名称
     */
    @TableField("name")
    private String companyName;
    /**
     * 公司编码
     */
    @TableField("code")
    private String companyCode;
    /**
     * 单位类型
     */
    @TableField("typename")
    private String typeName;
    /**
     * 单位类型编码
     */
    @TableField("typecode")
    private String typeCode;
    /**
     * 法人
     */
    @TableField("legalperson")
    private String legalPerson;
    /**
     * 法人联系方式
     */
    @TableField("legalphone")
    private String legalPhone;
    /**
     * 公司基础信息
     */
    @TableField("`data`")
    private String baseData;
    /**
     * 税号
     */
    @TableField("dutynum")
    private String dutyNum;
    /**
     * 附件
     */
    @TableField("")
    private String enclosure;
    /**
     * 备注
     */
    @TableField("enclosure")
    private String remark;

}
