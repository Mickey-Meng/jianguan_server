package com.ruoyi.flowable.service.impl;

import com.google.common.collect.Sets;
import com.ruoyi.common.core.dao.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.entity.CompanyInfo;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author qiaoxulin
 * @date : 2022/5/26 17:14
 */
@Service
@Slf4j
public class ZjFGroupsProjectsServiceImpl implements ZjFGroupsProjectsService {

    @Autowired
    private ZjFGroupsProjectsDAO projectsDAO;

    /**
     * 通过项目id获取施工单位 监理单位等
     *
     * @param peojectId
     * @return
     */
    @Override
    public CompanyInfo getCompanyInfoByProjectId(Integer peojectId) {
        //通过项目id查询项目详细信息（项目名、施工单位、监理单位、合同号等）
        List<Map<String, Object>> companys = projectsDAO.getCompanyInfoByProjectId(peojectId);
        //施工单位
        Set<String> sgdws = Sets.newHashSet();
        //监理单位
        Set<String> jldws = Sets.newHashSet();
        //管理单位
        Set<String> gldws = Sets.newHashSet();
        //建设单位
        Set<String> jsdws = Sets.newHashSet();
        //全过程咨询单位
        Set<String> qgczxdws = Sets.newHashSet();
        //设计单位
        Set<String> sjdws = Sets.newHashSet();
        if (Objects.nonNull(companys) && !companys.isEmpty()) {
            companys.forEach(company -> {
                String typecode = (String) company.get("typecode");
                if ("jldw".equals(typecode)) {
                    jldws.add((String) company.get("name"));
                }
                if ("sgdw".equals(typecode)) {
                    sgdws.add((String) company.get("name"));
                }
                if ("gldw".equals(typecode)) {
                    gldws.add((String) company.get("name"));
                }
                if ("jsdw".equals(typecode)) {
                    jsdws.add((String) company.get("name"));
                }
                if ("qgczxdw".equals(typecode)) {
                    qgczxdws.add((String) company.get("name"));
                }
                if ("sjdw".equals(typecode)) {
                    sjdws.add((String) company.get("name"));
                }
            });
        }
        //返回
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setJldws(jldws);
        companyInfo.setSgdws(sgdws);
        companyInfo.setGldws(gldws);
        companyInfo.setJsdws(jsdws);
        companyInfo.setQgczxdws(qgczxdws);
        companyInfo.setSjdws(sjdws);
        return companyInfo;
    }

    /**
     * 通过项目id获取所有施工标段
     *
     * @param projectId
     * @return
     */
    @Override
    public Set<String> getBuildSectionByProjectId(Integer projectId) {
        Set<String> buildSectionNames = Sets.newHashSet();
        //项目id查询所有标段
        List<SsFProjects> buildSectionInfo = projectsDAO.getBuildSectionByProjectId(projectId);
        if (Objects.nonNull(buildSectionInfo) && !buildSectionInfo.isEmpty()){
            buildSectionInfo.forEach(buildSection ->{
                buildSectionNames.add(buildSection.getName());
            });
        }
        return buildSectionNames;
    }
}
