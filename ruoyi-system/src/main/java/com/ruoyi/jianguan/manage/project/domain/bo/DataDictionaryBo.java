package com.ruoyi.jianguan.manage.project.domain.bo;

import com.ruoyi.common.core.domain.TreeEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品类别业务对象 ql_shop_goods_type
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DataDictionaryBo extends TreeEntity<DataDictionaryBo> {

    /**
     * id
     */
    private Long id;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 编码
     */
    private String code;

    /**
     * 商品类别名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
