package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.DailyReport;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportPageVo;


public interface DailyReportService extends IService<DailyReport> {



    ResponseBase addOrUpdate(DailyReportSaveDTO saveDto);


    DailyReportDetailVo getInfoById(Long id);


    PageInfo<DailyReportPageVo> getPageInfo(DailyReportPageDTO pageDto);



}