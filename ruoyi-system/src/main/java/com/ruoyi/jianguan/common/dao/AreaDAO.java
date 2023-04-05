package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjAreaDAO")
@Mapper
public interface AreaDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> getProjectByArea(Integer areaid);
}
