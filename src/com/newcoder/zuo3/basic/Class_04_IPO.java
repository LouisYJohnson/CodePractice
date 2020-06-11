package com.newcoder.zuo3.basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Class_04_IPO {
    //输出：
    //你最后获得的最大钱数
    //仍然是贪心算法,每次都选择能投资项目中的利益最高的即可,所以使用大根堆与小根堆
    //小根堆中存放的是不能做的项目(按照成本排序),大根堆中放的是可以做的项目(按照利润排序)
    //在限制k个项目内,每次在小根堆中弹到小于等于手上资金的所有项目,并将项目放入大根堆中,再将大根堆顶peek作为要做的项目,再将手上的钱+利润
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     * @param k 项目限制数
     * @param W 起始资金
     * @param Profits
     * @param Capital
     * @return
     */
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        //将每个项目的所有属性封装成对象
        Node[] nodes = new Node[Profits.length];
        for (int i = 0;i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i],Capital[i]);
        }
        //构建大根堆与小根堆,大根堆负责存放可以做的项目,按照利润排序,小根堆负责放不能考虑的项目,按照成本排序
        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(100, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.p > o2.p) {
                    return -1;
                }else if (o1.p < o2.p) {
                    return 1;
                }else return 0;
            }
        });
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(100, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.c < o2.c) {
                    return -1;
                }else if (o1.c > o2.c) {
                    return 1;
                }else return 0;
            }
        });
        //将所有项目放入小根堆中
        for (int i = 0; i < nodes.length; i++) {
            minHeap.add(nodes[i]);
        }
        //在k个项目中进行操作
        for (int i = 0; i < k;i++) {
            //将所有投入小于等于W的项目都拿出来(在小根堆项目堆不为空的情况下)
            while (!minHeap.isEmpty()) {
                if (minHeap.peek().c <= W) {
                    maxHeap.add(minHeap.poll());
                }
            }
            //以大堆中堆顶的元素作为本次投资项目
            while (!maxHeap.isEmpty()) {
                W += maxHeap.peek().p;
            }
        }
        return W;

    }
}
