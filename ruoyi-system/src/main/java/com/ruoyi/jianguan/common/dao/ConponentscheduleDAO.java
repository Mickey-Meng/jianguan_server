package com.ruoyi.jianguan.common.dao;

import com.ruoyi.common.core.domain.entity.Conponentschedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjConponentscheduleDAO")
@Mapper
public interface ConponentscheduleDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Conponentschedule record);

    int insertSelective(Conponentschedule record);

    Conponentschedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Conponentschedule record);

    int updateByPrimaryKey(Conponentschedule record);


    @Select("select * from conponentschedule")
    List<Conponentschedule> getAll();
}
