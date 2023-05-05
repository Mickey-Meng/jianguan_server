package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品类别对象 ql_shop_goods_type
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("data_dictionary")
public class DataDictionary extends TreeEntity<DataDictionary> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
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
     * 名称
     */
    private String name;
    /**
     * 显示顺序
     */
    private Long orderNum;
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
