package leetcode.top100interview;

import java.util.ArrayList;
import java.util.HashMap;

public class LC1TwoSum {
    //给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    //示例:
    //给定 nums = [2, 7, 11, 15], target = 9
    //因为 nums[0] + nums[1] = 2 + 7 = 9
    //所以返回 [0, 1]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/two-sum
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            //两遍哈希表
            //第一遍:key为数组值,value为数组下标
            HashMap<Integer, Integer> helpMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                helpMap.put(nums[i], i);
            }
            //第二遍,遍历数组(除了当前遍历到的元素,因为每个元素只能用一次)
            //去找hashMap中有没有当前遍历到的元素和target之差,如果有,返回
            for (int i = 0; i < nums.length; i++) {
                if (helpMap.containsKey(target - nums[i]) && helpMap.get(target - nums[i]) != i) {
                    return new int[]{i, helpMap.get(target - nums[i])};
                }
            }
            return null;
        }

        public int[] twoSum1(int[] nums, int target) {
            //两个指针,指向左边和右边,当左边与右边代表元素相加==target的时候返回答案,否则按照与target的差距来变化
            //这种方法只适用于有序数组,对于无序数组,是不可用的
            return null;
        }


    }
}
