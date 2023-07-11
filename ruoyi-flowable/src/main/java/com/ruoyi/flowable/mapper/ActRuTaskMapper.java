package com.ruoyi.flowable.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import org.apache.ibatis.annotations.Param;

/**
 * 运行时任务Mapper接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActRuTaskMapper extends BaseDaoMapper<ActRuTask> {

    void updateActRuTask(@Param("actRuTask") ActRuTask actRuTask);

}