package com.ruoyi.jianguan.common.service;

import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.entity.ZjConponentProducetime;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.dao.*;
import com.ruoyi.jianguan.common.domain.entity.SsFUserOnline;
import com.ruoyi.jianguan.common.domain.entity.ZjQualityEvent;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/21 17:21
 * @Version : 1.0
 * @Description :
 **/
@Service
public class ScriptService {

    Logger log = LoggerFactory.getLogger(ScriptService.class);

    @Autowired
    private ProduceandrecodeDAO produceDAO;
    @Autowired
    private ProduceDAO producesDAO;
    @Autowired
    private ZjConponentProducetimeDAO producetimeDAO;
    @Autowired
    private ConponentDAO conponentDAO;
    @Autowired
    private ZjSafeEventDAO safeEventDAO;
    @Autowired
    private ZjQualityEventDAO qualityEventDAO;
    @Autowired
    private SsFUsersDAO usersDAO;
    @Autowired
    private SsFUserOnlineDAO userOnlineDAO;
    @Autowired
    private SsFUserRoleDAO userRoleDAO;

    public ResponseBase moveProduce(){
        //获取所有工序填报数据
        List<Produceandrecode> recodes = produceDAO.getAll();
        for (Produceandrecode recode : recodes) {
            //获取以前的人员id
            Integer oldUpdateUserId = recode.getUpdateuser();
            Integer oldCheckUserId = recode.getCheckuser();
            //查询用户copy表查到人名,然后到新的user表里面找到对应的userId
            String oldUpdateUserName = usersDAO.getCopyNameByUserId(oldUpdateUserId);
            String oldCheckUserName = usersDAO.getCopyNameByUserId(oldCheckUserId);

            SsFUsers newUpdateUser = usersDAO.checkLogin(oldUpdateUserName);
            SsFUsers newCheckUser = usersDAO.checkLogin(oldCheckUserName);
            if (newUpdateUser == null || newCheckUser == null){
                continue;
            }
            Integer newUpdateUserId = newUpdateUser.getId();
            Integer newCheckUserId = newCheckUser.getId();

            recode.setUpdateuser(newUpdateUserId);
            recode.setCheckuser(newCheckUserId);
            //最后修改表的值
            produceDAO.updateByPrimaryKey(recode);
        }
        log.info("迁移produceandrecode表数据'updateUser'与'checkUser'成功!");
        //工序填报表修改完之后, 同步 工序进度表
        List<ZjConponentProducetime> producetimes = producetimeDAO.getAllNotNullTime();
        for (ZjConponentProducetime producetime : producetimes) {
            producetimeDAO.updateByConponentcode(producetime);
        }
        log.info("工序填报表时间迁移成功!");
        return new ResponseBase(200, "同步迁移数据成功!");
    }

    public ResponseBase moveSafeEvent(){
        //获取所有安全检查数据
        List<ZjSafeEvent> safeEvents = safeEventDAO.getAllByTime();
        for (ZjSafeEvent safeEvent : safeEvents) {
            Integer oldUploadId = safeEvent.getUploadid();
            Integer oldModifyId = safeEvent.getModifyid();

            //查询用户copy表查到人名,然后到新的user表里面找到对应的userId
            String oldUploadName = usersDAO.getCopyNameByUserId(oldUploadId);
            String oldModifyName = usersDAO.getCopyNameByUserId(oldModifyId);

            SsFUsers newUploadUser = usersDAO.checkLogin(oldUploadName);
            SsFUsers newModifyUser = usersDAO.checkLogin(oldModifyName);
            if (newUploadUser == null || newModifyUser == null){
                continue;
            }
            Integer newUploadId = newUploadUser.getId();
            Integer newModifyId = newModifyUser.getId();
            safeEvent.setUploadid(newUploadId);
            safeEvent.setModifyid(newModifyId);

            safeEventDAO.updateByPrimaryKey(safeEvent);
        }
        log.info("安全检测数据人员迁移成功!");
        return new ResponseBase(200, "同步迁移数据成功!");
    }

    public ResponseBase moveQualityEvent(){
        //获取所有安全检查数据
        List<ZjQualityEvent> qualityEvents = qualityEventDAO.getAllByTime();
        for (ZjQualityEvent qualityEvent : qualityEvents) {
            Integer oldUploadId = qualityEvent.getUploadid();
            Integer oldModifyId = qualityEvent.getModifyid();

            //查询用户copy表查到人名,然后到新的user表里面找到对应的userId
            String oldUploadName = usersDAO.getCopyNameByUserId(oldUploadId);
            String oldModifyName = usersDAO.getCopyNameByUserId(oldModifyId);

            SsFUsers newUploadUser = usersDAO.checkLogin(oldUploadName);
            SsFUsers newModifyUser = usersDAO.checkLogin(oldModifyName);
            if (newUploadUser == null || newModifyUser == null){
                continue;
            }
            Integer newUploadId = newUploadUser.getId();
            Integer newModifyId = newModifyUser.getId();
            qualityEvent.setUploadid(newUploadId);
            qualityEvent.setModifyid(newModifyId);

            qualityEventDAO.updateByPrimaryKey(qualityEvent);
        }
        log.info("质量检测数据人员迁移成功!");
        return new ResponseBase(200, "同步迁移数据成功!");
    }

    public ResponseBase getProduceTime(){
        List<String> componentCodeCopy = producetimeDAO.getCopyCodeNotNullTime();
        List<String> componentCode = producetimeDAO.getCodeNotNullTime();

        log.info("构件进度复制表的四个时间不为空的个数: " + componentCodeCopy.size());
        log.info("构件进度表的四个时间不为空的个数: " + componentCode.size());
        List<String> list = new LinkedList<>();
        for (String s : componentCode) {
            if (componentCodeCopy.contains(s)){
                continue;
            }
            list.add(s);
        }
        System.out.println("没有的: " + list);

        return new ResponseBase(200, "成功!", list);
    }

    @Transactional
    public ResponseBase updateProduceId(){
        List<Conponent> conponents = conponentDAO.getAllLMId();
        log.info("道路的构件有: " + conponents.size() + "个");
        for (Conponent conponent : conponents) {
            Integer id = conponent.getId();
            String conponentcode = conponent.getConponentcode();
            producetimeDAO.updateConponentIdByCode(id, conponentcode);
        }
        log.info("修改构件进度表的构件id成功!");
        return new ResponseBase(200, "修改构件进度表的构件id成功!");
    }

    @Transactional
    public ResponseBase updateUserOnline(){
        List<SsFUserOnline> userOnlines = userOnlineDAO.getAll();
        for (SsFUserOnline userOnline : userOnlines) {
            Integer userId = userOnline.getUserid();
            SsFUserRole userRole = userRoleDAO.getByUserid(userId);
            Integer roleId = userRole.getRoleid();
            userOnlineDAO.updateRoleBuUserId(userId, roleId);
        }
        log.info("修改人员在线表的人员对应角色id 完毕!");
        return new ResponseBase(200, "修改人员在线表的人员对应角色id完毕!");
    }

    @Transactional
    public ResponseBase updateProduceTime(){
        List<Produceandrecode> prodeuces = produceDAO.getFinishTimeCode();
        int flag = 0;
        for (Produceandrecode produce : prodeuces) {
            String componentCode = produce.getConponentcode();
            Date updateTime = produce.getUpdatetime();
            String type = produce.getConponenttype();
            Integer produceId = produce.getProduceid();

            List<Integer> produceIds = producesDAO.getIdByTypeDesc(type);
            if (produceId == produceIds.get(0)){
                ZjConponentProducetime producetime = producetimeDAO.getZjByConponentId(produce.getConponentid());
                if (producetime.getActulendtime() == null && !producetime.getActulendtime().equals("")){
                    producetimeDAO.updateFinishTime(componentCode, updateTime);
                    flag = flag ++;
                }
            }
        }
        log.info("修改构件完成时间成功! 一共修改个数为：" + flag);
        return new ResponseBase(200, "修改构件完成时间成功!");
    }
}
