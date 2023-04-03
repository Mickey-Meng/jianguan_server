package com.ruoyi.czjg.zjrw.service;

import com.google.common.collect.Lists;
import com.ruoyi.czjg.zjrw.dao.ConponentscheduleDAO;
import com.ruoyi.common.core.domain.dto.ConponentScheuleDto;
import com.ruoyi.common.core.domain.entity.Conponentschedule;
import com.ruoyi.common.utils.zjbim.zjrw.CaulateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/26 11:50 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class ConponentScheduleService {

    @Autowired
    ConponentscheduleDAO conponentscheduleDAO;


    public List<ConponentScheuleDto> getScheuleAll() {

        List<Conponentschedule> conponentscheduleList = conponentscheduleDAO.getAll();
        List<ConponentScheuleDto> conponentScheuleDtoList = Lists.newArrayList();
        if(conponentscheduleList.size()==0){
            return conponentScheuleDtoList;
        }else{
            conponentscheduleList.stream().forEach((conponentschedule)->{
                conponentScheuleDtoList.add(CaulateUtil.getConponentScheuleDto(conponentschedule));
            });
            return conponentScheuleDtoList;
        }
    }
}
