package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.QualityDetectionPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.QualityDetection;
import com.ruoyi.jianguan.quality.domain.vo.QualityDetectionPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质量检测 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-11
 */
@Mapper
@Repository
public interface QualityDetectionMapper extends BaseDaoMapper<QualityDetection> {
    /**
     * 质量检测分页查询
     *
     * @param pageDto
     * @return
     */
    List<QualityDetectionPageVo> getPageInfo(@Param("pageDto") QualityDetectionPageDTO pageDto);
}
