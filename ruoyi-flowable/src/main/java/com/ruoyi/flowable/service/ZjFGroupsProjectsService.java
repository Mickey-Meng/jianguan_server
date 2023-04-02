package com.ruoyi.flowable.service;


import com.ruoyi.common.core.domain.entity.CompanyInfo;

import java.util.Set;

/**
 * @author qiaoxulin
 * @date : 2022/5/26 17:14
 */
public interface ZjFGroupsProjectsService {

    /**
     * 通过项目id获取施工单位 监理单位等
     *
     * @param projectId
     * @return
     */
    CompanyInfo getCompanyInfoByProjectId(Integer projectId);


    /**
     * 通过项目id获取所有施工标段
     *
     * @param projectId
     * @return
     */
    Set<String> getBuildSectionByProjectId(Integer projectId);

}
