package com.ruoyi.jianguan.business.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.BuildContractPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractPageVo;
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
public interface BuildContractMapper extends BaseDaoMapper<BuildContract> {
    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    List<BuildContractPageVo> getPageInfo(@Param("pageDto") BuildContractPageDTO pageDto);
}
