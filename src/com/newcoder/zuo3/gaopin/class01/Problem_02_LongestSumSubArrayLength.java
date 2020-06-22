package com.newcoder.zuo3.gaopin.class01;

import java.util.HashMap;

public class Problem_02_LongestSumSubArrayLength {
    //2、给定一个数组，值可以为正、负和0，请返回累加和为给定值k的最长子数组长度。
    //必须以每个位置结尾累加和为k的最长子数组长度
    //用hashMap做,首次出现的累加和记录对应的结尾元素下标,
    // 那么最长的就是key之间相减等于k的下标(value)之差,找所有的最大的,
    // 要在map中初始加入0,-1,如果不加,正好满足sum=k的子串(需要去之前的位置找sum=0的索引)的都会被错过
    public static int maxLength(int[] arr, int k) {
        //每次有数进来,都要先看该数与之前数的和在hashMap的key中能不能找到正好为 数-hashMap 中的key值为k的数,如果有,计算长度
        //如果没有,把和与下标放入hashMap中
        //如果这个和在hashMap中已经出现过了,就不更新hashMap中的相同key对应的value(表示下标),因为要的是最长子数组长度
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {//要在map中初始加入0,-1,如果不加,正好满足sum=k的子串(需要去之前的位置找sum=0的索引)的都会被错过
            sum += arr[i];
            if (hashMap.containsKey(sum - k)) {
                //当前位置为sum,希望前面有值sum-该值为k,则sum-?=k,->? = sum-k
                maxLen = Math.max(i - hashMap.get(sum - k), maxLen);//长度不用+1,因为?的下标表示和为?的结尾下标,如果+1就多了
            }
            if (!hashMap.containsKey(sum)) {
                hashMap.put(sum, i);
            }
        }
        return maxLen;
    }


    //for test
    public static int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1); // important
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));
        System.out.println(maxLength1(arr, 10));

    }
}
