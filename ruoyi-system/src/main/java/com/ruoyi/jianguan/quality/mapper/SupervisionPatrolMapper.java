package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.SupervisionPatrolPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionPatrol;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionPatrolPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 监理巡视 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-10
 */
@Mapper
@Repository
public interface SupervisionPatrolMapper extends BaseDaoMapper<SupervisionPatrol> {
    /**
     * 分页查询监理巡视数据
     *
     * @param pageDto
     * @return
     */
    List<SupervisionPatrolPageVo> getPageInfo(@Param("pageDto") SupervisionPatrolPageDTO pageDto);
}
