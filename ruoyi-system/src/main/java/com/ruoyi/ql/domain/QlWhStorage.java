package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库位(储位)设置对象 ql_wh_storage
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_wh_storage")
public class QlWhStorage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 库位(储位)设置id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 库位编码
     */
    private String storageCode;
    /**
     * 库位名称
     */
    private String storageName;
    /**
     * 库位条码
     */
    private String storageBarcode;
    /**
     * 所属库区
     */
    private Long reservoirId;
    /**
     * 空库位标识(Y是 N否)
     */
    private String isEmpty;
    /**
     * 是否停用(0:正常 1:停用)
     */
    private String isDisable;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;

}
