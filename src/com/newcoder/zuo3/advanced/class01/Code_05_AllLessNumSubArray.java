package com.newcoder.zuo3.advanced.class01;

import java.util.LinkedList;

public class Code_05_AllLessNumSubArray {
    //给定数组arr和整数num， 共返回有多少个子数组满足如下情况：
    //max(arr[i..j]) - min(arr[i..j]) <= num
    //max(arr[i..j])表示子数组arr[i..j]中的最大值， min(arr[i..j])表示子数组arr[i.
    //中的最小值。
    //【要求】
    //如果数组长度为N， 请实现时间复杂度为O(N)的解法
    //结论:如果一个数组达标了,那么这个数组的子数组也是达标的(如果数组变成子数组,最大值只有可能变小或者不变,最小值只有可能变大或者不变,所以仍然达标)
    //如果一个数组不达标,那么这个数组不管是往左扩还是往右扩,都不会达标.因为数组扩最大值只有可能更大或者不变,最小值只有可能更小或者不变
//    流程:(整体思路为从左到右找到从数组中每一个元素为开始的并且达标的子数组)
//    做两个结构,得到窗口中的最大值与最小值
//    l从0开始,r每扩一步,查一下是否达标,如果达标,继续,直到不达标或者越界为止,得到的是0-0,0-1,...,0-r的子数组,0到r上做等差数列,就是得到0开始的达标的所有子数组
//    然后让L从1开始,然后看r是否能往右扩(0到r达标,那么1到r一定也达标),r扩了之后,1到r上的做一个等差数列就好(数组达标结论),就得到了1开头的达标的所有子数组
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //这两个结构分别存储窗口内最大与最小值(这种窗口结构是降低时间复杂度的关键)
        //窗口结构存储的是元素下标，用不着存元素因为通过下标可以找到元素
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        //两个指针,分别指向当前元素,与当前元素的最长达标数组
        int i = 0;
        int j = 0;
        //存储结果
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                //找到从i到j的最大值
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                //找从i到j的最小值
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            //窗口右移，清空窗口外侧的最大/最小值
            if (i == qmax.peekFirst()) {
                qmax.pollFirst();
            }
            if (i == qmin.peekFirst()) {
                qmin.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    //implement by zuo
    public static int getNum1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum1(arr, num));
        System.out.println(getNum(arr, num));

    }
}
