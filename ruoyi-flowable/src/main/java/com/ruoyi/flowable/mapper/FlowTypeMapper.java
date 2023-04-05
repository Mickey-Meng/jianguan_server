package com.ruoyi.flowable.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.flowable.domain.dto.FlowTypePageDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.flowable.domain.vo.FlowTypeDetailVo;
import com.ruoyi.flowable.model.FlowType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 流程类型 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Mapper
@Repository
public interface FlowTypeMapper extends BaseDaoMapper<FlowType> {
    /**
     * 分页查询流程类型数据
     *
     * @param pageDto
     * @return
     */
    List<FlowTypeDetailVo> getPageInfo(@Param("pageDto") FlowTypePageDTO pageDto);

    /**
     * 通过流程分类id查询流程审核人数据
     *
     * @param projectId
     * @param id
     * @return
     */
    List<FlowAuditEntry> getAuditInfoByTypeId(@Param("projectId") Integer projectId, @Param("id") Long id, @Param("buildSection") Integer buildSection);
}
