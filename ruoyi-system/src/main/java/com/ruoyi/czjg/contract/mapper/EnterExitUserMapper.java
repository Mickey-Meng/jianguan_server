package com.ruoyi.czjg.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.czjg.contract.domain.dto.EnterExitUserPageDTO;
import com.ruoyi.czjg.contract.domain.entity.EnterExitUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 进退场人员 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-28
 */
@Mapper
@Repository
public interface EnterExitUserMapper extends BaseDaoMapper<EnterExitUser> {
    /**
     * 分页查询人员一览
     *
     * @param pageDto
     * @return
     */
    List<EnterExitUser> getPageInfo(@Param("pageDto") EnterExitUserPageDTO pageDto);
}
