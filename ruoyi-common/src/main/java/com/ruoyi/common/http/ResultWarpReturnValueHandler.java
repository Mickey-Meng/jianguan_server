package com.ruoyi.common.http;

import com.ruoyi.common.core.domain.object.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回值控制Handler
 *
 * @author jiangchengtao
 * @date 2022/03/24
 */
@Slf4j
public class ResultWarpReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public ResultWarpReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String servletPath = nativeRequest.getServletPath();
        // 包含"/web/api/"进行接口返回值封装
        if (servletPath.contains("/web/api/")) {
            if (returnValue instanceof ResponseBase) {
                delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            } else {
                delegate.handleReturnValue(ResponseBase.success(returnValue), returnType, mavContainer, webRequest);
            }
        } else {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }
}
