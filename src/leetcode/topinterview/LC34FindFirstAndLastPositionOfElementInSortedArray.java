package leetcode.topinterview;

import com.newcoder.zuo3.advanced.class05.Code_03_Min_Gold;

public class LC34FindFirstAndLastPositionOfElementInSortedArray {
    //给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    //你的算法时间复杂度必须是 O(log n) 级别。
    //如果数组中不存在目标值，返回 [-1, -1]。
    //示例 1:
    //输入: nums = [5,7,7,8,8,10], target = 8
    //输出: [3,4]
    //示例 2:
    //输入: nums = [5,7,7,8,8,10], target = 6
    //输出: [-1,-1]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * 在排序数组中
     * 使用二分法可以找到恰好等于或者大于
     * 或者恰好小于或者等于一个数的元素的位置
     * {@link Code_03_Min_Gold}
     */

    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[] {-1, -1};
            }
            //使用两次二分,分别找到左边界和右边界
            int left = -1;
            int right = -1;
            int helpLeft = 0;
            int helpRight = nums.length - 1;

            int leftBound = -1;
            while (helpLeft <= helpRight) {
                int mid = helpLeft + (helpRight - helpLeft) / 2;
                if (nums[mid] >= target) { //找左边界,如果mid对应值大于等于target,则right为mid
                    leftBound = mid;
                    helpRight = mid - 1;
                } else {
                    helpLeft = mid + 1;
                }
            }

            helpLeft = 0;
            helpRight = nums.length - 1;
            int rightBound = -1;
            while (helpLeft <= helpRight) {  //找右边界,如果mid对应值小于等于target,则left为mid+1
                int mid = helpLeft + (helpRight - helpLeft) / 2;
                if (nums[mid] <= target) {
                    rightBound = mid;
                    helpLeft = mid + 1;
                } else {
                    helpRight = mid - 1;
                }
            }
            //防止数组只有一个元素,此种情况下左边界与右边界只会更新一个
            if (leftBound == -1 || rightBound == -1) {
                return new int[] {-1, -1};
            }
            leftBound = nums[leftBound] == target ? leftBound : -1;
            rightBound = nums[rightBound] == target ? rightBound : -1;
            return new int[] {leftBound, rightBound};
        }
    }
}
