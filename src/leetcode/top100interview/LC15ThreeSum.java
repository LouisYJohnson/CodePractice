package leetcode.top100interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15ThreeSum {
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    //
    //注意：答案中不可以包含重复的三元组。
    //示例：
    //给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    //满足要求的三元组集合为：
    //[
    //  [-1, 0, 1],
    //  [-1, -1, 2]
    //]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/3sum
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //回溯法时间复杂度较高,所以使用排序+双指针
        public List<List<Integer>> threeSum(int[] nums) {
            //首先对数组进行升序排序,并挨个遍历这个排序后的数组(以每个位置开始得到的三个下标组合)
            //如果当前遍历到的数组元素>0后面的肯定都>0,没有必要算了
            //否则,设置两个指针,left为当前元素的下一个,right为当前数组的最后一个
            //只动这两个指针,并比较这三个位置上的数的和与0的关系,>0right左移<0left右移,left和right不能重合,并收集这三个位置的坐标
            if (nums.length < 3) return new ArrayList<>();
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int curNum = nums[i];
                if (curNum > 0) {
                    return res;
                }
                if (i > 0 && curNum == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int curSum = nums[left] + nums[right] + curNum;
                    if (curSum == 0) {
                        List<Integer> helpList = new ArrayList<>();
                        helpList.add(nums[i]);
                        helpList.add(nums[left]);
                        helpList.add(nums[right]);
                        res.add(helpList);
                        //去掉所有重复的数
                        while (left < right && nums[left + 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right - 1] == nums[right]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (curSum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return res;
        }

        //回溯法
        public List<List<Integer>> threeSum1(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> helpList = new ArrayList<>();
            boolean[] isUsed = new boolean[nums.length];
            //必须和下面的去重语句一起使用才有效
            Arrays.sort(nums);
            process(nums, 3, 0, 0, isUsed, helpList, res);
            return res;
        }

        public void process(int[] nums, int constrain, int curSum, int curNumIndex, boolean[] isUsed, List<Integer> helpList, List<List<Integer>> res) {
            //base case
            if (constrain == 0) {
                if (curSum == 0) {
                    res.add(new ArrayList<>(helpList));
                } else {
                    return;
                }
            }

            for (int i = curNumIndex; i < nums.length; i++) {
                if (isUsed[i]) {
                    continue;
                } else if (i > curNumIndex && nums[i] == nums[i - 1]) {
                    //TODO 上面的去重语句必须和数组排序函数一起使用才有效!!!
                    continue;
                } else {
                    isUsed[i] = true;
                    helpList.add(nums[i]);
                    process(nums, constrain - 1, curSum + nums[i], i + 1, isUsed, helpList, res);
                    helpList.remove(helpList.size() - 1);
                    isUsed[i] = false;
                }
            }
        }

    }
}
