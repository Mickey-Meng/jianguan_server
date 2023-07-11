package com.ruoyi.flowable.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.entity.ActHiTaskinst;
import com.ruoyi.flowable.domain.entity.ActHiVarinst;
import org.apache.ibatis.annotations.Param;

/**
 * 历史的流程运行中的变量信息Mapper接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActHiVarinstMapper extends BaseDaoMapper<ActHiVarinst> {

    void updateActHiVarinst(@Param("actHiVarinst") ActHiVarinst actHiVarinst);

}