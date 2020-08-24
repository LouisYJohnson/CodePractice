package leetcode.topinterview;

public class LC322CoinChange {
    //给定不同面额的硬币 coins 和一个总金额 amount。
    // 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
    // 如果没有任何一种硬币组合能组成总金额，返回 -1。
    //示例 1:
    //输入: coins = [1, 2, 5], amount = 11
    //输出: 3
    //解释: 11 = 5 + 5 + 1
    //示例 2:
    //输入: coins = [2], amount = 3
    //输出: -1
    //说明:
    //你可以认为每种硬币的数量是无限的。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/coin-change
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/coin-change/solution/javadi-gui-ji-yi-hua-sou-suo-dong-tai-gui-hua-by-s/
        int res = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            if (coins == null || coins.length == 0) {
                return -1;
            }

//            process(coins, amount, 0);
//            return res == Integer.MAX_VALUE ? -1 : res;
            return process1(coins, amount);
        }

        //回溯改动态规划
        public int process1(int[] coins, int amount) {
            //实际上是个一维的动态规划,amount才是坐标
            //从0到amount
            int[] dp = new int[amount + 1];
            dp[0] = 0;
            //每个位置依赖的值是从coins中遍历一次,取到前面且不越界的最小值
            //所以从左到右填表,每次填表遍历一次coins
            for (int i = 1; i < dp.length; i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    //这个判断必须在外面,如果不越界就对dp[i-coins[j]] + 1 和temp中取最小的话
                    //dp[i-coins[j]]有可能本身就是Inter.MAX_VALUE,再+1就溢出变负数了,出现错误值
                    if (i - coins[j] >= 0 && dp[i - coins[j]] < temp) {
                        temp = dp[i - coins[j]] + 1;
                    }
                }
                dp[i] = temp;
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }


        //回溯
        //  给定硬币数组,与所需要组成的数量,返回组成该数量需要的最少硬币数
        public void process(int[] coins, int amount, int count) {
            if (amount == 0) {
                res = Math.min(res, count);
            } else if (amount < 0) {
                return;
            }

            for (int i = 0; i < coins.length; i++) {
                process(coins, amount - coins[i], count + 1);
            }
        }

        //递归
        //  给定当前位置i,i之前的位置上的硬币(不包括i都已经选好了,后面的都没选)
        //还有剩余的amount,返回能够组成amount需要的最小硬币个数
        public int process1(int[] counts, int i, int amount, int flag) {
            //base case
            if (i == counts.length) {
                if (amount == 0 && flag == 1) {
                    return 0;
                } else if (amount == 0 && flag == 0) {
                    return 0;
                } else if (amount != 0 && flag == 1) {
                    return Integer.MAX_VALUE - 1;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (amount < 0 && flag == 1) {
                    return Integer.MAX_VALUE - 1;
                } else if (amount < 0 && flag == 0) {
                    return Integer.MAX_VALUE;
                }
            }

            //分为当前位置要之后继续走,不要继续走,再要一次,但是这种方法没有办法处理边界条件
            return Math.min(1 + process1(counts, i + 1, amount - counts[i], 1),
                    Math.min(process1(counts, i + 1, amount, 0),
                            1 + process1(counts, i, amount - counts[i], 1)));
        }
    }


}
