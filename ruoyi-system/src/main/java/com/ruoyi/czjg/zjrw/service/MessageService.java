package com.ruoyi.czjg.zjrw.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.dao.*;
import com.ruoyi.czjg.zjrw.domain.dto.MessageUserDTO;
import com.ruoyi.czjg.zjrw.domain.entity.*;
import com.ruoyi.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/25 11:10
 * @description :
 **/
@Service
public class MessageService {

    @Autowired
    ZjSafeEventDAO zjSafeEventDAO;
    @Autowired
    ZjQualityEventDAO zjQualityEventDAO;
    @Autowired
    SsFUserGroupDAO userGroupDAO;
    @Autowired
    SsFGroupsDAO groupsDAO;
    @Autowired
    @Qualifier("zjProduceandrecodeDAO")
    ProduceandrecodeDAO produceandrecodeDAO;

    public ResponseBase getSupervisorMessagePrompt(){
        //获取用户信息
        PowerData user = JwtUtil.getTokenUser();

        MessageUserDTO userDTO = new MessageUserDTO();
        //说明该用户为监理, 监理 待办消息只查看 status为 1(施工申请延期),2(施工已经提交了整改,监理确认整改) 时
        List<ZjSafeEvent> delaySafeEvents = zjSafeEventDAO.getDelayByJianLiId(user.getId());
        List<ZjSafeEvent> rectificationSafeEvent = zjSafeEventDAO.getRectificationByJianLiId(user.getId());
        List<ZjQualityEvent> delayQualityEvents = zjQualityEventDAO.getDelayByJianLiId(user.getId());
        List<ZjQualityEvent> rectificationQualityEvent = zjQualityEventDAO.getRectificationByJianLiId(user.getId());
        List<Produceandrecode> processFilling = produceandrecodeDAO.getProcessByJianLiId(user.getId());
        userDTO.setDelayQualityEvent(delayQualityEvents);
        userDTO.setDelaySafeEvent(delaySafeEvents);
        userDTO.setDoRectificationSafeEvent(rectificationSafeEvent);
        userDTO.setDoRectificationQualityEvent(rectificationQualityEvent);
        userDTO.setProcessFilling(processFilling);

        //新增: 监理也可以查看待整改事件
        List<ZjSafeEvent> safeEvents = zjSafeEventDAO.getGQByUserId(user.getId());
        List<ZjQualityEvent> qualityEvents = zjQualityEventDAO.getGQByUserId(user.getId());
        userDTO.setZjQualityEventList(qualityEvents);
        userDTO.setZjSafeEventList(safeEvents);

        List<Integer> produceIds = produceandrecodeDAO.getProduceIdsByUserId(user.getId());
        List<Produceandrecode> produceCopy = Lists.newArrayList();
        if (produceIds.size() > 0) {
            produceCopy = produceandrecodeDAO.getProduceCopyByGids(produceIds);
        }
        userDTO.setProduceCopy(produceCopy);

        return new ResponseBase(200, "查看消息详情成功" , userDTO);
    }

    public ResponseBase getConstructionMessagePrompt(){
        //获取用户信息
        PowerData user = JwtUtil.getTokenUser();

        MessageUserDTO userDTO = new MessageUserDTO();
        //施工 通过 id 查询 整改id 和  事件为提交状态的事件 施工用户只查看为  status为0的一条数据
        List<ZjSafeEvent> safeEvents = zjSafeEventDAO.getGQByUserId(user.getId());
        List<ZjQualityEvent> qualityEvents = zjQualityEventDAO.getGQByUserId(user.getId());
        List<Produceandrecode> processFilling = produceandrecodeDAO.getApprovedProcess(user.getId());
        userDTO.setProcessFilling(processFilling);
        userDTO.setZjQualityEventList(qualityEvents);
        userDTO.setZjSafeEventList(safeEvents);

        return new ResponseBase(200, "查看消息详情成功" , userDTO);
    }

    public ResponseBase readProduce(Integer id){
        try {
            if (id == null || id.equals("")){
                return new ResponseBase(200, "工序id为空!");
            } else if (id <= 0){
                return new ResponseBase(200, "请检查工序id的有效性!");
            }
            PowerData userInfo = JwtUtil.getTokenUser();
            ProduceandrecodeUser user =
                    produceandrecodeDAO.getProduceUserByGidAndUserId(id, userInfo.getId());
            if (user == null){
                return new ResponseBase(200, "没有查到该用户对应的工序!");
            }
            produceandrecodeDAO.updateProduceUserState(id, userInfo.getId());
            return new ResponseBase(200, "已读工序完成!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

}
