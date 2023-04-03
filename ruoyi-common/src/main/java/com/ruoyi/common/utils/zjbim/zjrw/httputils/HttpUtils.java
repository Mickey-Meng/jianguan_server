package com.ruoyi.common.utils.zjbim.zjrw.httputils;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/13 09:51
 * @Version : 1.0
 * @Description :
 **/
public class HttpUtils {

    /**
     * 发送post请求
     * @param inurl
     * @param params
     * @return
     * @throws IOException
     */
    public static String readByPost(String inurl,String params) throws IOException {
        StringBuffer sbf = new StringBuffer();
        String strRead = null;
        URL url = new URL(inurl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.print(params);
        out.flush();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        while((strRead = reader.readLine())!=null){
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        connection.disconnect();
        return sbf.toString();
    }

    /**
     * 向指定 URL发送 POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常:" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public String sendPost(String url, Map<String, String> params) {
        try {
            String body = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postMethod = new HttpPost(url);
            List<NameValuePair> nvps = Lists.newArrayList();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            HttpEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
            postMethod.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(postMethod);
            HttpEntity httpEntity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(httpEntity, "UTF-8");
            }
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendGet(String url, Map<String, Object> params, String token) {
        try {
            String body = null;
            List<NameValuePair> nvps = Lists.newArrayList();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
                }
            }
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setParameters(nvps);
            HttpGet getMethod = new HttpGet(uriBuilder.build());
            getMethod.setHeader("token", token);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(getMethod);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                body = EntityUtils.toString(httpEntity, "UTF-8");
            }
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendGet(String url, Map<String, Object> params) {
        try {
            String body = null;
            List<NameValuePair> nvps = Lists.newArrayList();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
                }
            }
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setParameters(nvps);
            HttpGet getMethod = new HttpGet(uriBuilder.build());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(getMethod);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                body = EntityUtils.toString(httpEntity, "UTF-8");
            }
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
