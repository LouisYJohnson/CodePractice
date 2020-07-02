package com.newcoder.zuo3.advanced.class04;

public class Code_06_SmallestMissNum {
    //题目六
    //数组中未出现的最小正整数
    //【题目】
    //给定一个无序整型数组arr， 找到数组中未出现的最小正整数。
    //【举例】
    //arr=[-1,2,3,4]。 返回1。
    //arr=[1,2,3,4]。 返回5

    //这个题的意思就是要从数组的第一个元素开始按照依次递增的顺序往下标递增的位置放数,
    // 如果中间出现了断档,那么第一个断档的位置就是要返回的最小整数
    public static int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
