package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.FirstAcceptPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.FirstAccept;
import com.ruoyi.jianguan.quality.domain.vo.FirstAcceptPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首件认可 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Mapper
@Repository
public interface FirstAcceptMapper extends BaseDaoMapper<FirstAccept> {
    /**
     * 分页查询首件认可数据
     *
     * @param pageDto
     * @return
     */
    List<FirstAcceptPageVo> getPageInfo(@Param("pageDto") FirstAcceptPageDTO pageDto);

}
