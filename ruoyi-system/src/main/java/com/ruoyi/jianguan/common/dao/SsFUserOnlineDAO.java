package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.dto.UserOnlineDTO;
import com.ruoyi.jianguan.common.domain.entity.SsFUserOnline;
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
                             @Param("endTime")String endTime, @Param("roleKeys")List roleKeys);

    List<String> getJLOnlineUsers(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime,@Param("roleKeys")List roleKeys);

    /***
     * yangaogao 20230923
     * 通过角色ids来获取在线登录用户
     * @param startTime
     * @param endTime
     * @param roleIds
     * @return
     */
    List<String> getOnlineUsersByRoleIds(@Param("startTime")String startTime,
                                  @Param("endTime")String endTime,@Param("roleIds")List roleIds);
    /***
     * yangaogao 20230923
     * 通过角色ids来获取历史在线登录用户
     * @param roleIds
     * @return
     */
    List<String> getAllUsersByRoleIds(@Param("roleIds")List roleIds);

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

    @Select("select sfuo.* from ss_f_user_online  sfuo, sys_user su    where cid is not null and sfuo.userid=su.user_id and su.user_name not like '%admin'")
    List<SsFUserOnline> getAll();

    @Update("update ss_f_user_online set role = #{roleId} where userid = #{userId}")
    void updateRoleBuUserId(@Param("userId")Integer userId,
                            @Param("roleId")Integer roleId);


    List<UserOnlineDTO> getAllUserOnline();

    List<UserOnlineDTO> getAllUserOnlineByTime(@Param("startTime")String startTime,
                                               @Param("endTime")String endTime);
}
