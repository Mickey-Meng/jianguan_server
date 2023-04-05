package com.ruoyi.common.utils.jianguan.zjrw;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/21 2:51 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class NodeTreeUtil {

//    public static NodeTree getNodeTree(Conponent conponent){
//        NodeTree nodeTree = new NodeTree();
//        List<String> items = Arrays.asList(conponent.getConponentcode().split("-"));
//        Node nodeW1 =  new Node();
//        nodeW1.setName(conponent.getW1());
//        nodeW1.setCode(conponent.getW1code());
//        NodeTree nodeTreeW1 =new NodeTree();
//        nodeTreeW1.setNode(nodeW1);
//
//        Node nodeW2 =new Node();
//        nodeW2.setName(conponent.getW2());
//        nodeW2.setCode(conponent.getW2code());
//        NodeTree nodeTreeW2 =new NodeTree();
//        nodeTreeW2.setNode(nodeW2);
//
//        Node nodeW3 =new Node();
//        nodeW3.setName(conponent.getW3());
//        nodeW3.setCode(conponent.getW3code());
//        NodeTree nodeTreeW3 =new NodeTree();
//        nodeTreeW3.setNode(nodeW3);
//
//        Node nodeW4= new Node();
//        nodeW4.setName(conponent.getW4());
//        nodeW4.setCode(conponent.getW4code());
//        NodeTree nodeTreeW4 =new NodeTree();
//        nodeTreeW4.setNode(nodeW4);
//
//        Node nodeW5= new Node();
//        NodeTree nodeTreeW5 =new NodeTree();
//
//        if(items.size()>=5){
//            nodeW5.setName(conponent.getW5());
//            nodeW5.setCode(conponent.getW5code());
//            nodeTreeW5.setNode(nodeW5);
//        }
//        Node nodeW6= new Node();
//        NodeTree nodeTreeW6 =new NodeTree();
//        if(items.size()==6){
//            nodeW6.setCode(conponent.getW6code());
//            nodeW6.setName(conponent.getW6());
//            if(!ObjectUtils.isEmpty(conponent.getMouldid())&&conponent.getMouldid()!=null){
//                nodeW6.setMouldId(conponent.getMouldid());
//            }
//            nodeTreeW6.setNode(nodeW6);
//        }
//        //拼接nodeTree
//        if(items.size()==4){
//            nodeTreeW3.setChild(Arrays.asList(nodeTreeW4));
//            nodeTreeW2.setChild(Arrays.asList(nodeTreeW3));
//            nodeTreeW1.setChild(Arrays.asList(nodeTreeW2));
//        }else if(items.size()==5){
//            nodeTreeW4.setChild(Arrays.asList(nodeTreeW5));
//            nodeTreeW3.setChild(Arrays.asList(nodeTreeW4));
//            nodeTreeW2.setChild(Arrays.asList(nodeTreeW3));
//            nodeTreeW1.setChild(Arrays.asList(nodeTreeW2));
//        }else if(items.size()==6){
//            nodeTreeW5.setChild(Arrays.asList(nodeTreeW6));
//            nodeTreeW4.setChild(Arrays.asList(nodeTreeW5));
//            nodeTreeW3.setChild(Arrays.asList(nodeTreeW4));
//            nodeTreeW2.setChild(Arrays.asList(nodeTreeW3));
//            nodeTreeW1.setChild(Arrays.asList(nodeTreeW2));
//        }
//        return nodeTreeW1;
//    }

    public static <K extends Comparable, V extends Comparable> Map<K, V> sortMapByValues(Map<K, V> aMap) {
        HashMap<K, V> finalOut = new LinkedHashMap<>();
        aMap.entrySet()
                .stream()
                .sorted((p1, p2) -> p1.getValue().compareTo(p2.getValue()))
                .collect(Collectors.toList()).forEach(ele -> finalOut.put(ele.getKey(), ele.getValue()));
        return finalOut;
    }



}
