package com.ruoyi.project.file.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 附件记录视图对象 mea_file
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaFileVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String fileId;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String fileName;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String flowId;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String url;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String path;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
