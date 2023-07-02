package com.ruoyi.jianguan.business.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("construction_plan")
public class ConstructionPlan extends NewBaseEntity {


    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 内容
     */
    private String content;

    /**
     * 上报人
     */
    private String reportPeople;
    private String responsiblePerson;
    private Date plainStartTime;
    private Date plainEndTime;
    private String name;
    private Date reportTime;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
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