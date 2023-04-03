package com.ruoyi.czjg.zjrw.domain.dto;

import java.io.Serializable;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/25 16:23
 * @Version : 1.0
 * @Description :
 **/
public class BridgeData implements Serializable {

    private String groupName;
    private String bridgeName;
    private String code;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BridgeData{" +
                "groupName='" + groupName + '\'' +
                ", bridgeName='" + bridgeName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
