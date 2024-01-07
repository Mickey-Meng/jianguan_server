package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportPageVo;


public interface MaterialBrandReportService extends IService<MaterialBrandReport> {
    /**
     *
     * @param type 1 材料品牌报审 2 材料样板确认 3 材料进厂验收
     *
     */
    ResponseBase addOrUpdate(MaterialBrandReportSaveDTO constructionPlanSaveDTO,String type);

    MaterialBrandReportDetailVo getInfoById(Long id);

    PageInfo<MaterialBrandReportPageVo> getPageInfo(MaterialBrandReportPageDTO pageDto);

    ResponseBase addOrUpdateMaterialBrandReport(MaterialBrandReportSaveDTO saveDto);

    ResponseBase addOrUpdateMaterialSampleConfirmation(MaterialBrandReportSaveDTO saveDto);

    ResponseBase addOrUpdateMaterialAcceptance(MaterialBrandReportSaveDTO saveDto);
}