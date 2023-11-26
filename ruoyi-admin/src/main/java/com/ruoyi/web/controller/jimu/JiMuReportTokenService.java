package com.ruoyi.web.controller.jimu;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.system.service.ISysUserService;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.redisson.misc.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dzh
 * @version 1.0
 * @date 2022/7/26 11:06
 */
@Component
public class JiMuReportTokenService implements JmReportTokenServiceI {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public String getUsername(String token) {
        return getUserByToken(token).getUserName();
    }



//    @Override
//    public String[] getRoles(String s) {
//        return new String[0];
//    }

    @Override
    public Boolean verifyToken(String token) {
        SysUser sysUser = getUserByToken(token);
        if(ObjectUtil.isNotNull(sysUser)){
            return true;
        }
        return false;
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String token = getTokenByRequest(request);
        token = token.replace("Bearer ", "");
        System.out.println(token);
        return token;
    }

    public static String getTokenByRequest(HttpServletRequest request) {
        String parameter = request.getParameter("token");
        String header = request.getHeader("token");
        if (parameter == null && header == null) {
            parameter = request.getHeader("Authorization");
        }
        return parameter != null ? parameter : header;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        SysUser sysUser = getUserByToken(token);
        String userStr = JSON.toJSONString(sysUser);
        HashMap<String, Object> hashMap = JSON.parseObject(userStr, HashMap.class);
        return hashMap;
    }

    private SysUser getUserByToken(String token) {
        Object loginId = StpUtil.getLoginIdByToken(token);
        if(ObjectUtil.isNull(loginId)) {
            return null;
        }
        String[] split = String.valueOf(loginId).split(":");
        return iSysUserService.selectUserById(Long.parseLong(split[1]));
    }
}