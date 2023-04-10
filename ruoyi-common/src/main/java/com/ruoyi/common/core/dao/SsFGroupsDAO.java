package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFProjects;
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
    /**
     * zhuji_server原代码
     *
    @Select("select * from ss_f_projects where PARENTID = #{id}")
    List<SsFProjects> getByParentId(@Param("id") Integer id);
*/


    List<String> getProjectCode(List<Integer> list);

    @Select("select c.* from zj_f_groups_projects  a left join  " +
            "conponent b" +
            " on b.projectcode = a.projectid   " +
            "left join    " +
            "ss_f_groups c    " +
            "on c.id = a.groupid    " +
            "where b.id =#{conponentid}" )
    SsFGroups getGroup(@Param("conponentid") Integer conponentid);

    /**
     * zhuji_server原代码
    @Select("select c.* from zj_f_groups_projects  a left join  " +
        "conponent b" +
        " on b.projectcode = a.projectid   " +
        "left join    " +
        "ss_f_projects c    " +
        "on c.id = a.groupid    " +
        "where b.id =#{conponentid}" )
    SsFGroups getGroup(@Param("conponentid") Integer conponentid);
**/
    List<SsFUsers> getCheckByGongQi(@Param("projectId")Integer projectId);


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

    @Select("select a.* from ss_f_projects  a  " +
        "  left join zj_f_groups_projects b  " +
        "  on a.id = b.GROUPID " +
        "  where a.PARENTID = #{parentid} " +
        "  and b.PROJECTTYPE = #{type} ")
    List<SsFGroups> getByParentIdType(@Param("parentid") Integer parentid, @Param("type") String type);

/**
 * zhuji_server原代码
 *
    @Select("select * from ss_f_projects where name = #{name}")
    SsFGroups getGroupInit(@Param("name") String name);

    @Select("select a.* from ss_f_projects  a  " +
        "  left join zj_f_groups_projects b  " +
        "  on a.id = b.GROUPID " +
        "  where a.PARENTID = #{parentid} " +
        "  and b.PROJECTTYPE = #{type} ")
    List<SsFGroups> getByParentIdType(@Param("parentid") Integer parentid, @Param("type") String type);
    **/




    @Select("select * from ss_f_groups where grouplevel in (1,2,3)")
    List<SsFGroups> getGroupsByLevel();

    @Select("select * from ss_f_groups where name = #{name}")
    SsFGroups getByName(@Param("name") String name);

    /**
     * zhuji_server原代码
     */
    @Select("select * from ss_f_projects where name = #{name} and parentid = #{groupId}")
    SsFGroups getByName(@Param("name") String name,
                        @Param("groupId")Integer groupId);
    @Select("select count(1) from ss_f_groups where PARENTID = #{parentid}")
    Integer getCountByParentId(@Param("parentid")Integer parentid);

    /**
     * zhuji_server原代码
     *
     @Select("select * from ss_f_projects where grouplevel in (1,2,3)")
     List<SsFGroups> getGroupsByLevel();

     @Select("select count(1) from ss_f_projects where PARENTID = #{parentid}")
     Integer getCountByParentId(@Param("parentid")Integer parentid);

     */

    @Select("select " +
            "DISTINCT a.* " +
            "from ss_f_users a " +
            "left join ss_f_user_group b on a.id = b.userid " +
            "left join ss_f_groups d on b.groupid = d.id " +
            "left join ss_f_user_role c on c.userid = a.id " +
            "where c.roleid = 6 and d.id = #{groupId}")
    List<SsFUsers> getCheckByGongQu(@Param("groupId") Integer gongqu);


    /**
     * 以下都是zhuji_server原代码
     * @param gongqu
     * @param projectId
     * @return
     */
    @Select("select a.id, a.username, a.name, a.usercode, a.sttime, a.ugid " +
        " from ss_f_users a " +
        " left join ss_f_user_project b on a.id = b.userid " +
        " left join ss_f_projects d on b.groupid = d.id " +
        " left join ss_f_user_role c on c.userid = a.id " +
        " left join ss_f_roles e on c.roleid = e.id " +
        " left join ss_f_roles f on e.parentid = f.id   " +
        " left join ss_f_projects g on d.parentid = g.id" +
        " where (e.code = 'jl' or e.code = 'jianliyuan' or f.code = 'jianlijihe') and d.id = #{groupId}" +
        " and g.id = #{projectId} group by a.username, a.name")
    List<SsFUsers> getCheckByGongQu(@Param("groupId") Integer gongqu,
                                    @Param("projectId")Integer projectId);

    @Select("select id from ss_f_projects where parentid = #{parentid}")
    List<Integer> getIdByParentid(@Param("parentid") Integer parentid);

    @Select("select PROJECTPIC from ss_f_projects where PROJECTPIC is not null")
    List<String> getProjectPic();

    @Select("select b.id from ss_f_projects b left join ss_f_projects a on b.parentid = a.id " +
        " where a.id = #{id}")
    List<Integer> getGroupsById(@Param("id")Integer id);

    @Select("select * from ss_f_projects where id = #{id}")
    SsFProjects getProjectById(Integer id);

    /**
     * 通过id查询组织结构表
     * @param id
     * @return
     */
    @Select("select * from ss_f_groups where id = #{id}")
    SsFGroups getGroupInfoById(Integer id);
}
