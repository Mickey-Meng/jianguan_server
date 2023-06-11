package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.jianguan.common.domain.entity.SsFRoles;
import com.ruoyi.jianguan.common.domain.dto.RoleDataDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFUserRoleDAO {

    @Select("select sur.role_id as roileid,sur.user_id as userid from sys_user_role sur where sur.user_id= #{userid}")
    SsFUserRole getByUserid(@Param("userid")Integer userid);
    /*
    @Select("select * from ss_f_user_role where userid = #{userid}")
    SsFUserRole getByUserid(@Param("userid")Integer userid);
    */

    List<RoleDataDTO> getRoleByUserids(@Param("list") List<Integer> ids);

    List<Integer> getRolesIdByUserids(@Param("list") List<Integer> ids);

    @Select("select su.user_id as id," +
            "su.user_name as  username," +
            "su.nick_name as NAME," +
            "su.user_name as usercode," +
            "su.create_time as sttime," +
            "su.`status` as ststate," +
            "0 as  storder," +
            "null as ugid " +
            "from sys_user su LEFT JOIN  sys_user_role sur on su.user_id= sur.user_id" +
            "where sur.role_id = #{roleid}")
    List<SsFUsers> getUsersByRoleid(@Param("roleid")Integer roleid);
/*
    @Select("select a.id, a.username, a.name,a.usercode, " +
            "a.sttime, a.ststate, a.storder, a.ugid " +
            " from ss_f_users a left join ss_f_user_role b " +
            " on a.id = b.userid where b.roleid = #{roleid}")
    List<SsFUsers> getUsersByRoleid(@Param("roleid")Integer roleid);
*/

    @Select("SELECT" +
            " sr.role_id AS id," +
            " sr.role_name AS NAME," +
            " sr.role_key AS CODE," +
            " sr.parent_id AS parentid," +
            " 1 AS type," +
            " sr.role_level AS rolelevel," +
            " sr.create_time AS sttime," +
            " sr.`status` AS STSTATE " +
            " FROM" +
            " sys_role sr" +
            " LEFT JOIN sys_user_role sur ON sr.role_id = sur.role_id " +
            " WHERE" +
            " sur.user_id= #{id}")
    SsFRoles getRolesByUserid(@Param("id")Integer id);
/*

    @Select("select a.* from ss_f_roles a left join ss_f_user_role b " +
            "on a.id = b.roleid where b.userid = #{id}")
    SsFRoles getRolesByUserid(@Param("id")Integer id);
*/

    @Select("select * from ss_f_roles where ststate = 1")
    List<SsFRoles> getAllRoles();

    @Select("select role_key from sys_role where role_id = #{parentId}")
    String getParentCodeByRoleId(@Param("parentId")Integer parentId);

/*
    @Select("select code from ss_f_roles where id = #{parentId}")
    String getParentCodeByRoleId(@Param("parentId")Integer parentId);*/
    List<SsFRoles> getRolesByGroupIds(@Param("list")List<Integer> groupIds);

    @Update("update ss_f_user_role set roleid = #{roleid} where userid = #{userid}")
    void updateRoleByUserId(@Param("roleid")Integer roleid,
                            @Param("userid")Integer userid);

    @Delete("delete from ss_f_user_role where userid = #{userid} and roleid = #{roleid}")
    void deleteByUserId(@Param("roleid")Integer roleid,
                        @Param("userid")Integer userid);

    @Select("select b.role_id as id from sys_role a " +
            "            left join sys_role b on a.role_id = b.parent_id" +
            "            where a.role_key = 'shigongjihe'")
    List<Integer> getShiGongRoleIds();

   /*
      modify by yangaogao  20230421  修改为ruoyi的系统表
   @Select("select b.id from ss_f_roles a " +
            " left join ss_f_roles b on a.id = b.PARENTID " +
            " where a.code = 'shigongjihe'")
    List<Integer> getShiGongRoleIds();*/

    @Select(" select role_id as id from sys_role where role_key in('sg','sgy')  ")
    List<Integer> getOldShiGongRoleIds();

 /*
    modify by yangaogao  20230421  修改为ruoyi的系统表
    @Select("select id from ss_f_roles where code = 'sg' or 'sgy'")
    List<Integer> getOldShiGongRoleIds();*/

}
