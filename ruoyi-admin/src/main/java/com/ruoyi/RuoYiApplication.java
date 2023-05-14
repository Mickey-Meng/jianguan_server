package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author ruoyi
 */

@EnableScheduling
@ConfigurationPropertiesScan
@MapperScan(basePackages = {"com.ruoyi.*.**.mapper.**","com.ruoyi.*.**.dao.**"})
@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport","com.ruoyi"})
public class RuoYiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoYiApplication.class);
    }

}
