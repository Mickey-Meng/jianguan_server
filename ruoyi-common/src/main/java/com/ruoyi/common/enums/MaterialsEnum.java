package com.ruoyi.common.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 材料名称枚举类
 *
 * @author qiaoxulin
 * @date : 2022/5/13 11:24
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum MaterialsEnum {

    /**
     * 防水板
     */
    防水板(1, "防水板"),

    /**
     * 粉煤灰
     */
    粉煤灰(2, "粉煤灰"),
    /**
     * 工字钢
     */
    工字钢(3, "工字钢"),

    /**
     * 钢绞线
     */
    钢绞线(4, "钢绞线"),
    /**
     * 钢筋焊接
     */
    钢筋焊接(5, "钢筋焊接"),

    /**
     * 钢筋
     */
    钢筋(6, "钢筋"),
    /**
     * 钢筋网片
     */
    钢筋网片(7, "钢筋网片"),

    /**
     * 钢筋机械链接
     */
    钢筋机械链接(8, "钢筋机械链接"),
    /**
     * 金属波纹管
     */
    金属波纹管(9, "金属波纹管"),

    /**
     * 减水剂
     */
    减水剂(10, "减水剂"),
    /**
     * 机制砂
     */
    机制砂(11, "机制砂"),

    /**
     * 矿渣粉
     */
    矿渣粉(12, "矿渣粉"),
    /**
     * 卵石
     */
    卵石(13, "卵石"),

    /**
     * 沥青
     */
    沥青(14, "沥青"),
    /**
     * 锚夹具
     */
    锚夹具(15, "锚夹具"),
    /**
     * 水泥
     */
    水泥(16, "水泥"),

    /**
     * 速凝剂
     */
    速凝剂(17, "速凝剂"),
    /**
     * 石屑
     */
    石屑(18, "石屑"),

    /**
     * 石灰
     */
    石灰(19, "石灰"),
    /**
     * 塑料波纹管
     */
    塑料波纹管(20, "塑料波纹管"),

    /**
     * 碎石
     */
    碎石(21, "碎石"),
    /**
     * 砂
     */
    砂(22, "砂"),
    /**
     * 声测管
     */
    声测管(23, "声测管"),

    /**
     * 土工格栅
     */
    土工格栅(24, "土工格栅"),
    /**
     * 土工布
     */
    土工布(25, "土工布"),

    /**
     * 无缝钢管
     */
    无缝钢管(26, "无缝钢管"),
    /**
     * 压浆料
     */
    压浆料(27, "压浆料"),

    /**
     * 支座
     */
    支座(28, "支座"),
    /**
     * 止水条
     */
    止水条(29, "止水条"),
    /**
     * 止水带
     */
    止水带(30, "止水带"),

    /**
     * 其他
     */
    其他(31, "其他");


    private int code;
    private String des;

    public static final List<MaterialsEnum> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(MaterialsEnum.values()));
    }

    @SneakyThrows(Exception.class)
    public static MaterialsEnum getEnum(Integer code) {
        for (MaterialsEnum itemEnum : LIST) {
            if (itemEnum.getCode() == code) {
                return itemEnum;
            }
        }
        log.error("未找到指定的材料名称枚举！");
        return null;
    }
}
