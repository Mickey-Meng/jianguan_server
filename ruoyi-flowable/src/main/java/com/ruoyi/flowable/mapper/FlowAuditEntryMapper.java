package com.ruoyi.flowable.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 流程节点审核人员 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-29
 */
@Mapper
@Repository
public interface FlowAuditEntryMapper extends BaseDaoMapper<FlowAuditEntry> {
    /**
     * 分页查询流程节点审核人员数据
     *
     * @param pageDto
     * @return
     */
    List<FlowAuditEntry> getPageInfo(@Param("pageDto") FlowAuditEntryPageDTO pageDto);

    /**
     * 查询
     *
     * @param flowKey
     * @param projectId
     * @param buildSection
     * @return
     */
    List<FlowAuditEntryDetailVo> getAuditEntryByFlowKey(@Param("flowKey") String flowKey,
                                                        @Param("projectId") Integer projectId,
                                                        @Param("buildSection") Integer buildSection);
}
