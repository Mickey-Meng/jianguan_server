package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.czjg.zjrw.domain.entity.weather.WeatherNow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface WeatherDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(WeatherNow record);

    int insertSelective(WeatherNow record);

    WeatherNow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeatherNow record);

    int updateByPrimaryKey(WeatherNow record);

    List<WeatherNow> getAllTodayWeather(@Param("startTime") Date startTime,
                                        @Param("endTime")Date endTime);
}
