package com.newcoder.zuo3.gaopin.class02;

public class Problem_02_SmallSum {
    //数组小和的定义如下：
    //例如，数组3=[1,3,5,2,4,6]，在3[0]的左边小于或等于3[0]的
    //数 的 和 为 0 , 在 s[ 1] 的左边小于或等于 s[ 1] 的数的和为1,在 s[ 2]
    //的左边小于或等于s [2]的数的和为1 +3=4,在s [3]的左边小于或
    //等于s [3]的数的和为1，在s [4]的左边小于或等于s [4]的数的和
    //为1+3+2=6，在s [5]的左边小于或等于s [5]的数的和为
    //1+3+5+2+4=15，所以s的小和为0+1+4+1+6+15=27。
    //给定一个数组s，实现函数返回s的小和

    //mergeSort改写问题
    //首先复习一下mergeSort
    //递归函数功能:给定数组和左右边界,将数组排序
//    public static void mergeSort(int[] arr, int l, int r) {
//        //base case
//        if (l == r) return;
//
//        int mid = l + (r - l) / 2;
//        //将左右排好后,合并左右
//        mergeSort(arr, l, mid);
//        mergeSort(arr, mid + 1, r);
//        merge(arr, l, mid, r);
//    }
//
//    //给定一个数组,从中间分成两半,左边从小到大,右边也是从小到大,将这两个部分合并成一个整体的从小到大
//    public static void merge(int[] arr, int l, int mid, int r) {
//        int pL = l;
//        int pR = mid + 1;
//        int[] help = new int[r - l + 1];
//        int indexHelp = 0;
//
//        while (pL <= mid && pR <= r) {
//            if (arr[pL] < arr[pR]) {
//                help[indexHelp++] = arr[pL++];
//            } else {
//                help[indexHelp++] = arr[pR++];
//            }
//        }
//        //两边走完了,可能存在左边没走完或者右边没走完
//        while (pL <= mid) {
//            help[indexHelp++] = arr[pL++];
//        }
//        while (pR <= r) {
//            help[indexHelp++] = arr[pR++];
//        }
//        //将help中的内容拷贝回arr中
//        for (int i = 0; i < help.length; i++) {
////            这里不能是从i开始,因为arr的左边界是l而不是0!
//            arr[l++] = help[i];
//        }
//    }

    //解决小和问题就是改写mergeSort,在merge的时候如果左侧的数小于右侧,小和即为左侧的数乘以右侧指针距离右侧结尾差几个
    public static int getSmallSum1 (int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return smallSum(arr, 0, arr.length - 1);
    }


    public static int smallSum(int[] arr, int l, int r) {
        //base case
        if (l == r) return 0;

        int mid = l + (r - l) / 2;
        return smallSum(arr, l, mid) + smallSum(arr, mid + 1, r) + mergeSum(arr, l, mid, r);
    }

    public static int mergeSum(int[] arr, int l, int mid, int r) {
        int pL = l;
        int pR = mid + 1;
        int result = 0;
        int[] help = new int[r - l + 1];
        int indexHelp = 0;

        while (pL <= mid && pR <= r) {
            if (arr[pL] <= arr[pR]) {
                //pR右边有几个数比arr[pR]大,就有几个arr[pL]
                result += arr[pL] * (r - pR + 1) ;
                help[indexHelp++] = arr[pL++];
            }else {
                help[indexHelp++] = arr[pR++];
            }
        }
        while (pL <= mid) {
            help[indexHelp++] = arr[pL++];
        }
        while (pR <= r) {
            help[indexHelp++] = arr[pR++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
        return result;

    }


    //for test
    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }

    public static int func(int[] s, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }

    public static int merge(int[] s, int left, int mid, int right) {
        int[] h = new int[right - left + 1];
        int hi = 0;
        int i = left;
        int j = mid + 1;
        int smallSum = 0;
        while (i <= mid && j <= right) {
            if (s[i] <= s[j]) {
                smallSum += s[i] * (right - j + 1);
                h[hi++] = s[i++];
            } else {
                h[hi++] = s[j++];
            }
        }
//        for (; (j < right + 1) || (i < mid + 1); j++, i++) {
//            h[hi++] = i > mid ? s[j] : s[i];
//        }
//        for (int k = 0; k != h.length; k++) {
//            s[left++] = h[k];
//        }
        while (i <= mid) {
            h[hi++] = s[i++];
        }
        while (j <= right) {
            h[hi++] = s[j++];
        }
        for (int k = 0; k < h.length; k++) {
            s[left++] = h[k];
        }
        return smallSum;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        //测试的时候要两次申请数组,因为mergeSort做小和问题是会把这个数组排序的,握草!!!
        int[] arr = { 3, 1, 2, 4, 6, 2, 7, 8, 1 };
        int[] arr1 = { 3, 1, 2, 4, 6, 2, 7, 8, 1 };
        System.out.println(getSmallSum(arr));
        System.out.println(getSmallSum1(arr1));

    }

}
