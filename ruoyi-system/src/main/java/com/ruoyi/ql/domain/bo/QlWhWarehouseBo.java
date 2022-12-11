package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库设置业务对象 ql_wh_warehouse
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWhWarehouseBo extends BaseEntity {

    /**
     * 仓库设置id
     */
    @NotNull(message = "仓库设置id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 仓库编码
     */
    @NotBlank(message = "仓库编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehouseCode;

    /**
     * 仓库名称
     */
    @NotBlank(message = "仓库名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehouseName;

    /**
     * 所在城市
     */
    @NotBlank(message = "所在城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String city;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String principal;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String telephone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
