package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.SupervisionOrderPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionOrderPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 监理指令 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-14
 */
@Mapper
@Repository
public interface SupervisionOrderMapper extends BaseDaoMapper<SupervisionOrder> {
    /**
     * 分页查询监理指令数据
     *
     * @param pageDto
     * @return
     */
    List<SupervisionOrderPageVo> getPageInfo(@Param("pageDto") SupervisionOrderPageDTO pageDto);
}
