package com.ruoyi.jianguan.business.metrology.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.business.metrology.domain.dto.MetrologyPageDTO;
import com.ruoyi.jianguan.business.metrology.domain.vo.MetrologyPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 施工专业分包合同表 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-18
 */
@Mapper
@Repository
public interface MetrologyMapper extends BaseDaoMapper<Metrology> {
    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    List<MetrologyPageVo> getPageInfo(@Param("pageDto") MetrologyPageDTO pageDto);
}
