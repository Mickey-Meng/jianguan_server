package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.dto.ProduceAndRecodeDTO;
import com.ruoyi.jianguan.common.domain.entity.ProduceandrecodeUser;
import com.ruoyi.jianguan.common.domain.dto.ConModel;
import com.ruoyi.jianguan.common.domain.dto.RecodeQueryData;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("zjProduceandrecodeDAO")
@Mapper
public interface ProduceandrecodeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Produceandrecode record);

    Produceandrecode selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Produceandrecode record);

    List<Produceandrecode> getRecodeId(ArrayList<Integer> typeList);

    List<ConModel> getRecodeModel(List<String> list,String type);

    List<Produceandrecode> getAllByConponentId(Integer conponentId);

    List<Produceandrecode> getAgencyByUserid(@Param("checkuser") Integer id,
                                             @Param("projectId")Integer projectId);


    void updateRecondStatus(@Param("recondid")Integer recondid,
                            @Param("result") Integer result,
                            @Param("produceid")Integer produceid,
                            @Param("copyUsers")String copyUsers);

    List<ConModel> getRecodeModelAndConponentId(Integer conponentidadd);

    @Select("select * from produceandrecode where recodeid =#{recondid}")
    Produceandrecode getProduceandReconde(@Param("recondid") Integer recondid);

    @Select("select * from produceandrecode where checkuser = #{userid} and projectId = #{projectId} group by updatetime desc")
    List<Produceandrecode> getRecodeModelByCheckUser(@Param("userid") Integer userid,
                                                     @Param("projectId")Integer projectId);

    @Select("select * from produceandrecode  where updateuser = #{userid} and projectId = #{projectId} group by updatetime desc")
    List<Produceandrecode> getRecodeModelByUpdateUser(@Param("userid")Integer userid,
                                                      @Param("projectId")Integer projectId);


//    List<Produceandrecode> getAllProduce(@Param("list") List<String> list, @Param("type") String type);

    @Select("select * from produceandrecode where conponentid = #{conponentid}  and produceid = #{produceid}")
    Produceandrecode getByIdAndProduceId(@Param("conponentid") Integer conponentid, @Param("produceid") Integer produceid);

    List<Produceandrecode> getAllProduce(RecodeQueryData recodeQueryData);

    List<Produceandrecode> getAllProduceTime(RecodeQueryData recodeQueryData);

    @Delete("delete from produceandrecode where recodeid =#{recondid} ")
    void del(@Param("recondid") Integer recondid);

    Integer updateByCode(Produceandrecode record);

    Integer getCheckuserByRecodeid(@Param("recondid") Integer recondid,
                                   @Param("produceid")Integer produceid);

    @Select("select * from produceandrecode where checkuser = #{id} and status = 0 and checkresult = 3")
    List<Produceandrecode> getProcessByJianLiId(@Param("id") Integer id);

    @Select("select * from produceandrecode where updateuser = #{id} and status in (1,2) and checkresult in (1,2)")
    List<Produceandrecode> getApprovedProcess(@Param("id") Integer id);

    void updateReject(@Param("recodeid")Integer recodeid,
                      @Param("produceid")Integer produceid);

    @Select("select * from produceandrecode where conponentid = #{conponentid} and recodeid = #{recodeid}")
    Produceandrecode getInfoByConponentidAndRecodeId(@Param("conponentid")Integer conponentid,
                                            @Param("recodeid")Integer recodeid);

    @Delete("delete from produceandrecode where conponentid = #{conponentid} and recodeid = #{recodeid}")
    Integer deleteByConponentidAndRecodeId(@Param("conponentid")Integer conponentid,
                                            @Param("recodeid")Integer recodeid);

    List<Produceandrecode> getByProjectcode(@Param("list")List<String> list);

    String getConpontentCodeByRecodeId(@Param("recodeid") Integer recodeid,
                                       @Param("produceid")Integer produceid);

    List<Produceandrecode> getAll();

    @Select("select * from produceandrecode")
    List<Produceandrecode> getAllAsc();

    @Update("update produceandrecode set conponentid = #{conponentid}," +
            " conponentcode = #{conponentcode} where conponentcode = #{modelcode}")
    Integer updateModelCode(@Param("conponentid")Integer conponentid,
                            @Param("conponentcode")String conponentcode,
                            @Param("modelcode")String modelcode);

    @Select("select count(1) from produceandrecode where conponentid = #{conponentid}")
    Integer getCountByComponentid(@Param("conponentid")Integer conponentid);


    Produceandrecode selectByRecodeId(@Param("recodeid")Integer recodeid,
                                      @Param("produceid")Integer produceid);

    @Select("select count(1) from produceandrecode where conponentid = #{conponentid} and produceid = #{produceid}")
    Integer getCountById(@Param("conponentid")Integer conponentid,
                         @Param("produceid")Integer produceid);

    List<Produceandrecode> getAllByConponentCodes(@Param("list")List<String> conponentCodes);


    @Select("select * from produceandrecode where updatetime >= '2022-06-27 00:00:00' and " +
            "updatetime <= '2022-07-03 23:59:59'")
    List<Produceandrecode> getFinishTimeCode();


    List<ProduceAndRecodeDTO> getAllProduceByProjectId(@Param("projectId")Integer projectId);

    List<ProduceAndRecodeDTO> getAllByProjectAndUnitCodes(@Param("projectId")Integer projectId,
                                                       @Param("list")List<String> codes);

    List<ProduceAndRecodeDTO> getAllByUnitAndTime(@Param("projectId")Integer projectId,
                                                  @Param("list")List<String> codes,
                                                  @Param("start")String start,
                                                  @Param("end")String end);

    @Select("SELECT a.NAME as workAreaName, c.PROJECTNAME as unitName " +
            " FROM ss_f_projects a " +
            " LEFT JOIN ss_f_projects b ON a.id = b.PARENTID " +
            " LEFT JOIN zj_f_groups_projects c ON c.GROUPID = b.id " +
            " WHERE c.PROJECTID = #{code}")
    Map<String, String> getUnitNameAndWorkAreaNameByCode(String code);

    int insertProduceUser(ProduceandrecodeUser record);

    @Select("select gid from produceandrecode_user where userid = #{id} and ststate = 0")
    List<Integer> getProduceIdsByUserId(Integer id);

    List<Produceandrecode> getProduceCopyByGids(@Param("list") List<Integer> ids);

    @Select("select * from produceandrecode_user where gid = #{id} and userid = #{userId}")
    ProduceandrecodeUser getProduceUserByGidAndUserId(@Param("id")Integer id,
                                                      @Param("userId")Integer userId);

    @Update("update produceandrecode_user set ststate = 1 where gid = #{id} and userid = #{userId}")
    int updateProduceUserState(@Param("id")Integer id,
                               @Param("userId")Integer userId);
}
