package com.ruoyi.common.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 拟分包工程部位枚举类
 *
 * @author qiaoxulin
 * @date : 2022/5/19
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum BuildProjectPartEnum {

    /**
     * 路基工程
     */
    路基工程(1, "路基工程"),

    /**
     * 路面工程
     */
    路面工程(2, "路面工程"),
    /**
     * 桥梁工程
     */
    桥梁工程(3, "桥梁工程"),
    /**
     * 隧道工程
     */
    隧道工程(4, "隧道工程"),
    /**
     * 绿化工程
     */
    绿化工程(5, "绿化工程"),
    /**
     * 交安工程
     */
    交安工程(6, "交安工程"),
    /**
     * 其他
     */
    其他(7, "其他");


    private int code;
    private String des;

    public static final List<BuildProjectPartEnum> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(BuildProjectPartEnum.values()));
    }

    @SneakyThrows(Exception.class)
    public static BuildProjectPartEnum getEnum(Integer code) {
        for (BuildProjectPartEnum itemEnum : LIST) {
            if (itemEnum.getCode() == code) {
                return itemEnum;
            }
        }
        log.error("未找到指定的拟分包工程部位枚举！");
        return null;
    }
}
