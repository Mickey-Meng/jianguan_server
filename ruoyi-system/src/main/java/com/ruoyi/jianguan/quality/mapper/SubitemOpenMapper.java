package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.SubitemOpenPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.SubitemOpen;
import com.ruoyi.jianguan.quality.domain.vo.SubitemOpenPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分项开工申请 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Mapper
@Repository
public interface SubitemOpenMapper extends BaseDaoMapper<SubitemOpen> {
    /**
     * 分页查询分项开工申请数据
     *
     * @param pageDto
     * @return
     */
    List<SubitemOpenPageVo> getPageInfo(@Param("pageDto") SubitemOpenPageDTO pageDto);
}
