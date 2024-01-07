package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.Recode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("zjRecodeDAO")
@Mapper
public interface RecodeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Recode record);

    int insertSelective(Recode record);

    Recode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recode record);

    int updateByPrimaryKey(Recode record);

    @Update("update recode set fillinid = #{recodeDetailId} where id = #{recodeid}")
    void updateFillin(@Param("recodeid") Integer recodeid, @Param("recodeDetailId")int recodeDetailId);
    @Update("update recode set checkmyselfid = #{recodeDetailId} where id = #{recodeid}")
    void updateCheckMyself(@Param("recodeid") Integer recodeid, @Param("recodeDetailId")int recodeDetailId);

    @Update("update recode set checkid = #{recodeDetailId} where id = #{recodeid}")
    void updateCheck(@Param("recodeid") Integer recodeid, @Param("recodeDetailId")int recodeDetailId);

    @Select("select remark from recode where id = #{recodeid}")
    String getUrlById(@Param("recodeid")Integer recodeid);

    int updateUrl(Recode recode);

    @Select("select remark from recode where remark is not null and remark != ''")
    List<String> getNotNullRemark();
    @Select("select accrecodeurl from recode where accrecodeurl is " +
            "not null and accrecodeurl not like 'http://121.4.218.185/%' and accrecodeurl != ''")
    List<String> getAccrecodeurl();
    @Select("select testurl from recode where testurl is " +
            "not null and testurl not like 'http://121.4.218.185/%' and testurl != ''")
    List<String> getTesturl();
    @Select("select standbyrecode from recode where standbyrecode is not null and standbyrecode != ''")
    List<String> getStandbyrecode();
    @Select("select MAX(id) from recode ")
    Integer selectPrimaryKey();
}
