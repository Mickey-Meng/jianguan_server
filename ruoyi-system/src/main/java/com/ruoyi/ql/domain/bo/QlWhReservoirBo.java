package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库区设置业务对象 ql_wh_reservoir
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWhReservoirBo extends BaseEntity {

    /**
     * 库区设置id
     */
    @NotNull(message = "库区设置id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 库区编码
     */
    @NotBlank(message = "库区编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reservoirCode;

    /**
     * 库区名称
     */
    @NotBlank(message = "库区名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reservoirName;

    /**
     * 所属仓库
     */
    @NotNull(message = "所属仓库不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
