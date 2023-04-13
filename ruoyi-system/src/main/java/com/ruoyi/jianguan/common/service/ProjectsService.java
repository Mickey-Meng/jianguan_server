package com.ruoyi.jianguan.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.domain.dto.UserRolesDTO;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.jianguan.common.dao.FileMapper;
import com.ruoyi.jianguan.common.dao.ProjectsDAO;
import com.ruoyi.jianguan.common.dao.SsFUserRoleDAO;
import com.ruoyi.jianguan.common.domain.dto.ProjectGroupUserTree;
import com.ruoyi.jianguan.common.domain.dto.ProjectsDTO;
import com.ruoyi.jianguan.common.domain.dto.RoleDataDTO;
import com.ruoyi.jianguan.common.domain.dto.SsFProjectsTree;
import com.ruoyi.jianguan.common.domain.entity.SsFCompany;
import com.ruoyi.jianguan.common.domain.entity.SsFProjectCompany;
import com.ruoyi.jianguan.common.domain.entity.SsFRoles;

import com.ruoyi.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/16 16:06
 * @Version : 1.0
 * @Description :
 **/
@Service
public class ProjectsService {

    @Autowired
    ProjectsDAO projectsDAO;
    @Autowired
    SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    SsFUserRoleDAO ssFUserRoleDAO;
    @Autowired
    FileMapper fileMapper;

    public ResponseBase addCompany(SsFCompany ssFCompany){
        if(ssFCompany.getName().equals("") || ssFCompany.getName() == null){
            return new ResponseBase(500, "请输入有效的单位名称!");
        }
        if (ssFCompany.getTypename().equals("") || ssFCompany.getTypename() == null){
            return new ResponseBase(500, "请输入有效的单位类型!");
        }
        if (ssFCompany.getLegalperson().equals("") || ssFCompany.getLegalperson() == null){
            return new ResponseBase(500, "请输入有效的法人!");
        }
        if (ssFCompany.getLegalphone().equals("") || ssFCompany.getLegalphone() == null){
            return new ResponseBase(500, "请输入有效的法人电话!");
        }
        if (ssFCompany.getDutynum().equals("") || ssFCompany.getDutynum() == null){
            return new ResponseBase(500, "请输入有效的单位税号!");
        }
        Integer row = projectsDAO.addCompany(ssFCompany);
        if (row == 1){
            return new ResponseBase(200, "新增单位成功!");
        }
        return new ResponseBase(500, "新增单位失败!");
    }

    public ResponseBase deleteCompany(Integer id){
        if (id <= 0){
            return new ResponseBase(500, "请输入有效的单位id!");
        }
        Integer result = projectsDAO.getCompanyNumById(id);
        if (result <= 0){
            return new ResponseBase(500, "无有效数据!");
        }
        Integer row = projectsDAO.deleteCompanyById(id);
        if (row == 1){
            return new ResponseBase(200, "删除成功!");
        }
        return new ResponseBase(500, "删除失败!");
    }

    public ResponseBase updateCompany(SsFCompany ssFCompany){
        if (ssFCompany.getId() <= 0){
            return new ResponseBase(500, "请输入有效的单位id!");
        }
        if (ssFCompany.getTypename().equals("") || ssFCompany.getTypename() == null){
            return new ResponseBase(500, "请输入有效的单位类型!");
        }
        if(ssFCompany.getName().equals("") || ssFCompany.getName() == null){
            return new ResponseBase(500, "请输入有效的单位名称!");
        }
        if (ssFCompany.getLegalphone().equals("") || ssFCompany.getLegalphone() == null){
            return new ResponseBase(500, "请输入有效的法人电话!");
        }
        if (ssFCompany.getLegalperson().equals("") || ssFCompany.getLegalperson() == null){
            return new ResponseBase(500, "请输入有效的法人!");
        }
        if (ssFCompany.getDutynum().equals("") || ssFCompany.getDutynum() == null){
            return new ResponseBase(500, "请输入有效的单位税号!");
        }
        Integer result = projectsDAO.getCompanyNumById(ssFCompany.getId());
        if (result <= 0){
            return new ResponseBase(500, "无有效数据!");
        }
        Integer row = projectsDAO.updateCompany(ssFCompany);
        if (row == 1){
            return new ResponseBase(200, "修改单位信息成功!");
        }
        return new ResponseBase(500, "修改单位信息失败!");
    }

    public ResponseBase getAllCompany(){
        List<SsFCompany> company = projectsDAO.getAllCompany();
        return new ResponseBase(200, "查询成功!", company);
    }

    public ResponseBase addProjects(ProjectsDTO projectsDTO){
        if (projectsDTO.getName() == null && projectsDTO.getName().equals("")){
            return new ResponseBase(200, "输入的项目名称为空,请重新输入!");
        }
        if (projectsDTO.getParentid() <= 0){
            return new ResponseBase(200, "请输入有效的父级id!");
        }
        if (projectsDTO.getGrouplevel() <= 0){
            return new ResponseBase(200, "请输入有效的级别");
        }

        projectsDTO.setSttime(new Date());
        projectsDTO.setStstate(1);
        if (projectsDTO.getGrouplevel() == 2){
            projectsDTO.setStorder(1);
            Integer row1 = projectsDAO.getNumByLevel2();
            projectsDTO.setCode("1-" + (row1 + 1));

            projectsDAO.addProject(projectsDTO);
        } else if (projectsDTO.getGrouplevel() == 3){
            projectsDTO.setStorder(2);
            //获取父级下的子项目有多少个
            Integer row2 = projectsDAO.getNumLevel3ByParentId(projectsDTO.getParentid());
            //获取父级的code
            String parentcode = projectsDAO.getCodeById(projectsDTO.getParentid());
            projectsDTO.setCode(parentcode + "-" + (row2 + 1));
            //插入项目表
            projectsDAO.addProject(projectsDTO);
            //把关联的单位字符串转成integer的集合  例:  "1,2,3,4"
            if (!projectsDTO.getProjectCompany().equals("") || projectsDTO.getProjectCompany() != null){
                List<Integer> companyids =
                        Arrays.stream(projectsDTO.getProjectCompany().split(","))
                                .map(Integer::parseInt).collect(Collectors.toList());
                //查询刚插入的项目的id
                Integer projectId = projectsDAO.getId();
                List<SsFProjectCompany> projectCompany = Lists.newArrayList();
                for (Integer id : companyids) {
                    //设置值
                    SsFProjectCompany ssFProjectCompany = new SsFProjectCompany();
                    ssFProjectCompany.setProjectid(projectId);
                    ssFProjectCompany.setCompanyid(id);
                    ssFProjectCompany.setState(1);
                    ssFProjectCompany.setSttime(new Date());
                    //放入list中
                    projectCompany.add(ssFProjectCompany);
                }
                projectsDAO.addProjectCompany(projectCompany);
            }
        } else {
            projectsDTO.setSttime(new Date());
            projectsDTO.setStstate(1);
            projectsDTO.setStorder(8);
            projectsDAO.addProject(projectsDTO);
        }
        return new ResponseBase(200, "插入成功!");
    }

    public ResponseBase deleteProjects(Integer projectid){
        if (projectid <= 0){
            return new ResponseBase(500, "请输入有效的项目id!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectid);
        if (project == null){
            return new ResponseBase(500, "没有查询到数据!");
        }
        if (project.getGrouplevel() == 3){
            //去project_company表是否存在数据
            projectsDAO.deleteProjectCompanyById(project.getId());
        }
        projectsDAO.deleteProjectById(projectid);
        //同时删除文件
        if (project.getProjectpic() != null && !project.getProjectpic().equals("")){
            try {
                fileMapper.delete(project.getProjectpic());
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseBase(200, "删除成功!");
    }

    public ResponseBase updateProjects(ProjectsDTO projectsDTO){
        if (projectsDTO.getId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectsDTO.getId());
        if (count <= 0){
            return new ResponseBase(200, "没有查询到数据!");
        }
        projectsDAO.updateProjects(projectsDTO);

        List<SsFProjectCompany> projectCompany = Lists.newArrayList();
        if (projectsDTO.getProjectCompany() != null && !projectsDTO.getProjectCompany().equals("")){
            List<Integer> companys = Arrays.stream(projectsDTO.getProjectCompany().split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
            //首先删除之前该项目对应的单位,再重新插入
            projectsDAO.deleteProjectCompanyById(projectsDTO.getId());
            for (Integer company : companys) {
                //设置值
                SsFProjectCompany ssFProjectCompany = new SsFProjectCompany();
                ssFProjectCompany.setProjectid(projectsDTO.getId());
                ssFProjectCompany.setCompanyid(company);
                ssFProjectCompany.setState(1);
                ssFProjectCompany.setSttime(new Date());
                //放入list中
                projectCompany.add(ssFProjectCompany);
            }
            projectsDAO.addProjectCompany(projectCompany);
        }
        return new ResponseBase(500, "修改项目数据信息成功!");
    }

    public ResponseBase getAll(){
        List<SsFProjects> ssFGroups = projectsDAO.getAll();
        return new ResponseBase(200, "查询成功!", ssFGroups);
    }

    public ResponseBase getAllProjects(){
        List<SsFProjects> ssFGroups = projectsDAO.getAllProjects();
        if (ssFGroups.size() == 0){
            return new ResponseBase(500, "暂未无数据!");
        }
        return new ResponseBase(200, "查询成功!", ssFGroups);
    }

    public ResponseBase getInfoByProjectId(Integer projectId){
        Map<String, Object> maps = new HashMap<>();
        if (projectId <= 0){
            return new ResponseBase(500, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(500, "没有查询到数据!");
        }
        String groupids = projectsDAO.getGroupIdsById(projectId);
        List<SsFGroups> groupInfos = Lists.newArrayList();
        List<SsFUsers> userInfos = Lists.newArrayList();
        if (!groupids.equals("") || groupids != null){
            //把关联的单位字符串转成integer的集合  例:  "1,2,3,4"
            List<Integer> groupIds = Arrays.stream(groupids.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
            groupInfos = projectsDAO.getGroupInfosByIds(groupIds);
            userInfos = projectsDAO.getUserInfosByGroupsIds(groupIds);
        }

        maps.put("groupInfos", groupInfos);
        maps.put("userInfos", userInfos);

        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getProjectInfoById(Integer projectId){
        Map<String, Object> maps = new HashMap<>();
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(200, "没有查询到数据!");
        }
        // 1.查询项目有关单位信息
        List<Integer> companyids = projectsDAO.getCompanyidsByProjectId(projectId);
        if (companyids.size()==0){
            //没有查到相关的单位信息
            maps.put("companys", companyids);
        } else {
            List<SsFCompany> companys = projectsDAO.getCompnayByIds(companyids);
            maps.put("companys", companys);
        }
        // 2.查询项目信息
        SsFProjects project = projectsDAO.getProjectById(projectId);
        maps.put("project", project);

        return new ResponseBase(200, "查询成功！", maps);
    }

    public ResponseBase getProjectUserTree(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        String groupIds = project.getGroupid();
        List<Integer> groupids = Arrays.stream(groupIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //这里的ss_f_groups为最下面一层(第三层结构)
        //获取第一层
        ProjectGroupUserTree tree1 = projectsDAO.getGroupsLevel(2);
        //获取第二层
        List<ProjectGroupUserTree> tree2 = projectsDAO.getGroupsLevel3(groupids);
        //获取第三层
        for (ProjectGroupUserTree tree : tree2) {
            List<SsFUsers> users = ssFUserGroupDAO.getUserByGroupsId(tree.getId());
            tree.setChildren(Collections.singletonList(users));
        }
        tree1.setChildren(Collections.singletonList(tree2));

        return new ResponseBase(200, "查询成功!", tree1);
    }

    public ResponseBase getProjectRoleTree(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        String groupIds = project.getGroupid();
        List<Integer> groupids = Arrays.stream(groupIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //这里的ss_f_groups为最下面一层(第三层结构)
        //获取第一层
        ProjectGroupUserTree tree1 = projectsDAO.getGroupsLevel(2);
        //获取第二层
        List<ProjectGroupUserTree> tree2 = projectsDAO.getGroupsLevel3(groupids);
        //获取第三层
        for (ProjectGroupUserTree tree : tree2) {
            List<Integer> userids = ssFUserGroupDAO.getUserIdsByGroupsIds(tree.getId());
            if (userids.size() >= 1) {
                List<RoleDataDTO> roles = ssFUserRoleDAO.getRoleByUserids(userids);
                //去重
                roles = roles.stream().collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RoleDataDTO::getName))), ArrayList::new)
                );
                tree.setChildren(Collections.singletonList(roles));
            }
        }
        tree1.setChildren(Collections.singletonList(tree2));

        return new ResponseBase(200, "查询成功!", tree1);
    }

    public ResponseBase getChildProject(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<SsFProjects> projects = projectsDAO.getChildProjectById(projectId);

        return new ResponseBase(200, "查询成功!", projects);
    }

    public ResponseBase getCompanyByProjectId(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count = projectsDAO.getCountById(projectId);
        if (count <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Map<String, List<SsFCompany>> maps = new HashMap<>();
        List<String> typeCode = projectsDAO.getTypeCodeByProjectId(projectId);
        typeCode = typeCode.stream().distinct().collect(Collectors.toList());
        for (String s : typeCode) {
            List<SsFCompany> companys = projectsDAO.getAllCompanyByTypecode(s);
            maps.put(s, companys);
        }

        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getUserByRoleId(Integer projectId, Integer roleid){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        if (roleid <= 0){
            return new ResponseBase(200, "请输入有效的角色id!");
        }
        Integer count2 = projectsDAO.getRoleCountById(projectId);
        if (count2 <= 0){
            return new ResponseBase(200, "该角色id无数据!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        List<Integer> groupids = Arrays.stream(project.getGroupid().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> userIds = Lists.newArrayList();

        for (Integer groupid : groupids) {
            List<Integer> userids = ssFUserGroupDAO.getUserIdsByGroupsIds(groupid);
            userIds.addAll(userids);
        }
        List<Integer> roles = ssFUserRoleDAO.getRolesIdByUserids(userIds);
        if (!roles.contains(roleid)){
            return new ResponseBase(507, "该角色id不属于该项目!");
        }
        List<SsFUsers> users = ssFUserRoleDAO.getUsersByRoleid(roleid);

        return new ResponseBase(200, "查询成功!", users);
    }

    public ResponseBase getRoleByUserId(Integer projectId, Integer userid){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        if (userid <= 0){
            return new ResponseBase(200, "该用户id无效!");
        }
        Integer count2 = projectsDAO.getUserCountById(userid);
        if (count2 <= 0){
            return new ResponseBase(200, "该用户id无数据!");
        }
        Integer count3 = projectsDAO.getRoleCountByUserId(userid);
        if (count3 <= 0){
            return new ResponseBase(503, "该用户没暂时没有角色权限");
        }
        if (userid == null || userid == 0){
            userid = LoginHelper.getUserId().intValue();
        }
        SsFRoles roles = ssFUserRoleDAO.getRolesByUserid(userid);
        return new ResponseBase(200, "查询成功!", roles);
    }

    public ResponseBase getUsersByUserid(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = LoginHelper.getUserId().intValue();
        if (userid <= 0){
            return new ResponseBase(200, "该用户id无效!");
        }
        List<UserRolesDTO> users = ssFUserGroupDAO.getUsersByUserid(userid);

        return new ResponseBase(200, "查询成功!", users);
    }

    public ResponseBase getGongQuByProjectId(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<SsFProjects> projects = projectsDAO.getAllGongQuByProjectId(projectId);
        return new ResponseBase(200, "查询成功!", projects);
    }

    public ResponseBase getProjects(){
        SsFProjectsTree projectsTree = new SsFProjectsTree();
        projectsTree.setId(1);
        projectsTree.setName("顶级");
        projectsTree.setCode("1");
        List<SsFProjectsTree> tree = projectsDAO.getALlProject();
        List<SsFProjectsTree> newTree = getProjectsTree(tree, projectsTree);
        return new ResponseBase(200, "查询成功!", newTree);
    }

    public ResponseBase getRoleByUser(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Map<String, Object> maps = Maps.newHashMap();
        Integer userId = LoginHelper.getUserId().intValue();
        SsFRoles role = ssFUserRoleDAO.getRolesByUserid(userId);
        String parentCode = ssFUserRoleDAO.getParentCodeByRoleId(role.getParentid());
        maps.put("role", role);
        maps.put("parentCode", parentCode);
        return new ResponseBase(200, "查询成功!", maps);
    }

    public ResponseBase getRolesByProject(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取该项目id下的所有部门id
        String groups = projectsDAO.getGroupIdsById(projectId);
        List<SsFRoles> roles = Lists.newArrayList();
        if (groups != null || !groups.equals("")){
            List<Integer> groupIds = Arrays.stream(groups.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            roles = ssFUserRoleDAO.getRolesByGroupIds(groupIds);
        }

        return new ResponseBase(200, "查询成功!", roles);
    }

    public ResponseBase getProjectByUser(){
        try {
            //Integer userId = LoginHelper.getUserId().intValue();
            Long userId = LoginHelper.getLoginUser().getUserId();
            List<SsFProjects> projects = projectsDAO.getSectionProjectsIdByUserId(userId.intValue());
            return new ResponseBase(200, "", projects);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public ResponseBase getAppProjectByUser (){
        try {
            Integer userId = LoginHelper.getUserId().intValue();
            //查询用户所拥有的标段
            List<SsFProjects> projects = projectsDAO.getSectionProjectsIdByUserId(userId);
            List<Integer> projectIds = Lists.newArrayList();
            SsFProjectsTree projectsTree = new SsFProjectsTree();
            if (projects.size() > 0){
                for (SsFProjects project : projects) {
                    //获取标段的父级id以及自身id(2级, 3级)
                    projectIds.add(project.getId());
                    projectIds.add(project.getParentid());
                }
                projectsTree.setName("顶级");
                projectsTree.setId(1);
                projectsTree.setCode("1");
                List<SsFProjectsTree> tree = projectsDAO.getALlProjectByIds(projectIds);
                List<SsFProjectsTree> newTree = getProjectsTree(tree, projectsTree);
                return new ResponseBase(200, "查询成功!", newTree);
            } else {
                return new ResponseBase(200, "查询成功！", projectsTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    private List<SsFProjectsTree> getProjectsTree(List<SsFProjectsTree> lists, SsFProjectsTree tree){
        List<SsFProjectsTree> child = lists.stream().filter(item -> Integer.compare(item.getParentid(), tree.getId()) == 0)
                .map((item) -> {
                    item.setChild(getProjectsTree(lists, item));
                    return item;
                }).collect(Collectors.toList());
        return child;
    }

}
