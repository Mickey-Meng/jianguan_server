package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.entity.Produce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjProduceDAO")
@Mapper
public interface ProduceDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Produce record);

    int insertSelective(Produce record);

    Produce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Produce record);

    int updateByPrimaryKey(Produce record);

    List<Produce> getProduce(@Param("type") String type);

    @Select("select * from produce where type = #{s} and rangee = #{par}")
    Produce select(@Param("s") String s, @Param("par") Integer par);
    @Select("select * from produce ")
    List<Produce> getAll();

    @Select("select * from produce where type = #{type}")
    List<Produce> getAllByType(@Param("type") String type);

    @Select("select count(1) from produce where type = #{type}")
    Integer getNumProduce(@Param("type")String type);

    @Select("select * from produce where type = #{type}")
    Produce getByType(String conponenttype);

    @Select("select id from produce where type = #{type} group by id desc")
    List<Integer> getIdByTypeDesc(@Param("type") String type);
}
