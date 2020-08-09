package leetcode.topinterview;

public class LC33SearchInRotatedSortedArray {
    //假设按照升序排序的数组在预先未知的某个点上进行了旋转。
    //( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
    //搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
    //你可以假设数组中不存在重复的元素。
    //你的算法时间复杂度必须是 O(log n) 级别。
    //示例 1:
    //输入: nums = [4,5,6,7,0,1,2], target = 0
    //输出: 4
    //示例 2:
    //输入: nums = [4,5,6,7,0,1,2], target = 3
    //输出: -1
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link LC153FindMinimumInRotatedSortedArray}
     */
    class Solution {
        public int search(int[] nums, int target) {
            //使用上面链接中的方法作为本题的基础
            //求出这个最小点,以这个最小点为基准,将这个数组分为两个部分,两个部分各自使用二分法查找目标值
            if (nums == null || nums.length == 0) return -1;
            int res = -1;
            //找到最小值,为分割点
            int minIndex = findMin(nums);
            int leftIndex = 0;
            int rightIndex = nums.length - 1;
            //根据这个分割点,将数组分成两个,然后各自进行二分法
            int leftPartRes = Math.max(-1, binarySearchIndex(nums, leftIndex, minIndex - 1, target));
            int rightPartRes = Math.max(-1, binarySearchIndex(nums, minIndex, rightIndex, target));
            if (leftPartRes == rightPartRes) {
                return -1;
            } else {
                return leftPartRes == -1 ? rightPartRes : leftPartRes;
            }
        }

        //给定数组与数组搜索范围与目标数,返回这个目标数所在的下标,如果没有返回-1
        public int binarySearchIndex(int[] nums, int leftIndex, int rightIndex, int target) {
            while (leftIndex <= rightIndex) {
                int mid = leftIndex + (rightIndex - leftIndex) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    leftIndex = mid + 1;
                } else {
                    rightIndex = mid - 1;
                }
            }
            return -1;
        }

        /**
         * 在有序数组的旋转数组中找旋转点
         * {@link LC153FindMinimumInRotatedSortedArray}
         */
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else {    //因为数组中不存在重复的元素,所以在nums[mid] == nums[right]的时候,就是mid,left,right重合的时候
                    left = mid + 1;
                }
            }
            return mid;
        }
    }
}
