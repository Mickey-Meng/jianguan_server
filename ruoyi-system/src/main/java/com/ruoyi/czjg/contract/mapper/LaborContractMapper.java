package com.ruoyi.czjg.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.czjg.contract.domain.dto.LaborContractPageDTO;
import com.ruoyi.czjg.contract.domain.entity.LaborContract;
import com.ruoyi.czjg.contract.domain.vo.LaborContractPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 劳务分包合同 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-20
 */
@Mapper
@Repository
public interface LaborContractMapper extends BaseDaoMapper<LaborContract> {
    /**
     * 分页查询劳务分包合同数据
     *
     * @param pageDto
     * @return
     */
    List<LaborContractPageVo> getPageInfo(@Param("pageDto") LaborContractPageDTO pageDto);
}
