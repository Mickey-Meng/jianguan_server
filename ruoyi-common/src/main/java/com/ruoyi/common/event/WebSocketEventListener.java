package com.ruoyi.common.event;

import com.ruoyi.common.config.zjrw.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:19
 * @description :
 **/
@Component
public class WebSocketEventListener implements ApplicationListener<WebSocketEvent> {


    @Autowired
    WebSocketServer webSocketServer;

    static Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Override
    public void onApplicationEvent(WebSocketEvent webSocketEvent) {
        String userId = webSocketEvent.getUserId();
        if(WebSocketServer.informMap.contains(userId)){
            webSocketServer.onMessage(WebSocketServer.informMap.get(userId),null);
            webSocketServer.informMap.remove(userId);
            log.info("补录通知");
        }else{
            log.info("不需要通知");
        }

    }
}
