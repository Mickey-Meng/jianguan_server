package com.ruoyi.common.constant;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/17 3:11 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:   项目类型
 **/
public enum ProjectType {

    桥梁工程(1, "桥梁工程"),
    道路工程(2, "道路工程"),
    隧道工程(3, "隧道工程");

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

    ProjectType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        ProjectType[] projectTypes = ProjectType.values();
        for (ProjectType projectType : projectTypes
        ) {
            if (code == projectType.code) {
                return projectType.name;
            }
        }
        return null;
    }


}
