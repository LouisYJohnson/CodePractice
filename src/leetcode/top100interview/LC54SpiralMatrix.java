package leetcode.top100interview;

import java.util.ArrayList;
import java.util.List;

public class LC54SpiralMatrix {
    //给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
    //示例 1:
    //输入:
    //[
    // [ 1, 2, 3 ],
    // [ 4, 5, 6 ],
    // [ 7, 8, 9 ]
    //]
    //输出: [1,2,3,6,9,8,7,4,5]
    //示例 2:
    //输入:
    //[
    //  [1, 2, 3, 4],
    //  [5, 6, 7, 8],
    //  [9,10,11,12]
    //]
    //输出: [1,2,3,4,8,12,11,10,9,5,6,7]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/spiral-matrix
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            //不要局限于每一个位置应该怎么处理,而是从整体上去考虑该怎么打印
            //一个矩阵的左上角坐标与右下角坐标就可以确定一个环应该如何打印
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return new ArrayList<>();
            }
            int leftTopRow = 0;
            int leftTopCol = 0;
            int rightBotRow = matrix.length - 1;
            int rightBotCol = matrix[0].length - 1;
            List<Integer> res = new ArrayList<>();

            //左上角右下角斜着向中间靠拢,条件就是两个点的行和列重合后不能再动了
            while (leftTopRow <= rightBotRow && leftTopCol <= rightBotCol) {
                getSpiralOrder(matrix, leftTopRow, leftTopCol, rightBotRow, rightBotCol, res);
                leftTopRow++;
                leftTopCol++;
                rightBotRow--;
                rightBotCol--;
            }
            return res;
        }

        //根据输入的点的情况,判断是打印一圈还是一行,还是一列
        public void getSpiralOrder(int[][] matrix,
                                   int leftTopRow, int leftTopCol, int rightBotRow, int rightBotCol,
                                   List<Integer> res) {
            if (leftTopRow == rightBotRow) {
                //只有一行
                for (int col = leftTopCol; col <= rightBotCol; col++) {
                    res.add(matrix[leftTopRow][col]);
                }
            } else if (leftTopCol == rightBotCol) {
                //只有一列
                for (int row = leftTopRow; row <= rightBotRow; row++) {
                    res.add(matrix[row][leftTopCol]);
                }
            }else {
                //一圈,注意重复点不要重复选取
                //上横
                for (int col = leftTopCol; col <= rightBotCol; col++) {
                    res.add(matrix[leftTopRow][col]);
                }
                //下竖
                for (int row = leftTopRow + 1; row <= rightBotRow; row++) {
                    res.add(matrix[row][rightBotCol]);
                }
                //下横
                for (int col = rightBotCol - 1; col >= leftTopCol; col--) {
                    res.add(matrix[rightBotRow][col]);
                }
                //上竖
                for (int row = rightBotRow - 1; row >= leftTopRow + 1; row--) {
                    res.add(matrix[row][leftTopCol]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC54SpiralMatrix().new Solution();
        int[][] test = new int[][] {{1,2,3,4,5,6,7,8,9,10}};
        List<Integer> res = solution.spiralOrder(test);
        System.out.println(1);
    }
}
