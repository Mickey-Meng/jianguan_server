package com.ruoyi.czjg.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.czjg.contract.domain.dto.EquipmentInfoPageDTO;
import com.ruoyi.czjg.contract.domain.entity.EquipmentInfo;
import com.ruoyi.czjg.contract.mapper.EquipmentInfoMapper;
import com.ruoyi.czjg.contract.service.EquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备信息 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-08-13
 */
@Service
public class EquipmentInfoServiceImpl extends ServiceImpl<EquipmentInfoMapper, EquipmentInfo> implements EquipmentInfoService {
    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;

    @Override
    public boolean removeByEnterId(Long enterId) {
        LambdaQueryWrapper<EquipmentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EquipmentInfo::getEnterId, enterId);
        return this.remove(queryWrapper);
    }

    /**
     * 分页查询设备信息数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<EquipmentInfo> getPageInfo(EquipmentInfoPageDTO pageDto) {
        //分页查询设备信息数据
        PageHelper.startPage(pageDto.getPageNum(),pageDto.getPageSize());
        List<EquipmentInfo> equipmentInfos = equipmentInfoMapper.getPageInfo(pageDto);
        return new PageInfo<>(equipmentInfos);
    }

    /**
     * 设备信息根据进场记录查询
     *
     * @param id
     * @return
     */
    @Override
    public List<EquipmentInfo> getByEnterId(Long id) {
        LambdaQueryWrapper<EquipmentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EquipmentInfo::getEnterId, id);
        queryWrapper.gt(EquipmentInfo::getNum,0);
        return this.list(queryWrapper);
    }
}
