package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.entity.SafeCheck;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeDic;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjSafeDicDAO {
    int insert(ZjSafeDic record);

    int insertSelective(ZjSafeDic record);

    @Select("select * from zj_safe_dic where pid = #{pid}")
    List<ZjSafeDic> getByPid(@Param("pid") Integer id);

    @Select("select a.ID,a.NAME as USERNAME ,e.`NAME` as GROUPNAME, e.id as gongquid from ss_f_users  a " +
            "            left join ss_f_user_role b  " +
            "            on a.id = b.userid  " +
            "            left join ss_f_roles c " +
            "            on c.id = b.roleid  " +
            "            left join ss_f_user_group  d  " +
            "            on a.id = d.userid  " +
            "            left join ss_f_projects e  " +
            "            on d.groupid = e.id " +
            "            where c.code = 'sg' ")
    List<SafeCheck> getCheck();

    @Select("select * from zj_safe_dic where projectid = #{projectid}")
    List<ZjSafeEvent> getGongquData(@Param("projectid")String projectid);

    @Select("select * from zj_safe_dic where name  =  #{s} and pid = 0")
    ZjSafeDic getbyName(@Param("s") String s);

    @Select("select * from zj_safe_dic where name  =  #{s} and pid = #{id}")
    ZjSafeDic getbyNameAndpid(@Param("s") String s, @Param("id") Integer id);
}
