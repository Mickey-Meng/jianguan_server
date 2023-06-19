package com.ruoyi.jianguan.business.certificate.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * 计划管理-证照管理分页dto
 *
 * @author M.Z.B
 * @date : 2023/06/19
 */
@Data
@ApiModel(value = "ProgressCertificatePhotosPageDTO", description = "进度管理-证照管理查询dto")
public class ProgressCertificatePhotosPageDTO extends PageDTO {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
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
     * 责任人
     */
    private String owner;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
