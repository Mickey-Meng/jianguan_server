package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息业务对象 ql_project_info
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlProjectInfoBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户id
     */
    @NotBlank(message = "客户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerId;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectName;

    /**
     * 项目金额
     */
    private BigDecimal projectAmount;

    /**
     * 项目简述
     */
    private String projectResume;

    /**
     * 项目描述
     */
    private String projectDistribute;

    /**
     * 宣传图
     */
    private String photo;

    /**
     * 附件
     */
    private String fj;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 项目所属地区
     */
    @NotBlank(message = "项目所属地区不能为空", groups = { AddGroup.class, EditGroup.class })
    private String area;


}
