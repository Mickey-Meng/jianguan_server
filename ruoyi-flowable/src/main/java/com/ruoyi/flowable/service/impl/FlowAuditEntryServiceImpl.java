package com.ruoyi.flowable.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.dto.SsFUsersDTO;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.utils.jianguan.BeanCopyUtils;
import com.ruoyi.flowable.service.UserService;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.flowable.domain.dto.FlowAuditEntrySaveDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import com.ruoyi.flowable.mapper.FlowAuditEntryMapper;
import com.ruoyi.flowable.model.FlowEntry;
import com.ruoyi.flowable.model.FlowTaskExt;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowEntryService;
import com.ruoyi.flowable.service.FlowTaskExtService;
import io.netty.util.internal.StringUtil;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.Lists;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 流程节点审核人员 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-29
 */
@Service
public class FlowAuditEntryServiceImpl extends ServiceImpl<FlowAuditEntryMapper, FlowAuditEntry> implements FlowAuditEntryService {

    @Autowired
    private FlowTaskExtService flowTaskExtService;

    @Autowired
    private FlowAuditEntryMapper auditEntryMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FlowEntryService flowEntryService;

    @Autowired
    private FlowApiService flowApiService;


    /**
     * 通过流程key获取节点审核人员
     *
     * @param flowKey
     * @return
     */
    @Override
    public Map<String, List<FlowAuditEntryDetailVo>> getAuditEntryByFlowKey(String flowKey, Integer projectId, Integer buildSection) {
        //返回
        Map<String, List<FlowAuditEntryDetailVo>> result = Maps.newHashMap();
        //查询
        List<FlowAuditEntryDetailVo> detailVos = auditEntryMapper.getAuditEntryByFlowKey(flowKey, projectId, buildSection);
        //非空
        if (Objects.nonNull(detailVos) && !detailVos.isEmpty()) {
            detailVos.forEach(flowAuditEntrie -> {
                //用户id
                List<Integer> userIds = JSONArray.parseArray(flowAuditEntrie.getUserId(), Integer.class);
                flowAuditEntrie.setUserIds(userIds);
                //用户信息
                if (Objects.nonNull(userIds) && !userIds.isEmpty()) {
                    List<SsFUsersDTO> ssFUsersDTOList  = new ArrayList<>();
                    List<SsFUsers> ssFUsersList  = userService.getUsersByIds(userIds);
                    ssFUsersDTOList = com.ruoyi.common.utils.BeanCopyUtils.copyList(ssFUsersList,SsFUsersDTO.class);
                    flowAuditEntrie.setUserInfo(ssFUsersDTOList);
                }
                //用户名称
                List<String> userNames = JSONArray.parseArray(flowAuditEntrie.getUserName(), String.class);
                flowAuditEntrie.setUserNames(userNames);
                //抄送人员
                List<Integer> copyUserIds = JSONArray.parseArray(flowAuditEntrie.getCopyUser(), Integer.class);
                flowAuditEntrie.setCopyUserId(copyUserIds);
                if (Objects.nonNull(copyUserIds) && !copyUserIds.isEmpty()) {
                    List<SsFUsersDTO> ssFUsersDTOList  = new ArrayList<>();
                    List<SsFUsers> ssFUsersList  = userService.getUsersByIds(userIds);
                    ssFUsersDTOList = com.ruoyi.common.utils.BeanCopyUtils.copyList(ssFUsersList,SsFUsersDTO.class);
                    flowAuditEntrie.setUserInfo(ssFUsersDTOList);
                }
            });
            return detailVos.stream().collect(Collectors.groupingBy(FlowAuditEntryDetailVo::getTypeName));
        }
        return result;
    }

    /**
     * 根据流程key设置节点审批人
     *
     * @param flowKey
     * @return
     */
    @Override
    public ResponseResult<Map<String, Object>> addFlowAuditVariable(String flowKey, Integer projectId) {
//        //审批人对象
//        Map<String, Object> variable = Maps.newHashMap();
//        //流程定义key
//        BimFlowKey bimFlowKey = BimFlowKey.getEnum(flowKey);
//        if (Objects.isNull(bimFlowKey)) {
//            return ResponseResult.error(410, "未找到对应的流程！请联系管理员进行配置");
//        }
//        //通过流程key获取节点审核人员
//        List<FlowAuditEntryDetailVo> auditEntryByFlowKey = this.getAuditEntryByFlowKey(flowKey, projectId);
//        if (Objects.isNull(auditEntryByFlowKey) || auditEntryByFlowKey.isEmpty()) {
//            return ResponseResult.error(411, bimFlowKey.getKey() + "流程审核人未配置！请联系管理员进行配置后发起流程");
//        }
//        //查询流程主版本的审核节点，不包括开始和结束节点
//        List<FlowTaskExt> entryExtByFlowKey = flowTaskExtService.getEntryExtByFlowKey(flowKey);
//        if (Objects.isNull(entryExtByFlowKey) || entryExtByFlowKey.isEmpty()) {
//            return ResponseResult.error(412, bimFlowKey.getKey() + "流程未配置流程模板！请联系管理员进行配置");
//        }
//        //先比较流程节点和人数是否相等，相等则配置，不等则不对
//        if (auditEntryByFlowKey.size() != entryExtByFlowKey.size()) {
//            return ResponseResult.error(413, bimFlowKey.getKey() + "流程审批人员和节点不匹配！请联系管理员进行流程人员配置");
//        }
//        //配置人员
//        Map<String, List<FlowTaskExt>> taskFlowEntry = entryExtByFlowKey.stream().collect(Collectors.groupingBy(FlowTaskExt::getTaskId));
//        auditEntryByFlowKey.forEach(auditEntryByFlow -> {
//            List<FlowTaskExt> taskExts = taskFlowEntry.get(auditEntryByFlow.getEntryKey());
//            if (taskExts.isEmpty()) {
//                throw new RuntimeException(bimFlowKey.getKey() + "流程，" + auditEntryByFlow.getEntryKey() + "审批人员未配置！请联系管理员进行流程人员配置");
//            }
//            variable.put(auditEntryByFlow.getEntryUserVariable(), auditEntryByFlow.getUserName());
//        });
//        return ResponseResult.success(variable);
        return null;
    }

    /**
     * 通过流程key获取一条流程节点审核人员数据
     *
     * @param flowKey
     * @return
     */
    @Override
    public List<FlowAuditEntryDetailVo> getAuditInfoByFlowKey(String flowKey) {


        return null;
    }

    /**
     * 新增或者更新流程节点审核人员数据
     *
     * @param saveDto
     * @return
     */
    @Override
    public boolean addOrUpdate(FlowAuditEntrySaveDTO saveDto) {
        //属性copy
        FlowAuditEntry flowAuditEntry = new FlowAuditEntry();
        BeanUtils.copyProperties(saveDto, flowAuditEntry);
        //抄送人员id
        flowAuditEntry.setCopyUser(JSON.toJSONString(saveDto.getCopyUser()));
        //审核人员名称
        flowAuditEntry.setUserName(JSON.toJSONString(saveDto.getUserName()));
        //审核人员id
        flowAuditEntry.setUserId(JSON.toJSONString(saveDto.getUserId()));
        //新增
        if (Objects.isNull(saveDto.getId())) {
            flowAuditEntry.setId(IdUtil.nextLongId());
        }
        return this.saveOrUpdate(flowAuditEntry);
    }

    /**
     * 通过id获取一条流程节点审核人员数据
     *
     * @param id
     * @return
     */
    @Override
    public FlowAuditEntryDetailVo getInfoById(Long id) {
        //查询
        FlowAuditEntry flowAuditEntry = this.getById(id);
        if (Objects.isNull(flowAuditEntry)) {
            return null;
        }
        //属性转换
        FlowAuditEntryDetailVo vo = new FlowAuditEntryDetailVo();
        BeanUtils.copyProperties(flowAuditEntry, vo);
        //抄送人员id
        vo.setCopyUserId(JSONArray.parseArray(flowAuditEntry.getCopyUser(), Integer.class));
        //审核人员名称
        vo.setUserNames(JSONArray.parseArray(flowAuditEntry.getUserName(), String.class));
        //审核人员id
        vo.setUserIds(JSONArray.parseArray(flowAuditEntry.getUserId(), Integer.class));
        return vo;
    }

    /**
     * 分页查询流程节点审核人员数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<FlowAuditEntryDetailVo> getPageInfo(FlowAuditEntryPageDTO pageDto) {
        //结果
        List<FlowAuditEntryDetailVo> detailVos = Lists.newArrayList();
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<FlowAuditEntry> pageListVo = auditEntryMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(pageListVo) && !pageListVo.isEmpty()) {
            pageListVo.forEach(pageVo -> {
                FlowAuditEntryDetailVo vo = new FlowAuditEntryDetailVo();
                BeanUtils.copyProperties(pageVo, vo);
                //抄送人员id
                vo.setCopyUserId(JSONArray.parseArray(pageVo.getCopyUser(), Integer.class));
                //审核人员名称
                vo.setUserNames(JSONArray.parseArray(pageVo.getUserName(), String.class));
                //审核人员id
                vo.setUserIds(JSONArray.parseArray(pageVo.getUserId(), Integer.class));
                detailVos.add(vo);
            });
        }
        return new PageInfo<>(detailVos);
    }

    /**
     * 通过流程key获取一条流程节点抄送人员数据
     *
     * @param flowKey
     * @param projectId
     * @param entryKey
     * @return
     */
    @Override
    public FlowAuditEntryDetailVo getCopyUserByFlowKey(String flowKey, Integer projectId, String entryKey, Integer buildSection) {
        //返回
        FlowAuditEntryDetailVo detailVo = new FlowAuditEntryDetailVo();
        //查询
        LambdaQueryWrapper<FlowAuditEntry> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowAuditEntry::getFlowKey, flowKey);
        queryWrapper.eq(FlowAuditEntry::getProjectId, projectId);
        queryWrapper.eq(FlowAuditEntry::getEntryKey, entryKey);
        queryWrapper.eq(FlowAuditEntry::getBuildSection, buildSection);
        queryWrapper.last(" limit 1 ");
        //查询
        FlowAuditEntry flowAuditEntrie = this.getOne(queryWrapper);
        if (Objects.nonNull(flowAuditEntrie)) {
            BeanUtils.copyProperties(flowAuditEntrie, detailVo);
            //抄送人员id
            List<Integer> copyUserIds = JSONArray.parseArray(flowAuditEntrie.getCopyUser(), Integer.class);
            if (Objects.nonNull(copyUserIds) && !copyUserIds.isEmpty()) {
                detailVo.setCopyUserId(copyUserIds);
                List<SsFUsersDTO> ssFUsersDTOList  = new ArrayList<>();
                List<SsFUsers> ssFUsers = userService.getUsersByIds(copyUserIds);
                ssFUsersDTOList = com.ruoyi.common.utils.BeanCopyUtils.copyList(ssFUsers,SsFUsersDTO.class);
                detailVo.setCopyUserInfo(ssFUsersDTOList);
            }
            //审核人员名称
            detailVo.setUserNames(JSONArray.parseArray(flowAuditEntrie.getUserName(), String.class));
            //审核人员id
            detailVo.setUserIds(JSONArray.parseArray(flowAuditEntrie.getUserId(), Integer.class));
            return detailVo;
        }
        return null;
    }

    /**
     * 更新流程节点信息
     *
     * @param newEntryPublishId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowAuditEntryDetailVo updateFlowEntry(String newEntryPublishId) {
        //先删除key
        //查询最新的流程entry
        List<FlowTaskExt> flowEntries = flowTaskExtService.getEntryExtByFlowKey(newEntryPublishId);
        //循环
        if (Objects.nonNull(flowEntries) && !flowEntries.isEmpty()) {


        }


        return null;
    }

    /**
     * 新增流程节点信息
     *
     * @param flowKey
     * @param projectId
     * @param buildSection
     * @return
     */


    @SneakyThrows
    @Override
    public ResponseBase addFlowEntryByFlowKey(String flowKey, Integer projectId, Integer buildSection, Long typeId) {
        //错误信息
        String errorMessage = "";
        //获取流程最新的流程节点信息
        List<FlowTaskExt> flowTaskExts = flowTaskExtService.getEntryExtByFlowKey(flowKey);
        FlowEntry flowEntry = flowEntryService.getFlowEntryByProcessDefinitionKey(flowKey);
        if (Objects.isNull(flowEntry)) {
            return ResponseBase.error("当前流程key并不存在流程节点信息！");
        }
        String bpmnXml = flowEntry.getBpmnXml();
        BpmnModel bpmnModel = flowApiService.convertToBpmnModel(bpmnXml);
        Process process = bpmnModel.getMainProcess();
        if (process == null) {
            errorMessage = "数据验证失败，当前流程标识 [" + flowEntry.getProcessDefinitionKey() + "] 关联的流程模型并不存在！";
            return ResponseBase.error(errorMessage);
        }
        Collection<FlowElement> elementList = process.getFlowElements();
        Map<String, FlowElement> eleInfo = new HashMap<>();
        List<String> eleKey = Lists.newArrayList();
        Map<String, String> taskEntry = new HashMap<>();
        //获取流程的整体顺序节点
        elementList.forEach(element -> {
            //开始节点
            if (element instanceof StartEvent) {
                StartEvent startEvent = (StartEvent) element;
                taskEntry.put("start", startEvent.getId());
                eleInfo.put(startEvent.getId(), element);
            }
            //结束节点
            if (element instanceof EndEvent) {
                EndEvent endEvent = (EndEvent) element;
                taskEntry.put("end", endEvent.getId());
                eleInfo.put(endEvent.getId(), element);
            }
            //连接节点
            if (element instanceof SequenceFlow) {
                SequenceFlow sequenceFlow = (SequenceFlow) element;
                taskEntry.put(sequenceFlow.getSourceRef(), sequenceFlow.getTargetRef());
            }
            //任务节点
            if (element instanceof UserTask) {
                UserTask userTask = (UserTask) element;
                eleInfo.put(userTask.getId(), element);
            }
        });
        //开始节点
        String start = taskEntry.get("start");
        //结束节点
        String end = taskEntry.get("end");
        if (!StringUtil.isNullOrEmpty(start) && !StringUtil.isNullOrEmpty(end)) {
            for (; ; ) {
                String entry = taskEntry.get(start);
                if (!entry.equals(end)) {
                    eleKey.add(entry);
                    start = entry;
                }//表示已经结束
                else {
                    break;
                }
            }
        }

        //循环添加节点
        //判断
        if (!eleKey.isEmpty()) {
            //节点信息
            List<FlowAuditEntry> auditEntries = Lists.newArrayList();
            //去掉第一个节点
            for (int j = 0; j < eleKey.size(); j++) {
                FlowElement flowElement = eleInfo.get(eleKey.get(j));
                //转换为UserTask
                UserTask userTask = (UserTask) flowElement;
                //审核节点数据
                FlowAuditEntry auditEntry = new FlowAuditEntry();
                auditEntry.setId(IdUtil.nextLongId());
                auditEntry.setTypeId(typeId);
                auditEntry.setFlowKey(flowKey);
                auditEntry.setEntryKey(userTask.getId());
                auditEntry.setEntryName(userTask.getName());
                String assignee = userTask.getAssignee();
                if (StringUtil.isNullOrEmpty(assignee)) {
                    return ResponseBase.error("流程节点审核人未配置");
                }
                //是否会签
                String reaAssignee = assignee.replace("${", "").replace("}", "");
                auditEntry.setEntryUserVariable(reaAssignee);
                if ("assignee".equals(reaAssignee)) {
                    auditEntry.setEntryUserVariable("assigneeList");
                    auditEntry.setIsSign(1);
                }
                auditEntry.setSort(j + 1);
                auditEntry.setProjectId(projectId);
                auditEntry.setBuildSection(buildSection);
                auditEntry.setCopyUser("[]");
                auditEntry.setUserId("[]");
                auditEntry.setUserName("[]");
                auditEntries.add(auditEntry);
            }
            this.saveBatch(auditEntries);
        }
        return ResponseBase.success(true);
    }

    /**
     * 通过flowKey删除流程节点审核人员
     *
     * @param flowKey
     * @return
     */
    @Override
    public boolean removeByFlowKey(String flowKey) {
        return this.remove(new LambdaQueryWrapper<FlowAuditEntry>().eq(FlowAuditEntry::getFlowKey, flowKey));
    }

    /**
     * 配置节点数量
     *
     * @param id
     * @param projectId
     * @param buildSection
     * @return
     */
    @Override
    public int getCount(Long id, Integer projectId, Integer buildSection) {
        LambdaQueryWrapper<FlowAuditEntry> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowAuditEntry::getTypeId, id);
        queryWrapper.eq(FlowAuditEntry::getProjectId, projectId);
        queryWrapper.eq(FlowAuditEntry::getBuildSection, buildSection);
        List<FlowAuditEntry> flowAuditEntries = this.list(queryWrapper);
        return flowAuditEntries.size();
    }

    /**
     * 获取流程的人员变量
     *
     * @param flowKey
     * @param projectId
     * @param buildSection
     * @return
     */
    @Override
    public List<String> getUSerVariable(String flowKey, Integer projectId, Integer buildSection) {
        LambdaQueryWrapper<FlowAuditEntry> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowAuditEntry::getFlowKey, flowKey);
        queryWrapper.eq(FlowAuditEntry::getProjectId, projectId);
        queryWrapper.eq(FlowAuditEntry::getBuildSection, buildSection);
        List<FlowAuditEntry> flowAuditEntries = this.list(queryWrapper);
        //获取用户变量
        return Objects.requireNonNull(flowAuditEntries).stream().map(FlowAuditEntry::getEntryUserVariable).collect(Collectors.toList());
    }

    /**
     * 数字模型
     */
    Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
