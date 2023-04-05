package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.weather.WeatherNow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository("zjWeatherDAO")
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
