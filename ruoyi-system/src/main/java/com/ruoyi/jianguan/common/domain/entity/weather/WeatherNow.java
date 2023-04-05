package com.ruoyi.jianguan.common.domain.entity.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/22 17:16
 * @Version : 1.0
 * @Description :
 **/
public class WeatherNow {

    private Integer id;

    @ApiModelProperty(value = "省份名")
    private String province;

    @ApiModelProperty(value = "城市名")
    private String city;

    @ApiModelProperty(value = "区域编码")
    private String adcode;

    @ApiModelProperty(value = "天气现象（汉字描述）")
    private String weather;

    @ApiModelProperty(value = "实时气温，单位：摄氏度")
    private String temperature;

    @ApiModelProperty(value = "风向描述")
    private String winddirection;

    @ApiModelProperty(value = "风力级别，单位：级")
    private String windpower;

    @ApiModelProperty(value = "空气湿度")
    private String humidity;

    @ApiModelProperty(value = "数据发布的时间")
    private String reporttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer status;

    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "WeatherNow{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", adcode='" + adcode + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", winddirection='" + winddirection + '\'' +
                ", windpower='" + windpower + '\'' +
                ", humidity='" + humidity + '\'' +
                ", reporttime='" + reporttime + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", order=" + order +
                '}';
    }
}
