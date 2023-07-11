package com.ruoyi.flowable.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 运行时任务表 ACT_RU_TASK
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACT_RU_TASK")
public class ActRuTask extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    private String processInstanceId;

    private String scopeId;

    private String scopeType;
}