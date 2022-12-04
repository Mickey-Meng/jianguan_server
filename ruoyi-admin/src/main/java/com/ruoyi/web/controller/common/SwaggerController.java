package com.ruoyi.web.controller.common;


import com.ruoyi.common.annotation.Anonymous;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool/swagger")
@Anonymous
public class SwaggerController {

    @GetMapping("index")
    @Anonymous
    public String index(){

     return "/doc.html";

    }
}
