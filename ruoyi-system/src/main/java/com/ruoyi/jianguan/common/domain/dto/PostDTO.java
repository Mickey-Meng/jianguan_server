package com.ruoyi.jianguan.common.domain.dto;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/1 17:12
 * @Version : 1.0
 * @Description :
 **/
public class PostDTO {

    private Integer postId;

    private String postName;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", postName='" + postName + '\'' +
                '}';
    }
}
