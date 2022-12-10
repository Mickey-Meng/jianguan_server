package com.ruoyi.project.flowidatenfo.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 工作流单数据视图对象 mea_flow_data_info
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@Data
@ExcelIgnoreUnannotated
public class MeaFlowDataInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 任务id
     */
    @ExcelProperty(value = "任务id")
    private String taskId;

    /**
     * 业务类型
     */
    @ExcelProperty(value = "业务类型")
    private String bussinessKey;

    /**
     * 业务数据  
     */
    @ExcelProperty(value = "业务数据  ")
    private String bussinessData;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;


}
