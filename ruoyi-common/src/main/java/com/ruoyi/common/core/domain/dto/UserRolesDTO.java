package com.ruoyi.common.core.domain.dto;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/24 15:35
 * @Version : 1.0
 * @Description :
 **/
public class UserRolesDTO {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户编号
     */
    private String usercode;
    /**
     * 用户组表id
     */
    private Integer ugid;

    private Integer roleid;

    private String rolename;

    private String rolecode;

    private Integer parentid;

    private Integer type;

    private Integer rolelevel;

    private String visible;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Integer getUgid() {
        return ugid;
    }

    public void setUgid(Integer ugid) {
        this.ugid = ugid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }
}
