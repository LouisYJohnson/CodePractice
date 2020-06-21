package com.newcoder.zuo3.basic.class07;

public class Knapsack {
    //给定两个数组w和v， 两个数组长度相等， w[i]表示第i件商品的
    //重量， v[i]表示第i件商品的价值。
    //再给定一个整数bag， 要求你挑选商品的重量加起来一定不能超
    //过bag， 返回满足这个条件下， 你能获得的最大价值。

    public static int maxValue1(int[] w, int[] v, int bag) {
        return process1(0, w, v, 0, bag);
    }

    //递归版本
    //函数含义:i位置之前的都已经选好了,i到i后面的位置都是自由选取的,并且限制的条件为重量
    //到头的不会产生重量并且正好能够和v累加得到结果
    public static int process1(int i, int[] w, int[] v, int weight, int bag) {
        //base case:
        if (weight > bag) return Integer.MIN_VALUE;
        if (i == w.length) return 0;//已经到头了,后面不会有重量产生了,所以这里返回的是产生的重量0

        return Math.max(process1(i + 1, w, v, weight, bag),
                v[i] + process1(i + 1, w, v, weight + w[i], bag));
    }

    //非递归版本
    public static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                    //看解空间中的对应其实就是看解空间中对应到递归函数中的变量是如何依赖的,
                    //其他的表示传递信息的变量是不用管的.
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] c = {3, 2, 4, 7};
        int[] p = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue1(c, p, bag));
        System.out.println(maxValue2(c, p, bag));
    }

}
