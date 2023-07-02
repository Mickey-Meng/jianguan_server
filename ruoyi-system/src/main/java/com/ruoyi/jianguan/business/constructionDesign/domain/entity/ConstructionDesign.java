package com.ruoyi.jianguan.business.constructionDesign.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 进度管理-施工图设计对象 zz_construction_design
 *
 * @author mickey
 * @date 2023-06-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_construction_design")
@ApiModel(value = "进度管理-施工图设计", description = "进度管理-施工图设计")
public class ConstructionDesign extends NewBaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 证照名称
     */
    private String name;
    /**
     * 证照内容
     */
    private String contents;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 上报时间
     */
    private Date reportTime;
    /**
     * 上报人
     */
    private String reportUser;
    /**
     * 责任人ID
     */
    private Long ownerId;

    /**
     * 责任人名称
     */
    private String ownerName;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 计划管理状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer planStatus;

    /**
     * 进度管理状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer progressStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 备注
     */
    private String remark;

}
