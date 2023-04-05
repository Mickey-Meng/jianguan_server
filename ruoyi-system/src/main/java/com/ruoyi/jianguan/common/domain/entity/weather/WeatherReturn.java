package com.ruoyi.jianguan.common.domain.entity.weather;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/22 17:16
 * @Version : 1.0
 * @Description : 第三方天气返回
 **/
public class WeatherReturn {

    private String status;    //值为0或1     1：成功；0：失败

    private String count;       //返回结果总数

    private String info;        //返回的状态信息

    private String infocode;        //返回状态说明,10000代表正确

    private List<WeatherNow> lives;       //实况天气数据信息

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<WeatherNow> getLives() {
        return lives;
    }

    public void setLives(List<WeatherNow> lives) {
        this.lives = lives;
    }
}
