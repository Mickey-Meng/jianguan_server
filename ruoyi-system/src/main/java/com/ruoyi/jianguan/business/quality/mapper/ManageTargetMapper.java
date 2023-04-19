package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.quality.domain.entity.ManageTarget;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageTargetPageVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理目标 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Mapper
@Repository
public interface ManageTargetMapper extends BaseDaoMapper<ManageTarget> {
    /**
     * 分页查询管理目标数据
     *
     * @param pageDto
     * @return
     */
    List<ManageTargetPageVo> getPageInfo(@Param("pageDto") PageDTO pageDto);
}
