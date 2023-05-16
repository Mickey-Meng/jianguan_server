package com.ruoyi.jianguan.manage.company.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 公司信息视图对象 jg_company
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "公司信息", description = "公司信息VO")
public class JgCompanyVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    @ApiModelProperty(value = "", required = true)
    private Long id;

    /**
     * 公司名称
     */
    @ExcelProperty(value = "公司名称")
    @ApiModelProperty(value = "公司名称", required = true)
    private String companyName;

    /**
     * 公司编码
     */
    @ExcelProperty(value = "公司编码")
    @ApiModelProperty(value = "公司编码", required = true)
    private String companyCode;

    /**
     * 单位类型
     */
    @ExcelProperty(value = "单位类型")
    @ApiModelProperty(value = "单位类型", required = true)
    private String typeName;

    /**
     * 单位类型编码
     */
    @ExcelProperty(value = "单位类型编码")
    @ApiModelProperty(value = "单位类型编码", required = true)
    private String typeCode;

    /**
     * 法人
     */
    @ExcelProperty(value = "法人")
    @ApiModelProperty(value = "法人", required = true)
    private String legalPerson;

    /**
     * 法人联系方式
     */
    @ExcelProperty(value = "法人联系方式")
    @ApiModelProperty(value = "法人联系方式", required = true)
    private String legalPhone;

    /**
     * 公司基础信息
     */
    @ExcelProperty(value = "公司基础信息")
    @ApiModelProperty(value = "公司基础信息", required = true)
    private String baseData;

    /**
     * 税号
     */
    @ExcelProperty(value = "税号")
    @ApiModelProperty(value = "税号", required = true)
    private String dutyNum;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private String enclosure;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
