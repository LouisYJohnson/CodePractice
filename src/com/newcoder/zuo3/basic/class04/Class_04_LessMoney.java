package com.newcoder.zuo3.basic.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Class_04_LessMoney {
    //一块金条切成两半， 是需要花费和长度数值一样的铜板的。 比如长度为20的
    //金条， 不管切成长度多大的两半， 都要花费20个铜板。 一群人想整分整块金
    //条， 怎么分最省铜板？
    //贪心问题,其实就是每次合成需要代价最低就可以了(找到当前长度最低的两个段进行相加),因为已经有了分离之后的长度,所以问题从下往上求解也一样
    //使用小根堆是因为每次都能弹出最小值,而且将数字加进去之后仍然保持弹出最小值
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(100, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {//小根堆
                return o1 - o2;
            }
        });
        //将数组构成一个小根堆
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int help = 0;
        //将小根堆的前两个节点拿出来进行求和,然后放回堆中,如此反复,直到堆中只剩下一个数据
        while (pq.size() != 1) {
            help += pq.poll() + pq.poll();
            pq.add(help);
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        // solution
        int[] arr = {10, 20, 30};
        System.out.println(lessMoney(arr));

    }
}
