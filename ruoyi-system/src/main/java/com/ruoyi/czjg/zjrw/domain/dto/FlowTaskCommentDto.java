package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @author : Chen_ZhiWei
 * @Date : Create file in 2022/9/8 14:49
 * @Version : 1.0
 * @Description :
 **/
public class FlowTaskCommentDto {

    private String approvalType;

    private String comment;

    private String delegateAssginee;

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

    public String getDelegateAssginee() {
        return delegateAssginee;
    }

    public void setDelegateAssginee(String delegateAssginee) {
        this.delegateAssginee = delegateAssginee;
    }
}
