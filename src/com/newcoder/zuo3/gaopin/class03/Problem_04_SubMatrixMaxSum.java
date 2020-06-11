package com.newcoder.zuo3.gaopin.class03;

public class Problem_04_SubMatrixMaxSum {
    //子矩阵的最大累加和问题
    //【题目】
    //给定一个矩阵matrix， 其中的值有正、 有负、 有0， 返回子矩阵的最大累加和。
    //例如， 矩阵matrix为：
    //-90 48 78
    //64 -40 64
    //-81 -7 66
    //其中， 最大累加和的子矩阵为：
    //48 78
    //-40 64
    //-7 66
    //所以返回累加和209。
    //例如， matrix为：
    //-1 -1 -1
    //-1 2 2
    //-1 -1 -1
    //其中， 最大累加和的子矩阵为：
    //2 2
    //所以返回累加和4。

    //可以把多行得问题转化成一行(子矩阵,有一个方向上是必须相加的,可以选择的只是左右的边界)
    //每次都把多行压缩成一个数组,这个数组求最大累加和,就是这个多行矩阵对应的子矩阵得最大累加和
    //
    //每次找 1,1 2,1 2 3,1 2 3 4
    //2,2 3,2 3 4
    //3,3 4
    //4
    //然后找最大值就是结果
    //
    //把找子矩阵的过程变成找所有连续行组合方式的过程

    public static int maxSum(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) return 0;

        int max = Integer.MIN_VALUE;
        //起始行
        for (int k = 0; k < arr.length; k++) {
            int[] help = new int[arr[0].length];
            //经过行
            for (int i = k; i < arr.length; i++) {
                //列
                for (int j = 0; j < arr[0].length; j++) {
                    help[j] += arr[i][j];
                }
                max = Math.max(subArrayMaxSum(help), max);
            }
        }
        return max;
    }

    public static int subArrayMaxSum (int[] arr) {
        if (arr == null || arr.length ==0) return 0;

        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = Math.max(0, cur);
        }
        return max;
    }

    //for test
    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));

    }



}
