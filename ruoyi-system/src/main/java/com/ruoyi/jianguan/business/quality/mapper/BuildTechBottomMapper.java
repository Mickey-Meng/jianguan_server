package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.jianguan.business.quality.domain.dto.BuildTechBottomPageDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildTechBottom;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 施工技术交底 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
@Mapper
@Repository
public interface BuildTechBottomMapper extends BaseDaoMapper<BuildTechBottom> {
    /**
     * 分页查询施工技术交底数据
     *
     * @param pageDto
     * @return
     */
    List<BuildTechBottomPageVo> getPageInfo(@Param("pageDto") BuildTechBottomPageDTO pageDto);
}
