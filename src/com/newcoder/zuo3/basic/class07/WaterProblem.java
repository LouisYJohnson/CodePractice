package com.newcoder.zuo3.basic.class07;

import java.util.HashMap;
import java.util.Map;

public class WaterProblem {
    //给定一个数组代表一个容器，
    //比如[3,1,2,4]，
    //代表0位置是一个宽度为1， 高度为3的直方图。
    //代表1位置是一个宽度为1， 高度为1的直方图。
    //代表2位置是一个宽度为1， 高度为2的直方图。
    //代表3位置是一个宽度为1， 高度为4的直方图。
    //所有直方图的底部都在一条水平线上， 且紧靠着。
    //把这个图想象成一个容器， 这个容器可以装3格的水。
    //给定一个没有负数的数组arr， 返回能装几格水？
    public static int getWater2(int[] arr) {
        //第一个位置和最后一个位置是不可能装水的!
        //构建一个辅助数组,分别存储当前节点左边(到当前节点)和右边(到当前节点)的最大值
        //流程:从左向右,如果当前遍历到的原数组中的数字比辅助数组中前一个数字大,就将这个数字填入辅助数组
        //否则复制辅助数组中前一个位置的数
        if (arr == null || arr.length <= 2) return 0;
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        leftMax[0] = arr[0];
        rightMax[arr.length-1] = arr[arr.length-1];

        for (int i = 1; i < leftMax.length; i++) {
            if (arr[i] > leftMax[i-1]) {
                leftMax[i] = arr[i];
            }else {
                leftMax[i] = leftMax[i-1];
            }
        }
        for (int i = arr.length-2; i >=0 ; i--) {
            if (arr[i] > rightMax[i+1]) {
                rightMax[i] = arr[i];
            }else {
                rightMax[i] = rightMax[i+1];
            }
        }
        int res = 0;
        //遍历数组的每一个点,得到当前点能够装的水量
        for (int i = 0; i < arr.length; i++) {
            res += Math.max(Math.min(leftMax[i]-arr[i],rightMax[i]-arr[i]),0);
        }
        return res;
    }

    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(arr[l], leftMax);
            }
            for (int r = i + 1; r < arr.length; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }
            value += Math.max(0, Math.min(leftMax, rightMax) - arr[i]);
        }
        return value;
    }

//    public static int getWater2(int[] arr) {
//        if (arr == null || arr.length < 3) {
//            return 0;
//        }
//        int n = arr.length - 2;
//        int[] leftMaxs = new int[n];
//        leftMaxs[0] = arr[0];
//        for (int i = 1; i < n; i++) {
//            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
//        }
//        int[] rightMaxs = new int[n];
//        rightMaxs[n - 1] = arr[n + 1];
//        for (int i = n - 2; i >= 0; i--) {
//            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
//        }
//        int value = 0;
//        for (int i = 1; i <= n; i++) {
//            value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arr[i]);
//        }
//        return value;
//    }

    public static int getWater3(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length - 2;
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        int leftMax = arr[0];
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMax, rightMaxs[i - 1]) - arr[i]);
            leftMax = Math.max(leftMax, arr[i]);
        }
        return value;
    }

    public static int getWater4(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            if (leftMax <= rightMax) {
                value += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                value += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }
        return value;
    }

    public static int[] generateRandomArray() {
        int[] arr = new int[(int) (Math.random() * 98) + 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 200) + 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray();
            int r1 = getWater1(arr);
            int r2 = getWater2(arr);
            int r3 = getWater3(arr);
            int r4 = getWater4(arr);
            if (r1 != r2 || r3 != r4 || r1 != r3) {
                System.out.println("What a fucking day! fuck that! man!");
            }
        }

        HashMap<String, String> map = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " , " + entry.getValue());
        }

    }
}
