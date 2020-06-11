package com.newcoder.zuo3.gaopin.class04;

public class Problem_04_MaxABSBetweenLeftAndRight {
    //给定一个长度为N (N>1)的整型数组arr，可以划分成左右两个
    //部分，左部分为arr[O..K]，右部分为arr[K+1..N-1]，K可以取
    //值的范围是[0，N-2]。求这么多划分方案中，左部分中的最大值
    //减去右部分最大值的绝对值中，最大是多少？
    //例如：[2, 7, 3, 1,1]，当左部分为[2, 7]，右部分为[3, 1,1]时，
    //左部分中的最大值减去右部分最大值的绝对值为4。当左部分为
    //[2, 7, 3]，右部分为[1,1]时，左部分中的最大值减去右部分最
    //大值的绝对值为6。还有很多划分方案，但最终返回6。

    //构建两个辅助数组,分别存储包含当前坐标的左边,右边的最大值
    public static int maxABS1(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;

        int[] helpMaxLeft = new int[arr.length];
        helpMaxLeft[0] = arr[0];
        int helpLeft = arr[0];
        int[] helpMaxRight = new int[arr.length];
        helpMaxRight[arr.length - 1] = arr[arr.length - 1];
        int helpRight = arr[arr.length - 1];

        //构建左边最大值的数组
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > helpLeft) {
                helpLeft = arr[i];
                helpMaxLeft[i] = arr[i];
            }else {
                helpMaxLeft[i] = helpLeft;
            }
        }
        //构建右边最大值的数组
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > helpRight) {
                helpRight = arr[i];
                helpMaxRight[i] = arr[i];
            }else {
                helpMaxRight[i] = helpRight;
            }
        }

        int maxLeft = 0;
        int maxright = 0;
        int result = 0;
        //从左到右遍历这两个数组,左边的从0开始,到length-2结束,右边的从1开始到length-1结束
        for (int i = 0; i <= arr.length - 2; i++) {
            maxLeft = helpMaxLeft[i];
            maxright = helpMaxRight[i + 1];
            result = Math.max(Math.abs(maxLeft - maxright), result);
        }
        return result;
    }

    //for test
    public static int maxABS2(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }
}
