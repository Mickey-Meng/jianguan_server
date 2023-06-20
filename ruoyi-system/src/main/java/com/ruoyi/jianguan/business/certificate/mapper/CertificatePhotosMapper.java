package com.ruoyi.jianguan.business.certificate.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.certificate.domain.dto.CertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 计划管理-证照管理Mapper接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface CertificatePhotosMapper extends BaseDaoMapper<CertificatePhotos> {

    /**
     * 分页查询施计划管理-证照管理表数据
     *
     * @param pageDto
     * @return
     */
    List<PlanCertificatePhotosVo> getPlanPageInfo(@Param("pageDto") CertificatePhotosPageDTO pageDto);

    /**
     * 分页查询施进度管理-证照管理表数据
     *
     * @param pageDto
     * @return
     */
    List<ProgressCertificatePhotosVo> getProgressPageInfo(@Param("pageDto") CertificatePhotosPageDTO pageDto);

}