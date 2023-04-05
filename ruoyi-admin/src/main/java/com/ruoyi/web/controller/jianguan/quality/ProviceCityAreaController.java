package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.jianguan.quality.domain.entity.ProviceCityArea;
import com.ruoyi.jianguan.quality.service.ProviceCityAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 省市区
 *
 * @author qiaoxulin
 * @date 2022-05-15
 */
@Api(value = "省市区", tags = {"省市区"})
@RestController
@RequestMapping("/web/api/v1/proviceCityArea")
public class ProviceCityAreaController {


    @Autowired
    private ProviceCityAreaService proviceCityAreaService;


    /**
     * 查询省
     *
     * @param
     */
    @GetMapping(value = "/getProvince", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询省")
    public List<ProviceCityArea> getProvince() {
        return proviceCityAreaService.getProvince();
    }

    /**
     * 查询市
     *
     * @param
     */
    @GetMapping(value = "/getCity", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询市")
    public List<ProviceCityArea> getCity(Integer provinceId) {
        return proviceCityAreaService.getCity(provinceId);
    }


    /**
     * 查询区
     *
     * @param
     */
    @GetMapping(value = "/getDistrict", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询区")
    public List<ProviceCityArea> getDistrict(Integer cityId) {
        return proviceCityAreaService.getDistrict(cityId);
    }

}
