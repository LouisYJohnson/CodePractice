package leetcode.topinterview;

import leetcode.median.LC75SearchA2dMatrix;

public class LC240SearchA2dMatrixII {
    //编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
    //
    //每行的元素从左到右升序排列。
    //每列的元素从上到下升序排列。
    //示例:
    //
    //现有矩阵 matrix 如下：
    //
    //[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]
    //给定 target = 5，返回 true。
    //
    //给定 target = 20，返回 false。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * 本题与下面这个不同,这里的下一行数值的第一个数是有可能比上一行的最后一个数小的,不能从右上角挪了
     * {@link LC75SearchA2dMatrix}
     */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            //每一行使用二分查找
            for (int i = 0; i < matrix.length; i++) {
                if (hasNum(matrix[i], target)) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasNum(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] < target) {
                    left = mid + 1;
                }
                if (nums[mid] > target) {
                    right = mid - 1;
                }
            }
            return false;
        }
    }
}
