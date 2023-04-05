package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.ZjYcperday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ZjYcperdayDAO {
    int insert(ZjYcperday record);

    int insertSelective(ZjYcperday record);


    List<ZjYcperday> searchByStAndEnd(Date sttime, Date endtime);

    List<ZjYcperday> searchByStAndEndAndCode(String code, Date sttime, Date endtime);

    @Select("select addr from zj_ycperday where code = #{code} and addr is not null group by addr")
    String getAddrByCode(@Param("code") String code);
}
