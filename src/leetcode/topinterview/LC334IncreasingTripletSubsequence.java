package leetcode.topinterview;

public class LC334IncreasingTripletSubsequence {
    //给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
    //数学表达式如下:
    //如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
    //使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
    //说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
    //示例 1:
    //输入: [1,2,3,4,5]
    //输出: true
    //示例 2:
    //输入: [5,4,3,2,1]
    //输出: false
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean increasingTriplet(int[] nums) {
            //双指针
            //子序列指可以不连续,但是顺序不能变
            //用两个变量 r1, r2 分别记录第一小和第二小的数。然后遍历 nums。只要碰到比 r1 小的数我们就替换掉 r1，碰到比 r1 大但比 r2 小的数就替换 r2。
            //只要碰到比 r2 大的数就已经满足题意了。
            //
            //作者：rockypan
            //链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/jian-dan-hao-dong-yi-shi-xian-yi-ci-xun-huan-guo-b/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < r1) {
                    r1 = nums[i];
                }
                if (nums[i] > r1 && nums[i] < r2) {
                    r2 = nums[i];
                }
                if (nums[i] > r2) {
                    return true;
                }
            }
            return false;
        }
    }
}
