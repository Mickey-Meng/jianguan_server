package com.ruoyi.jianguan.common.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/24 10:50 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class CountData {
    //总数
    private Integer count;
    //完成
    private Integer finish;
    //详细
    private Map<String, List<ConponentCountData>> mapData;


    public CountData(Integer count, Integer finish, Map<String, List<ConponentCountData>> mapData) {
        this.count = count;
        this.finish = finish;
        this.mapData = mapData;
    }

    public CountData() {

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Map<String, List<ConponentCountData>> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, List<ConponentCountData>> mapData) {
        this.mapData = mapData;
    }
}
