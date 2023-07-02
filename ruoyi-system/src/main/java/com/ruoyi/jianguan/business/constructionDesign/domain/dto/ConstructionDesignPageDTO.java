package com.ruoyi.jianguan.business.constructionDesign.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * 计划管理-施工图管理分页dto
 *
 * @author M.Z.B
 * @date : 2023/06/25
 */
@Data
@ApiModel(value = "ProgressConstructionDesignPageDTO", description = "进度管理-施工图管理查询dto")
public class ConstructionDesignPageDTO extends PageDTO {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 施工图名称
     */
    private String name;
    /**
     * 施工图内容
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
    private String ownerId;

    /**
     * 责任人名称
     */
    private String ownerName;
    /**
     * 附件名称
     */
    private String attachmentNames;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
