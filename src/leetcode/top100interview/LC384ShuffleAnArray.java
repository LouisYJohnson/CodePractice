package leetcode.top100interview;

import java.util.HashMap;

public class LC384ShuffleAnArray {
    //打乱一个没有重复元素的数组。
    //示例:
    //// 以数字集合 1, 2 和 3 初始化数组。
    //int[] nums = {1,2,3};
    //Solution solution = new Solution(nums);
    //// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
    //solution.shuffle();
    //// 重设数组到它的初始状态[1,2,3]。
    //solution.reset();
    //// 随机返回数组[1,2,3]打乱后的结果。
    //solution.shuffle();
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/shuffle-an-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //关键要在于,洗牌的种类要满足n!才是正确的
    class Solution {
        private int[] nums;
        private int[] helpNums;

        public Solution(int[] nums) {
            this.nums = nums;
            this.helpNums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                helpNums[i] = nums[i];
            }
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            for (int i = 0; i < this.nums.length; i++) {
                this.nums[i] = this.helpNums[i];
            }
            return this.nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = 0; i < nums.length; i++) {
                //random是左闭右开区间,从0<=x<1,所以说最大值是nums.length-i而不是nums.length-i-1
                int helpRand = i + (int) (Math.random() * (nums.length - i));
                swap(i, helpRand);
            }
            return this.nums;
        }

        private void swap(int i, int j) {
            int temp = this.nums[i];
            this.nums[i] = this.nums[j];
            this.nums[j] = temp;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
