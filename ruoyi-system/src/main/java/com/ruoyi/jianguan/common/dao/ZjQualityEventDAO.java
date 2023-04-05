package com.ruoyi.jianguan.common.dao;

import com.ruoyi.jianguan.common.domain.entity.SafeGongQugroup;
import com.ruoyi.jianguan.common.domain.entity.SafeGongQugroupOverdue;
import com.ruoyi.jianguan.common.domain.entity.ZjQualityEvent;
import com.ruoyi.jianguan.common.domain.dto.EventGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ZjQualityEventDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ZjQualityEvent record);

    int insertSelective(ZjQualityEvent record);

    ZjQualityEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZjQualityEvent record);

    int updateByPrimaryKey(ZjQualityEvent record);

    @Select("select * from zj_quality_event where status = 0 and (modifyid = #{userid} or uploadid = #{userid}) " +
            " and projectId = #{projectId}")
    List<ZjQualityEvent> getSafeEventByModify(@Param("userid") Integer userid,
                                              @Param("projectId")Integer projectId);


    @Select("select * from zj_quality_event where  uploadid = #{userid}  and status = 3")
    List<ZjQualityEvent> getDoneSafeEventByModify(@Param("userid")Integer userid);

    @Select("select * from zj_quality_event where  uploadid = #{userid}  and status = 1 and projectId = #{projectId}")
    List<ZjQualityEvent> getDelaySafeEventByModify(@Param("userid")Integer userid,
                                                   @Param("projectId")Integer project);

    @Select("select * from zj_quality_event where  uploadid = #{userid}  and status = 2 and projectId = #{projectId}")
    List<ZjQualityEvent> getNotDoneSafeEvent(@Param("userid")Integer userid,
                                             @Param("projectId")Integer projectId);

    @Select("select * from zj_quality_event where singleProjectId = #{projectid} and projectId = #{projectId}")
    List<ZjQualityEvent> getAllStatusSafeByProjectcode(@Param("projectid") Integer projectid,
                                                       @Param("projectId")Integer projectId);

    @Select("select * from zj_quality_event where gongquid in   ( ${userid} )")
    List<ZjQualityEvent> getAllStatusQualityByModify(@Param("userid") String userid);

    List<ZjQualityEvent> getGongquData(String toString);


    List<SafeGongQugroup> getAllStatusCount(@Param("list") List<Integer> list, @Param("sttime") Date sttime, @Param("endtime") Date endtime);

    List<SafeGongQugroupOverdue> getOverdueCount(@Param("list") List<Integer> list);

//    @Select("select count(1) as count, gongquid ,qualityfirstname as safefirstname from zj_quality_event  where gongquid in (${abc}) group by gongquid ,safefirstname")
//    List<EventGroup> group(@Param("abc") String abc);

    List<EventGroup> group(@Param("list") List<Integer> list, @Param("sttime") Date sttime, @Param("endtime") Date endtime);


    List<ZjQualityEvent> getGQ(@Param("sttime") Date sttime, @Param("endtime") Date endtime);

    List<ZjQualityEvent> getByGQ(@Param("gqid") Integer gqid, @Param("sttime") Date sttime, @Param("endtime") Date endtime);

    @Select("select a.id from ss_f_roles a \n" +
            "left join ss_f_user_role b \n" +
            "on a.id = b.ROLEID\n" +
            "where b.userid = #{userid}")
    Integer getRole(@Param("userid") Integer userid);

    @Select("select * from zj_quality_event where projectid = #{projectdoce}")
    List<ZjQualityEvent> getAllStatusQualityByProjectcode(@Param("projectdoce") Integer projectdoce);

    List<ZjQualityEvent> getGQByUserId(@Param("modifyid")Integer modifyid);

    List<ZjQualityEvent> getGQByGongQuIds(@Param("list") List<Integer> list,
                                          @Param("sttime") Date sttime,
                                          @Param("endtime") Date endtime);

    List<ZjQualityEvent> getDataById(List<Integer> list);

    Integer batchDeleteById(@Param("list") List<Integer> list);

    List<ZjQualityEvent> getDelayByJianLiId(@Param("uploadid")Integer uploadid);

    List<ZjQualityEvent> getRectificationByJianLiId(@Param("uploadid")Integer uploadid);

    @Select("select * from zj_quality_event group by uploadtime desc")
    List<ZjQualityEvent> getAllByTime();

    @Update("update zj_quality_event set projectid = #{projectId} where id = #{id}")
    int updateProjectId(@Param("projectId")Integer projectId,
                        @Param("id")Integer id);

    @Select("select count(1) from zj_quality_event where id = #{id}")
    Integer getById(@Param("id")Integer id);

    @Select("select uploadurl from zj_quality_event where uploadurl is not null and uploadurl != ''")
    List<String> getNotNullUploadurl();
    @Select("select modifyurl from zj_quality_event where modifyurl is not null and modifyurl != ''")
    List<String> getNotNullModifyurl();
}
