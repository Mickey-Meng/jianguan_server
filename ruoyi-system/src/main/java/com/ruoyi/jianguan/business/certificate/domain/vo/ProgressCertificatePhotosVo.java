package com.ruoyi.jianguan.business.certificate.domain.vo;

import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import lombok.Data;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 进度管理-证照管理视图对象
 *
 * @author mickey
 * @date 2023-06-19
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "证照管理", description = "证照管理VO")
public class ProgressCertificatePhotosVo extends PlanCertificatePhotosVo {

    private static final long serialVersionUID = 1L;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private List<FileModel> attachment;

}

