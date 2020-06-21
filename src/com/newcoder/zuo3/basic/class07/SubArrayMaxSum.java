package com.newcoder.zuo3.basic.class07;

public class SubArrayMaxSum {
    //给定一个数组arr， 返回所有子数组的累加和中， 最大的累加和
    //假设:找一个累加和最大而且最长的子数组,这个子数组的和即为所求
    //找到一个累加和最大而且最长的数组,即途中L到R的范围,
    // 则L之前的数组元素和必为负数且不可能为0(如果为0就算进来了),
    // 且在L到R范围内的数从任意一个数开始到L的位置上的累加和,
    // 都必不可能为负数(如果为符数,就会在前面L-1那段里面了)

    //查找方式为:
    //定义一个cur=0,一个max = Integer.Min_VALUE
    //从头遍历数组,并将cur与当前数字相加.
    //如果cur>max(找到了目标数组的一部分),则max=cur
    //如果cur<max,max不变(说明有负数出现)
    //如果cur<0,则cur=0并继续向下遍历,max不变(说明找到了累加和会为负数的点,不要了)
    public static int maxSum(int[] arr) {
        if (arr.length < 1 || arr == null) return 0;
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

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));

    }
}
