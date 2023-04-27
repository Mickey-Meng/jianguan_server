package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFProjects;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.jianguan.common.domain.dto.ProjectGroupUserTree;
import com.ruoyi.jianguan.common.domain.dto.SsFProjectsTree;
import com.ruoyi.jianguan.common.domain.entity.SsFCompany;
import com.ruoyi.jianguan.common.domain.entity.SsFProjectCompany;
import com.ruoyi.jianguan.common.domain.dto.ProjectsDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/16 16:17
 * @Version : 1.0
 * @Description :
 **/
@Mapper
@Repository
public interface ProjectsDAO {

    @Select("select LAST_INSERT_ID()")
    Integer getId();

    Integer addCompany(SsFCompany ssFCompany);

    @Select("select count(1) from ss_f_company where id = #{id}")
    Integer getCompanyNumById(@Param("id")Integer id);

    @Delete("delete from ss_f_company where id = #{id}")
    Integer deleteCompanyById(@Param("id")Integer id);

    Integer updateCompany(SsFCompany ssFCompany);

    @Select("select * from ss_f_company")
    List<SsFCompany> getAllCompany();

    Integer addProject(ProjectsDTO projectsDTO);

    @Select("select count(1) from ss_f_projects where id = #{id}")
    Integer getCountById(@Param("id")Integer id);

    @Select("select count(1) from ss_f_roles where id = #{id}")
    Integer getRoleCountById(@Param("id")Integer id);

    @Select("select count(1) from ss_f_users where id = #{id}")
    Integer getUserCountById(@Param("id")Integer id);

    @Select("select count(1) from ss_f_user_role where userid = #{id}")
    Integer getRoleCountByUserId(@Param("id") Integer id);

    @Select("select companyid from ss_f_project_company where projectid = #{projectid} and state = 1")
    List<Integer> getCompanyidsByProjectId(@Param("projectid")Integer projectid);

    List<SsFCompany> getCompnayByIds(@Param("list") List<Integer> ids);

    @Delete("delete from ss_f_projects where id = #{id}")
    Integer deleteProjectById(@Param("id")Integer id);

    @Select("SELECT * FROM `ss_f_projects` WHERE id =(SELECT PARENTID FROM `ss_f_projects`  WHERE id =#{gongQuId})")
    SsFProjectsTree getSsFProjectsTreeByGongQuId(@Param("gongQuId")Integer gongQuId);

    Integer updateProjects(ProjectsDTO projectsDTO);

    @Select("select * from ss_f_projects where ststate = 1")
    List<SsFProjects> getAll();

    @Select("select * from ss_f_projects where grouplevel in (2, 3)")
    List<SsFProjectsTree> getALlProject();


    List<SsFProjectsTree> getALlProjectByIds(@Param("list")List<Integer> ids);

    @Select("select * from ss_f_projects where name = #{name}")
    SsFProjects getByName(@Param("name")String name);

    @Select("select * from ss_f_projects where id = #{id}")
    SsFProjects getProjectById(@Param("id")Integer id);

    @Select("select * from ss_f_projects where ststate = 1 and GROUPLEVEL = 3")
    List<SsFProjects> getAllProjects();

    @Select("select * from ss_f_projects where ststate = 1 and GROUPLEVEL = 5")
    List<SsFProjects> getAllSingleProjects();

    @Select("select dept_id from sys_project_dept where project_id = #{id}")
    List<Integer>  getGroupIdsById(@Param("id")Integer id);


    /*
     yangaogao #23 20230426
    @Select("select groupid from ss_f_projects where ststate = 1 and id = #{id}")
    String getGroupIdsById(@Param("id")Integer id);
    */


    List<SsFGroups> getGroupInfosByIds(@Param("list") List<Integer> ids);

    List<SsFUsers> getUserInfosByGroupsIds(@Param("list") List<Integer> ids);

    Integer addProjectCompany(@Param("list") List<SsFProjectCompany> company);

    @Select("select count(1) from ss_f_projects where GROUPLEVEL = 2")
    Integer getNumByLevel2();

    @Select("select count(1) from ss_f_projects where GROUPLEVEL = 3 and PARENTID = #{id}")
    Integer getNumLevel3ByParentId(@Param("id")Integer id);

    @Select("select code from ss_f_projects where id = #{id}")
    String getCodeById(@Param("id")Integer id);

    @Select("select name from ss_f_projects where id = #{id}")
    String getNameById(@Param("id")Integer id);

    @Select("select a.id from ss_f_projects a left join ss_f_projects b " +
            " on a.id = b.parentid where b.id = #{id}")
    Integer getIdByChildId(@Param("id")Integer id);

    @Select("select a.name from ss_f_projects a left join ss_f_projects b " +
            " on a.id = b.parentid where b.id = #{id}")
    String getNameByChildId(@Param("id")Integer id);

    @Delete("delete from ss_f_project_company where projectid = #{id}")
    void deleteProjectCompanyById(@Param("id")Integer id);

    List<ProjectGroupUserTree> getGroups(@Param("list")List<Integer> ids);

    @Select("select * from ss_f_groups where grouplevel = #{level}")
    ProjectGroupUserTree getGroupsLevel(@Param("level")Integer level);

    List<ProjectGroupUserTree> getGroupsLevel3(@Param("list")List<Integer> id);

    @Select("select * from ss_f_projects where parentid = #{id}")
    List<SsFProjects> getChildProjectById(@Param("id")Integer id);

    @Select("select a.typecode from ss_f_company a left join ss_f_project_company b " +
            " on a.id = b.companyid where b.projectid = #{id}")
    List<String> getTypeCodeByProjectId(@Param("id")Integer id);

    @Select("select * from ss_f_company where typecode = #{typecode}")
    List<SsFCompany> getAllCompanyByTypecode(@Param("typecode")String typecode);

    @Select("select * from ss_f_projects where parentid = #{projectId}")
    List<SsFProjects> getAllGongQuByProjectId(@Param("projectId")Integer projectId);

    @Select("select groupid from ss_f_user_project where userid = #{userId} and ststate = 1")
    List<Integer> getGongQusByUserId(@Param("userId")Integer userId);

    @Select("select grouplevel from ss_f_projects where id = #{id}")
    Integer getProjectLevelById(@Param("id")Integer id);

    @Select("select c.id " +
            " from ss_f_projects c " +
            " left join ss_f_projects b on b.id = c.parentid " +
            " left join ss_f_projects a on a.id = b.parentid " +
            " where a.id = #{id}")
    List<Integer> getProjectChild(@Param("id")Integer id);

    @Select("select b.id " +
            " from ss_f_projects b left join " +
            " ss_f_projects a on b.parentid = a.id" +
            " where a.id = #{id}")
    List<Integer> getProjectsChild(@Param("id")Integer id);

    List<String> getChildCode(@Param("id")Integer id);

    @Select("select a.id from ss_f_projects a " +
            " left join ss_f_projects b on a.id = b.parentid " +
            " left join zj_f_groups_projects c on b.id = c.groupid " +
            " left join conponent d on c.projectid = d.projectcode " +
            " where d.conponentcode = #{code}")
    Integer getProjectIdByCode(@Param("code")String code);

    @Select("select a.* from ss_f_projects a " +
            " left join ss_f_projects b on a.id = b.parentid " +
            " left join ss_f_user_project c on b.id = c.GROUPID " +
            " where c.USERID = #{userId} group by a.name")
    List<SsFProjects> getSectionProjectsIdByUserId(@Param("userId")Integer userId);

    @Select("select a.PROJECTID from zj_f_groups_projects a " +
            " left join ss_f_projects b on a.GROUPID = b.id " +
            " left join ss_f_projects c on b.PARENTID = c.id " +
            " where c.id = #{id}")
    List<String> getProjectCodes(Integer id);

    @Select("select parentid from ss_f_projects where id = #{id}")
    Integer getParentId(Integer id);
}
