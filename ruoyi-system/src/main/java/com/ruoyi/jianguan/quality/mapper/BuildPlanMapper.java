package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.BuildPlanPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.BuildPlan;
import com.ruoyi.jianguan.quality.domain.vo.BuildPlanPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 施工方案 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-25
 */
@Mapper
@Repository
public interface BuildPlanMapper extends BaseDaoMapper<BuildPlan> {
    /**
     * 分页查询
     *
     * @param pageDto
     * @return
     */
    List<BuildPlanPageVo> getPageInfo(@Param("pageDto") BuildPlanPageDTO pageDto);
}
