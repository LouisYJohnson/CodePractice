package leetcode.median;

public class LC116SearchForARange {
    //题目描述
    //给出一个有序数组，请在数组中找出目标值的起始位置和结束位置
    //你的算法的时间复杂度应该在O(log n)之内
    //如果数组中不存在目标，返回[-1, -1].
    //例如：
    //给出的数组是[5, 7, 7, 8, 8, 10]，目标值是8,
    //返回[3, 4].
    public class Solution {
        /**
         *
         * @param A int整型一维数组
         * @param target int整型
         * @return int整型一维数组
         */
        public int[] searchRange (int[] A, int target) {
            // write code here
            //思路:
            //  使用二分查找,找到最左边的等于这个数的下标,然后以这个下标开始向右,一直到找不到重复数为止
            if (A == null || A.length == 0) return new int[] {-1, -1};
            //要么下面这三行,要么将while中的left<right改成left<=right
//            if (A.length == 1) {
//                return A[0] == target ? new int[] {0, 0} : new int[] {-1, -1};
//            }

            int left = 0;
            int right = A.length - 1;
            int firstLeft = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (A[mid] == target) {
                    right = mid - 1;
                    firstLeft = mid;
                }else if (A[mid] > target) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            //如果没找到
            if (firstLeft == -1) {
                return new int[] {-1, -1};
            }
            //找到了,向右遍历
            int lastRight = firstLeft + 1;
            while (lastRight < A.length && A[firstLeft] == A[lastRight]) {
                lastRight++;
            }
            //如果没有重复的
            if (lastRight == firstLeft + 1) {
                return new int[] {firstLeft, firstLeft};
            }else {
                return new int[] {firstLeft, lastRight - 1};
            }

        }
    }

    public static void main(String[] args) {
        Solution solution = new LC116SearchForARange().new Solution();
        solution.searchRange(new int[] {1}, 1);
    }
}
