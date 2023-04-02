package com.ruoyi.czjg.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.czjg.contract.domain.dto.EquipmentEnterPageDTO;
import com.ruoyi.czjg.contract.domain.entity.EquipmentEnter;
import com.ruoyi.czjg.contract.domain.vo.EquipmentEnterPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备进场报验 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
@Mapper
@Repository
public interface EquipmentEnterMapper extends BaseDaoMapper<EquipmentEnter> {
    /**
     * 分页查询设备进场报验数据
     *
     * @param pageDto
     * @return
     */
    List<EquipmentEnterPageVo> getPageInfo(@Param("pageDto") EquipmentEnterPageDTO pageDto);
}
