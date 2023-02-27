package com.ruoyi.web.controller.wx;

/**
 * URL常量
 */
public class UrlConstant {

    //获取accessToken
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";

    //微信推送模板消息
    public final static String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //获取关注用户信息
    public final static String GET_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";


}
