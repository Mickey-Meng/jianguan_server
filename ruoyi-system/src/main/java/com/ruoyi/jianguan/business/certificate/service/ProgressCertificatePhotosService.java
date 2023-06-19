package com.ruoyi.jianguan.business.certificate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.ProgressCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentPageVo;

/**
 * 合同付款Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ProgressCertificatePhotosService extends IService<ProgressCertificatePhotos> {


    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(ProgressCertificatePhotosSaveDTO saveDto);

    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     * @return
     */
    ProgressCertificatePhotosVo getInfoById(Long id);

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ProgressCertificatePhotosVo> getPageInfo(ProgressCertificatePhotosPageDTO pageDto);



}