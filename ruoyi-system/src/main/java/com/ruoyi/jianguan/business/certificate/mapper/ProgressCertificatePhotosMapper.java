package com.ruoyi.jianguan.business.certificate.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.ProgressCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 进度管理-证照管理Mapper接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface ProgressCertificatePhotosMapper extends BaseDaoMapper<ProgressCertificatePhotos> {

    /**
     * 分页查询施进度管理-证照管理表数据
     *
     * @param pageDto
     * @return
     */
    List<ProgressCertificatePhotosVo> getPageInfo(@Param("pageDto") ProgressCertificatePhotosPageDTO pageDto);

}