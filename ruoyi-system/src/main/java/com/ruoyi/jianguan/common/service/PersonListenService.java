package com.ruoyi.jianguan.common.service;

import com.ruoyi.common.core.dao.PersonListenDAO;
import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.model.ZjPersonChange;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.common.utils.jianguan.zjrw.RestApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/17 17:01
 * @Version : 1.0
 * @Description : 监听实现类
 **/
@Service
public class PersonListenService {

    Logger log = LoggerFactory.getLogger(PersonListenService.class);

    @Autowired
    private PersonListenDAO personListenDAO;

    /**
     * 监听流程完成之后,报审调用接口
     * @param processInstanceId
     */
    public void finishContract(String processInstanceId){
        // todo 通过processInstanceId获取业务数据,目前暂时报审只更改业务数据状态 state
        log.info("监听到有 '人员报审' 流程完成!");
        PersonDTO person = personListenDAO.getByProcessId(processInstanceId);
        if(person != null){
            //设置2为已审批流程
            personListenDAO.updateContractStatus(2, processInstanceId);
        }
    }

    /**
     * 监听流程完成之后, 变更调用接口
     * @param processInstanceId
     */
    public void finishPersonChange(String processInstanceId){
        log.info("监听到有 '人员变更' 流程完成!");
        ZjPersonChange personChange = personListenDAO.getChangeByProcessId(processInstanceId);
        if (personChange != null) {
            personListenDAO.updateChangeStatus(2, processInstanceId);
            //获取变更前人员id
            Integer beforePersonId = personChange.getBeforePersonId();
            //获取变更后人员id
            Integer afterPersonId = personChange.getAfterPersonId();
            //获取变更前人员对应岗位(角色)id
            SsFUserRole beforePersonRole = personListenDAO.getByUserid(beforePersonId);
            Integer beforePersonRoleId = beforePersonRole.getRoleid();
            //修改变更后人员角色id
            personListenDAO.updateRoleByUserId(beforePersonRoleId, afterPersonId);
            //最后删除变更前人员id对应岗位角色id数据
            personListenDAO.deleteByUserId(beforePersonRoleId, beforePersonId);
        }

    }

    /**
     * 监听流程完成之后, 请假调用接口
     * @param processInstanceId
     */
    public void finishPersonLeave(String processInstanceId){
        // todo 通过processInstanceId获取业务数据,
        //  目前暂时请假只更改业务数据状态 state 并通知app该人员请假审批通过
        log.info("监听到有 '人员请假' 流程完成!");
        ZjPersonLeave personLeave = personListenDAO.getLeaveByProcessId(processInstanceId);
        if (personLeave != null){
            //设置该条请假数据为流程已审批完成
            personListenDAO.updateLeaveStatus(2, processInstanceId);
            //最后把消息发送app
            String cid = personListenDAO.getCidByUserid(personLeave.getLeavePersonId());
            if (cid != null && !cid.equals("")){
                RestApiUtils.sendMessageToCid(cid, "新消息通知！", "你最近申请的请假流程已审批完成！");
            }
        }
    }
}
