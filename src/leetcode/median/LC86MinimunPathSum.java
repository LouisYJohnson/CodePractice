package leetcode.median;

public class LC86MinimunPathSum {
    //题目描述
    //给定一个由非负整数填充的m x n的二维数组，
    // 现在要从二维数组的左上角走到右下角，请找出路径上的所有数字之和最小的路径。
    //注意：你每次只能向下或向右移动。
    public class Solution {
        /**
         * @param grid int整型二维数组
         * @return int整型
         */
        public int minPathSum(int[][] grid) {
            // write code here

//            return process(grid, 0, 0);
            return process2(grid);

        }

        //动态规划:
        //因为变量只有row与col,并且变化范围为从0到grid.length-1与grid[0].length
        //最终结果只要dp[0][0]
        public int process2(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length];
            //首先按照base case填表
            dp[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
            //然后按照其余初始化条件填表
            for (int row = dp.length - 2; row >= 0; row--) {
                dp[row][dp[0].length - 1] = dp[row + 1][dp[0].length - 1] + grid[row][dp[0].length - 1];
            }
            for (int col = dp[0].length - 2; col >= 0; col--) {
                dp[dp.length - 1][col] = dp[dp.length - 1][col + 1] + grid[dp.length - 1][col];
            }
            //最后其余的自由位置填表
            for (int col = dp[0].length - 2; col >= 0; col--) {
                for (int row = dp.length - 2; row >= 0; row--) {
                    dp[row][col] = Math.min(dp[row][col + 1], dp[row + 1][col]) + grid[row][col];
                }
            }
            return dp[0][0];
        }

        //递归函数oj会超时,所以将其改为动态规划
        //递归函数功能:
        //  传入矩阵与当前所在位置,返回从当前位置出发得到的路径上的最小值数字之和
        public int process(int[][] grid, int row, int col) {
            //base case
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return grid[row][col];
            }

            //没进base case说明至少有一个条件没满足,也就是要么没碰到下边,要么没碰到左边,要么这两个边都没碰到
            //分为如果到达下边界,右边界,和没有到达下右边界该怎么走
            if (row == grid.length - 1) {   //如果到达下边界,只能向右走
                return process(grid, row, col + 1) + grid[row][col];
            }
            if (col == grid[0].length - 1) {    //如果到达右边界,只能向下走
                return process(grid, row + 1, col) + grid[row][col];
            }
            if (row < grid.length - 1 && col < grid[0].length - 1) { //如果两个边界都没有碰到,而且还能继续往下走,选择更小的那一个走
                return Math.min(
                        process(grid, row, col + 1) + grid[row][col],
                        process(grid, row + 1, col) + grid[row][col]
                );
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC86MinimunPathSum().new Solution();
        int[][] test = new int[][] {{1,2,5},{3,2,1}};
        int res = solution.minPathSum(test);
        System.out.println(res);
    }

}
