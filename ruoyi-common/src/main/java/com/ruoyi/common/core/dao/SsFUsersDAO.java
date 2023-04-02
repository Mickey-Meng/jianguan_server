package com.ruoyi.common.core.dao;

import com.ruoyi.common.core.domain.entity.CheckModel;
import com.ruoyi.common.core.domain.entity.SafeCheck;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SsFUsersDAO extends BaseDaoMapper<SsFUsers> {
    int deleteByPrimaryKey(Integer id);

    int insert(SsFUsers record);

    int insertSelective(SsFUsers record);

    SsFUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsFUsers record);

    int updateByPrimaryKey(SsFUsers record);

    @Select("select * from ss_f_users where USERNAME = #{username} ")
    SsFUsers checkLogin(@Param("username") String username);


    List<CheckModel> getSupCheck();

    @Select("select roleid from ss_f_user_role where userid = #{userid}")
    Integer getRoleById(@Param("userid") Integer userid);

    @Update("update ss_f_users set pwd = #{pwd} where id = #{id}")
    Integer updatePwd(@Param("pwd") String pwd,
                      @Param("id") Integer id);

    List<SafeCheck> getInfoById(@Param("list") List<Integer> list);

    @Select("select * from ss_f_users where id = #{id}")
    SsFUsers checkLoginById(@Param("id") Integer id);

    @Select("select `name` from ss_f_users where id =#{id}")
    String getNameByUserId(@Param("id") Integer id);

    /**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     */
    List<SsFUsers> getUserNamesByIds(@Param("userIds") List<Integer> userIds);

    /**
     * 查询用户显示名通过登录名称
     *
     * @param userNames
     * @return
     */
    List<SsFUsers> getNamesByUserName(@Param("userNames")List<String> userNames);
}
