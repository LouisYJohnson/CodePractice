package com.newcoder.zuo3.basic.class05;

import java.util.*;

public class Class_05_TopologySort {
    //拓扑排序
    //将所有入度为0的节点打印(不打也行,放队列里一次弹出也一样),并放入队列,然后删除,图中会有新的节点入度为0
    //然后将新一轮的入度为0的节点放入队列,删除,(打印),直到图中没有点为止
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node,Integer> inMap = new HashMap<>();//不能改变原图的结构,所以引入这个辅助
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {//遍历graph中所有的节点
            inMap.put(node,node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (zeroInQueue.size() != 0) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {//遍历这个入度为0所有的子节点,并将子节点的入度-1
                //next.in--;这里不能这么写是因为不能改变原图的结构,所以要加入inMap辅助记录原图中每个节点的入度 !!
                inMap.put(next,inMap.get(next) - 1);//不能改变原图结构,所以此处不能写--  !!
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;

    }
}
