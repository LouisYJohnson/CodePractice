package com.newcoder.zuo3.basic.class02_1;

public class MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int len = arr.length;
        //检查数组中其中是否只是一个数在重复出现
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        //只有一个数,gap为0
        if (min == max) return 0;

        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        int numBucket = 0;
        //遍历所有数,并将对应桶中状态改变
        for (int i = 0; i < arr.length; i++) {
            numBucket = bucket(arr[i], len, min, max);
            //有数我才比,没数的话,最大最小的都是一样的值
            maxs[numBucket] = hasNum[numBucket] ? Math.max(arr[i], maxs[numBucket]) : arr[i];
            mins[numBucket] = hasNum[numBucket] ? Math.min(arr[i], mins[numBucket]) : arr[i];
            hasNum[numBucket] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i < len + 1; i++) {
            if (hasNum[i]) {
                res = Math.max(mins[i] - lastMax, res);
                lastMax = maxs[i];
            }
        }
        return res;

    }


    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}