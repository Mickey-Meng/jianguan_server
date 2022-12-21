package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库管理业务对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWarehousingBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 入库单号
     */
    @NotBlank(message = "入库单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingCode;

    /**
     * 入库对接人
     */
    @NotBlank(message = "入库对接人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingUsername;

    /**
     * 采购订单id
     */
    private String purchaseOrderId;

    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String proudctId;

    /**
     * 入库数量
     */
    private Long warehousingNumber;

    /**
     * 入库时间
     */
    private Date warehousingDate;
    /**
     * 入库状态（1：已入库 0 未入库）
     */
    @NotBlank(message = "入库状态（1：已入库 0 未入库）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingStatus;
    /**
     * 采购数量
     */
    @NotNull(message = "采购数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderNumber;
    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String proudctName;


}
