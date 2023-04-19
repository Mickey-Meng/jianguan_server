package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivityPageDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityActivity;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质量活动 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Mapper
@Repository
public interface QualityActivityMapper extends BaseDaoMapper<QualityActivity> {
    /**
     * 分页查询质量活动数据
     *
     * @param pageDto
     * @return
     */
    List<QualityActivityPageVo> getPageInfo(@Param("pageDto") QualityActivityPageDTO pageDto);
}
