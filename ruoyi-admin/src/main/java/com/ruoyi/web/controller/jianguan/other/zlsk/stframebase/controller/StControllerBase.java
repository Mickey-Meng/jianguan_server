package com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.controller;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.NetUtil;

import javax.servlet.http.*;

public class StControllerBase
{
    public static HttpServletRequest getRequest() {
        return NetUtil.getRequest();
    }

    public static String getBasePath() {
        return NetUtil.getBasePath();
    }

    public static String getLocalHostIp() {
        try {
            return NetUtil.getLocalHostIp();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static String getRemoteIp() {
        try {
            return NetUtil.getRemoteIp();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static String getRealRemoteIp() {
        try {
            return NetUtil.getRealRemoteIp();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
