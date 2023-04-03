package com.ruoyi.common.config.zjrw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/6 11:46 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/swagger/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/null/swagger-resources/configuration/ui",
                        "/webjars/springfox-swagger-ui/**");
    }

}
