package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.dto.ClockInCensusReturn;
import com.ruoyi.czjg.zjrw.domain.dto.UserRolesDTO;
import com.ruoyi.czjg.zjrw.domain.entity.SsFGroups;
import com.ruoyi.czjg.zjrw.domain.entity.SsFUserGroup;
import com.ruoyi.czjg.zjrw.domain.entity.SsFUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFUserGroupDAO {
    int insert(SsFUserGroup record);

    int insertSelective(SsFUserGroup record);

    @Select("select * from ss_f_user_project where userid =  #{userid}" )
    SsFUserGroup getGroup(@Param("userid") Integer id);

    @Select("select * from ss_f_user_project where userid =  #{userid} and ststate = 1" )
    List<SsFUserGroup> getGroups(@Param("userid") Integer id);

    @Select("select * from ss_f_projects where grouplevel = 4")
    List<SsFGroups> getall();
    @Select("select * from ss_f_projects where grouplevel = 5 and parentid = #{id}")
    List<SsFGroups> getallGq(@Param("id") Integer id);

    Integer deleteByUserIds(@Param("list") List<Integer> list);

    @Select("select groupid from ss_f_user_project where userid = #{id} and ststate = 1")
    List<Integer> getGroupsByUserId(@Param("id") Integer id);

    @Select("select groupid from ss_f_user_group where userid = #{id} and ststate = 1")
    Integer getGroupIdByUserId(@Param("id") Integer id);

    @Select("select * from ss_f_user_project where groupid = #{groupid}")
    List<SsFUserGroup> getAllByGroupId(@Param("groupid")Integer groupid);

    @Select("select groupid from ss_f_user_project where userid = #{id}")
    List<Integer> getGroupsById(@Param("id") Integer id);

    @Select("select id from ss_f_projects where grouplevel = 4 and parentid = 3 and ststate = 1")
    List<Integer> getAllGroups();

    @Select("select id from ss_f_projects where grouplevel = 4 and parentid = #{projectId} and ststate = 1")
    List<Integer> getAllGroupsByProjectId(@Param("projectId") Integer projectid);

    List<SsFUsers> getUserByGroupsIds(@Param("list")List<Integer> ids);

    @Select("select a.id,a.username,a.name,a.usercode,a.sttime,a.ststate,a.storder " +
            " from ss_f_user_group b left join ss_f_users a on a.id = b.userid " +
            " where b.groupid = #{id}")
    List<SsFUsers> getUserByGroupsId(@Param("id") Integer id);

    @Select("select userid from ss_f_user_group where groupid = #{id}")
    List<Integer> getUserIdsByGroupsIds(@Param("id") Integer id);

    @Select("SELECT d.projectid FROM zj_f_groups_projects d " +
            "LEFT JOIN ss_f_projects c ON d.GROUPID = c.ID " +
            "LEFT JOIN ss_f_projects b on c.PARENTID = b.id " +
            "LEFT JOIN ss_f_user_project a on a.GROUPID = b.id " +
            "where a.USERID = #{userid} and d.PROJECTTYPE is NOT NULL")
    List<String> getProjectsByUserId(@Param("userid")Integer userid);

    @Select("select " +
            "  a.id, a.username, a.name, a.usercode, a.ugid, " +
            "  b.id as roleid, b.name as rolename, b.code as rolecode, " +
            "  b.parentid, b.type, b.rolelevel, b.visible " +
            " from ss_f_users a " +
            "left join ss_f_user_role c on a.id = c.userid " +
            "left join ss_f_roles b on c.roleid = b.id " +
            " where a.id in (select userid from ss_f_user_group where groupid = " +
            " (select groupid from ss_f_user_group where userid = #{userid}))")
    List<UserRolesDTO> getUsersByUserid(@Param("userid")Integer userid);

    @Select("select id from ss_f_projects where PARENTID = #{id}")
    List<Integer> getChildIdByProjectId(Integer projectId);

    int deleteProjectsByUser(@Param("userid") Integer userid,
                             @Param("list")List<Integer> projects);

    @Select("select a.PROJECTID from zj_f_groups_projects a " +
            " left join ss_f_projects b on a.GROUPID = b.id " +
            " left join ss_f_projects c on b.parentid = c.id " +
            " where c.id in (select groupid from ss_f_user_project " +
            " where userid = #{userid}) group by a.PROJECTID")
    List<String> getGroupProjectsByUserId(@Param("userid")Integer userid);

    @Select("select a.id,a.username,a.name,a.sttime,a.ststate,a.storder " +
            " from ss_f_users a " +
            " left join ss_f_user_role b on a.id = b.userid " +
            " left join ss_f_roles c on b.roleid = c.id " +
            " left join ss_f_roles d on c.PARENTID = d.id " +
            " where d.code = 'quanzijihe' ")
    List<SsFUsers> getQZUsers();

    @Select("select a.id,a.username,a.name,a.sttime,a.ststate,a.storder " +
            " from ss_f_users a " +
            " left join ss_f_user_role b on a.id = b.userid " +
            " left join ss_f_roles c on b.roleid = c.id " +
            " left join ss_f_roles d on c.PARENTID = d.id " +
            " where d.code = 'jiashedanweijihe' ")
    List<SsFUsers> getJSDWUsers();

    @Select("select count(a.id) from ss_f_users a " +
            " left join ss_f_user_group b on a.id = b.userid " +
            " left join ss_f_groups c on b.groupid = c.id " +
            " where a.ststate = 1 and c.id = #{groupId}")
    int getGroupCount(@Param("groupId")Integer groupId);

    Integer getAllCount(@Param("list") List<Integer> groupIds);

    List<ClockInCensusReturn> getCensusList(@Param("list")List<Integer> units);

    @Select("select a.id from ss_f_users a " +
            " left join ss_f_user_group b on a.id = b.userid " +
            " left join ss_f_groups c on b.groupid = c.id " +
            " where c.id = #{groupId}")
    List<Integer> getAllIds(Integer groupId);

    Integer getAllUserCountByGroupIds(@Param("list") List<Integer> groupIds);

    Integer getAllUserOnDutyByGroupIds(@Param("list") List<Integer> groupIds);
}
