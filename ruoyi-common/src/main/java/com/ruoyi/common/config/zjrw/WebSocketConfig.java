package com.ruoyi.common.config.zjrw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:57
 * @description :
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig {

//    @Autowired
//    private MyHandler myHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//        //规定连接带有两个连接参数 name暂时只有root keeper
//        webSocketHandlerRegistry.addHandler (myHandler,"/websocket/{name}/{id}").setAllowedOrigins("*");
//    }

    /**
     * ServerEndpointExporter 作用
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}
