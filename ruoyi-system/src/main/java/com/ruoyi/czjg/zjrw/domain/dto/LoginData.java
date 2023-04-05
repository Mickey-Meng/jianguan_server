package com.ruoyi.czjg.zjrw.domain.dto;

import lombok.Data;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 2:49 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Data
public class LoginData {
    private Integer id;
    private String name;
    private String token;

    private String authToken;

}
