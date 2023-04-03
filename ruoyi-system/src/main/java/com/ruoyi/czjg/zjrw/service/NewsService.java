package com.ruoyi.czjg.zjrw.service;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.dao.FileMapper;
import com.ruoyi.czjg.zjrw.dao.ProjectsDAO;
import com.ruoyi.czjg.zjrw.dao.ZjNewsDAO;
import com.ruoyi.czjg.zjrw.domain.entity.ZjNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {


    @Autowired
    ZjNewsDAO zjNewsDAO;
    @Autowired
    private ProjectsDAO projectsDAO;
    @Autowired
    private FileMapper fileMapper;
    public ResponseBase addNews(ZjNews zjNews) {
        zjNewsDAO.insert(zjNews);
        return new ResponseBase(200,"添加成功");

    }

    public ResponseBase del(Integer id) {
        ZjNews zjNews = zjNewsDAO.getById(id);
        String pic = zjNews.getPic();
        //先删除数据库里面存的文件
        fileMapper.delete(pic);
        //再删除对应数据信息
        zjNewsDAO.deleteByPrimaryKey(id);
        return new ResponseBase(200,"删除成功");
    }

    public ResponseBase update(ZjNews zjNews) {
        zjNewsDAO.updateByPrimaryKeySelective(zjNews);
        return new ResponseBase(200,"更新成功");
    }

    public ResponseBase get(Integer id, Integer type, Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<ZjNews> list = new ArrayList<>();
        if( ObjectUtils.isEmpty(id)){
            list = zjNewsDAO.getbyType(type, projectId);
        } else if(ObjectUtils.isEmpty(type)){
            ZjNews zjNews = zjNewsDAO.selectByPrimaryKey(id, projectId);
            list.add(zjNews);
        }
        return new ResponseBase(200,"查询成功",list);
    }

    public ResponseBase getNews(Integer projectId){
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        List<ZjNews> list = zjNewsDAO.getNews(projectId);

        return new ResponseBase(200, "查询成功", list);
    }
}
