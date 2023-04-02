package com.ruoyi.flowable.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.object.MyPageData;
import com.ruoyi.common.core.domain.object.MyPageParam;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.common.core.sequence.wrapper.IdGeneratorWrapper;
import com.ruoyi.common.core.service.BaseService;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.FlowTaskHandleStatus;
import com.ruoyi.flowable.mapper.FlowTaskHandleMapper;
import com.ruoyi.flowable.model.FlowTaskHandle;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.flowable.service.FlowTaskHandleService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lpeng
 * @Date: 2022-05-05 14:34
 * @Description:
 */

@Slf4j
@Service("FlowTaskHandleService")
public class FlowTaskHandleServiceImpl extends BaseService<FlowTaskHandle, Long> implements FlowTaskHandleService {

    @Autowired
    private FlowTaskHandleMapper flowTaskHandleMapper;
    @Autowired
    private IdGeneratorWrapper idGenerator;
    @Autowired
    private TaskService taskService;

    @Autowired
    private FlowApiService flowApiService;
    /**
     * 返回当前Service的主表Mapper对象。
     *
     * @return 主表Mapper对象。
     */
    @Override
    protected BaseDaoMapper<FlowTaskHandle> mapper() {
        return flowTaskHandleMapper;
    }

    /**
     * 保存新增对象。
     * @return 返回新增对象。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowTaskHandle saveUnHandleByTask(String processInstanceId,String taskId){
        FlowTaskHandle flowTaskHandle=new  FlowTaskHandle();
        Task task=flowApiService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        flowTaskHandle.setTaskHandleStatus(FlowTaskHandleStatus.UNHANDLE);
        flowTaskHandle.setTaskHandleStatusStr(FlowTaskHandleStatus.getName(FlowTaskHandleStatus.UNHANDLE));
        flowTaskHandle.setTaskId(taskId);
        flowTaskHandle.setTaskKey(task.getTaskDefinitionKey());
        flowTaskHandle.setTaskName(task.getName());
        flowTaskHandle.setProcessInstanceId(processInstanceId);
        if(StringUtils.isNotBlank(task.getExecutionId())){
            flowTaskHandle.setTaskHandleUserId(Long.parseLong(task.getExecutionId()));
        }
        return this.saveUnHandleNew(flowTaskHandle);
    }
    /**
     * 保存新增对象。
     *
     * @param flowTaskHandle 新增对象。
     * @return 返回新增对象。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowTaskHandle saveUnHandleNew(FlowTaskHandle flowTaskHandle) {
        if(StringUtils.isNotBlank(flowTaskHandle.getTaskId())){
            List<FlowTaskHandle> taskHandle=this.list(new QueryWrapper<FlowTaskHandle>().eq("task_id",flowTaskHandle.getTaskId()));
            if(!CollectionUtils.isEmpty(taskHandle)){
                return taskHandle.get(0);
            }else{
                flowTaskHandle.setId(idGenerator.nextLongId());
                if(FlowTaskHandleStatus.isValid(flowTaskHandle.getTaskHandleStatus())){
                    //设置描述
                    flowTaskHandle.setTaskHandleStatusStr(FlowTaskHandleStatus.getName(flowTaskHandle.getTaskHandleStatus()));
                }
//                TokenData tokenData = TokenData.takeFromRequest();
                SsFUsers user = JwtUtil.getUserToken();
                flowTaskHandle.setCreateUserId(user.getId().longValue());
                flowTaskHandle.setCreateLoginName(user.getUsername());
                flowTaskHandle.setCreateUsername(user.getName());
                flowTaskHandle.setCreateTime(new Date());
                flowTaskHandleMapper.insert(flowTaskHandle);
                return flowTaskHandle;
            }
        }else{
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean setTaskHandleStatus(Long id,Integer handleStatus) {
        FlowTaskHandle flowTaskHandle=this.getById(id);
        if(flowTaskHandle==null){
            return  false;
        }
        if(FlowTaskHandleStatus.isValid(handleStatus)){
            flowTaskHandle.setTaskHandleStatus(handleStatus);
            //设置描述
            flowTaskHandle.setTaskHandleStatusStr(FlowTaskHandleStatus.getName(FlowTaskHandleStatus.HANDLEING));
        }else{
            return  false;
        }
        flowTaskHandleMapper.updateById(flowTaskHandle);
        return true;
    }

    /**
     * 查询 待办 在办任务数据
     * @param username       指派人。
     * @param definitionKey  流程定义的标识。
     * @param definitionName 流程定义名。
     * @param taskName       任务名称。
     * @param handleStatus
     * @param pageParam      分页对象。
     * @return
     */
    @Override
    public MyPageData<Task> getTaskListByUserName(
            String username, String definitionKey, String definitionName, String taskName,Integer handleStatus, MyPageParam pageParam) {
        // 首先查询出 之前的激活的任务数据
        TaskQuery query = taskService.createTaskQuery().active();
        if (StrUtil.isNotBlank(definitionKey)) {
            query.processDefinitionKey(definitionKey);
        }
        if (StrUtil.isNotBlank(definitionName)) {
            query.processDefinitionNameLike("%" + definitionName + "%");
        }
        if (StrUtil.isNotBlank(taskName)) {
            query.taskNameLike("%" + taskName + "%");
        }
        // 查询
        this.buildCandidateCondition(query, username);
        query.orderByTaskCreateTime().desc();
        List<Task> taskListTem = query.list();
        //在办 已办
        if(handleStatus.equals(FlowTaskHandleStatus.HANDLEING)||handleStatus.equals(FlowTaskHandleStatus.ALREADHANDLE)){
            QueryWrapper<FlowTaskHandle> queryWrapper=new QueryWrapper();
            if (null!=handleStatus) {
                queryWrapper.eq("task_handle_status", handleStatus );
            }
            if(!CollectionUtils.isEmpty(taskListTem)){
                queryWrapper.in("task_id",taskListTem.stream().map(Task::getId).collect(Collectors.toList()));
            }
            queryWrapper.orderByDesc("create_time");
            IPage<FlowTaskHandle> page = flowTaskHandleMapper.selectPage(new Page(pageParam.getPageNum(),pageParam.getPageSize()),queryWrapper);

            if(page!=null){
                long totalCount = page.getTotal();
                List<String> ids=page.getRecords().stream().map(m->m.getTaskId()).collect(Collectors.toList());
                taskListTem=taskListTem.stream().filter(i-> ids.contains(i.getId())).collect(Collectors.toList());
                return new MyPageData<>(taskListTem, totalCount);
            }else{
                return new MyPageData<>(new ArrayList<>(), 0L);
            }
        }else{
            // 待办
            // 去除 正在处理,或者已经处理了的的数据
            QueryWrapper<FlowTaskHandle> queryWrapper=new QueryWrapper();
            if(!CollectionUtils.isEmpty(taskListTem)){
                queryWrapper.in("task_id",taskListTem.stream().map(Task::getId).collect(Collectors.toList()));
            }
            List<String> stringLists =this.list(queryWrapper).stream().map(m->m.getTaskId()).collect(Collectors.toList());
            long totalCount = taskListTem.size()-stringLists.size();
            if(!CollectionUtils.isEmpty(stringLists)){
                taskListTem =taskListTem.stream().filter(i-> !stringLists.contains(i.getId())).collect(Collectors.toList());
            }
            int firstResult = (pageParam.getPageNum() - 1) * pageParam.getPageSize();
            List<Task> taskList = taskListTem.stream().skip(firstResult).limit(pageParam.getPageSize()).collect(Collectors.toList());
            return new MyPageData<>(taskList, totalCount);
        }

    }

    private void buildCandidateCondition(TaskQuery query, String loginName) {
        Set<String> groupIdSet = new HashSet<>();
        // NOTE: 需要注意的是，部门Id、部门岗位Id，或者其他类型的分组Id，他们之间一定不能重复。
//        TokenData tokenData = TokenData.takeFromRequest();
//        Object deptId = tokenData.getDeptId();
//        if (deptId != null) {
//            groupIdSet.add(deptId.toString());
//        }
//        String roleIds = tokenData.getRoleIds();
//        if (StrUtil.isNotBlank(tokenData.getRoleIds())) {
//            groupIdSet.addAll(StrUtil.split(roleIds, ","));
//        }
//        String postIds = tokenData.getPostIds();
//        if (StrUtil.isNotBlank(tokenData.getPostIds())) {
//            groupIdSet.addAll(StrUtil.split(postIds, ","));
//        }
//        String deptPostIds = tokenData.getDeptPostIds();
//        if (StrUtil.isNotBlank(deptPostIds)) {
//            groupIdSet.addAll(StrUtil.split(deptPostIds, ","));
//        }
        if (CollUtil.isNotEmpty(groupIdSet)) {
            query.or().taskCandidateGroupIn(groupIdSet).taskCandidateOrAssigned(loginName).endOr();
        } else {
            query.taskCandidateOrAssigned(loginName);
        }
    }

}
