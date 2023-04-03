package com.ruoyi.common.utils.zjbim.zjrw;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/22 10:36 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author :CX
 * @Date :Create in 2018/8/17 10:02
 * @Effect :
 */
@Component
public class HttpsUtils {


    @Autowired
    private CloseableHttpClient closeableHttpClient;

    public  String httpsGet(String s, String url) throws Exception{
        HttpGet hg = new HttpGet(url);
        hg.addHeader("Authorization", "Bearer " + s);
        CloseableHttpResponse response = closeableHttpClient.execute(hg);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        return content;
    }

    //https post请求
    public String httpsPost(String url, String json) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setEntity(new StringEntity(json));

        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        closeableHttpClient.close();
        return content;
    }



    public  CloseableHttpClient createSSLClientDefault() throws Exception{

        return this.closeableHttpClient;

    }


    public String httpsPostZhuJi(String url, Map<String, String> mapdata) {
        CloseableHttpResponse response = null;

        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        try {
            // 设置提交方式
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            // 添加参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (mapdata.size() != 0) {
                // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
                Set keySet = mapdata.keySet();
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String k =  it.next().toString();// key
                    String v = mapdata.get(k);// value
                    nameValuePairs.add(new BasicNameValuePair(k, v));
                }
            }
            httpPost.setEntity( new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            // 执行http请求
            response = closeableHttpClient.execute(httpPost);
            // 获得http响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 响应的结果
                String content = EntityUtils.toString(entity, "UTF-8");
                return content;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取数据错误";
    }

    public String httpsPostData(String url, String a, String token) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization", "Bearer " + token);
        httpPost.setEntity(new StringEntity(a));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");

        return content;
    }
}
