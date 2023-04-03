package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.entity.ZjFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZjFileDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjFile record);

    int insertSelective(ZjFile record);

    ZjFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZjFile record);

    int updateByPrimaryKey(ZjFile record);

    List<ZjFile> selectByType(@Param("type")Integer type,
                              @Param("projectId")Integer projectId);

    @Select("select fileurl from zj_file where id = #{id}")
    String getFileIdById(@Param("id") Integer id);

    @Select("select fileurl from zj_file where fileurl is not null and fileurl != ''")
    List<String> getFileurl();
}
