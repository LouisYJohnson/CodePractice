package com.newcoder.zuo3.basic.class07;

public class Factorial {
    //计算n!的值
    //先写递归版本,然后写非递归版本(动态优化)
    //递归:
    //1.把问题规模缩小的同类问题的子问题(同类问题指问题逻辑相同)
    //2.有明确的停止递归的条件(base case)
    //3.有当子问题得到解决结果之后的决策过程
    //4.不记录每个子问题的解
    public static long getFactorial1(int n) {
        //base case:
        if (n == 1) return n;
        return (long) n * getFactorial1(n - 1);
    }
    //非递归(动态优化)版本:
    //首先确定位置以来:如果n确定了,那么返回值就确定了,所以所有的结果都可以放到一个一维表中(解空间),长度为n,范围从1到n
    //接下来的过程与题意无关,只与暴力递归中的东西有关
    //我们要的答案是解空间中的n位置的元素
    //base case中不被依赖的是n=1的位置
    //其他任意点的依赖看递归语句中的怎么写,就是怎么依赖的,反向填解空间那张表即可.
    public static long getFactorial2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
//        dp[2] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = i * dp[i-1];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        int n = 3;
        System.out.println(getFactorial1(n));
        System.out.println(getFactorial2(n));
    }
}
