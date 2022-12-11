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
 * 出库管理业务对象 ql_outbound
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlOutboundBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 出库单号
     */
    private String outboundCode;

    /**
     * 出库对接人
     */
    @NotBlank(message = "出库对接人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundUsername;

    /**
     * 产品id
     */
    private String proudctId;

    /**
     * 出库数量
     */
    @NotNull(message = "出库数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long outboundNumber;

    /**
     * 出库时间
     */
    @NotNull(message = "出库时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date outboundDate;

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
