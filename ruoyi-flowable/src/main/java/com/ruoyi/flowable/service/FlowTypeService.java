package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flowable.domain.dto.FlowTypePageDTO;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import com.ruoyi.flowable.domain.vo.FlowTypeDetailVo;
import com.ruoyi.flowable.model.FlowType;

import java.util.List;

/**
 * 流程类型 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
public interface FlowTypeService extends IService<FlowType> {
    /**
     * 新增或者更新流程类型数据
     *
     * @param flowType
     * @return
     */
    boolean addOrUpdate(FlowType flowType);

    /**
     * 分页查询流程类型数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<FlowTypeDetailVo> getPageInfo(FlowTypePageDTO pageDto);

    /**
     * 通过流程分类id查询流程审核人数据
     *
     * @param projectId
     * @param id
     * @return
     */
    List<FlowAuditEntryDetailVo> getAuditInfoByTypeId(Integer projectId, Long id, Integer buildSection);

    /**
     * 通过id删除一条流程类型数据
     *
     * @param id
     * @return
     */
    boolean delById(Long id);
}
