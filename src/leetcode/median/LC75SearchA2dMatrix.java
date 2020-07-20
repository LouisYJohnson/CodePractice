package leetcode.median;

public class LC75SearchA2dMatrix {
    //题目描述
    //请写出一个高效的在m*n矩阵中判断目标值是否存在的算法，矩阵具有如下特征：
    //每一行的数字都从左到右排序
    //每一行的第一个数字都比上一行最后一个数字大
    //例如：
    //对于下面的矩阵：
    //[
    // [1,   3,  5,  7],
    // [10, 11, 16, 20],
    // [23, 30, 34, 50]
    // ]
    //要搜索的目标值为3，返回true；
    public class Solution {
        /**
         *
         * @param matrix int整型二维数组
         * @param target int整型
         * @return bool布尔型
         */
        public boolean searchMatrix (int[][] matrix, int target) {
            // write code here
            //思路:从右上角开始找,如果当前位置的数==target就返回
            //  每次移动的时候都保证这个数在右上角
            //  如果当前数大于target则向左移动,因为这个数下面的数都比它大,
            //  如果当前数小于target则向下移动,因为这个数左边的都比他小
            int row = 0;
            int col = matrix[0].length - 1;
            while (row < matrix.length && col < matrix[0].length && row >=0 && col >= 0) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) {
                    row++;
                }else {
                    col--;
                }
            }
            return false;
        }
    }
}
