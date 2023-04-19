package com.ruoyi.jianguan.business.quality.mapper;

import com.ruoyi.jianguan.business.quality.domain.dto.ProjectOpenPageDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ProjectOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目开工申请 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Mapper
@Repository
public interface ProjectOpenMapper extends BaseDaoMapper<ProjectOpen> {
    /**
     * 分页查询项目开工申请数据
     *
     * @param pageDto
     * @return
     */
    List<ProjectOpenPageVo> getPageInfo(@Param("pageDto") ProjectOpenPageDTO pageDto);
}
