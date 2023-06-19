package com.ruoyi.jianguan.business.contract.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.DailyReport;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyReportMapper extends BaseDaoMapper<DailyReport> {


    List<DailyReportPageVo> getPageInfo(@Param("pageDto") DailyReportPageDTO pageDto);

}