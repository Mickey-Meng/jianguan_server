package com.ruoyi.jianguan.common.dao;


import com.ruoyi.jianguan.common.domain.entity.ZjZlDic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjZlDicDAO {

    @Select("select * from zj_zl_dic where pid = 0 and name = #{name}")
    ZjZlDic getByName(@Param("name") String name) ;

    int insert(ZjZlDic record);

    int insertSelective(ZjZlDic record);


    @Select("select * from zj_zl_dic where pid = #{id}")
    List<ZjZlDic> getByPid(@Param("id") Integer id);

    @Select("select * from zj_zl_dic where  name = #{name} and pid = #{id}")
    ZjZlDic getByNameTwo(@Param("name") String toString, @Param("id") Integer id);
}
