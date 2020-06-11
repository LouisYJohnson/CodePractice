package com.newcoder.zuo3.basic.class05;

import java.util.*;

public class Class_05_Prim {
    //P算法
    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(100,new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                //如果node不在set中,则node加入set(注册),并将这个node的所有边加入小根堆中
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    //每次从小根堆中弹出一个边,并检查这个边所连的节点是否已经注册过了
                    //如果注册了,弹出就弹出了,如果没注册,先将边保存
                    // 再将边对应的节点注册后将节点对应的新边压入队列
                    if (set.contains(priorityQueue.poll().to)) {
                        Edge edge = priorityQueue.poll();
                        if (!set.contains(edge.to)) {
                            result.add(edge);
                            set.add(edge.to);
                            for (Edge edge1 : edge.to.edges) {
                                priorityQueue.add(edge1);
                            }
                        }
                    }
                }

            }
        }
        return result;


    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.weight > o2.weight) {
                return 1;
            }else if (o1.weight < o2.weight) {
                return -1;
            }else return 0;
        }
    }
}
