package com.ruoyi.czjg.zjrw.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ruoyi.common.config.zjrw.WebSocketServer;
import com.ruoyi.common.core.domain.dto.WebsocketMessageDTO;
import com.ruoyi.common.core.domain.model.SafePerData;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.dao.*;
import com.ruoyi.czjg.zjrw.domain.dto.*;
import com.ruoyi.czjg.zjrw.domain.entity.*;
import com.ruoyi.common.utils.zjbim.zjrw.DateUtils;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.zjbim.zjrw.MyExcelUtil;
import com.ruoyi.common.utils.zjbim.zjrw.RestApiUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.zjbim.zjrw.DateUtils.getNowDate;
import static com.ruoyi.common.utils.zjbim.zjrw.DateUtils.getNowToDate;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/10 4:11 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class SafeService {

    private static final Logger log = LoggerFactory.getLogger(SafeService.class);

    @Autowired
    FileService fileService;
    @Autowired
    ZjQualityEventDAO zjQualityEventDAO;
    @Autowired
    ZjSafeDicDAO zjSafeDicDAO;
    @Autowired
    SsFUsersDAO ssFUsersDAO;
    @Autowired
    SsFGroupsDAO ssFGroupsDAO;
    @Autowired
    SsFUserRoleDAO userRoleDAO;
    @Autowired
    SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    ZjSafeEventDAO zjSafeEventDAO;
    @Autowired
    SsFUserOnlineDAO userOnlineDAO;
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
        List<ZjSafeDic> zjSafeDics = Lists.newArrayList();
        if (projectId == 3){
            zjSafeDics = zjSafeDicDAO.getByPid(id);
        }
        return new ResponseBase(200,"查询成功", zjSafeDics);
    }

    public void upload(List<List<String>> list) {
        list.stream().forEach((stringList)->{
            ZjSafeDic zjSafeDic = new ZjSafeDic();
            zjSafeDic.setName(stringList.get(0));
            zjSafeDic.setId(Integer.parseInt(stringList.get(1)));
            zjSafeDic.setPid(Integer.parseInt(stringList.get(2)));
            zjSafeDicDAO.insert(zjSafeDic);
        });
    }


    public ResponseBase getcheck() {
        //查询每个工区的项目负责人 在role里面查
        List<SafeCheck> safeCheckList = zjSafeDicDAO.getCheck();
        return new ResponseBase(200,"查询成功",safeCheckList);
    }

    public ResponseBase uploadCheck(SafeEventUpload safeEventUpload) {
        ZjSafeEvent zjSafeEvent = new ZjSafeEvent();

        //根据施工员 确定 项目编号
        zjSafeEvent.setSafefirst(safeEventUpload.getSafefirst());
        zjSafeEvent.setSafesecond(safeEventUpload.getSafesecond());
        zjSafeEvent.setUploadid(safeEventUpload.getUploadid());
        zjSafeEvent.setUploadname(safeEventUpload.getUploadname());
        zjSafeEvent.setUploadremark(safeEventUpload.getUploadremark());
        zjSafeEvent.setUploadurl(safeEventUpload.getUploadurl());
        zjSafeEvent.setModifyid(safeEventUpload.getModifyid());
        zjSafeEvent.setModifyname(safeEventUpload.getModifyname());
        zjSafeEvent.setModifydate(safeEventUpload.getModifydate());
        zjSafeEvent.setGongquid(safeEventUpload.getGongquid());
        zjSafeEvent.setSafefirstname(safeEventUpload.getSafefirstname());
        zjSafeEvent.setSafesecondname(safeEventUpload.getSafesecondname());
        zjSafeEvent.setSingleProjectId(safeEventUpload.getSingleProjectId());
        zjSafeEvent.setProjectId(safeEventUpload.getProjectId());
        zjSafeEvent.setDelayresult(0);
        zjSafeEvent.setNormal(0);
        zjSafeEvent.setStatus(0);
        if(ObjectUtils.isEmpty(safeEventUpload.getUploadtime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = sdf.format(date);
//            zjSafeEvent.setUploadtime(new Date());
            zjSafeEvent.setUploadtime(new Date(time));
        }else{
            zjSafeEvent.setUploadtime(safeEventUpload.getUploadtime());
        }
        int insert = zjSafeEventDAO.insert(zjSafeEvent);

        //发送websocket给有权限审批的（一般是检查提交人uploadId）
        List<Integer> userIds = new ArrayList<>();
        List<ZjSafeEvent> obj = new ArrayList<>();
        obj.add(zjSafeEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("监理上传安全检查");
        //对施工和监理都通知
        userIds.add(zjSafeEvent.getUploadid());
        userIds.add(zjSafeEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjSafeEvent.getModifyid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条新的安全检查事件，请处理！");
        }

        if(insert>0){
            return new ResponseBase(200,"插入成功");
        }
        return new ResponseBase(300,"插入失败");
    }

    public ResponseBase getSafeEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = JwtUtil.getTokenUser().getId();
        Map<String,Object> map = new HashMap<>();
        Integer role = zjQualityEventDAO.getRole(userid);
        //通过用户id查询用户对应权限
        List<Integer> shiGongIds = userRoleDAO.getShiGongRoleIds();
        List<Integer> shiGongIds1 = userRoleDAO.getOldShiGongRoleIds();
        //把老的施工id也加到集合中
        shiGongIds.addAll(shiGongIds1);
        if (shiGongIds.contains(role)){
            map.put("role", 1);
        }else {
            map.put("role", 0);
        }
        // 获取用户id 根据用户id对应的角色权限来 查询代办事件
        //拥有施工权限只查询所有的待整改事件
        //拥有监理
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getSafeEventByModify(userid, projectId);
        if(zjSafeEventList.size()==0){
            map.put("data",new ArrayList<>());
            return new ResponseBase(300,"暂无数据", map);
        }

        //增加超期时间和是否超期两字段
        Calendar ca = Calendar.getInstance();
        Date systemtime = new Date();

        List<SafeEventInfo> safeEventInfos = new ArrayList<>();
        for (ZjSafeEvent zjSafeEvent : zjSafeEventList) {
            //新逻辑,增加是否超期  是否超期: uploadtime + modifyday < systemday 超期
            Integer isOverdue = 0;

            ca.setTime(zjSafeEvent.getUploadtime());
            ca.add(Calendar.DAY_OF_YEAR, zjSafeEvent.getModifydate());

            Date addTime = ca.getTime();
            //期限日期
            SafeEventInfo eventInfo = new SafeEventInfo();
            eventInfo.setZjSafeEvent(zjSafeEvent);
            eventInfo.setOverdueTime(addTime);
            eventInfo.setIsOverdue(isOverdue);

            boolean flag = addTime.before(systemtime);
            if (flag){
                isOverdue = 1;
                eventInfo.setIsOverdue(isOverdue);
            }
            safeEventInfos.add(eventInfo);
        }
        map.put("data", safeEventInfos);
        return new ResponseBase(200,"查询成功", map);
    }


    public ResponseBase getDoneSafeEvent() {
        Integer userid = JwtUtil.getTokenUser().getId();
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getDoneSafeEventByModify(userid);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"暂无数据");
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);
    }


    public ResponseBase getDelaySafeEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = JwtUtil.getTokenUser().getId();
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getDelaySafeEventByModify(userid, projectId);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"",new ArrayList<ZjSafeEvent>());
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);
    }

    public ResponseBase getNotDoneSafeEvent(Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = JwtUtil.getTokenUser().getId();
        List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getNotDoneSafeEvent(userid, projectId);
        if(zjSafeEventList.size()==0){
            return new ResponseBase(300,"暂无数据");
        }
        return new ResponseBase(200,"查询成功",zjSafeEventList);
    }

    public ResponseBase submitDelaySafeEvent(SubMitDelayEvent subMitDelayEvent) {
        //对于延期的处理
        //先根据id 获取 event
        ZjSafeEvent zjSafeEvent = zjSafeEventDAO.selectByPrimaryKey(subMitDelayEvent.getEventid());
        int a =0;
        if(!ObjectUtils.isEmpty(zjSafeEvent.getDelayday())){
            a=zjSafeEvent.getDelayday();
        }

        zjSafeEvent.setDelayday(subMitDelayEvent.getDelay()+a);
        zjSafeEvent.setDelayreason(subMitDelayEvent.getReason());
        zjSafeEvent.setStatus(1);
        zjSafeEvent.setDelayresult(1);
        int i = zjSafeEventDAO.updateByPrimaryKeySelective(zjSafeEvent);

        //发送websocket给有权限审批的（一般是检查提交人uploadId）
        List<ZjSafeEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        obj.add(zjSafeEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("施工方对安全事件进行延期处理");
        //对施工和监理都通知
        userIds.add(zjSafeEvent.getUploadid());
        userIds.add(zjSafeEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjSafeEvent.getUploadid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条安全检查延期申请事件，请处理！");
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase doDelaySafeEvent(DoEvent doDelayEvent) {

        ZjSafeEvent zjSafeEvent = zjSafeEventDAO.selectByPrimaryKey(doDelayEvent.getEventid());
        if(doDelayEvent.getResult()==2){
            zjSafeEvent.setDelayday(0);
            zjSafeEvent.setDodelayreason(doDelayEvent.getReason());
            zjSafeEvent.setDelayresult(3);
            zjSafeEvent.setStatus(0);
        }else if(doDelayEvent.getResult()==1){
            //zjSafeEvent.setDelayday(0); 延期成功
            zjSafeEvent.setDodelayreason(doDelayEvent.getReason());
            zjSafeEvent.setDelayresult(2);
            zjSafeEvent.setStatus(0);
        }else{
            return new ResponseBase(300,"参数错误");
        }
        int i = zjSafeEventDAO.updateByPrimaryKeySelective(zjSafeEvent);

        //发送websocket给有权限审批的（一般是检查提交人uploadId）
        List<ZjSafeEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        obj.add(zjSafeEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("监理方确认安全事件延期处理代办");
        //对施工和监理都通知
        userIds.add(zjSafeEvent.getUploadid());
        userIds.add(zjSafeEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjSafeEvent.getModifyid());
        if(doDelayEvent.getResult()==1){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "安全检查延期事件申请已通过，请确认！");
            }
        }else {
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "安全检查延期事件申请被驳回，请确认！");
            }
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");
    }

    public ResponseBase submitDealWithSafeEvent(SubmitDealWithSafeEvent submitDealWithSafeEvent) {
        ZjSafeEvent zjSafeEvent = zjSafeEventDAO.selectByPrimaryKey(submitDealWithSafeEvent.getEventid());
        zjSafeEvent.setStatus(2);
        zjSafeEvent.setModifyremark(submitDealWithSafeEvent.getModifyremark());
        zjSafeEvent.setModifyurl(submitDealWithSafeEvent.getModifyurl());
        if(submitDealWithSafeEvent.getModifydate() == null || submitDealWithSafeEvent.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = sdf.format(date);
//            zjSafeEvent.setUploadtime(new Date());
            zjSafeEvent.setUploadtime(new Date(time));
        }else{
            zjSafeEvent.setModifytime(submitDealWithSafeEvent.getModifydate());
        }
        int i = zjSafeEventDAO.updateByPrimaryKeySelective(zjSafeEvent);

        //发送websocket给有权限审批的（一般是检查提交人uploadId）
        List<ZjSafeEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        obj.add(zjSafeEvent);
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        messageDTO.setData(obj);
        messageDTO.setEvent("施工方对安全事件进行处理");
        //对施工和监理都通知
        userIds.add(zjSafeEvent.getUploadid());
        userIds.add(zjSafeEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjSafeEvent.getModifyid());
        if (cid != null && !cid.equals("")){
            RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有一条安全事件已整改，请确认！");
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");

    }

    public ResponseBase doNotDoneSafeEvent(DoEvent doDelayEvent) {
        ZjSafeEvent zjSafeEvent = zjSafeEventDAO.selectByPrimaryKey(doDelayEvent.getEventid());

        if(doDelayEvent.getResult()==2){
            zjSafeEvent.setStatus(0);
            zjSafeEvent.setDodelayreason(doDelayEvent.getReason());
        } else if(doDelayEvent.getResult()==1){
            zjSafeEvent.setStatus(3);
            //状态检查
        } else{
            return new ResponseBase(200,"参数错误");
        }
        int i = zjSafeEventDAO.updateByPrimaryKeySelective(zjSafeEvent);

        //发送websocket给有权限审批的（一般是检查提交人uploadId）
        List<ZjSafeEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
        obj.add(zjSafeEvent);
        messageDTO.setData(obj);
        messageDTO.setEvent("监理方处理安全整改代办");
        //对施工和监理都通知
        userIds.add(zjSafeEvent.getUploadid());
        userIds.add(zjSafeEvent.getModifyid());
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);
        //通过时,发送通知
        if (doDelayEvent.getResult() == 2){
            // todo 添加抄送人
            if (doDelayEvent.getCopyUsers() != null && !doDelayEvent.getCopyUsers().equals("")){
                List<String> copyUsers = Arrays.asList(doDelayEvent.getCopyUsers().split(","));
                for (String copyUser : copyUsers) {
                    Integer userId = Integer.parseInt(copyUser);
                    WebsocketMessageDTO messages = new WebsocketMessageDTO();
                    obj.add(zjSafeEvent);
                    obj.add(zjSafeEvent);
                    messageDTO.setData(obj);
                    messageDTO.setEvent("监理方已处理完安全整改代办");
                    //通知抄送人
                    userIds.add(userId);
                    messages.setUserIds(userIds);
                    webSocketServer.onMessage(message, null);

                    //为保证用户web和app收到,最后也需要发送app
                    String cid = userOnlineDAO.getCidByUserid(userId);
                    if (cid != null && !cid.equals("")){
                        RestApiUtils.sendMessageToCid(cid, "新消息通知！", "有新的安全整改事件已通过，请悉知！");
                    }
                }
            }
        }

        //最后把消息传给指定的app人  cid
        String cid = userOnlineDAO.getCidByUserid(zjSafeEvent.getUploadid());
        if(doDelayEvent.getResult()==2){
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "安全整改事件被驳回，请确认！");
            }
        }else {
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "安全整改事件已通过审核，请确认！");
            }
        }

        if(i>0){
            return new ResponseBase(200,"提交成功");
        }
        return new ResponseBase(500,"提交失败");

    }

    public ResponseBase getAllStatusSafeEvent(Integer singleProjectId, Integer projectId) {
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        if(!ObjectUtils.isEmpty(singleProjectId)){
            List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getAllStatusSafeByProjectcode(singleProjectId, projectId);
            return new ResponseBase(200,"查询成功",zjSafeEventList);
        }
        List<ZjSafeEvent> zjSafeEventList = Lists.newArrayList();
        Integer userId = JwtUtil.getTokenUser().getId();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(userId);
        //当查到的用户的角色权限为2时,默认拥有所有工区权限
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
            zjSafeEventList = zjSafeEventDAO.getAllStatusSafeByModify(abc, projectId);
            if(zjSafeEventList.size() == 0){
                return new ResponseBase(511,"暂无数据");
            }
        }
        return new ResponseBase(200,"查询成功", zjSafeEventList);
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
            List<ZjSafeEvent> zjSafeEventList = zjSafeEventDAO.getAllStatusSafeByProjectcode(singleProjectId, projectId);
            return new ResponseBase(200,"查询成功", zjSafeEventList);
        }
        Integer userId = JwtUtil.getTokenUser().getId();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(userId);
        //根据工区维度获取数据
        List<ZjSafeEvent> zjSafeEventList = Lists.newArrayList();
        if (gongqus.size() > 0){
            zjSafeEventList = zjSafeEventDAO.getGongquDatas(gongqus, projectId);
        }
        return new ResponseBase(200,"查询成功", zjSafeEventList);
    }

    public ResponseBase getQu() {
        List<SsFGroups> lists = ssFUserGroupDAO.getall();
        return new ResponseBase(200,null,lists);
    }

    public ResponseBase getProject(Integer id) {
        List<SsFGroups> lists = ssFUserGroupDAO.getallGq(id);
        return new ResponseBase(200,null,lists);
    }

    public void uploadxyz(List<List<Object>> list) throws Exception {

        for (int i = 0; i < list.size(); i++) {
            ZjSafeDic zjSafeDic =  zjSafeDicDAO.getbyName(list.get(i).get(1).toString());

            String lost2 = ObjectUtils.isEmpty(list.get(i).get(2))?"其他":list.get(i).get(2).toString();
             ZjSafeDic  zjsafeDic = zjSafeDicDAO.getbyNameAndpid(lost2,zjSafeDic.getId());
            ZjSafeEvent zjSafeEvent = new ZjSafeEvent();

            //设置时间
            if(ObjectUtils.isEmpty(list.get(i).get(7))){

                zjSafeEvent.setUploadtime(getNowDate());
            }else{
                DateTime dateTime = (DateTime) list.get(i).get(7);
                zjSafeEvent.setUploadtime(getNowToDate(dateTime));
            }
            if(ObjectUtils.isEmpty(list.get(i).get(9))){
                zjSafeEvent.setModifytime(getNowDate());
            }else{
                DateTime dateTime = (DateTime) list.get(i).get(9);
                zjSafeEvent.setModifytime(getNowToDate(dateTime));
            }

            if(list.get(i).get(6).toString().equals("监理领导")){
                zjSafeEvent.setUploadname("总部监理领导");
            }else {
                zjSafeEvent.setUploadname(list.get(i).get(6).toString());
            }

            zjSafeEvent.setModifyid(10);
            String up ="";
            if(ObjectUtils.isEmpty(list.get(i).get(3))){
                up+="";
            }else{
                up+=list.get(i).get(3).toString();
            }
            if(ObjectUtils.isEmpty(list.get(i).get(4))){
                up+="";
            }else{
                up+=list.get(i).get(4).toString();
            }
            zjSafeEvent.setUploadremark(up);
            if(!ObjectUtils.isEmpty(list.get(i).get(10))){
                zjSafeEvent.setModifyremark(list.get(i).get(10).toString());
            }

            zjSafeEvent.setUploadid(8);
            zjSafeEvent.setModifyname("施工员");

            //todo 根据文件，用户多加一列，安全检测事件状态, 目前暂设成0, 未读
            zjSafeEvent.setStatus(0);

            SsFGroups ssFGroups = ssFGroupsDAO.selectByPrimaryKey(Integer.parseInt(list.get(i).get(0).toString()));
            //设置工区
            zjSafeEvent.setGongquid(ssFGroups.getParentid());
            zjSafeEvent.setSingleProjectId(Integer.parseInt(list.get(i).get(0).toString()));
            //设置大小类别
            zjSafeEvent.setSafefirst(zjSafeDic.getId());
            zjSafeEvent.setSafefirstname(zjSafeDic.getName());
            zjSafeEvent.setSafesecond(zjsafeDic.getId());
            zjSafeEvent.setSafesecondname(zjsafeDic.getName());

            //设置检查图片

            if(!ObjectUtils.isEmpty(list.get(i).get(5))){
                String names = list.get(i).get(5).toString();
                String url = getPicUrl(names);
                zjSafeEvent.setUploadurl(url);
            }else{
                zjSafeEvent.setUploadurl(null);
            }

            //设置 整改图片

            if(!ObjectUtils.isEmpty(list.get(i).get(11))){
                String s = list.get(i).get(11).toString();
                String url = getPicUrl(s);
                zjSafeEvent.setModifyurl(url);

            }else{
                zjSafeEvent.setModifyurl(null);
            }

            zjSafeEventDAO.insert(zjSafeEvent);

            //发送websocket给有权限审批的（一般是检查提交人uploadId）
            List<ZjSafeEvent> obj = new ArrayList<>();
            WebsocketMessageDTO messageDTO = new WebsocketMessageDTO();
            obj.add(zjSafeEvent);
            messageDTO.setData(obj);
            messageDTO.setEvent("批量上传安全事件");
            //对施工和监理都通知
            List<Integer> userIds = new ArrayList<>();
            userIds.add(zjSafeEvent.getUploadid());
            userIds.add(zjSafeEvent.getModifyid());
            messageDTO.setUserIds(userIds);
            String message = JSON.toJSONString(messageDTO);
            webSocketServer.onMessage(message, null);
        }
    }

    private String getPicUrl(String names) {

        String a = fileService.uploadFile(names);
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
        Integer id = JwtUtil.getTokenUser().getId();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(id);
        if (gongqus.size() == 0){
            return new ResponseBase(200, "该用户暂时没有绑定单位工程!");
        }
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        //当用户的角色权限为2时,默认拥有所有权限
        if (gongqus.contains(2)){
            gongqus.clear();
            gongqus = allGroups;
        }
        //取两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, gongqus);
        List<Integer> gids = Lists.newArrayList();
        List<SafeGongQugroup> list = Lists.newArrayList();
        List<SafeGongQugroupOverdue> overdueCount = Lists.newArrayList();
        ZjSafeEventDTO eventDTO = new ZjSafeEventDTO();
        // 按照工区  与 时间
        if (intersectionList.size() > 0){
            list = zjSafeEventDAO.getAllStatusCount(intersectionList,safePerData.getSttime(),safePerData.getEndtime());
            overdueCount = zjSafeEventDAO.getOverdueCount(intersectionList);
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
                SsFProjects project = ssFGroupsDAO.getProjectById(qugroup.getGongquid());
                qugroup.setGongquname(project.getName());
//                if (qugroup.getGongquid() == 4) {
//                    qugroup.setGongquname("工区一");
//                } else if (qugroup.getGongquid() == 5) {
//                    qugroup.setGongquname("工区二");
//                } else if (qugroup.getGongquid() == 6) {
//                    qugroup.setGongquname("工区三");
//                } else if (qugroup.getGongquid() == 7) {
//                    qugroup.setGongquname("工区四");
//                }
            }
            //获得超期数据
            for (SafeGongQugroupOverdue over : overdueCount) {
//                if (over.getGongquid() == 4) {
//                    over.setGongquname("工区一");
//                } else if (over.getGongquid() == 5) {
//                    over.setGongquname("工区二");
//                } else if (over.getGongquid() == 6) {
//                    over.setGongquname("工区三");
//                } else if (over.getGongquid() == 7) {
//                    over.setGongquname("工区四");
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(over.getGongquid());
                over.setGongquname(project.getName());
            }
            //对list做排序
            list = list.stream().sorted(Comparator.comparing(SafeGongQugroup::getGongquid)).collect(Collectors.toList());

            eventDTO.setTotal(list);
            eventDTO.setOverdueList(overdueCount);
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
        Integer id = JwtUtil.getTokenUser().getId();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsByUserId(id);
        if (gongqus.size() == 0){
            return new ResponseBase(200,"查询成功", resb);
        }
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        //当用户的角色权限为2时,默认拥有所有权限
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
        List<EventGroup> list =
                zjSafeEventDAO.group(intersectionList,safePerData.getSttime(),safePerData.getEndtime());
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
            safeProcessNull(intersectionList, resb);
        }
//        if (projectId == 3){
        if(CollUtil.isNotEmpty(collect)) {
            collect.forEach((key, value)->{
//                if(key == 4){
//                    resb.add(new GqDataAll("工区一", key, value));
//                }else if(key == 5){
//                    resb.add(new GqDataAll("工区二", key, value));
//                }else if(key == 6){
//                    resb.add(new GqDataAll("工区三", key, value));
//                }else if(key == 7){
//                    resb.add(new GqDataAll("工区四", key, value));
//                }
                SsFProjects project = ssFGroupsDAO.getProjectById(key);
                resb.add(new GqDataAll(project.getName(), key, value));
            });
        }
//        }
        Collections.sort(resb);
        return new ResponseBase(200,"查询成功", resb);
    }

    public ResponseBase getgqAll() {

        List<SsFGroups> list= zjSafeEventDAO.getgqAll();
        return new ResponseBase(200,"",list);
    }

    public ResponseBase getprojectBygongqu(Integer id) {
        List<SsFGroups> list= zjSafeEventDAO.getprojectBygongqu(id);
        return new ResponseBase(200,"",list);

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
        //获取该查询角色的工区
        Integer id = JwtUtil.getTokenUser().getId();
        List<Integer> groups = ssFUserGroupDAO.getGroupsByUserId(id);
        if (groups.size() == 0){
            //当用户没有工区权限时, 返回空集合
            return new ResponseBase(200, "查询成功!", resb);
        }
        //查询该项目下的子级的单位工程
        List<Integer> allGroups = ssFUserGroupDAO.getAllGroupsByProjectId(projectId);
        //当用户的角色权限为2时,默认拥有所有权限
        if (groups.contains(2)){
            groups.clear();
            groups = allGroups;
        }
        //取到上述两个集合的交集
        List<Integer> intersectionList =
                (List<Integer>) CollectionUtils.intersection(allGroups, groups);

        //根据date 获取 开始与结束时间
        SafePerData safePerData = DateUtils.abcc(date);
        //新： 通过用拥有的工区id来查询 事件
        List<ZjSafeEvent> list =
                zjSafeEventDAO.getGQByGongQuIds(intersectionList,safePerData.getSttime(),safePerData.getEndtime());
        Map<Integer, List<ZjSafeEvent>> collect =
                list.stream().collect(Collectors.groupingBy(ZjSafeEvent::getGongquid));
        //如果map的size不等于该用户拥有的工区权限数,说明查询到的数据种有的工区没有
        if (collect.size() != intersectionList.size()){
            Iterator<Integer> iterator = collect.keySet().iterator();
            List<Integer> mapKeys = new ArrayList<>();
            while(iterator.hasNext()){
                Integer key = iterator.next();
                mapKeys.add(key);
            }
            intersectionList.removeAll(mapKeys);
            //处理工区为空时的数据
            safeProcessNull(intersectionList, resb);
        }
        collect.forEach((key,value)->{
            Map<Date, List<ZjSafeEvent>> collect1 = value.stream().collect(Collectors.groupingBy(ZjSafeEvent::getUploadtime));
            List<String> a = new ArrayList<>();
            for (Date date1 : collect1.keySet()) {
                String da = DateUtils.toDay(date1);
                a.add(da);
            }
//            if(key == 4){
//                resb.add(new GqDataAll("工区一",key,a));
//            }else if(key == 5){
//                resb.add(new GqDataAll("工区二",key,a));
//            }else if(key == 6){
//                resb.add(new GqDataAll("工区三",key,a));
//            }else if(key == 7){
//                resb.add(new GqDataAll("工区四",key,a));
//            }
            SsFProjects project = ssFGroupsDAO.getProjectById(key);
            resb.add(new GqDataAll(project.getName(), key, a));
        });
        return new ResponseBase(200,"获取每天安全数据成功!", resb);
    }


    public ResponseBase getDay(String date, Integer gqid) {
        SafePerData safePerData = DateUtils.abccc(date);
        List<ZjSafeEvent> list = zjSafeEventDAO.getByGQ(gqid,safePerData.getSttime(),safePerData.getEndtime());
        return new ResponseBase(200,"",list);

    }

    /**
     * 对获取每天质量数据时,工区为空时的处理
     * @param groups 工区
     * @param resb
     * @return resb
     */
    private List<GqDataAll> safeProcessNull(List<Integer> groups, List<GqDataAll> resb){
        for (Integer group : groups) {
            GqDataAll dataAll = new GqDataAll();
            List<Object> objects = new ArrayList<>();

            SsFProjects project = ssFGroupsDAO.getProjectById(group);
            dataAll.setName(project.getName());
            dataAll.setSort(group);
            dataAll.setObject(objects);
//            if (group == 4){
//                dataAll.setName("工区一");
//                dataAll.setSort(group);
//                dataAll.setObject(objects);
//            } else if (group == 5){
//                dataAll.setName("工区二");
//                dataAll.setSort(group);
//                dataAll.setObject(objects);
//            } else if (group == 6){
//                dataAll.setName("工区三");
//                dataAll.setSort(group);
//                dataAll.setObject(objects);
//            } else if (group == 7){
//                dataAll.setName("工区四");
//                dataAll.setSort(group);
//                dataAll.setObject(objects);
//            }
            resb.add(dataAll);
        }
        return resb;
    }

    public ResponseBase deleteEvent(List<Integer> gids){
        //先判断操作的用户groupid是否为顶级或者总部(1,2),否则没有该权限
        Integer userId = JwtUtil.getTokenUser().getId();
        SsFUserRole userRole = userRoleDAO.getByUserid(userId);
        if (userRole.getRoleid() != 1 && userRole.getRoleid() !=2){
            return new ResponseBase(500, "该操作用户没有删除权限", null);
        }

        List<ZjSafeEvent> obj = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();

        //删除之前须到数据库查询需要删除的数据是否存在
        List<ZjSafeEvent> safeEvents = zjSafeEventDAO.getDataById(gids);
        List<Integer> ids = new ArrayList<>();
        for (ZjSafeEvent safeEvent : safeEvents) {
            ids.add(safeEvent.getId());
            //往消息推送的里面加上数据
            obj.add(safeEvent);
            userIds.add(safeEvent.getUploadid());
            userIds.add(safeEvent.getModifyid());
        }
        //需要推送的userIds与数据 去重
        obj = obj.stream().distinct().collect(Collectors.toList());
        userIds = userIds.stream().distinct().collect(Collectors.toList());
        //传进来的id数量和查询到的id数量不一致,说明有的数据不存在
        if (gids.size() != ids.size()){
            gids.removeAll(ids);
            return new ResponseBase(500, "删除失败", "删除失败, " + gids + "这条数据不存在");
        }
        //删除
        Integer rows = zjSafeEventDAO.batchDeleteById(gids);
        //并且删除mongdb里面对应的图片纪录
        for (ZjSafeEvent safeEvent : safeEvents) {
            String uploadUrls = safeEvent.getUploadurl();
            String modifyUrls = safeEvent.getModifyurl();
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
        messageDTO.setEvent("监理删除安全检查事件");
        messageDTO.setUserIds(userIds);
        String message = JSON.toJSONString(messageDTO);
        webSocketServer.onMessage(message, null);

        if (rows == gids.size()){
            return new ResponseBase(200, "删除成功", null);
        }
        return new ResponseBase(500, "删除失败", "未知的失败原因");
    }

    public ResponseBase getGroups(Integer projectId) {
        // 查询level=4的项目数据
        List<SsFProjects> allGongQuByProjectId = projectsDAO.getAllGongQuByProjectId(projectId);
        // 转为map对象
        Map<Integer, SsFProjects> ssFProjectsMap = allGongQuByProjectId
                .stream()
                .collect(Collectors.toMap(SsFProjects::getId, ssFProjects -> ssFProjects, (oldObj, netObj) -> oldObj));
        // 查询用户拥有的项目
        Integer userId = JwtUtil.getTokenUser().getId();
        List<SsFUserGroup> userGroups = ssFUserGroupDAO.getGroups(userId);
        // 数据集合取交集，用户拥有权限的项目
        List<GqDataAll> datas = Lists.newArrayList();
        for (SsFUserGroup userGroup : userGroups) {
            GqDataAll data = new GqDataAll();
            // 判断当前的group是否数据目标项目
            if (ssFProjectsMap.containsKey(userGroup.getGroupid())){
                data.setName(ssFProjectsMap.get(userGroup.getGroupid()).getName());
                data.setSort(userGroup.getGroupid());
                datas.add(data);
            }
        }
        return new ResponseBase(200, "查询成功!", datas);
    }

    public ResponseBase getGroups1(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取工区id
        List<GqDataAll> datas = Lists.newArrayList();
        List<Integer> groups = Lists.newArrayList();
        //通过token获取用户所拥有的工区数
        Integer userId = JwtUtil.getTokenUser().getId();
        List<Integer> groupIds = ssFUserGroupDAO.getGroupsByUserId(userId);
        if (groupIds.contains(2) || groupIds.contains(3)){
            //说明 该用户工区权限为总部, 先清空
            groupIds.clear();
            groupIds = ssFUserGroupDAO.getAllGroups();
            for (Integer groupId : groupIds) {
                GqDataAll data = new GqDataAll();
                SsFProjects project = ssFGroupsDAO.getProjectById(groupId);
                if (project != null){
                    data.setName(project.getName());
                    data.setSort(groupId);
                }
                datas.add(data);
            }
        } else{
            List<SsFUserGroup> userGroups = ssFUserGroupDAO.getGroups(userId);
            for (SsFUserGroup userGroup : userGroups) {
                GqDataAll data = new GqDataAll();
                groups.add(userGroup.getGroupid());
                SsFProjects project = ssFGroupsDAO.getProjectById(userGroup.getGroupid());
                if (project != null){
                    data.setName(project.getName());
                    data.setSort(userGroup.getGroupid());
                }
                datas.add(data);
            }
        }
        return new ResponseBase(200, "查询成功!", datas);
    }

    public ResponseBase getPersonLiableByGroup(Integer projectId, Integer group){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<SafeCheck> safeChecks = Lists.newArrayList();
        List<Integer> groupIds = ssFGroupsDAO.getGroupsById(projectId);
        if (groupIds.contains(group)){
            //通过groupid 查询对应的有多少用户
            List<SsFUserGroup> userGroups = ssFUserGroupDAO.getAllByGroupId(group);
            List<Integer> userIds = Lists.newArrayList();
            for (SsFUserGroup userGroup : userGroups) {
                userIds.add(userGroup.getUserid());
            }
            //通过用户id查询
            if (userIds != null && userIds.size() != 0){
                safeChecks = ssFUsersDAO.getInfoById(userIds);
            }
            return new ResponseBase(200, "查询查询成功!", safeChecks);
        } else {
            return new ResponseBase(521, "该项目工程id不在该项目id下!");
        }
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
                Integer row = zjSafeEventDAO.getById(id);
                if (row == 1){
                    zjSafeEventDAO.updateProjectId(projectId, id);
                }
            });
            return new ResponseBase(200, "更新质量检查表组织成功!");
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseBase(500, "更新质量检查表组织失败！");
    }
}
