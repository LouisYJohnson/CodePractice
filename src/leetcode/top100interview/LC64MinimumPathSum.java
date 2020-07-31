package leetcode.top100interview;

public class LC64MinimumPathSum {
    //给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    //说明：每次只能向下或者向右移动一步。
    //示例:
    //输入:
    //[
    //  [1,3,1],
    //  [1,5,1],
    //  [4,2,1]
    //]
    //输出: 7
    //解释: 因为路径 1→3→1→1→1 的总和最小。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/minimum-path-sum
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    //递归改动态规划
    class Solution {
        public int minPathSum(int[][] grid) {
            //递归
            return process(grid, 0, 0);
        }

        //递归函数功能:
        //  给定当前位置,返回从当前位置到右下角的最小路径(当前位置的数还没选)
        public int process(int[][] grid, int row, int col){
            //base case 当前位置就在右下角了
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return grid[grid.length - 1][grid[0].length - 1];
            }

            int res = grid[row][col];
            //否则判断是否在下边界或者右边界
            if (row == grid.length - 1) {
                //process中表示从下一个位置开始走的最小路径和,并没有包含当前位置的数,所以这里要加上才是结果
                res += process(grid, row, col + 1);
            }
            if (col == grid[0].length - 1) {
                res += process(grid, row + 1, col);
            }
            if (row < grid.length - 1 && col < grid[0].length - 1) {
                res += Math.min(
                        process(grid, row + 1, col),
                        process(grid, row, col + 1)
                );
            }
            return res;

        }
    }
}
