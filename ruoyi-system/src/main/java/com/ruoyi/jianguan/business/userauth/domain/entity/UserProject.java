package com.ruoyi.jianguan.business.userauth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName("ss_f_user_project")
public class UserProject extends BaseEntity {

    @TableField("USERID")
    private Integer userId;

    @TableField("GROUPID")
    private Integer workAreaId;

}
