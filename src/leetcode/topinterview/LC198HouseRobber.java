package leetcode.topinterview;

public class LC198HouseRobber {
    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //示例 1：
    //输入：[1,2,3,1]
    //输出：4
    //解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
    //     偷窃到的最高金额 = 1 + 3 = 4 。
    //示例 2：
    //输入：[2,7,9,3,1]
    //输出：12
    //解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
    //     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
    //提示：
    //0 <= nums.length <= 100
    //0 <= nums[i] <= 400
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/house-robber
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int rob(int[] nums) {
            boolean[] haveRobbed = new boolean[nums.length];
            return process(nums);
        }


        //递归改动态规划
        //动的只有i,从0到nums.length
        public int process(int[] nums) {
            int[] dp = new int[nums.length + 1];
            //看base case填初始值
            dp[nums.length] = 0;
            //看递归函数中的功能来确定每个位置该怎么填从而确定表该以一个什么样的顺序来填
            //每个位置都依赖于右边的一个或者两个位置,所有从右到左填
            for (int i = dp.length - 2; i >= 0; i--) {
                if (i + 2 < dp.length) {
                    dp[i] = Math.max(dp[i + 2] + nums[i], dp[i + 1]);
                }else {
                    dp[i] = Math.max(nums[i], dp[i + 1]);
                }
            }
            return dp[0];
        }


        //递归功能:
        //  给数组,给当前位置i,前面的位置全都抢过了,返回后面能够抢到的最多钱
        //https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-dong-tai-gui-hua-jie-gou-hua-si-lu-/
        public int process(int[] nums, int i){
            //base case
            if (i >= nums.length) {
                return 0;
            }

            //如果抢第k间房屋,那么一定不能抢k + 1
            //如果不抢第k间屋,那么可以抢k + 1,也可以不抢k + 1
            return Math.max(process(nums, i + 2) + nums[i], //k抢了,k + 1一定不能抢
                    process(nums, i + 1)  //k没抢,k+1可抢可不抢,让下一层自己决定
            );

        }

        //递归功能:
        //  给数组,给当前位置i,哪些位置已经抢过了(相临的不能再抢),
        //  位置i与i之后的都没有抢过,前面的全抢过了,返回后面能够抢到的最多钱
        //这个递归虽然实现了功能,但是没有办法改动态规划
        public int process1(int[] nums, int i, boolean[] haveRobbed) {
            //base case
            if (i == nums.length) {
                return 0;
            }

            int res = 0;
            //当前的位置前面的位置如果没有抢过,可以选择抢或者不抢
            //如果当前位置前面的位置已经抢过,则这个位置一定不能抢
            if (i == 0) {   //第一个位置,可以选择要或者不要
                res = Math.max(res, process1(nums, i + 1, haveRobbed));
                haveRobbed[i] = true;
                res = Math.max(res, process1(nums, i + 1, haveRobbed) + nums[0]);
                haveRobbed[i] = false;
            }
            if (i > 0 && haveRobbed[i - 1]) {   //前面的位置抢过了
                res = Math.max(res, process1(nums, i + 1, haveRobbed));
            }
            if (i > 0 && !haveRobbed[i - 1]) {  //前面的位置没抢过
                res = Math.max(res, process1(nums, i + 1, haveRobbed));
                haveRobbed[i] = true;
                res = Math.max(res, process1(nums, i + 1, haveRobbed) + nums[i]);
                haveRobbed[i] = false;
            }
            return res;
        }

    }
}
