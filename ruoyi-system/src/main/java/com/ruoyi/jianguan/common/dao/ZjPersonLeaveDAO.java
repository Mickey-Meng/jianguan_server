package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ChenZhiWei
 * @since 2022-05-27
 */
@Repository
@Mapper
public interface ZjPersonLeaveDAO {

    @Select("SELECT LAST_INSERT_ID();")
    Integer getLastInsertId();

    @Select("select count(*) from zj_person_leave")
    int getAllCount();

    @Select("select * from zj_person_leave where id = #{id}")
    ZjPersonLeave selectByPrimaryKey(@Param("id") Integer id);

    void newPersonLeave(ZjPersonLeave personLeave);

    void updatePersonLeave(ZjPersonLeave personLeave);

    @Select("select * from zj_person_leave where leavePersonId = #{id} order by id desc")
    List<ZjPersonLeave> getById(@Param("id")Integer id);

    List<ZjPersonLeave> getByUserId(@Param("leavePersonId")Integer leavePersonId);

    @Select("select * from zj_person_leave where businessKey = #{businessKey} ")
    ZjPersonLeave getByBusinessKey(@Param("businessKey")String businessKey);


    @Select("select count(1) from zj_person_leave where processInstanceId = #{processId}")
    Integer getCountByProcessId(@Param("processId")String processId);

    @Select("select * from zj_person_leave where processInstanceId = #{processId}")
    ZjPersonLeave getLeaveByProcessId(@Param("processId")String processId);

    //todo group by subDate desc
    @Select("select * from zj_person_leave where status = 1 order by startTime desc")
    List<ZjPersonLeave> getAllFinishLeaves();


    @Select("select * from zj_person_leave where status = 1 and leavePersonId = #{userId} order by subDate desc ")
    List<ZjPersonLeave> getFinishLeavesByUserId(@Param("userId")Integer userId);


    @Select("select * from zj_person_leave order by subDate desc")
    List<ZjPersonLeave> getAllByProjectId();

    @Select("select * from zj_person_leave order by subDate desc limit #{pageNum}, #{pageSize}")
    List<ZjPersonLeave> getAllByProjectIdLimit(@Param("pageNum")Integer pageNum,
                                               @Param("pageSize")Integer pageSize);

    @Delete("delete from zj_person_leave where id = #{id}")
    void delLeave(@Param("id")Integer id);

    @Select("select a.* from zj_person_leave a " +
            " LEFT JOIN ss_f_user_role b ON a.leavePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} and a.status in (2, 3)")
    List<ZjPersonLeave> getLeavesByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person_leave a " +
            " LEFT JOIN ss_f_user_role b ON a.leavePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc")
    List<ZjPersonLeave> getAllLeavesByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person_leave a " +
            " LEFT JOIN ss_f_user_role b ON a.leavePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc " +
            " limit #{pageNum}, #{pageSize}")
    List<ZjPersonLeave> getAllLeavesByRoleIdLimit(@Param("roleId")Integer roleId,
                                                  @Param("pageNum")Integer pageNum,
                                                  @Param("pageSize")Integer pageSize);

    @Select("select * from zj_person_leave where " +
            " leavePersonId = #{userId} and `status` = 2 order by startTime desc" )
    ZjPersonLeave getFinishLeaveByUserId(@Param("userId")Integer userId);

    /**
     * 获取  请假中和 请假失效的数据
     * @param userId
     * @return
     */
    @Select("select * from zj_person_leave where " +
            " leavePersonId = #{userId} and `status` in (2, 3) and startTime >= #{startTime}")
    List<ZjPersonLeave> getFinishedLeaveByUserId(@Param("userId") Integer userId,
                                                 @Param("startTime") String startTime);

    @Select("select * from zj_person_leave where leavePersonId = #{userId} " +
            " and `status` in (2, 3) and subDate >= #{start} and subDate <= now()")
    List<ZjPersonLeave> getFinishedLeaveByUserIdAndTime(@Param("userId")Integer userId,
                                                        @Param("start")String startTime);

    @Select("select * from zj_person_leave where leavePersonId = #{userId} " +
            " and `status` = 2 and endTime >= #{start} and startTime <= #{start}")
    ZjPersonLeave getLeavingByUserIdAndTime(@Param("userId")Integer userId,
                             @Param("start") Date startTime);

    @Select("select a.* from zj_person_leave a " +
            " LEFT JOIN ss_f_user_role b ON a.leavePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} and a.status = #{state} order by a.subDate desc " +
            " limit #{pageNum}, #{pageSize}")
    List<ZjPersonLeave> getAllLeavesByRoleIdLimitAndState(@Param("roleId") Integer roleId,
                                                          @Param("pageNum")Integer num,
                                                          @Param("pageSize")Integer pageSize,
                                                          @Param("state")Integer state);

    @Select("select * from zj_person_leave and status = #{state} order by subDate desc limit #{pageNum}, #{pageSize}")
    List<ZjPersonLeave> getAllLeavesByLimitAndState(@Param("pageNum") Integer num,
                                                    @Param("pageSize")Integer pageSize,
                                                    @Param("state") Integer state);

    @Select("select a.* from zj_person_leave a " +
            " LEFT JOIN ss_f_user_role b ON a.leavePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} and a.status = #{state} order by a.subDate desc")
    List<ZjPersonLeave> getAllLeavesByRoleIdAndState(@Param("roleId") Integer roleId,
                                                     @Param("state")Integer state);

    @Select("select * from zj_person_leave where status = #{state} order by subDate desc")
    List<ZjPersonLeave> getAllLeavesByState(Integer state);

    /**
     * 查询大于指定开始日期的请假数据
     * @param startDateTime
     * @return
     */
    @Select("select * from zj_person_leave where  `status` in (2, 3) and startTime >= #{startTime}")
    List<ZjPersonLeave> getPersonLeaveListByStartDate(String startDateTime);
}
