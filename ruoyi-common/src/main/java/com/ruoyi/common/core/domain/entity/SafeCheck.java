package com.ruoyi.common.core.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 2:34 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SafeCheck {
    private Integer id;
    private String username;
    private String groupname;
    private Integer gongquid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGongquid() {
        return gongquid;
    }

    public void setGongquid(Integer gongquid) {
        this.gongquid = gongquid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public String toString() {
        return "SafeCheck{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", groupname='" + groupname + '\'' +
                ", gongquid=" + gongquid +
                ", name='" + name + '\'' +
                '}';
    }
}
