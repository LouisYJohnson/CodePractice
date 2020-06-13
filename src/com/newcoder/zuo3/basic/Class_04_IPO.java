package com.newcoder.zuo3.basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Class_04_IPO {
    //输出：
    //你最后获得的最大钱数
    //仍然是贪心算法,每次都选择能投资项目中的利益最高的即可,
    //所以使用大根堆装能做的项目(花费小于等于手上钱的项目)与小根堆装不能做的项目(花费大于手上钱的项目)
    //小根堆只是为了方便确定哪些项目能做(小根堆方便取东西,因为堆顶是最小),大根堆才是真正的确定哪些项目被做
    //小根堆中存放的是不能做的项目(按照成本排序),大根堆中放的是可以做的项目(按照利润排序)
    //在限制k个项目内,每次在小根堆(不能做的项目堆)将小于等于手上资金的所有项目,
    //并将项目放入大根堆(能够做的项目堆)中,再将大根堆顶peek作为要做的项目,再将手上的钱+扣除花费后的利润
    //扣除花费后的利润表达的意思就是手上的钱不用减,直接加利润即可
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
                return o2.c - o1.c;
            }
        });
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(100, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c - o2.c;
            }
        });
        //将所有项目放入小根堆中
        for (int i = 0; i < nodes.length; i++) {
            minHeap.add(nodes[i]);
        }
        //在k个项目中进行操作
        for (int i = 0; i < k;i++) {
            //将所有能够做的项目都放到待做的堆中
            while (!minHeap.isEmpty() && minHeap.peek().c <= W) {
                maxHeap.add(minHeap.poll());
            }
            //如果加完了要做的项目堆中没有项目了,直接返回目前有的资金
            if (maxHeap.isEmpty()) {
                return W;
            }
            //如果能做的项目堆中还有项目,每次做一个,按照for循环的次数来
            W += maxHeap.poll().p;
        }
        return W;
    }
}
