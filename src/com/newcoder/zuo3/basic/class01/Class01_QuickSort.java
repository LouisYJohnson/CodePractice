package com.newcoder.zuo3.basic.class01;

import java.util.Arrays;

public class Class01_QuickSort {
    //快速排序(从小到大)
    //思路:每次从数组中取出最后一个数作为筛选的标准,以该数为标准,左边为小于该数的数,右边为大于该数的数,中间为等于该数的数
    //递归实现,先将当前数组按照选取的数值分成两半,然后再把左右两半分别传入子过程中
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);

    }

    //该方法将数组排序后返回等于数组中最后一个数的一段区域的下标用于下一次迭代
    public static void quickSort(int[] arr, int min, int max) {
        if (min < max) {
            int[] p = partion(arr, min, max);
            quickSort(arr, min, p[0] - 1);
            quickSort(arr, p[1] + 1, max);
        }
    }

    public static int[] partion(int[] arr, int min, int max) {
        int l = min - 1;
        int r = max;
        int cur = min;
        while (cur < r) {
            if (arr[cur] < arr[max]) {
                swap(arr, cur++, ++l);
            } else if (arr[cur] == arr[max]) {
                cur++;
            } else {
                swap(arr, cur, --r);
            }
        }

        swap(arr, r, max);
        return new int[]{l + 1, r};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for Combinations
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for Combinations
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for Combinations
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for Combinations
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for Combinations
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for Combinations
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }
}


