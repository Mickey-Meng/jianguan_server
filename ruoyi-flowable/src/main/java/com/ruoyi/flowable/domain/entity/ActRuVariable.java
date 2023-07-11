package com.ruoyi.flowable.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 运行时变量表 ACT_RU_VARIABLE
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACT_RU_VARIABLE")
public class ActRuVariable extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    private String processInstanceId;

    private String scopeId;

    private String scopeType;

}