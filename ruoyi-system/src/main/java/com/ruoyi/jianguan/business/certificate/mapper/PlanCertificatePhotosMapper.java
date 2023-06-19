package com.ruoyi.jianguan.business.certificate.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.PlanCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 计划管理-证照管理Mapper接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface PlanCertificatePhotosMapper extends BaseDaoMapper<PlanCertificatePhotos> {

    /**
     * 分页查询施计划管理-证照管理表数据
     *
     * @param pageDto
     * @return
     */
    List<PlanCertificatePhotosVo> getPageInfo(@Param("pageDto") PlanCertificatePhotosPageDTO pageDto);

}