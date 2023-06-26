package com.ruoyi.jianguan.business.contract.dao;

import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.jianguan.common.domain.dto.PersonGroupTree;
import com.ruoyi.jianguan.common.domain.entity.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/9 13:42
 * @Version : 1.0
 * @Description :
 **/
@Mapper
@Repository
public interface PersonDAO {

    @Select("select peoplePic from zj_person_people_sub where peoplePic is not null")
    List<String> getPeoPlePics();

    @Select("SELECT LAST_INSERT_ID();")
    Integer getInsertId();

    @Delete("delete from ss_f_person_groups where id = #{id}")
    Integer deleteDepartment(@Param("id") Integer id);

    @Select("select count(1) from ss_f_person_user_group_role where userid = #{userid}")
    Integer getUserGroupByUserid(@Param("userid") Integer userid);

    @Delete("delete from ss_f_person_user_group_role where userid = #{userid}")
    Integer deleteUserGroup(@Param("userid") Integer userid);

    Integer bulkInsertSsFGroups(@Param("ssFGroups") List<SsFPersonGroups> ssFGroups);

    Integer updateDepartment(SsFPersonGroups ssFGroups);

    @Select("select * from ss_f_person_groups where status = 1")
    List<PersonGroupTree> getSsFGroups();

    Integer insertUserGroup(PersonUserGroupRole ssFGroups);

    @Select("select count(1) from ss_f_user_project where userid = #{userid}")
    Integer getGroupidByUserid(@Param("userid")Integer userid);

    @Select("select a.* from ss_f_projects a left join ss_f_user_project b on " +
            "a.id = b.GROUPID where b.userid = #{userid}")
    List<SsFGroups> getGroupByUserid(@Param("userid")Integer userid);

    Integer newContract(PersonDTO person);

    @Select("select * from zj_person where id = #{id}")
    PersonDTO selectByPrimaryKey(Integer id);

    @Delete("delete from zj_person where id = #{id} and projectId = #{projectId}")
    void delContractById(@Param("id")Integer id,
                         @Param("projectId")Integer projectId);

    Integer updateContract(PersonDTO person);

    void updateContractPersons(PersonSub personSubs);

    @Select("select * from zj_person")
    List<PersonDTO> getAllPerson();

    @Select("select * from zj_person where projectId = #{projectId} order by subDate desc")
    List<PersonDTO> getAllPersonByProjectId(@Param("projectId")Integer projectId);

    @Select("select *, create_user_id as createUserId from zj_person where recordId = #{userid} order by create_time desc")
    List<PersonDTO> getAllPersonByUserid(@Param("userid") Integer userid);

    @Select("select *, create_user_id as createUserId from zj_person where recordId = #{userid} and projectId = #{projectId} order by create_time desc")
    List<PersonDTO> getAllPersonByUseridAndProjectId(@Param("userid") Integer userid, @Param("projectId") Integer projectId);

    @Select("select * from zj_person where handle = #{userid}")
    List<PersonDTO> getAllVerifyPersonByUserid(@Param("userid") Integer userid);

    @Select("select count(1) from zj_person where id = #{id}")
    Integer getPersonCountById(@Param("id") Integer id);

    void addPersonSub(PersonSub personSub);

    void updatePersonSub(PersonSub personSub);

    void updatePerson(Person person);

    @Delete("delete from zj_person_people_sub where gid = #{gid}")
    void delPersonSubByGid(@Param("gid")Integer gid);

    @Select("select * from zj_person_people_sub where gid = #{gid}")
    List<PersonSub> getAllPersonSubById(@Param("gid")Integer gid);


    @Select("select * from zj_person where businessKey = #{businessKey}")
    PersonDTO findPersonByBusinessKey(@Param("businessKey")String  businessKey);



    @Select("select a.* from ss_f_roles a left join ss_f_user_role b " +
            "on a.id = b.roleid where b.userid = #{userid}")
    SsFRoles getRoleByUserid(@Param("userid") Integer userid);

    @Select("select * from ss_f_person_user_group_role")
    List<PersonUserGroupRole> getPersonGroupRoles();

    @Select("select * from ss_f_person_user_group_role where personGroupid = #{personGroupid}")
    List<PersonUserGroupRole> getByGroupId(@Param("personGroupid") Integer personGroupid);


    List<SsFUsers> getByRoleIdAndProjectId(@Param("roleId") Integer roleid,
                               @Param("projectId")Integer projectId);

    List<SsFUsers> getByRoleId(@Param("roleid") Integer roleid,
                               @Param("list")List<Integer> groupId);

    @Select("select count(1) from zj_person where taskId = #{id}")
    Integer getCountByProcessId(@Param("id")String id);

    @Select("select * from zj_person where taskId = #{id}")
    PersonDTO getByProcessId(@Param("id")String id);

    @Select("select * from zj_person where businessKey = #{businessKey}")
    PersonDTO getByBusinessKey(@Param("businessKey")String businessKey);

    @Select("select * from zj_person_people_sub where gid = #{gid}")
    List<PersonSub> getPersonByGid(@Param("gid")Integer gid);

    @Select("select PROC_DEF_ID_ as processDefinitionId, " +
            " PROC_INST_ID_ as processInstanceId from act_hi_taskinst" +
            " where PROC_INST_ID_ = #{id} GROUP BY processInstanceId")
    Map<String, String> getIdsByProcessInstanceId(@Param("id")String id);

    @Select("select PROC_DEF_ID_ as processDefinitionId, " +
            " PROC_INST_ID_ as processInstanceId from act_ru_task" +
            " where PROC_INST_ID_ = #{id} GROUP BY processInstanceId")
    Map<String, String> getRuntimeIdsByProcessInstanceId(@Param("id")String id);

    @Select("select PROC_INST_ID_ from act_ru_execution where BUSINESS_KEY_ = #{key}")
    String getProcessInstanceIdByBusinessKey(String key);

    @Select("select PROC_INST_ID_ from act_hi_procinst where BUSINESS_KEY_ = #{key}")
    String getHisInstanceIdByBusinessKey(String key);

    // #118 modify mengzhengbin 20230405 修改：group by subDate desc 改为 order by subDate desc。 [S]
    @Select("select * from zj_person where status = 1 order by subDate desc")
    List<PersonDTO> getAllFinishContracts();
    // #118 --[E]

    @Select("select id from ss_f_roles where code = 'shigongjihe'")
    Integer getShiGongRoleId();

    @Select("select id from ss_f_roles where code = 'jianlijihe'")
    Integer getJianLiRoleId();

    @Select("select id from ss_f_roles where code = 'quanzijihe'")
    Integer getQuanZiRoleId();

    @Select("select a.* from zj_person a " +
            " left join ss_f_user_role b on a.recordId = b.USERID " +
            " left join ss_f_roles c on b.ROLEID = c.id " +
            " left join ss_f_roles d on c.parentid = d.id " +
            " where d.id = #{roleId} and a.status = 2 order by a.subDate")
    List<PersonDTO> getContractByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person a " +
            " left join ss_f_user_role b on a.recordId = b.USERID " +
            " left join ss_f_roles c on b.ROLEID = c.id " +
            " left join ss_f_roles d on c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc")
    List<PersonDTO> getAllContractByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person a " +
            " left join ss_f_user_role b on a.recordId = b.USERID " +
            " left join ss_f_roles c on b.ROLEID = c.id " +
            " left join ss_f_roles d on c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc")
    List<PersonDTO> getAllContractByRoleIdLimit(@Param("roleId")Integer roleId);
}
