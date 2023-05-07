package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.annotation.DataColumn;
import com.ruoyi.common.annotation.DataPermission;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanServerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理 数据层
 *
 * @author Lion Li
 */
public interface SysDeptMapper extends BaseMapperPlus<SysDeptMapper, SysDept, SysDept> {

    /**
     * 查询部门管理数据
     *
     * @param queryWrapper 查询条件
     * @return 部门信息集合
     */
    @DataPermission({
        @DataColumn(key = "deptName", value = "dept_id")
    })
    List<SysDept> selectDeptList(@Param(Constants.WRAPPER) Wrapper<SysDept> queryWrapper);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId            角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据项目ID查询项目关联的部门数据
     * @param projectId
     * @return
     */
    List<SysDept> selectDeptListByProjectId(@Param("projectId")Long projectId);

    Page<SysDept> selectDeptPageByProjectId(@Param("projectId")Long projectId, IPage<MapPlanServerVo> page);
}
