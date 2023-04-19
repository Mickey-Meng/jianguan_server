package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.entity.ProviceCityArea;

import java.util.List;

/**
 * 省市区 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-15
 */
public interface ProviceCityAreaService extends IService<ProviceCityArea> {

    /**
     * 查询省
     *
     * @return
     */
    List<ProviceCityArea> getProvince();

    /**
     * 查询市
     *
     * @param provinceId
     * @return
     */
    List<ProviceCityArea> getCity(Integer provinceId);

    /**
     * 查询区
     *
     * @param cityId
     * @return
     */
    List<ProviceCityArea> getDistrict(Integer cityId);
}
