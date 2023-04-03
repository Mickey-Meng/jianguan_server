package com.ruoyi.common.config.zjrw;//package com.ruoyi.common.config.zjrw;
//
//import com.getui.push.v2.sdk.ApiHelper;
//import com.getui.push.v2.sdk.GtApiConfiguration;
//import com.getui.push.v2.sdk.api.PushApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
///**
// * @version : 1.0
// * @Author : chenzhiwei
// * @date : Create file in 2022/3/17 18:15
// * @description :
// **/
//@Configuration
//public class RestAPIConfig {
//
//    @Bean
//    public void restApiInit() {
//        // 设置httpClient最大连接数，当并发较大时建议调大此参数。或者启动参数加上 -Dhttp.maxConnections=200
//        System.setProperty("http.maxConnections", "200");
//        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
//        //填写应用配置
//        apiConfiguration.setAppId("zlsk");
//        apiConfiguration.setAppKey("ZhuJiRoad");
//        apiConfiguration.setMasterSecret("zlsk_ZhuJiRoad");
//        // 接口调用前缀，请查看文档: 接口调用规范 -> 接口前缀, 可不填写appId
//        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
//        // 实例化ApiHelper对象，用于创建接口对象
//        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
//        // 创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
//        PushApi pushApi = apiHelper.creatApi(PushApi.class);
//    }
//}
