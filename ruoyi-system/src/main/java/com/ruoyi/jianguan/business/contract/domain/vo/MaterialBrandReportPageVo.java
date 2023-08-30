package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@ExcelIgnoreUnannotated
public class MaterialBrandReportPageVo extends NewBaseVo {


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;
    private String materialBrand;
    private String materialCategory;
    private String materialCategoryCode;
    private String samplePhoto;
    private String sampleContent;
    private String materialApproachPhoto;
    private String materialApproachContent;
    private String materialApproachQuantity;
    /**
     * 附件
     */
    private List<FileModel> attachment;
    private List<FileModel>  attachment1;
    private List<FileModel>  attachment2;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    private Integer status1;
    private Integer status2;

}