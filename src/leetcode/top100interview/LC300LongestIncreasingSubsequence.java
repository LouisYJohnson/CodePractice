package leetcode.top100interview;

public class LC300LongestIncreasingSubsequence {
    //给定一个无序的整数数组，找到其中最长上升子序列的长度。
    //示例:
    //输入: [10,9,2,5,3,7,101,18]
    //输出: 4
    //解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
    //说明:
    //可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
    //你算法的时间复杂度应该为 O(n2) 。
    //进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //子序列子数组问题:从必须以某个位置结尾,必须以某个位置开头,i位置之前都排完了入手,是很好的思路!!!

    /**
     * {@link }
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
//            int res = 0;
//            for (int i = 0; i < nums.length; i++) {
//                res = Math.max(process(nums, i), res);
//            }
//            return res;
            return process1(nums);
        }

        //递归改动态规划
        //主函数中只能调用循环来找每一个位置结尾的最长上升子序列
        //这里需要改一下(结合主函数与递归函数)才能得到这个表该怎么填
        //变量只有i,i从0,到nums.length-1,其中,每一个i都要与前面找最大值,最大值++才是这个格子该填的
        //最后结合主函数,找到dp中的最大值,即为结果
        //其实改地方在于让递归函数从得到一个位置结尾的最长上升子序列变为每一个位置结尾的最长上升子序列值
        //而最终的结果查找还是要从dp中找最大值
        //也就是说,dp的含义是每个位置结尾的最大值
        public int process1(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int res = dp[0];
            //这一层for表示主函数中的找以每一个位置为结尾得到的最长递增子序列(主要体现在下面的res = Math.max(res, dp[i]);)
            for (int i = 1; i < nums.length; i++) {
                dp[i] = 0;
                //这一层是递归改动态规划,dp[i]的位置填表需要用到前面的位置,并且是前面所有位置中的最大的
                //主要看递归中的函数,对应着填表,i是变量,process(i)表示表中坐标
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j]);
                    }
                }
                dp[i]++;
                res = Math.max(res, dp[i]);
            }
            return res;
        }

        //递归函数功能:
        //  给定一个数组和一个下标i,返回必须以该下标结尾的最长上升子序列
        //主函数中只能调用循环来找每一个位置结尾的最长上升子序列
        public int process(int[] nums, int i) {
            //base case
            if (i == 0) {
                return 1;
            }

            int res = 0;
            //看这个i位置要和前面哪一个位置结尾的长度连在一起才是最长
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    res = Math.max(process(nums, j), res);
                }
             }
            res++;  //能连接并且最长的递增子序列后别忘了算上自己
            return res;
        }

//        //回溯法,复杂度太高了
//        public void process(int[] nums, int i, ArrayList<Integer> helpList) {
//            //base case
//            if (i == nums.length) {
//                res = Math.max(res, helpList.size());
//                return;
//            }
//
//            for (int j = i; j < nums.length; j++) {
//                //这里必须是<=,这样就可以去掉重复值了
//                if (helpList.size() != 0 && nums[j] <= helpList.get(helpList.size() - 1)) {
//                    if (j == nums.length - 1) {
//                        res = Math.max(res, helpList.size());
//                    }
//                    continue;
//                }
//                helpList.add(nums[j]);
//                process(nums, j + 1, helpList);
//                helpList.remove(helpList.size() - 1);
//            }
//        }
    }

    public static void main(String[] args) {
        Solution solution = new LC300LongestIncreasingSubsequence().new Solution();
        int[] test = new int[] {1};
        int res = solution.lengthOfLIS(test);
        System.out.println(res);
    }
}
