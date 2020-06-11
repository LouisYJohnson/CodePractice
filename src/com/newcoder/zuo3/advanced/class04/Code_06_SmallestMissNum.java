package com.newcoder.zuo3.advanced.class04;

public class Code_06_SmallestMissNum {
    //题目六
    //数组中未出现的最小正整数
    //【题目】
    //给定一个无序整型数组arr， 找到数组中未出现的最小正整数。
    //【举例】
    //arr=[-1,2,3,4]。 返回1。
    //arr=[1,2,3,4]。 返回5。
    //注意这题中找的是未出现的最小正整数,所以要把数组排成数组上的元素比数组下标大1的就可以了
    public static int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            }else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            }else {
                swap(arr,l,arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr,int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
