package com.ruoyi.jianguan.business.contract.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypePageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPrototype;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypePageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConstructionPrototypeMapper extends BaseDaoMapper<ConstructionPrototype> {


    List<ConstructionPrototypePageVo> getPageInfo(@Param("pageDto") ConstructionPrototypePageDTO pageDto);

}