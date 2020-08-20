package leetcode.topinterview;

public class LC209MinimumSizeSubarraySum {
    //给定一个含有 n 个正整数的数组和一个正整数 s ，
    // 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
    //示例：
    //输入：s = 7, nums = [2,3,1,2,4,3]
    //输出：2
    //解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    //进阶：
    //如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            //https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
            //双指针,只要当前窗口内和满足了大于等于target,就更新长度,返回整体的最小
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int left = 0;
            int right = 0;
            int sum = 0;
            int res = Integer.MAX_VALUE;

            //每一轮迭代，将 nums[end] 加到sum，如果sum≥s，则更新子数组的最小长度
            // （此时子数组的长度是end-start+1，
            // 然后将nums[start]从sum中减去并将start右移，直到sum<s，
            // 在此过程中同样更新子数组的最小长度。
            // 在每一轮迭代的最后，将end右移。
            while (right < nums.length) {
                sum += nums[right];
                if (sum < s) {
                    right++;
                } else {
                    //应该在满足条件的时候,每次都判断是否是最小窗口,
                    //因为右边可能加进来个大值,让左边出去了好几个小值
                    while (sum >= s) {
                        res = Math.min(res, right - left + 1);
                        sum -= nums[left++];
                    }
                    right++;
                }
            }
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
}
