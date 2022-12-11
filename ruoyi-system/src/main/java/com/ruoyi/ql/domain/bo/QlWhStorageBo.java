package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库位(储位)设置业务对象 ql_wh_storage
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWhStorageBo extends BaseEntity {

    /**
     * 库位(储位)设置id
     */
    @NotNull(message = "库位(储位)设置id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 库位编码
     */
    @NotBlank(message = "库位编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageCode;

    /**
     * 库位名称
     */
    @NotBlank(message = "库位名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageName;

    /**
     * 库位条码
     */
    private String storageBarcode;

    /**
     * 所属库区
     */
    @NotNull(message = "所属库区不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long reservoirId;

    /**
     * 空库位标识(Y是 N否)
     */
    @NotBlank(message = "空库位标识(Y是 N否)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isEmpty;

    /**
     * 是否停用(0:正常 1:停用)
     */
    @NotBlank(message = "是否停用(0:正常 1:停用)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isDisable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
