package com.ruoyi.czjg.zjrw.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.dao.ProjectsDAO;
import com.ruoyi.czjg.zjrw.dao.ZjPersonFenceDAO;
import com.ruoyi.czjg.zjrw.domain.dto.PostDTO;
import com.ruoyi.czjg.zjrw.domain.entity.ZjPersonFence;
import com.ruoyi.czjg.zjrw.domain.entity.ZjPersonFenceTime;
import com.ruoyi.common.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/30 16:09
 * @Version : 1.0
 * @Description : 电子围栏
 **/
@Service
public class FenceService {

    Logger log = LoggerFactory.getLogger(FenceService.class);

    @Autowired
    private ZjPersonFenceDAO fenceDAO;
    @Autowired
    private ProjectsDAO projectsDAO;


    public ResponseBase addFence(ZjPersonFence fence){
        if (fence.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(fence.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        fence.setStatus(1);
        fence.setOrder(1);
        fenceDAO.newFence(fence);

        return new ResponseBase(200, "新增成功!");
    }

    @Transactional
    public ResponseBase delFence(Integer projectId, Integer id){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        fenceDAO.delById(id);
        return new ResponseBase(200, "删除成功!");
    }

    @Transactional
    public ResponseBase updateFence(@RequestBody ZjPersonFence fence){
        if (fence.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(fence.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer row =  fenceDAO.updateFence(fence);
        if (row == 1){
            return new ResponseBase(200, "修改成功!");
        }
        return new ResponseBase(511, "修改失败!");
    }

    public ResponseBase getFence(Integer projectId, Integer fenceId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<ZjPersonFence> fences = Lists.newArrayList();
        if (fenceId != null && !fenceId.equals("")){
            fences = fenceDAO.getAllFenceById(projectId, fenceId);
        } else {
            fences = fenceDAO.getAllFence(projectId);
        }
        return new ResponseBase(200, "查询成功!", fences);
    }

    public ResponseBase addClock(ZjPersonFenceTime fenceTime){
        if (fenceTime.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(fenceTime.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<String> postIds = fenceDAO.getPostIdsByProjectId(fenceTime.getProjectId());
        //数据取到的postIds
        List<Integer> postids = Lists.newArrayList();
        StringBuffer sb = new StringBuffer();
        for (String postId : postIds) {
            List<String> strPost = Arrays.asList(postId.split(","));
            for (String s : strPost) {
                postids.add(Integer.parseInt(s));
            }
        }
        List<String> strPostIds = Arrays.asList(fenceTime.getPostId().split(","));
        //前端传的postIds
        for (String strPostId : strPostIds) {
            if (postids.contains(Integer.parseInt(strPostId))) {
                continue;
            }
            sb.append(strPostId + ",");
        }
        fenceTime.setPostId(sb.substring(0, sb.length()-1));

        fenceTime.setStatus(1);
        fenceTime.setOrder(1);

        Integer row = fenceDAO.newFenceTimes(fenceTime);
        if (row == 1){
            return new ResponseBase(200, "添加打卡信息成功!");
        }
        return new ResponseBase(511, "添加打卡信息失败!");
    }

    @Transactional
    public ResponseBase delClock(Integer projectId, Integer id){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer count2 = fenceDAO.getCountByClockId(id);
        if (count2 <= 0){
            return new ResponseBase(513, "该打卡id无数据!");
        }
        fenceDAO.delClockById(id);
        return new ResponseBase(200, "删除成功!");
    }

    public ResponseBase updateClock(ZjPersonFenceTime fenceTime){
        if (fenceTime.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(fenceTime.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }

        //数据取到的postIds
        List<String> strPostIds = fenceDAO.getAllExceptId(fenceTime.getId(), fenceTime.getProjectId());
        List<Integer> postids = Lists.newArrayList();
        StringBuffer sb = new StringBuffer();
        for (String postId : strPostIds) {
            List<String> strPost = Arrays.asList(postId.split(","));
            for (String s : strPost) {
                postids.add(Integer.parseInt(s));
            }
        }
        List<String> strPostIds1 = Arrays.asList(fenceTime.getPostId().split(","));
        //前端传的postIds
        for (String strPostId : strPostIds1) {
            if (postids.contains(Integer.parseInt(strPostId))) {
                continue;
            }
            sb.append(strPostId + ",");
        }

        List<Integer> intPostids = Lists.newArrayList();

        if (strPostIds.size() == 0){
            strPostIds = fenceDAO.getPostIdsByProjectId(fenceTime.getProjectId());
        }
        for (String postId : strPostIds) {
            List<String> strPost = Arrays.asList(postId.split(","));
            for (String s : strPost) {
                Integer intPost = Integer.parseInt(s);
                intPostids.add(intPost);
            }
        }

        fenceTime.setPostId(sb.substring(0, sb.length()-1));

        Integer row =  fenceDAO.updateFenceTime(fenceTime);
        if (row == 1){
            return new ResponseBase(200, "修改打卡信息成功!");
        }
        return new ResponseBase(511, "修改打卡信息失败!");
    }

    public ResponseBase getClock(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }

        List<ZjPersonFenceTime> fenceTimes = fenceDAO.getAllFenceTime();
        for (ZjPersonFenceTime fenceTime : fenceTimes) {
            List<PostDTO> postDTOS = Lists.newArrayList();
            String strPostId = fenceTime.getPostId();
            List<String> strPostIds1 = Arrays.asList(strPostId.split(","));
            for (String strPostId2 : strPostIds1) {
                Integer id = Integer.parseInt(strPostId2);
                String name = fenceDAO.getPostNameByPostId(id);

                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(id);
                postDTO.setPostName(name);
                postDTOS.add(postDTO);
            }
            fenceTime.setPosts(postDTOS);
        }



        return new ResponseBase(200, "查询成功!", fenceTimes);
    }

    public ResponseBase getClockByUserId(Integer id){
        if (id <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(id);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer roleId = JwtUtil.getTokenUser().getRole();
        List<ZjPersonFenceTime> fenceTimes = fenceDAO.getFenceTimeByPostId(roleId);

        for (ZjPersonFenceTime fenceTime : fenceTimes) {
            List<PostDTO> postDTOS = Lists.newArrayList();
            String strPostId = fenceTime.getPostId();
            List<String> strPostIds1 = Arrays.asList(strPostId.split(","));
            for (String strPostId2 : strPostIds1) {
                String name = fenceDAO.getPostNameByPostId(Integer.parseInt(strPostId));

                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(Integer.parseInt(strPostId2));
                postDTO.setPostName(name);
                postDTOS.add(postDTO);
            }
            fenceTime.setPosts(postDTOS);
        }


        return new ResponseBase(200, "查询成功!", fenceTimes);
    }

}
