package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.ZjConponentProducetime;
import com.ruoyi.czjg.zjrw.domain.dto.*;
import com.ruoyi.czjg.zjrw.domain.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ZjConponentProducetimeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjConponentProducetime record);

    int insertSelective(ZjConponentProducetime record);

    ZjConponentProducetime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZjConponentProducetime record);

    int updateByPrimaryKey(ZjConponentProducetime record);

    @Select("select * from zj_conponent_producetime where conponentid = #{conpoentid}")
    ZjConponentProducetime getZjByConponentId(@Param("conpoentid") int conpoentid);


    List<ZjConponentProducetime> getProgressData(List<String> list);

    @Select("select * from zj_conponent_producetime where mouldid = #{mouldid}")
    ZjConponentProducetime getZjTimeByMouildid(@Param("mouldid")String mouldid);

    @Select("select * from zj_conponent_producetime where conponentcode = #{mouldid}")
    ZjConponentProducetime getZjTimeByCode(@Param("mouldid")String mouldid);

    List<ZjConponentProducetime> getActureData(@Param("list") List<String> list,
                                               @Param("sttime") Date stDate,
                                               @Param("endtime") Date endDate,
                                               @Param("type")String type);

    List<ZjConponentProducetime> getPlantureData(@Param("list") List<String> list,
                                                 @Param("sttime") Date stDate,
                                                 @Param("endtime") Date endDate,
                                                 @Param("type")String type);



    List<ZjConponentProducetime> getActureByToday(List<String> list, Date date);

    List<ZjConponentProducetime> getPlanByToday(List<String> list, Date date);

    int getCountNumber(List<String> list);

    @Select("select PROJECTTYPE as projecttype, conponenttypename , conponenttype     " +
            "from zj_conponent_producetime GROUP BY PROJECTTYPE , conponenttypename ,conponenttype  ")
    List<TypeProject> getTypeProject();


    int getCountPeriodNum(String conponentType, List<String> list, Integer sort, Date sttime, Date endtime);

    @Select("select * from zj_conponent_producetime where projectcode = #{projectid} ")
    List<ZjConponentProducetime> getAllTimeData(@Param("projectid")String projectid);

    List<MiddleData> getMiddleData(List<String> list);

    List<StatusData> getStatus(@Param("list")List<String> projects, @Param("sttime")Date sttime, @Param("endtime")Date endtime);

    List<PlanDataNew> getPlan(@Param("list") List<String> projects, @Param("type") String type);
    List<ActDataNew> getAct(@Param("list") List<String> projects, @Param("type") String type);

    @Select("select * from zj_conponent_producetime where conponentcode = #{conponentcode}")
    ZjConponentProducetime getZjConponentBycode(@Param("conponentcode") String conponentcode);


    @Select("select * from zj_conponent_producetime where projecttype = 'QL'")
    List<ZjConponentProducetime> getAll();

    @Select("select * from zj_conponent_producetime where projecttype = 'LM'")
    List<ZjConponentProducetime> getDLAll();

    @Select("select * from zj_conponent_producetime where (gongquid is null or gongquname is null)")
    List<ZjConponentProducetime> getAllsByGongquidAndGongquname();

    @Select("select * from zj_conponent_producetime where conponenttypename is null")
    List<ZjConponentProducetime> getAllsByConponentTypeName();

    @Select("\n" +
            "select c.* from zj_f_groups_projects a \n" +
            "left join \n" +
            "ss_f_projects b\n" +
            "on a.groupid = b.id\n" +
            "left join ss_f_projects c\n" +
            "on c.id=b.parentid\n" +
            "where\n" +
            "a.projectid = #{projectcode}")
    SsFGroups getByGroup(@Param("projectcode") String projectcode);

    List<GqFirst> getAllByProJect(@Param("list") List<String> list,
                                  @Param("type")String type);


    List<PjGirst> getAllByProJectSX(@Param("projectcode") String projectcode);
    List<PjGirst> getAllByProJectSD(@Param("projectcode") String projectcode);
    List<PjGirst> getAllByProJectLM(@Param("projectcode") String projectcode);

    int getcount(@Param("gongquid") Integer gongquid, Date sttime, Date endtime, @Param("type") String type);

    List<NewCensusData> getTypeWeek(@Param("conponentType") String conponentType,
                                    @Param("list") List<String> list,
                                    @Param("projectCode")String projectCode);

    List<NewCensusData> getTypeMonth(@Param("conponentType") String conponentType,
                                     @Param("list")List<String> list,
                                     @Param("projectCode")String projectCode);

    List<NewCensusData> getTypeSeason(@Param("conponentType") String conponentType,
                                      @Param("list") List<String> list,
                                      @Param("projectCode")String projectCode);

    List<NewProjectConType> getallNew(@Param("abc") String abc);

    @Select("select conponenttype as type  ,conponenttypename as name  from  zj_conponent_producetime where projecttype = 'SD'  " +
            " and  gongquid in (${abc}) group by  conponenttype ,conponenttypename")
    List<NewProjectConType> getallNewSD(@Param("abc") String abc);

    @Select("select conponenttype as type, conponenttypename as name from zj_conponent_producetime where projecttype = 'LM' " +
            " and gongquid in (${abc}) group by conponenttype, conponenttypename")
    List<NewProjectConType> getallNewDL(@Param("abc") String abc);

    List<GroupProjectDTO> getallProject(@Param("abc") String abc);

    List<ZjConponentProducetime> getbyTypeData(NewCheckData newCheckData);

    void updateTime(@Param("zjByConponentId") ZjConponentProducetime zjByConponentId);

    List<ComponentProducetimeDTO> getData(@Param("list") List<String> list);


    List<ZjConponentProducetime> getAllByCode(@Param("list") List<String> list);

    @Update("update zj_conponent_producetime set actulendtime = #{actulendtime} where conponentcode = #{conponentcode}")
    int updateFinishTime(@Param("conponentcode")String conponentcode,
                         @Param("actulendtime")Date actulendtime);

    @Select("select a.conponentcode, b.plansttime, b.actulendtime, b.actulsttime, b.actulendtime" +
            " from conponent a left join zj_conponent_producetime_copy b on " +
            "a.oldconponentcode = b.conponentcode where " +
            "(plansttime is not null or planendtime is not null or actulsttime is not null or actulendtime is not null)")
    List<ComponentProgressTimes> getCopyAll();

    int updateTimesByCopy(ComponentProgressTimes time);

    @Select("select count(1) from zj_conponent_producetime where conponentcode = #{conponentcode}")
    Integer getByConponentId(@Param("conponentcode")String conponentcode);

    Integer updateByConponentcode(ZjConponentProducetime producetime);

    int updateGongQu(ZjConponentProducetime producetime);

    int updatePjcType(ZjConponentProducetime producetime);

    @Update("update zj_conponent_producetime set plansttime = null, planendtime = null, " +
            "actulsttime = null, actulendtime = null where conponentid = #{conponentid}")
    Integer setNullTime(@Param("conponentid")Integer conponentid);

    @Update("update zj_conponent_producetime set actulendtime = null " +
            "where conponentid = #{conponentid}")
    Integer setActulendNullTime(@Param("conponentid")Integer conponentid);

    @Update("update zj_conponent_producetime set plansttime = null, planendtime = null, " +
            "actulsttime = null where conponentid = #{conponentid}")
    Integer setActulstNullTime(@Param("conponentid")Integer conponentid);


    @Select("select * from zj_conponent_producetime_copy " +
            " where plansttime is not null or planendtime is not null " +
            " or actulsttime is not null or actulendtime is not null")
    List<ZjConponentProducetime> getAllNotNullTime();

    @Select("select conponentcode from zj_conponent_producetime_copy " +
            " where plansttime is not null or planendtime is not null " +
            " or actulsttime is not null or actulendtime is not null")
    List<String> getCopyCodeNotNullTime();

    @Select("select conponentcode from zj_conponent_producetime " +
            " where plansttime is not null or planendtime is not null " +
            " or actulsttime is not null or actulendtime is not null")
    List<String> getCodeNotNullTime();

    @Update("update zj_conponent_producetime set conponentid = " +
            " #{conponentid} where conponentcode = #{conponentcode}")
    void updateConponentIdByCode(@Param("conponentid")Integer conponentid,
                                 @Param("conponentcode")String conponentcode);
}
