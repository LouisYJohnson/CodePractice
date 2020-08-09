package leetcode.topinterview;

public class LC289GameOfLife {
    //根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
    //给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    //如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
    //根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
    //示例：
    //输入：
    //[
    //  [0,1,0],
    //  [0,0,1],
    //  [1,1,1],
    //  [0,0,0]
    //]
    //输出：
    //[
    //  [0,0,0],
    //  [1,0,1],
    //  [0,1,1],
    //  [0,1,0]
    //]
    //进阶
    //你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
    //本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/game-of-life
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        public void gameOfLife(int[][] board) {
            //因为Java中不能在函数中改变变量的值,board在这里只是个临时变量,在函数结束后,就没了,外面的实参指向的依然是那个地址
            //所以就要复制一份board并在那个复制出来的board上做判断,最终的结果直接在传入的board中指向的内存地址改变
            int[][] helpBoard = new int[board.length][board[0].length];
            for (int i = 0; i < helpBoard.length; i++) {
                for (int j = 0; j < helpBoard[0].length; j++) {
                    helpBoard[i][j] = board[i][j];
                }
            }

            for (int row = 0; row < helpBoard.length; row++) {
                for (int col = 0; col < helpBoard[0].length; col++) {
                    int num = 0;
                    //判断当前元素的周围八个位置是否都在边界内,并计算八邻域内的细胞个数
                    if (row - 1 >= 0 && helpBoard[row - 1][col] == 1) { //上
                        num++;
                    }
                    if (row + 1 < helpBoard.length && helpBoard[row + 1][col] == 1) {    //下
                        num++;
                    }
                    if (col + 1 < helpBoard[0].length && helpBoard[row][col + 1] == 1) {    //右
                        num++;
                    }
                    if (col - 1 >= 0 && helpBoard[row][col - 1] == 1) { //左
                        num++;
                    }
                    if (col - 1 >= 0 && row - 1 >= 0 && helpBoard[row - 1][col - 1] == 1) { //左上
                        num++;
                    }
                    if (col + 1 < helpBoard[0].length && row - 1 >= 0 && helpBoard[row - 1][col + 1] == 1) {    //右上
                        num++;
                    }
                    if (col - 1 >= 0 && row + 1 < helpBoard.length && helpBoard[row + 1][col - 1] == 1) {   //左下
                        num++;
                    }
                    if (col + 1 < helpBoard[0].length && row + 1 < helpBoard.length && helpBoard[row + 1][col + 1] == 1) { //右下
                        num++;
                    }

                    if (helpBoard[row][col] == 1 && num < 2) {
                        board[row][col] = 0;
                    }else if (helpBoard[row][col] == 1 && num > 3) {
                        board[row][col] = 0;
                    }else if (helpBoard[row][col] == 0 && num == 3) {
                        board[row][col] = 1;
                    }else if (helpBoard[row][col] == 1 && (num == 2 || num == 3)){
                        board[row][col] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC289GameOfLife().new Solution();
        int[][] test = new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        solution.gameOfLife(test);
        System.out.println(1);
    }
}
