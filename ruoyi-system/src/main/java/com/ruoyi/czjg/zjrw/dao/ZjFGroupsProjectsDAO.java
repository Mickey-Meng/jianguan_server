package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.dto.TypeNameAndCode;
import com.ruoyi.czjg.zjrw.domain.entity.ZjFGroupsProjects;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjFGroupsProjectsDAO {
    int insert(ZjFGroupsProjects record);

    int insertSelective(ZjFGroupsProjects record);

    List<ZjFGroupsProjects> getProjectByUserid(Integer id);

    List<ZjFGroupsProjects> getDeatail(List<String> list);

    List<ZjFGroupsProjects> getProjectCode(@Param("list") List<Integer> list);


    @Select(" select * from zj_f_groups_projects where PROJECTID is not null and PROJECTTYPE is not null")
    List<ZjFGroupsProjects> getAll();


    @Select(" select conponenttype, conponenttypename from zj_conponent_producetime where projecttype = #{type}" +
            " GROUP BY conponenttype, conponenttypename")
    List<TypeNameAndCode> getTypeNameAndCode(@Param("type") String type);


    @Select("select * from zj_f_groups_projects where groupid = #{groupid}")
    ZjFGroupsProjects getProjectCodeByProjectId(@Param("groupid")Integer id);

    List<ZjFGroupsProjects> getProjectCodeByProjectIds(@Param("list")List<String> list);

    @Delete("truncate  table zj_f_groups_projects ")
    void deleteAll();

    List<String> getByGonquId(@Param("gongquid") Integer gongquid);

    @Select("select GROUPID from zj_f_groups_projects where PROJECTID = #{project}")
    Integer getGroupidByProjectid(@Param("project") String project);

    @Select("select * from zj_f_groups_projects_copy where GROUPID = #{groupid}")
    ZjFGroupsProjects getCopyAllByGroupid(@Param("groupid") Integer groupid);

    @Select("select * from zj_f_groups_projects where PROJECTNAME = #{name}")
    ZjFGroupsProjects getProjectByProjectName(@Param("name")String name);

    @Update("update zj_f_groups_projects set GROUPID = #{id} where PROJECTNAME = #{name}")
    void updateGroupId(@Param("id")Integer id,
                       @Param("name")String name);
}
