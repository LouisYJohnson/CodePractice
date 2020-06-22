package myCodePractice.zuo.basic.class08;

import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaxSum {
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
    //以本题为例， 结果应该返回{5,5,5,4,6,7}

    public static int[] slidingWindowMaxSum(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w > arr.length) return null;

        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> window = new LinkedList<>();

        int r = 0;
        int l = 0;
        //window初始化
        for (l = 0; l < w; l++) {
            if (window.isEmpty()) {
                window.add(l);
                continue;
            }
            while (!window.isEmpty() && arr[l] >= arr[window.peekLast()]) {
                window.pollLast();
            }
            window.add(l);
        }
        l--;
        res[0] = arr[window.peekFirst()];

        for (int i = l + 1; i < arr.length; i++) {
            if (window.contains(r)) {
                window.remove(Integer.valueOf(r));
            }
            r++;
            while (!window.isEmpty() && arr[i] >= arr[window.peekLast()]) {
                window.pollLast();
            }
            window.add(i);
            res[i - l] = arr[window.peekFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = slidingWindowMaxSum(arr, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
