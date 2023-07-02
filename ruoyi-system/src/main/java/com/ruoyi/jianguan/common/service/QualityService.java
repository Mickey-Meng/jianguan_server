package com.ruoyi.jianguan.common.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ruoyi.common.config.zjrw.WebSocketServer;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.dto.WebsocketMessageDTO;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.common.core.domain.model.SafePerData;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.jianguan.common.dao.*;
import com.ruoyi.jianguan.common.domain.dto.*;
import com.ruoyi.jianguan.common.domain.entity.SafeGongQugroup;
import com.ruoyi.jianguan.common.domain.entity.SafeGongQugroupOverdue;
import com.ruoyi.jianguan.common.domain.entity.ZjQualityEvent;
import com.ruoyi.jianguan.common.domain.entity.ZjZlDic;

import com.ruoyi.common.utils.jianguan.zjrw.DateUtils;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.jianguan.zjrw.MyExcelUtil;
import com.ruoyi.common.utils.jianguan.zjrw.RestApiUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.jianguan.zjrw.DateUtils.getNowDate;
import static com.ruoyi.common.utils.jianguan.zjrw.DateUtils.getNowToDate;

@Service
public class QualityService {

    @Autowired
    ZjQualityEventDAO zjQualityEventDAO;

    @Autowired
    ZjSafeEventDAO zjSafeEventDAO;

    @Autowired
    @Qualifier("zjFGroupsProjectsDAO")
    ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;

    @Autowired
    ZjZlDicDAO zjZlDicDAO;

    @Autowired
    SsFGroupsDAO ssFGroupsDAO;

    @Autowired
    SsFUsersDAO ssFUsersDAO;

    @Autowired
    FileService fileService;

    @Autowired
    SsFUserGroupDAO ssFUserGroupDAO;

    @Autowired
    SsFUserRoleDAO userRoleDAO;

    @Autowired
    private SsFUserOnlineDAO userOnlineDAO;

    @Autowired
    private ProjectsDAO projectsDAO;

    @Autowired
    private FileMapper fileMapper;


    @Autowired
    WebSocketServer webSocketServer;

    public ResponseBase getTree(Integer id, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<ZjZlDic> zjZlDics = Lists.newArrayList();
        if (projectId == 3){
            zjZlDics = zjZlDicDAO.getByPid(id);
        }
        return new ResponseBase(200,null,zjZlDics);
    }

    public ResponseBase uploadCheck(QualityEventUpload qualityEventUpload) {
        //quality
        //Quality
        ZjQualityEvent zjQualityEvent = new ZjQualityEvent();
        zjQualityEvent.setQualityfirst(qualityEventUpload.getQualityfirst());
        zjQualityEvent.setQualitysecond(qualityEventUpload.getQualitysecond());
        zjQualityEvent.setUploadid(qualityEventUpload.getUploadid());
        zjQualityEvent.setUploadname(qualityEventUpload.getUploadname());
        zjQualityEvent.setUploadremark(qualityEventUpload.getUploadremark());
        zjQualityEvent.setUploadurl(qualityEventUpload.getUploadurl());
        zjQualityEvent.setModifyid(qualityEventUpload.getModifyid());
        zjQualityEvent.setModifyname(qualityEventUpload.getModifyname());
        zjQualityEvent.setModifydate(qualityEventUpload.getModifydate());
        zjQualityEvent.setGongquid(qualityEventUpload.getGongquid());
        zjQualityEvent.setQualityfirstname(qualityEventUpload.getQualityfirstname());
        zjQualityEvent.setQualitysecondname(qualityEventUpload.getQualitysecondname());
        zjQualityEvent.setSingleProjectId(qualityEventUpload.getSingleProjectId());
        zjQualityEvent.setProjectId(qualityEventUpload.getProjectId());
        zjQualityEvent.setDelayresult(0);
        zjQualityEvent.setNormal(0);
        zjQualityEvent.setStatus(0);
        if(ObjectUtils.isEmpty(qualityEventUpload.getUploadtime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = sdf.format(date);
//          zjQualityEvent.setUploadtime(new Date());
            zjQualityEvent.setUploadtime(new Date(time));
        }else{
            zjQualityEvent.setUploadtime(qualityEventUpload.getUploadtime());
        }
        int insert = zjQualityEventDAO.insert(zjQualityEvent);

        //往websocket推送消息
        List<ZjQualityEvent> obj = new ArrayList<>();
        obj.add(zjQualityEvent);

        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("监理提出质量检查事件");
        //提出待整改事件时,modifyid即为需要推送消息的id
        //对施工和监理都通知
        List<Integer> userIds = new ArrayList<>();
        userIds.add(zjQualityEvent.getUploadid());
        userIds.add(zjQualityEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjQualityEvent.getModifyid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条新的质量检查事件，请处理！");
        }

        if(insert>0){
            return new ResponseBase(200,"插入成功");
        }
        return new ResponseBase(300,"插入失败");
    }

    public ResponseBase getQualityEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
      /*
        yangaogao 20230421 直接获取token中的当前登录用户对应的角色信息 S
        Integer userid = LoginHelper.getUserId().intValue();

        Integer role = zjQualityEventDAO.getRole(userid);
        yangaogao 20230421 E
        */
        Map<String,Object> map = new HashMap<>();
        Integer role =  LoginHelper.getLoginUser().getRoleId().intValue();

//        if(role == 7 || role == 70){
//            map.put("role",1);
//        }else{
//            map.put("role",0);
//        }
        List<Integer> shiGongIds = userRoleDAO.getShiGongRoleIds();
        List<Integer> shiGongIds1 = userRoleDAO.getOldShiGongRoleIds();
        //把老的施工id也加到集合中
        shiGongIds.addAll(shiGongIds1);
        if (shiGongIds.contains(role)){
            map.put("role", 1);
        }else {
            map.put("role", 0);
        }
        // 获取用户id 根据用户id 查询代办事件
        List<ZjQualityEvent> zjSafeEventList = zjQualityEventDAO.getSafeEventByModify(LoginHelper.getUserId().intValue(), projectId);
        if(zjSafeEventList.size()==0){
            map.put("data", new ArrayList<>());
            return new ResponseBase(200,"暂无数据", map);
        }

        // 填充项目名称喝工区名称
        List<Integer> projectIds = zjSafeEventList.stream().map(ZjQualityEvent::getProjectId).collect(Collectors.toList());
        List<Integer> gongquIds = zjSafeEventList.stream().map(ZjQualityEvent::getGongquid).collect(Collectors.toList());
        projectIds.addAll(gongquIds);
        List<SsFProjects> result = ssFGroupsDAO.getProjectByIds(projectIds);
        Map<Integer, String> id2name = result.stream().collect(Collectors.toMap(SsFProjects::getId, SsFProjects::getName));

        //增加超期时间字段和是否超期字段
        Calendar ca = Calendar.getInstance();
        Date systemtime = new Date();
        List<QualityEventInfo> safeEventInfos = new ArrayList<>();
        for (ZjQualityEvent zjQualityEvent : zjSafeEventList) {
            //新逻辑,增加是否超期  是否超期: uploadtime + modifyday < systemday 超期
            Integer isOverdue = 0;

            ca.setTime(zjQualityEvent.getUploadtime());
            ca.add(Calendar.DAY_OF_YEAR, zjQualityEvent.getModifydate());

            Date addTime = ca.getTime();
            //期限日期
            QualityEventInfo eventInfo = new QualityEventInfo();
            eventInfo.setZjQualityEvent(zjQualityEvent);
            eventInfo.setOverdueTime(addTime);
            eventInfo.setIsOverdue(isOverdue);
            eventInfo.setGongquname(id2name.get(zjQualityEvent.getGongquid()));
            eventInfo.setProjectname(id2name.get(zjQualityEvent.getProjectId()));

            boolean flag = addTime.before(systemtime);
            if (flag){
                isOverdue = 1;
                eventInfo.setIsOverdue(isOverdue);
            }
            safeEventInfos.add(eventInfo);
        }

        map.put("data", safeEventInfos);
        return new ResponseBase(200,"查询成功",map);
    }

    public ResponseBase submitDelaySafeEvent(SubMitDelayEvent subMitDelayEvent) {
        ZjQualityEvent zjQualityEvent = zjQualityEventDAO.selectByPrimaryKey(subMitDelayEvent.getEventid());
        int a =0;
        if(!ObjectUtils.isEmpty(zjQualityEvent.getDelayday())){
            a=zjQualityEvent.getDelayday();
        }
        zjQualityEvent.setDelayday(subMitDelayEvent.getDelay()+a);
        zjQualityEvent.setDelayreason(subMitDelayEvent.getReason());
        zjQualityEvent.setStatus(1);
        zjQualityEvent.setDelayresult(1);
        int i = zjQualityEventDAO.updateByPrimaryKeySelective(zjQualityEvent);

        //发送websocket消息
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        List<ZjQualityEvent> obj = new ArrayList<>();
        obj.add(zjQualityEvent);
        messageDTO.setEvent("施工方申请质量延期事件");
        messageDTO.setData(obj);
        List<Integer> userIds = new ArrayList<>();
        userIds.add(zjQualityEvent.getUploadid());
        userIds.add(zjQualityEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjQualityEvent.getUploadid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条质量检查延期申请事件，请处理！");
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase getDelaySafeEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = LoginHelper.getUserId().intValue();
        List<ZjQualityEvent> zjSafeEventList = zjQualityEventDAO.getDelaySafeEventByModify(userid, projectId);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"暂无数据");
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);
    }

    public ResponseBase doDelaySafeEvent(DoEvent doDelayEvent) {
        ZjQualityEvent zjQualityEvent = zjQualityEventDAO.selectByPrimaryKey(doDelayEvent.getEventid());
        if(doDelayEvent.getResult()==2){
            zjQualityEvent.setDelayday(0);
            zjQualityEvent.setDodelayreason(doDelayEvent.getReason());
            zjQualityEvent.setDelayresult(3);
            zjQualityEvent.setStatus(0);
        }else if(doDelayEvent.getResult()==1){
            //zjSafeEvent.setDelayday(0); 延期成功
            zjQualityEvent.setDodelayreason(doDelayEvent.getReason());
            zjQualityEvent.setDelayresult(2);
            zjQualityEvent.setStatus(0);
        }else{
            return new ResponseBase(300,"参数错误");
        }
        int i = zjQualityEventDAO.updateByPrimaryKeySelective(zjQualityEvent);

        //发送websocket消息
        List<ZjQualityEvent> obj = new ArrayList<>();
        obj.add(zjQualityEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("监理确认质量延期事件");
        List<Integer> userIds = new ArrayList<>();
        userIds.add(zjQualityEvent.getUploadid());
        userIds.add(zjQualityEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjQualityEvent.getModifyid());
        if(doDelayEvent.getResult()==1){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "质量检查延期事件申请已通过，请确认！");
            }
        }else {
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "质量检查延期事件申请被驳回，请确认！");
            }
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase submitDealWithSafeEvent(SubmitDealWithSafeEvent submitDealWithSafeEvent) {
        ZjQualityEvent zjQualityEvent = zjQualityEventDAO.selectByPrimaryKey(submitDealWithSafeEvent.getEventid());
        zjQualityEvent.setStatus(2);
        zjQualityEvent.setModifyremark(submitDealWithSafeEvent.getModifyremark());
        zjQualityEvent.setModifyurl(submitDealWithSafeEvent.getModifyurl());
        if(ObjectUtils.isEmpty(submitDealWithSafeEvent.getModifydate())){
//            zjSafeEvent.setModifytime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = sdf.format(date);
            zjQualityEvent.setUploadtime(new Date(time));
        }else{
            zjQualityEvent.setModifytime(submitDealWithSafeEvent.getModifydate());
        }
        int i = zjQualityEventDAO.updateByPrimaryKeySelective(zjQualityEvent);

        //发送websocket消息
        List<ZjQualityEvent> obj = new ArrayList<>();
        obj.add(zjQualityEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        List<Integer> userIds = new ArrayList<>();
        userIds.add(zjQualityEvent.getUploadid());
        userIds.add(zjQualityEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        messageDTO.setData(obj);
        messageDTO.setEvent("施工方处理质量事件");
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjQualityEvent.getModifyid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条质量事件已整改，请确认！");
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase getNotDoneSafeEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = LoginHelper.getUserId().intValue();
        List<ZjQualityEventDTO> zjSafeEventList = zjQualityEventDAO.getNotDoneSafeEventDTO(userid, projectId);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"暂无数据");
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);

    }

    public ResponseBase doNotDoneSafeEvent(DoEvent doDelayEvent) {
        ZjQualityEvent zjQualityEvent = zjQualityEventDAO.selectByPrimaryKey(doDelayEvent.getEventid());

        if(doDelayEvent.getResult()==2){
            zjQualityEvent.setStatus(0);
            zjQualityEvent.setDodelayreason(doDelayEvent.getReason());
        }else if(doDelayEvent.getResult()==1){
            zjQualityEvent.setStatus(3);
            //状态检查
        }
        else{
            return new ResponseBase(300,"参数错误");
        }
        int i = zjQualityEventDAO.updateByPrimaryKeySelective(zjQualityEvent);

        //发送websocket消息
        List<ZjQualityEvent> obj = new ArrayList<>();
        obj.add(zjQualityEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        List<Integer> userIds = new ArrayList<>();
        userIds.add(zjQualityEvent.getUploadid());
        userIds.add(zjQualityEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        messageDTO.setEvent("监理处理质量整改事件");
        webSocketServer.onMessage(JSON.toJSONString(messageDTO), null);

        if (doDelayEvent.getResult() == 2){
            if (doDelayEvent.getCopyUsers() != null && !doDelayEvent.getCopyUsers().equals("")){
                List<String> copyUsers = Arrays.asList(doDelayEvent.getCopyUsers().split(","));
                for (String copyUser : copyUsers) {
                    Integer userId = Integer.parseInt(copyUser);
                    WebsocketMessageDTO messagesDTO = new WebsocketMessageDTO();
                    obj.add(zjQualityEvent);
                    obj.add(zjQualityEvent);
                    messagesDTO.setData(obj);
                    messagesDTO.setEvent("监理方已处理完质量整改代办");
                    //通知抄送人
                    userIds.add(userId);
                    messagesDTO.setUserIds(userIds);
                    webSocketServer.onMessage(JSON.toJSONString(messagesDTO), null);

                    //为保证用户web和app收到,最后也需要发送app
                    String cid = userOnlineDAO.getCidByUserid(userId);
                    if (cid != null && !cid.equals("")){
                        RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有新的安全整改事件已通过，请悉知！");
                    }
                }
            }
        }

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjQualityEvent.getUploadid());
        if(doDelayEvent.getResult()==2){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "质量整改事件被驳回，请确认！");
            }
        }else {
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "质量整改事件已通过审核，请确认！");
            }
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase getDoneSafeEvent() {
        Integer userid = LoginHelper.getUserId().intValue();
        List<ZjQualityEvent> zjSafeEventList = zjQualityEventDAO.getDoneSafeEventByModify(userid);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"暂无数据");
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);

    }

    public ResponseBase getAllStatusQualityEvent(Integer projectId, Integer singleProjectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
//        if(!ObjectUtils.isEmpty(singleProjectId)){
//            List<ZjQualityEvent> zjQualityEvent = zjQualityEventDAO.getAllStatusSafeByProjectcode(singleProjectId, projectId);
            // yangaogao 改为查询该项目向下，所有的质量管理事件
            List<ZjQualityEvent> zjQualityEvent = zjQualityEventDAO.getAllStatusQualityByProjectcode(projectId);
            return new ResponseBase(200,"查询成功",zjQualityEvent);
//        }
        /*List<ZjQualityEvent> zjSafeEventList = Lists.newArrayList();

        Integer userId = LoginHelper.getUserId().intValue();

        if (projectId == 3){
            List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(userId);
            List<Integer> allGroups = ssFUserGroupDAO.getAllGroups();
            if (gongqus.size() > 0){
                if (gongqus.contains(2)){
                    gongqus.clear();
                    gongqus = allGroups;
                }
                StringBuilder sb = new StringBuilder();
                for (Integer i : gongqus) {
                    sb.append(i).append(",");
                }
                String abc = sb.substring(0, sb.toString().length() - 1);
                zjSafeEventList = zjQualityEventDAO.getAllStatusQualityByModify(abc);
                if(zjSafeEventList.size()==0){
                    return new ResponseBase(300,"暂无数据");
                }
            }
        }*/
//        return new ResponseBase(200,"查询成功",zjSafeEventList);
    }

    public ResponseBase getAllSafeEvent(Integer projectId, Integer singleProjectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        if(!ObjectUtils.isEmpty(singleProjectId) || singleProjectId != 0){
            List<ZjQualityEvent> zjQualityEventList = zjQualityEventDAO.getAllStatusSafeByProjectcode(singleProjectId, projectId);
            return new ResponseBase(200,"查询成功", zjQualityEventList);
        }

        Integer userId = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        //根据工区维度获取数据
        List<ZjQualityEvent> zjSafeEventList = Lists.newArrayList();
        if(gongqus.size() > 0){
            gongqus.stream().forEach((gongquid)->{
                List<ZjQualityEvent> zjSafeEvents = zjQualityEventDAO.getGongquData(gongquid.toString());
                zjSafeEventList.addAll(zjSafeEvents);
            });
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);

    }

    public void uploadxyz(List<List<Object>> list) throws ParseException {

        for (int i = 0; i < list.size(); i++) {

            ZjZlDic zjZlDic = zjZlDicDAO.getByName(list.get(i).get(1).toString());

            if(ObjectUtils.isEmpty(zjZlDic)){
                System.out.println(list.get(i).get(1).toString());
            }

            ZjZlDic zjZlDicTwo = zjZlDicDAO.getByNameTwo(list.get(i).get(2).toString(),zjZlDic.getId());

            if(ObjectUtils.isEmpty(zjZlDic)||ObjectUtils.isEmpty(zjZlDicTwo)){
                System.out.println(list.get(i).get(1)+"_"+list.get(i).get(2));
            }

            ZjQualityEvent zjQualityEvent  = new ZjQualityEvent();

            //设置时间
            if(ObjectUtils.isEmpty(list.get(i).get(7))){
                zjQualityEvent.setUploadtime(getNowDate());
            }else{
                DateTime dateTime;
                dateTime = (DateTime) list.get(i).get(7);
                zjQualityEvent.setUploadtime(getNowToDate(dateTime));
            }
            if(ObjectUtils.isEmpty(list.get(i).get(9))){
                zjQualityEvent.setModifytime(getNowDate());
            }else{
                DateTime dateTime = (DateTime) list.get(i).get(9);
                zjQualityEvent.setModifytime(getNowToDate(dateTime));
            }
//
            if(list.get(i).get(6).toString().equals("监理领导")){
                zjQualityEvent.setUploadname("总部监理领导");
            }else {
                zjQualityEvent.setUploadname(list.get(i).get(6).toString());
            }
//
            zjQualityEvent.setModifyid(10);
            zjQualityEvent.setUploadremark(list.get(i).get(3).toString());

            if(!ObjectUtils.isEmpty(list.get(i).get(10))){
                zjQualityEvent.setModifyremark(list.get(i).get(10).toString());
            }
//
            zjQualityEvent.setUploadid(8);
            zjQualityEvent.setModifyname("施工员");

            zjQualityEvent.setStatus(0);
            SsFGroups ssFGroups = ssFGroupsDAO.selectByPrimaryKey(Integer.parseInt(list.get(i).get(0).toString()));
//            //设置工区
            zjQualityEvent.setGongquid(ssFGroups.getParentid());
            zjQualityEvent.setSingleProjectId(Integer.parseInt(list.get(i).get(0).toString()));
//            //设置大小类别
            zjQualityEvent.setQualityfirst(zjZlDic.getId());
            zjQualityEvent.setQualityfirstname(zjZlDic.getName());
            zjQualityEvent.setQualitysecond(zjZlDicTwo.getId());
            zjQualityEvent.setQualitysecondname(zjZlDicTwo.getName());
//
//            //设置检查图片
//
            if(!ObjectUtils.isEmpty(list.get(i).get(5))){
                String names = list.get(i).get(5).toString();
                String url = getPicUrl(names);
                zjQualityEvent.setUploadurl(url);
            }else{
                zjQualityEvent.setUploadurl(null);
            }
//
//            //设置 整改图片
//
            if(!ObjectUtils.isEmpty(list.get(i).get(11))){
                String s = list.get(i).get(11).toString();
                String url = getPicUrl(s);
                zjQualityEvent.setModifyurl(url);

            }else{
                zjQualityEvent.setModifyurl(null);
            }
//
//            zjSafeEventDAO.insert(zjSafeEvent);

            zjQualityEventDAO.insert(zjQualityEvent);

            //往websocket推送消息
            List<ZjQualityEvent> obj = new ArrayList<>();
            List<Integer> userIds = new ArrayList<>();
            obj.add(zjQualityEvent);
            WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
            messageDTO.setData(obj);
            messageDTO.setEvent("质量检查事件");
            userIds.add(zjQualityEvent.getUploadid());
            userIds.add(zjQualityEvent.getModifyid());
            messageDTO.setUserIds(userIds);
            String message = JSON.toJSONString(messageDTO);
            webSocketServer.onMessage(message, null);
        }



    }

    private String getPicUrl(String names) {

        String a =fileService.uploadFile(names);
        if(ObjectUtils.isEmpty(a)){
            return "";
        }
        return a.substring(0,a.length()-1);
    }

    public ResponseBase newInterface(Integer count, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        SafePerData safePerData = new SafePerData();
        //采用新逻辑
        safePerData = DateUtils.getDay(count);

        //获取到用户所拥有的工区权限
        Integer id = LoginHelper.getUserId().intValue();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(id);
        if (gongqus.size() == 0){
            return new ResponseBase(200, "该用户暂时没有绑定单位工程!");
        }
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        if (gongqus.contains(2)){
            gongqus.clear();
            gongqus = allGroups;
        }
        //取两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, gongqus);
        //新建一个 装 查询到的数据的工区id  list
        List<Integer> gids = new ArrayList<>();
        // 按照工区  与 时间
        List<SafeGongQugroup> list = Lists.newArrayList();
        List<SafeGongQugroupOverdue> list1 = Lists.newArrayList();
        ZjSafeEventDTO eventDTO = new ZjSafeEventDTO();
        if (intersectionList.size() > 0){
            list = zjQualityEventDAO.getAllStatusCount(intersectionList,safePerData.getSttime(),safePerData.getEndtime());
            //通过工区id查询所有的超期数据
            list1 = zjQualityEventDAO.getOverdueCount(intersectionList);

            //把所有查到的 数据的工区id撞到gids中
            for (SafeGongQugroup qugroup : list) {
                gids.add(qugroup.getGongquid());
            }
            //gids去重
            gids = gids.stream().distinct().collect(Collectors.toList());
            //获取到的数据工区数量不等于用户所拥有的工区权限,说明取到的数据中有些工区没有
            if (gids.size() != intersectionList.size()){
                intersectionList.removeAll(gids);
                //把没有查到的工区设置为空
                for (Integer gongqu : intersectionList) {
                    SafeGongQugroup qugroup = new SafeGongQugroup();
//                    if (gongqu == 4) {
//                        qugroup.setGongquname("工区一");
//                        qugroup.setGongquid(gongqu);
//                        qugroup.setCount(0);
//                        qugroup.setFinish(0);
//                    } else if (gongqu == 5) {
//                        qugroup.setGongquname("工区二");
//                        qugroup.setGongquid(gongqu);
//                        qugroup.setCount(0);
//                        qugroup.setFinish(0);
//                    } else if (gongqu == 6) {
//                        qugroup.setGongquname("工区三");
//                        qugroup.setGongquid(gongqu);
//                        qugroup.setCount(0);
//                        qugroup.setFinish(0);
//                    } else if (gongqu == 7) {
//                        qugroup.setGongquname("工区四");
//                        qugroup.setGongquid(gongqu);
//                        qugroup.setCount(0);
//                        qugroup.setFinish(0);
//                    }
                    SsFProjects project = ssFGroupsDAO.getProjectById(gongqu);
                    if (project != null){
                        qugroup.setGongquname(project.getName());
                        qugroup.setGongquid(gongqu);
                        qugroup.setCount(0);
                        qugroup.setFinish(0);
                    }
                    list.add(qugroup);
                }
            }
            for (SafeGongQugroup qugroup : list) {
//                if (qugroup.getGongquid() == 4) {
//                    qugroup.setGongquname("工区一");
//                } else if (qugroup.getGongquid() == 5) {
//                    qugroup.setGongquname("工区二");
//                } else if (qugroup.getGongquid() == 6) {
//                    qugroup.setGongquname("工区三");
//                } else if (qugroup.getGongquid() == 7) {
//                    qugroup.setGongquname("工区四");
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(qugroup.getGongquid());
                qugroup.setGongquname(project.getName());
            }
            for (SafeGongQugroupOverdue lists : list1) {
//                if(lists.getGongquid() == 4){
//                    lists.setGongquname("工区一");
//                } else if(lists.getGongquid() == 5){
//                    lists.setGongquname("工区二");
//                } else if(lists.getGongquid() == 6){
//                    lists.setGongquname("工区三");
//                } else if(lists.getGongquid() == 7){
//                    lists.setGongquname("工区四");
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(lists.getGongquid());
                lists.setGongquname(project.getName());
            }
            //对list做排序
            list = list.stream().sorted(Comparator.comparing(SafeGongQugroup::getGongquid)).collect(Collectors.toList());

            eventDTO.setTotal(list);
            eventDTO.setOverdueList(list1);
        }

        return new ResponseBase(200,"查询成功", eventDTO);
    }

    public ResponseBase group(Integer count, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<GqDataAll> resb = Lists.newArrayList();

        Integer id = LoginHelper.getUserId().intValue();
        // 根据用户ID，查询用户所属的工具集合
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(id);
        if (gongqus.size() == 0){
            return new ResponseBase(200,"查询成功", resb);
        }
        // 根据项目ID查询项目下的所有工区集合
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        if (gongqus.contains(2)){
            gongqus.clear();
            gongqus = allGroups;
        }
        //取两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, gongqus);
        SafePerData safePerData = new SafePerData();
        //采用新逻辑
        safePerData = DateUtils.getDay(count);

        // 根据工区ID、开始和结束日期查询事件集合
        List<EventGroup> list =
                zjQualityEventDAO.group(intersectionList,safePerData.getSttime(),safePerData.getEndtime());
        Map<Integer, List<EventGroup>> collect =
                list.stream().collect(Collectors.groupingBy(EventGroup::getGongquid));

        //如果map的size不等于该用户拥有的工区权限数,说明查询到的数据种有的工区没有
        if (collect.size() != intersectionList.size()){
            Iterator<Integer> iterator = collect.keySet().iterator();
            List<Integer> mapKeys = new ArrayList<>();
            while(iterator.hasNext()){
                Integer key = iterator.next();
                mapKeys.add(key);
            }
            //去掉重复,留下的工区是数据没有查询到的工区,设置为空返回给前端
            intersectionList.removeAll(mapKeys);
            //处理工区为空时的数据
            qualityProcessNull(intersectionList, resb);
        }
        // 去掉写死的项目ID
//        if (projectId == 3){
        // 添加集合非空校验
        if (CollUtil.isNotEmpty(collect)) {
            collect.forEach((key,value)->{
//                if(key == 4){
//                    resb.add(new GqDataAll("工区一",key,value));
//                }else if(key == 5){
//                    resb.add(new GqDataAll("工区二",key,value));
//                }else if(key == 6){
//                    resb.add(new GqDataAll("工区三",key,value));
//                }else if(key == 7){
//                    resb.add(new GqDataAll("工区四",key,value));
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(key);
                resb.add(new GqDataAll(project.getName(), key, value));
            });
        }
//        }
        Collections.sort(resb);
        return new ResponseBase(200,"查询成功",resb);
    }

    public ResponseBase getPerday(String date, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<GqDataAll> resb = Lists.newArrayList();
        Integer id = LoginHelper.getUserId().intValue();
        //获取当前用户的工区清单
        List<Integer> groups = ssFUserGroupDAO.getGroupsOfProjectByUserId(id);
        if (groups.size() == 0){
            //当用户没有工区权限时, 返回空集合
            return new ResponseBase(200, "查询成功!", resb);
        }
        //查询该项目下的工区清单
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        //当用户的角色权限为2时,默认拥有所有权限
        if (groups.contains(2)){
            groups.clear();
            groups = allGroups;
        }
        //取到上述两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, groups);
        SafePerData safePerData = DateUtils.abcc(date);

        //变更为通过用户拥有的权限工区查询
        List<ZjQualityEvent> list =
                zjQualityEventDAO.getGQByGongQuIds(intersectionList,safePerData.getSttime(),safePerData.getEndtime());

        Map<Integer, List<ZjQualityEvent>> collect =
                list.stream().collect(Collectors.groupingBy(ZjQualityEvent::getGongquid));
        //如果map的size不等于该用户拥有的工区权限数,说明查询到的数据种有的工区没有
        if (collect.size() != intersectionList.size()){
            Iterator<Integer> iterator = collect.keySet().iterator();
            List<Integer> mapKeys = new ArrayList<>();
            while(iterator.hasNext()){
                Integer key = iterator.next();
                mapKeys.add(key);
            }
            //去掉重复,留下的工区是数据没有查询到的工区,设置为空返回给前端
            intersectionList.removeAll(mapKeys);
            //处理工区为空时的数据
            qualityProcessNull(intersectionList, resb);
        }
//        if (projectId == 3){  yangaogao 20230331
            collect.forEach((key,value)->{
                Map<Date, List<ZjQualityEvent>> collect1 = value.stream().collect(Collectors.groupingBy(ZjQualityEvent::getUploadtime));
                List<String> a = new ArrayList<>();
                for (Date date1 : collect1.keySet()) {
                    String da = DateUtils.toDay(date1);
                    a.add(da);
                }
//                switch(key){
//                    case 4:
//                        resb.add(new GqDataAll("工区一",key,a));
//                        break;
//                    case 5:
//                        resb.add(new GqDataAll("工区二",key,a));
//                        break;
//                    case 6:
//                        resb.add(new GqDataAll("工区三",key,a));
//                        break;
//                    case 7:
//                        resb.add(new GqDataAll("工区四",key,a));
//                        break;
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(key);
                resb.add(new GqDataAll(project.getName(), key, a));
            });
//        }
        Collections.sort(resb);
        return new ResponseBase(200,"", resb);
    }


    public ResponseBase getDay(String date, Integer gqid) {
        SafePerData safePerData = DateUtils.abccc(date);
        List<ZjQualityEvent> list = zjQualityEventDAO.getByGQ(gqid,safePerData.getSttime(),safePerData.getEndtime());
        return new ResponseBase(200,"",list);

    }


    /**
     * 对获取每天质量数据时,工区为空时的处理
     * @param groups 工区
     * @param resb
     * @return resb
     */
    public List<GqDataAll> qualityProcessNull(List<Integer> groups, List<GqDataAll> resb){
        for (Integer group : groups) {
            GqDataAll dataAll = new GqDataAll();
            List<Object> objects = new ArrayList<>();
//            switch(group){
//                case 4:
//                    dataAll.setName("工区一");
//                    dataAll.setSort(group);
//                    dataAll.setObject(objects);
//                    break;
//                case 5:
//                    dataAll.setName("工区二");
//                    dataAll.setSort(group);
//                    dataAll.setObject(objects);
//                    break;
//                case 6:
//                    dataAll.setName("工区三");
//                    dataAll.setSort(group);
//                    dataAll.setObject(objects);
//                    break;
//                case 7:
//                    dataAll.setName("工区四");
//                    dataAll.setSort(group);
//                    dataAll.setObject(objects);
//                    break;
//            }

            SsFProjects project = ssFGroupsDAO.getProjectById(group);
            dataAll.setName(project.getName());
            dataAll.setSort(group);
            dataAll.setObject(objects);

            resb.add(dataAll);
        }
        return resb;
    }


    public ResponseBase deleteEvent(List<Integer> gids){
        //先判断操作的用户groupid是否为顶级或者总部(1,2),否则没有该权限
        Integer userId = LoginHelper.getUserId().intValue();
        SsFUserRole userRole = userRoleDAO.getByUserid(userId);
        // yangaogao 20230427 获取当前登录用户的权限改为如下方法
        Set<String> roleKey = LoginHelper.getLoginUser().getRolePermission();
        //当用户所属权限不为管理员时，不准传入实际开始时间和实际结束时间
        if (!roleKey.contains("gly") && !roleKey.contains("admin")){
            return new ResponseBase(500, "该操作用户没有删除权限", null);
        }

        List<ZjQualityEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();

        //删除之前须到数据库查询需要删除的数据是否存在
        List<ZjQualityEvent> qualityEvents = zjQualityEventDAO.getDataById(gids);
        List<Integer> ids = new ArrayList<>();
        for (ZjQualityEvent qualityEvent : qualityEvents) {
            ids.add(qualityEvent.getId());
            //往消息推送的里面加上数据
            obj.add(qualityEvent);
            userIds.add(qualityEvent.getUploadid());
            userIds.add(qualityEvent.getModifyid());
        }
        //需要推送的userIds与数据 去重
        userIds = userIds.stream().distinct().collect(Collectors.toList());
        obj = obj.stream().distinct().collect(Collectors.toList());

        //传进来的id数量和查询到的id数量不一致,说明有的数据不存在
        if (gids.size() != ids.size()){
            gids.removeAll(ids);
            return new ResponseBase(500, "删除失败", "删除失败, " + gids + "这条数据不存在");
        }
        //删除
        Integer rows = zjQualityEventDAO.batchDeleteById(gids);
        //并且删除mongdb里面对应的图片纪录
        for (ZjQualityEvent qualityEvent : qualityEvents) {
            String modifyUrls = qualityEvent.getModifyurl();
            String uploadUrls = qualityEvent.getUploadurl();
            if (uploadUrls != null && !uploadUrls.equals("")){
                String[] uploadUrl = uploadUrls.split(",");
                List<String> urls = Arrays.asList(uploadUrl);
                for (String url : urls) {
                    fileMapper.delete(url);
                }
            }
            if (modifyUrls != null && !modifyUrls.equals("")){
                String[] modifyUrl = modifyUrls.split(",");
                List<String> urls = Arrays.asList(modifyUrl);
                for (String url : urls) {
                    fileMapper.delete(url);
                }
            }
        }
        //发送消息
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("监理删除质量检查事件");
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        if (rows == gids.size()){
            return new ResponseBase(200, "删除成功", null);
        }
        return new ResponseBase(500, "删除失败", "未知的失败原因");
    }

    public ResponseBase updateByEvent(MultipartFile file){
        try{
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = MyExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            lists.stream().forEach((list) -> {
                //获取文档第一列  id
                String stringId = String.valueOf(list.get(0));
                Integer id = Integer.parseInt(stringId);
                //获取文档第25列  projectid
                String project = String.valueOf(list.get(25));
                Integer projectId = Integer.parseInt(project);
                Integer row = zjQualityEventDAO.getById(id);
                if (row == 1){
                    zjQualityEventDAO.updateProjectId(projectId, id);
                }
            });
            return new ResponseBase(200, "更新质量检查表组织成功!");
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseBase(500, "更新质量检查表组织失败！");
    }
}
