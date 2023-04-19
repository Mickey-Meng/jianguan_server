package com.ruoyi.jianguan.business.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.EquipmentExitPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentExit;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentExitPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退场设备报验单 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Mapper
@Repository
public interface EquipmentExitMapper extends BaseDaoMapper<EquipmentExit> {
    /**
     * 分页查询退场设备报验单数据
     *
     * @param pageDto
     * @return
     */
    List<EquipmentExitPageVo> getPageInfo(@Param("pageDto") EquipmentExitPageDTO pageDto);
}
