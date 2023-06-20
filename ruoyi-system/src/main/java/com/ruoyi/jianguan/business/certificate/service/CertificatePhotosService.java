package com.ruoyi.jianguan.business.certificate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.certificate.domain.dto.CertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;


/**
 * 计划管理-证照管理Service接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface CertificatePhotosService extends IService<CertificatePhotos> {


    /**
     * 新增或者更新计划管理-证照管理表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(PlanCertificatePhotosSaveDTO saveDto);


    /**
     * 查询[计划管理-证照管理]分页数据
     * @param pageDto
     * @return
     */
    public PageInfo<PlanCertificatePhotosVo> getPlanPageInfo(CertificatePhotosPageDTO pageDto);

    /**
     * 查询[进度管理-证照管理]分页数据
     * @param pageDto
     * @return
     */
    public PageInfo<ProgressCertificatePhotosVo> getProgressPageInfo(CertificatePhotosPageDTO pageDto);

    /**
     * 通过id获取一条计划管理-证照管理表数据
     *
     * @param id
     * @return
     */
    PlanCertificatePhotosVo getPlanInfoById(Long id);

    /**
     * 通过id获取一条进度管理-证照管理表数据
     *
     * @param id
     * @return
     */
    ProgressCertificatePhotosVo getProgressInfoById(Long id);

    ResponseBase updateUploadFile(ProgressCertificatePhotosSaveDTO saveDto);
}