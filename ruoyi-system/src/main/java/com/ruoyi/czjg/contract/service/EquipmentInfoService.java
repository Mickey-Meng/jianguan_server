package com.ruoyi.czjg.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.czjg.contract.domain.dto.EquipmentInfoPageDTO;
import com.ruoyi.czjg.contract.domain.entity.EquipmentInfo;

import java.util.List;

/**
 * 设备信息 服务类
 *
 * @author qiaoxulin
 * @date 2022-08-13
 */
public interface EquipmentInfoService extends IService<EquipmentInfo> {
    /**
     * 通过进场设备id删除设备信息
     *
     * @param enterId
     * @return
     */
    boolean removeByEnterId(Long enterId);

    /**
     * 分页查询设备信息数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<EquipmentInfo> getPageInfo(EquipmentInfoPageDTO pageDto);

    /**
     * 设备信息
     *
     * @param id
     * @return
     */
    List<EquipmentInfo> getByEnterId(Long id);
}
