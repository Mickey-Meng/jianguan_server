package com.ruoyi.jianguan.common.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/22 4:02 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ConponentParent {
    private String pcode;
    private String code;
    private String pname;
    private String name;
    private Integer pid;
    private Integer id;

    public ConponentParent(String pcode, String pname, String code, String name, Integer pid) {
        this.pcode = pcode;
        this.pname = pname;
        this.code = code;
        this.name = name;
        this.pid = pid;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
