package com.ruoyi.jianguan.contract.dao;

import com.ruoyi.jianguan.common.domain.entity.ZjPersonFence;
import com.ruoyi.jianguan.common.domain.entity.ZjPersonFenceTime;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Chen_ZhiWei
 * @since 2022-05-30
 */
@Repository
@Mapper
public interface ZjPersonFenceDAO {

    void newFence(ZjPersonFence fence);

    @Delete("delete from zj_person_fence where id = #{id}")
    void delById(@Param("id")Integer id);

    Integer updateFence(ZjPersonFence fence);

    @Select("select * from zj_person_fence where status = 1 and projectId = #{projectId}")
    List<ZjPersonFence> getAllFence(@Param("projectId")Integer projectId);

    @Select("select * from zj_person_fence where status = 1 " +
            " and projectId = #{projectId} and id = #{id}")
    List<ZjPersonFence> getAllFenceById(@Param("projectId")Integer projectId,
                                    @Param("id")Integer id);

    @Select("select clockAddrName from zj_person_fence where id = #{id}")
    String getAddrNameById(@Param("id")Integer id);




    Integer newFenceTimes(ZjPersonFenceTime fenceTimes);

    @Delete("delete from zj_person_fence_time where id = #{id}")
    void delClockById(@Param("id")Integer id);

    Integer updateFenceTime(ZjPersonFenceTime fenceTime);

    @Select("select postid from zj_person_fence_time where projectId = #{id}")
    List<String> getPostIdsByProjectId(@Param("id")Integer id);

    @Select("select postid from zj_person_fence_time where projectId = #{projectId} and id != #{id}")
    List<String> getAllExceptId(@Param("id")Integer id,
                                @Param("projectId")Integer projectId);

    @Select("select * from zj_person_fence_time where status = 1")
    List<ZjPersonFenceTime> getAllFenceTime();

    @Select("select count(1) from zj_person_fence_time where id = #{id}")
    Integer getCountByClockId(@Param("id")Integer id);

    @Select("select * from zj_person_fence_time where id = #{id} and status = 1")
    List<ZjPersonFenceTime> getAllFenceTimeByGid(@Param("id")Integer id);

    @Select("select * from zj_person_fence_time where postId = #{postId}")
    List<ZjPersonFenceTime> getFenceTimeByPostId(@Param("postId")Integer postId);

    @Select("select name as postName from ss_f_roles where id = #{postId}")
    String getPostNameByPostId(@Param("postId")Integer postId);


}
