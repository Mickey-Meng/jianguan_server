package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.ZjPersonClockin;
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
 * @author Chen_ZhiWei
 * @since 2022-06-09
 */
@Mapper
@Repository
public interface ZjPersonClockinDAO {

    void AddClockIn(ZjPersonClockin clockIn);

    void updateClockIn(ZjPersonClockin clockIn);

    @Select("select count(1) from zj_person_clockin where attendancePersonId = #{userId} and" +
            " clockTime >= #{startTime} and clockTime <= #{endTime}")
    Integer getCountByUserId(@Param("userId") Integer userId,
                             @Param("startTime") Date startTime,
                             @Param("endTime")Date endTime);

    //todo group by clockTime desc
    @Select("select * from zj_person_clockin where state = 1 and projectId = #{projectId} ")
    List<ZjPersonClockin> getAllByProjectId(@Param("projectId")Integer projectId);

    //todo group by a.clockTime desc
    @Select(" SELECT   zpc.*,   zpf.clockAddrName AS fenceAddrName   FROM   zj_person_clockin zpc " +
            " left join zj_person_fence zpf on  zpc.gid = zpf.id   " +
            " LEFT JOIN ss_f_user_role b ON a.attendancePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where a.state = 1 and a.projectId = #{projectId} and d.id = #{roleId} ")
    List<ZjPersonClockin> getAllByProjectIdAndRoleId(@Param("projectId")Integer projectId,
                                                     @Param("roleId")Integer roleId);

    //todo group by clockTime desc
    @Select(" SELECT   zpc.*,   zpf.clockAddrName AS fenceAddrName   FROM   zj_person_clockin zpc " +
            "left join   zj_person_fence zpf on  zpc.gid = zpf.id   " +
            "where  state = 1 and projectId = #{projectId}" +
            " and clockTime >= #{startTime} and clockTime <= #{endTime} ")
    List<ZjPersonClockin> getAllByProjectIdInTime(@Param("projectId")Integer projectId,
                                                  @Param("startTime")String startTime,
                                                  @Param("endTime")String endTime);

    //todo group by a.clockTime desc
    @Select("select a.* from zj_person_clockin a " +
            " LEFT JOIN ss_f_user_role b ON a.attendancePersonId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where a.state = 1 and a.projectId = #{projectId}" +
            " and a.clockTime >= #{startTime} and a.clockTime <= #{endTime} " +
            " and d.id = #{roleId} ")
    List<ZjPersonClockin> getAllByProjectIdAndRoleIdInTime(@Param("projectId")Integer projectId,
                                                           @Param("startTime")String startTime,
                                                           @Param("endTime")String endTime,
                                                           @Param("roleId")Integer roleId);

    //todo group by clockTime desc
    @Select("select * from zj_person_clockin where attendancePersonId = #{userId} " +
            " and state = 1 and projectId = #{projectId} ")
    List<ZjPersonClockin> getSelfAllByProjectId(@Param("projectId")Integer projectId,
                                            @Param("userId")Integer userId);


    //todo group by clockTime asc
    @Select("select * from zj_person_clockin where attendancePersonId = #{userId} " +
            " and clockTime >= #{startTime} and clockTime <= #{endTime} ")
    List<ZjPersonClockin> getSelfAllByUserIdAndTime(@Param("userId") Integer userId,
                                                    @Param("startTime") String start,
                                                    @Param("endTime") String end);

    //todo group by clockTime desc
    @Select("select * from zj_person_clockin where attendancePersonId = #{userId}" +
            " and state = 1 and projectId = #{projectId}" +
            " and clockTime >= #{startTime} and clockTime <= #{endTime} ")
    List<ZjPersonClockin> getSelfAllByProjectIdInTime(@Param("projectId")Integer projectId,
                                                  @Param("userId")Integer userId,
                                                  @Param("startTime")String startTime,
                                                  @Param("endTime")String endTime);

    //todo group by clockTime desc
    @Select("select * from zj_person_clockin where attendancePersonId = #{userId}" +
            " and state = 1 and projectId = #{projectId}" +
            " and clockTime >= #{startTime} and clockTime <= #{endTime} ")
    ZjPersonClockin getSelfTodayByProjectIdInTime(@Param("projectId")Integer projectId,
                                                      @Param("userId")Integer userId,
                                                      @Param("startTime")String startTime,
                                                      @Param("endTime")String endTime);

    List<ZjPersonClockin> getGroupClockInfo(@Param("start") String start,
                                         @Param("end") String end,
                                         @Param("groupId")Integer groupId);
}
