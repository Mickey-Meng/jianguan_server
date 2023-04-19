package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import org.springframework.web.context.request.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class NetUtil extends StSupporterBase
{
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static final String getBasePath() {
        final HttpServletRequest request = getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    }

    public static final String getLocalHost() {
        try {
            return InetAddress.getLocalHost().toString();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static final String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static String getLocalHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static final String getRemoteIp() {
        return getRequest().getRemoteAddr();
    }

    public static String getRealRemoteIp() {
        final HttpServletRequest request;
        final String header;
        String s;
        if ((header = (request = getRequest()).getHeader("X-Forwarded-For")) == null || header.length() == 0) {
            s = request.getHeader("Proxy-Client-IP");
        }
        else if ("unknown".equalsIgnoreCase(header)) {
            s = request.getHeader("Proxy-Client-IP");
        }
        else {
            final String[] split = (s = request.getHeader("Proxy-Client-IP")).split(",");
            for (int i = 0; i < split.length; ++i) {
                if (!split[i].equalsIgnoreCase("unknown")) {
                    s = split[i];
                    break;
                }
            }
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getRemoteAddr();
        }
        return s;
    }

    public static List<String> getMacList() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            final StringBuilder sb = new StringBuilder(30);
            final ArrayList<String> list = new ArrayList<String>();
            while (networkInterfaces.hasMoreElements()) {
                final Iterator<InterfaceAddress> iterator = networkInterfaces.nextElement().getInterfaceAddresses().iterator();
                while (iterator.hasNext()) {
                    final NetworkInterface byInetAddress;
                    final byte[] hardwareAddress;
                    if ((byInetAddress = NetworkInterface.getByInetAddress(iterator.next().getAddress())) != null && (hardwareAddress = byInetAddress.getHardwareAddress()) != null) {
                        sb.delete(0, sb.length());
                        for (int i = 0; i < hardwareAddress.length; ++i) {
                            sb.append(String.format("%02X", hardwareAddress[i]));
                        }
                        final String string = sb.toString();
                        if (list.contains(string)) {
                            continue;
                        }
                        list.add(string);
                    }
                }
            }
            return list;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getUrl(String s) {
        final StringBuilder sb = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        try {
            (httpURLConnection = (HttpURLConnection)new URL(s).openConnection()).setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(18000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                final InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
                bufferedReader.close();
                inputStreamReader.close();
                s = sb.toString();
            }
            else {
                s = "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25," + httpURLConnection.getResponseCode() + "\"}";
            }
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            ex2.printStackTrace();
            return "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25, " + ex.getMessage() + "\"}";
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return "";
    }

    public static String getUrl1(String line) {
        try {
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)new URL(line).openConnection()).setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() != 200) {
                return "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25," + httpURLConnection.getResponseCode() + "\"}";
            }
            return line = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        }
        catch (Exception ex) {
            return "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25, " + ex.getMessage() + "\"}";
        }
    }
}
