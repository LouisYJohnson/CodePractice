package com.newcoder.zuo3.basic.class07;

import java.util.LinkedList;

public class SlidingWindowMaxArray {
    //生成窗口最大值数组
    //【题目】
    //有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边， 窗口每次向右边滑一个
    //位置。
    //例如， 数组为[4,3,5,4,3,3,6,7]， 窗口大小为3时：
    //[4 3 5] 4 3 3 6 7 窗口中最大值为5
    //4 [3 5 4] 3 3 6 7 窗口中最大值为5
    //4 3 [5 4 3] 3 6 7 窗口中最大值为5
    //4 3 5 [4 3 3] 6 7 窗口中最大值为4
    //4 3 5 4 [3 3 6] 7 窗口中最大值为6
    //4 3 5 4 3 [3 6 7] 窗口中最大值为7
    //如果数组长度为n， 窗口大小为w， 则一共产生n-w+1个窗口的最大值。
    //请实现一个函数。
    //输入： 整型数组arr， 窗口大小为w。
    //输出： 一个长度为n-w+1的数组res， res[i]表示每一种窗口状态下的最大值。
    //以本题为例， 结果应该返回{5,5,5,4,6,7}。

    //每一步右边加一下左边缩一下结束后判断当前最大值
    public static int[] getMaxWindow(int[] arr, int w) {
        //负责存储值对应的原始数组中的下标,可以通过下标找到原始数组中的值
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        int[] res = new int[arr.length-w+1];
        int index = 0;
        //只有i在动,根据i,w,原始数组下标的关系可以确定窗口的位置,
        //由linkedList中的队列首位得到当前窗口的最大值,长度为l的数组arr,用长度为w的窗口滚动,共有l-w+1个窗口
        for (int i = 0; i < arr.length; i++) {
            //增操作,加的是i位置的数,那么就应该减i-w位置的数了,而不是i-w+1
            //如果当前增加的数大于或者等于队列尾部的数将队列尾部全部弹出,直到能够在尾部以从大到小的顺序放置增加的数为止
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] <= arr[i]) {
                linkedList.pollLast();
            }
            linkedList.addLast(i);
            //减操作
            if (linkedList.peekFirst() == i-w) {//如果当前被减元素是队列的第一个元素且序号是原数组中被减的那一个,直接减
                linkedList.pollFirst();
            }
            //到窗口成型后,向里面塞值
            if (i >= w-1) {//这么做是直到成为大小为w的数组的时候,才开始将结果返回到res数组中
                res[index++] = arr[linkedList.peekFirst()];
            }
        }
        return res;
    }

}
