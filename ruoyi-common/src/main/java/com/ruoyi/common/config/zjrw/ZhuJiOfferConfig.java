package com.ruoyi.common.config.zjrw;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/22 12:08 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Configuration
@ConfigurationProperties(prefix = "zhuji")
@Data
public class ZhuJiOfferConfig {
    private String loginurl;
    private String ycApiurl;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String grantType;
    private String projectid;
    private String appKey;
    private String secret;
    private String ycApiNewUrl;
}
