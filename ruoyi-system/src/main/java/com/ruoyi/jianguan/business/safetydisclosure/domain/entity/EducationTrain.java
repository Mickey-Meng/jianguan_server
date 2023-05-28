package com.ruoyi.jianguan.business.safetydisclosure.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author bx 2023/5/20
 */
@Data
@TableName("education_train")
public class EducationTrain {

    private Long id;
    private String content;
    private String trainer;
    private String fj;
    @TableField(value = "fj_type")
    private String fjType;
    @TableField(value = "fj_name")
    private String fjName;
    @TableField(value = "project_id")
    private Integer projectId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "train_date")
    private Date trainDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
