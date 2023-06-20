package com.ruoyi.web.controller.common;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.logo.Logo;
import com.ruoyi.jianguan.business.logo.mapper.LogoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bx 2023/5/30
 */
@RestController
@RequestMapping("logo")

@Anonymous
public class LogoController {

    @Autowired
    LogoMapper logoMapper;

    @Value("${jianguan.jmreportUrl}")
    String jmreportUrl;
    @Value("${jianguan.serverBaseUrl}")
    String serverBaseUrl;

    @GetMapping("/{id}")
    public ResponseBase getLogo(@PathVariable Long id) {
        Logo logo = logoMapper.selectById(id);
        return new ResponseBase(200, "查询成功!", logo);
    }

    @GetMapping("/jmreportUrl")
    public ResponseBase getJmreportUrl() {
        return new ResponseBase(200, "查询成功!", jmreportUrl);
    }

    @GetMapping("/baseServerUrl")
    public ResponseBase getBaseServerUrl() {
        return new ResponseBase(200, "查询成功!", serverBaseUrl);
    }
}
