package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.NonNull;

/**
 * 项目和部门关联对象 sys_project_dept
 *
 * @author ruoyi
 * @date 2023-04-30
 */
@Data
@AllArgsConstructor
@TableName("sys_project_dept")
public class JgProjectDept {

    private static final long serialVersionUID=1L;

    /**
     * 项目ID
     */
    @TableField(value = "project_id")
    @NonNull
    private Long projectId;
    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    @NonNull
    private Long deptId;

}
