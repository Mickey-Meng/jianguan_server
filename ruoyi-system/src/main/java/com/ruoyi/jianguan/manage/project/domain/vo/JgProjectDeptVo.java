package com.ruoyi.jianguan.manage.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 项目和部门关联视图对象 sys_project_dept
 *
 * @author ruoyi
 * @date 2023-04-30
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "项目和部门关联", description = "项目和部门关联VO")
public class JgProjectDeptVo {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    @ExcelProperty(value = "项目ID")
    @ApiModelProperty(value = "项目ID", required = true)
    private Long projectId;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;


}
