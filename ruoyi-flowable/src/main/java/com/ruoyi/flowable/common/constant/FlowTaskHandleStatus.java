package com.ruoyi.flowable.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 工单处理状态
 * @Author: lpeng
 * @Date: 2022-05-05 13:53
 * @Description:
 */
public final class FlowTaskHandleStatus {

    /**
     * 待处理。
     */
    public static final int UNHANDLE = 1;
    /**
     * 正在处理。
     */
    public static final int HANDLEING = 2;

    /**
     * 已办。
     */
    public static final int ALREADHANDLE = 3;


    private static final Map<Object, String> DICT_MAP = new HashMap<>(2);
    static {
        DICT_MAP.put(UNHANDLE, "待办");
        DICT_MAP.put(HANDLEING, "在办");
        DICT_MAP.put(ALREADHANDLE, "已办");
    }

    /**
     * 获取描述
     * @param value
     * @return
     */
    public static String  getName(Integer value) {
        return  DICT_MAP.get(value);
    }

    /**
     * 判断参数是否为当前常量字典的合法值。
     *
     * @param value 待验证的参数值。
     * @return 合法返回true，否则false。
     */
    public static boolean isValid(Integer value) {
        return value != null && DICT_MAP.containsKey(value);
    }

    /**
     * 私有构造函数，明确标识该常量类的作用。
     */
    private FlowTaskHandleStatus() {
    }
}
