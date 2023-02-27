package com.ruoyi.web.controller.ql;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.web.controller.wx.UrlConstant;
import com.ruoyi.web.controller.wx.WechatSendMsgVo;
import com.ruoyi.web.controller.wx.WechatTemplateVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WXController
 * @Description TODO
 * @Author Karl
 * @Date 2023/2/27 10:47
 * @Version 1.0
 */
@Controller
public class WXController {

    @RequestMapping("test")
    public  void main(String[] args) {

        String appId = "wx2314d05ce9b3bfb1";
        String appSecret = "5bc7e208afce53cc48bb091309beadfe";
        String requestUrl = UrlConstant.ACCESS_TOKEN_URL + "appid=" + appId + "&secret=" + appSecret;
        String resp = HttpUtil.get(requestUrl);
        System.out.println("qweqwe"+resp);
        JSONObject result = JSONUtil.parseObj(resp);
        String token = result.getStr("access_token");

        List<String> userList = getUserList(token);
        for (String openid : userList) {

            Map<String, WechatTemplateVo> map = new HashMap<>();
            //获取早安语句

            map.put("test", new WechatTemplateVo("qwe","#ff6666"));
            WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
            //设置模板id
            sendMsgVo.setTemplate_id("模板id");
            //设置接收用户
            sendMsgVo.setTouser(openid);
            sendMsgVo.setData(map);
            sendMsg(sendMsgVo, token, appId);
        }

    }


    public JSONObject sendMsg(WechatSendMsgVo sendMsgVo, String token, String openId) {
        //请求路径
        String requestUrl = UrlConstant.SEND_TEMPLATE  + token;
        //参数转json
        String json = JSONUtil.parseObj(sendMsgVo).toString();
        String resp = HttpUtil.createPost(requestUrl).body(json).execute().body();
        JSONObject result = JSONUtil.parseObj(resp);
        System.out.println(result);
        return result;
    }
    public List<String> getUserList(String accessToken) {
        String requestUrl =  UrlConstant.GET_USER_LIST+ accessToken;
        String resp = HttpUtil.get(requestUrl);
        JSONObject result = JSONUtil.parseObj(resp);
        System.out.println(resp);
        System.out.println(result);
        JSONArray openIdJsonArray = result.getJSONObject("data").getJSONArray("openid");
        List<String> openIds = JSONUtil.toList(openIdJsonArray, String.class);
        return openIds;
    }

}
