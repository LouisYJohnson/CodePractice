package exams.qianxin;

public class test1 {
    public static int maxValue(int[] w, int[] v, int bag) {

        return process(w, v, bag, 0, 0);
    }

    //递归函数功能:
    //给定重量与钱,当前是第几个物品,返回0-i-1位置商品已经选完了,i到最后商品还没选的情况下(满足重量较小)
    //能够得到的最大使用价值
    public static int process(int[] w, int[] v, int bag, int i, int curWeight) {
        //base case
        if (curWeight > bag) {//超过最大重量,作废
            return Integer.MIN_VALUE;
        }
        if (i == w.length) {    //走到最后一个位置了不要了
            return 0;
        }
        //下一步还要当前这个位置的元素,和不要当前这个位置的元素,和要这个元素后继续向后走
        return Math.max(process(w, v, bag, i + 1, curWeight),
                Math.max(v[i] + process(w, v, bag, i, curWeight + w[i]),
                        v[i] + process(w, v, bag, i + 1, curWeight + w[i])
                )
        );
    }
    //递归改动态规划:
    public static int process1(int[] w, int[] v, int bag) {
        int[][] dp = new int[bag + 1][w.length + 1];
        for (int row = 0; row < dp.length; row++) {
            dp[row][dp[0].length - 1] = 0;
        }
        //从右下开始往往上填
        for (int col = dp[0].length - 2; col >= 0; col--) {
            for (int row = dp.length - 1; row >= 0; row--) {
                if (row + w[col] < dp.length) {
                    dp[row][col] = Math.max(dp[row][col + 1],
                            Math.max(v[col] + dp[row + w[col]][col],
                                    v[col] + dp[row + w[col]][col + 1]));
                }else {
                    dp[row][col] = dp[row][col + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] w = new int[]{77,22,29,50,99};
        int[] v = new int[]{92, 22, 36, 46,90};
        int res = test1.process1(w, v, 100);
        System.out.println(res);
    }
}
