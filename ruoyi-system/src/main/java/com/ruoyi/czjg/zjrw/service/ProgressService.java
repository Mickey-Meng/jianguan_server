package com.ruoyi.czjg.zjrw.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.model.ItemCount;
import com.ruoyi.common.core.domain.entity.ZjConponentProducetime;
import com.ruoyi.common.core.domain.model.RightData;
import com.ruoyi.common.core.domain.entity.ZjYcdata;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.dao.ProjectsDAO;
import com.ruoyi.czjg.zjrw.dao.SsFGroupsDAO;
import com.ruoyi.czjg.zjrw.dao.SsFUserGroupDAO;
import com.ruoyi.czjg.zjrw.dao.ZjConponentProducetimeDAO;
import com.ruoyi.czjg.zjrw.domain.dto.*;
import com.ruoyi.czjg.zjrw.domain.entity.*;
import com.ruoyi.common.utils.JwtUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ruoyi.common.constant.Constant.zjYcdataMap;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 3:01 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class ProgressService {

    Logger log = LoggerFactory.getLogger(ProgressService.class);

    @Autowired
    private ZjConponentProducetimeDAO zjConponentProducetimeDAO;

    @Autowired
    private SsFGroupsDAO ssFGroupsDAO;
    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    private ProjectsDAO projectsDAO;

    public ResponseBase uploadProgress(ProgressData progressData) {
        Integer role = JwtUtil.getTokenUser().getRole();
        //当用户所属权限不为管理员时，不准传入实际开始时间和实际结束时间
        if (role != 2){
            if (progressData.getActulsttime() != null || progressData.getActulendtime() != null){
                return new ResponseBase(500, "计划填报失败！",
                        "该用户权限不够，不允许输入实际开始时间和实际结束时间！");
            }
        }

        //处理时间
        List<Integer> conponentids = progressData.getConponentids();
        conponentids.forEach(conponentid -> {
            ZjConponentProducetime zjByConponentId = zjConponentProducetimeDAO.getZjByConponentId(conponentid);
            if (!ObjectUtils.isEmpty(progressData.getActulendtime())) {
                zjByConponentId.setActulendtime(progressData.getActulendtime());
            }else{
                zjByConponentId.setActulendtime(null);
            }
            if (!ObjectUtils.isEmpty(progressData.getActulsttime())) {
                zjByConponentId.setActulsttime(progressData.getActulsttime());
            }else{
                zjByConponentId.setActulsttime(null);
            }
            if (!ObjectUtils.isEmpty(progressData.getPlanendtime())) {
                zjByConponentId.setPlanendtime(progressData.getPlanendtime());
            }else{
                zjByConponentId.setPlanendtime(null);
            }
            if (!ObjectUtils.isEmpty(progressData.getPlansttime())) {
                zjByConponentId.setPlansttime(progressData.getPlansttime());
            }else{
                zjByConponentId.setPlansttime(null);
            }

            zjConponentProducetimeDAO.updateTime(zjByConponentId);
//            zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjByConponentId);
        });

        return new ResponseBase(200, "批量修改构件计划或者实际完成时间成功");
    }

    public ResponseBase getStatus(final RightData rightData) {
        PowerData tokenUser = JwtUtil.getTokenUser();

        Integer userId = JwtUtil.getTokenUser().getId();
        List<String> projects = Lists.newArrayList();
        if (ObjectUtils.isEmpty(rightData.getProjectids())) {
            projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        } else {
            projects = rightData.getProjectids();
        }
        Long st = System.currentTimeMillis();
        //根据项目查询所有的数据在进行分区处理
        Map<String, List<CheckCount>> sy = Maps.newHashMap();
        if (projects.size() > 0) {
            List<StatusData> status = zjConponentProducetimeDAO.getStatus(projects, rightData.getSttime(), rightData.getEndtime());

            Map<String, List<StatusData>> collect = status.stream().collect(Collectors.groupingBy(StatusData::getProjecttype));

            collect.forEach((projecttye, typeItemList)->{
                List<CheckCount> checkCounts =Lists.newArrayList();
                for (StatusData statusData : typeItemList) {
                    CheckCount checkCount =new CheckCount();
                    checkCount.setTodayFinish(statusData.getPriod());  //3
                    checkCount.setCount(statusData.getCountall());   //1
                    checkCount.setDalay(statusData.getDelay());   //4
                    checkCount.setFinish(statusData.getFinish());   //2
                    checkCount.setName(statusData.getConponenttypename());
                    checkCount.setType(statusData.getConponenttype());   //5
                    checkCounts.add(checkCount);
                }
                Collections.sort(checkCounts);
                sy.put(projecttye,checkCounts);
            });
        }
        return new ResponseBase(200, "查询成功", sy);
    }

    public ResponseBase getMiddleData(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取用户所拥有的单位工程code
        Integer userId = JwtUtil.getTokenUser().getId();
        List<String> projects = ssFUserGroupDAO.getProjectsByUserId(userId);
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(projectId);
        //取到两个list 的交集
        List<String> intersectionList = Lists.newArrayList();
        if (projects.size() > 0){
            intersectionList = (List<String>) CollectionUtils.intersection(proChildCode, projects);
        }
        Map<String, ItemCount> itemMap = Maps.newHashMap();
        if (projects.size() > 0) {
            if (intersectionList.size() > 0){
                List<MiddleData> middleData = zjConponentProducetimeDAO.getMiddleData(intersectionList);
                for (MiddleData middle : middleData) {
                    ItemCount itemCount=new ItemCount();
                    itemCount.setCount(middle.getCountall());
                    itemCount.setDalay(middle.getDelay());
                    itemCount.setTodayFinish(middle.getToday());
                    itemCount.setFinish(middle.getFinish());
                    itemMap.put(middle.getProjecttype(),itemCount);
                }
            }
        }
        return new ResponseBase(200, "查询成功", itemMap);
    }

    public ResponseBase projectSelect() {
        Integer userId = JwtUtil.getTokenUser().getId();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        //获取了项目编号
        //项目对应的groups id 查询
        List<SsFGroups> zjFGroupsProjectsList = ssFGroupsDAO.getSsFGroups(projects);

        return new ResponseBase(200, "查询成功", zjFGroupsProjectsList);
    }

    public ResponseBase getYcData() {
        List<ZjYcdata> zjYcdataList = Lists.newArrayList();
        zjYcdataMap.forEach((key, zjYcdata) -> {
            zjYcdataList.add(zjYcdata);
        });
        return new ResponseBase(200, "查询成功", zjYcdataList);
    }

    public ResponseBase getConponentStatus(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(projectId);

        Integer userId = JwtUtil.getTokenUser().getId();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);

        List<ComponentProducetimeDTO> progressData = Lists.newArrayList();

        if (projects.size() <= 0){
            //当用户没有单位工程工区权限时,直接返回空数据
            return new ResponseBase(200, "查询成功!", progressData);
        }
        //取两个list的交集
        projects = projects.stream().distinct().collect(Collectors.toList());
        List<String> intersectionList = (List<String>) CollectionUtils.intersection(proChildCode, projects);
        progressData = zjConponentProducetimeDAO.getData(intersectionList);
        Date now = new Date();
        //执行循环
        progressData.stream().forEach(producetime -> {
            Date planEnd = producetime.getPlanendtime();
            Date actStart = producetime.getActulsttime();
            Date actEnd = producetime.getActulendtime();
            //当计划完成时间为空时, 只判断是否开工, 不判断延期
            if (planEnd == null){
                if (actStart == null){
                    //当没有实际开始时间和, 未开工
                    producetime.setStatus(0);
                } else if (actStart != null && actEnd == null){
                    //当有开始时间, 没有结束时间  施工中
                    producetime.setStatus(1);
                } else if (actStart != null && actEnd != null){
                    //当 两个实际时间都不为空  已完工
                    producetime.setStatus(2);
                }
            } else {
                //有计划完成时间   需要再判断是否延期
                if (actStart == null && actEnd == null){
                    if (now.compareTo(planEnd) > 0){
                        //系统时间大于计划完成时间  延期
                        producetime.setStatus(3);
                    } else{
                        //系统时间小于计划完成时间  未开工
                        producetime.setStatus(0);
                    }
                } else if (actStart != null && actEnd == null){
                    //开始时间不为空, 完成时间为空
                    if (now.compareTo(planEnd) > 0){
                        producetime.setStatus(3);
                    } else {
                        //系统时间小于计划完成时间  施工中
                        producetime.setStatus(1);
                    }
                } else if (actStart != null && actEnd != null){
                    //当有完成时间 则不用判断   已完工
                    producetime.setStatus(2);
                }
            }
        });
        return new ResponseBase(200,"查询成功", progressData);
    }

    public ResponseBase getByConponentid(Integer conid) {
        ZjConponentProducetime zjConponentProducetime = zjConponentProducetimeDAO.getZjByConponentId(conid);

        return  new ResponseBase(200,"查询成功",zjConponentProducetime);
    }
}




