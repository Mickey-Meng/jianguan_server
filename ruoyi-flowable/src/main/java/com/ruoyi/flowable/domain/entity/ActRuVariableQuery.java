package com.ruoyi.flowable.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.variable.api.types.VariableType;

/**
 * 运行时变量表 ACT_RU_VARIABLE
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ActRuVariableQuery extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected VariableType type;
    protected String typeName;

    protected String executionId;
    protected String processInstanceId;
    protected String processDefinitionId;
    protected String taskId;
    protected String scopeId;
    protected String subScopeId;
    protected String scopeType;

    protected Long longValue;
    protected Double doubleValue;
    protected String textValue;
    protected String textValue2;
    protected ByteArrayRef byteArrayRef;

    protected Object cachedValue;
    protected boolean forcedUpdate;
    protected boolean deleted;

}