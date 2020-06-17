package com.newcoder.zuo3.basic.class07;

public class Cow {
    //母牛每年生一只母牛， 新出生的母牛成长三年后也能每年生一只
    //母牛， 假设不会死。 求N年后， 母牛的数量。

    //递归版本
    public static int cowNumber1(int n) {
        //base case
        if (n < 1) return 0;
        if (n<4) return n;
        return cowNumber1(n-1) + cowNumber1(n-3);
    }

    //非递归版本
    public static int cowNumber2(int n) {
        //建立解空间,因为只有n和结果有关,所以解空间为一维空间
        int[] dp = new int[n+1];//多一个是为了下标与年数对应
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-3];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
    }

}
