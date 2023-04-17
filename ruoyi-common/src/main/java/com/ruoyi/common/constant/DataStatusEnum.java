package com.ruoyi.common.constant;

/**
 * 数据状态
 *
 * @author G.X.L
 * @since 1.0
 * @date 2023-0405
 */
public enum DataStatusEnum {

    /**
     * 草稿
     */
    DRAFT("DRAFT", "草稿"),
    PENDING("PENDING", "创建"),
    FINISHED("FINISHED", "完成");

    private String status;

    private String desc;

    DataStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
