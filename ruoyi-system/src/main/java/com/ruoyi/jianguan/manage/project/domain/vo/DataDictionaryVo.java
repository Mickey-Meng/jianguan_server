package com.ruoyi.jianguan.manage.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * 商品类别视图对象 ql_shop_goods_type
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class DataDictionaryVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 父商品类别id
     */
    @ExcelProperty(value = "父id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String ancestors;

    /**
     * 商品类别编码
     */
    @ExcelProperty(value = "编码")
    private String code;

    /**
     * 商品类别名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long orderNum;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    private Long deptId;


}
