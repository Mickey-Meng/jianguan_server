package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFGroupsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SsFGroups record);

    int insertSelective(SsFGroups record);

    SsFGroups selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsFGroups record);

    int updateByPrimaryKey(SsFGroups record);

    @Select("select * from ss_f_groups where PARENTID = #{id}")
    List<SsFGroups> getByParentId(@Param("id") Integer id);


    List<String> getProjectCode(List<Integer> list);

    @Select("select c.* from zj_f_groups_projects  a left join  " +
            "conponent b" +
            " on b.projectcode = a.projectid   " +
            "left join    " +
            "ss_f_groups c    " +
            "on c.id = a.groupid    " +
            "where b.id =#{conponentid}" )
    SsFGroups getGroup(@Param("conponentid") Integer conponentid);


    @Select("select DISTINCT b.* from ss_f_user_group a   " +
            "left join ss_f_users b   " +
            "on a.USERID = b.id    " +
            "left join ss_f_user_role c   " +
            "on c.userid = b.id   " +
            "where   c.roleid = 6 ")
    List<SsFUsers> getCheckByGongQi();


    List<SsFGroups> getSsFGroups(List<String> projects);


    @Select("select * from ss_f_groups where name = #{name}")
    SsFGroups getGroupInit(@Param("name") String name);

    @Select("select a.* from ss_f_groups  a  " +
            "  left join zj_f_groups_projects b  " +
            "  on a.id = b.GROUPID " +
            "  where a.PARENTID = #{parentid} " +
            "  and b.PROJECTTYPE = #{type} ")
    List<SsFGroups> getByParentIdType(@Param("parentid") Integer parentid, @Param("type") String type);

    @Select("select * from ss_f_groups where grouplevel in (1,2,3)")
    List<SsFGroups> getGroupsByLevel();

    @Select("select * from ss_f_groups where name = #{name}")
    SsFGroups getByName(@Param("name") String name);

    @Select("select count(1) from ss_f_groups where PARENTID = #{parentid}")
    Integer getCountByParentId(@Param("parentid")Integer parentid);

    @Select("select " +
            "DISTINCT a.* " +
            "from ss_f_users a " +
            "left join ss_f_user_group b on a.id = b.userid " +
            "left join ss_f_groups d on b.groupid = d.id " +
            "left join ss_f_user_role c on c.userid = a.id " +
            "where c.roleid = 6 and d.id = #{groupId}")
    List<SsFUsers> getCheckByGongQu(@Param("groupId") Integer gongqu);
}
