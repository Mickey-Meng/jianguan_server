package com.ruoyi.project.ledger.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 台账分解业务对象 mea_ledger_breakdown
 *
 * @author ruoyi
 * @date 2022-12-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerBreakdownBo extends TreeEntity<MeaLedgerBreakdownBo> {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

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
     * 台账分解编号父节点
     */
    @NotBlank(message = "台账分解编号父节点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tzfjbhParent;

    /**
     * 台账分解编号祖级列表
     */
    private String tzfjbhAncestors;

    /**
     * 台账分解名称
     */
    @NotBlank(message = "台账分解名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tzfjmc;

    /**
     * 分解类型（0正常 1变更）
     */
//    @NotBlank(message = "分解类型（0正常 1变更）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fjlx;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


    /**
     *  '数据是否是变更获取（1 变更数据 0正常合同）',
     */
    private String isChange;
}
