package com.ruoyi.jianguan.common.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.ZjFGroupsProjects;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.dao.ConponentDAO;
import com.ruoyi.jianguan.common.domain.entity.NodeTree;

import com.ruoyi.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/9/30 下午2:08
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;


    @Autowired
    private SsFGroupsDAO ssFGroupsDAO;
    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;
    @Autowired
    @Qualifier("zjFGroupsProjectsDAO")
    private ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;

    @Autowired
    private ConponentDAO conponentDAO;

    public List<NodeTree> cacheByProject() {
        List<ZjFGroupsProjects> all = zjFGroupsProjectsDAO.getAll();
        System.out.println(all.size());
        List<NodeTree> s= Lists.newArrayList();
        //缓存左侧树
        all.forEach(zjFGroupsProject -> {
            NodeTree nodeTree = new NodeTree();
            String projectid = zjFGroupsProject.getProjectid();
            nodeTree.setCode(projectid);
            nodeTree.setName(zjFGroupsProject.getProjectname());
            nodeTree.setMouldId(zjFGroupsProject.getProjectname());
            List<Conponent> conponentList =conponentDAO.
                    getProjectConponentByProjectId(projectid);
            //设置 子节点
            nodeTree=toBeNotree(nodeTree,conponentList);
            redisTemplate.opsForValue().set(projectid, nodeTree);
            s.add(nodeTree);
        });
        // 缓存 工序左侧树
        return s;
    }

    private String getItemName(String item) {
        if("QL".equals(item)){
            return "桥梁工程";
        }else if("DD".equals(item)){
            return "地道工程";
        }else{
            return "隧道工程";
        }
    }


    //对conponentList 进行 树状排序
    private NodeTree toBeNotree(NodeTree nodeTree,List<Conponent> conponentList){

        Map<String, List<Conponent>> w4 = conponentList.stream().
                collect(Collectors.groupingBy(Conponent::getW4CodeAndW4Name));
        List<NodeTree> listw4= Lists.newArrayList();
        //上下部结构
        w4.forEach((key,listw5)->{
            NodeTree nodeTreew4 = new NodeTree();
            String[] s = key.split("_");
            nodeTreew4.setCode(s[0]);
            nodeTreew4.setName(s[1]);
            nodeTreew4.setMouldId(key);
            List<NodeTree> listw5s= Lists.newArrayList();
            //分项
            Map<String, List<Conponent>> collect = listw5.stream().collect(
                    Collectors.groupingBy(Conponent::getW5CodeAndW5Name));
            collect.forEach((keys,list6)->{
                NodeTree nodeTreew5 = new NodeTree();
                String[] s1 = keys.split("_");
                nodeTreew5.setCode(s1[0]);
                nodeTreew5.setName(s1[1]);
                nodeTreew5.setMouldId(keys);
                List<NodeTree> listw6s= Lists.newArrayList();
                //类型分类
                Map<String, List<Conponent>> collect1 = list6.stream().collect(
                        Collectors.groupingBy(Conponent::getW6CodeAndW6Name));
                //dz   gl
                collect1.forEach((keyss,list7)->{
                    NodeTree nodeTreew6 = new NodeTree();
                    String[] s11 = keyss.split("_");
                    nodeTreew6.setCode(s11[0]);
                    nodeTreew6.setName(s11[1]);
                    nodeTreew6.setMouldId(keyss);
                    List<NodeTree> nodeTrees7 = Lists.newArrayList();
                    if(list7.size()>1){
                        Map<String, List<Conponent>> collect2 = list7.stream().collect(Collectors.groupingBy(Conponent::getW7CodeAndW7Name));
                        collect2.forEach((keysss,list8)->{
                            NodeTree nodeTree1 = new NodeTree();
                            String[] s111 = keysss.split("_");
                            nodeTree1.setCode(s111[0]);
                            nodeTree1.setName(s111[1]);
                            nodeTree1.setMouldId(keysss);
                            List<NodeTree> nodeTrees8 = Lists.newArrayList();
                            list8.stream().forEach((list99)->{
                                NodeTree nodeTree11 = new NodeTree();
                                List<Conponent> one =Lists.newArrayList();
                                one.add(list99);
                                nodeTree11.setChild(new ArrayList<>());
                                nodeTree11.setChildConponent(one);
                                nodeTree11.setCode(list99.getCode());
                                nodeTree11.setX(list99.getW9code());
                                nodeTree11.setY(list99.getW10());
                                nodeTree11.setZ(list99.getW10code());
                                nodeTree11.setName(list99.getW7());
                                nodeTree11.setMouldId(list99.getMouldid());
                                nodeTree11.setId(list99.getId());
                                nodeTrees8.add(nodeTree11);
                            });
                            nodeTree1.setChild(nodeTrees8);
                            nodeTrees7.add(nodeTree1);
                        });
                    }else{
                        NodeTree nodeTree1 = new NodeTree();
                        nodeTree1.setChild(new ArrayList<>());
                        nodeTree1.setName(list7.get(0).getW6());
                        nodeTree1.setCode(list7.get(0).getW6code());
                        nodeTree1.setX(list7.get(0).getW9code());
                        nodeTree1.setY(list7.get(0).getW10());
                        nodeTree1.setZ(list7.get(0).getW10code());
                        nodeTree1.setName(list7.get(0).getW6());
                        nodeTree1.setMouldId(list7.get(0).getMouldid());
                        nodeTree1.setId(list7.get(0).getId());

                        nodeTree1.setChildConponent(list7);
                        nodeTrees7.add(nodeTree1);
                    }
                    nodeTreew6.setChild(nodeTrees7);
                    listw6s.add(nodeTreew6);
                });
                nodeTreew5.setChild(listw6s);
                listw5s.add(nodeTreew5);
            });
            nodeTreew4.setChild(listw5s);
            listw4.add(nodeTreew4);
        });
        nodeTree.setChild(listw4);
        return  nodeTree;
    }


    public ResponseBase getTree() {
        PowerData tokenUser = JwtUtil.getTokenUser();
        Integer userId = JwtUtil.getTokenUser().getId();
        List<Integer> gongqus = ssFUserGroupDAO.getGroupsOfProjectByUserId(userId);
        NodeTree nodeTree1 =new NodeTree();
        nodeTree1.setName("235项目");
        nodeTree1.setCode("0");
        nodeTree1.setMouldId("235项目");
        List<NodeTree> list =Lists.newArrayList();
        //根据工区查询 项目
        gongqus.stream().forEach((gongquid)->{
            SsFGroups ssFGroups = ssFGroupsDAO.selectByPrimaryKey(gongquid);
            NodeTree nodeTree2 =new NodeTree();
            nodeTree2.setName(ssFGroups.getName());
            nodeTree2.setCode(ssFGroups.getId().toString());
            nodeTree2.setMouldId(gongquid.toString());
            List<NodeTree> listss =Lists.newArrayList();
            List<SsFGroups> byParentId = ssFGroupsDAO.getByParentId(gongquid);
            byParentId.forEach((byParent)->{
                ZjFGroupsProjects projectCodeByProjectId = zjFGroupsProjectsDAO.getProjectCodeByProjectId(byParent.getId());
                Object o = redisTemplate.opsForValue().get(projectCodeByProjectId.getProjectid());
                String JSONStr = JSON.toJSONString(o);
                NodeTree nodeTree = JSON.parseObject(JSONStr, NodeTree.class);
                nodeTree.setMouldId(byParent.getName());
                listss.add(nodeTree);
            });
            nodeTree2.setChild(listss);
            list.add(nodeTree2);
        });
        nodeTree1.setChild(list);

        //根据项目号进行查询
        return new ResponseBase(200,"查询成功",nodeTree1);

    }

    public List<NodeTree> cacheProduce() {
        List<ZjFGroupsProjects> all = zjFGroupsProjectsDAO.getAll();
        NodeTree nodeTree =new NodeTree();
        all.forEach(zjFGroupsProject -> {
            List<String> projects =Lists.newArrayList();
            projects.add(zjFGroupsProject.getProjectid());
            List<Conponent> conponentList = conponentDAO.getConponentByRule(projects);
            nodeTree.setName(zjFGroupsProject.getProjectname());
            nodeTree.setCode(zjFGroupsProject.getProjectid());
            List<NodeTree> childNodes = Lists.newArrayList();
            // 形成树
            Map<String, List<Conponent>> typeGroup = conponentList.stream().
                    collect(Collectors.groupingBy(Conponent::groupType));
            typeGroup.forEach((splitName,conponents1)->{
                String[] ss = splitName.split("_");
                NodeTree nodeTree1= new NodeTree();
                nodeTree1.setName(ss[0]);
                nodeTree1.setCode(ss[1]);
//                nodeTree1.setChildConponent(conponents1);
                childNodes.add(nodeTree1);
            });
            nodeTree.setChild(childNodes);
            redisTemplate.opsForValue().set(zjFGroupsProject.getProjectid()+"_"+"produce", nodeTree);
        });
        return null;
    }

    public void cacheProducex() {
        List<ZjFGroupsProjects> all = zjFGroupsProjectsDAO.getAll();
        NodeTree nodeTree =new NodeTree();
        all.forEach(zjFGroupsProject -> {
            List<String> projects =Lists.newArrayList();
            projects.add(zjFGroupsProject.getProjectid());
            List<Conponent> conponentList = conponentDAO.getConponentByRule(projects);
            nodeTree.setName(zjFGroupsProject.getProjectname());
            nodeTree.setCode(zjFGroupsProject.getProjectid());
            List<NodeTree> childNodes = Lists.newArrayList();
            // 形成树
            Map<String, List<Conponent>> typeGroup = conponentList.stream().
                    collect(Collectors.groupingBy(Conponent::groupType));
            typeGroup.forEach((splitName,conponents1)->{
                String[] ss = splitName.split("_");
                NodeTree nodeTree1= new NodeTree();
                nodeTree1.setName(ss[0]);
                nodeTree1.setCode(ss[1]);
                redisTemplate.opsForValue().set(zjFGroupsProject.getProjectid()+"_"+ss[1], conponents1);
//                nodeTree1.setChildConponent(conponents1);
                childNodes.add(nodeTree1);
            });
            nodeTree.setChild(childNodes);

            redisTemplate.opsForValue().set(zjFGroupsProject.getProjectid()+"_"+"produce", nodeTree);
        });


    }
}


