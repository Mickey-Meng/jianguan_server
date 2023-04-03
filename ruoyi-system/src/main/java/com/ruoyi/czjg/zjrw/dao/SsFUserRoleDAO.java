package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.czjg.zjrw.domain.dto.RoleDataDTO;
import com.ruoyi.czjg.zjrw.domain.entity.SsFRoles;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFUserRoleDAO {

    @Select("select * from ss_f_user_role where userid = #{userid}")
    SsFUserRole getByUserid(@Param("userid")Integer userid);

    List<RoleDataDTO> getRoleByUserids(@Param("list") List<Integer> ids);

    List<Integer> getRolesIdByUserids(@Param("list") List<Integer> ids);

    @Select("select a.id, a.username, a.name,a.usercode, " +
            "a.sttime, a.ststate, a.storder, a.ugid " +
            " from ss_f_users a left join ss_f_user_role b " +
            " on a.id = b.userid where b.roleid = #{roleid}")
    List<SsFUsers> getUsersByRoleid(@Param("roleid")Integer roleid);

    @Select("select a.* from ss_f_roles a left join ss_f_user_role b " +
            "on a.id = b.roleid where b.userid = #{id}")
    SsFRoles getRolesByUserid(@Param("id")Integer id);

    @Select("select * from ss_f_roles where ststate = 1")
    List<SsFRoles> getAllRoles();

    @Select("select code from ss_f_roles where id = #{parentId}")
    String getParentCodeByRoleId(@Param("parentId")Integer parentId);

    List<SsFRoles> getRolesByGroupIds(@Param("list")List<Integer> groupIds);

    @Update("update ss_f_user_role set roleid = #{roleid} where userid = #{userid}")
    void updateRoleByUserId(@Param("roleid")Integer roleid,
                            @Param("userid")Integer userid);

    @Delete("delete from ss_f_user_role where userid = #{userid} and roleid = #{roleid}")
    void deleteByUserId(@Param("roleid")Integer roleid,
                        @Param("userid")Integer userid);

    @Select("select b.id from ss_f_roles a " +
            " left join ss_f_roles b on a.id = b.PARENTID " +
            " where a.code = 'shigongjihe'")
    List<Integer> getShiGongRoleIds();

    @Select("select id from ss_f_roles where code = 'sg' or 'sgy'")
    List<Integer> getOldShiGongRoleIds();

}
