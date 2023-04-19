package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.entity.NodeTree;
import com.ruoyi.jianguan.common.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/9/30 下午1:44
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@RestController
@RequestMapping("/redis")
@Api(value="缓存管理")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService ;

    @RequestMapping("setAndGet")
    @ResponseBody
    public String setAndGet(String name,String value){
        redisTemplate.opsForValue().set(name,value);
        return (String)redisTemplate.opsForValue().get(name);
    }

    //根据项目缓存数据
    @RequestMapping("cache")
    @ResponseBody
    public ResponseBase cache(){
        List<NodeTree> nodeTrees = redisService.cacheByProject();
        return new ResponseBase(200,"c",nodeTrees);
    }

    @RequestMapping("cacheProduce")
    @ResponseBody
    public ResponseBase cacheProduce(){
        List<NodeTree> nodeTrees = redisService.cacheProduce();
        redisService.cacheProducex();
        return new ResponseBase(200,"c",nodeTrees);
    }

    @RequestMapping("get")
    @ResponseBody
    public ResponseBase getProjectId(String projectid){
        Object o = redisTemplate.opsForValue().get(projectid);
        String JSONStr = JSON.toJSONString(o);
        NodeTree nodeTree = JSON.parseObject(JSONStr, NodeTree.class);
        return new ResponseBase(200,"c",nodeTree);
    }

}
