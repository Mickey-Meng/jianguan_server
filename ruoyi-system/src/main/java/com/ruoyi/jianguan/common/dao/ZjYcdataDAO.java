package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.dto.YcDataDTO;
import com.ruoyi.common.core.domain.entity.ZjYcdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ZjYcdataDAO {
    int insert(ZjYcdata record);

    int insertSelective(ZjYcdata record);

    @Select("select address from zj_ycdata where code = #{code} and address is not null group by address")
    String getNameByCode(@Param("code") String code);

    List<YcDataDTO> searchByStAndEndAndCode(@Param("code") String code,
                                            @Param("sttime") Date sttime,
                                            @Param("endtime") Date endtime);
}
