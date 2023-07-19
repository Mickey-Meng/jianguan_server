package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanPageVo;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportPageVo;


public interface MaterialBrandReportService extends IService<MaterialBrandReport> {
    ResponseBase addOrUpdate(MaterialBrandReportSaveDTO constructionPlanSaveDTO);

    MaterialBrandReportDetailVo getInfoById(Long id);

    PageInfo<MaterialBrandReportPageVo> getPageInfo(MaterialBrandReportPageDTO pageDto);
}