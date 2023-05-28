package com.ruoyi.jianguan.business.safetydisclosure.domain.dto;


import lombok.Data;

import java.util.Date;

/**
 * @author bx 2023/5/20
 */
@Data
public class EducationTrainDto {
    private Long id;
    private String content;
    private String trainer;
    private String fj;
    private String fjType;
    private String fjName;
    private Integer projectId;
    private Date trainDate;
}
