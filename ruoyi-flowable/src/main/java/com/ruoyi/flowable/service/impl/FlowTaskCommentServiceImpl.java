package com.ruoyi.flowable.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.common.core.sequence.wrapper.IdGeneratorWrapper;
import com.ruoyi.common.core.service.BaseService;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.flowable.mapper.FlowTaskCommentMapper;
import com.ruoyi.flowable.model.FlowTaskComment;
import com.ruoyi.flowable.service.FlowTaskCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 流程任务批注数据操作服务类。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Slf4j
@Service("flowTaskCommentService")
public class FlowTaskCommentServiceImpl extends BaseService<FlowTaskComment, Long> implements FlowTaskCommentService {

    @Autowired
    private FlowTaskCommentMapper flowTaskCommentMapper;
    @Autowired
    private IdGeneratorWrapper idGenerator;

    /**
     * 返回当前Service的主表Mapper对象。
     *
     * @return 主表Mapper对象。
     */
    @Override
    protected BaseDaoMapper<FlowTaskComment> mapper() {
        return flowTaskCommentMapper;
    }

    /**
     * 保存新增对象。
     *
     * @param flowTaskComment 新增对象。
     * @return 返回新增对象。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowTaskComment saveNew(FlowTaskComment flowTaskComment) {
        flowTaskComment.setId(idGenerator.nextLongId());
//        TokenData tokenData = TokenData.takeFromRequest();
        String name = JwtUtil.getUserNameByToken();
        Integer id = LoginHelper.getUserId().intValue();
        flowTaskComment.setCreateUserId(id.longValue());
        flowTaskComment.setCreateLoginName(name);
        flowTaskComment.setCreateUsername(name);
        flowTaskComment.setCreateTime(new Date());
        flowTaskCommentMapper.insert(flowTaskComment);
        return flowTaskComment;
    }

    /**
     * 查询指定流程实例Id下的所有审批任务的批注。
     *
     * @param processInstanceId 流程实例Id。
     * @return 查询结果集。
     */
    @Override
    public List<FlowTaskComment> getFlowTaskCommentList(String processInstanceId) {
        LambdaQueryWrapper<FlowTaskComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowTaskComment::getProcessInstanceId, processInstanceId);
        queryWrapper.orderByAsc(FlowTaskComment::getId);
        return flowTaskCommentMapper.selectList(queryWrapper);
    }

    @Override
    public List<FlowTaskComment> getFlowTaskCommentListByTaskIds(Set<String> taskIdSet) {
        LambdaQueryWrapper<FlowTaskComment> queryWrapper =
                new LambdaQueryWrapper<FlowTaskComment>().in(FlowTaskComment::getTaskId, taskIdSet);
        queryWrapper.orderByDesc(FlowTaskComment::getId);
        return flowTaskCommentMapper.selectList(queryWrapper);
    }

    @Override
    public FlowTaskComment getLatestFlowTaskComment(String processInstanceId) {
        LambdaQueryWrapper<FlowTaskComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowTaskComment::getProcessInstanceId, processInstanceId);
        queryWrapper.orderByDesc(FlowTaskComment::getId);
        IPage<FlowTaskComment> pageData = flowTaskCommentMapper.selectPage(new Page<>(1, 1), queryWrapper);
        return CollUtil.isEmpty(pageData.getRecords()) ? null : pageData.getRecords().get(0);
    }

    @Override
    public FlowTaskComment getLatestFlowTaskComment(String processInstanceId, String taskDefinitionKey) {
        LambdaQueryWrapper<FlowTaskComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowTaskComment::getProcessInstanceId, processInstanceId);
        queryWrapper.eq(FlowTaskComment::getTaskKey, taskDefinitionKey);
        queryWrapper.orderByDesc(FlowTaskComment::getId);
        IPage<FlowTaskComment> pageData = flowTaskCommentMapper.selectPage(new Page<>(1, 1), queryWrapper);
        return CollUtil.isEmpty(pageData.getRecords()) ? null : pageData.getRecords().get(0);
    }

    @Override
    public FlowTaskComment getFirstFlowTaskComment(String processInstanceId) {
        LambdaQueryWrapper<FlowTaskComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowTaskComment::getProcessInstanceId, processInstanceId);
        queryWrapper.orderByAsc(FlowTaskComment::getId);
        IPage<FlowTaskComment> pageData = flowTaskCommentMapper.selectPage(new Page<>(1, 1), queryWrapper);
        return CollUtil.isEmpty(pageData.getRecords()) ? null : pageData.getRecords().get(0);
    }
}
