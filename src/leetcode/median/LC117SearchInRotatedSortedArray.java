package leetcode.median;

public class LC117SearchInRotatedSortedArray {
    //题目描述
    //给出一个转动过的有序数组，你事先不知道该数组转动了多少
    //(例如,0 1 2 4 5 6 7可能变为4 5 6 7 0 1 2).
    //在数组中搜索给出的目标值，如果能在数组中找到，返回它的索引，否则返回-1。
    //假设数组中不存在重复项。
    public class Solution {
        /**
         *
         * @param A int整型一维数组
         * @param target int整型
         * @return int整型
         */
        public int search (int[] A, int target) {
            // write code here
            //思路:
            //  首先判断数组是否旋转,如果最后一个数小于第一个数,说明旋转了(没有重复项)
            //  如果没旋转,直接二分,如果旋转了找到旋转点,分为两个数组,分别进行二分查找
            for (int i = 0; i < A.length; i++) {
                if (A[i] == target) {
                    return i;
                }
            }
            return -1;
        }
    }
}
