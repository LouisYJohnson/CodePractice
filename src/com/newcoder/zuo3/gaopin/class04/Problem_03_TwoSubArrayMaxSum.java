package com.newcoder.zuo3.gaopin.class04;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Problem_03_TwoSubArrayMaxSum {
    //给定一个数组，长度大于2。找出不相交的两个子数组，情况是
    //很多的。请返回这么多情况中，两个不相交子数组最大的和。
    //例如：
    //-1,3, 4, -9, 1,2
    //当两个不相交子数组为[3, 4]和[1，2]时，可以得到最大的和为
    //10

    //解法:将数组分成两半,从左到右,分别计算两边的子数组最大累加和
    public static int twoSubArrayMaxSum1(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, maxSumSubArray(arr, 0, i) + maxSumSubArray(arr, i + 1, arr.length - 1));
        }
        return max;
    }

    public static int maxSumSubArray (int[] arr, int l, int r) {
        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = l; i < r + 1; i++) {
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


    public static int twoSubArrayMaxSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] rArray = new int[arr.length];
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            cur += arr[i];
            max = Math.max(max, cur);
            rArray[i] = max;
            cur = cur < 0 ? 0 : cur;
        }
        int res = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        cur = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            res = Math.max(res, max + rArray[i + 1]);
            cur = cur < 0 ? 0 : cur;
        }
        return res;
    }

    // for test
    public static int rightAnswer(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int p = 0; p < arr.length - 1; p++) {
            res = Math.max(res, maxSum(arr, 0, p) + maxSum(arr, p + 1, arr.length - 1));
        }
        return res;
    }

    // for test
    public static int maxSum(int[] arr, int l, int r) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = l; i <= r; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) - 10;
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 5000000;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            int[] test = generateRandomArray();
            if (twoSubArrayMaxSum1(test) != rightAnswer(test)) {
                hasErr = true;
            }
        }
        if (hasErr) {
            System.out.println("23333333");
        } else {
            System.out.println("66666666");
        }

    }
}
