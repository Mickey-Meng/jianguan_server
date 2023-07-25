package com.ruoyi.web.controller.jianguan.other.zjrw;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.jianguan.common.domain.dto.AskEnvPerData;
import com.ruoyi.jianguan.common.domain.dto.Census;
import com.ruoyi.jianguan.common.domain.dto.NewCheckData;
import com.ruoyi.common.core.domain.model.SafePerData;
import com.ruoyi.jianguan.common.service.CountService;
import com.ruoyi.jianguan.common.service.ProjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/19 1:53 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("/count")
@Api(value="首页统计数据")
public class CountController {

    @Autowired
    CountService countService;

    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/getProjectDetail")
    @ResponseBody
    @ApiOperation(value="获取项目信息工程规模，合同工期，投资规模以及关联单位")
    public ResponseBase getProjectDetail(@RequestParam(value ="projectId",required = false) Integer projectId){
        setProjectId(projectId);
        return countService.getProjectDetail(projectId);
    }

    private void setProjectId(Integer projectId) {
        log.info("ComponentController.setProjectId.projectId: {}", projectId);
        if(ObjectUtil.isNull(projectId)) {
            return;
        }
        ResponseBase responseBase = projectsService.getProjectInfoById(projectId);
        log.info("ComponentController.setProjectId.responseBase: {}", responseBase);
        if(ObjectUtil.isNull(responseBase) || ObjectUtil.isNull(responseBase.getData())) {
            return;
        }
        Map data = (Map)responseBase.getData();
        SsFProjects project = (SsFProjects)data.get("project");
        log.info("ComponentController.setProjectId.project: {}", project);
        if(ObjectUtil.isNull(project) || ObjectUtil.isNull(project.getParentid())) {
            return;
        }
        RedisUtils.setCacheObject(LoginHelper.getUserId()+".projectId", String.valueOf(project.getParentid()));

        Object cacheObject = RedisUtils.getCacheObject(LoginHelper.getUserId() + ".projectId");

        log.info("ComponentController.setProjectId.cacheObject: {}", (String)cacheObject);
    }

    @PostMapping("/getPerMonthSafeData")
    @ResponseBody
    @ApiOperation(value="获取每月安全事件数据--表格左侧的工区每日状态")
    public ResponseBase getPerMonthSafeData(@RequestBody SafePerData safePerData){
        try {
            return countService.getPerMonthSafeData(safePerData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  new ResponseBase(500,"时间填错");
    }

    @GetMapping("/getPerSafeData")
    @ResponseBody
    @ApiOperation(value="获取周月履职情况")
    public ResponseBase getPerSafeData(@RequestParam(value ="askTime",required = false) String askTime){
        List<String> items = Arrays.asList(askTime.split("-"));
        if(2<=items.size()&&items.size()<=3){
            try {
                return countService.getPerSafeData(askTime,items.size());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return  new ResponseBase(500,"时间填错");
    }

    @GetMapping("/getDayData")
    @ResponseBody
    @ApiOperation(value="获取某一天的安全事件")
    public ResponseBase getDayData(@RequestParam(value ="askTime",required = false) String askTime){
        List<String> items = Arrays.asList(askTime.split("-"));
        if(2<=items.size()&&items.size()<=3){
            try {
                return countService.getDayData(askTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return  new ResponseBase(500,"时间填错");
    }


    @GetMapping("/getByFirstType")
    @ResponseBody
    @ApiOperation(value="安全事件分类汇总")
    public ResponseBase getByFirstType(){
        return countService.getByFirstType();
    }

    @PostMapping("/getSafeIds")
    @ResponseBody
    @ApiOperation(value="根据安全事件ids查询安全事件")
    public ResponseBase getSafeIds(@RequestBody List<Integer> ids){
        return countService.getSafeIds(ids);
    }

    @PostMapping("/getFinishConponent")
    @ResponseBody
    @ApiOperation(value="构件完成数量--左右上图数据，还有方量数量")
    public ResponseBase getFinishConponent(@RequestBody SafePerData safePerData){

        try {
            return countService.getFinishConponent(safePerData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseBase(500,"时间参数有问题");
    }

    @GetMapping("/getGqFirst")
    @ResponseBody
    @ApiOperation(value="工区统计数据")
    public ResponseBase getGqFirst(@RequestParam(value = "type", required = false)String type,
                                   @RequestParam(value = "projectId", required = false) Integer projectId){
        return countService.getGqFirst(type, projectId);
    }

    @GetMapping("/getpjFirst")
    @ResponseBody
    @ApiOperation(value="工区统计数据")
    public ResponseBase getpjFirst(@RequestParam("projectcode") String projectcode,
                                   @RequestParam(value = "projectId", required = false)Integer projectId){
        return countService.getpjFirst(projectcode, projectId);
    }

    @GetMapping("/getProject")
    @ResponseBody
    @ApiOperation(value="获取项目下的构件的类型分类")
    public ResponseBase getProject(@RequestParam(value = "projectId", required = false) Integer projectId){
        return countService.getProject(projectId);
    }

    @GetMapping("/getNewType")
    @ResponseBody
    @ApiOperation(value="获取项目下的构件的类型分类")
    public ResponseBase getNewType(@RequestParam(value = "projectId", required = false) Integer projectId){
        return countService.getNewType(projectId);
    }

    @PostMapping("/getTypeData")
    @ResponseBody
    @ApiOperation(value="获取表格数据")
    public ResponseBase getTypeData(@RequestBody NewCheckData newCheckData){
        return countService.getTypeData(newCheckData);
    }

    @PostMapping("/getCountConponent")
    @ResponseBody
    @ApiOperation(value="构件完成数量按照月，按照日，按照季度--左下图数据")
    public ResponseBase getCountConponent(@RequestBody Census census){
        if (census.getProjectId() == null || census.getProjectId().equals("")){
            census.setProjectId(3);
        }
        return countService.getCountConponent(census);
    }

    @PostMapping("/getCountIncresConponent")
    @ResponseBody
    @ApiOperation(value="构件完成数量按照月，按照日，按照季度--右下图数据")
    public ResponseBase getCountIncresConponent(@RequestBody Census census){
        return countService.getCountIncresConponent(census);
    }

    @GetMapping("/getCountIncresConponentGroupGq")
    @ResponseBody
    @ApiOperation(value="构件按照工区获取每月的完成情况(工区：group（4，5，6，7），类型：type（QL || SD || LM || other）)")
    public ResponseBase getCountIncresConponent(@RequestParam(value = "group", required = false)Integer group,
                                                @RequestParam(value = "type", required = false)String type,
                                                @RequestParam(value = "projectId", required = false) Integer projectId){
        return countService.getCountIncresConponentGroupGq(type, group, projectId);
    }

    @GetMapping("/getEnvPerMonth")
    @ResponseBody
    @ApiOperation(value="获取每月环境统计数据")
    public ResponseBase getEnvPerMonth() throws ParseException {
        return countService.getEnvPerMonth();
    }

    @PostMapping("/getDayTrend")
    @ResponseBody
    @ApiOperation(value="每日环境趋势变化图（可传时间）")
    public ResponseBase getDayTrend(@RequestBody AskEnvPerData askEnvPerData) throws ParseException {
        return countService.getDayTrend(askEnvPerData);
    }

    @GetMapping("/getExceedData")
    @ResponseBody
    @ApiOperation(value="每日环境趋势变化图")
    public ResponseBase getExceedData() {
        return countService.getExceedData();
    }

    @GetMapping("/getQiaoData")
    @ResponseBody
    @ApiOperation(value="获取桥数据")
    public ResponseBase getQiaoData() {
        return countService.getQiaoData();
    }

    @GetMapping("/getData")
    @ResponseBody
    @ApiOperation(value="获取每个工程的项目构件类型")
    public ResponseBase getData(@RequestParam(value ="type",required = false) String type) throws ParseException {
        if("QL".equals(type)||"LM".equals(type)||"SD".equals(type)){
            return countService.getData(type);
        }
        return new ResponseBase(505,"暂时只支持：桥梁、隧道、道路！");
    }

    //获取工作面信息
    @GetMapping("/getOperationData")
    @ResponseBody
    @ApiOperation(value="获取工作面信息")
    public ResponseBase getOperationData() throws ParseException {
//        if("QL".equals(type)||"DL".equals(type)||"DD".equals(type)){
//            return countService.getData(type);
//        }
//        return new ResponseBase(500,"参数错误");
        return countService.getOperationData();
    }

    @ResponseBody
    @PostMapping("/getTodayWeather")
    @ApiOperation(value="获取当天天气情况")
    public ResponseBase getTodayWeather(){
        return countService.getTodayWeather();
    }

    @ResponseBody
    @PostMapping("/getTodayClockOut")
    @ApiOperation(value = "获取今日考勤统计")
    public ResponseBase getTodayClockOut(@RequestParam(value = "projectId")Integer id){
        return countService.getTodayClockOut(id);
    }

    @ResponseBody
    @PostMapping("/getPeopleProportion")
    @ApiOperation(value = "统计人员占比")
    public ResponseBase getPeopleProportion(@RequestParam(value = "projectId")Integer id){
        return countService.getPeopleProportion(id);
    }

    @ResponseBody
    @PostMapping("/getOnDutyOrNotCount")
    @ApiOperation(value = "获取在岗/不在岗统计(只统计 施工,监理,全资)")
    public ResponseBase getOnDutyOrNotCount(@RequestParam(value = "projectId")Integer id){
        return countService.getOnDutyOrNotCount(id);
    }

    @ResponseBody
    @PostMapping("/getAllClockOut")
    @ApiOperation(value = "获取考勤统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目(标段)id"),
            @ApiImplicitParam(name = "unitType", value = "单位: 1-施工, 2-监理, 3-全资"),
            @ApiImplicitParam(name = "type", value = "考勤状态:1-已打卡, 2-未打卡, 3-请休假"),
            @ApiImplicitParam(name = "date", value = "按月查询时的时间(固定格式: yyyy-MM)"),
    })
    public ResponseBase getAllClockOut(@RequestBody Map<String, Object> data){
        return countService.getAllClock(data);
    }

    @ResponseBody
    @GetMapping("/getAllClockOutExport")
    @ApiOperation(value = "导出考勤统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目(标段)id"),
            @ApiImplicitParam(name = "unitType", value = "单位: 1-施工, 2-监理, 3-全资"),
            @ApiImplicitParam(name = "type", value = "考勤状态:1-已打卡, 2-未打卡, 3-请休假"),
            @ApiImplicitParam(name = "date", value = "按月查询时的时间(固定格式: yyyy-MM)")
    })
    public void doExportAllClock(@RequestParam(value = "projectId",required = true) Integer projectId,
                                 @RequestParam(value = "unitType", required = false) Integer unitType,
                                 @RequestParam(value = "type", required = false)Integer type,
                                 @RequestParam(value = "date", required = false)String date,
                                 HttpServletRequest request, HttpServletResponse response){
        countService.doExportAllClock(projectId,unitType, type, date, request, response);
    }
}
