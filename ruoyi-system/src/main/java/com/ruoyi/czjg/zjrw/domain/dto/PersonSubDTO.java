package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.czjg.zjrw.domain.entity.PersonSub;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/10 09:25
 * @Version : 1.0
 * @Description : 人员合同填报提交时所用到的类
 **/
public class PersonSubDTO {

    @ApiModelProperty(value = "基础信息")
    private PersonDTO person;
    @ApiModelProperty(value = "报审信息")
    private List<PersonSub> personSubs;
    @ApiModelProperty(value = "projectid 项目id")
    private int projectid;
    @ApiModelProperty(value = "流程定义的key; 流程模板名称")
    private String processKey;
    @ApiModelProperty(value = "下一级处理人username", required = false)
    private String variables;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public List<PersonSub> getPersonSubs() {
        return personSubs;
    }

    public void setPersonSubs(List<PersonSub> personSubs) {
        this.personSubs = personSubs;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }
}
