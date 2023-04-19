package com.ruoyi.jianguan.business.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.ComeGoMoneyPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ComeGoMoney;
import com.ruoyi.jianguan.business.contract.domain.vo.ComeGoMoneyPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 往来款 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Mapper
@Repository
public interface ComeGoMoneyMapper extends BaseDaoMapper<ComeGoMoney> {
    /**
     * 分页查询往来款数据
     *
     * @param pageDto
     * @return
     */
    List<ComeGoMoneyPageVo> getPageInfo(@Param("pageDto") ComeGoMoneyPageDTO pageDto);
}
