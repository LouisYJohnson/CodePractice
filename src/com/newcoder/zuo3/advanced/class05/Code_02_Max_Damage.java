package com.newcoder.zuo3.advanced.class05;

public class Code_02_Max_Damage {
    public static int maxDamage(int[] arr, int threshold) {
        return process(arr, 0, threshold);
    }

    public static int process(int[] arr, int i, int threshold) {
        //base case
        if (i == arr.length) return threshold == 0 ? 1 : -1;
        if (threshold < 0) return -1;

        int nextWithOutCur = process(arr, i + 1, threshold);
        int nextWithCur = process(arr, i + 1, threshold - arr[i]) * arr[i];
        return Math.max(nextWithCur, nextWithOutCur);
    }

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

    //for Combinations
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
        // printMatrix(dp);
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int threshold = 10;
        System.out.println(maxDamage(arr, threshold));
        System.out.println(maxDamage1(arr, threshold));
        System.out.println(maxDamage2(arr, threshold));
    }
}
