package com.ruoyi.jianguan.business.userauth.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserProjectDto {
    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 用户ID集合
     */
    private List<Integer> userIds;

    /**
     * 工区(项目子集)ID集合
     */
    private List<Integer> workAreaIds;
}
