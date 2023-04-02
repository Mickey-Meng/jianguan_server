package com.ruoyi.flowable.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.model.FlowEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * FlowEntry数据操作访问接口。
 *
 * @author Jerry
 * @date 2021-06-06
 */
public interface FlowEntryMapper extends BaseDaoMapper<FlowEntry> {

    /**
     * 获取过滤后的对象列表。
     *
     * @param flowEntryFilter 主表过滤对象。
     * @param orderBy         排序字符串，order by从句的参数。
     * @return 对象列表。
     */
    List<FlowEntry> getFlowEntryList(
            @Param("flowEntryFilter") FlowEntry flowEntryFilter, @Param("orderBy") String orderBy);

    /**
     * 查询流程所有节点
     *
     * @param newEntryPublishId
     * @return
     */
    @Select("select t.* from  zz_flow_entry_publish p left join zz_flow_task_ext t on p.process_definition_id\n" +
            " = t.process_definition_id where p.entry_publish_id = #{newEntryPublishId}")
    List<FlowEntry> getFlowEntryByPublishId(String newEntryPublishId);

    /**
     * 获取流程最新的流程节点信息
     *
     * @param flowKey
     * @return
     */
    @Select("select t.* from zz_flow_entry e left join zz_flow_entry_publish p on e.main_entry_publish_id = p.entry_publish_id " +
            " left join zz_flow_task_ext t on p.process_definition_id = t.process_definition_id where e.process_definition_key=#{flowKey}")
    List<FlowEntry> getFlowEntryByFlowKey(String flowKey);
}
