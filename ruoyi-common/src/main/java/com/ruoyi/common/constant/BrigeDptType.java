package com.ruoyi.common.constant;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/17 3:19 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public enum BrigeDptType {
    上部结构(1, "上部结构"),
    下部结构(2, "下部结构");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    BrigeDptType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        BrigeDptType[] brigeDptTypes = BrigeDptType.values();
        for (BrigeDptType brigeDptType : brigeDptTypes
        ) {
            if (code == brigeDptType.code) {
                return brigeDptType.name;
            }
        }
        return null;
    }

}
