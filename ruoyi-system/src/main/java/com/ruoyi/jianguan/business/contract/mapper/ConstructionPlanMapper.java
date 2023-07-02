package com.ruoyi.jianguan.business.contract.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConstructionPlanMapper extends BaseDaoMapper<ConstructionPlan> {


    List<ConstructionPlanPageVo> getPageInfo(@Param("pageDto") ConstructionPlanPageDTO pageDto);

}