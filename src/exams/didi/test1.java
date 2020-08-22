package exams.didi;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 1) {
            System.out.print(0);
            return;
        }
        if (n == 1) {
            System.out.print(1);
            return;
        }
        long[] dp = new long[n * n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        //dp中存储的就是所有的数字
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        //dp从右到左即从大到小,遍历到下标为1,顺时针存到一个二维数组中就可以了
        long[][] res = new long[n][n];
        int startPos = dp.length - 1;
        int helpN = n;
        int len = helpN * 4 - 4;
        //给定左上角与右下角的边界位置,将dp中的数据顺时针添加到结果二维数组中
        for (int i = 0; i <= n / 2; i++) {
            int leftTopRow = i;
            int leftTopCol = i;
            int rightBotRow = res.length - 1 - i;
            int rightBotCol = res.length - 1 - i;
            //给定数组,初始添加位置,与停止位置,将一维数组中的结果添加到二维数组中
            process(res, dp, leftTopRow, rightBotRow, startPos, len);
            //下次的开始位置就是本次用掉的下一个
            startPos = startPos - len;
            helpN -= 2;
            len = helpN * 4 - 4;
        }
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                System.out.print(res[i][j]);
                if (i != res.length - 1 || j != res.length - 1) {
                    System.out.print(" ");
                }
            }
        }
    }

    private static void process(long[][] res, long[] dp, int leftTopRow, int rightBotRow, int startPos, int len) {
        //如果只有一个元素(len为1,直接放到那个位置就return)
        if (len == 0) {
            res[leftTopRow][leftTopRow] = 1;
            return;
        }
        //否则顺时针填数
        //上横
        for (int i = leftTopRow; i < rightBotRow; i++) {
            res[leftTopRow][i] = dp[startPos--];
        }
        //右竖
        for (int i = leftTopRow; i < rightBotRow; i++) {
            res[i][rightBotRow] = dp[startPos--];
        }
        //下横
        for (int i = rightBotRow; i > leftTopRow; i--) {
            res[rightBotRow][i] = dp[startPos--];
        }
        //左竖
        for (int i = rightBotRow; i > leftTopRow; i--) {
            res[i][leftTopRow] = dp[startPos--];
        }
    }


//    public static int process(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        if (n == 2) {
//            return 2;
//        }
//
//        return process(n - 1) + process(n - 2);
//    }
//
//    public static int process1(int n) {
//        int[] dp = new int[n + 1];
//        dp[0] = 0;
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i < dp.length; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//    }
}
