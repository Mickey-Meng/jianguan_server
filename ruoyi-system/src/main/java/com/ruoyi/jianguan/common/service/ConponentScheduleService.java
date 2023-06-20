package com.ruoyi.jianguan.common.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.common.dao.ConponentscheduleDAO;
import com.ruoyi.common.core.domain.dto.ConponentScheuleDto;
import com.ruoyi.common.core.domain.entity.Conponentschedule;
import com.ruoyi.common.utils.jianguan.zjrw.CaulateUtil;
import com.ruoyi.jianguan.common.dao.ProjectsDAO;
import com.ruoyi.jianguan.common.dao.ZjConponentProducetimeDAO;
import com.ruoyi.jianguan.common.domain.dto.NewCheckData;
import com.ruoyi.jianguan.common.domain.dto.ZjConponentProcessWarningDTO;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ProjectsDAO projectsDAO;

    @Autowired
    private ZjConponentProducetimeDAO zjConponentProducetimeDao;
    @Autowired
    private ISysDictDataService dictDataService ;



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



    public ResponseBase getProcessWarning(NewCheckData newCheckData) {
        if (newCheckData.getProjectId() <= 0) {
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(newCheckData.getProjectId());
        if (count1 <= 0) {
            return new ResponseBase(200, "该项目id无数据!");
        }
        //获取前端传的项目id,下属的所有子单位工程id
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
        List<ZjConponentProcessWarningDTO> list = null;
        List<ZjConponentProcessWarningDTO> listVo = new ArrayList<ZjConponentProcessWarningDTO>();

        SysDictData dictData = new SysDictData();
        dictData.setDictType("jg_process_warning_level");

        List<SysDictData>  sysDictDataList =  dictDataService.selectDictDataList(dictData);
        Map<String,Integer> map = new HashMap<String,Integer>();
        sysDictDataList.forEach(sysDictData -> {
            map.put(sysDictData.getDictLabel(),Integer.parseInt(sysDictData.getDictValue()));
        });


        if (!proChildCode.isEmpty()) {
            list = zjConponentProducetimeDao.getProcessWarning(newCheckData);
            for ( ZjConponentProcessWarningDTO zjConponentProcessWarningDTO : list ) {
                if(null != zjConponentProcessWarningDTO.getActulendtime() && null != zjConponentProcessWarningDTO.getOverdueDays()){//1 已经完成
                    if (zjConponentProcessWarningDTO.getOverdueDays()>0){
                        zjConponentProcessWarningDTO.setStatus(2);
                    }else {
                        zjConponentProcessWarningDTO.setStatus(1);
                    }
                }else {// 2 未完成
                    String level = zjConponentProcessWarningDTO.getWarninglevel() == null ? "1级" :zjConponentProcessWarningDTO.getWarninglevel() ;
                    Integer levelDays = map.get(level);//获取当前构件最小预警天数
                    if (zjConponentProcessWarningDTO.getStartDays() != null && zjConponentProcessWarningDTO.getStartDays() <= levelDays) {
                        zjConponentProcessWarningDTO.setStatus(0);
                    }
                }
                listVo.add(zjConponentProcessWarningDTO);
            }

        }
        return new ResponseBase(200, "", listVo);
    }

}
