package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.entity.ZjYcexceeddata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjYcexceeddataDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjYcexceeddata record);

    int insertSelective(ZjYcexceeddata record);

    ZjYcexceeddata selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZjYcexceeddata record);

    int updateByPrimaryKey(ZjYcexceeddata record);

    @Select("select * from zj_ycexceeddata ORDER BY id desc limit 0, 1000")
    List<ZjYcexceeddata> getAll();
}
