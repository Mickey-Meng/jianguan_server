package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/22 11:19 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class PostData {
    private String projectid;

    public PostData(String projectid) {
        this.projectid = projectid;
    }
    public PostData() {

    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }
}

