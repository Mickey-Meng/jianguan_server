package com.ruoyi.jianguan.business.constructionDesign.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.ProgressConstructionDesignVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 施工图管理Mapper接口
 *
 * @author mickey
 * @date 2023-06-25
 */
public interface ConstructionDesignMapper extends BaseDaoMapper<ConstructionDesign> {

    /**
     * 分页查询施计划报审-施工图管理表数据
     *
     * @param pageDto
     * @return
     */
    List<PlanConstructionDesignVo> getPlanPageInfo(@Param("pageDto") ConstructionDesignPageDTO pageDto);

    /**
     * 分页查询施进度管理-施工图管理表数据
     *
     * @param pageDto
     * @return
     */
    List<ProgressConstructionDesignVo> getProgressPageInfo(@Param("pageDto") ConstructionDesignPageDTO pageDto);

}