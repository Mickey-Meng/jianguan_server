package com.ruoyi.common.config.zjrw;

import com.ruoyi.common.annotation.AuthIgnore;
import com.ruoyi.common.constant.Constant;
import com.ruoyi.common.exception.LoginException;
import com.ruoyi.common.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/6 11:33 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("有效");
        AuthIgnore annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        }else{
            return true;
        }

        //如果有@AuthIgnore注解，则不验证token
        if(annotation != null){
            return true;
        }
        //获取用户凭证
        String token = request.getHeader(Constant.USER_TOKEN);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(Constant.USER_TOKEN);
        }
        if(StringUtils.isBlank(token)){
            Object obj = request.getAttribute(Constant.USER_TOKEN);
            if(null!=obj){
                token=obj.toString();
            }
        }
        //token凭证为空
        if(StringUtils.isBlank(token)){
            throw new LoginException(Constant.USER_TOKEN + "不能为空"+HttpStatus.UNAUTHORIZED.value());
        }
        boolean verity = JwtUtil.verity(token);
        if(!verity){
            throw new LoginException("请重新登陆");
        }
        return true;
    }
}
