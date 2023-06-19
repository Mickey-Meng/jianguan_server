package com.ruoyi.jianguan.business.certificate.domain.entity;

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
 * 进度管理-证照管理对象 zz_plan_certificate_photos
 *
 * @author mickey
 * @date 2023-06-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_progress_certificate_photos")
@ApiModel(value = "进度管理-证照管理", description = "进度管理-证照管理")
public class ProgressCertificatePhotos extends NewBaseEntity {

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
