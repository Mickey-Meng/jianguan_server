package com.ruoyi.czjg.zjrw.dao;

import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.czjg.zjrw.domain.dto.EventGroup;
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
public interface ZjSafeEventDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjSafeEvent record);

    int insertSelective(ZjSafeEvent record);

    ZjSafeEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZjSafeEvent record);

    int updateByPrimaryKey(ZjSafeEvent record);

    @Select("select * from zj_safe_event where status = 0 and  ( modifyid = #{userid} or uploadid = #{userid}  ) " +
            " and projectId = #{projectId}")
    List<ZjSafeEvent> getSafeEventByModify(@Param("userid") Integer userid,
                                           @Param("projectId")Integer projectId);


    @Select("select * from zj_safe_event where  uploadid = #{userid}  and status = 3")
    List<ZjSafeEvent> getDoneSafeEventByModify(@Param("userid")Integer userid);

    @Select("select * from zj_safe_event where  uploadid = #{userid}  and status = 1")
    List<ZjSafeEvent> getDelaySafeEventByModify(@Param("userid")Integer userid,
                                                @Param("projectId")Integer projectId);

    @Select("select * from zj_safe_event where  uploadid = #{userid}  and status = 2 and projectId = #{projectId}")
    List<ZjSafeEvent> getNotDoneSafeEvent(@Param("userid")Integer userid,
                                          @Param("projectId")Integer projectId);

    List<ZjSafeEvent> getDataByProjectIds(List<Integer> list, Date sttime, Date endtime);

    List<ZjSafeEvent> getDataByProjectId(List<Integer> list);

    List<ZjSafeEvent> getDataById(List<Integer> list);



    @Select("select * from zj_safe_event where projectid = #{projectid}")
    List<ZjSafeEvent> getGongquData(@Param("projectid")String projectid);


    @Select("select * from zj_safe_event where gongquid in  ( ${userid} ) and projectId = #{projectId}")
    List<ZjSafeEvent> getAllStatusSafeByModify(@Param("userid") String userid,
                                               @Param("projectId")Integer projectId);

    List<SafeCheckData> getSafeCheckData(List<Integer> list);

    List<ZjSafeEvent> getGongquDatas(@Param("list")List<Integer> list ,
                                     @Param("projectId")Integer projectId);



    List<SafeGongQugroup> getAllStatusCount(@Param("list") List<Integer> list, @Param("sttime") Date sttime, @Param("endtime") Date endtime);

    List<SafeGongQugroupOverdue> getOverdueCount(@Param("list") List<Integer> list);

//    @Select("select count(1) as count, gongquid ,safefirstname from zj_safe_event  where gongquid in (${abc}) group by gongquid ,safefirstname")
//    List<EventGroup> group(@Param("abc") String abc);

    List<EventGroup> group(@Param("list") List<Integer> list, @Param("sttime") Date sttime, @Param("endtime") Date endtime);


    @Select("select * from  ss_f_projects where grouplevel = 4")
    List<SsFGroups> getgqAll();

    @Select("select * from  ss_f_projects where grouplevel = 5 and parentid = #{id}")
    List<SsFGroups> getprojectBygongqu(@Param("id") Integer id);


    List<ZjSafeEvent> getGQ( @Param("sttime") Date sttime, @Param("endtime") Date endtime);

    List<ZjSafeEvent> getByGQ(@Param("gqid") Integer gqid, @Param("sttime") Date sttime, @Param("endtime") Date endtime);

    @Select("select * from zj_safe_event where singleProjectId = #{projectid} and projectId = #{projectId}")
    List<ZjSafeEvent> getAllStatusSafeByProjectcode(@Param("projectid") Integer projectid,
                                                       @Param("projectId")Integer projectId);

    List<ZjSafeEvent> getGQByUserId(@Param("modifyid")Integer modifyid);

    List<ZjSafeEvent> getGQByGongQuIds(@Param("list") List<Integer> list,
                                       @Param("sttime") Date sttime,
                                       @Param("endtime") Date endtime);

    Integer batchDeleteById(@Param("list") List<Integer> list);

    List<ZjSafeEvent> getDelayByJianLiId(@Param("uploadid")Integer uploadid);

    List<ZjSafeEvent> getRectificationByJianLiId(@Param("uploadid")Integer uploadid);

    @Select("select * from zj_safe_event group by uploadtime desc")
    List<ZjSafeEvent> getAllByTime();

    @Update("update zj_safe_event set projectid = #{projectId} where id = #{id}")
    int updateProjectId(@Param("projectId")Integer projectId,
                        @Param("id")Integer id);

    @Select("select count(1) from zj_safe_event where id = #{id}")
    Integer getById(@Param("id")Integer id);

    @Select("select uploadurl from zj_safe_event where uploadurl is not null and uploadurl != ''")
    List<String> getNotNullUploadurl();
    @Select("select modifyurl from zj_safe_event where modifyurl is not null and modifyurl != ''")
    List<String> getNotNullModifyurl();

}
