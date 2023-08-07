package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.model.ZjPersonChange;
import com.ruoyi.common.core.domain.model.ZjPersonChangeFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author ChenZhiWei
* @since 2022-05-26
*/
@Mapper
@Repository
public interface ZjPersonChangeDAO {

    void newPersonChange(ZjPersonChange personChange);

    @Select("select * from zj_person_change where id = #{id}")
    ZjPersonChange selectByPrimaryKey(@Param("id")Integer id);

    @Select("select * from zj_person_change where businessKey = #{businessKey}")
    ZjPersonChange selectByBusinessKey(@Param("businessKey")String businessKey);

    @Delete("delete from zj_person_change where id = #{id} and projectChildId = #{projectId}")
    void delChange(@Param("id")Integer id,
                   @Param("projectId")Integer projectId);

    @Select("SELECT LAST_INSERT_ID();")
    Integer getLastInsertId();

    @Select("select count(1) from zj_person_change")
    int getAllCount();

    void addPersonFile(ZjPersonChangeFile personChangeFile);

    @Delete("delete from zj_person_change_file where gid = #{gid}")
    void delPersonFile(@Param("gid")Integer gid);

    @Select("select * from zj_person_change_file where gid = #{gid}")
    ZjPersonChangeFile getFileByGid(@Param("gid")Integer gid);

    //todo group by subDate desc
    @Select("<script> select * from zj_person_change where  projectChildId = #{projectId}  " +
            "<if test='id != null and id != \"\"'> and recordId = #{id} </if> " +
            " order by id desc  </script>")
    List<ZjPersonChange> getChangeByUserId(@Param("id")Integer id,@Param("projectId")Integer projectId);


    @Select("select * from zj_person_change where projectChildId = #{projectId} order by subDate desc")
    List<ZjPersonChange> getAllChangeByProjectId(@Param("projectId")Integer projectId);

    @Select("select * from zj_person_change where projectChildId = #{projectId} " +
            " order by subDate desc limit #{pageNum}, #{pageSize}")
    List<ZjPersonChange> getAllChangeByProjectIdLimit(@Param("projectId")Integer projectId,
                                                      @Param("pageNum")Integer pageNum,
                                                      @Param("pageSize")Integer pageSize);

    //todo group by uploadTime desc
    @Select("select * from zj_person_change_file where gid = #{gid} ")
    List<ZjPersonChangeFile> getFileIdsByGid(@Param("gid")Integer gid);

    @Select("select count(1) from zj_person_change where processInstanceId = #{processId}")
    Integer getCountByProcessId(@Param("processId")String processId);

    @Select("select * from zj_person_change where processInstanceId = #{processId}")
    ZjPersonChange getChangeByProcessId(@Param("processId")String processId);

    @Select("select fileId from zj_person_change_file where fileId is not null")
    List<String> getFileIds();

    @Select("select count(1) from act_hi_actinst where PROC_INST_ID_ = #{processId} " +
            "and ASSIGNEE_ = '15158278032' and END_TIME_ is not null")
    Integer getCountActHiActinstByProcessId(@Param("processId")String processInstanceId);

    //todo group by subDate desc
    @Select("select * from zj_person_change where status = 1 ")
    List<ZjPersonChange> getAllFisishChanges();

    @Select("select a.* from zj_person_change a " +
            " LEFT JOIN ss_f_user_role b ON a.recordId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} and a.status = 2")
    List<ZjPersonChange> getChangeByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person_change a " +
            " LEFT JOIN ss_f_user_role b ON a.recordId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc")
    List<ZjPersonChange> getAllChangeByRoleId(@Param("roleId")Integer roleId);

    @Select("select a.* from zj_person_change a " +
            " LEFT JOIN ss_f_user_role b ON a.recordId = b.USERID " +
            " LEFT JOIN ss_f_roles c ON b.ROLEID = c.id " +
            " LEFT JOIN ss_f_roles d ON c.parentid = d.id " +
            " where d.id = #{roleId} order by a.subDate desc " +
            " limit #{pageNum}, #{pageSize}")
    List<ZjPersonChange> getAllChangeByRoleIdLimit(@Param("roleId")Integer roleId,
                                                   @Param("pageNum")Integer pageNum,
                                                   @Param("pageSize")Integer pageSize);

    @Select("select * from ss_f_user_role where userid = #{userid}")
    SsFUserRole getByUserid(@Param("userid")Integer userid);

    @Update("update ss_f_user_role set USERID = #{before} where USERID = #{after} and roleId = #{roleId}")
    int updateUserRole(@Param("before")Integer beforeuserid,
                       @Param("after")Integer afteruserid,
                       @Param("roleId")Integer roleid);

    @Update("update zj_person_change set status = #{status} where id = #{id}")
    int updatePersonChangeStatus(@Param("status")Integer status,
                       @Param("id")Integer id);


    @Insert("insert into ss_f_user_role values(#{userId}, #{roleId}, now(), 1, 0)")
    int insertUserRole(@Param("userId")Integer userid,
                       @Param("roleId")Integer roleid);


}
