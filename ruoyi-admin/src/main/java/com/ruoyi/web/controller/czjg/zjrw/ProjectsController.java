package com.ruoyi.web.controller.czjg.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.domain.dto.ProjectsDTO;
import com.ruoyi.czjg.zjrw.domain.entity.SsFCompany;
import com.ruoyi.czjg.zjrw.service.ProjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/16 16:00
 * @Version : 1.0
 * @Description :
 **/
@RestController
@RequestMapping("/projects")
@Api(value = "项目部门控制类")
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @PostMapping(value = "/addCompany")
    @ApiOperation(value = "添加单位")
    public ResponseBase addCompany(@RequestBody SsFCompany ssFCompany){
        return projectsService.addCompany(ssFCompany);
    }

    @PostMapping(value = "/deleteCompany")
    @ApiOperation(value = "通过id删除项目")
    public ResponseBase deleteCompany(@RequestParam(value = "id", required = false) Integer id){
        return projectsService.deleteCompany(id);
    }

    @PostMapping(value = "/updateCompany")
    @ApiOperation(value = "通过id修改项目数据信息")
    public ResponseBase updateCompany(@RequestBody SsFCompany ssFCompany){
        return projectsService.updateCompany(ssFCompany);
    }

    @PostMapping(value = "/getAllCompany")
    @ApiOperation(value = "获取所有单位信息")
    public ResponseBase getAllCompany(){
        return projectsService.getAllCompany();
    }

    @PostMapping(value = "/addProjects")
    @ApiOperation(value = "添加项目")
    public ResponseBase addProjects(@RequestBody ProjectsDTO projectsDTO){
        return projectsService.addProjects(projectsDTO);
    }

    @PostMapping(value = "/deleteProjects")
    @ApiOperation(value = "通过id删除项目")
    public ResponseBase deleteProjects(@RequestParam(value = "projectid", required = false) Integer projectid){
        return projectsService.deleteProjects(projectid);
    }

    @PostMapping(value = "/updateProjects")
    @ApiOperation(value = "通过id修改项目数据信息")
    public ResponseBase updateProjects(@RequestBody ProjectsDTO projectsDTO){
        return projectsService.updateProjects(projectsDTO);
    }

    @PostMapping(value = "/getAll")
    @ApiOperation(value = "查询所有项目(包括顶级)")
    public ResponseBase getAll(){
        return projectsService.getAll();
    }

    @PostMapping(value = "/getAllProjects")
    @ApiOperation(value = "查询所有项目")
    public ResponseBase getAllProjects(){
        return projectsService.getAllProjects();
    }

    @PostMapping(value = "/getInfoByProjectId")
    @ApiOperation(value = "通过项目id查询组织信息和用户信息")
    public ResponseBase getInfoByProjectId(@RequestParam(value = "id")Integer id){
        return projectsService.getInfoByProjectId(id);
    }

    @PostMapping(value = "/getProjectInfoById")
    @ApiOperation(value = "通过项目id查询项目详细信息（项目名、施工单位、监理单位、合同号等）")
    public ResponseBase getProjectInfoById(@RequestParam(value = "projectid")Integer projectid){
        return projectsService.getProjectInfoById(projectid);
    }

    @PostMapping(value = "/getProjectUserTree")
    @ApiOperation(value = "获取项目下面的组织用户树")
    public ResponseBase getProjectUserTree(@RequestParam(value = "projectid")Integer id){
        return projectsService.getProjectUserTree(id);
    }

    @PostMapping(value = "/getProjectRoleTree")
    @ApiOperation(value = "获取项目下面的组织角色树")
    public ResponseBase getProjectRoleTree(@RequestParam(value = "projectid")Integer id){
        return projectsService.getProjectRoleTree(id);
    }

    @PostMapping(value = "/getChildProject")
    @ApiOperation(value = "通过项目id获取项目子级的标段信息")
    public ResponseBase getChildProject(@RequestParam(value = "projectid")Integer id){
        return projectsService.getChildProject(id);
    }

    @PostMapping(value = "/getCompanyByProjectId")
    @ApiOperation(value = "通过项目下的标段id获取项目的单位信息")
    public ResponseBase getCompanyByProjectId(@RequestParam(value = "projectid")Integer id){
        return projectsService.getCompanyByProjectId(id);
    }

    @PostMapping(value = "/getUserByRoleId")
    @ApiOperation(value = "通过角色id获取用户信息")
    public ResponseBase getUserByRoleId(@RequestParam(value = "projectid")Integer projectid,
                                        @RequestParam(value = "roleid")Integer roleid){
        return projectsService.getUserByRoleId(projectid, roleid);
    }

    @PostMapping(value = "/getRoleByUserId")
    @ApiOperation(value = "通过用户id获取角色信息")
    public ResponseBase getRoleByUserId(@RequestParam(value = "projectid")Integer projectid,
                                        @RequestParam(value = "userid")Integer userid){
        return projectsService.getRoleByUserId(projectid, userid);
    }

    @PostMapping("/getUsersByUserid")
    @ApiOperation(value = "通过用户id获取对应组织下的所有用户信息")
    public ResponseBase getUsersByUserid(@RequestParam(value = "projectid")Integer projectid){
        return projectsService.getUsersByUserid(projectid);
    }

    @PostMapping("/getGongQu")
    @ApiOperation(value = "通过项目的标段id获取下面的所有工区")
    public ResponseBase getGongQuByProjectId(@RequestParam(value = "projectid")Integer projectid){
        return projectsService.getGongQuByProjectId(projectid);
    }

    @PostMapping("/getProjects")
    @ApiOperation(value = "获取所有项目及下面的标段")
    public ResponseBase getProjects(){
        return projectsService.getProjects();
    }

    @PostMapping("/getRoleByUser")
    @ApiOperation(value = "通过用户查询对应角色信息及父级的code")
    public ResponseBase getRoleByUser(@RequestParam(value = "projectId")Integer projectId){
        return projectsService.getRoleByUser(projectId);
    }

    @PostMapping("/getRolesByProject")
    @ApiOperation(value = "通过项目标段id查询该项目下所有用户所拥有的角色信息")
    public ResponseBase getRolesByProject(@RequestParam(value = "projectId")Integer projectId){
        return projectsService.getRolesByProject(projectId);
    }

    @ResponseBody
    @PostMapping("/getProjectByUser")
    @ApiOperation(value = "通过用户查询所拥有的项目权限(目前只支持用户所拥有的工区权限查询)")
    public ResponseBase getProjectByUser(){
        return projectsService.getProjectByUser();
    }

    @ResponseBody
    @PostMapping("/getAppProjectByUser")
    @ApiOperation(value = "通过用户查询所拥有的项目权限(目前只支持用户所拥有的工区权限查询, 分两级，同时查询项目以及标段)")
    public ResponseBase getAppProjectByUser(){
        return projectsService.getAppProjectByUser();
    }
}
