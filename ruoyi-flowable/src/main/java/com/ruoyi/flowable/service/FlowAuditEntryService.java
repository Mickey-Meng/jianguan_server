package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.flowable.domain.dto.FlowAuditEntrySaveDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;

import java.util.List;
import java.util.Map;

/**
 * 流程节点审核人员 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-29
 */
public interface FlowAuditEntryService extends IService<FlowAuditEntry> {


    /**
     * 通过流程key获取节点审核人员
     *
     * @param flowKey
     * @return
     */
    Map<String,List<FlowAuditEntryDetailVo>> getAuditEntryByFlowKey(String flowKey, Integer projectId, Integer buildSection);


    /**
     * 根据流程key设置节点审批人
     *
     * @param flowKey
     * @return
     */
    ResponseResult<Map<String, Object>> addFlowAuditVariable(String flowKey, Integer projectId);

    /**
     * 通过流程key获取一条流程节点审核人员数据
     *
     * @param flowKey
     * @return
     */
    List<FlowAuditEntryDetailVo> getAuditInfoByFlowKey(String flowKey);

    /**
     * 新增或者更新流程节点审核人员数据
     *
     * @param saveDto
     * @return
     */
    boolean addOrUpdate(FlowAuditEntrySaveDTO saveDto);

    /**
     * 通过id获取一条流程节点审核人员数据
     *
     * @param id
     * @return
     */
    FlowAuditEntryDetailVo getInfoById(Long id);

    /**
     * 分页查询流程节点审核人员数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<FlowAuditEntryDetailVo> getPageInfo(FlowAuditEntryPageDTO pageDto);

    /**
     * 通过流程key获取一条流程节点抄送人员数据
     *
     * @param flowKey
     * @param projectId
     * @param entryKey
     * @return
     */
    FlowAuditEntryDetailVo getCopyUserByFlowKey(String flowKey, Integer projectId, String entryKey, Integer buildSection);

    /**
     * 更新流程节点信息
     *
     * @param newEntryPublishId
     * @return
     */
    FlowAuditEntryDetailVo updateFlowEntry(String newEntryPublishId);

    /**
     * 新增流程节点信息
     *
     * @param flowKey
     * @param projectId
     * @param buildSection
     * @return
     */
    ResponseBase addFlowEntryByFlowKey(String flowKey, Integer projectId, Integer buildSection, Long typeId);

    /**
     * 通过flowKey删除流程节点审核人员
     *
     * @param flowKey
     * @return
     */
    boolean removeByFlowKey(String flowKey);

    /**
     * 配置节点数量
     *
     * @param id
     * @param projectId
     * @param buildSection
     * @return
     */
    int getCount(Long id, Integer projectId, Integer buildSection);

    /**
     * 获取流程的人员变量
     *
     * @param flowKey
     * @param projectId
     * @param buildSection
     * @return
     */
    List<String> getUSerVariable(String flowKey, Integer projectId, Integer buildSection);
}
