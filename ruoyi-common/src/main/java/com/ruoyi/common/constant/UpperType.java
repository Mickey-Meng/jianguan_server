package com.ruoyi.common.constant;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/17 3:22 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public enum UpperType {
    预制小箱梁_张拉(1, "预制小箱梁_张拉"),
    预制小箱梁_非张拉(1, "预制小箱梁_非张拉"),
    现浇箱梁(2, "现浇箱梁"),
    钢梁(2, "钢梁"),
    组合梁(2, "组合梁"),
    桥面系_铺装(2, "桥面系_铺装"),
    桥面系_伸缩缝(2, "桥面系_伸缩缝");

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

    UpperType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        UpperType[] upperTypes = UpperType.values();
        for (UpperType upperType : upperTypes
        ) {
            if (code == upperType.code) {
                return upperType.name;
            }
        }
        return null;
    }
}
