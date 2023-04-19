package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ManageRegime;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageRegimePageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理制度 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Mapper
@Repository
public interface ManageRegimeMapper extends BaseDaoMapper<ManageRegime> {
    /**
     * 分页查询管理制度数据
     *
     * @param pageDto
     * @return
     */
    List<ManageRegimePageVo> getPageInfo(@Param("pageDto") PageDTO pageDto);
}
