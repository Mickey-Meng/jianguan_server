package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import lombok.Data;

import java.util.Date;

@Data
public class MaterialBrandReportPageDTO extends PageDTO {

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
    private String attachment;
    private String attachment1;
    private String attachment2;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    private Integer status1;
    private Integer status2;
}
