package leetcode.topinterview;

public class LC48RotateImage {
    //给定一个 n × n 的二维矩阵表示一个图像。
    //
    //将图像顺时针旋转 90 度。
    //
    //说明：
    //
    //你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
    //
    //示例 1:
    //
    //给定 matrix =
    //[
    //  [1,2,3],
    //  [4,5,6],
    //  [7,8,9]
    //],
    //
    //原地旋转输入矩阵，使其变为:
    //[
    //  [7,4,1],
    //  [8,5,2],
    //  [9,6,3]
    //]
    //示例 2:
    //
    //给定 matrix =
    //[
    //  [ 5, 1, 9,11],
    //  [ 2, 4, 8,10],
    //  [13, 3, 6, 7],
    //  [15,14,12,16]
    //],
    //
    //原地旋转输入矩阵，使其变为:
    //[
    //  [15,13, 2, 5],
    //  [14, 3, 4, 1],
    //  [12, 6, 8, 9],
    //  [16, 7,10,11]
    //]
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/rotate-image
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public void rotate(int[][] matrix) {
            //和旋转打印矩阵的思路一样,不要想着每一个点该怎么办,而是要想着整体是一个什么样的趋势,
            //从外向内,每次交换四个点的值,
            //注意,换点值的时候,对于每一个圈换n-1个点的值,
            int helpJ = matrix[0].length - 1;
            for (int i = 0; i < matrix.length / 2; i++) {
                for (int j = 0; j < helpJ; j++) {
                    swap(matrix, i, i, matrix.length - 1 - i, matrix.length - 1 - i, j);
                }
                //注意,每次是比之前少2而不是1
                helpJ -= 2;
            }
        }

        //给定矩阵与左上角与右下角,将该坐标点对应的四个位置进行轮换
        public void swap(int[][] matrix, int leftTopRow, int leftTopCol, int rightDownRow, int rightDownCol, int j) {
            int oneRow = leftTopRow;
            int oneCol = leftTopCol + j;
            int twoRow = leftTopRow + j;
            int twoCol = rightDownCol;
            int thirdRow = rightDownRow;
            int thirdCol = rightDownCol - j;
            int fourRow = rightDownRow - j;
            int fourCol = leftTopCol;

            int temp1 = matrix[oneRow][oneCol];
            int temp2 = matrix[twoRow][twoCol];
            int temp3 = matrix[thirdRow][thirdCol];
            int temp4 = matrix[fourRow][fourCol];
            matrix[oneRow][oneCol] = temp4;
            matrix[twoRow][twoCol] = temp1;
            matrix[thirdRow][thirdCol] = temp2;
            matrix[fourRow][fourCol] = temp3;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC48RotateImage().new Solution();
        int[][] test = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution.rotate(test);

    }
}
