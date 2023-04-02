package com.ruoyi.flowable.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.flowable.domain.vo.FlowNextTaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthMapper {


    @Select("SELECT u.show_name FROM  zz_sys_user_role ur  INNER JOIN zz_sys_user u ON u.user_id = ur.user_id WHERE ur.role_id IN (#{roleIdList}) and u.deleted_flag = 1 and u.user_status = 0")
    List<String> getUserNameByRoleId(@Param("roleIdList") String roleIdList);

    @Select("SELECT show_name FROM zz_sys_user WHERE dept_id IN (#{deptIdList}) and deleted_flag = 1 and user_status = 0")
    List<String> getUserNameByDeptId(@Param("deptIdList") String deptIdList);

    @Select("SELECT show_name FROM zz_sys_user ${ew.customSqlSegment}")
    List<String> getUserNameByLogin(@Param(Constants.WRAPPER) Wrapper<String> wrapper);

    /**
     * 获取流程发起人部门领导
     * @param startUserId
     * @return
     */
    @Select("SELECT show_name FROM zz_sys_user WHERE user_id IN (SELECT user_id FROM zz_sys_user_post up " +
            "WHERE up.dept_post_id IN (SELECT dp.dept_post_id FROM zz_sys_dept_post dp INNER JOIN zz_sys_post p ON p.post_id = dp.post_id " +
            "WHERE dp.dept_id = (SELECT dept_id FROM zz_sys_user WHERE login_name= #{startUserId} and deleted_flag = 1 and user_status = 0) " +
            "and deleted_flag = 1 and user_status = 0 AND p.leader_post = 1));")
    List<String> getDeptLeaderUserName(@Param("startUserId") String startUserId);

    /**
     * 获取本部门上一级部门的领导
     * @param startUserId
     * @return
     */
    @Select("SELECT show_name FROM zz_sys_user WHERE user_id IN (SELECT user_id FROM zz_sys_user_post up " +
            "WHERE up.dept_post_id IN (SELECT dp.dept_post_id FROM zz_sys_dept_post dp " +
            "INNER JOIN zz_sys_post p ON p.post_id = dp.post_id WHERE dp.dept_id = " +
            "(SELECT IFNULL(parent_id, dept_id) FROM zz_sys_dept WHERE dept_id = (SELECT dept_id FROM zz_sys_user WHERE login_name=#{startUserId})) AND p.leader_post = 1)) and deleted_flag = 1 and user_status = 0")
    List<String> getUpDeptLeaderUserName(@Param("startUserId")String startUserId);

    @Select("SELECT u.show_name FROM zz_sys_user u INNER JOIN zz_sys_user_post up ON u.user_id = up.user_id ${ew.customSqlSegment}")
    List<String> getUserNameByPostId(@Param(Constants.WRAPPER) Wrapper<String> in);

    @Select("SELECT t.ASSIGNEE_ AS assignee, t.FORM_KEY_ AS formKey, t.ID_ AS taskId, t.NAME_ AS taskName, t.TASK_DEF_KEY_ as taskDefKey FROM ACT_RU_TASK t WHERE t.PROC_INST_ID_ = #{processInstanceId}")
    List<FlowNextTaskVo> getTasksByProcessInstanceId(String processInstanceId);

    @Select("SELECT login_name FROM zz_sys_user WHERE user_id = #{userId}")
    String getUserLoginNameByUserId(@Param("userId") Long userId);

    @Select("SELECT login_name FROM zz_sys_user WHERE dept_id = #{deptId} and deleted_flag = 1 and user_status = 0 LIMIT 1")
    String getUserLoginNameByDeptId(@Param("deptId") Long deptId);

    @Select("SELECT u.login_name FROM zz_sys_user u INNER JOIN  zz_sys_user_post up ON u.user_id = up.user_id WHERE up.dept_post_id = #{deptPostId} and u.deleted_flag = 1 and u.user_status = 0")
    List<String> getUserLoginNameByDeptPostId(@Param("deptPostId") Long deptPostId);

//    /**
//     * 通过流程实例获取下一任务执行人
//     * @param processInstanceId
//     * @return
//     */
//    @Select("SELECT GROUP_CONCAT(t.ASSIGNEE_) FROM ACT_RU_TASK t WHERE t.PROC_INST_ID_= #{processInstanceId} GROUP BY t.PROC_INST_ID_")
//    String getNextExecutorByProcessInstanceId(@Param("processInstanceId") String processInstanceId);
}
