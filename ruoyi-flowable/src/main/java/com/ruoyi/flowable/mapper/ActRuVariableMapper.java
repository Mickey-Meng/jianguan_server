package com.ruoyi.flowable.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.entity.ActRuVariable;
import com.ruoyi.flowable.domain.entity.ActRuVariableQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运行时变量表Mapper接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActRuVariableMapper extends BaseDaoMapper<ActRuVariable> {

    void updateActRuVariable(@Param("actRuVariable") ActRuVariable actRuVariable);

    List<ActRuVariable> findActRuVariable(@Param("actRuVariableQuery") ActRuVariableQuery actRuVariableQuery);
}