package myCodePractice.zuo.advance.class06;

public class SubArrayMaxSum {
    //子数组的最大累加和问题
    //【题目】
    //给定一个数组arr， 返回子数组的最大累加和。
    //例如， arr=[1,-2,3,5,-2,6,-1]， 所有的子数组中， [3,5,-2,6]
    //可以累加出最大的和12， 所以返回12。
    //【要求】
    //如果arr长度为N， 要求时间复杂度为O(N)， 额外空间复杂度为
    //O(1)。
    //【补充题目】
    //给定一个数组arr， 返回两个不相容子数组的最大累加和。

    //题目:
    //  求出累加和最大且长度最长的子数组
    //  方法:使用2个变量:cur,max 其中cur初始值为0,max初始值为Integer.MIN_VALUE
    //      从头遍历数组,每次cur与当前数字相加,如果大于max,更新max,如果cur<0,将cur置0
    public static int getSubArrayMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;

        int cur = 0;
        //max设置为这个值是为了即使数组中全是负数也能够选出一个较大的负数
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];

            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    //进阶:
    //  因为是两个不相容数组,所以将数组从左到右分成两半,每半必须至少有一个元素
    public static int getSubArrayMaxSum1(int[] arr) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            res = Math.max(res, getSubArrayMaxSum2(arr, 0, i) + getSubArrayMaxSum2(arr, i + 1, arr.length - 1));
        }
        return res;
    }

    public static int getSubArrayMaxSum2(int[] arr, int l, int r) {
        if (l == r) return arr[l];

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = l; i <= r; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {1,-2,3,5,-2,6,-1};
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        int[] arr3 = {-2, -3, -5, -1};
//        System.out.println(getSubArrayMaxSum(arr));
//        System.out.println(getSubArrayMaxSum(arr1));
//        System.out.println(getSubArrayMaxSum(arr2));
//        System.out.println(getSubArrayMaxSum(arr3));

//        System.out.println(getSubArrayMaxSum1(arr));
        System.out.println(getSubArrayMaxSum1(arr1));
        System.out.println(getSubArrayMaxSum1(arr2));
        System.out.println(getSubArrayMaxSum1(arr3));
    }

}
