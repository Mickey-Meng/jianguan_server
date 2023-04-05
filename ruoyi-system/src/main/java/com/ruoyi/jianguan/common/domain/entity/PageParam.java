package com.ruoyi.jianguan.common.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/25 14:30
 * @Version : 1.0
 * @Description :
 **/
public class PageParam {

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPage;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                '}';
    }
}
