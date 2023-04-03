package com.ruoyi.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:17
 * @description :
 **/
public class WebSocketEvent extends ApplicationEvent {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public WebSocketEvent(Object source,String userId) {
        super(source);
        this.userId=userId;
    }

    public void printMsg(String msg){
        System.out.println("监听到事件：");
    }
}
