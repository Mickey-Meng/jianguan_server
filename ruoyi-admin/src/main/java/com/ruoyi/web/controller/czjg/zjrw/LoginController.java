package com.ruoyi.web.controller.czjg.zjrw;

import com.ruoyi.common.annotation.AuthIgnore;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.domain.dto.UserAddGroupsDTO;
import com.ruoyi.czjg.zjrw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/6 12:04 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@RestController
@RequestMapping("/user")
@Api(value="登陆管理")
public class LoginController {


    @Autowired
    @Qualifier("loginUserService")
    private UserService userService;


    @PostMapping("/login")
    @ResponseBody
    @AuthIgnore
    @ApiOperation(value = "登录，支持单个用户对应多个组织机构（2022-03-08）")
    public ResponseBase login(@RequestBody SsFUsers user){
        return userService.login(user);
    }

    @PostMapping("/viewToken")
    @ResponseBody
    @ApiOperation(value="获取视频token，当视频token过期的时候请求这个接口")
    public ResponseBase viewToken() throws Exception {
        return userService.viewToken();
    }
    @PostMapping("/viewToken/new")
    @ResponseBody
    @ApiOperation(value="获取视频token，当视频token过期的时候请求这个接口")
    public ResponseBase viewNewToken() throws Exception {
        return userService.viewNewToken();
    }

    @PostMapping("/addGroups")
    @ResponseBody
    @ApiOperation(value = "用户增加工区权限")
    public ResponseBase userAddGroups(@RequestBody UserAddGroupsDTO addGroupsDTO){
        return userService.userAddGroups(addGroupsDTO);
    }
    @GetMapping("/userIdList/{projectId}")
    @ApiOperation(value = "getUserIDListByProjectId")
    public ResponseBase  getUserIDListByProjectId(@PathVariable String projectId){
        return userService.getUserIDListByProjectId(projectId);
    }

    @ResponseBody
    @PostMapping("/getGroups")
    @ApiOperation(value = "查询组织信息")
    public ResponseBase getGroups(@RequestParam(value = "userId", required = false) Integer userId){
        return userService.getGroups(userId);
    }

    @ResponseBody
    @PostMapping("/updatePwd")
    @ApiOperation(value = "修改密码")
    public ResponseBase updatePwd (@RequestBody SsFUsers user){
        return userService.updatePwd(user);
    }

    @ResponseBody
    @PostMapping("/updateOnline")
    @ApiOperation(value = "更新在线状态")
    public ResponseBase updateOnline(@RequestParam(value = "cid", required = false)String cid ,
                                     @RequestParam(value = "lon", required = false) String lon,
                                     @RequestParam(value = "lat", required = false) String lat){
        return userService.updateOnline(cid, lon, lat);
    }

    @ResponseBody
    @PostMapping("/getOnline")
    @ApiOperation(value = "获取在线人数（如果登录用户没有配置角色权限【4，5，6，7，16，17，18】-【监理领导，" +
            "施工领导，监理管理人员，施工管理人员，全咨领导，基础公司领导，建设集团领导】，则不会显示）")
    public ResponseBase getOnline(){
        return userService.getOnline();
    }

    @ResponseBody
    @PostMapping("/getOnlineCount")
    @ApiOperation(value = "/获取每天在线人数统计情况（仅管理员可见）")
    @ApiImplicitParam(name = "date", value = "日期(格式: yyyy-MM-dd)")
    public ResponseBase getOnlineCount(@RequestParam(value = "date", required = false)String date){
        return userService.getOnlineCount(date);
    }

    @ResponseBody
    @GetMapping("/exportOnlineCount")
    @ApiOperation(value = "把每天(或已选择的当天)在线人数统计情况导出")
    @ApiImplicitParam(name = "date", value = "日期(格式: yyyy-MM-dd)")
    public void exportOnlineCount(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "date", required = false)String date){
        userService.exportOnlineCount(request, response, date);
    }

    @ResponseBody
    @PostMapping("/getAllUserOnline")
    @ApiOperation(value = "获取所有用户的在线数据信息")
    public ResponseBase getAllUserOnline(){
        return userService.getAllUserOnline();
    }
}
