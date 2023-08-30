package com.ruoyi.flowable.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 历史的流程运行中的变量信息 ACT_HI_VARINST
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACT_HI_VARINST")
public class ActHiVarinst extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    private String processInstanceId;

    private String scopeId;

    private String scopeType;
}