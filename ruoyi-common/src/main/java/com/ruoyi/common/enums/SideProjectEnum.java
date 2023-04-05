package com.ruoyi.common.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 旁站监理项目枚举
 *
 * @author qiaoxulin
 * @date : 2022/6/10 16:53
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum SideProjectEnum {
    /**
     * 沥青面层
     */
    沥青面层(1, "沥青面层"),
    /**
     * 压浆
     */
    压浆(2, "压浆"),
    /**
     * 张拉
     */
    张拉(3, "张拉"),
    /**
     * 桩基首盘混凝土
     */
    桩基首盘混凝土(4, "桩基首盘混凝土"),
    /**
     * 桩基钢筋笼安放
     */
    桩基钢筋笼安放(5, "桩基钢筋笼安放"),
    /**
     * 砼浇筑
     */
    砼浇筑(6, "砼浇筑"),
    /**
     * 湿喷桩
     */
    湿喷桩(7, "湿喷桩"),
    /**
     * 桩基
     */
    桩基(8, "桩基"),

    /**
     * 水泥稳定碎石基层
     */
    水泥稳定碎石基层(9, "水泥稳定碎石基层"),
    /**
     * 级配碎石基层底基层
     */
    级配碎石基层底基层(10, "级配碎石基层底基层");


    private int code;
    private String des;

    public static final List<SideProjectEnum> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(SideProjectEnum.values()));
    }

    @SneakyThrows(Exception.class)
    public static SideProjectEnum getEnum(Integer code) {
        for (SideProjectEnum itemEnum : LIST) {
            if (itemEnum.getCode() == code) {
                return itemEnum;
            }
        }
        log.error("未找到指定的旁站监理项目枚举！");
        return null;
    }
}
