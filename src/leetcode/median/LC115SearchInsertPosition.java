package leetcode.median;

public class LC115SearchInsertPosition {
    //题目描述
    //给出一个有序的数组和一个目标值，如果数组中存在该目标值，则返回该目标值的下标。如果数组中不存在该目标值，则返回如果将该目标值插入这个数组应该插入的位置的下标
    //假设数组中没有重复项。
    //下面给出几个样例：
    //[1,3,5,6], 5 → 2
    //[1,3,5,6], 2 → 1
    //[1,3,5,6], 7 → 4
    //[1,3,5,6], 0 → 0
    public class Solution {
        /**
         * @param A      int整型一维数组
         * @param target int整型
         * @return int整型
         */
        public int searchInsert(int[] A, int target) {
            // write code here
            //思路:
            //  使用二分查找,如果有,直接返回下标
            //否则
            //  找到第一个大于/小于这个数的位置,并将找到的位置的左边/右边下标返回
            if (A == null || A.length == 0) return 0;

            int left = 0;
            int right = A.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (A[mid] == target) {
                    return mid;
                } else if (A[mid] > target) {
                    right = mid - 1;
                } else if (A[mid] < target) {
                    left = mid + 1;
                }
            }
            //如果跳出循环了还没找到,证明此时left与right一定停在比这个数小于比这个数大的数之间
            //且left在右边,right在左边(二分查找的终止条件)
            //插在位于右边的left比较好,因为left不会是-1
            return left;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC115SearchInsertPosition().new Solution();
        int res = solution.searchInsert(new int[]{1, 3}, 0);
        System.out.println('1');
    }
}
