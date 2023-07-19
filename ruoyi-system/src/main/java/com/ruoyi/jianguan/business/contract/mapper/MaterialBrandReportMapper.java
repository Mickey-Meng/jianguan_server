package com.ruoyi.jianguan.business.contract.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialBrandReportMapper extends BaseDaoMapper<MaterialBrandReport> {


    List<MaterialBrandReportPageVo> getPageInfo(@Param("pageDto") MaterialBrandReportPageDTO pageDto);

}