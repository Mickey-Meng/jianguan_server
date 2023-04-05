package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.QualityReportPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.QualityReport;
import com.ruoyi.jianguan.quality.domain.vo.QualityReportPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质量简报 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-02
 */
@Mapper
@Repository
public interface QualityReportMapper extends BaseDaoMapper<QualityReport> {
    /**
     * 分页查询质量简报数据
     *
     * @param pageDto
     * @return
     */
    List<QualityReportPageVo> getPageInfo(@Param("pageDto") QualityReportPageDTO pageDto);
}
