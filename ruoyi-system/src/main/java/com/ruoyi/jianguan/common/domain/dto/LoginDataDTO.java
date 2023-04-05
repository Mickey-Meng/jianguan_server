package com.ruoyi.jianguan.common.domain.dto;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/3 14:06
 * @description :
 **/
public class LoginDataDTO {

    private LoginData loginData;
    private int groupid; //用户的role权限;

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "LoginDataDTO{" +
                "loginData=" + loginData +
                ", groupid=" + groupid +
                '}';
    }
}
