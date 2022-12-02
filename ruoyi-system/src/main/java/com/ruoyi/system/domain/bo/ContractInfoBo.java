package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同条款业务对象 contract_info
 *
 * @author ruoyi
 * @date 2022-12-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ContractInfoBo extends BaseEntity {

    /**
     * 标段编号
     */
    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String BDBH;

    /**
     * 开工日期,YYYY-MM-DD
     */
    @NotNull(message = "开工日期,YYYY-MM-DD不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date KGRQ;

    /**
     * 竣工日期,YYYY-MM-DD
     */
    @NotNull(message = "竣工日期,YYYY-MM-DD不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date JGRQ;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String XMMC;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String HTBH;

    /**
     * 合同总金额
     */
    @NotNull(message = "合同总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal HTZJE;

    /**
     * 合同段
     */
    @NotBlank(message = "合同段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String HTD;

    /**
     * 工程量清单金额
     */
    @NotNull(message = "工程量清单金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal GCLQDJE;

    /**
     * 开工预付款金额
     */
    @NotNull(message = "开工预付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal KGYFKJE;

    /**
     * 暂列金金额（元）
     */
    @NotNull(message = "暂列金金额（元）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal ZLJJE;

    /**
     * 合同工期 (月/天  24/730)
     */
    @NotBlank(message = "合同工期 (月/天  24/730)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String HTGQ;

    /**
     * 开工预付款起扣比例（%）
     */
    @NotNull(message = "开工预付款起扣比例（%）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal KGYFKQKBL;

    /**
     * 开工预付款截止比例（%）
     */
    @NotNull(message = "开工预付款截止比例（%）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal KGYFKJZBL;

    /**
     * 质保金扣款比例（%）
     */
    @NotNull(message = "质保金扣款比例（%）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal ZBJKKBL;

    /**
     * 农民工工资保证金扣款比例（（%）
     */
    @NotNull(message = "农民工工资保证金扣款比例（（%）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal NMGGZBZJKKBL;

    /**
     * 质保金总额（元）
     */
    @NotNull(message = "质保金总额（元）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal ZBJZE;

    /**
     * 计日工金额（元）
     */
    @NotNull(message = "计日工金额（元）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal JRGJE;

    /**
     * 标段长度（KM）
     */
    @NotNull(message = "标段长度（KM）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal BDCD;

    /**
     * 起讫桩号
     */
    @NotBlank(message = "起讫桩号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String QQZH;

    /**
     * 开工预付款支付情况
     */
    @NotBlank(message = "开工预付款支付情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String KGYJKZFQK;

    /**
     * 开工预付款扣回规定
     */
    @NotBlank(message = "开工预付款扣回规定不能为空", groups = { AddGroup.class, EditGroup.class })
    private String KGYFKKHGD;

    /**
     * 材料预付款支付情况
     */
    @NotBlank(message = "材料预付款支付情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String CLYKFZFQK;

    /**
     * 材料预付款扣回规定
     */
    @NotBlank(message = "材料预付款扣回规定不能为空", groups = { AddGroup.class, EditGroup.class })
    private String CLYFKKHGD;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
