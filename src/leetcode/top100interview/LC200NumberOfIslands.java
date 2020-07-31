package leetcode.top100interview;

public class LC200NumberOfIslands {
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
    //此外，你可以假设该网格的四条边均被水包围。
    //示例 1:
    //输入:
    //[
    //['1','1','1','1','0'],
    //['1','1','0','1','0'],
    //['1','1','0','0','0'],
    //['0','0','0','0','0']
    //]
    //输出: 1
    //示例 2:
    //输入:
    //[
    //['1','1','0','0','0'],
    //['1','1','0','0','0'],
    //['0','0','1','0','0'],
    //['0','0','0','1','1']
    //]
    //输出: 3
    //解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/number-of-islands
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int res = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    //必须不是2或者0才能进行扩充与岛屿量的计算
                    if (grid[row][col] != '2' && grid[row][col] != '0') {
                        process(grid, row, col);
                        res++;
                    }
                }
            }
            return res;
        }

        //递归函数功能:
        //  输入一个二维矩阵与在这个二维矩阵上的坐标,将这个坐标上(包括这个坐标)的水平或者竖直方向上元素为1的位置都变为2
        public void process(char[][] grid, int row, int col) {
            //base case
            if (row == grid.length || col == grid[0].length
                    || row < 0 || col < 0
                    || grid[row][col] == '2' || grid[row][col] == '0') {
                return;
            }

            grid[row][col] = '2';
            process(grid, row + 1, col);
            process(grid, row - 1, col);
            process(grid, row, col + 1);
            process(grid, row, col - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC200NumberOfIslands().new Solution();
        char[][] test = new char[][] {{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        int res = solution.numIslands(test);
        System.out.println(res);
    }
}
