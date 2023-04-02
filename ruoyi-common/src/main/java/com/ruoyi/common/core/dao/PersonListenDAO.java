package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.model.ZjPersonChange;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/17 17:17
 * @Version : 1.0
 * @Description :
 **/
@Mapper
@Repository
public interface PersonListenDAO {

    @Select("select * from zj_person where taskId = #{id} and status = 1")
    PersonDTO getByProcessId(@Param("id")String id);

    Integer updateContract(PersonDTO person);


    @Select("select * from zj_person_change where processInstanceId = #{processId}")
    ZjPersonChange getChangeByProcessId(@Param("processId")String processId);

    void updatePersonChange(ZjPersonChange personChange);

    @Select("select * from ss_f_user_role where userid = #{userid}")
    SsFUserRole getByUserid(@Param("userid")Integer userid);

    @Update("update ss_f_user_role set roleid = #{roleid} where userid = #{userid}")
    void updateRoleByUserId(@Param("roleid")Integer roleid,
                            @Param("userid")Integer userid);


    @Delete("delete from ss_f_user_role where userid = #{userid} and roleid = #{roleid}")
    void deleteByUserId(@Param("roleid")Integer roleid,
                        @Param("userid")Integer userid);

    @Select("select * from zj_person_leave where processInstanceId = #{processId}")
    ZjPersonLeave getLeaveByProcessId(@Param("processId")String processId);

    void updatePersonLeave(ZjPersonLeave personLeave);


    @Select("select cid from ss_f_user_online where userid = #{userid}")
    String getCidByUserid(Integer userid);

    @Update("update zj_person set status = #{status}, approvalTime = now() " +
            " where taskId = #{taskId}")
    void updateContractStatus(@Param("status")Integer status,
                              @Param("taskId")String taskId);

    @Update("update zj_person_change set status = #{status}, approvalTime = now() " +
            " where processInstanceId = #{taskId}")
    void updateChangeStatus(@Param("status")Integer status,
                              @Param("taskId")String taskId);

    @Update("update zj_person_leave set status = #{status}, approvalTime = now() " +
            " where processInstanceId = #{taskId}")
    void updateLeaveStatus(@Param("status")Integer status,
                            @Param("taskId")String taskId);
}
