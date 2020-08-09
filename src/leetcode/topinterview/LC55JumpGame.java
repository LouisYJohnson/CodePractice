package leetcode.topinterview;

public class LC55JumpGame {
    //给定一个非负整数数组，你最初位于数组的第一个位置。
    //数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //判断你是否能够到达最后一个位置。
    //示例 1:
    //输入: [2,3,1,1,4]
    //输出: true
    //解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
    //示例 2:
    //输入: [3,2,1,0,4]
    //输出: false
    //解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/jump-game
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean canJump(int[] nums) {
            //定义几个变量,分别表示当前状态
            //index:当前所在位置
            //next:当前所在位置能够跳到最远的位置
            //每次更新能够跳到的最远边界,
            // 如果当前位置是最右边界而且还是0,而且这个位置并不是最后一个位置(这个位置一定不能是最后一个位置,在程序中
            // 如果next到了最后一个位置了就直接return true了)
            // 则为false
            if (nums == null || nums.length == 0) {
                return false;
            }
            if (nums.length == 1) {
                return true;
            }
            int next = 0;

            for (int i = 0; i < nums.length; i++) {
                if (next == i && nums[i] == 0) {    //这个位置走不了了
                    return false;
                }
                next = Math.max(next, i + nums[i]);
                if (next >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }
}
