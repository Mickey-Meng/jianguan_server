package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionSidePageDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionSide;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionSidePageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 监理旁站 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-10
 */
@Mapper
@Repository
public interface SupervisionSideMapper extends BaseDaoMapper<SupervisionSide> {
    /**
     * 分页查询监理旁站数据
     *
     * @param pageDto
     * @return
     */
    List<SupervisionSidePageVo> getPageInfo(@Param("pageDto") SupervisionSidePageDTO pageDto);
}
