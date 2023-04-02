package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFUserGroup;
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

    @Select("select * from ss_f_groups where grouplevel = 3")
    List<SsFGroups> getall();
    @Select("select * from ss_f_groups where grouplevel = 4 and parentid = #{id}")
    List<SsFGroups> getallGq(@Param("id") Integer id);

    Integer deleteByUserIds(@Param("list") List<Integer> list);

    @Select("select groupid from ss_f_user_group where userid = #{id} and ststate = 1")
    List<Integer> getGroupsByUserId(@Param("id") Integer id);

    @Select("select * from ss_f_user_group where groupid = #{groupid}")
    List<SsFUserGroup> getAllByGroupId(@Param("groupid")Integer groupid);

    @Select("select groupid from ss_f_user_group where userid = #{id}")
    List<Integer> getGroupsById(@Param("id") Integer id);

    @Select("select groupid from ss_f_user_group where ststate = 1")
    List<Integer> getAllGroups();
}
