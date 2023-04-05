package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.Check;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjCheckDAO")
@Mapper
public interface CheckDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    @Select("select * from checkconfig")
    List<Check> getAll();

    @Select("select * from checkconfig where pid = #{id}")
    List<Check> getByPid(@Param("id") Integer id);
}