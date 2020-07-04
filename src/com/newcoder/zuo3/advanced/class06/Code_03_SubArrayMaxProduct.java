package com.newcoder.zuo3.advanced.class06;

public class Code_03_SubArrayMaxProduct {
    //数组中子数组的最大累乘积
    //【题目】
    //给定一个double类型的数组arr， 其中的元素可正、 可负、 可0， 返回
    //子数组累乘的最大乘积。 例如， arr=[-2.5， 4， 0， 3， 0.5， 8， -1]，
    //子数组[3， 0.5， 8]累乘可以获得最大的乘积12， 所以返回12

    //预处理数组:
    //遍历数组的每一个位置,每一个位置上填的值就是必须以这个位置上的数为结尾的情况下得到的最大乘积
    //i位置需要i-1位置上的max,min,才能得到这个位置上的max,min
    //每个位置上需要两个信息,下一个位置才能继续往下走
    ////可能性1:不要前面的数,只要i
    //    //可能性2:要前面的数,i位置上的最大累乘积是i-1位置上的最大累乘积乘i位置得到的
    //    //可能性3:i-1位置上的最小累乘积乘i位置上的可能得到最大累乘积
    //
    //算到最后,每个位置上最大的max就是答案
    public static double maxProduct(double[] arr) {
        if (arr == null || arr.length == 0) return 0;
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        double maxEnd = 0;
        double minEnd = 0;
        for (int i = 1; i < arr.length; i++) {
            maxEnd = max * arr[i];
            minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        double[] arr = {-2.5, 4, 0, 3, 0.5, 8, -1};
        System.out.println(maxProduct(arr));

    }
}
