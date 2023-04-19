package com.ruoyi.jianguan.business.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.jianguan.business.quality.domain.entity.ProviceCityArea;
import com.ruoyi.jianguan.business.quality.mapper.ProviceCityAreaMapper;
import com.ruoyi.jianguan.business.quality.service.ProviceCityAreaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 省市区 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-15
 */
@Service
public class ProviceCityAreaServiceImpl extends ServiceImpl<ProviceCityAreaMapper, ProviceCityArea> implements ProviceCityAreaService {

    /**
     * 查询省
     *
     * @return
     */
    @Override
    public List<ProviceCityArea> getProvince() {
        LambdaQueryWrapper<ProviceCityArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProviceCityArea::getPid, 0);
        return this.list(queryWrapper);
    }


    /**
     * 查询市s
     *
     * @param provinceId
     * @return
     */
    @Override
    public List<ProviceCityArea> getCity(Integer provinceId) {
        LambdaQueryWrapper<ProviceCityArea> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(provinceId)) {
            queryWrapper.eq(ProviceCityArea::getPid, provinceId);
        }
        return this.list(queryWrapper);
    }

    /**
     * 查询区
     *
     * @param cityId
     * @return
     */
    @Override
    public List<ProviceCityArea> getDistrict(Integer cityId) {
        LambdaQueryWrapper<ProviceCityArea> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(cityId)) {
            queryWrapper.eq(ProviceCityArea::getPid, cityId);
        }
        return this.list(queryWrapper);
    }
}
