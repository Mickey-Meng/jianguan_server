package com.ruoyi.jianguan.manage.project.domain.bo;

import lombok.Data;

/**
 * 项目和部门关联业务对象 sys_project_dept
 *
 * @author ruoyi
 * @date 2023-04-30
 */

@Data
public class JgProjectDeptBo {

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 关联的部门ID
     */
    private String deptIds;

}
