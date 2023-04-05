package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.Recodedetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("zjRecodedetailDAO")
@Mapper
public interface RecodedetailDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Recodedetail record);

    int insertSelective(Recodedetail record);

    Recodedetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recodedetail record);

    int updateByPrimaryKey(Recodedetail record);

    @Select(" select id from produce where " +
            "type in (select type  from produce where id = #{produceid}) " +
            "ORDER BY `rangee` asc limit 0,1")
    int compareProduceId(@Param("produceid") Integer produceid);

    @Select(" select id from produce where " +
            "type in (select type  from produce where id = #{produceid}) " +
            "ORDER BY `rangee` desc limit 0,1")
    int compareToProduceId(@Param("produceid") Integer produceid);
}
