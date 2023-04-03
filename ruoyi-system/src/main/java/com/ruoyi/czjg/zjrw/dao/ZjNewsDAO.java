package com.ruoyi.czjg.zjrw.dao;


import com.ruoyi.czjg.zjrw.domain.entity.ZjNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjNewsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjNews record);

    int insertSelective(ZjNews record);

    ZjNews selectByPrimaryKey(@Param("id") Integer id,
                              @Param("projectId")Integer projectId);

    int updateByPrimaryKeySelective(ZjNews record);

    int updateByPrimaryKey(ZjNews record);

    @Select("select * from zj_news where type = #{type} and projectId = #{projectId} order by sttime desc limit 0,10")
    List<ZjNews> getbyType(@Param("type") Integer type,
                           @Param("projectId")Integer projectId);

    @Select("select * from zj_news where projectId = #{projectId} order by sttime desc limit 0, 10")
    List<ZjNews> getNews(@Param("projectId")Integer projectId);

    @Select("select pic from zj_news where pic is not null and pic != ''")
    List<String> getPic();

    @Select("select * from zj_news where id = #{id}")
    ZjNews getById(@Param("id") Integer id);
}
