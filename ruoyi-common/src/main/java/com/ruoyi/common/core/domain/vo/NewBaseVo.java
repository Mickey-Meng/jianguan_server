package com.ruoyi.common.core.domain.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiaoxulin
 * @date : 2022/8/1 11:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBaseVo extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Integer createUserId;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createUserName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value = "审批状态 0：审批中 1: 审批完成 2:驳回")
    private Integer status;
}
