package com.ruoyi.project.contract.domain.bo;

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
 * 合同条款业务对象 mea_contract_info
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaContractInfoBo extends BaseEntity {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 标段编号
     */
    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bdbh;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String htbh;

    /**
     * 开工日期
     */
    @NotNull(message = "开工日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date kgrq;

    /**
     * 竣工日期
     */
    @NotNull(message = "竣工日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date jgrq;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String xmmc;

    /**
     * 合同总金额
     */
    @NotNull(message = "合同总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal htzje;

    /**
     * 合同段
     */
    @NotBlank(message = "合同段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String htd;

    /**
     * 工程量清单金额
     */
    @NotNull(message = "工程量清单金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal gclqdje;

    /**
     * 开工预付款金额
     */
    @NotNull(message = "开工预付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal kgyfkje;

    /**
     * 暂列金金额
     */
    @NotNull(message = "暂列金金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal zljje;

    /**
     * 合同工期)
     */
    @NotBlank(message = "合同工期)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String htgq;

    /**
     * 开工预付款起扣比例
     */
    @NotNull(message = "开工预付款起扣比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal kgyfkqkbl;

    /**
     * 开工预付款截止比例
     */
    @NotNull(message = "开工预付款截止比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal kgyfkjzbl;

    /**
     * 质保金扣款比例
     */
    @NotNull(message = "质保金扣款比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal zbjkkbl;

    /**
     * 农民工工资保证金扣款比例
     */
    @NotNull(message = "农民工工资保证金扣款比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal nmggzbzjkkbl;

    /**
     * 质保金总额
     */
    @NotNull(message = "质保金总额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal zbjze;

    /**
     * 计日工金额
     */
    @NotNull(message = "计日工金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal jrgje;

    /**
     * 标段长度(km)
     */
    @NotNull(message = "标段长度(km)不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal bdcd;

    /**
     * 起讫桩号
     */
    @NotBlank(message = "起讫桩号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String qqzh;

    /**
     * 开工预付款支付情况
     */
    @NotBlank(message = "开工预付款支付情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String kgyjkzfqk;

    /**
     * 开工预付款扣回规定
     */
    @NotBlank(message = "开工预付款扣回规定不能为空", groups = { AddGroup.class, EditGroup.class })
    private String kgyfkkhgd;

    /**
     * 材料预付款支付情况
     */
    @NotBlank(message = "材料预付款支付情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String clykfzfqk;

    /**
     * 材料预付款扣回规定
     */
    @NotBlank(message = "材料预付款扣回规定不能为空", groups = { AddGroup.class, EditGroup.class })
    private String clyfkkhgd;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
