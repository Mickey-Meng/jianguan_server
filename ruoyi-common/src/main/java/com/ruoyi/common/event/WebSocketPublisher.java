package com.ruoyi.common.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:22
 * @description :
 **/
@Component
public class WebSocketPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publish(String userId) {
        applicationContext.publishEvent(new WebSocketEvent(this, userId ));
    }
}
