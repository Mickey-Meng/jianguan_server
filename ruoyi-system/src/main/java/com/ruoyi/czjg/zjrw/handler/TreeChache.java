package com.ruoyi.czjg.zjrw.handler;

import com.ruoyi.czjg.zjrw.dao.ConponentDAO;
import com.ruoyi.czjg.zjrw.dao.ZjConponentProducetimeDAO;
import com.ruoyi.czjg.zjrw.dao.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.BaseTree;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.czjg.zjrw.domain.entity.ZjFGroupsProjects;
import com.ruoyi.common.utils.zjbim.zjrw.CacheProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/10 上午10:18
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Component
public class TreeChache implements CommandLineRunner {
    @Autowired
    ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;

    @Autowired
    ConponentDAO conponentDAO;

    @Autowired
    ZjConponentProducetimeDAO zjConponentProducetimeDAO;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start");
        List<ZjFGroupsProjects> all = zjFGroupsProjectsDAO.getAll();
        for (ZjFGroupsProjects zjFGroupsProject : all) {
            List<Conponent> list = conponentDAO.getAllProject(zjFGroupsProject.getProjectid());
            //获取项目的pid
            Conponent target = new Conponent();
            BaseTree baseTree = new BaseTree();
            List<BaseTree> temContaner = new ArrayList<>();
            for (Conponent conponent : list) {
                BaseTree temp = CacheProject.toBeBaseTree(conponent);
                temContaner.add(temp);
                if(!ObjectUtils.isEmpty(conponent.getCode())&&conponent.getCode().equals(conponent.getProjectcode())){
                    target = conponent;
                    baseTree = temp;
                };
            }

            List<BaseTree> child = getChildrenBase(temContaner,baseTree);

            baseTree.setChild(child);
            //缓存
            CacheProject.PROJECTCACHE.put(target.getProjectcode(),baseTree);
        }
        //根据项目，获取项目所属的数据
        System.out.println("end");
    }

    private List<BaseTree> getChildrenBase(List<BaseTree> temContaner, BaseTree baseTree) {
        List<BaseTree> children = temContaner.stream().filter(item -> Integer.compare(item.getPid(), baseTree.getId())==0)
                .map((item) -> {
                    item.setChild(getChildrenBase(temContaner, item));
                    return item;
                }).collect(Collectors.toList());

        return children;
    }

    private  List<Conponent> getChildren(Conponent root, List<Conponent> all){
        List<Conponent> children = all.stream().filter(item -> Integer.compare(item.getPid(),root.getId())==0)
                .map((item) -> {
                    item.setChild(getChildren(item, all));
                    return item;
                }).collect(Collectors.toList());

        return children;
    }




}
