package com.newcoder.zuo3.advanced.class06;

import java.util.Arrays;

public class Code_02_SubMatrixMaxSum {
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

    //可以把多行得问题转化成一行
    //每次都把多行压缩成一个数组,这个数组求最大累加和,就是这个多行矩阵对应的子矩阵得最大累加和
    //
    //每次找 1,1 2,1 2 3,1 2 3 4
    //2,2 3,2 3 4
    //3,3 4
    //4
    //然后找最大值就是结果
    //
    //把找子矩阵的过程变成找所有连续行组合方式的过程
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int helpMaxValueSize = 0;
        for (int i = 0; i < m.length; i++) {
            helpMaxValueSize += (i + 1);
        }
        int[] helpMaxValue = new int[helpMaxValueSize];
//        int[] helpSubSum = new int[m[0].length];
        int index = 0;
        //下面最外面两层嵌套循环,找到了所有连续的行的组合(比如矩阵有4行从第一行开始找2,3,4,然后从2开始找3,4,从3开始找4,最后只有4自己)
        for (int i = 0; i < m.length; i++) {            //行
            int[] helpSubSum = new int[m[0].length];
            for (int j = i; j < m.length; j++) {    //该行下面的所有
                for (int k = 0; k < m[0].length; k++) {
                    helpSubSum[k] += m[j][k];
                }
                helpMaxValue[index++] = maxSumArr(helpSubSum);
            }
        }
        Arrays.sort(helpMaxValue);
        return helpMaxValue[0];
    }

    public static int maxSumArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
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

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
    }
}
