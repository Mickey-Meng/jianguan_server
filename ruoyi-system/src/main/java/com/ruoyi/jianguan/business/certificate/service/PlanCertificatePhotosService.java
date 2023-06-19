package com.ruoyi.jianguan.business.certificate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.PlanCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;

/**
 * 计划管理-证照管理Service接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface PlanCertificatePhotosService extends IService<PlanCertificatePhotos> {


    /**
     * 新增或者更新计划管理-证照管理表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(PlanCertificatePhotosSaveDTO saveDto);

    /**
     * 通过id获取一条计划管理-证照管理表数据
     *
     * @param id
     * @return
     */
    PlanCertificatePhotosVo getInfoById(Long id);

    /**
     * 分页查询计划管理-证照管理表数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<PlanCertificatePhotosVo> getPageInfo(PlanCertificatePhotosPageDTO pageDto);

}