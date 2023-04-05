package com.ruoyi.jianguan.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EnterExit;
import com.ruoyi.jianguan.contract.domain.vo.EnterExitPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 进退场 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-28
 */
@Mapper
@Repository
public interface EnterExitMapper extends BaseDaoMapper<EnterExit> {
    /**
     * 分页查询进退场数据
     *
     * @param pageDto
     * @return
     */
    List<EnterExitPageVo> getPageInfo(@Param("pageDto") EnterExitPageDTO pageDto);
}
