package com.ruoyi.jianguan.common.service;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.config.zjrw.WebSocketServer;
import com.ruoyi.common.constant.ComponentType;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.dto.WebsocketMessageDTO;
import com.ruoyi.common.core.domain.entity.*;
import com.ruoyi.common.core.domain.model.Head;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.jianguan.common.dao.*;
import com.ruoyi.jianguan.common.domain.dto.*;
import com.ruoyi.jianguan.common.domain.entity.NodeTree;
import com.ruoyi.jianguan.common.domain.entity.ProduceandrecodeUser;
import com.ruoyi.jianguan.common.domain.entity.Recode;

import com.ruoyi.common.utils.jianguan.zjrw.DateUtils;
import com.ruoyi.common.utils.jianguan.zjrw.MyExcelUtil;
import com.ruoyi.common.utils.jianguan.zjrw.RestApiUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.jianguan.zjrw.DateUtils.*;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/1 3:01 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

@Service
public class ProduceService {

    Logger log = LoggerFactory.getLogger(ProduceService.class);

    @Autowired
    SsFGroupsDAO ssFGroupsDAO;

    @Autowired
    SsFUserRoleDAO userRoleDAO;

    @Autowired
    FileService fileService;

    @Autowired
    ProduceDAO produceDAO;

    @Autowired
    @Qualifier("zjConponentDAO")
    ConponentDAO conponentDAO;

    @Autowired
    SsFUserOnlineDAO userOnlineDAO;

    @Autowired
    private ProjectsDAO projectsDAO;

    @Autowired
    FileMapper fileMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    RecodedetailDAO recodedetailDAO;

    @Autowired
@Qualifier("zjProduceandrecodeDAO")
    ProduceandrecodeDAO produceandrecodeDAO;
    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    @Qualifier("zjFGroupsProjectsDAO")
    ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;
    @Autowired
    RecodeDAO recodeDAO;

    @Autowired
    ZjConponentProducetimeDAO zjConponentProducetimeDAO;
    @Autowired
    SsFUsersDAO ssFUsersDAO;
    @Autowired
    WebSocketServer webSocketServer;

    public ResponseBase getRecodeStatus(RecodeQueryData recodeQueryData) {
        if (recodeQueryData.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(recodeQueryData.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //根据前端传的 项目标段id查询对应单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(recodeQueryData.getProjectId());

        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> groups = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        String type = recodeQueryData.getType();
        //获取用户所拥有的单位工程code
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        //得到前端传的项目标段下面的所有单位工程code 和该用户拥有权限的单位工程code交集
        List<String> intersectionList = (List<String>) CollectionUtils.intersection(proChildCode, projects);
        //先判断前端传过来的工区id是否是有值,没有值直接跳过该判断
        if (recodeQueryData.getGongquid() != null){
            //当要查询的工区id 不在该用户拥有的工区权限内,直接返回
            if (!groups.contains(recodeQueryData.getGongquid())){
                List<Head> tohead = tohead(type);
                List<ProduceRecord> listss = new ArrayList<>();
                Collections.sort(tohead);
                Map<String,Object> data = new HashMap<>();
                data.put("list", listss);
                data.put("head", tohead);
                return new ResponseBase(200, "要查询的工区不在用户所拥有的工区权限内! ", data);
            }
        }

        //查询所有的工序对应项目
        List<ZjFGroupsProjects> listttt = zjFGroupsProjectsDAO.getAll();
        //查询所有的工序
        List<Produce> allProduce = produceDAO.getAll();
        Map<Integer, List<Produce>> collect2 = allProduce.stream().collect(Collectors.groupingBy(Produce::getId));
        Map<String, String> collect1 =
                listttt.stream().collect(Collectors.toMap(ZjFGroupsProjects::getProjectid, ZjFGroupsProjects::getProjectname));

        //通过工区来查询该工区有哪些项目code
        List<String> groupsProjectsDTOs = zjFGroupsProjectsDAO.getByGonquId(recodeQueryData.getGongquid());

        if(ObjectUtils.isEmpty(recodeQueryData.getCode())){
            recodeQueryData.setCode(null);
        }

        //当前端没有传工区id时,默认查询该用户所拥有的projectcode
        if (recodeQueryData.getGongquid() == null){
            if(ObjectUtils.isEmpty(recodeQueryData.getList())){
                recodeQueryData.setList(intersectionList);
            }
            Map<String,Object> data = processData(type, collect2, collect1, recodeQueryData);
            return new ResponseBase(200,"查询成功", data);
        } else{
            //前端传有工区id时, 使用前端传过来的工区id
            if(ObjectUtils.isEmpty(recodeQueryData.getList())){
                recodeQueryData.setList(groupsProjectsDTOs);
            }
            Map<String,Object> data = processData(type, collect2, collect1, recodeQueryData);
            return new ResponseBase(200,"查询成功", data);
        }
    }

    private List<Head>  tohead(String type){
        List<Head> heads = ProduceRecord.toMap();
        int num = heads.size();
        List<Produce> produces = produceDAO.getAllByType(type);
        if (produces.size() == 1){
            heads.add(new Head(produces.get(0).getName(),"one",num + 1));
        }else if (produces.size() == 2){
            heads.add(new Head(produces.get(0).getName(),"one",num + 1));
            heads.add(new Head(produces.get(1).getName(),"two",num + 2));
        } else if (produces.size() == 3){
            heads.add(new Head(produces.get(0).getName(),"one",num + 1));
            heads.add(new Head(produces.get(1).getName(),"two",num + 2));
            heads.add(new Head(produces.get(2).getName(),"three",num + 3));
        } else if (produces.size() == 4){
            heads.add(new Head(produces.get(0).getName(),"one",num + 1));
            heads.add(new Head(produces.get(1).getName(),"two",num + 2));
            heads.add(new Head(produces.get(2).getName(),"three",num + 3));
            heads.add(new Head(produces.get(3).getName(),"four",num + 4));
        }
        return heads;
    }


    private boolean getStatus(Float key, Integer produceid) {
        float k =produceid;
        return key==k;
    }

    private Map<Float, String>  dealBasicData(Float key, String[] record,
                                              Map<Float, String> smallMap) throws Exception {
        if (key == 0.1f) {
            //工区
            smallMap.put(key, record[3]);
        } else if (key == 0.2f) {
            //项目
            smallMap.put(key, record[2]);
        } else if (key == 0.3f) {
            //构件类型
            smallMap.put(key, record[5]);
        } else if(key == 0.4f){
            //墩号与孔号
            smallMap.put(key, record[7]);
        }else if(key == 0.5f){
            //构件编码
            smallMap.put(0f, record[0]);
            smallMap.put(key, record[1]);

        }else if(key == 100000.5f){
            smallMap.put(key, getDate(record[8]));
        }else if(key == 110000.5f){
            smallMap.put(key, getDate(record[9]));
        }else if(key == 120000.5f){
            smallMap.put(key, getDate(record[10]));
        }else if(key == 130000.5f){
            smallMap.put(key, getDate(record[11]));
        }else if(key == 140000.5f){
            smallMap.put(key, getDate(record[12]));
        }else if(key == 150000.5f){
            String xxwanc=getDate(record[13]);
            smallMap.put(key, xxwanc);
            if(!StringUtil.isEmpty(xxwanc)){
                smallMap.put(180000.5f, "finish");
            }else{
                smallMap.put(180000.5f, "notFinish");
            }
        }else {
            return smallMap;
        }
        return smallMap;
    }

    public ResponseBase getType() {

        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);

        NodeTree nodeTree = new NodeTree();
        nodeTree.setName("汇总");

        //查询分类数据
        List<NodeTree> list =Lists.newArrayList();

        List<ZjFGroupsProjects> lists = zjFGroupsProjectsDAO.getProjectCodeByProjectIds(projects);

        Map<String, List<ZjFGroupsProjects>> collect = lists.stream().
                collect(Collectors.groupingBy(ZjFGroupsProjects::getProjecttype));

        collect.forEach((projectType,zjFGroupsProjects)->{
            NodeTree nodeTree1 =new NodeTree();
            String name = getItemName(projectType);
            nodeTree1.setName(name);
            List<NodeTree> nodeTreeList = Lists.newArrayList();
            zjFGroupsProjects.stream().forEach((zjFGroupsProject)->{
                Object o = redisTemplate.opsForValue().get(zjFGroupsProject.getProjectid()+"_"+"produce");
                String JSONStr = JSON.toJSONString(o);
                NodeTree nodeTree11 = JSON.parseObject(JSONStr, NodeTree.class);
                nodeTreeList.add(nodeTree11);
            });
            nodeTree1.setChild(nodeTreeList);
            list.add(nodeTree1);
        });
        nodeTree.setChild(list);
//        List<Conponent> conponentList = conponentDAO.getConponentByRule(projects);
//        //根据 工程分组
//        Map<String, List<Conponent>> groupByItem = conponentList.stream().
//                collect(Collectors.groupingBy(Conponent::getProjecttype));
//        //

//        List<NodeTree> nodes = Lists.newArrayList();
//        groupByItem.forEach((item,conponents)->{
//            NodeTree nodeTreeChild = new NodeTree();
//            String name = getItemName(item);
//            //设置名字
//            nodeTreeChild.setName(name);a
//            Map<String, List<Conponent>> typeGroup = conponents.stream().
//                    collect(Collectors.groupingBy(Conponent::groupType));
//            List<NodeTree> childNodes = Lists.newArrayList();
//            typeGroup.forEach((splitName,conponents1)->{
//                String[] s = splitName.split("_");
//                NodeTree nodeTree1= new NodeTree();
//                nodeTree1.setName(s[0]);
//                nodeTree1.setCode(s[1]);
//                nodeTree1.setChildConponent(conponents1);
//                childNodes.add(nodeTree1);
//            });
//            nodeTreeChild.setChild(childNodes);
//            nodes.add(nodeTreeChild);
//        });
//        nodeTree.setChild(nodes);
        return new ResponseBase(200,"查询成功",nodeTree);
    }

    private String getItemName(String item) {
        if("QL".equals(item)){
            return "桥梁工程";
        }else if("DD".equals(item)){
            return "地道工程";
        }else{
            return "隧道工程";
        }
    }

    public ResponseBase getCheckDataByConponentId(Integer conponentId) {
        // 获取工序进度表数据
        ZjConponentProducetime zjConponentProducetime = zjConponentProducetimeDAO.getZjByConponentId(conponentId);
        String type = zjConponentProducetime.getConponenttype();
        List<Produce> lists = produceDAO.getProduce(type);
        List<ProduceData> res = new ArrayList<>();
        for (Produce list : lists) {
            //封装数据
            ProduceData produceData = new ProduceData();
            produceData.setSort(list.getRange());
            produceData.setName(list.getName());
            produceData.setProduceid(list.getId());
            Produceandrecode produceandrecode = produceandrecodeDAO.getByIdAndProduceId(conponentId,list.getId());
            if(!ObjectUtils.isEmpty(produceandrecode)){
                produceData.setFinish(produceandrecode.getUpdatetime());
                //2022.3.17  这里finish 修改为sttime, 工序完成时间使用
//                produceData.setFinish(produceandrecode.getStime());
                produceData.setRecordid(produceandrecode.getRecodeid());
                //  #106  modify yangaogao 20230403 增加Status的返回，按照数据库实例对象。 S
                // 之前将 checkresult 转为status返回了，不知道用意何在。而且checkresult的值，和数据库中定义的字典不匹配，3不知道是什么。因此统一改为用status，让前台也修改。
                //可能因为ProduceData 中status的定义是int, new对象后，默认是0，而和数据库中status=0其实是有业务含义的，冲突了。因此新加了checkresult字段，用于代替“提交未审核”状态。
                produceData.setStatus(produceandrecode.getStatus());
            }
            res.add(produceData);

        }
        Map<String,Object> map = new HashMap();
        map.put("data",zjConponentProducetime);
        map.put("check",res);
        return new ResponseBase(200,"查询成功",map);

    }

    public ResponseBase getCheckDataById(Integer id) {
        SingleCheckData singleCheckData =new SingleCheckData();

        Recode recode = recodeDAO.selectByPrimaryKey(id);
        singleCheckData.setRecode(recode);

        Produceandrecode produceandrecode = produceandrecodeDAO.getProduceandReconde(id);
        singleCheckData.setProduceandrecode(produceandrecode);
        if(recode.getFillinid()!=null){

            singleCheckData.setFillin(
                    recodedetailDAO.selectByPrimaryKey(recode.getFillinid()));
        }
        if(recode.getCheckid()!=null){
            singleCheckData.setCheck(recodedetailDAO.selectByPrimaryKey(recode.getCheckid()));
        }

        return new ResponseBase(200,"查询成功",singleCheckData);
    }

    public ResponseBase uploadRecode(RecodeUploadData recodeData) {
        if (recodeData.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(recodeData.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //查询最开始上报的信息
        Integer produceid = recodeData.getProduceid();
        // 根据produceid 判断是否是第一个时间
        int findfirstProduceId = recodedetailDAO.compareProduceId(produceid);
        //如果相等，那么就插入一个 形象开始时间
        int findLastProduceid = recodedetailDAO.compareToProduceId(produceid);

        ZjConponentProducetime zjConponentProducetime1 =
                zjConponentProducetimeDAO.getZjByConponentId(recodeData.getConpoentid());
        if(findfirstProduceId == produceid ){
            //修改项目进度表的形象开始时间
            int conpoentid = recodeData.getConpoentid();
            ZjConponentProducetime zjConponentProducetime =
                    zjConponentProducetimeDAO.getZjByConponentId(conpoentid);
            // 2022-11-11 按甲方要求， 填报不用填上计划时间
//            zjConponentProducetime.setPlanendtime(
//                    ObjectUtils.isEmpty(recodeData.getUploadtime())?
//                            new Date():recodeData.getUploadtime());
            zjConponentProducetime.setActulsttime(new Date());
            zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
        }else if(findLastProduceid == produceid){
            //修改项目进度表的计划完成时间
            int conpoentid = recodeData.getConpoentid();
            ZjConponentProducetime zjConponentProducetime =
                    zjConponentProducetimeDAO.getZjByConponentId(conpoentid);
//            zjConponentProducetime.setPlanendtime(
//                    ObjectUtils.isEmpty(recodeData.getUploadtime())?
//                            new Date():recodeData.getUploadtime());
            zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
        }

        Integer userId = LoginHelper.getUserId().intValue();


        //往recode表查一个数据
        Recode recode = new Recode();
        recode.setCreatetime(new Date());
        //新加四个url
        recode.setRemark(recodeData.getRemark());
        recode.setAccrecodeurl(recodeData.getAccrecodeurl());
        recode.setStandbyrecode(recodeData.getStandbyrecode());
        recode.setTesturl(recodeData.getTesturl());

        recodeDAO.insert(recode);

        Produceandrecode produceandrecode = new Produceandrecode();
        //审核通过后才设置完成时间
//        produceandrecode.setUpdatetime(new Date());
        produceandrecode.setConponentcode(zjConponentProducetime1.getConponentcode());
        //2022.3.17  sttime字段当作   该条提交工序完成时间
        produceandrecode.setStime(recodeData.getUploadtime());
        produceandrecode.setCheckusername(recodeData.getCheckusername());
        produceandrecode.setCheckuser(recodeData.getCheckid());
        produceandrecode.setUpdateusername(recodeData.getUpdateusername());
        //这里前端有可能没有传上传用户id,修改成从token中获取
        produceandrecode.setUpdateuser(userId);
        produceandrecode.setConponentname(recodeData.getConponentname());
        produceandrecode.setConponenttype(recodeData.getConponenttype());
        produceandrecode.setConponentid(recodeData.getConpoentid());
        produceandrecode.setProducename(recodeData.getProduceidname());
        produceandrecode.setProjectcode(recodeData.getProjectcode());
        produceandrecode.setProduceid(recodeData.getProduceid());
        produceandrecode.setRecodeid(recode.getId());
        produceandrecode.setCheckresult(3);
        //新增工序状态
        produceandrecode.setStatus(0);
        produceandrecode.setProjectId(recodeData.getProjectId());
        //在插入之前先通过编码查询该条数据是否存在，如存在则修改
        Integer row = produceandrecodeDAO.getCountById(produceandrecode.getConponentid(), produceandrecode.getProduceid());
        if (row == 1){
            produceandrecodeDAO.updateByCode(produceandrecode);
        } else {
            //插入数据工序填报
            produceandrecodeDAO.insert(produceandrecode);
        }


        // 发送通知给监理
        List<Produceandrecode> obj = new ArrayList<>();
        obj.add(produceandrecode);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        List<Integer> userIds = new ArrayList<>();
        userIds.add(produceandrecode.getCheckuser());
        messageDTO.setUserIds(userIds);
        messageDTO.setEvent("施工上传填报记录");
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(produceandrecode.getCheckuser());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一个新的工序报验待确认");
        }

        return new ResponseBase(200,"插入成功");
    }

    public ResponseBase updateRecode(RecodeUploadData recodeData){
        if (recodeData.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(recodeData.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        if (recodeData.getRecodeid() == null){
            return new ResponseBase(500, "修改失败!", "recodeid为空，无法查到需要修改的工序填报记录！");
        }
        //通过主键id查询工序填报表记录
        Produceandrecode produceandrecode1 = produceandrecodeDAO.selectByRecodeId(recodeData.getRecodeid(), recodeData.getProduceid());
        //被驳回之后重新提交， 修改构建进度表的计划开始时间
        //修改项目进度表的形象开始时间
        int conpoentid = produceandrecode1.getConponentid();
        ZjConponentProducetime zjConponentProducetime =
                zjConponentProducetimeDAO.getZjByConponentId(conpoentid);
        zjConponentProducetime.setPlanendtime(
                ObjectUtils.isEmpty(recodeData.getUploadtime())?
                        new Date():recodeData.getUploadtime());
        zjConponentProducetimeDAO.updateByPrimaryKeySelective(zjConponentProducetime);
        // 从token中获取上传填报记录人的id
        Integer userId = LoginHelper.getUserId().intValue();
        //往recode表查一个数据
        Recode recode = new Recode();
        recode.setCreatetime(new Date());
        //新加四个url
        recode.setRemark(recodeData.getRemark());
        recode.setAccrecodeurl(recodeData.getAccrecodeurl());
        recode.setStandbyrecode(recodeData.getStandbyrecode());
        recode.setTesturl(recodeData.getTesturl());
        recodeDAO.insert(recode);
        //修改工序填报
        Produceandrecode produceandrecode = new Produceandrecode();
        produceandrecode.setId(produceandrecode1.getId());
        produceandrecode.setUpdatetime(new Date());
        produceandrecode.setConponentcode(produceandrecode1.getConponentcode());
        produceandrecode.setStime(recodeData.getUploadtime());
        produceandrecode.setCheckusername(recodeData.getCheckusername());
        produceandrecode.setCheckuser(recodeData.getCheckid());
        produceandrecode.setUpdateusername(recodeData.getUpdateusername());
        produceandrecode.setUpdateuser(userId);
        produceandrecode.setConponentname(recodeData.getConponentname());
        produceandrecode.setConponenttype(recodeData.getConponenttype());
        produceandrecode.setConponentid(conpoentid);
        produceandrecode.setProducename(recodeData.getProduceidname());
        produceandrecode.setProjectcode(recodeData.getProjectcode());
        produceandrecode.setProduceid(recodeData.getProduceid());
        produceandrecode.setRecodeid(recode.getId());
        produceandrecode.setCheckresult(3);
        produceandrecode.setStatus(0);
        produceandrecode.setProjectId(recodeData.getProjectId());
        produceandrecodeDAO.updateByCode(produceandrecode);

        // 发送通知给监理
        List<Produceandrecode> obj = new ArrayList<>();
        obj.add(produceandrecode);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        List<Integer> userIds = new ArrayList<>();
        userIds.add(produceandrecode.getCheckuser());
        messageDTO.setUserIds(userIds);
        messageDTO.setEvent("施工重新上传填报记录");
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(produceandrecode.getCheckuser());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一个新的工序报验待确认");
        }

        return new ResponseBase(200, "重新填报工序记录成功！");
    }

    public ResponseBase getChecker(String group, Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<SsFUsers> ssFUsers = new ArrayList<>();
        if (group.equals("") || group == null){
            ssFUsers = ssFGroupsDAO.getCheckByGongQi(projectId);
        } else {
//            if (group.equals("GQ01")){
//                i = 4;
//            } else if (group.equals("GQ02")){
//                i = 5;
//            } else if (group.equals("GQ03")){
//                i = 6;
//            } else if (group.equals("GQ04")){
//                i = 7;
//            }

            //   0707   group改成传构件code
            Integer i = projectsDAO.getProjectIdByCode(group);

            ssFUsers = ssFGroupsDAO.getCheckByGongQu(i, projectId);
        }
        return new ResponseBase(200,"查询成功",ssFUsers);
    }

    public ResponseBase getPorjectItem() {
        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        //根据项目获取项目名字
        List<ZjFGroupsProjects> zjFGroupsProjectsList =zjFGroupsProjectsDAO.getDeatail(projects);
        //根据项目类型分类
        Map<String, List<ZjFGroupsProjects>> projectTypeMap = zjFGroupsProjectsList.stream().
                collect(Collectors.groupingBy(ZjFGroupsProjects::getProjecttype));
        return new ResponseBase(200,"查询成功",projectTypeMap);
    }

    public ResponseBase getUnitProjectCheckType(String projectid) {
        //根据项目id 获取项目下所有的构件检查类型

        //查询produce表，获取produce的所有
        List<String> nameType = conponentDAO.getCheckTypeAndName(projectid);
        Map<String,String> typeNames =Maps.newHashMap();
        nameType.stream().forEach((typeName)->{
            String[] split = typeName.split("-");
            typeNames.put(split[0],split[1]);
        });
        return new ResponseBase(200,"查询成功",typeNames);
    }

    public ResponseBase getAgency(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer id = LoginHelper.getUserId().intValue();
        //根据 用户id 查询代办
        List<Produceandrecode> produceandrecodeList = produceandrecodeDAO.getAgencyByUserid(id, projectId);
        return new ResponseBase(200,"查询成功", produceandrecodeList);
    }

    public ResponseBase check(RecondSubmitData recondSubmitData) {

        Integer recondid = recondSubmitData.getRecondid();
        Integer produceId = recondSubmitData.getProduceId();
        Integer result = recondSubmitData.getResult();
        //先获取对应的checkid
        Integer checkId = produceandrecodeDAO.getCheckuserByRecodeid(recondid, produceId);
        Produceandrecode produceandrecode = new Produceandrecode();
        if (recondSubmitData.getCopyUsers() != null && !recondSubmitData.getCopyUsers().equals("")){
            produceandrecode.setCopyUsers(recondSubmitData.getCopyUsers());
        }
        //判断监理是驳回还是同意
        if(result==1){
            produceandrecodeDAO.updateRecondStatus(recondid, result, produceId, produceandrecode.getCopyUsers());
            // 2022-03-21 判断该工序是否为最后一步   获取conponenttype和produceid
            produceandrecode = produceandrecodeDAO.selectByRecodeId(recondid, produceId);
            String conponenttype = produceandrecode.getConponenttype();
            //通过这俩值查询工序表里面对应的工序是否为最后一步,是则添加上构件进度表完成时间
            Integer produceSum = produceDAO.getNumProduce(conponenttype);
            if (produceSum == 1){
                //当工序只有一步的时候直接插入
                String conponentcode = produceandrecodeDAO.getConpontentCodeByRecodeId(recondid, produceId);
                zjConponentProducetimeDAO.updateFinishTime(conponentcode, new Date());
            } else{
                //当工序不止一步时，通过工序查询
                List<Integer> produces = produceDAO.getIdByTypeDesc(conponenttype);
                if (produceId != null && !produceId.equals("")){
                    if (produces.get(0).equals(produceId)){
                        String conponentcode = produceandrecodeDAO.getConpontentCodeByRecodeId(recondid, produceId);
                        zjConponentProducetimeDAO.updateFinishTime(conponentcode, new Date());
                    }
                } else {
                    produceId = produceandrecode.getProduceid();
                    String conponentcode = produceandrecodeDAO.getConpontentCodeByRecodeId(recondid, produceId);
                    zjConponentProducetimeDAO.updateFinishTime(conponentcode, new Date());
                }
            }

            //当审批确认最后, 通知抄送人
            List<Integer> copyUserIds = Lists.newArrayList();
            if (recondSubmitData.getCopyUsers() != null && !recondSubmitData.getCopyUsers().equals("")){
                List<String> strIds = Arrays.asList(recondSubmitData.getCopyUsers().split(","));
                //去重
                strIds = strIds.stream().distinct().collect(Collectors.toList());
                for (String strId : strIds) {
                    Integer id = Integer.parseInt(strId);
                    //先把抄送人id和该工序存入工序抄送表内
                    ProduceandrecodeUser user = new ProduceandrecodeUser();
                    user.setGid(produceandrecode.getId());
                    user.setUserid(id);
                    user.setStstate(0);
                    user.setStorder(1);
                    produceandrecodeDAO.insertProduceUser(user);

                    String cid = userOnlineDAO.getCidByUserid(id);
                    RestApiUtils.sendMessageToCid(cid, "新消息通知！", "工序报验已通过，请悉知！");

                    //把id放入list中
                    copyUserIds.add(id);
                }
                //再发送websocket通知
                WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
                messageDTO.setEvent("工序审核已通过");
                messageDTO.setUserIds(copyUserIds);
                webSocketServer.onMessage(JSON.toJSONString(messageDTO), null);
            }
        }else if(result==2){
            produceandrecode = produceandrecodeDAO.selectByRecodeId(recondid, produceId);
            produceandrecodeDAO.updateReject(recondid, produceId);
        }
        //往recode表插入url
        Recode recode = new Recode();
        recode.setId(recondSubmitData.getRecondid());
        recode.setStandbyrecode(recondSubmitData.getStandbyrecode());
        recode.setTesturl(recondSubmitData.getTesturl());
        recode.setCreatetime(new Date());
        recodeDAO.updateUrl(recode);

        //往websocket发送消息
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        List<Integer> userIds = new ArrayList<>();
        userIds.add(checkId);
        messageDTO.setUserIds(userIds);
        messageDTO.setEvent("监理审核工序");
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(checkId);
        if(result==1){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "工序报验已通过，请确认！");
            }
        }else if(result==2){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "工序报验已驳回，请确认！");
            }
        }
        return new ResponseBase(200,"提交成功");
    }

    public ResponseBase getAllcheckData(Integer type, Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = LoginHelper.getUserId().intValue();
        //如果是1，查询报检数据
        List<Produceandrecode> produceandrecodeList =Lists.newArrayList();
        if(type==1){
            produceandrecodeList = produceandrecodeDAO.getRecodeModelByCheckUser(userid, projectId);
        }
        //如果是2，查询填报数据
        if(type == 2){
            produceandrecodeList = produceandrecodeDAO.getRecodeModelByUpdateUser(userid, projectId);
        }
        //排序
        Comparator<Produceandrecode> comparator =
                (t1, t2) -> t1.getUpdatetime().compareTo(t2.getUpdatetime());
        produceandrecodeList.sort(comparator.reversed());
        if(produceandrecodeList.size()==0){
            return  new ResponseBase(200,"没查询到数据");
        }
        return  new ResponseBase(200,"查询成功", produceandrecodeList);
    }

    public ResponseBase getTypeDetail(String type) {
        Integer userId = LoginHelper.getUserId().intValue();
        List<String> projects = ssFUserGroupDAO.getGroupProjectsByUserId(userId);
        List<Conponent> add =Lists.newArrayList();
        projects.stream().forEach((projectid)->{
            Object o = redisTemplate.opsForValue().get(projectid+"_"+type);
            if(!ObjectUtils.isEmpty(o)){
                String JSONStr = JSON.toJSONString(o);
                add.addAll(JSONObject.parseArray(JSONStr, Conponent.class));
            }

        });

        return new ResponseBase(200,"查询成功",add);
    }

    public ResponseBase uploadTime(MultipartFile uploadFile) throws Exception {

        InputStream inputStream = uploadFile.getInputStream();
        List<List<Object>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
//        System.out.println(list.size());
        for (List<Object> listt : list) {
            ZjConponentProducetime zjConponentBycode = zjConponentProducetimeDAO.getZjConponentBycode(listt.get(2).toString());
            Conponent conponent = conponentDAO.getConponentByCode(listt.get(2).toString());
            if(ObjectUtils.isEmpty(zjConponentBycode)){
                System.out.println(listt.get(2));
            }
//            String

            Long a = (long) listt.get(1);
            int b = a.intValue();
            Produce produce = produceDAO.select(listt.get(0).toString(),a.intValue());
            if(ObjectUtils.isEmpty(produce)){
                System.out.println(1);
            }

            String names = listt.get(4).toString();
            String picUrl = getPicUrl(names);

            //生成记录

            Recode recode = new Recode();
            recode.setRemark(picUrl);


            if(ObjectUtils.isEmpty(listt.get(7))){
                recode.setCreatetime(getNowDate());
            }else{
                DateTime dateTime = (DateTime) listt.get(7);
                System.out.println(listt.get(7));
                recode.setCreatetime(getNowToDate(dateTime));
            }

            recodeDAO.insert(recode);

            Produceandrecode produceandrecode = new Produceandrecode();

            produceandrecode.setCheckuser(8);
            produceandrecode.setCheckresult(1);

            produceandrecode.setRecodeid(recode.getId());
            produceandrecode.setConponentid(conponent.getId());
            produceandrecode.setProduceid(produce.getId());
            produceandrecode.setProjectcode(zjConponentBycode.getProjectcode());
            produceandrecode.setUpdateuser(11);


            if(ObjectUtils.isEmpty(listt.get(5))){
                produceandrecode.setStime(getNowDate());
            }else{
                DateTime dateTime = (DateTime) listt.get(5);
                produceandrecode.setStime(getNowToDate(dateTime));
            }

            if(ObjectUtils.isEmpty(listt.get(7))){

            }else{
                System.out.println(listt.get(4).toString());
                DateTime dateTime = (DateTime) listt.get(7);
                produceandrecode.setUpdatetime(getNowToDate(dateTime));
            }

            produceandrecode.setProducename(produce.getName());
            produceandrecode.setConponenttype(zjConponentBycode.getConponenttype());
            produceandrecode.setConponentname(zjConponentBycode.getConponenttypename());

            produceandrecode.setUpdateusername(listt.get(3).toString());
            produceandrecode.setCheckusername(listt.get(6).toString());
            produceandrecode.setConponentcode(conponent.getConponentcode());

            produceandrecodeDAO.insert(produceandrecode);


        }

        System.out.println("结束");

        return new ResponseBase(200,null,"导入成功");
    }



    private String getPicUrl(String names) {


        String a =fileService.uploadFile(names);
        if(ObjectUtils.isEmpty(a)){
            return "";
        }
        return a.substring(0,a.length()-1);
    }

    private Date toBeDate(String s) throws ParseException {
        return DateUtils.getDateForCon(s);
    }

        public static void main(String[] args) {
//        String a = "GQ01-QL04Z-XB-D03-ZJ-01-2.pdf.pdf";
//
//        if(a.endsWith(".pdf.pdf")){
//            int i = a.indexOf(".pdf.pdf");
//            System.out.println(i);
//            a = a.substring(0,i)+".pdf";
//        }
//        System.out.println(a);
    }

    public ResponseBase findDataByCode(String conponentCode) {
        ZjConponentProducetime zjConponentBycode = zjConponentProducetimeDAO.getZjConponentBycode(conponentCode);

//        getCheckDataByrecod()



        return null;
    }

    public RecodeCombin getCheckDataByrecod(Integer recodeid) {
        Produceandrecode produceandrecode = produceandrecodeDAO.getProduceandReconde(recodeid);
        Recode recode = recodeDAO.selectByPrimaryKey(recodeid);

        RecodeCombin recodeCombin = new RecodeCombin();
        recodeCombin.setRecode(recode);
        recodeCombin.setProduceandrecode(produceandrecode);

        return recodeCombin;

    }

    /**
     * 处理数据
     * @param type
     * @param collect2
     * @param collect1
     * @param recodeQueryData
     * @return
     */
    public Map<String,Object> processData(String type,
                                           Map<Integer, List<Produce>> collect2,
                                           Map<String, String> collect1,
                                           RecodeQueryData recodeQueryData
                                           ){
        List<Produceandrecode> list = Lists.newArrayList();
        List<String> codes = Lists.newArrayList();
            try {
                //当构件code不为空时, 说明是通过构件code来查询,因此这里设置projectType为null
                if (recodeQueryData.getCode() != null && !recodeQueryData.getCode().equals("")){
                    recodeQueryData.setProjectType(null);
                }
                //当用户传有时间相同时做的处理
                if (recodeQueryData.getSttime() != null || recodeQueryData.getEndtime() != null){
                    Date sttime = recodeQueryData.getSttime();
                    Date endtime = recodeQueryData.getEndtime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String oldStime = sdf.format(sttime);
                    String oldEndtime = sdf.format(endtime);
                    String newStime = oldStime + " 00:00:00";
                    String newEndtime = oldEndtime + " 23:59:59";
                    recodeQueryData.setNewStime(newStime);
                    recodeQueryData.setNewEndTime(newEndtime);
                    list = produceandrecodeDAO.getAllProduceTime(recodeQueryData);
                    if (list.size() == 0){
                        return null;
                    } else {
                        //新增逻辑，查出来的工序，需要看到所有的
                        for (Produceandrecode produceandrecode : list) {
                            String code = produceandrecode.getConponentcode();
                            codes.add(code);
                        }
                        codes = codes.stream().distinct().collect(Collectors.toList());
                        list = produceandrecodeDAO.getAllByConponentCodes(codes);
                    }
                } else{
                    list = produceandrecodeDAO.getAllProduce(recodeQueryData);
                    if (list.size() == 0){
                        return null;
                    } else {
                        //新增逻辑，查出来的工序，需要看到所有的
                        for (Produceandrecode produceandrecode : list) {
                            String code = produceandrecode.getConponentcode();
                            codes.add(code);
                        }
                        codes = codes.stream().distinct().collect(Collectors.toList());
                        list = produceandrecodeDAO.getAllByConponentCodes(codes);
                    }
                }

                Map<String, List<Produceandrecode>> collect = list.stream().
                        collect(Collectors.groupingBy(Produceandrecode::getConponentcode));

                List<ProduceRecord> listss = new ArrayList<>();
                collect.forEach((conponentcode, listt)->{
                    Collections.sort(listt);
                    //将一个转换成一个
                    ProduceRecord produceRecord = new ProduceRecord();
                    Produceandrecode produceandrecode = listt.get(0);
                    produceRecord.setConponentcode(produceandrecode.getConponentcode());
                    produceRecord.setConponentid(produceandrecode.getConponentid());
                    produceRecord.setConponenttypename(produceandrecode.getConponentname());
                    produceRecord.setProjectcode(produceandrecode.getProjectcode());
                    produceRecord.setProjectname(collect1.get(produceandrecode.getProjectcode()));
                    produceRecord.setSttime(produceandrecode.getStime());

                    listt.stream().forEach((produceandrecodeindex)->{
                        //设置详细数据
                        ComponentType.fillProduce(produceandrecodeindex,produceRecord,type,collect2);
                    });
                    listss.add(produceRecord);
                    //
                });

                //对listss进行遍历
                listss.stream().forEach(l -> {
                    List<Date> times = Lists.newArrayList();
                    if (l.getOneTime() != null && !l.getOneTime().equals("")){
                        times.add(l.getOneTime());
                    }
                    if (l.getTwoTime() != null && !l.getTwoTime().equals("")){
                        times.add(l.getTwoTime());
                    }
                    if (l.getThreeTime() != null && !l.getThreeTime().equals("")){
                        times.add(l.getThreeTime());
                    }
                    if (l.getFourTime() != null && !l.getFourTime().equals("")){
                        times.add(l.getOneTime());
                    }
                    if (l.getFiveTime() != null && !l.getFiveTime().equals("")){
                        times.add(l.getFiveTime());
                    }
                    //倒叙排完之后, 获取第一个时间放入maxTime中
                    if (times.size() > 0){
                        times.sort(Comparator.reverseOrder());
                        l.setMaxTime(times.get(0));
                    }
                });

                //最后对maxTime进行倒叙排列
                listss.sort(Comparator.comparing(ProduceRecord::getMaxTime, Comparator.reverseOrder()));

                List<Head> tohead = tohead(type);
                Collections.sort(tohead);
                Map<String,Object> data = new HashMap<>();
                data.put("head",tohead);
                data.put("list",listss);

                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseBase deleteProcess(Integer conponentid, Integer recodeid){
        //先判断操作的用户groupid是否为顶级或者总部(1,2),否则没有该权限
        Integer userId = LoginHelper.getUserId().intValue();
        SsFUserRole userRole = userRoleDAO.getByUserid(userId);
        Set<String> roleKey = LoginHelper.getLoginUser().getRolePermission();
        //当用户所属权限不为管理员时，不准传入实际开始时间和实际结束时间
        if (!roleKey.contains("gly") && !roleKey.contains("admin")){
            return new ResponseBase(500, "该操作用户没有删除权限", null);
        }
        //删除之前须到数据库查询需要删除的数据是否存在
        Produceandrecode produceandrecode = produceandrecodeDAO.getInfoByConponentidAndRecodeId(conponentid, recodeid);
        if (produceandrecode == null){
            return new ResponseBase(500, "没有该条记录! ");
        }
        // 在删除之前判断该条工序是否为该工序的第一道工序或者最后一道工序
        Integer count = produceDAO.getNumProduce(produceandrecode.getConponenttype());


        if (count == 1) {
            //说明只有一道工序，此时直接删除进度表的实际开始、结束时间
            zjConponentProducetimeDAO.setNullTime(conponentid);
        } else{
            //说明不只有一道工序
            List<Integer> produces = produceDAO.getIdByTypeDesc(produceandrecode.getConponenttype());
            //获取倒叙工序的最后一个（即每个类型的工序的第一步）
            Integer produce = produces.stream().reduce((first, second) -> second).orElse(0);
            //当倒叙排列的第一条（该类型工序的最后一道工序） == 工序填报表的工序id，说明删除的为最后一道工序
            if (produces.get(0) == produceandrecode.getProduceid()){
                //此时只设置实际完成时间为null即可
                zjConponentProducetimeDAO.setActulendNullTime(conponentid);
            } else if (produce == produceandrecode.getProduceid()){
                //此时需设置完成时间和开始时间为null
                zjConponentProducetimeDAO.setActulstNullTime(conponentid);
            }
        }
        Integer row = produceandrecodeDAO.deleteByConponentidAndRecodeId(conponentid, recodeid);
        if (row != 1){
            return new ResponseBase(500, "删除对应工序数据失败! ");
        }

        //再查询recode里面有没有对应的文件
        String url = recodeDAO.getUrlById(recodeid);
        fileMapper.delete(url);


        //最后把消息发送给审核 checkuser
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        List<Integer> userIds = new ArrayList<>();
        userIds.add(produceandrecode.getCheckuser());
        messageDTO.setUserIds(userIds);
        messageDTO.setEvent("删除工序");
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        return new ResponseBase(200, "删除成功!");
    }

    public ResponseBase getAllReverse(){
        List<Produceandrecode> produceandrecodes = produceandrecodeDAO.getAll();
        return new ResponseBase(200, "查询所有工序成功", produceandrecodes);
    }

    public ResponseBase syncData(){
        List<Produceandrecode> produceandrecodes = produceandrecodeDAO.getAll();
        for (Produceandrecode produceandrecode : produceandrecodes) {
            Integer produceid = produceandrecode.getProduceid();
            Integer recondid = produceandrecode.getRecodeid();
            String componentType = produceandrecode.getConponenttype();
            //通过这俩值查询工序表里面对应的工序是否为最后一步,是则添加上构件进度表完成时间
            Integer produceSum = produceDAO.getNumProduce(componentType);
            if (produceSum == 1){
                //当工序只有一步的时候直接插入
                String conponentcode = produceandrecodeDAO.getConpontentCodeByRecodeId(recondid, produceid);
                zjConponentProducetimeDAO.updateFinishTime(conponentcode,new Date());
            } else{
                //当工序不止一步时，通过工序查询
                List<Integer> produces = produceDAO.getIdByTypeDesc(componentType);
                if (produces.get(0) == produceid){
                    String conponentcode = produceandrecodeDAO.getConpontentCodeByRecodeId(recondid, produceid);
                    zjConponentProducetimeDAO.updateFinishTime(conponentcode,new Date());
                }
            }
        }
        log.debug("同步工序记录表与构建进度表数据已完成！");
        return new ResponseBase(200, "同步工序记录表与构建进度表数据已完成！");
    }

    @Transactional
    public ResponseBase updateGroups(){
        log.info("准备开始同步工序填报表和构件表数据。。");
        List<Produceandrecode> recodes = produceandrecodeDAO.getAll();
        for (Produceandrecode recode : recodes) {
            String code = recode.getConponentcode();
            //查询构建表code对应的projectcode
            Conponent conponent = conponentDAO.getConponentByCode(code);
            String projectcode = conponent.getProjectcode();
            recode.setProjectcode(projectcode);
            produceandrecodeDAO.updateByCode(recode);
        }

        log.info("同步完毕！");
        return new ResponseBase(200,"同步数据完成！");
    }

    public ResponseBase getCopyInfos(Map<String, String> data){
        //标段id
        Integer projectId = Integer.parseInt(data.get("projectId"));
        //工区id
        Integer workAreaId = null;
        if (!data.get("workAreaId").equals("")){
            workAreaId = Integer.parseInt(data.get("workAreaId"));
        }
        //单位工程code
        String unitProjectCode = data.get("unitProjectCode");
        //开始与结束时间
        String startTime = data.get("startTime");
        String endTime = data.get("endTime");

        if (projectId <= 0){
            return new ResponseBase(200, "请核实项目id的有效性!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userId = LoginHelper.getUserId().intValue();
        List<ProduceAndRecodeDTO> produces = Lists.newArrayList();
        if (workAreaId == null && unitProjectCode.equals("")
                && startTime.equals("") && endTime.equals("")){
            //当其他参数都为空时
            produces = produceandrecodeDAO.getAllProduceByProjectId(projectId);
        } else if (startTime.equals("") && endTime.equals("")){
            List<String> unitCodes = Lists.newArrayList();
                //当只有标段id和工区id时, 先查询该工区下面有哪些单位工程code
            if (!workAreaId.equals("")) {
                unitCodes = projectsDAO.getProjectCodes(workAreaId);
            }
            //当前端传了单位工程时
            if (!unitProjectCode.equals("")) {
                //清空list
                unitCodes.clear();
                //再添加上
                unitCodes.add(unitProjectCode);
            }
            //再通过单位工程code 查询结果
            produces = produceandrecodeDAO.getAllByProjectAndUnitCodes(projectId, unitCodes);
        } else {
            List<String> unitCodes = Lists.newArrayList();
            //当只有标段id和工区id时, 先查询该工区下面有哪些单位工程code
            if (!workAreaId.equals("")) {
                unitCodes = projectsDAO.getProjectCodes(workAreaId);
            }
            //当前端传了单位工程时
            if (workAreaId.equals("") && !unitProjectCode.equals("")) {
                //清空list
                unitCodes.clear();
                //再添加上
                unitCodes.add(unitProjectCode);
            }
            // 再加上时间
            String strStart = startTime + " 00:00:00";
            String endStart = endTime + " 23:59:59";

            produces = produceandrecodeDAO.getAllByUnitAndTime(projectId, unitCodes, strStart, endStart);
        }

        List<ProduceAndRecodeDTO> lists = Lists.newArrayList();
        for (ProduceAndRecodeDTO produce : produces) {
            String copyUsers = produce.getCopyUsers();
            if (copyUsers != null && !copyUsers.equals("")){
                List<String> str = Arrays.asList(copyUsers.split(","));
                if (str.contains(String.valueOf(userId))){
                    lists.add(produce);
                }
            }
            Map<String, String> map = produceandrecodeDAO.getUnitNameAndWorkAreaNameByCode(produce.getProjectcode());
            String workAreaName = map.get("workAreaName");
            produce.setWorkAreaName(workAreaName);
            String unitName = map.get("unitName");
            produce.setUnitName(unitName);
        }
        return new ResponseBase(200, "", lists);
    }
}
