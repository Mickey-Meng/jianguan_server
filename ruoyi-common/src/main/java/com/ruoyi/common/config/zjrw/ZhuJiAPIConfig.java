//package com.ruoyi.common.config.zjrw;
//
//import com.ruoyi.common.utils.jianguan.zjrw.httputils.HttpHeader;
//import com.ruoyi.common.utils.jianguan.zjrw.httputils.HttpParamers;
//import com.ruoyi.common.utils.jianguan.zjrw.httputils.HttpService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * @Author : Chen_ZhiWei
// * @Date : Create file in 2022/5/26 10:21
// * @Version : 1.0
// * @Description :
// **/
//@Configuration
//public class ZhuJiAPIConfig {
//
//    static Logger log = LoggerFactory.getLogger(ZhuJiAPIConfig.class);
//
//    @Value("${zhujiapi.host}")
//    private static String host;
//
//    @PostConstruct
//    public void getHostInit() throws IOException {
//        //读取配置文件, 注入静态变量
//        InputStream inputStream = ZhuJiAPIConfig.class.getResourceAsStream("/application-prod.yml");
//        Properties properties = new Properties();
//        properties.load(inputStream);
//        this.host = properties.getProperty("zhujiapi.host");
//    }
//
//    private static String doService(String url, HttpParamers param, HttpHeader header){
//        String response = "";
//        try {
//            HttpService httpService = new HttpService(host);
//            response = httpService.service(url, param, header);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    /**
//     * 查询我的待办列表
//     * @param param
//     * @param header
//     * @return
//     */
//    public static String getListRuntimeTask(HttpParamers param, HttpHeader header){
//        String url = "/ZhuJiApi/admin/flow/flowOperation/listRuntimeTask";
//        String response = doService(url, param, header);
//        return response;
//    }
//
//    public static String createProcess(String processKey, HttpParamers param, HttpHeader header){
//        String url = "/ZhuJiApi/admin/flow/flowStaticPage/startProcessFlow/" + processKey;
//        String response = doService(url, param, header);
//        return response;
//    }
//
//    /**
//     * 通用接口
//     * @param url 调用地址
//     * @param param 请求体
//     * @param header 请求头
//     * @return
//     */
//    public static String getListHistoricTask(String url, HttpParamers param, HttpHeader header){
//        String response = doService(url, param, header);
//        return response;
//    }
//}
