package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanPageVo;


public interface ConstructionPlanService extends IService<ConstructionPlan> {



    ResponseBase addOrUpdate(ConstructionPlanSaveDTO constructionPlanSaveDTO);


    ConstructionPlanDetailVo getInfoById(Long id);


    PageInfo<ConstructionPlanPageVo> getPageInfo(ConstructionPlanPageDTO pageDto);



}