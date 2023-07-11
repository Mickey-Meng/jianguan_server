package com.ruoyi.flowable.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.entity.ActHiTaskinst;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import org.apache.ibatis.annotations.Param;

/**
 * 历史的任务实例Mapper接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActHiTaskinstMapper extends BaseDaoMapper<ActHiTaskinst> {

    void updateActHiTaskinst(@Param("actHiTaskinst") ActHiTaskinst actHiTaskinst);

}