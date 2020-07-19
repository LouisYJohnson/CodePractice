package leetcode.easy;

import leetcode.median.LC87UniquePathsII;

public class LC88UniquePaths {
    //题目描述
    //一个机器人在m×n大小的地图的左上角（起点，下图中的标记“start"的位置）。
    //机器人每次向下或向右移动。机器人要到达地图的右下角。（终点，下图中的标记“Finish"的位置）。
    //可以有多少种不同的路径从起点走到终点？
    public class Solution {
        /**
         * @param m int整型
         * @param n int整型
         * @return int整型
         */
        public int uniquePaths(int m, int n) {
            // write code here
            if (n == 0 || m == 0) return 0;
            return process(1, 1, m, n);
        }

        public int process2(int m, int n) {
            //变量只有row与col,从1到m,n
            int[][] dp = new int[m][n];
            //根据base case初始化dp数组
            dp[m - 1][n - 1] = 1;
            //根据后面的条件填dp中的值
            for (int i = 0; i < n - 1; i++) {
                dp[m - 1][i] = 1;
            }
            for (int i = 0; i < m - 1; i++) {
                dp[i][n - 1] = 1;
            }
            //按列从下到上从右到左填表
            for (int j = n - 2; j >= 0; j--) {
                for (int i = m - 2; i >= 0; i--) {
                    dp[i][j] += dp[i + 1][j];
                    dp[i][j] += dp[i][j + 1];
                }
            }
            return dp[0][0];
        }


        //如果使用递归,oj会超时,所以改成动态规划
        //递归函数功能:
        //  输入行数与列数与机器人所在位置,返回从机器人当前所在位置到右下角总共有多少种路径走法
        public int process(int row, int col, int m, int n) {
            //base case
            if (row == m && col == n) {
                //1表示1条可以走的路径
                return 1;
            }
            int res = 0;
            //这三个if条件只有一个成立
            /**
             * {@link LC87UniquePathsII}
             */
            if (row == m) { //如果到了底部,只能向右走
                res += process(row, col + 1, m, n);
            }

            if (col == n) { //如果到了最右边,只能向下走
                res += process(row + 1, col, m, n);
            }

            if (row < m && col < n) {   //表示右边和下边都还能走的时候才走
                res += process(row + 1, col, m, n);
                res += process(row, col + 1, m, n);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC88UniquePaths().new Solution();
        System.out.println(solution.uniquePaths(2, 3));
    }
}
