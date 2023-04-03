package com.ruoyi.czjg.zjrw.domain.entity.weather;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/22 17:17
 * @Version : 1.0
 * @Description :
 **/
public class WeatherRefer {

    @ApiModelProperty(value = "原始数据来源，或数据源说明，可能为空")
    private String[] sources;

    @ApiModelProperty(value = "数据许可或版权声明，可能为空")
    private String[] license;

    public String[] getSources() {
        return sources;
    }

    public void setSources(String[] sources) {
        this.sources = sources;
    }

    public String[] getLicense() {
        return license;
    }

    public void setLicense(String[] license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "WeatherRefer{" +
                "sources=" + Arrays.toString(sources) +
                ", license=" + Arrays.toString(license) +
                '}';
    }
}
