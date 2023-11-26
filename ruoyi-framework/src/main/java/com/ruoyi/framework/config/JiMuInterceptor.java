package com.ruoyi.framework.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.helper.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dzh
 * @version 1.0
 * @date 2022/7/26 16:28
 */
@Component
public class JiMuInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceHandler userServiceHandler;

    private SysUser getUserByToken(String token) {
        Object loginId = StpUtil.getLoginIdByToken(token);
        if(ObjectUtil.isNull(loginId)) {
            return null;
        }
        String[] split = String.valueOf(loginId).split(":");
        return userServiceHandler.selectUserById(Long.parseLong(split[1]));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String token = request.getParameter("token");

        token = token.replace("Bearer ", "");
        SysUser sysUser = getUserByToken(token);

        if (sysUser != null) {
            return true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "参数错误或无权访问数据");
        response.getWriter().println(jsonObject);
        return false;
    }
}