package com.ruoyi.common.core.domain.dto;

import java.util.List;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/28 10:38
 * @description :
 **/
public class WebsocketMessageDTO {

    //推送的消息内容
    private List<?> data;
    //推送的事件
    private String event;
    //需要推送给其他用的id
    private List<Integer> userIds;
//    //当前在线人数统计
//    //监理
//    private Integer onlineJL;
//    //施工
//    private Integer onlineSG;
//    //业主
//    private Integer onlineYZ;
//    //除去监理\施工\业主   的其他角色用户
//    private Integer onlineOther;
//
//    public Integer getOnlineJL() {
//        return onlineJL;
//    }
//
//    public void setOnlineJL(Integer onlineJL) {
//        this.onlineJL = onlineJL;
//    }
//
//    public Integer getOnlineSG() {
//        return onlineSG;
//    }
//
//    public void setOnlineSG(Integer onlineSG) {
//        this.onlineSG = onlineSG;
//    }
//
//    public Integer getOnlineYZ() {
//        return onlineYZ;
//    }
//
//    public void setOnlineYZ(Integer onlineYZ) {
//        this.onlineYZ = onlineYZ;
//    }
//
//    public Integer getOnlineOther() {
//        return onlineOther;
//    }
//
//    public void setOnlineOther(Integer onlineOther) {
//        this.onlineOther = onlineOther;
//    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "WebsocketMessageDTO{" +
                "data=" + data +
                ", event='" + event + '\'' +
                ", userIds=" + userIds +
                '}';
    }

//    @Override
//    public String toString() {
//        return "WebsocketMessageDTO{" +
//                "data=" + data +
//                ", event='" + event + '\'' +
//                ", userIds=" + userIds +
//                ", onlineJL=" + onlineJL +
//                ", onlineSG=" + onlineSG +
//                ", onlineYZ=" + onlineYZ +
//                ", onlineOther=" + onlineOther +
//                '}';
//    }
}
