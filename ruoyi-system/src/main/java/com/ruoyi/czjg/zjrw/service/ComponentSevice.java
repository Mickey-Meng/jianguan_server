package com.ruoyi.czjg.zjrw.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.ComponentType;
import com.ruoyi.common.core.domain.BaseTree;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.Produce;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.common.core.domain.entity.ZjConponentProducetime;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.czjg.zjrw.dao.*;
import com.ruoyi.czjg.zjrw.domain.dto.*;
import com.ruoyi.czjg.zjrw.domain.entity.*;
import com.ruoyi.common.utils.zjbim.zjrw.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/18 8:55 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class ComponentSevice {
    Logger log = LoggerFactory.getLogger(ComponentSevice.class);

    @Autowired
    ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;
    @Autowired
    ConponentDAO conponentDAO;
    @Autowired
    ProduceandrecodeDAO produceandrecodeDAO;
    @Autowired
    ProduceDAO produceDAO;
    @Autowired
    private ProjectsDAO projectsDAO;
    @Autowired
    SsFGroupsDAO ssFGroupsDao;
    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;


    @Autowired
    ZjConponentProducetimeDAO zjConponentProducetimeDAO;

    @Transactional
    public void uploadExcel(List<List<String>> list) {
        System.out.println("需要处理的数据有：" + list.size() + "条！");

        List<String> bimCodes = new ArrayList<>();

        list.forEach(value -> {

            Conponent conponent = MyExcelUtil.toConponent(value);
            //从w1 到  w7
            String projectname = conponent.getW1();
            String projectcode = conponent.getW1code();
            int projectid = 0;
            String gongquname = conponent.getW2();
            String gongqucode = conponent.getW2code();
            int firstId = 0;
            //先判断工区与项目的关系是否存在
            Conponent first = conponentDAO.getParentWithOutProjectCode(gongquname, gongqucode, projectname, projectcode, projectid);
            if (ObjectUtils.isEmpty(first) || first == null) {
                //如果不存在就插入
                Conponent conponentCheck = new Conponent();
                conponentCheck.setName(gongquname);
                conponentCheck.setCode(gongqucode);
                conponentCheck.setPname(projectname);
                conponentCheck.setPcode(projectcode);
                conponentCheck.setPid(projectid);
//                conponentCheck.setProjectcode(conponent.getW3code());
                conponentDAO.insert(conponentCheck);
                firstId = conponentCheck.getId();
            } else {
                firstId = first.getId();
            }
            //工区与项目已经好了，在设置 工区与项目关系
            String unitname = conponent.getW3();
            String unitcode = conponent.getW3code();
            Conponent second = conponentDAO.getParent(unitname, unitcode, gongquname, gongqucode, firstId, conponent.getW3code());
            int secondId = 0;
            if (ObjectUtils.isEmpty(second) || second == null) {
                //如果不存在就插入
                Conponent conponentCheck = new Conponent();
                conponentCheck.setName(unitname);
                conponentCheck.setCode(unitcode);
                conponentCheck.setPname(gongquname);
                conponentCheck.setPcode(gongqucode);
                conponentCheck.setPid(firstId);
                conponentCheck.setProjectcode(conponent.getW3code());
                conponentDAO.insert(conponentCheck);
                secondId = conponentCheck.getId();
            } else {
                secondId = second.getId();
            }
            //项目 工区 单体项目关系已经确定
            //设置第一层结构路径 上部结构，下部结构，桥面附属
            String firstConstructname = conponent.getW4();
            String firstConstructCode = conponent.getW4code();
            //单体项目 与 第一空间结构关系
            Conponent thrid = conponentDAO.getParent(firstConstructname,
                    firstConstructCode, unitname, unitcode, secondId, conponent.getW3code());
            int thridId = 0;
            if (ObjectUtils.isEmpty(thrid) || thrid == null) {
                //如果不存在就插入
                Conponent conponentCheck = new Conponent();
                conponentCheck.setName(firstConstructname);
                conponentCheck.setCode(firstConstructCode);
                conponentCheck.setPname(unitname);
                conponentCheck.setPcode(unitcode);
                conponentCheck.setPid(secondId);
                conponentCheck.setProjectcode(conponent.getW3code());
                conponentDAO.insert(conponentCheck);
                thridId = conponentCheck.getId();
            } else {
                thridId = thrid.getId();
            }
            //下面就复杂一点 ，需要进行判断
            String w5 = conponent.getW5();
            String w5code = conponent.getW5code();

            if (ObjectUtils.isEmpty(conponent.getW6())) {
                //说明w5是最后一层
                conponent.setPid(thridId);
                conponent.setPcode(unitcode);
                conponent.setPname(unitname);
                conponent.setName(w5);
                conponent.setCode(w5code);
                conponentDAO.insert(conponent);

                //插入 --
                ZjConponentProducetime zjConponentProducetime = new ZjConponentProducetime();

                //设置构件id
                zjConponentProducetime.setConponentid(conponent.getId());
                //构件code
                zjConponentProducetime.setConponentcode(conponent.getConponentcode());
                //构件类型
                zjConponentProducetime.setConponenttype(conponent.getConponenttype());
                //构件类型名字
                zjConponentProducetime.setConponenttypename(conponent.getConponenttypename());
                //项目code
                zjConponentProducetime.setProjectcode(conponent.getProjectcode());
                //项目名称
                zjConponentProducetime.setProjectname(conponent.getW3());
                //项目类型
                zjConponentProducetime.setProjecttype(conponent.getProjecttype());
                //gongquname
                zjConponentProducetime.setGongquname(conponent.getW2());
                //pjctype
                zjConponentProducetime.setPjctype(conponent.getW4());

                zjConponentProducetimeDAO.insert(zjConponentProducetime);


            } else if (ObjectUtils.isEmpty(conponent.getW7())) {
                // 设置 w4 与 w5 的关系
                Conponent forth = conponentDAO.getParent(w5, w5code, firstConstructname,
                        firstConstructCode, thridId, conponent.getW3code());
                int fortdId = 0;
                if (ObjectUtils.isEmpty(forth) || forth == null) {
                    Conponent conponentCheck = new Conponent();
                    conponentCheck.setName(w5);
                    conponentCheck.setCode(w5code);
                    conponentCheck.setPname(firstConstructname);
                    conponentCheck.setPcode(firstConstructCode);
                    conponentCheck.setPid(thridId);
                    conponentCheck.setProjectcode(conponent.getW3code());
                    conponentDAO.insert(conponentCheck);
                    fortdId = conponentCheck.getId();
                } else {
                    fortdId = forth.getId();
                }
                conponent.setPid(fortdId);
                conponent.setPcode(w5code);
                conponent.setPname(w5);
                conponent.setName(conponent.getW6());
                conponent.setCode(conponent.getW6code());
                conponentDAO.insert(conponent);

                ZjConponentProducetime zjConponentProducetime = new ZjConponentProducetime();

                //设置构件id
                zjConponentProducetime.setConponentid(conponent.getId());
                //构件code
                zjConponentProducetime.setConponentcode(conponent.getConponentcode());
                //构件类型
                zjConponentProducetime.setConponenttype(conponent.getConponenttype());
                //构件类型名字
                zjConponentProducetime.setConponenttypename(conponent.getConponenttypename());
                //项目code
                zjConponentProducetime.setProjectcode(conponent.getProjectcode());
                //项目名称
                zjConponentProducetime.setProjectname(conponent.getW3());
                //项目类型
                zjConponentProducetime.setProjecttype(conponent.getProjecttype());
                //gongquname
                zjConponentProducetime.setGongquname(conponent.getW2());
                //pjctype
                zjConponentProducetime.setPjctype(conponent.getW4());

                zjConponentProducetimeDAO.insert(zjConponentProducetime);
            } else if (ObjectUtils.isEmpty(conponent.getW8())) {
                // 设置 w4 与 w5 的关系
                Conponent forth = conponentDAO.getParent(w5, w5code, firstConstructname,
                        firstConstructCode, thridId, conponent.getW3code());
                int fortdId = 0;
                if (ObjectUtils.isEmpty(forth) || forth == null) {
                    Conponent conponentCheck = new Conponent();
                    conponentCheck.setName(w5);
                    conponentCheck.setCode(w5code);
                    conponentCheck.setPname(firstConstructname);
                    conponentCheck.setPcode(firstConstructCode);
                    conponentCheck.setPid(thridId);
                    conponentCheck.setProjectcode(conponent.getW3code());
                    conponentDAO.insert(conponentCheck);
                    fortdId = conponentCheck.getId();
                } else {
                    fortdId = forth.getId();
                }
                //设置 w5与w6关系
                Conponent fiveth = conponentDAO.getParent(conponent.getW6(), conponent.getW6code(), w5, w5code, fortdId, conponent.getW3code());
                int fivthId = 0;
                if (ObjectUtils.isEmpty(fiveth) || fiveth == null) {
                    Conponent conponentCheck = new Conponent();
                    conponentCheck.setName(conponent.getW6());
                    conponentCheck.setCode(conponent.getW6code());
                    conponentCheck.setPname(w5);
                    conponentCheck.setPcode(w5code);
                    conponentCheck.setPid(fortdId);
                    conponentCheck.setProjectcode(conponent.getW3code());
                    conponentDAO.insert(conponentCheck);
                    fivthId = conponentCheck.getId();
                } else {
                    fivthId = fiveth.getId();
                }
                conponent.setCode(conponent.getW7code());
                conponent.setName(conponent.getW7());
                conponent.setPname(conponent.getW6());
                conponent.setPcode(conponent.getW6code());
                conponent.setPid(fivthId);
                conponentDAO.insert(conponent);
                ZjConponentProducetime zjConponentProducetime = new ZjConponentProducetime();

                //设置构件id
                zjConponentProducetime.setConponentid(conponent.getId());
                //构件code
                zjConponentProducetime.setConponentcode(conponent.getConponentcode());
                //构件类型
                zjConponentProducetime.setConponenttype(conponent.getConponenttype());
                //构件类型名字
                zjConponentProducetime.setConponenttypename(conponent.getConponenttypename());
                //项目code
                zjConponentProducetime.setProjectcode(conponent.getProjectcode());
                //项目名称
                zjConponentProducetime.setProjectname(conponent.getW3());
                //项目类型
                zjConponentProducetime.setProjecttype(conponent.getProjecttype());
                //gongquname
                zjConponentProducetime.setGongquname(conponent.getW2());
                //pjctype
                zjConponentProducetime.setPjctype(conponent.getW4());

                zjConponentProducetimeDAO.insert(zjConponentProducetime);
            } else {
                System.out.println("有遗漏");
            }

            //最后 把zj_f_conpontent_producetime的BIM编码导入到对应的conpontent中
            String bimCode = value.get(0);
            bimCodes.add(bimCode);
        });

        log.info("文档导入完毕！");
    }

    public ResponseBase getTree(Integer projectId, String type) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        BaseTree baseTree = new BaseTree();
        //通询该子级的单位工程
        List<Integer> proChildId = Lists.newArrayList();
        Integer projectLevel = projectsDAO.getProjectLevelById(projectId);
        if (projectLevel == 2) {
            proChildId = projectsDAO.getProjectChild(projectId);
        } else if (projectLevel == 3) {
            proChildId = projectsDAO.getProjectsChild(projectId);
        }
        Integer userId = JwtUtil.getTokenUser().getId();
        //该人员的单位工程权限id
//        Integer userId = 14;
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(userId);
        List<Integer> gongqusSet = gongqus.stream()
                .filter(proChildId::contains)
                .collect(Collectors.toList());

        SsFProjectsTree ssFProjectsTree = projectsDAO.getSsFProjectsTreeByGongQuId(proChildId.get(0));
        baseTree.setName(ssFProjectsTree.getName());
        baseTree.setId(0);
        baseTree.setPid(-1);
        List<BaseTree> all = Lists.newArrayList();
        for (Integer gongQuId : gongqusSet) {
            Conponent gqConponent = conponentDAO.getConponentByGqId(gongQuId.toString());
            if (gqConponent == null) {
                continue;
            }

            BaseTree baseTree1 = CacheProject.toBeBaseTree(gqConponent);

            List<SsFGroups> projects = ssFGroupsDao.getByParentIdType(gongQuId, type);
            List<BaseTree> gqList = Lists.newArrayList();
            for (SsFGroups project : projects) {
                ZjFGroupsProjects projectCodeByProjectId = zjFGroupsProjectsDAO.getProjectCodeByProjectId(project.getId());
                BaseTree conponentProject = CacheProject.PROJECTCACHE.get(projectCodeByProjectId.getProjectid());
                gqList.add(conponentProject);
            }
            baseTree1.setChild(gqList);
            all.add(baseTree1);
        }
        baseTree.setChild(all);
        return new ResponseBase(200, "查询成功", baseTree);
    }

    private Integer getGq(Integer i) {
        if (i.intValue() == 4) {
            Integer num = conponentDAO.getIdByName("工区一");
            return num;
        }
        if (i.intValue() == 5) {
            Integer num = conponentDAO.getIdByName("工区二");
            return num;
        }
        if (i.intValue() == 6) {
            Integer num = conponentDAO.getIdByName("工区三");
            return num;
        }
        if (i.intValue() == 7) {
            Integer num = conponentDAO.getIdByName("工区四");
            return num;
        }
        return 0;

    }

    public static List<Conponent> beChild(Conponent root, List<Conponent> all){
        if(root.getId()==1134){
            System.out.println(1);
        }
        List<Conponent> children =Lists.newArrayList();
        for (Conponent conponent : all) {
            if(conponent.getPid()==1134){
                System.out.println(1);
                System.out.println(conponent.getPid());
                System.out.println(root.getId());
                System.out.println(Integer.compare(conponent.getPid(), root.getId()));
            }
            if (Integer.compare(conponent.getPid(), root.getId()) == 0) {
                if (conponent.getPid() == 1134) {
                    System.out.println(1);
                }
                conponent.setChild(beChild(conponent, all));
                children.add(conponent);
            }
        }

//        all.forEach(item->{
//            if(item.getPid()==root.getId()){
//                item.setChild(beChild(item,all));
//                children.add(item);
//            }
//        });
        return children;
    }

    public static List<Conponent> getChildren(Conponent root, List<Conponent> all) {


        List<Conponent> children = all.stream().filter(item -> Integer.compare(item.getPid(), root.getId()) == 0)
                .map((item) -> {
                    item.setChild(getChildren(item, all));
                    return item;
                }).collect(Collectors.toList());

        return children;
    }


    private List<NodeTree> getNodeTree(List<Conponent> list, Integer id) {

        List<NodeTree> nodeTreeList = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (id.intValue() == list.get(i).getPid()) {
                    Conponent conponent = list.get(i);
                    NodeTree nodeTree = new NodeTree();
                    nodeTree.setId(conponent.getId());
                    nodeTree.setMouldId(conponent.getMouldid());
                    nodeTree.setName(conponent.getName());
                    nodeTree.setChild(Lists.newArrayList());
                    nodeTree.setX(conponent.getW9code());
                    nodeTree.setY(conponent.getW10());
                    nodeTree.setZ(conponent.getW10code());
                    //提升性能，修改
                    list.remove(i);
                    nodeTree.setChild(getNodeTree(list, conponent.getId()));
                    List<Conponent> conponentList = Lists.newArrayList();
                    conponentList.add(conponent);
                    nodeTree.setChildConponent(conponentList);
                    nodeTreeList.add(nodeTree);
                }
            }
        }
        return nodeTreeList;
    }

    public void xyz(List<List<String>> list) {
        list.stream().forEach((lists -> {
            String s = lists.get(0);
            Conponent conponent = conponentDAO.checkByConponentCode(s);
            if (ObjectUtils.isEmpty(conponent)) {
                System.out.println(1);
            } else {
                //查出
                ZjConponentProducetime zjConponentProducetime = zjConponentProducetimeDAO.getZjByConponentId(conponent.getId());
                zjConponentProducetime.setMouldid(lists.get(1).toString());
                zjConponentProducetime.setX(lists.get(2).toString());
                zjConponentProducetime.setY(lists.get(3).toString());
                zjConponentProducetime.setZ(lists.get(4).toString());
                zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
            }
        }));
    }

    public void num(List<List<String>> list) {
        list.stream().forEach((l) -> {
            String mouldid = l.get(0);
            ZjConponentProducetime zjConponentProducetime = zjConponentProducetimeDAO.getZjTimeByCode(mouldid);
            List<String> strings = Arrays.asList(l.get(1).split("-"));
            if (ObjectUtils.isEmpty(l.get(2))) {

            } else {

            }
            zjConponentProducetime.setDesginnum(Float.parseFloat(l.get(2)));
            zjConponentProducetime.setActulnum(Float.parseFloat(l.get(3)));
            if (strings.size() == 3) {
                String year = strings.get(0);
                String month = strings.get(1).length() == 1 ? "0" + strings.get(1) : strings.get(1);
                String day = strings.get(2).length() == 1 ? "0" + strings.get(2) : strings.get(2);
                String date = year + "-" + month + "-" + day;
                try {
                    Date date1 = DateUtils.getDateForCon(date);
                    zjConponentProducetime.setXxendtime(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
            } else {
            }

        });
    }

    public void img(List<List<String>> list) {
        list.forEach(lists -> {

        });

    }

    public void init() {
        zjFGroupsProjectsDAO.deleteAll();

        List<Conponent> projects = conponentDAO.getProject();

        for (Conponent project : projects) {
            SsFGroups group = ssFGroupsDao.getGroupInit(project.getName());
            ZjFGroupsProjects zjFGroupsProjects = new ZjFGroupsProjects();
            zjFGroupsProjects.setGroupid(group.getId());
            zjFGroupsProjects.setProjectid(project.getProjectcode());
            zjFGroupsProjects.setProjectname(project.getName());
            zjFGroupsProjects.setStorder(1);
            zjFGroupsProjects.setSttime(new Date());
            zjFGroupsProjects.setStstate(1);
//            zjFGroupsProjects.setGroupid();

            zjFGroupsProjectsDAO.insert(zjFGroupsProjects);
        }
//        SsFGroups group = ssFGroupsDao.getGroup();

    }

    public ResponseBase initxyz() {
        List<Conponent> conponents = conponentDAO.getAllBak();

        for (Conponent conponent : conponents) {
            if (!ObjectUtils.isEmpty(conponent.getW3())) {
                String conponentcode = conponent.getConponentcode();
                if (conponent.getW3().equals("次坞大桥（左线）")) {

                    String[] split = conponentcode.split("-");
                    String first = split[0].replace("GQ01", "GQ02");
                    String end = "Z" + split[split.length - 1];
                    StringBuilder sb = new StringBuilder();
                    sb.append(first).append("-");
                    for (int i = 1; i < split.length - 1; i++) {
                        sb.append(split[i]).append("-");
                    }
//
                    sb.append(end);
                    Conponent conponent1 = conponentDAO.getConponentByCode(sb.toString());
                    if (ObjectUtils.isEmpty(conponent1)) {
                        System.out.println(sb.toString());
                    } else {
                        conponent1.setW9code(conponent.getW9code());
                        conponent1.setW10(conponent.getW10());
                        conponent1.setW10code(conponent.getW10code());
                        conponent1.setMouldid(conponent.getMouldid());
                        conponentDAO.updateByPrimaryKeySelective(conponent1);
                    }
                } else {
                    String[] split = conponentcode.split("-");
                    String first = split[0].replace("GQ01", "GQ02");
                    String end = "Y" + split[split.length - 1];
                    StringBuilder sb = new StringBuilder();
                    sb.append(first).append("-");
                    for (int i = 1; i < split.length - 1; i++) {
                        sb.append(split[i]).append("-");
                    }
//                        sb.append("ZJ").append("-");
                    sb.append(end);
                    Conponent conponent1 = conponentDAO.getConponentByCode(sb.toString());
                    if (ObjectUtils.isEmpty(conponent1)) {
                        System.out.println(sb.toString());
                    } else {
                        conponent1.setW9code(conponent.getW9code());
                        conponent1.setW10(conponent.getW10());
                        conponent1.setW10code(conponent.getW10code());
                        conponent1.setMouldid(conponent.getMouldid());
                        conponentDAO.updateByPrimaryKeySelective(conponent1);
                    }
                }
            }
        }

        return new ResponseBase(200, "更新成功", "更新成功");
    }

    public ResponseBase initConponentproducetime() {
        List<Conponent> conponents = conponentDAO.getAllConponent();
        for (Conponent conponent : conponents) {

            ZjConponentProducetime zjConponentProducetime = new ZjConponentProducetime();

            zjConponentProducetime.setConponentid(conponent.getId());
            zjConponentProducetime.setConponentcode(conponent.getConponentcode());
            zjConponentProducetime.setProjectcode(conponent.getProjectcode());
            zjConponentProducetime.setProjecttype(conponent.getProjecttype());
            zjConponentProducetime.setProjectname(conponent.getW3());
            zjConponentProducetime.setConponenttype(conponent.getConponenttype());
            zjConponentProducetime.setConponenttypename(conponent.getConponenttypename());


            zjConponentProducetimeDAO.insert(zjConponentProducetime);
        }
        return new ResponseBase(200, "更新成功", "更新成功");
    }

    public ResponseBase getComponentData(ComponentProgressDTO progressDTO) {
        //通过id查询构件进度表的信息
        ComponentDataDetail dataDetail = new ComponentDataDetail();
        Conponent conponent = new Conponent();
        if (progressDTO.getMouldid() != null && !progressDTO.getMouldid().equals("")) {
            conponent = conponentDAO.getDataByMouldid(progressDTO.getMouldid());
        } else if (progressDTO.getId() != null && !progressDTO.getId().equals("")) {
            conponent = conponentDAO.getDataById(progressDTO.getId());
        }

        if (conponent == null) {
            return new ResponseBase(500, "没有找到对应的模型编码信息！");
        }
        //通过模型id 查出来的不为空
        if (conponent.getId() != null) {
            List<Produce> produces = produceDAO.getAllByType(conponent.getConponenttype());
            dataDetail.setProduces(produces);

            List<Produceandrecode> produceandrecode = produceandrecodeDAO.getAllByConponentId(conponent.getId());

            ZjConponentProducetime producetime = zjConponentProducetimeDAO.getZjByConponentId(conponent.getId());
            ComponentProgressDetails progressDetails = new ComponentProgressDetails();
            progressDetails.setPlanStartTime(producetime.getPlansttime());
            progressDetails.setPlanEndTime(producetime.getPlanendtime());
            progressDetails.setActualStartTime(producetime.getActulsttime());
            progressDetails.setActualEndTime(producetime.getActulendtime());
            //判断当前构件进度状态：当有实际开始时间和结束时间时-2，有实际开始时间没有结束时间-1，都没有说明未开工-0
//            Integer row1 = produceandrecodeDAO.getCountByComponentid(conponent.getId());
//            Integer row2 = produceDAO.getNumProduce(producetime.getConponenttype());

            Date planEnd = progressDetails.getPlanEndTime();
            Date actStart = progressDetails.getActualStartTime();
            Date actEnd = progressDetails.getActualEndTime();
            Date now = new Date();

            //当计划完成时间为空时, 只判断是否开工, 不判断延期
            if (planEnd == null) {
                if (actStart == null) {
                    //当没有实际开始时间和, 未开工
                    progressDetails.setStatus(0);
                } else if (actStart != null && actEnd == null) {
                    //当有开始时间, 没有结束时间  施工中
                    progressDetails.setStatus(1);
                } else if (actStart != null && actEnd != null) {
                    //当 两个实际时间都不为空  已完工
                    progressDetails.setStatus(2);
                }
            } else {
                //有计划完成时间   需要再判断是否延期
                if (actStart == null && actEnd == null) {
                    if (now.compareTo(planEnd) > 0) {
                        //系统时间大于计划完成时间  延期
                        progressDetails.setStatus(3);
                    } else {
                        //系统时间小于计划完成时间  未开工
                        progressDetails.setStatus(0);
                    }
                } else if (actStart != null && actEnd == null) {
                    //开始时间不为空, 完成时间为空
                    if (now.compareTo(planEnd) > 0) {
                        progressDetails.setStatus(3);
                    } else {
                        //系统时间小于计划完成时间  施工中
                        progressDetails.setStatus(1);
                    }
                } else if (actStart != null && actEnd != null) {
                    //当有完成时间 则不用判断   已完工
                    progressDetails.setStatus(2);
                }
            }

            dataDetail.setProduceandrecodes(produceandrecode);
            dataDetail.setProgressDetails(progressDetails);
            dataDetail.setConponent(conponent);
        }

        return new ResponseBase(200, "查询详细构件进度信息成功！", dataDetail);
    }

    public ResponseBase getSubProject(String type) {
        if (type == null) {
            return new ResponseBase(500, "查询分部工程失败，请核实项目类型有效值！");
        } else if (!type.equals("SD") && !type.equals("QL") && !type.equals("LM")) {
            return new ResponseBase(500, "查询分部工程失败，目前只支持查询桥梁、隧道、路基路面的分部工程！");
        }
        List<String> project = conponentDAO.getProjectTypeByProject(type);
        return new ResponseBase(200, "查询分部工程成功！", project);
    }

    public void uploadxyz(List<List<String>> list) {
        System.out.println(list.size());
        for (List<String> lost : list) {

            if (!lost.get(0).contains("ignore") && !ObjectUtils.isEmpty(lost.get(0))) {
//                System.out.println(lost.get(0));
                Conponent conponentByCode = conponentDAO.getConponentByCode(lost.get(0));
                if (ObjectUtils.isEmpty(conponentByCode)) {
                    System.out.println(conponentByCode);
                } else {
                    conponentByCode.setW9code(String.valueOf(lost.get(1)));
                    conponentByCode.setW10(String.valueOf(lost.get(2)));
                    conponentByCode.setW10code(String.valueOf(lost.get(3)));
                    conponentByCode.setMouldid(String.valueOf(lost.get(0)));
                    conponentDAO.updateByPrimaryKeySelective(conponentByCode);
                }
            }

        }

    }

    public ResponseBase initgq() {
        List<ZjConponentProducetime> zjConponentProducetimes = zjConponentProducetimeDAO.getAll();
        for (ZjConponentProducetime zjConponentProducetime : zjConponentProducetimes) {
            //获取工区

            SsFGroups ssFGroups = zjConponentProducetimeDAO.getByGroup(zjConponentProducetime.getProjectcode());
            zjConponentProducetime.setX(ssFGroups.getName());
            zjConponentProducetime.setY(ssFGroups.getId().toString());
            zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
        }
        return new ResponseBase(200, "成功", "");

    }

    public ResponseBase initxyzmouild() {
        List<ZjConponentProducetime> zjConponentProducetimes = zjConponentProducetimeDAO.getAll();
        for (ZjConponentProducetime zjConponentProducetime : zjConponentProducetimes) {
            //获取工区
            Conponent conponentByCode = conponentDAO.getConponentByCode(zjConponentProducetime.getConponentcode());

            zjConponentProducetime.setX(conponentByCode.getW9code());
            zjConponentProducetime.setY(conponentByCode.getW10());
            zjConponentProducetime.setZ(conponentByCode.getW10code());
            zjConponentProducetime.setMouldid(conponentByCode.getMouldid());

            zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
        }
        return new ResponseBase(200, "成功", "");

    }

    public ResponseBase getProjectTree(Integer id) {
        ZjFGroupsProjects projectCodeByProjectId = zjFGroupsProjectsDAO.getProjectCodeByProjectId(id);
        BaseTree conponentProject = CacheProject.PROJECTCACHE.get(projectCodeByProjectId.getProjectid());
        return new ResponseBase(200, "成功", conponentProject);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseBase addServiceName(MultipartFile uploadFile) {
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            list.stream().forEach((l) -> {
                //获取到文档的第一列   模型bim编码
                String mouldid = l.get(0);
                //根据模型编码去构建表查询
                Integer row = conponentDAO.getByMouldid(mouldid);
                //获取文档的第二列
                String layerName = l.get(1);
                if (row == 1) {
                    conponentDAO.updateLayerName(mouldid, layerName);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(500, "往构件表插入服务名称失败");
        }
        log.info("layername模型名称导入成功！");
        return new ResponseBase(200, "插入成功");
    }

    @Transactional
    public ResponseBase addCode(MultipartFile uploadFile) {
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            list.stream().forEach((l) -> {
                //获取到文档的第7列   新的bim编码
                String componentCode = l.get(0);
                // 获取文档的第10列   老BIM编码
                String oldComponentCode = l.get(9);
                // 获取文档第9列   模型编码
                String modelCode = l.get(8);
                //获取文档的第8列   wbs编码
                String wbsCode = l.get(7);
                //获取文档第3列
                String name = l.get(2);
                Integer row = conponentDAO.getByCode(componentCode);
                if (row == 1) {
                    if (name.contains("桥") || name.contains("匝道")) {
                        //修改模型编码
                        if (modelCode != null && !modelCode.equals("")) {
                            conponentDAO.updateModelCode(componentCode, modelCode);
                        }
                        if (oldComponentCode != null && !oldComponentCode.equals("")) {
                            conponentDAO.updateOldCode(componentCode, oldComponentCode);
                        }
                        if (wbsCode != null && !wbsCode.equals("")) {
                            conponentDAO.updateWbsCode(componentCode, wbsCode);
                        }
                    } else {
                        //修改模型编码
                        if (modelCode != null && !modelCode.equals("")) {
                            conponentDAO.updateModelCode(componentCode, modelCode);
                        }
                        if (wbsCode != null && !wbsCode.equals("")) {
                            conponentDAO.updateWbsCode(componentCode, wbsCode);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(500, "往构件表插入服务名称失败");
        }
        log.info("模型编码、老BIM编码、wbs编码插入成功！");
        return new ResponseBase(200, "插入成功");
    }

    @Transactional
    public ResponseBase addGroups(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = MyExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            //第一遍循环,插入 ssf_groups表
            List<BridgeData> bridgeDatas = new ArrayList<>();
            //遍历list，添加数据
            lists.stream().forEach((l) -> {
                BridgeData bridgeData = new BridgeData();
                //获取文档第二列   所属工区
                bridgeData.setGroupName(l.get(1));
                //获取文档第三列   单位名称
                bridgeData.setBridgeName(l.get(2));
                //新BIM编码
                String mouldid = l.get(0);
                //工区
                String singleGroup = l.get(1);
                //截取   例: GQ02-04Y-01-01-01-06  截取到 04Y
                String projectId = mouldid;
                if (projectId.contains("-")) {
                    projectId = projectId.substring(0, 2);
                }
                if (bridgeData.getBridgeName().contains("隧道")) {
                    projectId = "SD" + projectId;
                } else if (bridgeData.getBridgeName().contains("桥") ||
                        bridgeData.getBridgeName().equals("BK0+187.794草塔互通B匝道")) {
                    projectId = "QL" + projectId;
                } else if (bridgeData.getBridgeName().contains("路基工程") ||
                        bridgeData.getBridgeName().contains("路面工程")) {
                    // GQ-01J-01-01-04-02
                    // GQ-01M-01-01-01-01
                    // 这里路面一个单位工程有可能出现两个工区及以上
                    projectId = mouldid.substring(3, 6);
                    if (singleGroup.equals("工区一")) {
                        projectId = "LM" + projectId + "1";
                    } else if (singleGroup.equals("工区二")) {
                        projectId = "LM" + projectId + "2";
                    } else if (singleGroup.equals("工区三")) {
                        projectId = "LM" + projectId + "3";
                    } else {
                        projectId = "LM" + projectId + "4";
                    }
                }
                bridgeData.setCode(projectId);
                bridgeDatas.add(bridgeData);
            });
            //去重,通过去重工区和名称同时去重
            List<BridgeData> bridgeDataList = bridgeDatas.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                    new TreeSet<>(Comparator.comparing(o -> o.getBridgeName() + "#" + o.getGroupName()))), ArrayList::new));

            for (int i = 0; i < bridgeDataList.size(); i++) {
                Integer count = 0;
                SsFGroups ssFGroups = new SsFGroups();
                ssFGroups.setName(bridgeDataList.get(i).getBridgeName());
                if (bridgeDataList.get(i).getGroupName().equals("工区一")) {
                    count = ssFGroupsDao.getCountByParentId(4);
                    ssFGroups.setCode("1-1-1-1-" + (count + 1));
                    ssFGroups.setParentid(4);
                } else if (bridgeDataList.get(i).getGroupName().equals("工区二")) {
                    count = ssFGroupsDao.getCountByParentId(5);
                    ssFGroups.setCode("1-1-1-2-" + (count + 1));
                    ssFGroups.setParentid(5);
                } else if (bridgeDataList.get(i).getGroupName().equals("工区三")) {
                    count = ssFGroupsDao.getCountByParentId(6);
                    ssFGroups.setCode("1-1-1-3-" + (count + 1));
                    ssFGroups.setParentid(6);
                } else if (bridgeDataList.get(i).getGroupName().equals("工区四")) {
                    count = ssFGroupsDao.getCountByParentId(7);
                    ssFGroups.setCode("1-1-1-4-" + (count + 1));
                    ssFGroups.setParentid(7);
                }
                ssFGroups.setType("province");
                ssFGroups.setGrouplevel(5);
                ssFGroups.setVisible(null);
                ssFGroups.setSttime(new Date());
                ssFGroups.setStstate(1);
                ssFGroups.setStorder(5);
                ssFGroupsDao.insert(ssFGroups);
            }

            for (int i = 0; i < bridgeDataList.size(); i++) {
                int a = 0;
                if (bridgeDataList.get(i).getGroupName().equals("工区一")) {
                    a = 4;
                } else if (bridgeDataList.get(i).getGroupName().equals("工区二")) {
                    a = 5;
                } else if (bridgeDataList.get(i).getGroupName().equals("工区三")) {
                    a = 6;
                } else {
                    a = 7;
                }
                SsFGroups group = ssFGroupsDao.getByName(bridgeDataList.get(i).getBridgeName(), a);
                ZjFGroupsProjects groupsProjects = new ZjFGroupsProjects();
                groupsProjects.setGroupid(group.getId());
                groupsProjects.setProjectid(bridgeDataList.get(i).getCode());
                groupsProjects.setSttime(new Date());
                groupsProjects.setStstate(1);
                groupsProjects.setStorder(1);
                groupsProjects.setProjectname(bridgeDataList.get(i).getBridgeName());
                groupsProjects.setProjecttype(bridgeDataList.get(i).getCode().substring(0, 2));
                zjFGroupsProjectsDAO.insert(groupsProjects);
            }
            log.debug("往组织表插入组织成功！");
            return new ResponseBase(200, "插入成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase(500, "批量插入失败!");
    }

    @Transactional
    public ResponseBase supplementData() {

        List<ZjConponentProducetime> conponentProducetimes = zjConponentProducetimeDAO.getAllsByConponentTypeName();
        List<String> typenames = Lists.newArrayList();
        for (ZjConponentProducetime producetime : conponentProducetimes) {
            String conponenttypename = producetime.getConponenttypename();
            String conponenttype = producetime.getConponenttype();
            typenames.add(conponenttype);
            String projecttype = producetime.getProjecttype();
            if (conponenttypename == null) {
                if (projecttype.equals("QL")) {
                    ComponentType.setConponentTypeName(producetime, conponenttype);
                } else if (projecttype.equals("SD")) {
                    Produce produce = produceDAO.getByType(conponenttype);
                    producetime.setConponenttypename(produce.getName());
                } else if (projecttype.equals("LM")) {
                    ComponentType.setLMDLTypeName(producetime, conponenttype);
                }
                zjConponentProducetimeDAO.updateByPrimaryKey(producetime);
            }
        }
        log.info("补齐数据成功！");
        return new ResponseBase(200, "补齐数据成功!");
    }

    @Transactional
    public ResponseBase updateProduceTimeGroupData() {
        List<ZjConponentProducetime> producetimes = zjConponentProducetimeDAO.getAllsByGongquidAndGongquname();
        for (ZjConponentProducetime producetime : producetimes) {
            //获取构件编码
            String bimCode = producetime.getConponentcode();
            Conponent conponent = conponentDAO.getConponentByCode(bimCode);
            producetime.setProjectcode(conponent.getProjectcode());
            producetime.setProjecttype(conponent.getProjecttype());
            producetime.setConponenttype(conponent.getConponenttype());
            producetime.setProjectname(conponent.getW3());
            //添加工区id
            String project = producetime.getProjectcode();
            Integer groupid = zjFGroupsProjectsDAO.getGroupidByProjectid(project);
            SsFGroups groups = ssFGroupsDao.selectByPrimaryKey(groupid);
            if (groups.getParentid() == 4) {
                producetime.setGongquid("4");
                producetime.setGongquname("工区一");
            } else if (groups.getParentid() == 5) {
                producetime.setGongquid("5");
                producetime.setGongquname("工区二");
            } else if (groups.getParentid() == 6) {
                producetime.setGongquid("6");
                producetime.setGongquname("工区三");
            } else if (groups.getParentid() == 7) {
                producetime.setGongquid("7");
                producetime.setGongquname("工区四");
            }
            zjConponentProducetimeDAO.updateGongQu(producetime);
        }
        log.info("添加构建进度标工区方法执行完毕! ");
        return new ResponseBase(200, "补齐数据成功!");
    }

    @Transactional
    public ResponseBase updateProduceData() {
        List<Produceandrecode> produceandrecodes = produceandrecodeDAO.getAllAsc();
        for (Produceandrecode produceandrecode : produceandrecodes) {
            String modelCode = produceandrecode.getConponentcode();
            String newModelCode = StringUtils.replaceBlank(modelCode);
            List<String> items = Arrays.asList(newModelCode.split("-"));
            Integer row = conponentDAO.getByOldCode(newModelCode);
            if (row == 1) {
                Conponent conponent = conponentDAO.getDataByOldCode(newModelCode);
                produceandrecodeDAO.updateModelCode(conponent.getId(), conponent.getConponentcode(), modelCode);
            }
        }

        return new ResponseBase(200, "更新编码完毕！");
    }

    @Transactional
    public ResponseBase updateProgressDetail() {
        //因目前构件进度表有数据，因此这里查询备份表中四个时间不为空的数据
        List<ComponentProgressTimes> producetimeList = zjConponentProducetimeDAO.getCopyAll();
        for (ComponentProgressTimes producetime : producetimeList) {
            zjConponentProducetimeDAO.updateTimesByCopy(producetime);
        }
        log.info("通过构件进度备份表导入时间数据成功！");
        return new ResponseBase(200, "更新构件进度表完毕！");
    }

    @Transactional
    public ResponseBase updatePjtType() {
        //获取到所有componentCode 不为null的
        List<Conponent> conponents = conponentDAO.getAllConponent();
        for (Conponent conponent : conponents) {
            ZjConponentProducetime producetime = new ZjConponentProducetime();
            producetime.setPjctype(conponent.getW4());
            producetime.setConponentid(conponent.getId());

            zjConponentProducetimeDAO.updatePjcType(producetime);
        }
        log.info("更新构建进度表 构件结构完毕！");
        return new ResponseBase(200, "更新构件结构完毕！");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseBase updateConponentXYZ(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = MyExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            lists.stream().forEach((list) -> {
                //获取到文档第一列模型编码
                String moduldid = list.get(0);
                // X
                String axisX = String.valueOf(list.get(9));
                // Y
                String axisY = String.valueOf(list.get(10));
                // Z
                String axisZ = String.valueOf(list.get(11));

                Integer row = conponentDAO.getByMouldid(moduldid);
                if (row == 1) {
                    conponentDAO.updateByMould(axisX, axisY, axisZ, moduldid);
                }
            });

            log.info("更新构建表XYZ轴成功！");
            return new ResponseBase(200, "更新构建表XYZ轴成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("更新构建表XYZ轴失败！");
        return new ResponseBase(500, "更新构建表XYZ轴失败!");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseBase updateConponentProducetime(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = MyExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            Long long1 = System.currentTimeMillis();
            System.out.println("需要处理的数据有：" + lists.size() + "条");
            for (List<String> list : lists) {
                ZjConponentProducetime time = new ZjConponentProducetime();
                //String转Date
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取到文档第三列构件编码
                String code = String.valueOf(list.get(2));
                //获取文档第二列构件id
                String strId = String.valueOf(list.get(1));
                Integer id = Integer.parseInt(strId);
                // 计划开始时间
                Date date1 = null;
                Date date2 = null;
                Date date3 = null;
                Date date4 = null;
                //计划开始时间
                String plansttime = String.valueOf(list.get(3));
                // 计划结束时间
                String planendtime = String.valueOf(list.get(4));
                // 实际开始时间
                String actulsttime = String.valueOf(list.get(5));
                // 实际开始时间
                String actulendtime = String.valueOf(list.get(6));
                if (plansttime == null || plansttime.equals("null") || plansttime.equals("")) {
                    if (planendtime == null || planendtime.equals("null") || planendtime.equals("")) {
                        if (actulsttime == null || actulsttime.equals("null") || actulsttime.equals("")) {
                            if (actulendtime == null || actulendtime.equals("null") || actulendtime.equals("")) {
                                continue;
                            }
                        }
                    }
                }
                if (plansttime != null && !plansttime.equals("") && !plansttime.equals("null")) {
                    date1 = simpleDateFormat.parse(plansttime);
                }
                if (planendtime != null && !planendtime.equals("") && !planendtime.equals("null")) {
                    date2 = simpleDateFormat.parse(planendtime);
                }
                if (actulsttime != null && !actulsttime.equals("") && !actulsttime.equals("null")) {
                    date3 = simpleDateFormat.parse(actulsttime);
                }
                if (actulendtime != null && !actulendtime.equals("") && !actulendtime.equals("null")) {
                    date4 = simpleDateFormat.parse(actulendtime);
                }
                time.setPlansttime(date1);
                time.setPlanendtime(date2);
                time.setActulsttime(date3);
                time.setActulendtime(date4);
                time.setConponentcode(code);
                time.setConponentid(id);
                zjConponentProducetimeDAO.updateTime(time);
            }

            Long long2 = System.currentTimeMillis();
            log.info("更新构建进度表时间成功！共计花费：" + (long2 - long1) / 1000 + "秒。");
            System.out.println("更新构建进度表时间成功！共计花费：" + (long2 - long1) / 1000 + "秒。");
            return new ResponseBase(200, "更新构建进度表时间成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(500, "更新构建进度表时间失败!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseBase updateProduceId() {
        log.info("开始执行: 修改ss_f_projects与zj_f_groups_projects关系表 的方法..");
        List<SsFProjects> projects = projectsDAO.getAllSingleProjects();
        for (SsFProjects project : projects) {
            String name = project.getName();
            Integer id = project.getId();

            ZjFGroupsProjects groupsProject = zjFGroupsProjectsDAO.getProjectByProjectName(name);
            if (groupsProject != null) {
                zjFGroupsProjectsDAO.updateGroupId(id, name);
            }
        }
        log.info("方法执行完毕!");

        return new ResponseBase(200, "执行方法完成!");
    }
}


