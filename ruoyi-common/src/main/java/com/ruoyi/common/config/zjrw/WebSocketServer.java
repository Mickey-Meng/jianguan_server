package com.ruoyi.common.config.zjrw;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.dto.WebsocketMessageDTO;
import com.ruoyi.common.event.WebSocketEvent;
import com.ruoyi.common.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:09
 * @description :
 **/
@ServerEndpoint(value = "/imserver/{token}")
@Component
public class WebSocketServer implements ApplicationContextAware {

    public static ConcurrentHashMap<String, String> informMap=
            new ConcurrentHashMap<String, String>();
    private static ApplicationContext applicationContext;

    static Logger log= LoggerFactory.getLogger(WebSocketServer.class);

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    //监理在线数
    private static int onlineJLCount = 0;
    //施工在线数
    private static int onlineSGCount = 0;
    //业主在线数
    private static int onlineYZCount = 0;
    //其他在线
    private static int onlineOtherCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap =
            new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WebSocketServer{" +
                "userId='" + userId + '\'' +
                '}';
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token){
        String accountId = JwtUtil.getTokenUser(token).getId().toString();
        this.session = session;
        this.userId = accountId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            //加入set中
            webSocketMap.put(userId,this);
            //在线数加1
//            addOnlineCount();
            // todo 区分角色
            Integer roleId = JwtUtil.getTokenUser(token).getRole();
            if (roleId == 4 || roleId == 6){
                addOnlineJLCount();
            } else if (roleId == 5 || roleId == 7){
                addOnlineSGCount();
            } else if (roleId == 16 || roleId == 17 || roleId == 18){
                addOnlineYZCount();
            } else {
                addOnlineOtherCount();
            }
        }
        log.info("当前登录用户: "+ userId + ", 当前总在线人数为:" + getOnlineCount());

        applicationContext.publishEvent(new WebSocketEvent(this, userId));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("token") String token) {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
//            subOnlineCount();
            Integer roleId = JwtUtil.getTokenUser(token).getRole();
            if (roleId == 4 || roleId == 6){
                subOnlineJLCount();
            } else if (roleId == 5 || roleId == 7){
                subOnlineSGCount();
            } else if (roleId == 16 || roleId == 17 || roleId == 18){
                subOnlineYZCount();
            } else {
                subOnlineOtherCount();
            }
        }
        log.info("用户退出:" + userId + ", 当前总在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            //解析发送的报文
            WebsocketMessageDTO messageDTO = JSONObject.parseObject(message, WebsocketMessageDTO.class);
            //追加发送人(防止串改)
            //传送给对应UserId用户的websocket
            if (webSocketMap.size() == 0 || webSocketMap == null){
                log.error("没有用户在线");
            } else {
                List<Integer> userIds = messageDTO.getUserIds();
                if (userIds.size() == 0 || userIds == null){
                    log.error("没有用户在线，不需要发送消息提醒！");
                }
                for (Integer userId : userIds) {
                    for(WebSocketServer value : webSocketMap.values()){
                        if (value.getUserId().equals(userId.toString())){
                            try {
                                webSocketMap.get(value.getUserId()).sendMessage(JSONObject.toJSONString(messageDTO));
                            } catch (IOException e) {
                                e.printStackTrace();
                                log.error("推送消息失败!");
                            }
                        }
                    }
                }
                log.info("推送的消息: " + JSONObject.toJSONString(messageDTO));
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:"+message);
        if(StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }


    //获取总在线数
    public static synchronized int getOnlineCount(){
        return onlineJLCount + onlineSGCount + onlineYZCount + onlineOtherCount;
    }
    //获取监理在线数
    public static synchronized int getOnlineJLCount(){
        return onlineJLCount;
    }
    //获取施工在线数
    public static synchronized int getOnlineSGCount(){
        return onlineSGCount;
    }
    //获取业主在线数
    public static synchronized int getOnlineYZCount(){
        return onlineYZCount;
    }
    //获取其他在线数
    public static synchronized int getOnlineOtherCount(){
        return onlineOtherCount;
    }
    //监理在线
    public static synchronized void addOnlineJLCount() {
        WebSocketServer.onlineJLCount++;
    }
    //施工在线
    public static synchronized void addOnlineSGCount() {
        WebSocketServer.onlineSGCount++;
    }
    //业主在线
    public static synchronized void addOnlineYZCount() {
        WebSocketServer.onlineYZCount++;
    }
    //其他角色在线
    public static synchronized void addOnlineOtherCount() {
        WebSocketServer.onlineOtherCount++;
    }
    //监理不在线
    public static synchronized void subOnlineJLCount() {
        WebSocketServer.onlineJLCount--;
    }
    //施工不在线
    public static synchronized void subOnlineSGCount() {
        WebSocketServer.onlineSGCount--;
    }
    //业主不在线
    public static synchronized void subOnlineYZCount() {
        WebSocketServer.onlineYZCount--;
    }
    //其他角色不在线
    public static synchronized void subOnlineOtherCount() {
        WebSocketServer.onlineOtherCount--;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
