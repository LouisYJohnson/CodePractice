package com.newcoder.zuo3.gaopin.class04;

import java.util.HashMap;
import java.util.Map;

public class Problem_02_WaterProblem {
    //给定一个数组，每个位置的值代表一个高度。那么整个数组可以看过
    //是一个直方图。
    //如果把这个直方图当做容器的话，求这个容器能装多少水。
    //例如：
    //3,1,2,4
    //代表第一个位置高度为3,第二个位置高度为1,第三个位置高度为2,
    //第四个位置高度为4。
    //3, 1,2, 4这个数组代表的容器可以装3格的水

    //解法:找每一个格子能装的水
    //构建两个辅助数组,分别保存了当前下标(包括当前下标)左边的最大值,右边的最大值
    //这个格子能装的水量就是左右两边最大值中较小的那个减去当前格子上的值,
    // 如果为正,那就能装,如果为负,不装

    public static int getWater1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] helpLeftMax = new int[arr.length];
        helpLeftMax[0] = arr[0];
        int[] helpRightMax = new int[arr.length];
        helpRightMax[arr.length - 1] = arr[arr.length - 1];

        int helpLeft = arr[0];
        int helpRight = arr[arr.length - 1];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > helpLeft) {
                helpLeft = arr[i];
                helpLeftMax[i] = arr[i];
            } else {
                helpLeftMax[i] = helpLeft;
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > helpRight) {
                helpRight = arr[i];
                helpRightMax[i] = arr[i];
            } else {
                helpRightMax[i] = helpRight;
            }
        }

        int result = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            result += Math.max(Math.min(helpLeftMax[i], helpRightMax[i]) - arr[i], 0);
        }
        return result;
    }

    //for test
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length - 2;
        int[] leftMaxs = new int[n];
        leftMaxs[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
        }
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arr[i]);
        }
        return value;
    }

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

        HashMap<String,String> map = new HashMap<String,String>();

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" , "+ entry.getValue());
        }

    }
}
