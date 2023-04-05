package com.ruoyi.web.controller.jianguan.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.entity.ZjNews;
import com.ruoyi.jianguan.common.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("news")
@RestController
@Api(value="新闻管理")
public class NewsController {


    @Autowired
    NewsService newsService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加新闻", notes = "添加新闻")
    public ResponseBase addNew(@RequestBody ZjNews zjNews){

        return newsService.addNews(zjNews);
    }

    @GetMapping(value = "/del")
    @ApiOperation(value = "删除新闻", notes = "删除新闻")
    public ResponseBase del(@RequestParam("id") Integer id){

        return newsService.del(id);
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "更新新闻", notes = "更新新闻")
    public ResponseBase update(@RequestBody ZjNews zjNews){

        return newsService.update(zjNews);
    }


    @GetMapping(value = "/get")
    @ApiOperation(value = "获取新闻(通过id或者新闻类型)", notes = "获取新闻(id或者新闻类型)")
    public ResponseBase get(@RequestParam(value = "id",required = false) Integer id,
                            @RequestParam(value = "type",required = false)Integer type,
                            @RequestParam(value = "projectId", required = false) Integer projectId){
        if(ObjectUtils.isEmpty(id)&&ObjectUtils.isEmpty(type)){
            return new ResponseBase(500,"请检查参数");
        }
        return newsService.get(id, type, projectId);
    }

    @GetMapping(value = "/getNews")
    @ApiOperation(value = "获取新闻", notes = "获取新闻")
    public ResponseBase getNews(@RequestParam(value = "projectId",required = false) Integer projectId){
        return newsService.getNews(projectId);
    }







}
