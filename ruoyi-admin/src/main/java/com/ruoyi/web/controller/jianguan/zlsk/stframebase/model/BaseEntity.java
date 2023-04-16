package com.ruoyi.web.controller.jianguan.zlsk.stframebase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


public class BaseEntity
{
    @Id
    // @Column(name = "Id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Transient
    private Integer page;
    @Transient
    private Integer rows;

    public BaseEntity() {
        this.page = 1;
        this.rows = 10;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return this.rows;
    }

    public void setRows(final Integer rows) {
        this.rows = rows;
    }
}
