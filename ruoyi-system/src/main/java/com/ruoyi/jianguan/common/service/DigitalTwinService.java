package com.ruoyi.jianguan.common.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.dao.DigitalTwinDAO;
import com.ruoyi.jianguan.common.dao.ProjectsDAO;
import com.ruoyi.jianguan.common.domain.dto.DigitalTwinReturnDTO;
import com.ruoyi.jianguan.common.domain.entity.ZjDigitalTwin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/3 11:24
 * @description : 工点信息
 **/
@Service
public class DigitalTwinService {

    @Autowired
    DigitalTwinDAO twinDAO;
    @Autowired
    private ProjectsDAO projectsDAO;

    Logger log = LoggerFactory.getLogger(DigitalTwinService.class);

    public ResponseBase addData(ZjDigitalTwin zjDigitalTwin){
        if (zjDigitalTwin.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(zjDigitalTwin.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(zjDigitalTwin.getProjectId());
        if (!proChildCode.contains(zjDigitalTwin.getProjectcode())){
            //如果传的项目标段下面所有的单位工程里面不包含有填的这个code
            return new ResponseBase(602, "添加工点失败!要添加的单位工程工点不属于该项目标段!");
        }
        Integer row = twinDAO.insert(zjDigitalTwin);
        if (row == 1){
            return new ResponseBase(200, "添加工点数据成功", null);
        }
        return new ResponseBase(500, "添加工点数据失败!");
    }

    public ResponseBase getData(Integer projectId){
        if (projectId <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(projectId);
        List<ZjDigitalTwin> digitalTwins = twinDAO.getData(proChildCode);
        List<DigitalTwinReturnDTO> returnDTOS = Lists.newArrayList();
        for (ZjDigitalTwin digitalTwin : digitalTwins) {
            DigitalTwinReturnDTO returnDTO = new DigitalTwinReturnDTO();
            //去conponent表查所有关于该project的数量
            Integer sum = twinDAO.getAllByProjectCode(digitalTwin.getProjectcode());
            //去zj_conponent_producetime表查所有关于project和有actulendtime的数量
            Integer finish = twinDAO.getFinishByProjectCode(digitalTwin.getProjectcode());
            returnDTO.setId(digitalTwin.getId());
            returnDTO.setGroupid(digitalTwin.getGroupid());
            returnDTO.setProjectCode(digitalTwin.getProjectcode());
            returnDTO.setProjectName(digitalTwin.getProjectname());
            returnDTO.setSum(sum);
            returnDTO.setFinish(finish);
            returnDTO.setType(digitalTwin.getType());
            returnDTO.setAltitude(digitalTwin.getAltitude());
            returnDTO.setLonglatitude(digitalTwin.getLonglatitude());
            //去zj_conponent_producetime表查所有关于project和有actulsttime并且没有actulendtime的数量
            Integer work = twinDAO.getWorkByProjectCode(digitalTwin.getProjectcode());
            returnDTO.setWork(work);

            returnDTOS.add(returnDTO);
        }
        return new ResponseBase(200 , "查询工点数据成功!", returnDTOS);
    }

    public ResponseBase delData(Integer id){
        if (id == null){
            return new ResponseBase(500, "请输入有效的需要删除的id");
        }
        Integer row = twinDAO.delDataById(id);
        if (row == 1){
            return new ResponseBase(200, "删除成功!");
        }
        return new ResponseBase(500, "删除失败!");
    }

    public ResponseBase updateData(ZjDigitalTwin zjDigitalTwin){
        if (zjDigitalTwin.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(zjDigitalTwin.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id, 获取该项目id下面所有单位工程的code
        List<String> proChildCode = projectsDAO.getChildCode(zjDigitalTwin.getProjectId());
        if (!proChildCode.contains(zjDigitalTwin.getProjectcode())){
            //如果传的项目标段下面所有的单位工程里面不包含有填的这个code
            return new ResponseBase(602, "添加工点失败!要添加的单位工程工点不属于该项目标段!");
        }
        Integer row = twinDAO.updateData(zjDigitalTwin);
        if (row == 1){
            return new ResponseBase(200, "修改工点信息成功!");
        }
        return new ResponseBase(500, "修改工点信息失败!");
    }
}
