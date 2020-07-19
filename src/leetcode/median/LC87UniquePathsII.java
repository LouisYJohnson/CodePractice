package leetcode.median;

import leetcode.easy.LC88UniquePaths;

public class LC87UniquePathsII {
    //题目描述
    //继续思考题目"Unique Paths":
    //如果在图中加入了一些障碍，有多少不同的路径？
    //分别用0和1代表空区域和障碍
    //例如
    //下图表示有一个障碍在3*3的图中央。
    //[↵  [0,0,0],↵  [0,1,0],↵  [0,0,0]↵]
    //有2条不同的路径
    //备注：m和n不超过100.

    public class Solution {
        /**
         * @param obstacleGrid int整型二维数组
         * @return int整型
         * {@link LC88UniquePaths}
         */
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // write code here
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            return process2(m, n, obstacleGrid);
//            return process(0, 0, m, n, obstacleGrid);
        }

        //动态规划
        //只看变量与在递归中的单独一层的代码,其他的都不看
        public int process2(int m, int n, int[][] obstacleGrid) {
            //变量只有row与col,并且为从0到m-1,n-1,并且最终的结果在dp[0][0]的位置
            //所以构建如下
            int[][] dp = new int[m][n];
            //根据base case初始化dp
            if (obstacleGrid[m - 1][n - 1] != 0) {
                dp[m - 1][n - 1] = 0;
            } else {
                dp[m - 1][n - 1] = 1;
            }
            //根据其余条件确定其余位置的值
            //右下角的那个点已经填完了,不要动
            //如果在下边界的时候应该这么填表
            //一定要按照递归中的相加相减关系来填,不能自以为是填上1或者0!
            for (int col = n - 2; col >= 0; col--) {
                if (obstacleGrid[m - 1][col] != 1) {
                    dp[m - 1][col] = dp[m - 1][col + 1];
                } else {
                    dp[m - 1][col] = 0;
                }
            }
            //如果在右边界的时候应该这么填表
            for (int row = m - 2; row >= 0; row--) {
                if (obstacleGrid[row][n - 1] != 1) {
                    dp[row][n - 1] = dp[row + 1][n - 1];
                } else {
                    dp[row][n - 1] = 0;
                }
            }
            //如果是其他任意位置应该这么填表
            for (int col = n - 2; col >= 0; col--) {
                for (int row = m - 2; row >= 0; row--) {
                    if (obstacleGrid[row][col] != 1) {
                        dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
                    }
                }
            }
            return dp[0][0];
        }


        //递归函数oj超时,改动态规划
        //递归函数功能:
        //  输入当前位置与格子的总大小,与障碍数组,返回从当前位置出发只向右或向下有多少种有效路径
        public int process(int row, int col, int m, int n, int[][] obstacleGrid) {
            //base case
            if (row == m - 1 && col == n - 1 && obstacleGrid[row][col] != 0) {
                return 0;   //如果最后一个位置给挡上了,说明这不是一条有效的路径
            } else if (row == m - 1 && col == n - 1 && obstacleGrid[row][col] == 0) {
                return 1;
            }
            int res = 0;
            //这三个if条件不可能同时成立,只能成立1个
            //如果不是下边也不是右边,而且下一个位置还能走,那么在当前位置不是1的前提下,右走下走都可以
            if (row < m - 1 && col < n - 1 && obstacleGrid[row][col] != 1) {
                res += process(row + 1, col, m, n, obstacleGrid);
                res += process(row, col + 1, m, n, obstacleGrid);
            }
            //如果到了最下边,只能往右走,并且当前位置不能是1
            if (row == m - 1 && (obstacleGrid[row][col] != 1)) {
                res += process(row, col + 1, m, n, obstacleGrid);
            }
            //如果到了最右边,只能向下走,并且当前位置不能是1
            if (col == n - 1 && (obstacleGrid[row][col] != 1)) {
                res += process(row + 1, col, m, n, obstacleGrid);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC87UniquePathsII().new Solution();
        int[][] test = new int[][] {{0, 1}};
        int res = solution.uniquePathsWithObstacles(test);
        System.out.println(1);
    }
}
