package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/10 12:00
 * @Version : 1.0
 * @Description :
 **/
public class TaskCommentReturn {

    private String approvalType;

    private String comment;

    private String createLoginName;

    private String createTime;

    private String createUserId;

    private String createUsername;

    private String delegateAssginee;

    private String id;

    private String processInstanceId;

    private String taskId;

    private String taskKey;

    private String taskName;

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateLoginName() {
        return createLoginName;
    }

    public void setCreateLoginName(String createLoginName) {
        this.createLoginName = createLoginName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getDelegateAssginee() {
        return delegateAssginee;
    }

    public void setDelegateAssginee(String delegateAssginee) {
        this.delegateAssginee = delegateAssginee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
