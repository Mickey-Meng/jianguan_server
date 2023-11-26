package com.ruoyi.jianguan.common.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.entity.ClockInCensusReturn;
import com.ruoyi.common.core.domain.entity.*;
import com.ruoyi.common.core.domain.model.SafePerData;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.jianguan.common.dao.*;
import com.ruoyi.jianguan.common.domain.dto.*;
import com.ruoyi.jianguan.common.domain.entity.*;

import com.ruoyi.jianguan.common.domain.entity.weather.WeatherNow;
import com.ruoyi.common.utils.jianguan.zjrw.DateUtils;
import com.ruoyi.common.utils.jianguan.zjrw.ListUtils;
import com.ruoyi.common.utils.jianguan.zjrw.MyExcelUtil;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysOssMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/19 1:56 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class CountService {
    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ZjSafeEventDAO zjSafeEventDAO;

    @Autowired
    private ZjYcexceeddataDAO zjYcexceeddataDAO;

    @Autowired
    private ZjYcperdayDAO zjYcperdayDAO;

    @Autowired
    private ZjYcdataDAO zjYcdataDAO;

    @Autowired
    private ZjConponentProducetimeDAO zjConponentProducetimeDao;

    @Autowired
    @Qualifier("zjFGroupsProjectsDAO")
    private ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;

    @Autowired
    private SsFGroupsDAO ssFGroupsDAO;

    @Autowired
    private ConponentDAO conponentDAO;
    @Autowired
    private ProjectsDAO projectsDAO;
    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    private ZjPersonClockinDAO clockInDAO;
    @Autowired
    private ZjPersonLeaveDAO leaveDAO;
    @Autowired
    private SsFUserRoleDAO userRoleDAO;
    @Autowired
    private SsFUsersDAO usersDAO;

    @Autowired
    private WeatherDAO weatherDAO;

    @Autowired
    private SysOssMapper ossMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;


    public ResponseBase getProjectDetail(Integer projectId) {
        Item projectDetail = itemDAO.selectByPrimaryKey(projectId);
        if(null != projectDetail){
            SysOssVo sysOssVo = ossMapper.selectVoById(projectDetail.getEngineeringLayoutImageUrl());
            if(sysOssVo != null){
                projectDetail.setEngineeringLayoutImageUrl(sysOssVo.getUrl());
            }
        }
        return new ResponseBase(200, "查询成功", projectDetail);

    }

    public ResponseBase getProjectProgress() {

        return null;
    }

    public ResponseBase getPerMonthSafeData(SafePerData safePerData) throws ParseException {
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);

        //设置safePerData 的开始与结束时间
        safePerData = DateUtils.getStAndEndByMonth(safePerData);

        //根据 项目权限数据查询
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getDataByProjectIds(gongqus, safePerData.getSttime(), safePerData.getEndtime());

        zjSafeEventList = changeDayFormat(zjSafeEventList);
        Map<Integer, List<ZjSafeEvent>> gongquItem =
                zjSafeEventList.stream().collect(Collectors.groupingBy(ZjSafeEvent::getGongquid));
        List<SafeCount> safe = Lists.newArrayList();
        //工区分组
        gongquItem.forEach((gongquid, zjSafeEvents) -> {
            //上传日期分组
            SafeCount safeCount = new SafeCount();
            safeCount.setGongqu(getGongquName(gongquid));
            List<PerDayDetail> dayDatas = Lists.newArrayList();
            Map<Date, List<ZjSafeEvent>> uploadItem =
                    zjSafeEvents.stream().collect(Collectors.groupingBy(ZjSafeEvent::getUploadtime));
            uploadItem.forEach(((date, zjSafeEventsPerDays) -> {
                PerDayDetail perDayDetail = new PerDayDetail();
                perDayDetail.setUploadTime(date);
                List<Integer> reconds = Lists.newArrayList();
                zjSafeEventsPerDays.stream().forEach((zjSafeEvent) -> {
                    reconds.add(zjSafeEvent.getId());
                });
                perDayDetail.setReconds(reconds);
                dayDatas.add(perDayDetail);
            }));
            safeCount.setDayDatas(dayDatas);
            safe.add(safeCount);
        });
        return new ResponseBase(200, "查询成功", safe);
    }

    private List<ZjSafeEvent> changeDayFormat(List<ZjSafeEvent> zjSafeEventList) {
        zjSafeEventList.stream().forEach((zjSafeEvent -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(zjSafeEvent.getUploadtime());
            try {
                Date date = sdf.parse(s);
                zjSafeEvent.setUploadtime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }));
        return zjSafeEventList;
    }

    private String getGongquName(Integer gongquid) {
        if (3 == gongquid.intValue()) {
            return "工区一";
        } else if (4 == gongquid.intValue()) {
            return "工区二";
        } else if (5 == gongquid.intValue()) {
            return "工区三";
        } else if (6 == gongquid.intValue()) {
            return "工区四";
        } else {
            return "未定义工区";
        }
    }

    public ResponseBase getPerSafeData(String askTime, int size) throws ParseException {
        WeekAndMonthCount weekAndMonthCount = new WeekAndMonthCount();
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        //固定返回4个数据
        List<SafePerData> list = DateUtils.getFourDateByType(askTime, size);
        //查询每周数据
        List<WeekData> weekDataList = Lists.newArrayList();
        list.stream().forEach(safePerData -> {
            WeekData weekData = new WeekData();
            String peroid = getPeroid(safePerData);
            weekData.setPeroid(peroid);
            weekData.setCount(7);
            List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.
                    getDataByProjectIds(gongqus, safePerData.getSttime(), safePerData.getEndtime());
            //查询到修改时间类型数据
            zjSafeEventList = changeDayFormat(zjSafeEventList);
            //每天的数据
            Map<Date, List<ZjSafeEvent>> daysData = zjSafeEventList.stream().
                    collect(Collectors.groupingBy(ZjSafeEvent::getUploadtime));
            weekData.setPerformance(daysData.size());
            //每周数据整理出来了
            weekDataList.add(weekData);
        });
        //再处理每月数据
        WeekData monthDate = new WeekData();
        SafePerData safePerData = new SafePerData();
        Date dateByStr = DateUtils.getDateByStr(askTime);
        safePerData.setAskTime(dateByStr);
        safePerData = DateUtils.getStAndEndByMonth(safePerData);
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getDataByProjectIds(gongqus, safePerData.getSttime(), safePerData.getEndtime());
        zjSafeEventList = changeDayFormat(zjSafeEventList);
        Map<Date, List<ZjSafeEvent>> monthData = zjSafeEventList.stream().
                collect(Collectors.groupingBy(ZjSafeEvent::getUploadtime));
        List<String> times = Arrays.asList(askTime.split("-"));
        monthDate.setPeroid(times.get(0) + times.get(1));
        int count = DateUtils.day(Integer.parseInt(times.get(1)), Integer.parseInt(times.get(0)));
        monthDate.setCount(count);
        monthDate.setPerformance(monthData.size());
        weekAndMonthCount.setWeeks(weekDataList);
        weekAndMonthCount.setMonth(monthDate);
        return new ResponseBase(200, "查询成功", weekAndMonthCount);
    }

    private String getPeroid(SafePerData safePerData) {
        String st = DateUtils.getDateByDate(safePerData.getSttime());
        String end = DateUtils.getDateByDate(safePerData.getEndtime());
        return st + "_" + end;
    }

    public ResponseBase getDayData(String askTime) throws ParseException {
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        //获取当前日期最早事件
        Date stTime = DateUtils.getEarlyDate(askTime);
        Date endTime = DateUtils.getlateDate(askTime);
        SafePerData safePerData = new SafePerData();
        safePerData.setSttime(stTime);
        safePerData.setEndtime(endTime);
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getDataByProjectIds(gongqus, safePerData.getSttime(), safePerData.getEndtime());
        //获取当前日期最晚时间
        return new ResponseBase(200, "查询成功", zjSafeEventList);
    }

    public ResponseBase getByFirstType() {
        List<SafeCountByType> safeCountByTypes = Lists.newArrayList();
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
//        List<ZjSafeEvent> zjSafeEventList =zjSafeEventDAO.getDataByProjectId(gongqus);

        List<SafeCheckData> safeCheckData = zjSafeEventDAO.getSafeCheckData(gongqus);

        for (SafeCheckData safeCheckDatum : safeCheckData) {
            SafeCountByType safeCountByType = new SafeCountByType();
            safeCountByType.setName(safeCheckDatum.getCheckFirstName());
            safeCountByType.setCount(safeCheckDatum.getCountall());
            safeCountByType.setNodo(safeCheckDatum.getNodo());
            safeCountByType.setNodelay(safeCheckDatum.getNodelay());
            safeCountByType.setCheck(safeCheckDatum.getFinish());
            safeCountByType.setNocheck(safeCheckDatum.getNocheck());
            safeCountByTypes.add(safeCountByType);
        }
//        Map<String, List<ZjSafeEvent>> firstItem = zjSafeEventList.stream().
//                collect(Collectors.groupingBy(ZjSafeEvent::getSafefirstname));
        //按照大类 已经分类了
//        firstItem.forEach((typeName,zjSafeEvents)->{
//            SafeCountByType safeCountByType = new SafeCountByType();
//            List<ZjSafeEvent> nodo = zjSafeEvents.stream().filter(
//                    zjSafeEvent -> zjSafeEvent.getStatus() == 0).collect(Collectors.toList());
//            List<ZjSafeEvent> nodelay = zjSafeEvents.stream().filter(
//                    zjSafeEvent -> zjSafeEvent.getStatus() == 1).collect(Collectors.toList());
//            List<ZjSafeEvent> nocheck = zjSafeEvents.stream().filter(
//                    zjSafeEvent -> zjSafeEvent.getStatus() == 2).collect(Collectors.toList());
//            List<ZjSafeEvent> check = zjSafeEvents.stream().filter(
//                    zjSafeEvent -> zjSafeEvent.getStatus() == 3).collect(Collectors.toList());
//            safeCountByType.setName(typeName);
//            safeCountByType.setCount(zjSafeEvents.size());
//            safeCountByType.setNodo(nodo.size());
//            safeCountByType.setNodelay(nodelay.size());
//            safeCountByType.setNocheck(nocheck.size());
//            safeCountByType.setCheck(check.size());
//            safeCountByTypes.add(safeCountByType);
//        });
        return new ResponseBase(200, "查询成功", safeCountByTypes);
    }

    public ResponseBase getSafeIds(List<Integer> ids) {
        List<ZjSafeEvent> zjSafeEvents = zjSafeEventDAO.getDataById(ids);
        return new ResponseBase(200, "查询成功", zjSafeEvents);
    }

    public ResponseBase getFinishConponent(SafePerData safePerData) throws ParseException {
        if (safePerData.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(safePerData.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }

        Map<String, CountData> map = Maps.newHashMap();
//        if (safePerData.getProjectId() == 3) {
            //获取当前用户的项目权限
            Integer userId = LoginHelper.getUserId().intValue();
            List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserIdAndProjectId(userId,safePerData.getProjectId());
            //截止到今天所有实际完成 今天的时间
            List<ZjConponentProducetime> actureData = Lists.newArrayList();
            //截止到今天为止所有计划完成
            List<ZjConponentProducetime> planData = Lists.newArrayList();
            Integer countNumber = 0;
            //当项目id有值时
            if (projects.size() > 0) {
                projects = projects.stream().distinct().collect(Collectors.toList());
                countNumber = zjConponentProducetimeDao.getCountNumber(projects);
                // 当前端type传值为空时，默认查询QL数据
                String type = safePerData.getType();
                if (type == null) {
                    type = "LM";
                }
                if (ObjectUtils.isEmpty(safePerData.getSttime()) || ObjectUtils.isEmpty(safePerData.getEndtime())) {
                    //没有开始与结束时间
                    Date date = new Date();
                    //修改  2021-11-11
                    List<PlanDataNew> planDataNew = zjConponentProducetimeDao.getPlan(projects, type);
                    List<ActDataNew> actDataNews = zjConponentProducetimeDao.getAct(projects, type);
                    //计划完成
                    int planAllSum = planDataNew.stream().mapToInt(PlanDataNew::getAllcount).sum();
                    //总数
                    int Sum = planDataNew.stream().mapToInt(PlanDataNew::getPlan).sum();
                    //实际完成总数
                    int actAllSum = actDataNews.stream().mapToInt(ActDataNew::getAct).sum();

                    Map<String, List<PlanDataNew>> plan = planDataNew.stream().
                            collect(Collectors.groupingBy(PlanDataNew::getProjecttype));
                    Map<String, List<ConponentCountData>> planmap = Maps.newHashMap();
                    plan.forEach((projectType, plans) -> {
                        List<ConponentCountData> planlist = Lists.newArrayList();
                        for (PlanDataNew dataNew : plans) {
                            ConponentCountData co = new ConponentCountData();
                            co.setFang(dataNew.getPlannum());
                            co.setName(dataNew.getConponenttypename());
                            co.setCount(dataNew.getPlan());
                            planlist.add(co);
                        }
                        planmap.put(projectType, planlist);
                    });

                    Map<String, List<ConponentCountData>> actmap = Maps.newHashMap();
                    Map<String, List<ActDataNew>> act = actDataNews.stream().
                            collect(Collectors.groupingBy(ActDataNew::getProjecttype));
                    act.forEach((projectType, acts) -> {
                        List<ConponentCountData> actlist = Lists.newArrayList();
                        for (ActDataNew dataNew : acts) {
                            ConponentCountData co = new ConponentCountData();
                            co.setFang(dataNew.getActnum());
                            co.setName(dataNew.getConponenttypename());
                            co.setCount(dataNew.getAct());
                            actlist.add(co);
                        }
                        actmap.put(projectType, actlist);
                    });

                    map.put("act", new CountData(planAllSum, actAllSum, actmap));
                    map.put("plan", new CountData(planAllSum, actAllSum, planmap));
                    return new ResponseBase(200, "查询成功", map);
                } else {
                    //有开始与结束时间
                    Date stDate = DateUtils.getStCheckDate(safePerData.getSttime());
                    Date endDate = DateUtils.getEndCheckDate(safePerData.getSttime());
                    actureData = zjConponentProducetimeDao.getActureData(projects, stDate, endDate, type);
                    planData = zjConponentProducetimeDao.getPlantureData(projects, stDate, endDate, type);
                }
                //实际
                Map<String, List<ConponentCountData>> actureMapData = getMapData(actureData);
                Map<String, List<ConponentCountData>> planMapData = getMapData(planData);
                CountData countDataAct = new CountData(countNumber, actureData.size(), actureMapData);
                CountData countDataPlan = new CountData(countNumber, planData.size(), planMapData);

                map.put("act", countDataAct);
                map.put("plan", countDataPlan);
            }
//        }
        return new ResponseBase(200, "查询成功", map);
    }

    private Map<String, List<ConponentCountData>> getMapData(List<ZjConponentProducetime> lists) {
        //先处理 实际完成数据   实际完成
        Map<String, List<ZjConponentProducetime>> projectTypeItems = lists.stream().
                collect(Collectors.groupingBy(ZjConponentProducetime::getProjecttype));
        Map<String, List<ConponentCountData>> mapData = Maps.newHashMap();
        projectTypeItems.forEach((type, projectTypeItem) -> {
            // 桥梁 ，隧道，道路
//            String name = getProjrctTypeName(type);
            String name = type;
            List<ConponentCountData> typeList = Lists.newArrayList();
            //构件类型
            // 桥梁项目的多个构件类型
            Map<String, List<ZjConponentProducetime>> conponentTypeItems =
                    projectTypeItem.stream().collect(Collectors.groupingBy(ZjConponentProducetime::getConponenttypename));
            //对每个构件类型进行查询
            conponentTypeItems.forEach((conpnentName, zjConponentProducetimeList) -> {
                //真正的数据对象
                ConponentCountData conponentCountData = new ConponentCountData();
                AtomicReference<Float> count = new AtomicReference<>(0f);

                zjConponentProducetimeList.stream().forEach(zjConponentProducetime -> {
                    if (!ObjectUtils.isEmpty(zjConponentProducetime.getDesginnum())) {
                        count.set(count.get() + zjConponentProducetime.getDesginnum());
                    }

                });
                conponentCountData.setName(conpnentName);
                conponentCountData.setCount(zjConponentProducetimeList.size());
                conponentCountData.setFang(count.get());
                typeList.add(conponentCountData);
            });
            mapData.put(name, typeList);
        });
        return mapData;
    }


    private String getProjrctTypeName(String type) {
        return "QL".equals(type) ? "桥梁" : "DD".equals(type) ? "隧道" : "道路";
    }

    public ResponseBase getType() {

        NodeTree nodeTree = new NodeTree();


//        nodeTree.setName("构件类型树");
//        nodeTree.setId(1);
//        List<NodeTree> head = Lists.newArrayList();
//        Map<String, List<TypeProject>> collect = typeProjectList.stream().
//                collect(Collectors.groupingBy(TypeProject::getProjecttype));
//        collect.forEach((type,typeProjects)->{
//            NodeTree first = new NodeTree();
//            first.setCode(type);
//            first.setName(getProjrctTypeName(type));
//            List<NodeTree> nodeTreeList = Lists.newArrayList();
//            typeProjects.stream().forEach(typeProject -> {
//                NodeTree second = new NodeTree();
//                second.setCode(typeProject.getConponenttype());
//                second.setName(typeProject.getConponenttypename());
//                nodeTreeList.add(second);
//            });
//            first.setChild(nodeTreeList);
//            head.add(first);
//        });
//        nodeTree.setChild(head);
        return new ResponseBase(200, "查询成功", nodeTree);
    }

    public ResponseBase getCountConponent(Census census) {
        if (census.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(census.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<NewCensusData> list = getCountService(census);

        return new ResponseBase(200, "查询成功", list);
    }


    private List<NewCensusData> getCountService(Census census) {
        //获取前端传的项目id
        List<String> proChildCode = projectsDAO.getChildCode(census.getProjectId());
        //获取用户所拥有的单位工程权限
        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        //取到两个集合的交集
        List<String> intersectionList =
                (List<String>) CollectionUtils.intersection(proChildCode, projects);
        List<NewCensusData> all = new ArrayList<>();

        String projectCode = census.getProjectCode();
        if (projectCode == null || projectCode.equals("")) {
            projectCode = "QL";
        }
        if (intersectionList.size() > 0) {
            if ("week".equals(census.getType())) {
                all = zjConponentProducetimeDao.getTypeWeek(census.getConponentType().equalsIgnoreCase("all") ? null : census.getConponentType(), intersectionList, projectCode);
                all.stream().forEach((newCensusData -> {
                    newCensusData.setName(DateUtils.setWeekName(newCensusData.getYear(), newCensusData.getType()));
                }));
            } else if ("month".equals(census.getType())) {
                all = zjConponentProducetimeDao.getTypeMonth(census.getConponentType().equalsIgnoreCase("all") ? null : census.getConponentType(), intersectionList, projectCode);
                all.stream().forEach((newCensusData -> {
                    newCensusData.setName(DateUtils.setMonthName(newCensusData.getYear(), newCensusData.getType()));
                }));
            } else if ("season".equals(census.getType())) {
                all = zjConponentProducetimeDao.getTypeSeason(census.getConponentType().equalsIgnoreCase("all") ? null : census.getConponentType(), intersectionList, projectCode);
                all.stream().forEach((newCensusData -> {
                    newCensusData.setName(DateUtils.setSeasonName(newCensusData.getYear(), newCensusData.getType()));
                }));
            }
        }
        return all;
    }


    public ResponseBase getCountIncresConponent(Census census) {
        if (census.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(census.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }

        List<NewCensusData> list = getCountService(census);

        int addCount = 0;
        for (NewCensusData newCensusData : list) {
            int add = newCensusData.getCount();
            newCensusData.setCount(add + addCount);
            addCount = addCount + add;
        }

        return new ResponseBase(200, "查询成功", list);
    }

    public ResponseBase getEnvPerMonth() throws ParseException {
        SafePerData safePerData = DateUtils.getMonthStAndEnd(new Date());
        List<ZjYcperday> zjYcperdays = zjYcperdayDAO.
                searchByStAndEnd(safePerData.getSttime(), safePerData.getEndtime());
        //根据code
        Map<String, List<ZjYcperday>> collect = zjYcperdays.stream().
                collect(Collectors.groupingBy(ZjYcperday::getCode));
//        Map<String,List<EnvPerMonthData>> data = Maps.newHashMap();
        Map<String, List<EnvPerMonthData>> data = Maps.newHashMap();
        //根据code 来分类
        collect.forEach((type, monthData) -> {
            List<EnvPerMonthData> list = Lists.newArrayList();
            Map<String, List<ZjYcperday>> collect1 = monthData.stream().
                    collect(Collectors.groupingBy(ZjYcperday::getType));
            //通过code获取到addr
            String address = zjYcperdayDAO.getAddrByCode(type);
            //根据 3个维度
            collect1.forEach((item, items) -> {
                EnvPerMonthData envPerMonthData = new EnvPerMonthData();
                envPerMonthData.setType(item);
                envPerMonthData.setCount(items.size());
                Map<Integer, List<ZjYcperday>> collect2 = items.stream().
                        collect(Collectors.groupingBy(ZjYcperday::getStatus));
                collect2.forEach((key, value) -> {
                    if (1 == key) {
                        envPerMonthData.setOne(value.size());
                    } else if (2 == key) {
                        envPerMonthData.setTwo(value.size());
                    } else if (3 == key) {
                        envPerMonthData.setThree(value.size());
                    } else if (4 == key) {
                        envPerMonthData.setFour(value.size());
                    } else if (5 == key) {
                        envPerMonthData.setFive(value.size());
                    } else if (6 == key) {
                        envPerMonthData.setSix(value.size());
                    }
                });
                list.add(envPerMonthData);
            });
            data.put(address, list);
        });
        return new ResponseBase(200, "查询成功", data);
    }

    public ResponseBase getDayTrend(AskEnvPerData askEnvPerData) throws ParseException {
        Date date = new Date();
        if (askEnvPerData.getAskTime() != null && !askEnvPerData.getAskTime().equals("")) {
            date = askEnvPerData.getAskTime();
        }
        //当code为空时，默认code为  D3-00001   草塔互通扬尘
        if (askEnvPerData.getCode() == null) {
            askEnvPerData.setCode("D3-00001");
        }
        //获取每天的最大时间与最小时间
        SafePerData safePerData = DateUtils.getDayStAndEnd(date);
        List<YcDataDTO> zjYcdata = zjYcdataDAO.searchByStAndEndAndCode(
                askEnvPerData.getCode(), safePerData.getSttime(), safePerData.getEndtime());
        if (zjYcdata.size() == 0) {
            return new ResponseBase(200, "当前没有数据，请过两小时后再进行尝试！");
        }
        Map<String, List<YcDataDTO>> collect =
                zjYcdata.stream().collect(Collectors.groupingBy(YcDataDTO::getCode));

        return new ResponseBase(200, "查询成功", collect);
    }


    public ResponseBase getExceedData() {
        List<ZjYcexceeddata> zjYcexceeddatas = zjYcexceeddataDAO.getAll();
        return new ResponseBase(200, "查询成功", zjYcexceeddatas);
    }

    public ResponseBase getQiaoData() {
        List<ZjFGroupsProjects> list = zjFGroupsProjectsDAO.getAll();
        return new ResponseBase(200, "查询成功", list);
    }

    public ResponseBase getData(String type) {
        List<TypeNameAndCode> list = zjFGroupsProjectsDAO.getTypeNameAndCode(type);
        return new ResponseBase(200, "查询成功", list);
    }

    public ResponseBase getOperationData() {
        //根据用户查项目
        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        List<OperationDataOffer> data = Lists.newArrayList();
        //查每个项目项目工作面
        projects.stream().forEach((projectcode) -> {
            Conponent operation = conponentDAO.getOperation(projectcode);
            //在查询完成情况
            if (!ObjectUtils.isEmpty(operation)) {
                //根据工作面查数据
                OperationDataOffer operationDataOffer = new OperationDataOffer();
                operationDataOffer.setProjectCode(projectcode);
                operationDataOffer.setProjectName(operation.getW3());
                operationDataOffer.setX(operation.getW9code());
                operationDataOffer.setY(operation.getW10());
                operationDataOffer.setZ(operation.getW10code());
                OperationData opeationData = conponentDAO.getOpeationData(projectcode);
                operationDataOffer.setAll(opeationData.getCountall());
                operationDataOffer.setFinish(opeationData.getCountall() - opeationData.getEndact());
                operationDataOffer.setDoing(opeationData.getEndact() - opeationData.getStartcat());
                data.add(operationDataOffer);
            }
        });
        return new ResponseBase(200, "查询成功", data);

        //查每个项目的构件总数和完成情况

    }

    public ResponseBase getGqFirst(String type, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(projectId);

//        PowerData tokenUser = JwtUtil.getTokenUser();
        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        if (projects.size() == 0 || projects == null) {
            return new ResponseBase(500, "该用户没有组织权限！");
        }
        projects = projects.stream().distinct().collect(Collectors.toList());

        //取到两个集合的交集
        List<String> intersectionList =
                (List<String>) CollectionUtils.intersection(proChildCode, projects);

        //当type为空时，默认桥梁
        if (type == null || type.equals("")) {
            type = "QL";
        }
        List<GqFirst> list = new ArrayList<>();
        if (intersectionList.size() > 0) {
            List<GqFirst> GqFirsts = zjConponentProducetimeDao.getAllByProJect(intersectionList, type);

            Map<String, List<GqFirst>> collect = GqFirsts.stream().collect(Collectors.groupingBy(GqFirst::gongqu));
            collect.forEach((key, value) -> {
                GqFirst gqFirst = new GqFirst();
                String[] s = key.split("_");
                gqFirst.setList(value);
                gqFirst.setGongqucode(s[0]);
                gqFirst.setGongquname(s[1]);
                gqFirst.setTotal(value.stream().mapToInt(GqFirst::getTotal).sum());
                gqFirst.setFinish(value.stream().mapToInt(GqFirst::getFinish).sum());
                list.add(gqFirst);
            });
        }

        return new ResponseBase(200, "查询成功", list);


    }

    public ResponseBase getpjFirst(String projectcode, Integer projectId,String projectType) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(projectId);
        //判断前端传的projectcode是否在该list中
        if (!proChildCode.contains(projectcode)) {
            return new ResponseBase(602, "该单位工程code在不属于该项目!");
        }
        //截取前端传的
//        String project = projectcode.substring(0, 2);
        List<PjGirst> pjFirsts = Lists.newArrayList();
        SsFProjects ssFProjects = projectsDAO.getProjectById(projectId);

        pjFirsts = zjConponentProducetimeDao.getAllByProJectType(projectcode,projectType);

        /*if (project.equals("QL")) {
            pjFirsts = zjConponentProducetimeDao.getAllByProJectSX(projectcode);
        } else if (project.equals("SD")) {
            pjFirsts = zjConponentProducetimeDao.getAllByProJectSD(projectcode);
        } else {
            pjFirsts = zjConponentProducetimeDao.getAllByProJectLM(projectcode);
        }*/
        Map<String, List<PjGirst>> collect = pjFirsts.stream().collect(Collectors.groupingBy(PjGirst::gettype));
        List<PjGirst> list = new ArrayList<>();
        collect.forEach((key, value) -> {
            PjGirst pjGirst = new PjGirst();
            String[] s = key.split("_");
            pjGirst.setList(value);
            pjGirst.setSxtype(s[0]);
            pjGirst.setSxname(s[1]);
            pjGirst.setTotal(value.stream().mapToInt(PjGirst::getTotal).sum());
            pjGirst.setFinish(value.stream().mapToInt(PjGirst::getFinish).sum());
            list.add(pjGirst);
        });

        return new ResponseBase(200, "查询成功", list);
    }

    public ResponseBase getCountIncresConponentGroupGq(String type, Integer group, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Map<Integer, SafePerData> dateMap = Maps.newHashMap();
        try {
            dateMap = DateUtils.getCount("month");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //查询该项目下的子级的单位工程
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        if (!gongqus.contains(group) && group != null) {
            return new ResponseBase(503, "查询失败，该用户没有该单位工程的工区的权限！");
        }
        if (group != null && !allGroups.contains(group)) {
            return new ResponseBase(503, "查询失败，要查的工区并不在该项目标段里面!");
        }
        //取到上述两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, gongqus);

        Map<String, List<CensusData>> map = new HashMap<>();
        if (group != null && group != 0) {
            putMapAllGroups(map, dateMap, group, type);
        } else {
            for (Integer gongquid : intersectionList) {
                putMapAllGroups(map, dateMap, gongquid, type);
            }
        }
        return new ResponseBase(200, "查询成功", map);

    }

    public ResponseBase getNewType(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }

        Map<String, List<NewProjectConType>> res = Maps.newHashMap();

        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(userId);

        List<NewProjectConType> list = Lists.newArrayList();
        List<NewProjectConType> list1 = Lists.newArrayList();
        List<NewProjectConType> list2 = Lists.newArrayList();
        List<NewProjectConType> list3 = Lists.newArrayList();
        //获取前端传的项目id
        List<Integer> proChildId = Lists.newArrayList();
        Integer projectLevel = projectsDAO.getProjectLevelById(projectId);
        if (projectLevel == 2) {
            proChildId = projectsDAO.getProjectChild(projectId);
        } else if (projectLevel == 3) {
            proChildId = projectsDAO.getProjectsChild(projectId);
        }
        if (gongqus.size() > 0) {
            //得到用户拥有权限与传的项目id下面项目工程的交集
            List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(proChildId, gongqus);
            StringBuilder sb = new StringBuilder();
            if (intersectionList.size() > 0) {
                for (Integer i : intersectionList) {
                    sb.append(i).append(",");
                }
                String abc = sb.substring(0, sb.toString().length() - 1);
                List<SysDictData> sysDictDataList =     sysDictDataMapper.selectDictDataByType("jg_gclx_all");

                for (SysDictData sysDictData : sysDictDataList) {

                    list = zjConponentProducetimeDao.getallNewByType(abc,sysDictData.getDictValue());
                    res.put(sysDictData.getDictLabel(), list);
                }
//                list = zjConponentProducetimeDao.getallNewByType(abc,"QL");
//                list1 = zjConponentProducetimeDao.getallNewByType(abc,"SD");
//                list2 = zjConponentProducetimeDao.getallNewByType(abc,"LM");
//                list3 = zjConponentProducetimeDao.getallNewByType(abc,"QT");
////                if (!list.isEmpty()) {
//                    res.put("桥梁工程", list);
////                }
////                if (!list1.isEmpty()) {
//                    res.put("隧道工程", list1);
////                }
//
//                res.put("房建工程", list2);
//                res.put("其他工程", list3);
            } else {
//                if (!list.isEmpty()) {
                    res.put("桥梁工程", list);
//                }
//                if (!list1.isEmpty()) {
                    res.put("隧道工程", list1);
//                }
                res.put("房建工程", list2);
                res.put("其他工程", list3);
            }
        }
        return new ResponseBase(200, "查询工序成功！", res);
    }

    public ResponseBase getProject(Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        List<Integer> singleProjectId = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        // 用户只能查当前项目下的工区
        gongqus = new ArrayList<>(CollUtil.intersectionDistinct(gongqus, singleProjectId));
        List<GroupProjectDTO> list = Lists.newArrayList();
        //当该用户绑定的项目时,应添加该项目下面的子分段的组织
        if (gongqus.contains(2)) {
            gongqus.clear();
            List<Integer> projectids = ssFGroupsDAO.getIdByParentid(2);
            for (Integer projectid : projectids) {
                List<Integer> groups = ssFGroupsDAO.getIdByParentid(projectid);
                gongqus.addAll(groups);
            }
        }
        //去重
        gongqus = gongqus.stream().distinct().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Integer i : gongqus) {
            sb.append(i).append(",");
        }
        String abc = sb.substring(0, sb.toString().length() - 1);
        list = zjConponentProducetimeDao.getallProject(abc);

        //通过java8按照时间倒叙排列
        list.sort(Comparator.comparing(GroupProjectDTO::getSttime, Comparator.reverseOrder()));
        return new ResponseBase(200, "查询成功!", list);
    }

    public ResponseBase getTypeData(NewCheckData newCheckData) {
        if (newCheckData.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(newCheckData.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id
        List<String> proChildCode = projectsDAO.getChildCode(newCheckData.getProjectId());
        if (ObjectUtils.isEmpty(newCheckData.getCode())) {
            newCheckData.setCode(null);
        }
        if (ObjectUtils.isEmpty(newCheckData.getConponenttype())) {
            newCheckData.setConponenttype(null);
        }
        if (ObjectUtils.isEmpty(newCheckData.getProjectid())) {
            newCheckData.setProjectid(null);
        }
        newCheckData.setProjectCodes(proChildCode);
        List<ZjConponentProducetime> list = null;
        if (!proChildCode.isEmpty()) {
            list = zjConponentProducetimeDao.getbyTypeData(newCheckData);
        }
        return new ResponseBase(200, "", list);
    }


    private Map<String, List<CensusData>> putMapAllGroups(Map<String, List<CensusData>> map, Map<Integer, SafePerData> dateMap,
                                                          Integer group, String type) {
        SsFGroups ssFGroups = ssFGroupsDAO.selectByPrimaryKey(group);
        String gongquname = ssFGroups.getName();
        List<CensusData> list = new ArrayList<>();
        for (Integer integer : dateMap.keySet()) {
            CensusData censusData = new CensusData();
            censusData.setSort(integer);
            censusData.setName(DateUtils.getDateByDateAndType(dateMap.get(integer).getSttime(), "month"));
            //yangaogao 20230918  #813  查询截至表格中当前月份的总量数据，原逻辑是查询表格中月份当月数据。
//            int num = zjConponentProducetimeDao.getcount(group, dateMap.get(integer).getSttime(), dateMap.get(integer).getEndtime(), type);
            int num = zjConponentProducetimeDao.getcount(group, null, dateMap.get(integer).getEndtime(), type);
            censusData.setNumber(num);
            list.add(censusData);
        }
        Map<Integer, CensusData> tempMap = Maps.newHashMap();
        if (list.size() < 1) {
            map.put(gongquname, new ArrayList<>());
        }
        list.forEach(censusData -> {
            tempMap.put(censusData.getSort(), censusData);
        });
        // todo 看不懂为什么要这样 目前这样导致 池州职业技术学院实验实训综合提升项目（二期）F+EPC 进度管理->进度总览内的统计图和表格图数据不对,
        // todo 目前发现的 区别在于急救中新存在一个工区,无子单位工程 二期存在多个工区,且存在子单位工程

//        for (int i = 6; i > 1; i--) {
//            Integer number = tempMap.get(i).getNumber();
//            Integer next = tempMap.get(i - 1).getNumber();
//            tempMap.get(i - 1).setNumber(number + next);
//            //设置前面一个
//        }
        List<CensusData> listTar = Lists.newArrayList();
        tempMap.forEach((key, censuse) -> {
            listTar.add(censuse);
        });
        map.put(gongquname, listTar);

        return map;
    }



    public ResponseBase getTodayWeather() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String today = sdf.format(time);
            String startToday = today + " 00:00:00";
            String endToday = today + " 23:59:59";
            Date startDate = sdf1.parse(startToday);
            Date endDate = sdf1.parse(endToday);
            List<WeatherNow> weathers = weatherDAO.getAllTodayWeather(startDate, endDate);
            return new ResponseBase(200, "",weathers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public ResponseBase getTodayClockOut(Integer projectId) {
        Map<String, Object> maps = Maps.newHashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        String today = sdf.format(time);
        String start = today + " 00:00:00";
        String end = today + " 23:59:59";
        if (projectId == null || projectId.equals("")) {
            return new ResponseBase(200, "项目(标段)id为空!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        if (project == null) {
            return new ResponseBase(200, "没有找到该项目数据!");
        }
        Integer SGId = null;
        Integer JLId = null;
        Integer QZId = null;
        List<String> strGroupIds = Lists.newArrayList();
        if (!Objects.isNull(project.getGroupid())) {
            strGroupIds = Arrays.asList(project.getGroupid().split(","));
        }
        for (String strGroupId : strGroupIds) {
            Integer groupId = Integer.parseInt(strGroupId);
            SsFGroups group = ssFGroupsDAO.getGroupInfoById(groupId);
            if (group.getName().contains("施工单位")) {
                SGId = groupId;
            } else if (group.getName().contains("监理单位")) {
                JLId = groupId;
            } else if (group.getName().contains("全咨单位")) {
                QZId = groupId;
            } else {
                continue;
            }
        }
        //打卡的集合
        List<ZjPersonClockin> SGList = clockInDAO.getGroupClockInfo(start, end, SGId);
        List<ZjPersonClockin> JLList = clockInDAO.getGroupClockInfo(start, end, JLId);
        List<ZjPersonClockin> QZList = clockInDAO.getGroupClockInfo(start, end, QZId);

        //施工
        TodayClockInCountDTO sg = returnCount(SGList, SGId);
        maps.put("sg", sg);
        //监理
        TodayClockInCountDTO jl = returnCount(JLList, JLId);
        maps.put("jl", jl);
        //全资
        TodayClockInCountDTO qz = returnCount(QZList, QZId);
        maps.put("qz", qz);

        return new ResponseBase(200, "", maps);
    }

    private TodayClockInCountDTO returnCount(List<ZjPersonClockin> clockInCounts, Integer groupId) {
        //先获取该集合的所有用户id
        List<Integer> allIds = ssFUserGroupDAO.getAllIds(groupId);
        //再获取今日未打卡的
        List<Integer> clockInIds = Lists.newArrayList();
        TodayClockInCountDTO dto = new TodayClockInCountDTO();
        if (clockInCounts.size() > 0) {
            for (ZjPersonClockin clockInCount : clockInCounts) {
                clockInIds.add(clockInCount.getAttendancePersonId());
            }
            //剩下的就是未打卡的以及请假的
            List<Integer> notClockIns = ListUtils.getSubtract(allIds, clockInIds);
            List<Integer> leaveIds = Lists.newArrayList();
            for (Integer notCount : notClockIns) {
                ZjPersonLeave leave = leaveDAO.getFinishLeaveByUserId(notCount);
                if (leave != null) {
                    //获取请假结束时间, 与当前时间作比较
                    LocalDateTime leaveEndTime = leave.getEndTime();
                    int result = LocalDateTime.now().compareTo(leaveEndTime);
                    if (result == 1) {
                        //当前时间大于请假结束时间
                        //说明请假已过期, 请假已过期就不加入到请假集合中
                        continue;
                    }
                    //把已经请假的人员id添加到集合中
                    leaveIds.add(notCount);
                }
            }
            dto.setClockInCount(clockInCounts.size());
            dto.setNotClockInCount(notClockIns.size() - leaveIds.size());
            dto.setLeaveCount(leaveIds.size());
            dto.setAll(allIds.size());
        } else {
            //当打卡的list为空时,说明都没打卡
            List<Integer> leaveIds = Lists.newArrayList();
            for (Integer id : allIds) {
                ZjPersonLeave leave = leaveDAO.getFinishLeaveByUserId(id);
                //获取请假结束时间, 与当前时间作比较
                if (leave != null) {
                    LocalDateTime leaveEndTime = leave.getEndTime();
                    int result = LocalDateTime.now().compareTo(leaveEndTime);
                    if (result == 1) {
                        //当前时间大于请假结束时间
                        //说明请假已过期, 请假已过期就不加入到请假集合中
                        continue;
                    }
                    //把已经请假的人员id添加到集合中
                    leaveIds.add(id);
                }
            }
            dto.setClockInCount(clockInCounts.size());
            dto.setNotClockInCount(allIds.size() - leaveIds.size());
            dto.setLeaveCount(leaveIds.size());
            dto.setAll(allIds.size());
        }
        return dto;
    }

    public ResponseBase getPeopleProportion(Integer projectId) {
        if (projectId == null || projectId.equals("")) {
            return new ResponseBase(200, "项目(标段)id为空!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        if (project == null) {
            return new ResponseBase(200, "没有找到该项目数据!");
        }
        Integer SGId = null;
        Integer JLId = null;
        Integer QZId = null;
        Integer buildUnitId = null;
        Integer buildGroupId = null;
        List<String> strGroupIds = Lists.newArrayList();
        if (!Objects.isNull(project.getGroupid())) {
            strGroupIds = Arrays.asList(project.getGroupid().split(","));
        }
        List<Integer> intGroupIds = Lists.newArrayList();
        for (String strGroupId : strGroupIds) {
            Integer groupId = Integer.parseInt(strGroupId);
            SsFGroups group = ssFGroupsDAO.getGroupInfoById(groupId);
            if (group.getName().contains("施工单位")) {
                SGId = groupId;
                intGroupIds.add(groupId);
            } else if (group.getName().contains("监理单位")) {
                JLId = groupId;
                intGroupIds.add(groupId);
            } else if (group.getName().contains("全咨单位")) {
                QZId = groupId;
                intGroupIds.add(groupId);
            } else if (group.getName().contains("建设单位")) {
                buildUnitId = groupId;
                intGroupIds.add(groupId);
            } else if (group.getName().contains("建设集团")) {
                buildGroupId = groupId;
                intGroupIds.add(groupId);
            } else {
                continue;
            }
        }
        Integer all = ssFUserGroupDAO.getAllCount(intGroupIds);
        Integer SGCount = ssFUserGroupDAO.getGroupCount(SGId);
        Integer JLCount = ssFUserGroupDAO.getGroupCount(JLId);
        Integer QZCount = ssFUserGroupDAO.getGroupCount(QZId);
        Integer buildUnitCount = ssFUserGroupDAO.getGroupCount(buildUnitId);
        Integer buildGroupCount = ssFUserGroupDAO.getGroupCount(buildGroupId);
        Map<String, Integer> maps = Maps.newHashMap();
        maps.put("all", all);
        maps.put("SGCount", SGCount);
        maps.put("JLCount", JLCount);
        maps.put("QZCount", QZCount);
        maps.put("buildUnitCount", buildUnitCount);
        maps.put("buildGroupCount", buildGroupCount);
        return new ResponseBase(200, "", maps);
    }

    public ResponseBase getOnDutyOrNotCount(Integer projectId) {
        if (projectId == null || projectId.equals("")) {
            return new ResponseBase(200, "项目(标段)id为空!");
        }
        SsFProjects project = projectsDAO.getProjectById(projectId);
        if (project == null) {
            return new ResponseBase(200, "没有找到该项目数据!");
        }
        List<String> strGroupIds = Lists.newArrayList();
        if (!Objects.isNull(project.getGroupid())) {
            strGroupIds = Arrays.asList(project.getGroupid().split(","));
        }
        List<Integer> intGroupIds = Lists.newArrayList();
        for (String strGroupId : strGroupIds) {
            Integer groupId = Integer.parseInt(strGroupId);
            SsFGroups group = ssFGroupsDAO.getGroupInfoById(groupId);
            if (group.getName().contains("施工单位") || group.getName().contains("监理单位") ||
                    group.getName().contains("全咨单位")) {
                intGroupIds.add(groupId);
            } else {
                continue;
            }
        }
        Integer all = ssFUserGroupDAO.getAllUserCountByGroupIds(intGroupIds);
        Integer onDuty = ssFUserGroupDAO.getAllUserOnDutyByGroupIds(intGroupIds);
        Map<String, Integer> maps = Maps.newHashMap();
        maps.put("all", all);
        maps.put("onDuty", onDuty);

        return new ResponseBase(200, "", maps);
    }

    public ResponseBase getAllClock(Map<String, Object> data) {
        try {
            String strProjectId = String.valueOf(data.get("projectId"));
            String strDate = String.valueOf(data.get("date"));

            Integer projectId;
            if (strProjectId != null && !strProjectId.equals("")) {
                projectId = Integer.parseInt(strProjectId);
            } else {
                return new ResponseBase(200, "项目(标段)id为空!");
            }
            SsFProjects project = projectsDAO.getProjectById(projectId);
            if (project == null) {
                return new ResponseBase(200, "没有找到该项目数据!");
            }
            Integer SGId = null;
            Integer JLId = null;
            Integer QZId = null;
            List<Integer> units = Lists.newArrayList();
            List<String> strGroupIds = Lists.newArrayList();
        if (!Objects.isNull(project.getGroupid())) {
            strGroupIds = Arrays.asList(project.getGroupid().split(","));
        }
            for (String strGroupId : strGroupIds) {
                Integer groupId = Integer.parseInt(strGroupId);
                SsFGroups group = ssFGroupsDAO.getGroupInfoById(groupId);
                if (group.getName().contains("施工单位")) {
                    SGId = groupId;
                    units.add(SGId);
                } else if (group.getName().contains("监理单位")) {
                    JLId = groupId;
                    units.add(JLId);
                } else if (group.getName().contains("全咨单位")) {
                    QZId = groupId;
                    units.add(QZId);
                } else {
                    continue;
                }
            }
            //当单位id为空时, 直接再看是否有考勤状态,如果有就查对应的,没有就查所有
            List<ClockInCensusReturn> censusList = Lists.newArrayList();
            List<ClockInCensusReturn> clockInList = Lists.newArrayList();
            List<ClockInCensusReturn> notClockIn = Lists.newArrayList();
            List<ClockInCensusReturn> leaveList = Lists.newArrayList();

            if (data.get("unitType") == null || data.get("unitType").equals("")) {
                censusList = processClockInCensus_New(projectId, units, strDate);
                for (ClockInCensusReturn censusReturn : censusList) {
                    if (censusReturn.getClockInState() == 1) {
                        clockInList.add(censusReturn);
                    } else if (censusReturn.getClockInState() == 2) {
                        notClockIn.add(censusReturn);
                    } else if (censusReturn.getClockInState() == 3) {
                        leaveList.add(censusReturn);
                    }
                }
                if (data.get("type") == null || data.get("type").equals("")) {
                    //为空返回所有
                    return new ResponseBase(200, "", censusList);
                } else if ((Integer) data.get("type") == 1) {
                    //已打卡
                    return new ResponseBase(200, "", clockInList);
                } else if ((Integer) data.get("type") == 2) {
                    //未打卡
                    return new ResponseBase(200, "", notClockIn);
                } else if ((Integer) data.get("type") == 3) {
                    //请休假
                    return new ResponseBase(200, "", leaveList);
                }
            } else {
                //传了单位
                Integer unitType = (Integer) data.get("unitType");
                if (unitType == 1) {
                    units.clear();
                    units.add(SGId);
                    censusList = processClockInCensus_New(projectId, units, strDate);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (data.get("type") == null || data.get("type").equals("")) {
                        //为空返回所有
                        return new ResponseBase(200, "", censusList);
                    } else if ((Integer) data.get("type") == 1) {
                        //已打卡
                        return new ResponseBase(200, "", clockInList);
                    } else if ((Integer) data.get("type") == 2) {
                        //未打卡
                        return new ResponseBase(200, "", notClockIn);
                    } else if ((Integer) data.get("type") == 3) {
                        //请休假
                        return new ResponseBase(200, "", leaveList);
                    }
                } else if (unitType == 2){
                    units.clear();
                    units.add(JLId);
                    censusList = processClockInCensus_New(projectId, units, strDate);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (data.get("type") == null || data.get("type").equals("")) {
                        //为空返回所有
                        return new ResponseBase(200, "", censusList);
                    } else if ((Integer) data.get("type") == 1) {
                        //已打卡
                        return new ResponseBase(200, "", clockInList);
                    } else if ((Integer) data.get("type") == 2) {
                        //未打卡
                        return new ResponseBase(200, "", notClockIn);
                    } else if ((Integer) data.get("type") == 3) {
                        //请休假
                        return new ResponseBase(200, "", leaveList);
                    }
                } else if (unitType == 3){
                    units.clear();
                    units.add(QZId);
                    censusList = processClockInCensus_New(projectId, units, strDate);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (data.get("type") == null || data.get("type").equals("")) {
                        //为空返回所有
                        return new ResponseBase(200, "", censusList);
                    } else if ((Integer) data.get("type") == 1) {
                        //已打卡
                        return new ResponseBase(200, "", clockInList);
                    } else if ((Integer) data.get("type") == 2) {
                        //未打卡
                        return new ResponseBase(200, "", notClockIn);
                    } else if ((Integer) data.get("type") == 3) {
                        //请休假
                        return new ResponseBase(200, "", leaveList);
                    }
                }
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public void doExportAllClock(Integer projectId, Integer unitType, Integer type,
                                 String date, HttpServletRequest request, HttpServletResponse response) {
        try {
            SsFProjects project = new SsFProjects();
            if (projectId != null && !projectId.equals("")) {
                project = projectsDAO.getProjectById(projectId);
            }

            Integer SGId = null;
            Integer JLId = null;
            Integer QZId = null;
            List<Integer> units = Lists.newArrayList();
            List<String> strGroupIds = Lists.newArrayList();
        if (!Objects.isNull(project.getGroupid())) {
            strGroupIds = Arrays.asList(project.getGroupid().split(","));
        }
            for (String strGroupId : strGroupIds) {
                Integer groupId = Integer.parseInt(strGroupId);
                SsFGroups group = ssFGroupsDAO.getGroupInfoById(groupId);
                if (group.getName().contains("施工单位")) {
                    SGId = groupId;
                    units.add(SGId);
                } else if (group.getName().contains("监理单位")) {
                    JLId = groupId;
                    units.add(JLId);
                } else if (group.getName().contains("全咨单位")) {
                    QZId = groupId;
                    units.add(QZId);
                } else {
                    continue;
                }
            }
            //当单位id为空时, 直接再看是否有考勤状态,如果有就查对应的,没有就查所有
            List<ClockInCensusReturn> censusList = Lists.newArrayList();
            List<ClockInCensusReturn> clockInList = Lists.newArrayList();
            List<ClockInCensusReturn> notClockIn = Lists.newArrayList();
            List<ClockInCensusReturn> leaveList = Lists.newArrayList();

            //创建excel表头
            List<String> column = Lists.newArrayList();
            column.add("姓名");
            column.add("单位");
            column.add("角色");
            column.add("总打卡次数");
            column.add("未打卡次数");
            column.add("请假数");
            column.add("今日状态");
            //表头对应数据
            List<Map<String, Object>> dataExcel = Lists.newArrayList();

            if (unitType == null || unitType.equals("")) {
                censusList = processClockInCensus_New(projectId, units, date);
                for (ClockInCensusReturn censusReturn : censusList) {
                    if (censusReturn.getClockInState() == 1) {
                        clockInList.add(censusReturn);
                    } else if (censusReturn.getClockInState() == 2) {
                        notClockIn.add(censusReturn);
                    } else if (censusReturn.getClockInState() == 3) {
                        leaveList.add(censusReturn);
                    }
                }
                if (type == null || type.equals("")) {
                    //为空返回所有
                    for (ClockInCensusReturn censusReturn : censusList) {
                        Map<String, Object> dataMap = Maps.newHashMap();
                        dataMap.put("姓名", censusReturn.getUserName());
                        dataMap.put("单位", censusReturn.getUnitName());
                        dataMap.put("角色", censusReturn.getRoleName());
                        dataMap.put("总打卡次数", censusReturn.getClockInDay());
                        dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                        dataMap.put("请假数", censusReturn.getLeaveDay());
                        dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                        dataExcel.add(dataMap);
                    }
                } else if (type == 1) {
                    //已打卡
                    for (ClockInCensusReturn censusReturn : clockInList) {
                        Map<String, Object> dataMap = Maps.newHashMap();
                        dataMap.put("姓名", censusReturn.getUserName());
                        dataMap.put("单位", censusReturn.getUnitName());
                        dataMap.put("角色", censusReturn.getRoleName());
                        dataMap.put("总打卡次数", censusReturn.getClockInDay());
                        dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                        dataMap.put("请假数", censusReturn.getLeaveDay());
                        dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                        dataExcel.add(dataMap);
                    }
                } else if (type == 2) {
                    //未打卡
                    for (ClockInCensusReturn censusReturn : notClockIn) {
                        Map<String, Object> dataMap = Maps.newHashMap();
                        dataMap.put("姓名", censusReturn.getUserName());
                        dataMap.put("单位", censusReturn.getUnitName());
                        dataMap.put("角色", censusReturn.getRoleName());
                        dataMap.put("总打卡次数", censusReturn.getClockInDay());
                        dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                        dataMap.put("请假数", censusReturn.getLeaveDay());
                        dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                        dataExcel.add(dataMap);
                    }
                } else if (type == 3) {
                    //请休假
                    for (ClockInCensusReturn censusReturn : leaveList) {
                        Map<String, Object> dataMap = Maps.newHashMap();
                        dataMap.put("姓名", censusReturn.getUserName());
                        dataMap.put("单位", censusReturn.getUnitName());
                        dataMap.put("角色", censusReturn.getRoleName());
                        dataMap.put("总打卡次数", censusReturn.getClockInDay());
                        dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                        dataMap.put("请假数", censusReturn.getLeaveDay());
                        dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                        dataExcel.add(dataMap);
                    }
                }
                // 调用工具类导出
                MyExcelUtil.exportExcel("考勤统计表", column, dataExcel, request, response);
            } else {
                //传了单位
                if (unitType == 1) {
                    units.clear();
                    units.add(SGId);
                    censusList = processClockInCensus_New(projectId, units, date);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (type == null || type.equals("")) {
                        //为空返回所有
                        for (ClockInCensusReturn censusReturn : censusList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 1) {
                        //已打卡
                        for (ClockInCensusReturn censusReturn : clockInList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 2) {
                        //未打卡
                        for (ClockInCensusReturn censusReturn : notClockIn) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 3) {
                        //请休假
                        for (ClockInCensusReturn censusReturn : leaveList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    }
                    // 调用工具类导出
                    MyExcelUtil.exportExcel("考勤统计表", column, dataExcel, request, response);
                } else if (unitType == 2){
                    units.clear();
                    units.add(JLId);
                    censusList = processClockInCensus_New(projectId, units, date);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (type == null || type.equals("")) {
                        //为空返回所有
                        for (ClockInCensusReturn censusReturn : censusList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 1) {
                        //已打卡
                        for (ClockInCensusReturn censusReturn : clockInList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 2) {
                        //未打卡
                        for (ClockInCensusReturn censusReturn : notClockIn) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 3) {
                        //请休假
                        for (ClockInCensusReturn censusReturn : leaveList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    }
                    // 调用工具类导出
                    MyExcelUtil.exportExcel("考勤统计表", column, dataExcel, request, response);
                } else if (unitType == 3){
                    units.clear();
                    units.add(QZId);
                    censusList = processClockInCensus_New(projectId, units, date);
                    for (ClockInCensusReturn censusReturn : censusList) {
                        if (censusReturn.getClockInState() == 1) {
                            clockInList.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 2) {
                            notClockIn.add(censusReturn);
                        } else if (censusReturn.getClockInState() == 3) {
                            leaveList.add(censusReturn);
                        }
                    }
                    if (type == null || type.equals("")) {
                        //为空返回所有
                        for (ClockInCensusReturn censusReturn : censusList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 1) {
                        //已打卡
                        for (ClockInCensusReturn censusReturn : clockInList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 2) {
                        //未打卡
                        for (ClockInCensusReturn censusReturn : notClockIn) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    } else if (type == 3) {
                        //请休假
                        for (ClockInCensusReturn censusReturn : leaveList) {
                            Map<String, Object> dataMap = Maps.newHashMap();
                            dataMap.put("姓名", censusReturn.getUserName());
                            dataMap.put("单位", censusReturn.getUnitName());
                            dataMap.put("角色", censusReturn.getRoleName());
                            dataMap.put("总打卡次数", censusReturn.getClockInDay());
                            dataMap.put("未打卡次数", censusReturn.getNotClockInDay());
                            dataMap.put("请假数", censusReturn.getLeaveDay());
                            dataMap.put("今日状态", getTodayState(censusReturn.getClockInState()));
                            dataExcel.add(dataMap);
                        }
                    }
                    // 调用工具类导出
                    MyExcelUtil.exportExcel("考勤统计表", column, dataExcel, request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取今日状态
     * @param state
     * @return
     */
    private String getTodayState(Integer state){
        if (state == 1){
            return "已打卡";
        } else if (state == 2){
            return "未打卡";
        } else {
            return "休假";
        }
    }

    /**
     *
     * @param projectId 项目标段id
     * @param units 组织单位id集合
     * @param strDate 查询时间
     * @return
     */
    private List<ClockInCensusReturn> processClockInCensus(Integer projectId, List<Integer> units, String strDate) throws Exception {

        List<ClockInCensusReturn> censusList = ssFUserGroupDAO.getCensusList(units);

        Iterator it = censusList.iterator();
        while (it.hasNext()) {
            ClockInCensusReturn censusReturn = (ClockInCensusReturn) it.next();
            //遍历每个人的打卡记录
            Integer userId = censusReturn.getUserId();
            SsFUsers userInfo = usersDAO.getUsingInfoByUserId(userId);
            if (userInfo == null) {
                //如果没查到说明该用户账号已经冻结, 则不需要显示
                it.remove();
                continue;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String today = sdf.format(now);
            String start = today + " 00:00:00";
            String end = today + " 23:59:59";
            // 按甲方要求 开始时间为 2022-08-01
            String startDay = "2022-08-01";
            //结束时间为当天日期
            String endDay = DateUtils.getDateByDate(now);
            //总天数  因为计算时没有把第一天计算在内, 因此最后需加一天
            Integer totalDay = DateUtils.subtractTwoDate(startDay, endDay) + 1;

            String startTime = startDay + " 00:00:00";
            List<ZjPersonClockin> clockInList = Lists.newArrayList();
            if (strDate != null && !strDate.equals("")){
                int year = Integer.parseInt(strDate.substring(0, 4));
                int month = Integer.parseInt(strDate.substring(5, 7));
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                Date parseStrDate = sdf1.parse(strDate);
                //先判断传入的月份是否是本月
                boolean flag = DateUtils.inCurrentMonth(parseStrDate);
                Date parseStartDate = DateUtils.getFirstOfMonth(parseStrDate);
                Date parseEndDate = DateUtils.getLastOfMonth(parseStrDate);
                String strStart = sdf.format(parseStartDate);
                String strEnd = sdf.format(parseEndDate);
                //如果是本月,先计算月初到今天有多少天
                if (flag){
                    totalDay = DateUtils.subtractTwoDate(strStart, today) + 1;
                } else {
                    totalDay = DateUtils.day(month, year);
                }

                clockInList = clockInDAO.getSelfAllByUserIdAndTime(userId, strStart, strEnd);
            } else {
                clockInList = clockInDAO.getSelfAllByUserIdAndTime(userId, startTime, end);
            }
            //已打卡天数就是该用户的打卡次数
            censusReturn.setClockInDay(clockInList.size());
            //未打卡天数
            Integer notClockInDay = totalDay - clockInList.size();
            censusReturn.setNotClockInDay(notClockInDay);
            //请假天数
            List<ZjPersonLeave> leaveList = leaveDAO.getFinishedLeaveByUserId(userId, startTime);
            Double leaveDay = leaveList.stream().collect(Collectors.summingDouble(ZjPersonLeave::getLeaveDay));
            censusReturn.setLeaveDay(leaveDay);
            //因为是按照时间正序排列, 所以第一次必定为该用户第一次打卡, 因此先获取该用户的账号表,
            // 查询UPDATETIME字段是否为空, 如果不为空说明该账号之前已经冻结/再次启动
            if (userInfo.getUpdatetime() != null && !userInfo.getUpdatetime().equals("")) {
                String startTime1 = DateUtils.getDateByDate(userInfo.getUpdatetime());
                //总天数   因为计算时没有把第一天计算在内, 因此最后需加一天
                Integer totalDay1 = DateUtils.subtractTwoDate(startTime1, endDay) + 1;
                // 再获取打卡次数时,从启动时间的当天开始算
                String useStartTime = sdf.format(userInfo.getUpdatetime());
                if (strDate != null && !strDate.equals("")){
                    int year = Integer.parseInt(strDate.substring(0, 4));
                    int month = Integer.parseInt(strDate.substring(5, 7));
                    //先判断传入的月份有多少天
                    totalDay1 = DateUtils.day(month, year);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                    Date parseStrDate = sdf1.parse(strDate);
                    //先判断传入的月份是否是本月
                    boolean flag = DateUtils.inCurrentMonth(parseStrDate);
                    Date parseStartDate = DateUtils.getFirstOfMonth(parseStrDate);
                    Date parseEndDate = DateUtils.getLastOfMonth(parseStrDate);
                    String strStart = sdf.format(parseStartDate);
                    String strEnd = sdf.format(parseEndDate);
                    //再判断 月初这天是在该账号冻结启用之前还是之后
                    if (flag){
                        Date userUpdateTime = userInfo.getUpdatetime();
                        if (userUpdateTime.compareTo(parseStartDate) == 1){
                            //用户账号启用时间在月初时间之前
                            clockInList = clockInDAO.getSelfAllByUserIdAndTime(userId, strStart, strEnd);
                        } else {
                            //用户账号启用时间在月初时间之后
                            clockInList = clockInDAO.getSelfAllByUserIdAndTime(userId, useStartTime, strEnd);
                        }
                    }

                } else {
                    clockInList = clockInDAO.getSelfAllByUserIdAndTime(userId, useStartTime, end);
                }

                censusReturn.setClockInDay(clockInList.size());
                //未打卡天数
                Integer notClockInDay1 = totalDay1 - clockInList.size();
                censusReturn.setNotClockInDay(notClockInDay1);
                //请假天数
                List<ZjPersonLeave> leaveList1 = leaveDAO.getFinishedLeaveByUserIdAndTime(userId, startTime1);
                Double leaveDay1 = leaveList1.stream().collect(Collectors.summingDouble(ZjPersonLeave::getLeaveDay));
                censusReturn.setLeaveDay(leaveDay1);
            }
            //考勤状态: 先查该用户打卡表今天是否有记录, 没有再查请假表今天是否有记录
            ZjPersonClockin userClockIn = clockInDAO.getSelfTodayByProjectIdInTime(projectId, userId, start, end);
            ZjPersonLeave userLeave = leaveDAO.getLeavingByUserIdAndTime(userId, now);
            censusReturn.setClockInState(2);
            if (userClockIn != null && !userClockIn.equals("")) {
                censusReturn.setClockInState(1);
            }
            if (userLeave != null && !userLeave.equals("")) {
                censusReturn.setClockInState(3);
            }
        }
        return censusList;
    }

    /**
     * 优化点：
     * 1、减少数据库IO操作->规避循环里操作数据库
     * 2、合并部分SQL查询语句
     * 3、重构代码结构，精简冗余代码
     *
     * @param projectId
     * @param units
     * @param paramDate
     * @return
     * @throws Exception
     */
    private List<ClockInCensusReturn> processClockInCensus_New(Integer projectId, List<Integer> units, String paramDate) throws Exception {
        List<ClockInCensusReturn> clockInCensusList = ssFUserGroupDAO.getClockInCensusList(units);

        String startDate = "2022-08-01";
        String startTime = " 00:00:00";
        String endTime = " 23:59:59";
        String currentDate = DateUtils.getDateByDate(new Date());

        // 按甲方要求 开始时间为 2022-08-01
        String startDateTime = startDate.concat(startTime);
        //结束时间为当天日期
        String endDateTime = currentDate.concat(endTime);
        //总天数:因为计算时没有把第一天计算在内, 因此最后需加一天
        Integer totalDay = DateUtils.subtractTwoDate(startDate, currentDate) + 1;

        //请假天数：查询大于指定开始日期的请假数据(此处需要前置查询，因为开始时间后面可能会重新赋值)
        List<ZjPersonLeave> personLeaveList = leaveDAO.getPersonLeaveListByStartDate(startDateTime);
        boolean isCurrentMonth = true;
        if (StringUtils.isNotEmpty(paramDate)) {
            DateTime paramFormatDate = DateUtil.parse(paramDate, "yyyy-MM");
            startDateTime = DateUtil.format(DateUtils.getFirstOfMonth(paramFormatDate), "yyyy-MM-dd");
            endDateTime = DateUtil.format(DateUtils.getLastOfMonth(paramFormatDate), "yyyy-MM-dd");
            //先判断传入的月份是否是本月
            isCurrentMonth = DateUtils.inCurrentMonth(paramFormatDate);
            totalDay = isCurrentMonth ? DateUtils.subtractTwoDate(startDateTime, endDateTime) + 1 : DateUtils.day(Integer.parseInt(paramDate.substring(5, 7)),Integer.parseInt(paramDate.substring(0, 4)));
        }
        // 根据开始、结束时间查询ZjPersonClockin对象列表数据
        List<ZjPersonClockin> personClockinList = clockInDAO.getPersonClockinListByDate(startDateTime, endDateTime);

        //循环更新考勤数据
        Integer finalTotalDay = totalDay;
        String finalEndDateTime = endDateTime;
        clockInCensusList.stream().forEach(clockInCensusReturn -> {
            // 当前用户考勤数据列表
            List<ZjPersonClockin> currentPersonClockinList = personClockinList.stream().filter(personClockin -> personClockin.getAttendancePersonId().equals(clockInCensusReturn.getUserId()))
                    .collect(Collectors.toList());
            // 当前用户请假数据列表
            List<ZjPersonLeave> currentPersonLeaveList = personLeaveList.stream().filter(personLeave -> personLeave.getLeavePersonId().equals(clockInCensusReturn.getUserId())).collect(Collectors.toList());

            //已打卡天数就是该用户的打卡次数
            Integer clockInDay = currentPersonClockinList.size();
            //未打卡天数
            Integer notClockInDay = finalTotalDay - currentPersonClockinList.size();
            //请假天数
            Double leaveDay = currentPersonLeaveList.stream().collect(Collectors.summingDouble(ZjPersonLeave::getLeaveDay));

            // 查询用户UPDATETIME字段是否为空(目前运维系统未记录用户账号开启\冻结的时间,暂时用false控制不执行以下逻辑), 如果不为空说明该账号之前已经冻结/再次启动
            if ( false && !Objects.isNull(clockInCensusReturn.getUserUpdateTime())) {
                //再判断 月初这天是在该账号冻结启用之前还是之后 OR 相同
                List<ZjPersonClockin> newPersonClockinList = Lists.newArrayList();
                if (StringUtils.isNotEmpty(paramDate)) {
                    if (clockInCensusReturn.getUserUpdateTime().compareTo(DateUtils.getFirstOfMonth(DateUtil.parse(paramDate, "yyyy-MM-dd"))) != 1) {
                        // 用户账号启用时间在月初时间之前 - 需要重新查询出考勤数据(因为账号启用到该月初的数据考勤列表数据里不包含)
                        newPersonClockinList = clockInDAO.getPersonClockinListByDate(DateUtil.format(clockInCensusReturn.getUserUpdateTime(), "yyyy-MM-dd"), finalEndDateTime);
                    } else {
                        // 用户账号启用时间在月初时间之后，从原列表筛选出启用日期之后的数据
                        newPersonClockinList = currentPersonClockinList.stream().filter(personClockin -> personClockin.getClockTime().compareTo(clockInCensusReturn.getUserUpdateTime()) == 1).collect(Collectors.toList());
                    }
                }
                //总天数:因为计算时没有把第一天计算在内, 因此最后需加一天
                Integer newTotalDay = DateUtils.subtractTwoDate(DateUtils.getDateByDate(clockInCensusReturn.getUserUpdateTime()), currentDate) + 1;
                //已打卡天数
                clockInDay = newPersonClockinList.size();
                //未打卡天数
                notClockInDay = newTotalDay - newPersonClockinList.size();
                //请假天数
                leaveDay = currentPersonLeaveList.stream().filter(personLeave -> LocalDateTime.ofInstant(clockInCensusReturn.getUserUpdateTime().toInstant(), ZoneId.systemDefault()).compareTo(personLeave.getEndTime()) > -1).collect(Collectors.summingDouble(ZjPersonLeave::getLeaveDay));
            }
            //已打卡天数就是该用户的打卡次数
            clockInCensusReturn.setClockInDay(clockInDay);
            //未打卡天数
            clockInCensusReturn.setNotClockInDay(notClockInDay);
            //请假天数
            clockInCensusReturn.setLeaveDay(leaveDay);
            //考勤状态: 先查该用户打卡表今天是否有记录, 没有再查请假表今天是否有记录

            Optional<ZjPersonClockin> personClockinOptional = currentPersonClockinList.stream().filter(personClockin -> Objects.equals(personClockin.getProjectId(), projectId) &&
                     !Objects.isNull(personClockin.getClockTime()) && personClockin.getClockTime().compareTo(DateUtil.parseDate(currentDate.concat(startTime)).toJdkDate()) > -1).findFirst();
            Optional<ZjPersonLeave> personLeaveOptional = currentPersonLeaveList.stream().filter(personLeave -> Objects.equals(personLeave.getProjectId(), projectId) &&
                    !Objects.isNull(personLeave.getEndTime()) && personLeave.getEndTime().toLocalDate().isEqual(LocalDate.now())).findFirst();
            clockInCensusReturn.setClockInState(2);
            if (personClockinOptional.isPresent()) {
                clockInCensusReturn.setClockInState(1);
            }
            if (personLeaveOptional.isPresent()) {
                clockInCensusReturn.setClockInState(3);
            }
        });
        return clockInCensusList;
    }
}
