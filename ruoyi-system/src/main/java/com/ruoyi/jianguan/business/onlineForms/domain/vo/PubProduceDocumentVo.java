package com.ruoyi.jianguan.business.onlineForms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 工序附件信息视图对象 pub_produce_document
 *
 * @author mickey
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "工序附件信息", description = "工序附件信息VO")
public class PubProduceDocumentVo {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键ID
     */
    @ExcelProperty(value = "业务主键ID")
    @ApiModelProperty(value = "业务主键ID", required = true)
    private Long id;

    /**
     * 构建ID
     */
    @ExcelProperty(value = "构建ID")
    @ApiModelProperty(value = "构建ID", required = true)
    private Long componentId;

    /**
     * 工序ID
     */
    @ExcelProperty(value = "工序ID")
    @ApiModelProperty(value = "工序ID", required = true)
    private Long produceId;

    /**
     * 文档编码
     */
    @ExcelProperty(value = "文档编码")
    @ApiModelProperty(value = "文档编码", required = true)
    private String documentCode;

    /**
     * 文档名称
     */
    @ExcelProperty(value = "文档名称")
    @ApiModelProperty(value = "文档名称", required = true)
    private String documentName;

    /**
     * 文档路径
     */
    @ExcelProperty(value = "文档路径")
    @ApiModelProperty(value = "文档路径", required = true)
    private String documentUrl;

    /**
     * 文档状态
     */
    @ExcelProperty(value = "文档状态")
    @ApiModelProperty(value = "文档状态", required = true)
    private Long documentStatus;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
