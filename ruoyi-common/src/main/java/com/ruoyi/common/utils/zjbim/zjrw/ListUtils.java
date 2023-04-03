package com.ruoyi.common.utils.zjbim.zjrw;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author : Chen_ZhiWei
 * @Date : Create file in 2022/9/1 15:37
 * @Version : 1.0
 * @Description :
 **/
public class ListUtils {

    /**
     * 获取两个集合的并集(自动去重)
     * @param list1
     * @param list2
     * @return
     */
    public static List getUnion(List list1, List list2){
        List union = (List) CollectionUtils.union(list1, list2);
        return union;
    }

    /**
     * 获取两个集合交集
     * @param list1
     * @param list2
     * @return
     */
    public static List getIntersection(List list1,List list2){
        List intersection = (List)CollectionUtils.intersection(list1, list2);
        return intersection;
    }

    /**
     * 获取两个集合交集的补集 即 list1 + list2 - 交集
     * @param list1
     * @param list2
     * @return
     */
    public static List getDisjunction(List list1,List list2){
        List disjunction = (List)CollectionUtils.disjunction(list1, list2);
        return disjunction;
    }

    /**
     * 获取两个集合的差集 list1 - 交集
     * @param list1
     * @param list2
     * @return
     */
    public static List getSubtract(List list1,List list2){
        List subtract = (List)CollectionUtils.subtract(list1, list2);
        return subtract;
    }
}
