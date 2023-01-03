package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 库位(储位)设置视图对象 ql_wh_storage
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlWhStorageVo {

    private static final long serialVersionUID = 1L;

    /**
     * 库位(储位)设置id
     */
    @ExcelProperty(value = "库位(储位)设置id")
    private Long id;

    /**
     * 库位编码
     */
    @ExcelProperty(value = "库位编码")
    private String storageCode;

    /**
     * 库位名称
     */
    @ExcelProperty(value = "库位名称")
    private String storageName;

    /**
     * 库位条码
     */
    @ExcelProperty(value = "库位条码")
    private String storageBarcode;

    /**
     * 所属库区
     */
    @ExcelProperty(value = "所属库区")
    private Long reservoirId;
    /**
     * 所属库区
     */
    @ExcelProperty(value = "所属库区名称")
    private String reservoirName;
    /**
     * 空库位标识(Y是 N否)
     */
    @ExcelProperty(value = "空库位标识(Y是 N否)")
    private String isEmpty;

    /**
     * 是否停用(0:正常 1:停用)
     */
    @ExcelProperty(value = "是否停用(0:正常 1:停用)")
    private String isDisable;


}
