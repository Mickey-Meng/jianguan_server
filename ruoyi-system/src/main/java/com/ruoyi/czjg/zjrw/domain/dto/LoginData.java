package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 2:49 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class LoginData {
    private Integer id;
    private String name;
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
