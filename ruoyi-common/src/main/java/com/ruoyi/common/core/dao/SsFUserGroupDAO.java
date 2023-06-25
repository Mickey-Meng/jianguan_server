package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.dto.UserRolesDTO;
import com.ruoyi.common.core.domain.entity.ClockInCensusReturn;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFUserGroup;
import com.ruoyi.common.core.domain.entity.SsFUsers;
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

    @Select("select * from ss_f_user_group where userid =  #{userid}" )
    SsFUserGroup getGroup(@Param("userid") Integer id);

    @Select("select * from ss_f_user_group where userid =  #{userid} and ststate = 1" )
    List<SsFUserGroup> getGroups(@Param("userid") Integer id);

    // yangaogao ,排查没有地方用到这个方法
    @Select("select * from ss_f_groups where grouplevel = 3")
    List<SsFGroups> getall();
    @Select("select * from ss_f_groups where grouplevel = 4 and parentid = #{id}")
    List<SsFGroups> getallGq(@Param("id") Integer id);

    Integer deleteByUserIds(@Param("list") List<Integer> list);


    @Select("select groupid from ss_f_user_project where userid = #{id} and ststate = 1")
    List<Integer> getGroupsByUserId(@Param("id") Integer id);
    @Select("select * from ss_f_user_project where groupid = #{groupid}")
    List<SsFUserGroup> getAllByGroupId(@Param("groupid")Integer groupid);

    @Select("select groupid from ss_f_user_group where userid = #{id}")
    List<Integer> getGroupsById(@Param("id") Integer id);

    @Select("select groupid from ss_f_user_group where ststate = 1")
    List<Integer> getAllGroups();


    /**
     * getGroup -> getUserGroupOfProject
     */
    @Select("select * from ss_f_user_project where userid =  #{userid}" )
    SsFUserGroup getUserGroupOfProject(@Param("userid") Integer id);
    /**
     * getGroups -> getUserGroupsOfProject
     */
    @Select("select * from ss_f_user_project where userid =  #{userid} and ststate = 1" )
    List<SsFUserGroup> getUserGroupsOfProject(@Param("userid") Integer id);
    /**
     * getall -> getAllUserGroup
     */
    @Select("select * from ss_f_projects where grouplevel = 4")
    List<SsFGroups> getAllUserGroup();
    /**
     * getallGq -> getAllUserGroupGq
     */
    @Select("select * from ss_f_projects where grouplevel = 5 and parentid = #{id}")
    List<SsFGroups> getAllUserGroupGq(@Param("id") Integer id);

    /**
     * getGroupsByUserId -> getGroupsOfProjectByUserId
     */
    @Select("select groupid from ss_f_user_project where userid = #{id} and ststate = 1")
    List<Integer> getGroupsOfProjectByUserId(@Param("id") Integer id);
    /**
     * getGroupIdByUserId -> getGroupIdByUserId
     */
    @Select("select su.dept_id from sys_user su where su.`status`=1 and su.user_id = #{id} ")
    Integer getGroupIdByUserId(@Param("id") Integer id);
    /**
     * getAllByGroupId -> getAllOfProjectByGroupId
     */
    @Select("select * from ss_f_user_project where groupid = #{groupid}")
    List<SsFUserGroup> getAllOfProjectByGroupId(@Param("groupid")Integer groupid);
    /**
     *  getGroupsById -> getAllOfProjectByGroupId
     */
    @Select("select groupid from ss_f_user_project where userid = #{id}")
    List<Integer> getGroupsOfProjectById(@Param("id") Integer id);

    /**
     * getAllGroups -> getAllGroupsOfProject
     */
    @Select("select id from ss_f_projects where grouplevel = 4 and parentid = 3 and ststate = 1")
    List<Integer> getAllGroupsOfProject();
    /**
     *  getAllGroupsByProjectId -> getAllOfProjectByGroupId
     */
    @Select("select id from ss_f_projects where grouplevel = 4 and parentid = #{projectId} and ststate = 1")
    List<Integer> getAllGroupsByProjectId(@Param("projectId") Integer projectid);

    List<SsFUsers> getUserByGroupsIds(@Param("list")List<Integer> ids);
    /**
     *  -> getAllOfProjectByGroupId
     */
    @Select("SELECT" +
            "su.user_id AS id," +
            "su.user_name AS username," +
            "su.`password` AS pwd," +
            "su.nick_name AS NAME," +
            "su.create_time AS sttime," +
            "su.`status` AS ststate " +
            "FROM " +
            "sys_user su " +
            "WHERE " +
            "su.dept_id =  #{id}")
    List<SsFUsers> getUserByGroupsId(@Param("id") Integer id);
/*

    @Select("select a.id,a.username,a.name,a.usercode,a.sttime,a.ststate,a.storder " +
            " from ss_f_user_group b left join ss_f_users a on a.id = b.userid " +
            " where b.groupid = #{id}")
    List<SsFUsers> getUserByGroupsId(@Param("id") Integer id);
*/

    /**
     *  -> getAllOfProjectByGroupId
     */
    @Select("select su.user_id from sys_user su where  su.`status`=1  and su.dept_id = #{id}")
    List<Integer> getUserIdsByGroupsIds(@Param("id") Integer id);
    /**
     *  -> getAllOfProjectByGroupId
     */
    @Select("SELECT d.projectid FROM zj_f_groups_projects d " +
        "LEFT JOIN ss_f_projects c ON d.GROUPID = c.ID " +
        "LEFT JOIN ss_f_projects b on c.PARENTID = b.id " +
        "LEFT JOIN ss_f_user_project a on a.GROUPID = b.id " +
        "where a.USERID = #{userid} and d.PROJECTTYPE is NOT NULL")
    List<String> getProjectsByUserId(@Param("userid")Integer userid);

    @Select("SELECT a.user_id as id, a.user_name as username, a.nick_name as NAME, a.user_id as usercode,  " +
            " null as ugid, b.role_id AS roleid, b.role_name AS rolename, b.role_key AS rolecode,  " +
            " b.PARENT_ID as parentid, 1 as type, b.role_level as rolelevel, b.STATUS as visible   " +
            "FROM sys_user a  " +
            " LEFT JOIN sys_user_role c ON a.user_id = c.user_id  " +
            " LEFT JOIN sys_role b ON c.role_id = b.role_id   " +
            "WHERE  " +
            " a.user_id IN (  " +
            " SELECT user_id FROM sys_user su,ss_f_user_project  sfu ,ss_f_projects sfp  " +
            "WHERE su.user_id = sfu.USERID and sfu.GROUPID = sfp.ID and sfp.PARENTID= #{projectId} and  " +
            "su.dept_id = ( SELECT s.dept_id FROM sys_user s WHERE user_id =  #{userid} )) ")
    /*" SELECT  user_id  FROM  sys_user  WHERE  dept_id = (  " +
            "  SELECT s.dept_id FROM  sys_user s  WHERE user_id =  #{userid}))")
    */
    List<UserRolesDTO> getUsersByUserid(@Param("userid")Integer userid,@Param("projectId")Integer projectId);
/*

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
*/

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

    @Select("select a.PROJECTID from zj_f_groups_projects a " +
        " left join ss_f_projects b on a.GROUPID = b.id " +
        " left join ss_f_projects c on b.parentid = c.id " +
        " left join ss_f_projects d on c.parentid = d.id " +
        " where c.id in (select groupid from ss_f_user_project " +
        " where userid = #{userid}  and d.id=  #{projectId} )    group by a.PROJECTID")
    List<String> getGroupProjectsByUserIdAndProjectId(@Param("userid")Integer userid,@Param("projectId")Integer projectId);

    @Select("select su.user_id as id,su.user_name as username,su.nick_name as name,   " +
            "  su.create_time as sttime,  su.`status` as ststate  from sys_user su    " +
            "  LEFT JOIN sys_user_role sur   on su.user_id = sur.user_id   " +
            "  LEFT JOIN sys_role sr1 on sur.role_id = sr1.role_id   " +
            "  LEFT JOIN sys_role sr2 on sr1.parent_id = sr2.role_id   " +
            "where sr2.role_key = 'quanzijihe' ")
    List<SsFUsers> getQZUsers();
/*

    @Select("select a.id,a.username,a.name,a.sttime,a.ststate,a.storder " +
        " from ss_f_users a " +
        " left join ss_f_user_role b on a.id = b.userid " +
        " left join ss_f_roles c on b.roleid = c.id " +
        " left join ss_f_roles d on c.PARENTID = d.id " +
        " where d.code = 'quanzijihe' ")
    List<SsFUsers> getQZUsers();
*/

    @Select("select su.user_id as id,su.user_name as username,su.nick_name as name,   " +
            "  su.create_time as sttime,  su.`status` as ststate  from sys_user su    " +
            "  LEFT JOIN sys_user_role sur   on su.user_id = sur.user_id   " +
            "  LEFT JOIN sys_role sr1 on sur.role_id = sr1.role_id   " +
            "  LEFT JOIN sys_role sr2 on sr1.parent_id = sr2.role_id   " +
            "where sr2.role_key =  'jiashedanweijihe' ")
    List<SsFUsers> getJSDWUsers();
    /*
    @Select("select a.id,a.username,a.name,a.sttime,a.ststate,a.storder " +
        " from ss_f_users a " +
        " left join ss_f_user_role b on a.id = b.userid " +
        " left join ss_f_roles c on b.roleid = c.id " +
        " left join ss_f_roles d on c.PARENTID = d.id " +
        " where d.code = 'jiashedanweijihe' ")
    List<SsFUsers> getJSDWUsers();*/

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
