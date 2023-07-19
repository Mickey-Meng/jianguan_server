package com.ruoyi.jianguan.business.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("jg_material_brand")
public class MaterialBrandReport extends NewBaseEntity {


    /**
     * id
     */
    @TableId(value = "id")
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
    private String attachment;
    private String attachment1;
    private String attachment2;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    private Integer status1;
    private Integer status2;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;

}