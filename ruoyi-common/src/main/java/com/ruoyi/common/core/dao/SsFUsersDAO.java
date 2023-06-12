package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.entity.CheckModel;
import com.ruoyi.common.core.domain.entity.SafeCheck;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFUsersDAO extends BaseDaoMapper<SsFUsers> {
    int deleteByPrimaryKey(Integer id);

    int insert(SsFUsers record);

    int insertSelective(SsFUsers record);

    SsFUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsFUsers record);

    int updateByPrimaryKey(SsFUsers record);

    @Select("SELECT" +
            "su.user_id AS id," +
            "su.user_name AS username," +
            "su.`password` AS pwd," +
            "su.nick_name AS NAME," +
            "su.create_time AS sttime," +
            "su.`status` AS ststate " +
            "FROM" +
            "sys_user su " +
            "WHERE" +
            "su.user_name  = #{username} ")
    SsFUsers checkLogin(@Param("username") String username);

    @Select("SELECT" +
            "su.user_id AS id," +
            "su.user_name AS username," +
            "su.`password` AS pwd," +
            "su.nick_name AS NAME," +
            "su.create_time AS sttime," +
            "su.`status` AS ststate " +
            "FROM" +
            "sys_user su " +
            "WHERE" +
            "su.user_name  = #{username} ")
    SsFUsers userLogin(@Param("username") String username);

    List<CheckModel> getSupCheck();

    @Select("select role_id from sys_user_role where user_id = #{userid}")
    Integer getRoleById(@Param("userid") Integer userid);

    @Update("update ss_f_users set pwd = #{pwd} where id = #{id}")
    Integer updatePwd(@Param("pwd") String pwd,
                      @Param("id") Integer id);

    List<SafeCheck> getInfoById(@Param("list") List<Integer> list);

    @Select("select * from ss_f_users where id = #{id}")
    SsFUsers checkLoginById(@Param("id") Integer id);

    @Select("SELECT " +
            "su.user_id AS id, " +
            "su.user_name AS username, " +
            "su.`password` AS pwd, " +
            "su.nick_name AS NAME, " +
            "su.create_time AS sttime, " +
            "su.`status` AS ststate " +
            "FROM " +
            "sys_user su " +
            "WHERE " +
            "su.user_id  =#{id} ")
    String getNameByUserId(@Param("id") Integer id);

    /**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     */
    List<SsFUsers> getUserNamesByIds(@Param("userIds") List<Integer> userIds);

    /**
     * 查询用户显示名通过登录名称
     *
     * @param userNames
     * @return
     */
    List<SsFUsers> getNamesByUserName(@Param("userNames")List<String> userNames);

    @Select("SELECT" +
            "su.user_id AS id," +
            "su.user_name AS username," +
            "su.`password` AS pwd," +
            "su.nick_name AS NAME," +
            "su.create_time AS sttime," +
            "su.`status` AS ststate " +
            "FROM" +
            "sys_user su " +
            "WHERE" +
            "su.dept_id = #{id}")
    List<SsFUsers> getUserByGroupId(@Param("id") Integer id);

    @Select("select `username` from ss_f_users_copy where id =#{id}")
    String getCopyNameByUserId(@Param("id") Integer id);

    @Select("SELECT" +
            "su.user_id AS id," +
            "su.user_name AS username," +
            "su.`password` AS pwd," +
            "su.nick_name AS NAME," +
            "su.create_time AS sttime," +
            "su.`status` AS ststate " +
            "FROM" +
            "sys_user su " +
            "WHERE " +
            "su.status = 1 and " +
            "su.user_id = #{id}")
    SsFUsers getUsingInfoByUserId(Integer id);

    @Select("SELECT sfu.USERID  FROM ss_f_user_project sfu , ss_f_projects sfp  WHERE sfu.GROUPID = sfp.ID AND sfp.PARENTID = #{projectId}")
    List<String> getUserIDListByProjectId(String projectId);


    /*
    @Select("select * from ss_f_users where USERNAME = #{username} ")
    SsFUsers checkLogin(@Param("username") String username);

    @Select("select * from ss_f_users where USERNAME = #{username}")
    SsFUsers userLogin(@Param("username") String username);

    List<CheckModel> getSupCheck();

    @Select("select roleid from ss_f_user_role where userid = #{userid}")
    Integer getRoleById(@Param("userid") Integer userid);

    @Update("update ss_f_users set pwd = #{pwd} where id = #{id}")
    Integer updatePwd(@Param("pwd") String pwd,
                      @Param("id") Integer id);

    List<SafeCheck> getInfoById(@Param("list") List<Integer> list);

    @Select("select * from ss_f_users where id = #{id}")
    SsFUsers checkLoginById(@Param("id") Integer id);

    @Select("select `name` from ss_f_users where id =#{id}")
    String getNameByUserId(@Param("id") Integer id);

    *//**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     *//*
    List<SsFUsers> getUserNamesByIds(@Param("userIds") List<Integer> userIds);

    *//**
     * 查询用户显示名通过登录名称
     *
     * @param userNames
     * @return
     *//*
    List<SsFUsers> getNamesByUserName(@Param("userNames")List<String> userNames);

    @Select("select a.usercode, a.sttime, a.ststate, a.storder, a.ugid " +
        " from ss_f_users a left join ss_f_user_group b on " +
        " a.id = b.userid where groupid = #{id}")
    List<SsFUsers> getUserByGroupId(@Param("id") Integer id);

    @Select("select `username` from ss_f_users_copy where id =#{id}")
    String getCopyNameByUserId(@Param("id") Integer id);

    @Select("select * from ss_f_users where id = #{id} and ststate = 1")
    SsFUsers getUsingInfoByUserId(Integer id);

    @Select("SELECT sfu.USERID  FROM ss_f_user_project sfu , ss_f_projects sfp  WHERE sfu.GROUPID = sfp.ID AND sfp.PARENTID = #{projectId}")
    List<String> getUserIDListByProjectId(String projectId);*/
}
