package com.ruoyi.common.utils.jianguan.zjrw;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/18 16:34
 * @Version : 1.0
 * @Description :
 **/
public class RestApiUtils {

    public static final String APP_ID = "ah9maLuvAc8tCMc0LpKJW9";
    public static final String APP_KEY = "1cbH9S0C8N7lwdxAwf4NrA";
    public static final String MASTER_SECRET = "6AtfZ7y5K3ATzMYi7gADr7";

    public static final String BASE_URL = "https://restapi.getui.com/v2/";

    //1.android在线可以，离线不行
    //2.ios在线离线都不行
    public static void main(String[] args){
        //a25d7b09ce163e3eca998e869f1f7773  4692c9005dada4f484132cf51c49e307
        sendMessageToCid("a25d7b09ce163e3eca998e869f1f7773","BIM","有工序消息待审核，请注意查收！");
    }

    public static String getToken() {
        String token = "";
        String url = BASE_URL + APP_ID + "/auth";
        Map<String,Object> params = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        String sign = getSHA256StrJava(APP_KEY + timestamp + MASTER_SECRET);
        params.put("appkey",APP_KEY);
        params.put("timestamp",timestamp);
        params.put("sign",sign);
        Map<String,Object> result = null;
        try {
            result = sendPost(url,params,null);
            if("success".equals(String.valueOf(result.getOrDefault("msg","")))){
                Map<String,Object> data = (Map<String, Object>) result.get("data");
                token = String.valueOf(data.get("token"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    public static void sendMessageToCid(String cid,String title,String content) {
        long timestamp = System.currentTimeMillis();
        String url = BASE_URL + APP_ID + "/push/single/cid";

        Map<String,Object> audience = new HashMap<>();
        audience.put("cid", Collections.singletonList(cid));

        Map<String,Object> notification = new HashMap<>();
        notification.put("title",title);
        notification.put("body",content);
        notification.put("click_type","startapp");

        Map<String,Object> ups = new HashMap<>();
        ups.put("notification",notification);
        Map<String,Object> android = new HashMap<>();
        android.put("ups",ups);

        Map<String,Object> alert = new HashMap<>();
        alert.put("title",title);
        alert.put("body",content);

        Map<String,Object> aps = new HashMap<>();
        aps.put("alert",alert);
        Map<String,Object> ios = new HashMap<>();
        ios.put("type","notify");
        ios.put("aps",aps);

        Map<String,Object> push_message = new HashMap<>();
        push_message.put("notification",notification);

        Map<String,Object> push_channel = new HashMap<>();
        push_channel.put("android",android);
        push_channel.put("ios",ios);

        Map<String,Object> params = new HashMap<>();
        params.put("request_id",timestamp + "");
        params.put("audience",audience);
        params.put("push_message",push_message);
        params.put("push_channel",push_channel);

        //测试
//        String a = JSON.toJSONString(params);
//        System.out.println(a);

        Map<String,Object> result = null;
        try {
            String token = getToken();
            Map<String,String> header = new HashMap<>();
            header.put("token",token);
            result = sendPost(url,params,header);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static Map<String,Object> sendPost(String url,Map<String,Object> map,Map<String,String> header) throws IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);

        RequestEntity entity = new StringRequestEntity(JSONObject.toJSONString(
                map, SerializerFeature.DisableCircularReferenceDetect),"application/json","UTF-8");
        postMethod.setRequestEntity(entity);

        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        if(header != null){
            for (String key : header.keySet()){
                postMethod.addRequestHeader(key, header.get(key));
            }
        }

        httpClient.executeMethod(postMethod);

        InputStream inputStream = postMethod.getResponseBodyAsStream();
        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        postMethod.releaseConnection();
        return JSONObject.parseObject(result);
    }
}
