package com.ruoyi.flowable.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.model.FlowTaskExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程任务扩展数据操作访问接口。
 *
 * @author Jerry
 * @date 2021-06-06
 */
public interface FlowTaskExtMapper extends BaseDaoMapper<FlowTaskExt> {

    /**
     * 批量插入流程任务扩展信息列表。
     *
     * @param flowTaskExtList 流程任务扩展信息列表。
     */
    void insertList(List<FlowTaskExt> flowTaskExtList);

    /**
     * 查询流程主版本的审核节点，不包括开始和结束节点
     *
     * @param flowKey
     * @return
     */
    List<FlowTaskExt> getEntryExtByFlowKey(@Param("flowKey") String flowKey);
}
