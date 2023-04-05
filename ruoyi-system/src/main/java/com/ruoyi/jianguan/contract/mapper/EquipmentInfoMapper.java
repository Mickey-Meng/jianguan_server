package com.ruoyi.jianguan.contract.mapper;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentInfoPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备信息 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-08-13
 */
@Mapper
@Repository
public interface EquipmentInfoMapper extends BaseDaoMapper<EquipmentInfo> {
    /**
     * 分页查询设备信息数据
     *
     * @param pageDto
     * @return
     */
    List<EquipmentInfo> getPageInfo(@Param("pageDto") EquipmentInfoPageDTO pageDto);

}
