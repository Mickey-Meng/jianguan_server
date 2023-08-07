package com.ruoyi.jianguan.common.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.model.ZjPersonChange;
import com.ruoyi.common.core.domain.model.ZjPersonChangeFile;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.common.core.domain.object.MyPageParam;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jianguan.zjrw.DateUtils;
import com.ruoyi.common.utils.jianguan.zjrw.RestApiUtils;
import com.ruoyi.common.utils.jianguan.zjrw.httputils.HttpHeader;
import com.ruoyi.common.utils.jianguan.zjrw.httputils.HttpParamers;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.flowable.domain.vo.FlowKeysVo;
import com.ruoyi.flowable.domain.vo.FlowTaskCommentVo;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.contract.dao.PersonDAO;
import com.ruoyi.jianguan.business.contract.dao.ZjPersonFenceDAO;
import com.ruoyi.jianguan.common.dao.*;
import com.ruoyi.jianguan.common.domain.dto.PersonGroupTree;
import com.ruoyi.jianguan.common.domain.dto.PersonSubDTO;
import com.ruoyi.jianguan.common.domain.dto.TaskCommentReturn;
import com.ruoyi.jianguan.common.domain.entity.*;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.JwtUtil.getRequest;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/9 13:41
 * @Version : 1.0
 * @Description :
 **/
@Service
public class PersonService {

    Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SsFUsersDAO usersDAO;
    @Autowired
    private ProjectsDAO projectsDAO;
    @Autowired
    private SsFUserGroupDAO userGroupDAO;
    @Autowired
    private ZjPersonChangeDAO personChangeDAO;
    @Autowired
    private ZjPersonLeaveDAO personLeaveDAO;
    @Autowired
    private ZjPersonClockinDAO clockinDAO;
    @Autowired
    private ZjPersonFenceDAO fenceDAO;
    @Autowired
    private SsFUserOnlineDAO userOnlineDAO;
    @Autowired
    private FileMapper fileMapper;


    public ResponseBase addDepartment(List<SsFPersonGroups> ssFGroups) {
        Integer role = null;
        try {
            role = LoginHelper.getLoginUser().getRoleId().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "请验证token的有效性!");
        }
        if (role != 2) {
            return new ResponseBase(200, "该用户没有配置部门组织的权限!");
        }
        for (SsFPersonGroups ssFGroup : ssFGroups) {
            ssFGroup.setStatus(1);
            if (ssFGroup.getPid() == 0) {
                ssFGroup.setOrder(0);
            } else {
                ssFGroup.setOrder(1);
            }
        }
        Integer row = personDAO.bulkInsertSsFGroups(ssFGroups);
        if (row == ssFGroups.size()) {
            return new ResponseBase(200, "添加部门成功!");
        }
        return new ResponseBase(200, "添加部门失败!");
    }

    public ResponseBase updateDepartment(SsFPersonGroups ssFGroup) {
        Integer role = null;
        try {
            role = LoginHelper.getLoginUser().getRoleId().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "请验证token的有效性!");
        }
        if (role != 2) {
            return new ResponseBase(200, "该用户没有修改部门组织的权限!");
        }
        if (ssFGroup.getId() <= 0) {
            return new ResponseBase(200, "要修改的部门id无效!");
        }
        Integer row = personDAO.updateDepartment(ssFGroup);
        if (row == 1) {
            return new ResponseBase(200, "修改部门信息成功!");
        }
        return new ResponseBase(500, "修改部门信息失败!");
    }

    public ResponseBase getDepartments() {
        PersonGroupTree tree = new PersonGroupTree();
        tree.setName("顶级");
        tree.setCode("super");
        tree.setPid(-1);
        tree.setId(0);
        List<PersonGroupTree> trees = personDAO.getSsFGroups();
        List<PersonGroupTree> newTree = getChildrenBase(trees, tree);
        return new ResponseBase(200, "查询成功!", newTree);
    }

    private List<PersonGroupTree> getChildrenBase(List<PersonGroupTree> tree, PersonGroupTree baseTree) {
        List<PersonGroupTree> children = tree.stream().filter(item -> Integer.compare(item.getPid(), baseTree.getId()) == 0)
                .map((item) -> {
                    item.setChildren(getChildrenBase(tree, item));
                    return item;
                }).collect(Collectors.toList());
        return children;
    }

    public ResponseBase deleteDepartment(Integer id) {
        Integer role = LoginHelper.getLoginUser().getRoleId().intValue();
        if (role != 2) {
            return new ResponseBase(200, "该用户没有删除部门组织的权限!");
        }
        if (id <= 0) {
            return new ResponseBase(200, "当前部门id无效!");
        }
        Integer row = personDAO.deleteDepartment(id);
        if (row == 1) {
            return new ResponseBase(200, "删除部门信息成功!");
        }
        return new ResponseBase(200, "删除部门信息失败!");
    }

    public ResponseBase addUserGroup(List<PersonUserGroupRole> ssFGroups) {
        for (PersonUserGroupRole ssFGroup : ssFGroups) {
            ssFGroup.setSttime(new Date());
            ssFGroup.setStorder(1);
            ssFGroup.setStatus(1);

            SsFRoles role = personDAO.getRoleByUserid(ssFGroup.getUserid());
            ssFGroup.setRoleid(role.getId());
            ssFGroup.setRolename(role.getName());

            //查询表里面是否存在该用户id
            Integer row1 = personDAO.getUserGroupByUserid(ssFGroup.getUserid());
            if (row1 == 1) {
                //如果存在,则先删除
                personDAO.deleteUserGroup(ssFGroup.getUserid());
            }
            //最后再插入
            Integer row = personDAO.insertUserGroup(ssFGroup);
            if (row == 1) {
                return new ResponseBase(200, "关联成功!");
            }
        }
        return new ResponseBase(200, "关联失败!");
    }

    public ResponseBase getPersonRole() {
        List<PersonUserGroupRole> roles = personDAO.getPersonGroupRoles();
        if (roles.size() == 0) {
            return new ResponseBase(200, "暂未查到数据!");
        }
        return new ResponseBase(200, "查询成功!", roles);
    }

    public ResponseBase getGroupByUserid(Integer userid) {
        if (userid <= 0) {
            return new ResponseBase(200, "当前用户id无效!");
        }
        Integer row = personDAO.getGroupidByUserid(userid);
        if (row <= 0) {
            return new ResponseBase(200, "该用户没有部门权限!");
        }
        List<SsFGroups> groups = personDAO.getGroupByUserid(userid);

        return new ResponseBase(200, "查询成功!", groups);
    }

    public ResponseBase getUserByGroup() {
        List<PersonGroupTree> personGroups = personDAO.getSsFGroups();
        List<PersonUserGroupRole> userGroups = personDAO.getPersonGroupRoles();
        Map<String, Object> maps = new HashMap<>();
        maps.put("personGroups", personGroups);
        maps.put("personGroupsUsers", userGroups);
        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getUserByRole(Integer roleid, Integer projectId) {
        if (roleid <= 0) {
            return new ResponseBase(200, "当前角色id无效!");
        }
        //获取该项目id下的所有部门id
//        List groups = projectsDAO.getGroupIdsById(projectId);
//        List<SsFUsers> users = Lists.newArrayList();
        // 调整查询逻辑，根据项目查询工工区，根据工区和角色查询用户
        List<SsFUsers> users = personDAO.getByRoleIdAndProjectId(roleid, projectId);

//        if (groups != null || !groups.equals("")) {
//           /* List<Integer> groupIds = Arrays.stream(groups.split(","))
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());*/
//
//            users = personDAO.getByRoleId(roleid, groups);
//        }

        return new ResponseBase(200, "查询成功!", users);
    }

    public ResponseBase delContract(Integer id, Integer projectId) {
        try {
            if (projectId <= 0) {
                return new ResponseBase(200, "请输入有效的项目id!");
            }
            Integer count1 = projectsDAO.getCountById(projectId);
            if (count1 <= 0) {
                return new ResponseBase(200, "该项目id无数据!");
            }
//            String token = getRequest().getHeader("Authorization");
//            if (token == null) {
//                return new ResponseBase(200, "token验证失败!");
//            }
            PersonDTO personDTO = personDAO.selectByPrimaryKey(id);
            if (personDTO == null) {
                return new ResponseBase(200, "没有找到有效的合同报审数据!");
            }
            String processInstanceId = personDTO.getTaskId();
            //调用停止接口 admin/flow/flowOperation/stopProcessInstance
            flowApiService.deleteProcessInstance(processInstanceId);
            personDAO.delContractById(id, projectId);
            // 删除zj_person_people_sub对应的合同人员数据
            List<PersonSub> personSubs = personDAO.getAllPersonSubById(id);
            if (personSubs.size() > 0) {
                for (PersonSub personSub : personSubs) {
                    if (personSub.getPeoplePic() != null && !personSub.getPeoplePic().equals("")) {
                        fileMapper.delete(personSub.getPeoplePic());
                    }
                }
            }
            personDAO.delPersonSubByGid(id);
            return new ResponseBase(200, "删除合同成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "删除合同报审失败!", e.getStackTrace());
        }
    }

    public ResponseBase updateContract(PersonSubDTO person) {
        Integer userid = LoginHelper.getUserId().intValue();

        if (person.getPerson().getId() <= 0) {
            return new ResponseBase(200, "该合同id无效");
        }
        Integer row = personDAO.getPersonCountById(person.getPerson().getId());
        if (row <= 0) {
            return new ResponseBase(200, "该合同id没有记录数据");
        }
        person.getPerson().setRecordId(userid);
        personDAO.updateContract(person.getPerson());
        for (PersonSub personSub : person.getPersonSubs()) {
            personDAO.updateContractPersons(personSub);
        }
        return new ResponseBase(200, "修改合同信息成功!");
    }

    public ResponseBase getContracts(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();
        List<PersonSubDTO> personSubDTOS = Lists.newArrayList();
        Set<String> roleKey = LoginHelper.getLoginUser().getRolePermission();
        //add by yangaogao 20230731  管理员可以查看所有数据
        if (roleKey.contains("gly") || roleKey.contains("admin")){
            userId = null;
        }
        List<PersonDTO> persons = personDAO.getAllPersonByUseridAndProjectId(userId, projectId);

        //调用接口获取数据
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        //通过processInstanceId获取该流程的三个id
        if (persons.size() > 0) {
            for (PersonDTO person : persons) {
                Map<String, Object> maps = Maps.newHashMap();
                PersonSubDTO subDTO = new PersonSubDTO();
                Integer userid = person.getRecordId();
                Integer projectid = person.getProjectId();
                String name = usersDAO.getNameByUserId(userid);
                String projectName = projectsDAO.getNameById(projectid);
                person.setRecodeName(name);
                person.setProjectName(projectName);

                subDTO.setPerson(person);

                List<PersonSub> subs = personDAO.getPersonByGid(person.getId());
                subDTO.setPersonSubs(subs);

                Map<String, String> getIds = new WeakHashMap();
                if (person.getBusinessKey() != null && !person.getBusinessKey().equals("")){
                    getIds = getFlowAndTaskInfo(person.getBusinessKey(), token);
                    if (getIds.get("processDefinitionId") != null) {
                        maps.put("processDefinitionId", getIds.get("processDefinitionId"));
                    }
                    if (getIds.get("processInstanceId") != null) {
                        maps.put("processInstanceId", getIds.get("processInstanceId"));
                    }
                    if (getIds.get("taskId") != null) {
                        maps.put("taskId", getIds.get("taskId"));
                    }
                    person.setMaps(maps);
                } else {
                    //这里的taskId为processInstanceId
                    TaskCommentReturn commentReturn = taskCommentReturn(person.getTaskId(), token);
                    if (commentReturn != null) {
                        getIds = personDAO.getIdsByProcessInstanceId(person.getTaskId());
                        if (person.getTaskId() != null) {
                            maps.put("processInstanceId", person.getTaskId());
                        }
                        if (commentReturn.getTaskId() != null) {
                            maps.put("taskId", commentReturn.getTaskId());
                        }
                        if (getIds != null && getIds.get("processDefinitionId") != null) {
                            maps.put("processDefinitionId", getIds.get("processDefinitionId"));
                        }
                    }
                    person.setMaps(maps);
                }
                personSubDTOS.add(subDTO);
            }
        }
        personSubDTOS.forEach(personSub -> {
            if (ObjectUtil.isEmpty(personSub.getPerson().getMaps().get("taskId"))) {
                personSub.getPerson().getMaps().put("taskId", personSub.getPerson().getTaskId());
            }
        });
        return new ResponseBase(200, "查询成功!", personSubDTOS);
    }

    public ResponseBase getContractByProcessId(Integer projectId, String businessKey, Integer taskType) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        Map<String, Object> maps = Maps.newHashMap();
        switch (taskType) {
            case 1:
                //查询待办
                ProcessReturn processReturn = getListRuntimeTask(businessKey, token);
                if (processReturn == null) {
                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
                }
                Map<String, Object> process1 = Maps.newHashMap();
                String processId1 = processReturn.getProcessInstanceId();
                Integer count1 = personDAO.getCountByProcessId(processId1);
                if (count1 <= 0) {
                    return new ResponseBase(601, "暂未查到数据");
                }
                PersonDTO personDTO1 = personDAO.getByProcessId(processId1);
                Integer userid1 = personDTO1.getRecordId();
                Integer projectid1 = personDTO1.getProjectId();
                String name1 = usersDAO.getNameByUserId(userid1);
                String projectName1 = projectsDAO.getNameById(projectid1);
                personDTO1.setRecodeName(name1);
                personDTO1.setProjectName(projectName1);
                List<PersonSub> personSub1 = personDAO.getPersonByGid(personDTO1.getId());

                Map<String, String> map1 = new WeakHashMap();
                if (personDTO1.getBusinessKey() != null && !personDTO1.getBusinessKey().equals("")){
                    map1 = getFlowAndTaskInfo(personDTO1.getBusinessKey(), token);
                    if (map1.get("processDefinitionId") != null) {
                        process1.put("processDefinitionId", map1.get("processDefinitionId"));
                        String[] keys = map1.get("processDefinitionId").split(":");
                        process1.put("flowKey", keys[0]);
                    }
                    if (map1.get("processInstanceId") != null) {
                        process1.put("processInstanceId", map1.get("processInstanceId"));
                    }
                    if (map1.get("taskId") != null) {
                        process1.put("taskId", map1.get("taskId"));
                    }
                    personDTO1.setMaps(process1);
                } else {
                    TaskCommentReturn commentReturn1 = taskCommentReturn(processId1, token);
                    if (commentReturn1 != null) {
                        map1 = personDAO.getRuntimeIdsByProcessInstanceId(processId1);
                        if (processId1 != null) {
                            process1.put("processInstanceId", processId1);
                        }
                        if (commentReturn1.getTaskId() != null) {
                            process1.put("taskId", commentReturn1.getTaskId());
                        }
                        if (map1.get("processDefinitionId") != null) {
                            process1.put("processDefinitionId", map1.get("processDefinitionId"));
                            String[] keys = map1.get("processDefinitionId").split(":");
                            process1.put("flowKey", keys[0]);
                        }
                        personDTO1.setMaps(process1);
                    }
                }
                maps.put("person", personDTO1);
                maps.put("personSub", personSub1);
                break;
//            case 2:
            default:
                //查询已办
                Map<String, Object> processMap = Maps.newHashMap();
//                ProcessListHistoryReturn listHistoricTask = listHistoricTask(businessKey, token);
//                if (listHistoricTask == null) {
//                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
//                }
//                String processInstanceId = listHistoricTask.getProcessInstanceId();
                String processInstanceId = getHistoryProcessInstanceId(businessKey);
                if (processInstanceId == null || processInstanceId.equals("")) {
                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
                }
                Integer count2 = personDAO.getCountByProcessId(processInstanceId);
                if (count2 <= 0) {
                    return new ResponseBase(601, "暂未查到数据");
                }
                PersonDTO personDTO = personDAO.getByProcessId(processInstanceId);
                Integer userid = personDTO.getRecordId();
                Integer projectid = personDTO.getProjectId();
                String name = usersDAO.getNameByUserId(userid);
                String projectName = projectsDAO.getNameById(projectid);
                personDTO.setRecodeName(name);
                personDTO.setProjectName(projectName);
                List<PersonSub> personSub = personDAO.getPersonByGid(personDTO.getId());

                TaskCommentReturn commentReturn = taskCommentReturn(processInstanceId, token);
                if (commentReturn != null) {
                    Map<String, String> map = personDAO.getIdsByProcessInstanceId(processInstanceId);
                    if (processInstanceId != null) {
                        processMap.put("processInstanceId", processInstanceId);
                    }
                    if (commentReturn.getTaskId() != null) {
                        processMap.put("taskId", commentReturn.getTaskId());
                    }
                    if (map.get("processDefinitionId") != null) {
                        processMap.put("processDefinitionId", map.get("processDefinitionId"));
                        String[] keys = map.get("processDefinitionId").split(":");
                        processMap.put("flowKey", keys[0]);
                    }
                    personDTO.setMaps(processMap);
                }

                maps.put("person", personDTO);
                maps.put("personSub", personSub);
                break;
        }
        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getAllContract(Integer projectId, Integer roleType, Integer pageNum, Integer pageSize) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<PersonSubDTO> personSubDTOS = Lists.newArrayList();
        List<PersonDTO> persons = Lists.newArrayList();
        if (pageNum != null && !pageNum.equals("") && pageSize != null && !pageSize.equals("")) {
            Integer num = (pageNum - 1) * pageSize;
            if (roleType != null && !roleType.equals("")) {
                Integer roleId = null;
                if (roleType == 1) {
                    //施工
                    roleId = personDAO.getShiGongRoleId();
                } else if (roleType == 2) {
                    //监理
                    roleId = personDAO.getJianLiRoleId();
                } else if (roleType == 3) {
                    //全资
                    roleId = personDAO.getQuanZiRoleId();
                }
                persons = personDAO.getAllContractByRoleId(roleId,projectId);
            } else {
                persons = personDAO.getAllPersonByProjectId(projectId);
            }
        } else {
            if (roleType != null && !roleType.equals("")) {
                Integer roleId = null;
                if (roleType == 1) {
                    //施工
                    roleId = personDAO.getShiGongRoleId();
                } else if (roleType == 2) {
                    //监理
                    roleId = personDAO.getJianLiRoleId();
                } else {
                    //全资
                    roleId = personDAO.getQuanZiRoleId();
                }
                persons = personDAO.getAllContractByRoleId(roleId,projectId);
            } else {
                persons = personDAO.getAllPersonByProjectId(projectId);
            }
        }

        //调用接口获取数据
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        //通过processInstanceId获取该流程的三个id
        Map<String, Object> maps = new WeakHashMap();
        if (persons.size() > 0) {
            for (PersonDTO person : persons) {
                PersonSubDTO subDTO = new PersonSubDTO();
                Integer projectid = person.getProjectId();
                Integer userid = person.getRecordId();
                String projectName = projectsDAO.getNameById(projectid);
                String name = usersDAO.getNameByUserId(userid);
                person.setProjectName(projectName);
                person.setRecodeName(name);

                subDTO.setPerson(person);
                //查询该流程下报审的人员
                List<PersonSub> subs = personDAO.getPersonByGid(person.getId());
                subDTO.setPersonSubs(subs);

                Map<String, String> getIds = new WeakHashMap();
                if (person.getBusinessKey() != null && !person.getBusinessKey().equals("")){
                    getIds = getFlowAndTaskInfo(person.getBusinessKey(), token);
                    if (getIds.get("processDefinitionId") != null) {
                        maps.put("processDefinitionId", getIds.get("processDefinitionId"));
                    }
                    if (getIds.get("processInstanceId") != null) {
                        maps.put("processInstanceId", getIds.get("processInstanceId"));
                    }
                    if (getIds.get("taskId") != null) {
                        maps.put("taskId", getIds.get("taskId"));
                    }
                } else {
                    //这里的taskId为processInstanceId
                    TaskCommentReturn commentReturn = taskCommentReturn(person.getTaskId(), token);
                    if (commentReturn == null){
                        continue;
                    }
                    if (commentReturn != null) {
                        getIds = personDAO.getIdsByProcessInstanceId(person.getTaskId());
                        if (person.getTaskId() != null) {
                            maps.put("processInstanceId", person.getTaskId());
                        }
                        if (commentReturn.getTaskId() != null) {
                            maps.put("taskId", commentReturn.getTaskId());
                        }
                        if (getIds != null && getIds.get("processDefinitionId") != null) {
                            maps.put("processDefinitionId", getIds.get("processDefinitionId"));
                        }
                    }
                }
                person.setMaps(maps);
                personSubDTOS.add(subDTO);
            }
        }

        return new ResponseBase(200, "获取所有报审信息成功!", personSubDTOS);
    }

    public ResponseBase getVerifyContact(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();
        List<PersonDTO> persons = personDAO.getAllVerifyPersonByUserid(userId);
        if (persons.size() == 0) {
            return new ResponseBase(200, "暂未查到数据!", persons);
        }
        for (PersonDTO person : persons) {
            Integer userid = person.getRecordId();
            Integer handle = person.getHandle();
            Integer projectid = person.getProjectId();
            String name = usersDAO.getNameByUserId(userid);
            String handleName = usersDAO.getNameByUserId(handle);
            String projectName = projectsDAO.getNameById(projectid);
            person.setRecodeName(name);
            person.setProjectName(projectName);
            person.setHandleName(handleName);
        }
        return new ResponseBase(200, "查询成功!", persons);
    }

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    public ResponseBase subContract(PersonSubDTO subDTO) {
        if (subDTO.getPerson().getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目(标段)id!");
        }
        if (subDTO.getPerson().getRecordId() <= 0) {
            return new ResponseBase(200, "请输入有效的记录人id!");
        }
        //调用流程接口发起流程
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        long userid = LoginHelper.getUserId();
        //流程定义的key
        String processKey = subDTO.getProcessKey();
        String businessKey = UUID.randomUUID().toString();

        HttpParamers param = HttpParamers.httpPostParamers();
        param.addParam("processKey", processKey);
        param.addParam("businessKey", businessKey);
        param.addParam("userId", userid);


        Map<String, Object> maps = Maps.newHashMap();
        if (subDTO.getVariables() != null && !subDTO.getVariables().equals("")) {
            maps.put("detectionUser", subDTO.getVariables());
        }
        param.addParam("variables", maps);
        HttpHeader header = new HttpHeader();
        header.addParam("token", token);
        //调用接口
        try {
            Integer gid = null;
            if(ObjectUtil.isNull(subDTO.getPerson().getId())) {

                ProcessInstance processInstance = flowStaticPageService.startProcess(processKey, businessKey, userid, maps);

                //添加填报的基础信息
                subDTO.getPerson().setOrder(1);
                subDTO.getPerson().setTaskId(processInstance.getId());
                subDTO.getPerson().setBusinessKey(businessKey);
                subDTO.getPerson().setCreateUserId(userid);
                subDTO.getPerson().setStatus(0);
                subDTO.getPerson().setCreateUserId(userid);
                subDTO.getPerson().setCreateTime(new Date());
                personDAO.newContract(subDTO.getPerson());
                gid = personDAO.getInsertId();
            } else {
                subDTO.getPerson().setUpdateUserId(userid);
                subDTO.getPerson().setUpdateTime(new Date());
                personDAO.updateContract(subDTO.getPerson());
                gid = subDTO.getPerson().getId();
            }

            personDAO.delPersonSubByGid(gid);
            for (PersonSub personSub : subDTO.getPersonSubs()) {
                personSub.setId(null);
                personSub.setGid(gid);
                personDAO.addPersonSub(personSub);
            }

            return new ResponseBase(200, "提交成功!", subDTO.getPerson().getTaskId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase(200, "提交失败!", "");
    }

    public ResponseBase getContractPerson(Integer id) {
        if (id.equals("") || id == null) {
            return new ResponseBase(200, "请输入有效的合同id！");
        }
        List<PersonSub> personSubs = personDAO.getAllPersonSubById(id);
        if (personSubs.size() == 0) {
            return new ResponseBase(200, "该合同暂未查到合同人员信息!");
        }
        return new ResponseBase(200, "查询成功!", personSubs);
    }

    public ResponseBase getAllUser(Integer projectid) {
        if (projectid <= 0) {
            return new ResponseBase(200, "请输入有效的项目标段id!");
        }
        Integer row1 = projectsDAO.getCountById(projectid);
        if (row1 <= 0) {
            return new ResponseBase(200, "无有效数据!");
        }
        //通过项目id查询组织
        SsFProjects project = projectsDAO.getProjectById(projectid);
        String groupIds = project.getGroupid();
        List<Integer> groupids = Arrays.stream(groupIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //通过token查询该用户的组织id,判断该用户是否在项目里面
        Integer userid = LoginHelper.getUserId().intValue();
        Integer groupid = userGroupDAO.getGroupIdByUserId(userid);
        if (!groupids.contains(groupid)) {
            return new ResponseBase(200, "用户权限不在该项目标段中!");
        }
        List<SsFUsers> users = usersDAO.getUserByGroupId(groupid);
        return new ResponseBase(200, "查询成功!", users);
    }

    public ResponseBase subPersonChange(ZjPersonChange personChange) {
        if (personChange.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        if (personChange.getProjectChildId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目标段id!");
        }
        //调用流程接口发起流程
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        long userId = LoginHelper.getUserId();
        //流程定义的key
        String processKey = personChange.getProcessDefinitionKey();
        String businessKey = UUID.randomUUID().toString();

        HttpParamers param = HttpParamers.httpPostParamers();
        param.addParam("processKey", processKey);
        param.addParam("businessKey", businessKey);
        param.addParam("userId", userId);

        Map<String, Object> maps = Maps.newHashMap();
        if (personChange.getVariables() != null && !personChange.getVariables().equals("")) {
            maps.put("detectionUser", personChange.getVariables());
        }
        param.addParam("variables", maps);
        HttpHeader header = new HttpHeader();
        header.addParam("token", token);
        try {
            ProcessInstance processInstance = flowStaticPageService.startProcess(processKey, businessKey, userId, maps);
            //调用接口
//            String response = ZhuJiAPIConfig.createProcess(businessKey, param, header);
            //接口返回的数据
//            PersonReturnModel returnModel = JSONObject.parseObject(response, PersonReturnModel.class);
//            log.info("returnModel: " + returnModel);
//            if (returnModel.isSuccess()) {
                personChange.setStatus(0);
                personChange.setOrder(1);
                personChange.setProcessInstanceId(processInstance.getProcessInstanceId());
                personChange.setBusinessKey(businessKey);

                personChangeDAO.newPersonChange(personChange);
                Integer id = personChangeDAO.getLastInsertId();
                if (personChange.getFiles() != null && personChange.getFiles().size() > 0) {
                    for (ZjPersonChangeFile file : personChange.getChangeFiles()) {
                        file.setGid(id);
                        personChangeDAO.addPersonFile(file);
                    }
                }
                return new ResponseBase(200, "提交人员变更成功!", processInstance.getProcessInstanceId());
//            }
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        return new ResponseBase(200, "提交人员变更失败!", null);
    }

    public ResponseBase getPersonChange(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        Set<String> roleKey = LoginHelper.getLoginUser().getRolePermission();
        //add by yangaogao 20230731  管理员可以查看所有数据
        if (roleKey.contains("gly") || roleKey.contains("admin")){
            userId = null;
        }
        List<ZjPersonChange> personChanges = personChangeDAO.getChangeByUserId(userId,projectId);
        if (personChanges.size() > 0) {
            for (ZjPersonChange personChange : personChanges) {
                Map<String, String> maps = new WeakHashMap();
                if (personChange.getBusinessKey() != null && !personChange.getBusinessKey().equals("")){
                    maps = getFlowAndTaskInfo(personChange.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personChange.setProcessDefinitionId(maps.get("processDefinitionId"));
                    }
                    if (maps.get("processInstanceId") != null) {
                        personChange.setProcessInstanceId(maps.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personChange.setTaskId(maps.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn = taskCommentReturn(personChange.getProcessInstanceId(), token);
                    if (commentReturn == null) {
                        continue;
                    }
                    maps = personDAO.getIdsByProcessInstanceId(personChange.getProcessInstanceId());
                    //放入三个id
                    if (personChange.getProcessInstanceId() != null) {
                        personChange.setProcessInstanceId(personChange.getProcessInstanceId());
                    }
                    if (commentReturn.getTaskId() != null) {
                        personChange.setTaskId(commentReturn.getTaskId());
                    }
                    if (maps.get("processDefinitionId") != null) {
                        personChange.setProcessDefinitionId(maps.get("processDefinitionId"));
                    }
                }
            }
            for (ZjPersonChange personChange : personChanges) {
                List<ZjPersonChangeFile> files = personChangeDAO.getFileIdsByGid(personChange.getId());
                personChange.setChangeFiles(files);
            }
        }

        return new ResponseBase(200, "查询成功!", personChanges);
    }

    public ResponseBase getChangeByProcessId(Integer projectId, String businessKey, Integer taskType) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        Map<String, Object> maps = Maps.newHashMap();
        switch (taskType) {
            case 1:
                //查询待办
//                ProcessReturn processReturn = getListRuntimeTask(businessKey, token);
//                if (processReturn == null) {
//                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
//                }
//                String processId1 = processReturn.getProcessInstanceId();
                String processId1 = getRuntimeProcessInstanceId(businessKey);
                if (processId1 == null || processId1.equals("")){
                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
                }
                Integer count1 = personChangeDAO.getCountByProcessId(processId1);
                if (count1 <= 0) {
                    return new ResponseBase(200, "暂未查到数据");
                }
                ZjPersonChange personDTO1 = personChangeDAO.getChangeByProcessId(processId1);
                List<ZjPersonChangeFile> personSub1 = personChangeDAO.getFileIdsByGid(personDTO1.getId());

                Map<String, String> map1 = new WeakHashMap();
                if (personDTO1.getBusinessKey() != null && !personDTO1.getBusinessKey().equals("")){
                    map1 = getFlowAndTaskInfo(personDTO1.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personDTO1.setProcessDefinitionId(map1.get("processDefinitionId"));
                    }
                    if (maps.get("processInstanceId") != null) {
                        personDTO1.setProcessInstanceId(map1.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personDTO1.setTaskId(map1.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn1 = taskCommentReturn(processId1, token);
                    map1 = personDAO.getRuntimeIdsByProcessInstanceId(processId1);
                    if (commentReturn1.getProcessInstanceId() != null) {
                        personDTO1.setProcessInstanceId(commentReturn1.getProcessInstanceId());
                    }
                    if (map1.get("processDefinitionId") != null) {
                        personDTO1.setProcessDefinitionId(map1.get("processDefinitionId"));
                    }
                    if (commentReturn1.getTaskId() != null) {
                        personDTO1.setTaskId(commentReturn1.getTaskId());
                    }
                }
                maps.put("ZjPersonChange", personDTO1);
                maps.put("ZjPersonChangeFile", personSub1);
                break;
//            case 2:
            default:
                //查询已办
//                ProcessListHistoryReturn listHistoricTask = listHistoricTask(businessKey, token);
//                if (listHistoricTask == null) {
//                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
//                }
//                String processInstanceId = listHistoricTask.getProcessInstanceId();
                String processInstanceId = getHistoryProcessInstanceId(businessKey);
                if (processInstanceId == null || processInstanceId.equals("")){
                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
                }
                Integer count2 = personChangeDAO.getCountByProcessId(processInstanceId);
                if (count2 <= 0) {
                    return new ResponseBase(200, "暂未查到数据");
                }
                ZjPersonChange personDTO2 = personChangeDAO.getChangeByProcessId(processInstanceId);
                List<ZjPersonChangeFile> personSub2 = personChangeDAO.getFileIdsByGid(personDTO2.getId());

                Map<String, String> map2 = new WeakHashMap();
                if (personDTO2.getBusinessKey() != null && !personDTO2.getBusinessKey().equals("")){
                    map2 = getFlowAndTaskInfo(personDTO2.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personDTO2.setProcessDefinitionId(map2.get("processDefinitionId"));
                    }
                    if (maps.get("processInstanceId") != null) {
                        personDTO2.setProcessInstanceId(map2.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personDTO2.setTaskId(map2.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn2 = taskCommentReturn(processInstanceId, token);
                    map2 = personDAO.getIdsByProcessInstanceId(processInstanceId);
                    if (commentReturn2.getProcessInstanceId() != null) {
                        personDTO2.setProcessInstanceId(commentReturn2.getProcessInstanceId());
                    }
                    if (map2.get("processDefinitionId") != null) {
                        personDTO2.setProcessDefinitionId(map2.get("processDefinitionId"));
                    }
                    if (commentReturn2.getTaskId() != null) {
                        personDTO2.setTaskId(commentReturn2.getTaskId());
                    }
                }
                maps.put("ZjPersonChange", personDTO2);
                maps.put("ZjPersonChangeFile", personSub2);
                break;
//            case 3:
//                //查询历史
//                ListHistoryProcessInstance processInstance = listHistoryProcessInstance(businessKey, token);
//                if (processInstance == null) {
//                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
//                }
//                String processId = processInstance.getProcessInstanceId();
//                Integer count3 = personChangeDAO.getCountByProcessId(processId);
//                if (count3 <= 0) {
//                    return new ResponseBase(200, "暂未查到数据");
//                }
//                ZjPersonChange personDTO3 = personChangeDAO.getChangeByProcessId(processId);
//                List<PersonSub> personSub3 = personDAO.getPersonByGid(personDTO3.getId());
//
//                TaskCommentReturn commentReturn3 = taskCommentReturn(processId, token);
//                Map<String, String> map3 = personDAO.getIdsByProcessInstanceId(processId);
//                if (commentReturn3.getProcessInstanceId() != null) {
//                    personDTO3.setProcessInstanceId(commentReturn3.getProcessInstanceId());
//                }
//                if (map3.get("processDefinitionId") != null) {
//                    personDTO3.setProcessDefinitionId(map3.get("processDefinitionId"));
//                }
//                if (commentReturn3.getTaskId() != null) {
//                    personDTO3.setTaskId(commentReturn3.getTaskId());
//                }
//
//                maps.put("ZjPersonChange", personDTO3);
//                maps.put("ZjPersonChangeFile", personSub3);
//                break;
//            case 4:
//                //抄送列表
//                ListCopyMessage copyMessage = listCopyMessage(businessKey, token);
//                if (copyMessage == null) {
//                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
//                }
//                String processId4 = copyMessage.getProcessInstanceId();
//                Integer count4 = personChangeDAO.getCountByProcessId(processId4);
//                if (count4 <= 0) {
//                    return new ResponseBase(200, "暂未查到数据");
//                }
//                ZjPersonChange personDTO4 = personChangeDAO.getChangeByProcessId(processId4);
//                List<PersonSub> personSub4 = personDAO.getPersonByGid(personDTO4.getId());
//
//                TaskCommentReturn commentReturn4 = taskCommentReturn(processId4, token);
//                Map<String, String> map4 = personDAO.getIdsByProcessInstanceId(processId4);
//                if (commentReturn4.getProcessInstanceId() != null) {
//                    personDTO4.setProcessInstanceId(commentReturn4.getProcessInstanceId());
//                }
//                if (map4 != null && map4.get("processDefinitionId") != null) {
//                    personDTO4.setProcessDefinitionId(map4.get("processDefinitionId"));
//                }
//                if (commentReturn4.getTaskId() != null) {
//                    personDTO4.setTaskId(commentReturn4.getTaskId());
//                }
//
//                maps.put("ZjPersonChange", personDTO4);
//                maps.put("ZjPersonChangeFile", personSub4);
//                break;
        }
        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getAllChange(Integer projectId, Integer roleType, Integer pageNum, Integer pageSize) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<ZjPersonChange> personChanges = Lists.newArrayList();
        String token = getRequest().getHeader("Authorization"); // 从 http 请求头中取出 token
        if (pageNum != null && !pageNum.equals("") && pageSize != null && !pageSize.equals("")) {
            Integer num = (pageNum - 1) * pageSize;
            if (roleType != null && !roleType.equals("")) {
                Integer roleId = null;
                if (roleType == 1) {
                    //施工
                    roleId = personDAO.getShiGongRoleId();
                } else if (roleType == 2) {
                    //监理
                    roleId = personDAO.getJianLiRoleId();
                } else {
                    //全资
                    roleId = personDAO.getQuanZiRoleId();
                }
                personChanges = personChangeDAO.getAllChangeByRoleIdLimit(roleId, num, pageSize);
            } else {
                personChanges = personChangeDAO.getAllChangeByProjectIdLimit(projectId, num, pageSize);
            }
        } else {
            if (roleType != null && !roleType.equals("")) {
                Integer roleId = null;
                if (roleType == 1) {
                    //施工
                    roleId = personDAO.getShiGongRoleId();
                } else if (roleType == 2) {
                    //监理
                    roleId = personDAO.getJianLiRoleId();
                } else {
                    //全资
                    roleId = personDAO.getQuanZiRoleId();
                }
                personChanges = personChangeDAO.getAllChangeByRoleId(roleId);
            } else {
                personChanges = personChangeDAO.getAllChangeByProjectId(projectId);
            }
        }
        if (personChanges.size() > 0) {
            for (ZjPersonChange personChange : personChanges) {
                Map<String, String> maps = new WeakHashMap();
                if (personChange.getBusinessKey() != null && !personChange.getBusinessKey().equals("")){
                    maps = getFlowAndTaskInfo(personChange.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personChange.setProcessDefinitionId(maps.get("processDefinitionId"));
                        String[] keys = personChange.getProcessDefinitionId().split(":");
                        personChange.setFlowKey(keys[0]);
                    }
                    if (maps.get("processInstanceId") != null) {
                        personChange.setProcessInstanceId(maps.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personChange.setTaskId(maps.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn = taskCommentReturn(personChange.getProcessInstanceId(), token);
                    if (commentReturn == null) {
                        continue;
                    }
                    maps = personDAO.getIdsByProcessInstanceId(personChange.getProcessInstanceId());
                    //放入三个id
                    if (personChange.getProcessInstanceId() != null) {
                        personChange.setProcessInstanceId(personChange.getProcessInstanceId());
                    }
                    if (commentReturn.getTaskId() != null) {
                        personChange.setTaskId(commentReturn.getTaskId());
                    }
                    if (maps != null){
                        if (maps.get("processDefinitionId") != null) {
                            personChange.setProcessDefinitionId(maps.get("processDefinitionId"));
                            String[] keys = personChange.getProcessDefinitionId().split(":");
                            personChange.setFlowKey(keys[0]);
                        }
                    }
                }


            }

            for (ZjPersonChange personChange : personChanges) {
                List<ZjPersonChangeFile> files = personChangeDAO.getFileIdsByGid(personChange.getId());
                personChange.setChangeFiles(files);
            }
        }
        Integer allCount = personChangeDAO.getAllCount();
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("count", allCount);
        maps.put("list", personChanges);

//        return new ResponseBase(200, "查询成功!", maps);
        return new ResponseBase(200, "查询成功!", personChanges);
    }

    public ZjPersonChange selectByBusinessKey(String businessId) {
        return personChangeDAO.selectByBusinessKey(businessId);
    }

    public void updatePersonChangeStatus(Integer status, Integer id) {
        personChangeDAO.updatePersonChangeStatus(status, id);
    }


    public ResponseBase delChange(Integer id, Integer projectId) {
        try {
            if (projectId <= 0) {
                return new ResponseBase(200, "请输入有效的项目id!");
            }
            Integer count1 = projectsDAO.getCountById(projectId);
            if (count1 <= 0) {
                return new ResponseBase(200, "该项目id无数据!");
            }
//            String token = getRequest().getHeader("Authorization");
            ZjPersonChange personChange = personChangeDAO.selectByPrimaryKey(id);
            if (personChange == null) {
                return new ResponseBase(200, "没有找到有效的人员变更数据!");
            }
            ////获取变更前人员id
//            Integer beforePersonId = personChange.getBeforePersonId();
            //获取变更后人员id
//            Integer afterPersonId = personChange.getAfterPersonId();
            //获取变更前人员对应岗位(角色)id
//            SsFUserRole beforePersonRole = personChangeDAO.getByUserid(beforePersonId);
//            Integer beforePersonRoleId = beforePersonRole.getRoleid();
            //获取变更后人员对应岗位（角色）id
//            SsFUserRole afterPersonRole = personChangeDAO.getByUserid(afterPersonId);
//            Integer afterPersonRoleId = afterPersonRole.getRoleid();
            //在调用停止之前获取到该条数据状态
//            Integer status = personChange.getStatus();
            String processInstanceId = personChange.getProcessInstanceId();
            //调用停止接口 admin/flow/flowOperation/stopProcessInstance
            flowApiService.deleteProcess(processInstanceId);
//            PersonReturnModel personReturnModel = stopProcessInstance(processInstanceId, token);
//            if (personReturnModel.isSuccess()) {
                //true 的话,说明流程删除成功!   再删除对应的业务数据
                // 因删除之前先要终止流程，而终止流程会强制完成该流程，导致触发变更完成，
                // 所以先把变更完成的还原
//                if (status == 1){
                    //说明该流程为强制完成的
                    //修改变更前人员id为老角色
//                    personChangeDAO.updateUserRole(beforePersonId, afterPersonId, beforePersonRoleId);
                    //增加变更后人员id
//                    personChangeDAO.insertUserRole(afterPersonId, afterPersonRoleId);
//                }
                //如果之前状态不是1，说明该流程为已审批
                personChangeDAO.delChange(id, projectId);
            List<ZjPersonChangeFile> personChangeFiles = personChangeDAO.getFileIdsByGid(id);
            for (ZjPersonChangeFile changeFile : personChangeFiles) {
//                ZjPersonChangeFile changeFile = personChangeDAO.getFileByGid(id);
                if (changeFile != null) {
                    String fileId = changeFile.getFileId();
                    fileMapper.delete(fileId);
                    personChangeDAO.delPersonFile(id);
                }
            }

                return new ResponseBase(200, "删除人员变更成功!");
//            }
//            return new ResponseBase(200, "删除人员变更失败!", personReturnModel.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "删除人员变更失败!", e.getStackTrace());
        }
    }

    public ResponseBase subLeave(ZjPersonLeave personLeave) {
        try {
            if (personLeave.getProjectId() <= 0) {
                return new ResponseBase(200, "请输入有效的项目id!");
            }
            if (personLeave.getProcessDefinitionKey() == null) {
                return new ResponseBase(200, "请输入有效的流程模板名称!");
            }

            //调用发起流程接口
            //流程定义的key
            String processKey = personLeave.getProcessDefinitionKey();
            String businessKey = UUID.randomUUID().toString();
            String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
            long userId = LoginHelper.getUserId();

            HttpHeader header = new HttpHeader();
            header.addParam("token", token);

            Map<String, Object> maps = Maps.newHashMap();
            HttpParamers param = HttpParamers.httpPostParamers();
            param.addParam("userId", userId);
            param.addParam("processKey", processKey);
            param.addParam("businessKey", businessKey);
            if (personLeave.getVariables() != null && !personLeave.getVariables().equals("")) {
                maps.put("detectionUser", personLeave.getVariables());
            }
            param.addParam("variables", maps);
            //调用接口
            ProcessInstance processInstance = flowStaticPageService.startProcess(processKey, businessKey, userId, maps);

//            String response = ZhuJiAPIConfig.createProcess(businessKey, param, header);
            //接口返回的数据
//            PersonReturnModel returnModel = JSONObject.parseObject(response, PersonReturnModel.class);
//            if (returnModel.isSuccess()) {
                personLeave.setSubDate(new Date());
                personLeave.setStatus(0);
                personLeave.setOrder(1);
                personLeave.setProcessInstanceId(processInstance.getProcessInstanceId());
                personLeave.setBusinessKey(businessKey);
                //往请假表插入数据
                personLeaveDAO.newPersonLeave(personLeave);

                //再调用startAndTakeUserTask接口发送每个流程节点审批人
                return new ResponseBase(200, "提交请假流程成功!", processInstance.getProcessInstanceId());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "提交流程失败! " + e.getMessage());
        }
    }

    public ResponseBase getLeaveData(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer leavePersonId = LoginHelper.getUserId().intValue();
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        Set<String> roleKey = LoginHelper.getLoginUser().getRolePermission();

        if (roleKey.contains("gly") || roleKey.contains("admin")){
            leavePersonId = null;
        }
        List<ZjPersonLeave> personLeaves = personLeaveDAO.getByUserId(leavePersonId);
        if (personLeaves.size() > 0) {
            for (ZjPersonLeave personLeave : personLeaves) {
                Map<String, String> maps = Maps.newHashMap();
                //先看businessKey是不是空的
                if (personLeave.getBusinessKey() != null && !personLeave.getBusinessKey().equals("")){
                    maps = getFlowAndTaskInfo(personLeave.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personLeave.setProcessDefinitionId(maps.get("processDefinitionId"));
                    }
                    if (maps.get("processInstanceId") != null) {
                        personLeave.setProcessInstanceId(maps.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personLeave.setTaskId(maps.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn = taskCommentReturn(personLeave.getProcessInstanceId(), token);
                    if (commentReturn == null) {
                        continue;
                    }
                    maps = personDAO.getIdsByProcessInstanceId(personLeave.getProcessInstanceId());
                    //放入三个id
                    if (personLeave.getProcessInstanceId() != null) {
                        personLeave.setProcessInstanceId(personLeave.getProcessInstanceId());
                    }
                    if (maps.get("processDefinitionId") != null) {
                        personLeave.setProcessDefinitionId(maps.get("processDefinitionId"));
                    }
                    if (commentReturn.getTaskId() != null) {
                        personLeave.setTaskId(commentReturn.getTaskId());
                    }
                }
            }
        }
        return new ResponseBase(200, "查询成功!", personLeaves);
    }

    public ResponseBase getLeaveByProcessId(Integer projectId, String businessKey, Integer taskType) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        Map<String, Object> maps = Maps.newHashMap();
        switch (taskType) {
            case 1:
                //查询待办
                String processId1 = getRuntimeProcessInstanceId(businessKey);
                if (processId1 == null || processId1.equals("")){
                    return new ResponseBase(200, "该businessKey暂未查到流程数据");
                }
                Integer count1 = personLeaveDAO.getCountByProcessId(processId1);
                if (count1 <= 0) {
                    return new ResponseBase(200, "暂未查到数据");
                }
                ZjPersonLeave personDTO1 = personLeaveDAO.getLeaveByProcessId(processId1);
                Map<String, String> map1 = Maps.newHashMap();
                //先看
                if (personDTO1.getBusinessKey() != null && !personDTO1.getBusinessKey().equals("")){
                    map1 = getFlowAndTaskInfo(personDTO1.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personDTO1.setProcessDefinitionId(map1.get("processDefinitionId"));
                        String[] keys = personDTO1.getProcessDefinitionId().split(":");
                        personDTO1.setFlowKey(keys[0]);
                    }
                    if (maps.get("processInstanceId") != null) {
                        personDTO1.setProcessInstanceId(map1.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personDTO1.setTaskId(map1.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn1 = taskCommentReturn(processId1, token);
                    map1 = personDAO.getRuntimeIdsByProcessInstanceId(processId1);
                    if (commentReturn1.getProcessInstanceId() != null) {
                        personDTO1.setProcessInstanceId(commentReturn1.getProcessInstanceId());
                    }
                    if (map1.get("processDefinitionId") != null) {
                        personDTO1.setProcessDefinitionId(map1.get("processDefinitionId"));
                        String[] keys = personDTO1.getProcessDefinitionId().split(":");
                        personDTO1.setFlowKey(keys[0]);
                    }
                    if (commentReturn1.getTaskId() != null) {
                        personDTO1.setTaskId(commentReturn1.getTaskId());
                    }
                }
                maps.put("ZjPersonLeave", personDTO1);
                break;
            default:
                //查询已办
                String processInstanceId = getHistoryProcessInstanceId(businessKey);
                Integer count2 = personLeaveDAO.getCountByProcessId(processInstanceId);
                if (count2 <= 0) {
                    return new ResponseBase(200, "暂未查到数据");
                }
                ZjPersonLeave personDTO2 = personLeaveDAO.getLeaveByProcessId(processInstanceId);
                Map<String, String> map2 = Maps.newHashMap();
                if (personDTO2.getBusinessKey() != null && !personDTO2.getBusinessKey().equals("")){
                    map2 = getFlowAndTaskInfo(personDTO2.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personDTO2.setProcessDefinitionId(map2.get("processDefinitionId"));
                        String[] keys = personDTO2.getProcessDefinitionId().split(":");
                        personDTO2.setFlowKey(keys[0]);
                    }
                    if (maps.get("processInstanceId") != null) {
                        personDTO2.setProcessInstanceId(map2.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personDTO2.setTaskId(map2.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn2 = taskCommentReturn(processInstanceId, token);
                    map2 = personDAO.getIdsByProcessInstanceId(processInstanceId);
                    if (commentReturn2.getProcessInstanceId() != null) {
                        personDTO2.setProcessInstanceId(commentReturn2.getProcessInstanceId());
                    }
                    if (map2.get("processDefinitionId") != null) {
                        personDTO2.setProcessDefinitionId(map2.get("processDefinitionId"));
                        String[] keys = personDTO2.getProcessDefinitionId().split(":");
                        personDTO2.setFlowKey(keys[0]);
                    }
                    if (commentReturn2.getTaskId() != null) {
                        personDTO2.setTaskId(commentReturn2.getTaskId());
                    }
                }
                maps.put("ZjPersonLeave", personDTO2);
                break;
        }
        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getAllLeave(Integer projectId, Integer roleType, Integer pageNum, Integer pageSize, Integer state) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");
        List<ZjPersonLeave> personLeaves = Lists.newArrayList();
        Integer roleId = null;
        if (pageNum != null && !pageNum.equals("") && pageSize != null && !pageSize.equals("")) {
            Integer num = (pageNum - 1) * pageSize;
            if (roleType != null && !roleType.equals("")) {
                roleId = getRoleIdByRoleType(roleType);
                if (state != null && !state.equals("")){
                    personLeaves = personLeaveDAO.getAllLeavesByRoleIdLimitAndState(roleId, num, pageSize, state);
                } else {
                    personLeaves = personLeaveDAO.getAllLeavesByRoleIdLimit(roleId, num, pageSize);
                }
            } else {
                if (state != null && !state.equals("")){
                    personLeaves = personLeaveDAO.getAllLeavesByLimitAndState(num, pageSize, state);
                } else {
                    personLeaves = personLeaveDAO.getAllByProjectIdLimit(num, pageSize);
                }
            }
        } else {
            if (roleType != null && !roleType.equals("")) {
                roleId = getRoleIdByRoleType(roleType);
                if (state != null && !state.equals("")){
                    personLeaves = personLeaveDAO.getAllLeavesByRoleIdAndState(roleId, state);
                } else {
                    personLeaves = personLeaveDAO.getAllLeavesByRoleId(roleId);
                }
            } else {
                if (state != null && !state.equals("")){
                    personLeaves = personLeaveDAO.getAllLeavesByState(state);
                } else {
                    personLeaves = personLeaveDAO.getAllByProjectId();
                }

            }
        }
        if (personLeaves.size() > 0) {
            for (ZjPersonLeave personLeave : personLeaves) {
                Map<String, String> maps = new WeakHashMap();
                if (personLeave.getBusinessKey() != null && !personLeave.getBusinessKey().equals("")){
                    maps = getFlowAndTaskInfo(personLeave.getBusinessKey(), token);
                    if (maps.get("processDefinitionId") != null) {
                        personLeave.setProcessDefinitionId(maps.get("processDefinitionId"));
                        String[] keys = personLeave.getProcessDefinitionId().split(":");
                        personLeave.setFlowKey(keys[0]);
                    }
                    if (maps.get("processInstanceId") != null) {
                        personLeave.setProcessInstanceId(maps.get("processInstanceId"));
                    }
                    if (maps.get("taskId") != null) {
                        personLeave.setTaskId(maps.get("taskId"));
                    }
                } else {
                    TaskCommentReturn commentReturn = taskCommentReturn(personLeave.getProcessInstanceId(), token);
                    if (commentReturn == null) {
                        continue;
                    }
                    maps = personDAO.getIdsByProcessInstanceId(personLeave.getProcessInstanceId());
                    if (maps == null) {
                        maps = personDAO.getRuntimeIdsByProcessInstanceId(personLeave.getProcessInstanceId());
                    }
                    if (maps != null){
                        //放入三个id
                        if (personLeave.getProcessInstanceId() != null) {
                            personLeave.setProcessInstanceId(personLeave.getProcessInstanceId());
                        }
                        if (maps.get("processDefinitionId") != null) {
                            personLeave.setProcessDefinitionId(maps.get("processDefinitionId"));
                            String[] keys = personLeave.getProcessDefinitionId().split(":");
                            personLeave.setFlowKey(keys[0]);
                        }
                        if (commentReturn.getTaskId() != null) {
                            personLeave.setTaskId(commentReturn.getTaskId());
                        }
                    }
                }
            }
        }
        Integer allCount = personLeaveDAO.getAllCount();
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("count", allCount);
        maps.put("list", personLeaves);
//        return new ResponseBase(200, "查询所有请假成功!", maps);
        return new ResponseBase(200, "查询所有请假成功!", personLeaves);
    }

    public ResponseBase delLeave(Integer id, Integer projectId) {
        try {
            if (projectId <= 0) {
                return new ResponseBase(200, "请输入有效的项目id!");
            }
            Integer count1 = projectsDAO.getCountById(projectId);
            if (count1 <= 0) {
                return new ResponseBase(200, "该项目id无数据!");
            }
            String token = getRequest().getHeader("Authorization");
            ZjPersonLeave personLeave = personLeaveDAO.selectByPrimaryKey(id);
            if (personLeave == null) {
                return new ResponseBase(200, "没有找到有效的人员请假数据!");
            }
            String processInstanceId = personLeave.getProcessInstanceId();
            //调用停止接口 admin/flow/flowOperation/stopProcessInstance
            PersonReturnModel personReturnModel = stopProcessInstance(processInstanceId, token);
            if (personReturnModel.isSuccess()) {
                //true 的话,说明流程删除成功!   再删除对应的业务数据
                personLeaveDAO.delLeave(id);
                return new ResponseBase(200, "删除人员请假成功!");
            }
            return new ResponseBase(200, "删除人员请假失败!", personReturnModel.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "删除人员请假失败!", e.getStackTrace());
        }
    }

    public ResponseBase clockIn(ZjPersonClockin clockIn) {
        if (clockIn.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(clockIn.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        if (clockIn.getAttendancePersonId() == null) {
            Integer id = LoginHelper.getUserId().intValue();
            clockIn.setAttendancePersonId(id);
        }
        //先查询打卡记录表今天有没有该用户的打卡记录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = sdf.format(date);
        String startTime = today + " 00:00:00";
        String endTime = today + " 23:59:59";
        ZjPersonClockin clockin =
                clockinDAO.getSelfTodayByProjectIdInTime(clockIn.getProjectId(),
                        clockIn.getAttendancePersonId(), startTime, endTime);

        clockIn.setAttendancePersonId(clockIn.getAttendancePersonId());
        String name = usersDAO.getNameByUserId(clockIn.getAttendancePersonId());
        clockIn.setAttendancePersonName(name);
        clockIn.setState(1);
        clockIn.setOrder(1);

        if (clockin == null) {
            clockIn.setClockTime(new Date());
            clockinDAO.AddClockIn(clockIn);
        } else {
            // 首先清除之前的打卡图片
            String pic = clockin.getClockPic();
            fileMapper.delete(pic);

            clockIn.setId(clockin.getId());
            clockIn.setClockTime(new Date());
            clockinDAO.updateClockIn(clockIn);
        }
        return new ResponseBase(200, "打卡成功!");
    }

    public ResponseBase getClockIn(Integer projectId, String startTime, String endTime, Integer roleType) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer roleId = null;
        List<ZjPersonClockin> clockInList = Lists.newArrayList();
        if (startTime == null && endTime == null) {
            if (roleType != null && !roleType.equals("")) {
                roleId = getRoleIdByRoleType(roleType);
                clockInList = clockinDAO.getAllByProjectIdAndRoleId(projectId, roleId);
            } else {
                clockInList = clockinDAO.getAllByProjectId(projectId);
            }
        } else {
            //转换时间
            startTime = startTime + " 00:00:00";
            endTime = endTime + " 23:59:59";
            if (roleType != null && !roleType.equals("")) {
                roleId = getRoleIdByRoleType(roleType);
                clockInList = clockinDAO.getAllByProjectIdAndRoleIdInTime(projectId, startTime, endTime, roleId);
            } else {
                clockInList = clockinDAO.getAllByProjectIdInTime(projectId, startTime, endTime);
            }

        }
        for (ZjPersonClockin clockin : clockInList) {
            Integer id = clockin.getGid();
            if (id != null) {
                String fenceAddrName = fenceDAO.getAddrNameById(id);
                clockin.setFenceAddrName(fenceAddrName);
            }
        }
        return new ResponseBase(200, "查询成功", clockInList);
    }

    public ResponseBase getSelfClockIn(Integer projectId, String startTime, String endTime) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();

        List<ZjPersonClockin> clockInList = Lists.newArrayList();
        if (startTime == null && endTime == null) {
            clockInList = clockinDAO.getSelfAllByProjectId(projectId, userId);
        } else {
            //转换时间
            startTime = startTime + " 00:00:00";
            endTime = endTime + " 23:59:59";
            clockInList = clockinDAO.getSelfAllByProjectIdInTime(projectId, userId, startTime, endTime);
        }
        for (ZjPersonClockin clockin : clockInList) {
            Integer id = clockin.getGid();
            if (id != null) {
                String fenceAddrName = fenceDAO.getAddrNameById(id);
                clockin.setFenceAddrName(fenceAddrName);
            }
        }
        return new ResponseBase(200, "查询成功", clockInList);
    }

    public ResponseBase getNoClockIn(Integer projectId, String startTime, String endTime, Integer roleType){
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        return null;
    }

    public ResponseBase remindClockIn() {
        log.info("未打卡消息提醒接口被调用! ");
        List<SsFUserOnline> userOnline = userOnlineDAO.getAll();
        for (SsFUserOnline ssFUserOnline : userOnline) {
            Integer userId = ssFUserOnline.getUserid();
            //先查询请假表, 查看当天该人员是否在请假, 如果没有请假则发起消息通知
            ZjPersonLeave leave = personLeaveDAO.getFinishLeaveByUserId(userId);
            LocalDateTime leaveEndTime = leave.getEndTime();
            int result = LocalDateTime.now().compareTo(leaveEndTime);
            if (result == 1) {
                //说明当前时间大于请假结束时间, 说明请假时间已过
                //再查看在线表, 今天是否有记录, 如果没有则发送通知
                String cid = ssFUserOnline.getCid();
                Date beginTime = DateUtils.getDayBegin();
                Date endTime = DateUtils.getDayEnd();
                Integer row = clockinDAO.getCountByUserId(userId, beginTime, endTime);
                if (row < 1) {
                    //未打卡, 进行通知
                    if (cid != null && !cid.equals("")) {
                        RestApiUtils.sendMessageToCid(cid, "新消息通知！", "今日还未打卡，请尽快打卡！");
                    }
                }
            }
        }
        return new ResponseBase(200, "未打卡消息提醒发送成功!");
    }

    public ResponseBase getContractStandingBook(Integer projectId, Integer parentRoleId,String subName) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }

        List<PersonSubDTO> personSubDTOS = Lists.newArrayList();
        List<PersonDTO> personDTOs = Lists.newArrayList();
        if (parentRoleId != null && !parentRoleId.equals("")) {
            Integer roleId = getRoleIdByRoleType(parentRoleId);
            personDTOs = personDAO.getContractByRoleId(roleId,projectId);
        } else {
            personDTOs = personDAO.getAllFinishContracts(projectId);
        }
        // 从 http 请求头中取出 token
        String token = getRequest().getHeader("Authorization");
        //通过processInstanceId获取该流程的三个id
        Map<String, Object> maps = Maps.newHashMap();
        if (personDTOs.size() > 0) {
            for (PersonDTO person : personDTOs) {
                PersonSubDTO subDTO = new PersonSubDTO();
                Integer projectid = person.getProjectId();
                String projectName = projectsDAO.getNameById(projectid);
                person.setProjectName(projectName);
                Integer userid = person.getRecordId();
                String rname = usersDAO.getNameByUserId(userid);
                person.setRecodeName(rname);
                subDTO.setPerson(person);
                if(StringUtils.isEmpty(subName)){
                    List<PersonSub> subs = personDAO.getPersonByGid(person.getId());
                    subDTO.setPersonSubs(subs);
                }else{
                    List<PersonSub> subs = personDAO.getPersonByGidAndName(person.getId(),subName);
                    subDTO.setPersonSubs(subs);
                }



                //这里的taskId为processInstanceId
//                TaskCommentReturn commentReturn = taskCommentReturn(person.getTaskId(), token);
//                if (commentReturn != null) {
//                    Map<String, String> getIds = personDAO.getIdsByProcessInstanceId(person.getTaskId());
//                    maps.put("processInstanceId", person.getTaskId());
//                    maps.put("taskId", commentReturn.getTaskId());
//                    maps.put("processDefinitionId", getIds.get("processDefinitionId"));
//                }
//                person.setMaps(maps);
                personSubDTOS.add(subDTO);
            }
        }
        return new ResponseBase(200, "查询报审台账成功!", personSubDTOS);
    }

    public ResponseBase getChangeStandingBook(Integer projectId, Integer parentRoleId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");
        List<ZjPersonChange> personChanges = Lists.newArrayList();
        if (parentRoleId != null && !parentRoleId.equals("")) {
            Integer roleId = getRoleIdByRoleType(parentRoleId);
            personChanges = personChangeDAO.getChangeByRoleId(roleId);
        } else {
            personChanges = personChangeDAO.getAllFisishChanges();
        }
        if (personChanges.size() > 0) {
            for (ZjPersonChange personChange : personChanges) {
                TaskCommentReturn commentReturn = taskCommentReturn(personChange.getProcessInstanceId(), token);
                if (commentReturn == null) {
                    continue;
                }
                Map<String, String> maps = personDAO.getIdsByProcessInstanceId(personChange.getProcessInstanceId());
                //放入三个id
                if (maps == null) {
                    continue;
                }
                if (personChange.getProcessInstanceId() != null) {
                    personChange.setProcessInstanceId(personChange.getProcessInstanceId());
                }
                if (commentReturn.getTaskId() != null) {
                    personChange.setTaskId(commentReturn.getTaskId());
                }
                if (maps.get("processDefinitionId") != null) {
                    personChange.setProcessDefinitionId(maps.get("processDefinitionId"));
                }
            }

            for (ZjPersonChange personChange : personChanges) {
                List<ZjPersonChangeFile> files = personChangeDAO.getFileIdsByGid(personChange.getId());
                personChange.setChangeFiles(files);
            }
        }
        return new ResponseBase(200, "查询人员变更台账成功!", personChanges);
    }

    public ResponseBase getLeaveStandingBook(Integer projectId, Integer parentRoleId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        String token = getRequest().getHeader("Authorization");
        List<ZjPersonLeave> personLeaves = Lists.newArrayList();
        if (parentRoleId != null && !parentRoleId.equals("")) {
            Integer roleId = getRoleIdByRoleType(parentRoleId);
            personLeaves = personLeaveDAO.getLeavesByRoleId(roleId);
        } else {
            personLeaves = personLeaveDAO.getAllFinishLeaves();
        }
        if (personLeaves.size() > 0) {
            for (ZjPersonLeave personLeave : personLeaves) {
                TaskCommentReturn commentReturn = taskCommentReturn(personLeave.getProcessInstanceId(), token);
                if (commentReturn == null) {
                    continue;
                }
                Map<String, String> maps = personDAO.getIdsByProcessInstanceId(personLeave.getProcessInstanceId());
                if (maps == null) {
                    continue;
                }
                //放入三个id
                if (personLeave.getProcessInstanceId() != null) {
                    personLeave.setProcessInstanceId(personLeave.getProcessInstanceId());
                }
                if (commentReturn.getTaskId() != null) {
                    personLeave.setTaskId(commentReturn.getTaskId());
                }
                if (maps.get("processDefinitionId") != null) {
                    personLeave.setProcessDefinitionId(maps.get("processDefinitionId"));
                }

            }
        }
        return new ResponseBase(200, "查询请假台账成功!", personLeaves);
    }

    public ResponseBase getFenceByUser(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        // todo 获取还没写
        return new ResponseBase(200, "成功");
    }

    public ResponseBase getPerson(Integer projectId){
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Map<String, Object> maps = Maps.newHashMap();
        List<SsFUsers> QZUsers = userGroupDAO.getQZUsers();
        List<SsFUsers> JSUsers = userGroupDAO.getJSDWUsers();
        maps.put("QZ", QZUsers);
        maps.put("JSDW", JSUsers);
        return new ResponseBase(200, "查询成功!", maps);
    }

    private String getRuntimeProcessInstanceId(String businessKey){
        String processInstanceId = personDAO.getProcessInstanceIdByBusinessKey(businessKey);
        return processInstanceId;
    }

    private String getHistoryProcessInstanceId(String businessKey){
        String processInstanceId = personDAO.getHisInstanceIdByBusinessKey(businessKey);
        return processInstanceId;
    }

    @Resource
    private FlowApiService flowApiService;

    /**
     * 获取所有该用户的流程
     *
     * @param token
     * @return
     */
    private ProcessReturn getListRuntimeTask(String businessKey, String token) {
        HttpParamers param = HttpParamers.httpPostParamers();
        param.addParam("processDefinitionKey", "");
        param.addParam("processDefinitionName", "");
        param.addParam("taskHandleStatus", 1);
        param.addParam("taskName", "");

        MyPageParam pageParam = new MyPageParam();
        pageParam.setPageNum(1);
        pageParam.setPageSize(1000);
//        pageParam.setTotalPage(1);

        param.addParam("pageParam", pageParam);

        HttpHeader header = new HttpHeader();
        header.addParam("token", token);
        String projectId = (String) RedisUtils.getCacheObject(LoginHelper.getUserId() + ".projectId");
        ResponseBase responseBase = flowApiService.runTimeTasks("", "", "", pageParam, projectId);

//        String response = ZhuJiAPIConfig.getListRuntimeTask(param, header);
        String response = JSONObject.toJSONString(responseBase);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String data = jsonObject.getString("data");
        JSONObject jsonData = JSONObject.parseObject(data);
        JSONArray jsonArray = jsonData.getJSONArray("list");

        List<ProcessReturn> processReturns = Lists.newArrayList();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            ProcessReturn processReturn =
                    (ProcessReturn) JSONObject.toJavaObject(object, ProcessReturn.class);
            processReturns.add(processReturn);
        }
        for (ProcessReturn processReturn : processReturns) {
            if (processReturn.getBusinessKey().equals(businessKey)) {
                return processReturn;
            }
        }
        return null;
    }

//    @Value("${zhujiapi.host}")
//    private String host;

    /**
     * 通过processInstanceId获取流程信息
     *
     * @param processInstanceId
     * @param token
     * @return
     */
    private TaskCommentReturn taskCommentReturn(String processInstanceId, String token) {
//        String post = "/ZhuJiApi/admin/flow/flowOperation/listFlowTaskComment";
//        String url = host + post;
//
//        Map<String, Object> maps = Maps.newHashMap();
//        maps.put("processInstanceId", processInstanceId);
//        String response = HttpUtils.sendGet(url, maps, token);

        ResponseResult<List<FlowTaskCommentVo>> listResponseResult = flowApiService.flowTaskComments(processInstanceId);

        String response = JSONObject.toJSONString(listResponseResult);

        JSONObject jsonObject = JSONObject.parseObject(response);
        if (ObjectUtil.isEmpty(jsonObject)){
            return null;
        }
        String isSuccess = jsonObject.getString("success");
        if (!isSuccess.equals("true")){
            return null;
        }
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        TaskCommentReturn commentReturn = new TaskCommentReturn();
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                TaskCommentReturn comment = (TaskCommentReturn) JSONObject.toJavaObject(object, TaskCommentReturn.class);
                commentReturn = comment;
            }
            return commentReturn;
        }
        return null;
    }

    private Map<String, String> getFlowAndTaskInfo(String businessKey, String token){
        ResponseBase flowAndTaskInfo = getFlowAndTaskInfo(businessKey);
        FlowKeysVo flowKeysVo = (FlowKeysVo)flowAndTaskInfo.getData();
        Map<String, String> maps = new WeakHashMap();
        maps.put("taskId", flowKeysVo.getTaskId());
        maps.put("processInstanceId", flowKeysVo.getProcessInstanceId());
        maps.put("processDefinitionId", flowKeysVo.getProcessDefinitionId());
        return maps;
    }

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    public ResponseBase getFlowAndTaskInfo(String businessKey) {
        //返回
        FlowKeysVo vo = new FlowKeysVo();
        //先查询正在运行的
        List<Task> tasks = taskService.createTaskQuery().active().processInstanceBusinessKey(businessKey).list();
        if (Objects.nonNull(tasks) && !tasks.isEmpty()){
            Task task = tasks.get(0);
            if (Objects.nonNull(task)){
                //查询流程实例信息
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
                vo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
                vo.setTaskId(task.getId());
                vo.setProcessInstanceId(processInstance.getId());
                return ResponseBase.success(vo);
            }
        }
        //否则查询历史，说明流程已经完成
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey).list();
        if (CollUtil.isNotEmpty(historicProcessInstances)){
//            #109 审批完成后，工作流审批记录显示不正确问题
            historicProcessInstances.sort(Comparator.comparing(HistoricProcessInstance::getEndTime).reversed());
            HistoricProcessInstance historicProcessInstance = historicProcessInstances.get(0);
            vo.setProcessDefinitionId(historicProcessInstance.getProcessDefinitionId());
            vo.setTaskId("");
            vo.setProcessInstanceId(historicProcessInstance.getId());
        }
        return ResponseBase.success(vo);
    }

//    private ProcessListHistoryReturn listHistoricTask(String businessKey, String token) {
//        String post = "/ZhuJiApi/admin/flow/flowOperation/listHistoricTask";
//
//        HttpParamers param = HttpParamers.httpPostParamers();
//        param.addParam("beginDate", "");
//        param.addParam("endDate", "");
//        param.addParam("processDefinitionName", "");
//
//        PageParam pageParam = new PageParam();
//        pageParam.setPageNum(1);
//        pageParam.setPageSize(100);
//        pageParam.setTotalPage(1);
//
//        param.addParam("pageParam", pageParam);
//
//        HttpHeader header = new HttpHeader();
//        header.addParam("token", token);
//
//        String response = ZhuJiAPIConfig.getListHistoricTask(post, param, header);
//        JSONObject jsonObject = JSONObject.parseObject(response);
//        String data = jsonObject.getString("data");
//        ProcessListHistoryReturn listHistoricTask1 = new ProcessListHistoryReturn();
//        if (data == null && data.equals("")) {
//            return listHistoricTask1;
//        }
//        JSONObject jsonData = JSONObject.parseObject(data);
//        JSONArray jsonArray = jsonData.getJSONArray("list");
//
//        List<ProcessListHistoryReturn> listHistoricTasks = Lists.newArrayList();
//        if (jsonArray.size() > 0) {
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject object = (JSONObject) jsonArray.get(i);
//                ProcessListHistoryReturn listHistoricTask =
//                        (ProcessListHistoryReturn) JSONObject.toJavaObject(object, ProcessListHistoryReturn.class);
//                listHistoricTasks.add(listHistoricTask);
//            }
//
//            for (ProcessListHistoryReturn listHistoricTask : listHistoricTasks) {
//                if (listHistoricTask.getBusinessKey().equals(businessKey)) {
//                    return listHistoricTask;
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 获取历史流程
     *
     * @param businessKey
     * @param token
     * @return
     */
//    private ListHistoryProcessInstance listHistoryProcessInstance(String businessKey, String token) {
//        String post = "/ZhuJiApi/admin/flow/flowOperation/listHistoricProcessInstance";
//
//        HttpParamers param = HttpParamers.httpPostParamers();
//        param.addParam("beginDate", "");
//        param.addParam("endDate", "");
//
//        PageParam pageParam = new PageParam();
//        pageParam.setPageSize(100);
//        pageParam.setTotalPage(1);
//        pageParam.setPageNum(1);
//
//        param.addParam("pageParam", pageParam);
//        param.addParam("processDefinitionName", "");
//
//        HttpHeader header = new HttpHeader();
//        header.addParam("token", token);
//
//        String response = ZhuJiAPIConfig.getListHistoricTask(post, param, header);
//        JSONObject jsonObject = JSONObject.parseObject(response);
//        String data = jsonObject.getString("data");
//        ListHistoryProcessInstance processReturn1 = new ListHistoryProcessInstance();
//        if (data == null && data.equals("")) {
//            return processReturn1;
//        }
//        JSONObject jsonData = JSONObject.parseObject(data);
//        JSONArray jsonArray = jsonData.getJSONArray("list");
//
//        List<ListHistoryProcessInstance> processReturns = Lists.newArrayList();
//        if (jsonArray.size() > 0) {
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject object = (JSONObject) jsonArray.get(i);
//                ListHistoryProcessInstance listHistoricTask =
//                        (ListHistoryProcessInstance) JSONObject.toJavaObject(object, ListHistoryProcessInstance.class);
//                processReturns.add(listHistoricTask);
//            }
//
//            for (ListHistoryProcessInstance processReturn : processReturns) {
//                if (processReturn.getBusinessKey().equals(businessKey)) {
//                    return processReturn;
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 停止并删除流程
     *
     * @param processInstanceId
     * @param token
     * @return
     */
    private PersonReturnModel stopProcessInstance(String processInstanceId, String token) {
        PersonReturnModel personReturnModel1 = new PersonReturnModel();
        personReturnModel1.setSuccess(true);
        flowApiService.stopAndDeleteProcessInstance(processInstanceId);
//        flowApiService.stopAndDeleteProcessInstance(processInstanceId);
//        //终止流程接口地址
//        String post1 = "/ZhuJiApi/admin/flow/flowOperation/stopProcessInstance";
//        //删除流程接口地址
//        String post2 = "/ZhuJiApi/admin/flow/flowOperation/deleteProcessInstance";
//
//        HttpParamers param1 = HttpParamers.httpPostParamers();
//        param1.addParam("processInstanceId", processInstanceId);
//        param1.addParam("stopReason", "终止流程, 需删除该流程数据!");
//
//        HttpHeader header1 = new HttpHeader();
//        header1.addParam("token", token);
//
//        String response1 = ZhuJiAPIConfig.getListHistoricTask(post1, param1, header1);
//        PersonReturnModel personReturnModel = JSONObject.parseObject(response1, PersonReturnModel.class);
//        PersonReturnModel personReturnModel1 = new PersonReturnModel();
//
//        HttpParamers param2 = HttpParamers.httpPostParamers();
//        param2.addParam("processInstanceId", processInstanceId);
//
//        HttpHeader header2 = new HttpHeader();
//        header2.addParam("token", token);
//
//        if (personReturnModel.isSuccess()) {
//            String response2 = ZhuJiAPIConfig.getListHistoricTask(post2, param2, header2);
//            personReturnModel1 = JSONObject.parseObject(response2, PersonReturnModel.class);
//        } else if (personReturnModel.getErrorMessage().contains("前流程尚未开始或已经结束")) {
//            String response2 = ZhuJiAPIConfig.getListHistoricTask(post2, param2, header2);
//            personReturnModel1 = JSONObject.parseObject(response2, PersonReturnModel.class);
//        }
        return personReturnModel1;
    }

//    private ListCopyMessage listCopyMessage(String businessKey, String token) {
//        //抄送列表地址
//        String post = "/ZhuJiApi/admin/flow/flowMessage/listCopyMessage";
//
//        HttpHeader header = new HttpHeader();
//        header.addParam("token", token);
//
//        HttpParamers param = HttpParamers.httpPostParamers();
//
//        PageParam pageParam = new PageParam();
//        pageParam.setPageNum(1);
//        pageParam.setPageSize(1000);
//        pageParam.setTotalPage(1);
//        param.addParam("pageParam", pageParam);
//
//        String response = ZhuJiAPIConfig.getListHistoricTask(post, param, header);
//
//        JSONObject jsonObject = JSONObject.parseObject(response);
//        String isSuccess = jsonObject.getString("success");
//        if (isSuccess.equals("true")) {
//            String data = jsonObject.getString("data");
//            JSONObject jsonData = JSONObject.parseObject(data);
//            JSONArray jsonArray = jsonData.getJSONArray("dataList");
//
//            List<ListCopyMessage> copyMessages = Lists.newArrayList();
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject object = (JSONObject) jsonArray.get(i);
//                ListCopyMessage copyMessage =
//                        (ListCopyMessage) JSONObject.toJavaObject(object, ListCopyMessage.class);
//
//                copyMessages.add(copyMessage);
//            }
//
//            for (ListCopyMessage copyMessage : copyMessages) {
//                if (copyMessage.getBusinessKey().equals(businessKey)) {
//                    return copyMessage;
//                }
//            }
//        }
//        return null;
//    }


    private Integer getRoleIdByRoleType(Integer roleType){
        Integer roleId = null;
        if (roleType == 1) {
            //施工
            roleId = personDAO.getShiGongRoleId();
        } else if (roleType == 2) {
            //监理
            roleId = personDAO.getJianLiRoleId();
        } else {
            //全资
            roleId = personDAO.getQuanZiRoleId();
        }
        return roleId;
    }

    public ResponseBase findPersonByPersonId(Integer personId) {
        PersonDTO personDTO = personDAO.selectByPrimaryKey(personId);
        List<PersonSub> personSubs = personDAO.getAllPersonSubById(personId);
        PersonSubDTO personSubDTO = new PersonSubDTO();
        personSubDTO.setPerson(personDTO);
        personSubDTO.setPersonSubs(personSubs);
        return ResponseBase.success(personSubDTO);
    }

    public ResponseBase findPersonByBusinessKey(String businessKey) {
        PersonDTO personDTO = personDAO.findPersonByBusinessKey(businessKey);
        List<PersonSub> personSubs = personDAO.getAllPersonSubById(personDTO.getId());
        PersonSubDTO personSubDTO = new PersonSubDTO();
        personSubDTO.setPerson(personDTO);
        personSubDTO.setPersonSubs(personSubs);
        return ResponseBase.success(personSubDTO);
    }
}
