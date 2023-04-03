package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.entity.ZjDigitalTwin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("zjDigitalTwinDAO")
@Mapper
public interface DigitalTwinDAO {

    int insert(ZjDigitalTwin zjDigitalTwin);

    List<ZjDigitalTwin> getData(@Param("list")List<String> projectCodes);

    @Delete("delete from zj_workpoint where id = #{id}")
    Integer delDataById(@Param("id")Integer id);

    Integer updateData(ZjDigitalTwin zjDigitalTwin);

    @Select("select count(1) from conponent where w3code = #{projectCode}")
    Integer getAllByProjectCode(@Param("projectCode") String projectCode);

    @Select("select count(1) from zj_conponent_producetime where projectcode = #{projectCode} and actulendtime is not null")
    Integer getFinishByProjectCode(@Param("projectCode") String projectCode);

    @Select("select count(1) from zj_conponent_producetime where " +
            "projectcode = #{projectCode} and actulsttime is not null and actulendtime is null")
    Integer getWorkByProjectCode(@Param("projectCode") String projectCode);
}
