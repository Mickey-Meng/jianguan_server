package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@ExcelIgnoreUnannotated
public class MaterialBrandReportDetailVo extends NewBaseEntity {


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    private String materialBrand;
    private String materialCategory;
    private String materialCategoryCode;
    private List<FileModel> samplePhoto;
    private String sampleContent;
    private List<FileModel> materialApproachPhoto;
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

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;
}