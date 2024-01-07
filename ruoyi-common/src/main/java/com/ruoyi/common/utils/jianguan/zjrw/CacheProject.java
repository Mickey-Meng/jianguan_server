package com.ruoyi.common.utils.jianguan.zjrw;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.BaseTree;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.model.ItemCount;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/10 上午10:20
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class CacheProject {
    public static Map<String, BaseTree> PROJECTCACHE = Maps.newHashMap();

    public static Map<String, ItemCount> PROJECTITEM =Maps.newHashMap();

    public static BaseTree toBeBaseTree(Conponent conponent){
        BaseTree baseTree = new BaseTree();

        baseTree.setId(conponent.getId());
        baseTree.setX(conponent.getW9code());
        baseTree.setY(conponent.getW10());
        baseTree.setZ(conponent.getW10code());
        baseTree.setMouldid(conponent.getMouldid());
        baseTree.setName(conponent.getName());
        baseTree.setPid(conponent.getPid());
        baseTree.setPname(conponent.getPname());
        baseTree.setWbstcode(conponent.getWbscode());
        baseTree.setConponetcode(ObjectUtils.isEmpty(conponent.getConponentcode())?"":conponent.getConponentcode());
        return baseTree;
    }
}
