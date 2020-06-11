package com.newcoder.zuo3.basic.class05;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Class_05_BFS {
    //宽度优先遍历
    //使用队列+set(注册节点)实现
    //从头节点开始,放入队列中,并在set中注册
    //1.从队列中弹出当前节点找到该节点的所有邻居节点,如果在set中没有,就把他们放入队列中并在set中加入这些邻居节点(注册)
    //2.如果这些邻居节点中有在set中出现的,就不加入队列
    //3.每次队列弹出一个元素,作为遍历到的元素,并将该元素未注册过的邻居节点加入队列并注册
    //重复1,2,3直到弹出的节点的所有节点都被注册过为止
    public static void bfs(Node node) {
        if (node == null) return;
        HashSet<Node> set = new HashSet<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
