package com.newcoder.zuo3.advanced.class06;

import java.util.Arrays;

public class Code_01_SubArrayMaxSum {
    //子数组的最大累加和问题
    //【题目】
    //给定一个数组arr， 返回子数组的最大累加和。
    //例如， arr=[1,-2,3,5,-2,6,-1]， 所有的子数组中， [3,5,-2,6]
    //可以累加出最大的和12， 所以返回12。
    //【要求】
    //如果arr长度为N， 要求时间复杂度为O(N)， 额外空间复杂度为
    //O(1)。
    //【补充题目】
    //给定一个数组arr， 返回两个不相容子数组的最大累加和。

    //要求中的题目:
    //就是求一个长度最长,并且累加和最大的那么一个子数组
    //cur初始值为0,max初始值为系统最小
    //从头遍历数组,cur依次累加,
    // 如果cur大于max,则max更新(如果cur一直为负,那就是找负数中的最大值),
    // 如果cur小于max,且大于0,不更新max,一旦cur变为小于0了,将cur重置为0(相当于这个子数组不要了)
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (cur > max) {
                max = cur;
            }
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }

    //for test
    public static int maxSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i != arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));
        System.out.println(maxSum1(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));
        System.out.println(maxSum1(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));
        System.out.println(maxSum1(arr3));

    }
}
