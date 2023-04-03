package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.czjg.zjrw.domain.entity.OperationData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConponentDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Conponent record);

    int insertSelective(Conponent record);

    Conponent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Conponent record);

    int updateByPrimaryKey(Conponent record);

    @Select("select * from conponent where  pid =#{pid} or id = #{pid} ")
    List<Conponent> getAll(@Param("pid")Integer pid);

    @Select("select * from conponent where w7 = #{w7} and projectcode = #{projectcode} and pcode = #{pcode} and pname = #{pname} " +
            "and code = #{code} and name = #{name} and pid =#{pid}")
    //w7 为工区     //projectcode 为项目编码
    Conponent getParentCount(@Param("w7")String w7,@Param("projectcode")String projectcode,@Param("pcode")String pcode, @Param("pname")String pname,
                       @Param("code")String code, @Param("name")String name,@Param("pid")Integer pid);


    @Select("select count(1) from conponent where mouldid = #{name}")
    int selectByMould(@Param("name")String name);


    @Update("update conponent set w9code = #{x} , w10 = #{y} ,w10code = #{z} where mouldid = #{name}")
    void updateByMould(@Param("x")String x, @Param("y")String y, @Param("z")String z,@Param("name")String name);


    List<Conponent> getAllByProject(Integer pid, List<String> list);

    List<Conponent> getConponentByRole(String type, List<String> list);

    List<Conponent> getConponentByRule(List<String> projects);


    @Select("select b.GROUPID from conponent a RIGHT JOIN  " +
            "zj_f_groups_projects b on a.projectcode = b.PROJECTID and a.id = #{conponentid} ")
    Integer getGroup(@Param("conponentid")Integer conponentid);

    List<String> getCheckTypeAndName(String projectid);

    @Select("select * from conponent where name =#{gongquname}  and code = #{gongqucode} " +
            " and pname = #{projectname} and pcode = #{projectcode} and pid = #{projectid} " +
            "and projectcode = #{projectcodeone}")
    Conponent getParent(@Param("gongquname") String gongquname, @Param("gongqucode")String gongqucode, @Param("projectname")String projectname,
                        @Param("projectcode")String projectcode, @Param("projectid")int projectid,@Param("projectcodeone")String projectcodeone);


    @Select("select * from conponent where name =#{gongquname}  and code = #{gongqucode} " +
            " and pname = #{projectname} and pcode = #{projectcode} and pid = #{projectid} ")
    Conponent getParentWithOutProjectCode(@Param("gongquname") String gongquname, @Param("gongqucode")String gongqucode, @Param("projectname")String projectname,
                                          @Param("projectcode")String projectcode, @Param("projectid")int projectid);


    List<Conponent> getAddids(List<Integer> target);

    @Select("Select * from conponent where conponentcode = #{s}")
    Conponent checkByConponentCode(@Param("s") String s);


    @Select("Select * from conponent where w3code = #{projectid} and conponenttype is not null")
    List<Conponent> getProjectConponentByProjectId(@Param("projectid")String projectid);

    @Select("select * from conponent ")
    List<Conponent> getAllData();

    @Select("select * from conponent where w9code is not null and w10 is not null " +
            "and w10code is not null and projectcode = #{projectcode} " +
            "order by conponentcode limit 1 ")
    Conponent getOperation(@Param("projectcode")String projectcode);

    @Select("select count(case when actulsttime is null and xxendtime is null then 0 end) as startact," +
            "count( case when xxendtime is null then 0 end ) as endact," +
            "count(0) as countall " +
            "from zj_conponent_producetime  " +
            "where projectcode = #{projectcode}")
    OperationData getOpeationData(@Param("projectcode")String projectcode);

    @Select("select * from conponent where projectcode = #{gq}")
    Conponent getgq(@Param("gq")String gq);

    @Select("select * from conponent where projectcode = #{projectid}")
    List<Conponent> getAllProject(@Param("projectid")String projectid);


    @Select("select *  from conponent a where pid in (1,663,3996) ")
    List<Conponent> getProject();


    @Select("select * from conponent_bak where w9code is not null and w10 is not null and w10code is not null")
    List<Conponent> getAllBak();

    @Select("select * from conponent where conponentcode is not null")
    List<Conponent> getAllConponent();

    @Select("select * from conponent where conponentcode = #{conponentcode} ")
    Conponent getConponentByCode(@Param("conponentcode") String conponentcode);


//    @Insert("insert into conponent (pcode, pname, code ,name,pid) values " +
//            "(#{conponentParent.pcode,jdbcType=VARCHAR},#{conponentParent.pname,jdbcType=VARCHAR}," +
//            "#{conponentParent.code,jdbcType=VARCHAR},#{conponentParent.name,jdbcType=VARCHAR}," +
//            "#{conponentParent.pid,jdbcType=INTEGER})")
//    @Options(useGeneratedKeys=true,keyProperty = "conponentParent.id", keyColumn = "id")
//    int insertParent(@Param("conponentParent")ConponentParent conponentParent);

    @Select("select count(1) from conponent where mouldid = #{mouldid}")
    Integer getByMouldid(@Param("mouldid") String mouldid);

    @Select("select * from conponent where mouldid = #{mouldid}")
    Conponent getDataByMouldid(@Param("mouldid") String mouldid);

    @Select("select * from conponent where id = #{id}")
    Conponent getDataById(@Param("id") Integer id);

    @Select("select count(1) from conponent where conponentcode = #{componentCode}")
    Integer getByCode(@Param("componentCode") String componentCode);

    @Select("select count(1) from conponent where oldconponentcode = #{componentCode}")
    Integer getByOldCode(@Param("componentCode") String componentCode);

    Integer updateLayerName(@Param("mouldid") String mouldid,
                            @Param("layername") String layername);

    @Update("update conponent set mouldid = #{mouldid} where conponentcode = #{componentCode}")
    Integer updateModelCode(@Param("componentCode") String componentCode,
                            @Param("mouldid") String mouldid);

    @Update("update conponent set oldconponentcode = #{oldComponentCode} where conponentcode = #{componentCode}")
    Integer updateOldCode(@Param("componentCode") String componentCode,
                          @Param("oldComponentCode")String oldComponentCode);

    @Select("select id from conponent where name = #{name}")
    Integer getIdByName(@Param("name") String name);

    @Update("update conponent set wbscode = #{wbsCode} where conponentcode = #{conponentcode}")
    Integer updateWbsCode(@Param("conponentcode") String conponentcode,
                          @Param("wbsCode") String wbsCode);

    @Select("select * from conponent where oldconponentcode = #{modelCode}")
    Conponent getDataByOldCode(@Param("modelCode") String modelCode);

    @Select("select * from conponent where mouldid = #{modelCode}")
    Conponent getDataByModelCode(@Param("modelCode") String modelCode);

    @Select("select w4 from conponent where projecttype = #{type} group by w4")
    List<String> getProjectTypeByProject(@Param("type") String type);

    @Select("select * from conponent where projecttype = 'LM'")
    List<Conponent> getAllLMId();

    @Select("select * from conponent where CODE = #{gongQuId}")
    Conponent getConponentByGqId(@Param("gongQuId") String gongQuId);
}
