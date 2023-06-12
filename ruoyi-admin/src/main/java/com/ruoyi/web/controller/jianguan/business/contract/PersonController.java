package com.ruoyi.web.controller.jianguan.business.contract;

import com.ruoyi.common.annotation.CheckRepeatCommit;
import com.ruoyi.common.core.domain.model.ZjPersonChange;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.PersonSubDTO;
import com.ruoyi.jianguan.common.domain.entity.PersonUserGroupRole;
import com.ruoyi.jianguan.common.domain.entity.SsFPersonGroups;
import com.ruoyi.jianguan.common.domain.entity.ZjPersonClockin;
import com.ruoyi.jianguan.common.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/9 13:38
 * @Version : 1.0
 * @Description : 人员报审
 **/
@RestController
@RequestMapping("/person")
@Api(value = "人员报审")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/addDepartment")
    @ApiOperation(value = "批量添加部门信息")
    public ResponseBase addDepartment(@RequestBody List<SsFPersonGroups> ssFGroups){
        return personService.addDepartment(ssFGroups);
    }

    @PostMapping("/updateDepartment")
    @ApiOperation(value = "修改部门信息")
    public ResponseBase updateDepartment(@RequestBody SsFPersonGroups ssFGroup){
        return personService.updateDepartment(ssFGroup);
    }

    @PostMapping("/getDepartments")
    @ApiOperation(value = "获取部门信息")
    public ResponseBase getDepartments(){
        return personService.getDepartments();
    }

    @PostMapping("/deleteDepartment")
    @ApiOperation(value = "删除部门信息")
    public ResponseBase deleteDepartment(@RequestParam(value = "id", required = true) Integer id){
        return personService.deleteDepartment(id);
    }

    @PostMapping("/addUserGroup")
    @ApiOperation(value = "添加用户、部门、角色关联关系")
    public ResponseBase addUserGroup(@RequestBody List<PersonUserGroupRole> ssFUserGroup){
        return personService.addUserGroup(ssFUserGroup);
    }

    @PostMapping("/getGroupByUserid")
    @ApiOperation(value = "通过用户查询所在部门")
    public ResponseBase getGroupByUserid(@RequestParam(value = "userid", required = true) Integer userid){
        return personService.getGroupByUserid(userid);
    }

    @PostMapping("/getUserByGroup")
    @ApiOperation(value = "查询部门及用户")
    public ResponseBase getUserByGroup(){
        return personService.getUserByGroup();
    }

    @PostMapping("/getUserByRole")
    @ApiOperation(value = "通过角色查询用户")
    public ResponseBase getUserByRole(@RequestParam(value = "roleid", defaultValue = "0") Integer roleid,
                                      @RequestParam(value = "projectId", required = false) Integer projectId){
        return personService.getUserByRole(roleid, projectId);
    }

    @PostMapping("/getPersonRole")
    @ApiOperation(value = "获取人员部门角色权限关联关系")
    public ResponseBase getPersonRole(){
        return personService.getPersonRole();
    }

    @PostMapping("/delContract")
    @ApiOperation(value = "删除人员报审记录")
    public ResponseBase delContract(@RequestParam(value = "id", defaultValue = "0")Integer id,
                                    @RequestParam(value = "projectId", defaultValue = "0")Integer projectId){
        return personService.delContract(id, projectId);
    }

    @PostMapping("/updateContract")
    @ApiOperation(value = "修改人员报审")
    public ResponseBase updateContract(@RequestBody PersonSubDTO person){
        return personService.updateContract(person);
    }

    @PostMapping("/getContracts")
    @ApiOperation(value = "通过项目id,用户id 获取所有人员合同(填报记录)")
    public ResponseBase getContracts(@RequestParam(value = "projectid", defaultValue = "0")Integer projectid){
        return personService.getContracts(projectid);
    }

    @PostMapping("/getContractByProcessId")
    @ApiOperation(value = "通过项目id, businessKey, type(动作id:1-我的待办," +
            "2-我的已办,3-我的历史,4-抄送列表) 获取所有人员记录")
    public ResponseBase getContractByProcessId(@RequestParam("projectid")Integer projectid,
                                               @RequestParam("businessKey")String businessKey,
                                               @RequestParam("type")Integer type){
        return personService.getContractByProcessId(projectid, businessKey, type);
    }

    @PostMapping("/getAllContract")
    @ApiOperation(value = "系统管理员获取所有报审")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id"),
            @ApiImplicitParam(name = "roleType", value = "角色(1-施工, 2-监理, 3-全资)"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数")
    })
    public ResponseBase getAllContract(@RequestParam(value = "projectId", defaultValue = "0")Integer projectId,
                                       @RequestParam(value = "roleType", required = false)Integer roleType,
                                       @RequestParam(value = "pageNum", required = false)Integer pageNum,
                                       @RequestParam(value = "pageSize", required = false)Integer pageSize){
        return personService.getAllContract(projectId, roleType, pageNum, pageSize);
    }

    @PostMapping("/getVerifyContact")
    @ApiOperation(value = "通过项目id,用户id 获取审核的所有人员合同(填报记录)")
    public ResponseBase getVerifyContact(@RequestParam("projectid")Integer projectid){
        return personService.getVerifyContact(projectid);
    }

    @PostMapping("/subContract")
    //检查用户是否在短时间内重复发送请求
    @CheckRepeatCommit(expireTime = 30)
    @ApiOperation(value = "提交合同(提交新增填报)")
    public ResponseBase subContract(@RequestBody PersonSubDTO personSubDTO){
        return personService.subContract(personSubDTO);
    }

    @PostMapping("/getContractPerson")
    @ApiOperation(value = "通过合同id查询该合同里面的人员信息")
    public ResponseBase getContractPerson(@RequestParam(value = "id", defaultValue = "0")Integer id){
        return personService.getContractPerson(id);
    }

    @PostMapping("/getAllUser")
    @ApiOperation(value = "通过项目下的标段id,用户token获取该用户统一标段下的所有用户信息")
    public ResponseBase getAllUser(@RequestParam(value = "projectId", defaultValue = "0")Integer projectid){
        return personService.getAllUser(projectid);
    }

    @PostMapping("subPersonChange")
    @ApiOperation(value = "提交人员变更")
    //检查用户是否在短时间内重复发送请求
    @CheckRepeatCommit(expireTime = 30)
    public ResponseBase subPersonChange(@RequestBody ZjPersonChange personChange){
        return personService.subPersonChange(personChange);
    }

    @PostMapping("/getPersonChange")
    @ApiOperation(value = "获取人员变更信息")
    public ResponseBase getPersonChange(@RequestParam(value = "projectid", defaultValue = "0")Integer projectid){
        return personService.getPersonChange(projectid);
    }

    @PostMapping("/getChangeByProcessId")
    @ApiOperation(value = "通过项目id, businessKey, type(动作id:1-待办,2-已办,3-历史,4-抄送) 获取所有人员记录")
    public ResponseBase getChangeByProcessId(@RequestParam(value = "projectid", defaultValue = "0")Integer projectid,
                                               @RequestParam(value = "businessKey", defaultValue = "")String businessKey,
                                               @RequestParam(value = "type", defaultValue = "")Integer type){
        return personService.getChangeByProcessId(projectid, businessKey, type);
    }

    @PostMapping("/getAllChange")
    @ApiOperation(value = "系统管理员获取所有变更")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id"),
            @ApiImplicitParam(name = "roleType", value = "角色(1-施工, 2-监理, 3-全资)"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数")
    })
    public ResponseBase getAllChange(@RequestParam(value = "projectId", defaultValue = "0")Integer projectId,
                                     @RequestParam(value = "roleType", required = false)Integer roleType,
                                     @RequestParam(value = "pageNum", required = false)Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false)Integer pageSize){
        return personService.getAllChange(projectId, roleType, pageNum, pageSize);
    }

    @PostMapping("/delChange")
    @ApiOperation(value = "删除变更信息")
    public ResponseBase delChange(@RequestParam(value = "id", defaultValue = "0")Integer id,
                                    @RequestParam(value = "projectId", defaultValue = "0")Integer projectId){
        return personService.delChange(id, projectId);
    }

    @PostMapping("/subLeave")
    @ApiOperation(value = "提交请假")
    //检查用户是否在短时间内重复发送请求
    @CheckRepeatCommit(expireTime = 30)
    public ResponseBase subLeave(@RequestBody ZjPersonLeave personLeave){
        return personService.subLeave(personLeave);
    }

    @PostMapping("/getLeaveData")
    @ApiOperation(value = "获取请假信息")
    public ResponseBase getLeaveData(@RequestParam(value = "projectId")Integer projecid){
        return personService.getLeaveData(projecid);
    }

    @PostMapping("/getLeaveByProcessId")
    @ApiOperation(value = "通过项目id, businessKey, type(动作id:1-待办,2-已办,3-历史,4-抄送) 获取所有人员记录")
    public ResponseBase getLeaveByProcessId(@RequestParam(value = "projectId", defaultValue = "0")Integer projectid,
                                             @RequestParam(value = "businessKey", defaultValue = "")String businessKey,
                                             @RequestParam(value = "type", defaultValue = "")Integer type){
        return personService.getLeaveByProcessId(projectid, businessKey, type);
    }

    @PostMapping("/getAllLeave")
    @ApiOperation(value = "系统管理员获取所有请假")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id"),
            @ApiImplicitParam(name = "roleType", value = "角色(1-施工, 2-监理, 3-全资)"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数"),
            @ApiImplicitParam(name = "state", value = "查询状态: 0-无效, 1-有效, 2-已审批通过, 3-请假已过期")
    })
    public ResponseBase getAllLeave(@RequestParam(value = "projectId", defaultValue = "0")Integer projectId,
                                    @RequestParam(value = "roleType", required = false)Integer roleType,
                                    @RequestParam(value = "pageNum", required = false)Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false)Integer pageSize,
                                    @RequestParam(value = "state", required = false)Integer state){
        return personService.getAllLeave(projectId, roleType, pageNum, pageSize, state);
    }

    @PostMapping("/delLeave")
    @ApiOperation(value = "删除请假信息")
    public ResponseBase delLeave(@RequestParam(value = "id", required = true, defaultValue = "0")Integer id,
                                  @RequestParam("projectId")Integer projectId){
        return personService.delLeave(id, projectId);
    }

    @PostMapping("/subClockIn")
    @ApiOperation(value = "考勤打卡")
    public ResponseBase clockIn(@RequestBody ZjPersonClockin clockIn){
        return personService.clockIn(clockIn);
    }

    @PostMapping("/getClockIn")
    @ApiOperation(value = "获取考勤打卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id"),
            @ApiImplicitParam(name = "startTime", value = "查询的开始时间(格式: yyyy-MM-dd)"),
            @ApiImplicitParam(name = "endTime", value = "查询的结束时间(格式同上开始时间)"),
            @ApiImplicitParam(name = "roleType", value = "角色的集合, 例(1:施工, 2:监理, 3:全资)")
    })
    public ResponseBase getClockIn(@RequestParam(value = "projectId")Integer project,
                                   @RequestParam(value = "startTime", required = false)String startTime,
                                   @RequestParam(value = "endTime", required = false)String endTime,
                                   @RequestParam(value = "roleType", required = false)Integer roleType){
        return personService.getClockIn(project, startTime, endTime, roleType);
    }

    @PostMapping("/getSelfClockIn")
    @ApiOperation(value = "获取个人考勤打卡")
    public ResponseBase getSelfClockIn(@RequestParam(value = "projectId")Integer project,
                                   @RequestParam(value = "startTime", required = false)String startTime,
                                   @RequestParam(value = "endTime", required = false)String endTime){
        return personService.getSelfClockIn(project, startTime, endTime);
    }

    @ResponseBody
    @PostMapping("/getNoClockIn")
    @ApiOperation(value = "获取未打卡人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id"),
            @ApiImplicitParam(name = "startTime", value = "查询的开始时间(格式: yyyy-MM-dd)"),
            @ApiImplicitParam(name = "endTime", value = "查询的结束时间(格式同上开始时间)"),
            @ApiImplicitParam(name = "roleType", value = "角色的集合, 例(1:施工, 2:监理, 3:全资)")
    })
    public ResponseBase getNoClockIn(@RequestParam(value = "projectId")Integer project,
                                     @RequestParam(value = "startTime", required = false)String startTime,
                                     @RequestParam(value = "endTime", required = false)String endTime,
                                     @RequestParam(value = "roleType", required = false)Integer roleType){
        return personService.getNoClockIn(project, startTime, endTime, roleType);
    }

    @PostMapping("/remindClockIn")
    @ApiOperation(value = "未打卡消息提醒")
    public ResponseBase remindClockIn(){
        return personService.remindClockIn();
    }

    @PostMapping("/getContractStandingBook")
    @ApiOperation(value = "获取人员报审台账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleType", value = "角色的集合, 例(1:施工, 2:监理, 3:全资)")
    })
    public ResponseBase getContractStandingBook(@RequestParam(value = "projectId")Integer project,
                                                @RequestParam(value = "roleType", required = false)Integer roleType){
        return personService.getContractStandingBook(project, roleType);
    }

    @PostMapping("/getChangeStandingBook")
    @ApiOperation(value = "获取人员变更台账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleType", value = "角色的集合, 例(1:施工, 2:监理, 3:全资)")
    })
    public ResponseBase getChangeStandingBook(@RequestParam(value = "projectId")Integer project,
                                              @RequestParam(value = "roleType", required = false)Integer roleType){
        return personService.getChangeStandingBook(project, roleType);
    }

    @PostMapping("/getLeaveStandingBook")
    @ApiOperation(value = "获取请假台账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleType", value = "角色的集合, 例(1:施工, 2:监理, 3:全资)")
    })
    public ResponseBase getLeaveStandingBook(@RequestParam(value = "projectId")Integer project,
                                             @RequestParam(value = "roleType", required = false)Integer roleType){
        return personService.getLeaveStandingBook(project, roleType);
    }

    @PostMapping("/getFenceByUser")
    @ApiOperation(value = "通过用户获取对应单位工程(工区)所属电子围栏范围")
    public ResponseBase getFenceByUser(@RequestParam(value = "projectId")Integer project){
        return personService.getFenceByUser(project);
    }

    @ResponseBody
    @PostMapping("/getPerson")
    @ApiOperation(value = "获取全资集团和建设集团的人员信息")
    public ResponseBase getPerson(@RequestParam(value = "projectId", required = true, defaultValue = "0")Integer projectId){
        return personService.getPerson(projectId);
    }

    @ResponseBody
    @PostMapping("/getPersonDetail")
    @ApiOperation(value = "查询人员申报详情")
    public ResponseBase getPersonDetail(@RequestParam(value = "personId", required = true,  defaultValue = "0")Integer personId){
        return personService.findPersonByPersonId(personId);
    }
}
