package com.ruoyi.system.domain.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * 通知公告表 sys_notice
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysNoticeBo extends BaseEntity {

    /**
     * 公告ID
     */
    @TableId(value = "notice_id")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
    /**
     * 接收者id
     */
    private Long receiveId;
    /**
     * 接收者名称
     */
    private String receiveName;
    /**
     * 阅读状态  1:已读   0：未读
     */
    private String readStatus;
    /**
     * 阅读时间
     */
    private Date readTime;
    /**
     * 消息失效时间
     */
    private Date expirationDate;

    /**
     * 业务Id
     */
    private Long businessId;

    /**
     *
     */
    private List<Long> businessIds;

    /**
     * 业务类型
     */
    private String businessType;

}
