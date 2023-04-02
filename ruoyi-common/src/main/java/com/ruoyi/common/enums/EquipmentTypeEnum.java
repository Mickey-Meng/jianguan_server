package com.ruoyi.common.enums;


import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 设备类型枚举类
 *
 * @author qiaoxulin
 * @date : 2022/5/26 17:47
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum EquipmentTypeEnum {
    /**
     * 一般设备
     */
    一般设备(1, "一般设备"),
    /**
     * 特种设备
     */
    特种设备(2, "特种设备"),

    /**
     * 其他设备
     */
    其他设备(3, "其他设备");


    private int code;
    private String des;

    public static final List<EquipmentTypeEnum> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(EquipmentTypeEnum.values()));
    }

    @SneakyThrows(Exception.class)
    public static EquipmentTypeEnum getEnum(Integer code) {
        for (EquipmentTypeEnum itemEnum : LIST) {
            if (itemEnum.getCode() == code) {
                return itemEnum;
            }
        }
        log.error("未找到指定的设备类型枚举！");
        return null;
    }
}
