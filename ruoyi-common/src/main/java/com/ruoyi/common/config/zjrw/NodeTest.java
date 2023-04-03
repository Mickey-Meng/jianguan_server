package com.ruoyi.common.config.zjrw;//package com.ruoyi.common.config.zjrw;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * @author ：lin_zhixiong
// * @date ：Created in 2021/9/13 上午11:17
// * @version: V1.0
// * @slogan: 天下风云出我辈，一入代码苦耕耘
// * @description:
// **/
//public class NodeTest {
//
//
//    public static void main(String[] args) {
//
//
//        ArrayList<Integer> a = new ArrayList();
//        a.add(2);a.add(3);
//
//        NodeOil one =new NodeOil(1,a);
//
//        ArrayList<Integer> b = new ArrayList();
//        b.add(5);
//        NodeOil two =new NodeOil(2,b);
//
//        ArrayList<Integer> c = new ArrayList();
//        c.add(4);
//        NodeOil three =new NodeOil(3,c);
//
//        ArrayList<Integer> d = new ArrayList();
//        c.add(5);
//        NodeOil four =new NodeOil(4,d);
//
//
//        ArrayList<Integer> e = new ArrayList();
//        NodeOil five =new NodeOil(5,e);
//
//        List<NodeOil> data =new ArrayList<>();
//
//        data.add(one);
//        data.add(two);
//        data.add(three);
//        data.add(four);
//        data.add(five);
//
//        List<LinkedList> res = deal(data);
//    }
//
//    private static List<LinkedList> deal(List<NodeOil> data) {
//        List<LinkedList> res = new ArrayList<>();
//
//        List<NodeOil> collect = data.stream().filter((nodeOil)->nodeOil.getId()==1).collect(Collectors.toList());
//
//
//        return res;
//
//    }
//}
//
//class NodeOil{
//    private int id;
//    private ArrayList tailNode ;
//
//
//    public NodeOil(int id, ArrayList tailNode) {
//        this.id = id;
//        this.tailNode = tailNode;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public ArrayList getTailNode() {
//        return tailNode;
//    }
//
//    public void setTailNode(ArrayList tailNode) {
//        this.tailNode = tailNode;
//    }
//}

