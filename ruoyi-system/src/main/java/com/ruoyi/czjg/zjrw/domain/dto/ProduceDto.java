package com.ruoyi.czjg.zjrw.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/1 4:37 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ProduceDto {
    private List<String>  name;
    private Map<String,List>  data;
    private Map<String,Float> sname;
    private List<Map<Float,String>> sdata;

    public Map<String, Float> getSname() {
        return sname;
    }

    public void setSname(Map<String, Float> sname) {
        this.sname = sname;
    }

    public List<Map<Float,String>> getSdata() {
        return sdata;
    }

    public void setSdata(List<Map<Float,String>> sdata) {
        this.sdata = sdata;
    }

    public List<String> getName() {
        return name;
    }

    public Map<String, List> getData() {
        return data;
    }

    public void setData(Map<String, List> data) {
        this.data = data;
    }


    public void setName(List<String> name) {
        this.name = name;
    }


}
