package com.ruoyi.jianguan.manage.company.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司信息业务对象 jg_company
 *
 * @author ruoyi
 * @date 2023-05-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class JgCompanyBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String companyName;

    /**
     * 公司编码
     */
    @NotBlank(message = "公司编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String companyCode;

    /**
     * 单位类型
     */
    @NotBlank(message = "单位类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeName;

    /**
     * 单位类型编码
     */
    @NotBlank(message = "单位类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeCode;

    /**
     * 法人
     */
    @NotBlank(message = "法人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String legalPerson;

    /**
     * 法人联系方式
     */
    @NotBlank(message = "法人联系方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String legalPhone;

    /**
     * 公司基础信息
     */
    @NotBlank(message = "公司基础信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String baseData;

    /**
     * 税号
     */
    @NotBlank(message = "税号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dutyNum;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private String enclosure;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
