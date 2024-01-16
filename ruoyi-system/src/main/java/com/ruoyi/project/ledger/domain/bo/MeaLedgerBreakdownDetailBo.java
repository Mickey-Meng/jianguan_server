package com.ruoyi.project.ledger.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账分解明细业务对象 mea_ledger_breakdown_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerBreakdownDetailBo extends BaseEntity {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 标段编号
     */
//    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bdbh;

    /**
     * 台账分解编号
     */
//    @NotBlank(message = "台账分解编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tzfjbh;

    /**
     * 父级目录
     */
    private String fjmulu;

    /**
     * 子目号
     */
//    @NotBlank(message = "子目号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmh;

    /**
     * 子目号
     */
//    @NotBlank(message = "子目号不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<String> zmhList;

    /**
     * 子目名称
     */
//    @NotBlank(message = "子目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmmc;

    /**
     * 单位
     */
//    @NotBlank(message = "单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dw;

    /**
     * 合同单价
     */
//    @NotNull(message = "合同单价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal htdj;

    /**
     * 设计数量
     */
//    @NotNull(message = "设计数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sjsl;

    /**
     * 分解数量
     */
//    @NotNull(message = "分解数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal fjsl;

    /**
     * 变更数量
     */
    private BigDecimal bgsl;
    /**
     * 变更分解数量
     */
    private BigDecimal bgfjsl;
    /**
     * 复核数量
     */
//    @NotNull(message = "复核数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal fhsl;

    /**
     * 已计量数量
     */
//    @NotNull(message = "已计量数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal yjlsl;

    //合同数量
    private BigDecimal htsl;

    /**
     * 复核金额
     */
//    @NotNull(message = "复核金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal fhje;

    /**
     * 状态（0正常 1变更）
     */
//    @NotBlank(message = "状态（0正常 1变更）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fjlx;

    /**
     * 状态（0正常 1停用）
     */
//    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


    private String reviewCode;
    /**
     *  '数据是否是变更获取（1 变更数据 0正常合同）',
     */
    private String isChange;

}
