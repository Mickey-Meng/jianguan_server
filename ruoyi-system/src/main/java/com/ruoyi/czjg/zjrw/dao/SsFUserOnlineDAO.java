package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.dto.UserOnlineDTO;
import com.ruoyi.czjg.zjrw.domain.entity.SsFUserOnline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjSsFUserOnlineDAO")
@Mapper
public interface SsFUserOnlineDAO {

    int insert(SsFUserOnline userOnline);

    int updateTimeByUserid(@Param("userid") Integer userid,
                           @Param("cid")String cid,
                           @Param("role")Integer role,
                           @Param("lon") String lon,
                           @Param("lat") String lat);

    @Select("select * from ss_f_user_online where userid = #{userid}")
    SsFUserOnline getAllByUserId(@Param("userid")Integer userid);

    @Select("select count(1) from ss_f_user_online where userid = #{userid}")
    Integer getByUserid(@Param("userid")Integer userid);

    Integer getJLOnlineCount(@Param("startTime")String startTime,
                             @Param("endTime")String endTime);

    List<String> getJLOnlineUsers(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime);

    List<String> getAllJLUsers(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime);

    Integer getSGOnlineCount(@Param("startTime")String startTime,
                             @Param("endTime")String endTime);

    List<String> getSGOnlineUsers(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime);

    List<String> getAllSGUsers(@Param("startTime")String startTime,
                               @Param("endTime")String endTime);

    Integer getYZOnlineCount(@Param("startTime")String startTime,
                             @Param("endTime")String endTime);

    List<String> getYZOnlineUsers(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime);

    List<String> getAllYZUsers(@Param("startTime")String startTime,
                               @Param("endTime")String endTime);

    @Select("select cid from ss_f_user_online where userid = #{userid}")
    String getCidByUserid(Integer userid);

    @Select("select * from ss_f_user_online where cid is not null")
    List<SsFUserOnline> getAll();

    @Update("update ss_f_user_online set role = #{roleId} where userid = #{userId}")
    void updateRoleBuUserId(@Param("userId")Integer userId,
                            @Param("roleId")Integer roleId);


    List<UserOnlineDTO> getAllUserOnline();

    List<UserOnlineDTO> getAllUserOnlineByTime(@Param("startTime")String startTime,
                                               @Param("endTime")String endTime);
}
