package com.newcoder.zuo3.advanced.class05;

public class Code_02_Max_Damage {
    //某游戏是一个卡牌类游戏， 玩家通过战斗或抽牌可以拿到一些
    //技能牌， 每张技能牌都有对应的伤害值(伤 害值>=0)， 当你有
    //了组合技属性之后， 你可以在自己手头上选择任意张技能牌，
    //以组合技的方式来攻击 boss， 组合技的总伤害将等于所组合的
    //各张技能牌的伤害值的乘积(只有一张牌时， 组合技伤害值等于
    //这张牌 本身的伤害值)， 但是能发动组合技必须有个前提:所有
    //被选择的技能牌的伤害系数之和必须等于m(m>0) 以解开封印;
    //你为了能赢得最终胜利， 需要在所有技能牌中挑出若干张技能
    //牌触发组合技(每张牌只能用一 次)， 以形成最大威力的组合技
    //攻击效果。 例如:你有伤害值分别为1,2,3,4,5的五张牌， 给定
    //的解开封印的阈值(m)为10， 那形成最大组合攻击效果 的组合
    //为30(5*3*2)， 而不是24(4*3*2*1)， 也不是20(5*4*1)， 需要输
    //出的结果即30。
    //变形的背包问题
    //其实就是:和为固定值的时候,要最大乘积,背包问题,用递归来做(父问题和子问题同一个解决逻辑,所以用递归做)
    //递归函数功能:i位置之前的位置都已经满足了,要在i位置以及i位置之后的所有数上自由选得到满足要求的最大乘积
    public static int maxDamage(int[] arr, int threshold) {
        return process(arr, 0, threshold);
    }
    public static int process(int[] arr, int i, int threshold) {
        //base case
        if (i == arr.length) return threshold == 0 ? 1 : -1;
        if (threshold < 0) return -1;

        //当前位置分为要和不要,才能进入下一步
        int nextWithOutCur = process(arr, i + 1,threshold); //不要
        int nextWithCur = process(arr, i+ 1,threshold - arr[i]) * arr[i];     //要
        return Math.max(nextWithCur,nextWithOutCur);
    }
    //暴力递归改动态规划
    public static int maxDamage1(int[] arr, int threshold) {
        int[][] dp = new int[arr.length + 1][threshold + 1];
        dp[arr.length][0] = 1;
        for (int i = 1; i <= threshold; i++) {
            dp[arr.length][i] = -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= threshold; j++) {
                int help = j - arr[i] < 0 ? -1 : (dp[i + 1][j - arr[i]]) * arr[i];
                dp[i][j] = Math.max(help, dp[i + 1][j]);
            }
        }
        return dp[0][threshold];
    }

    //for test
    public static void printMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int maxDamage2(int[] arr, int threshold) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][threshold + 1];
        if (arr[0] <= threshold) {
            dp[0][arr[0]] = arr[0];
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= threshold; j++) {
                int no = dp[i - 1][j];
                int only = j - arr[i] == 0 ? arr[i] : 0;
                int part = j - arr[i] > 0 ? dp[i - 1][j - arr[i]] * arr[i] : 0;
                dp[i][j] = Math.max(no, Math.max(only, part));
            }
        }
        // printMatrix(dp); // 可以打印dp看看
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int threshold = 10;
        System.out.println(maxDamage(arr, threshold));
        System.out.println(maxDamage1(arr, threshold));
        System.out.println(maxDamage2(arr, threshold));
    }
}
