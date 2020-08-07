package leetcode.top100interview;

public class LC322 {
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

        //TODO
        public int coinChange(int[] coins, int amount) {
            if (coins == null || coins.length == 0) {
                return -1;
            }

//            process(coins, amount, 0);
//            return res == Integer.MAX_VALUE ? -1 : res;
            return process1(coins, 0, amount, 0);
        }

        //递归
        //  给定当前位置i,i之前的位置上的硬币(不包括i都已经选好了,后面的都没选)
        //还有剩余的amount,返回能够组成amount需要的最小硬币个数
        public int process1(int[] counts, int i, int amount, int flag) {
            //base case
            if (i == counts.length) {
                if (amount == 0 && flag == 1) {
                    return 0;
                } else if (amount == 0 && flag == 0){
                    return 0;
                } else if (amount != 0 && flag == 1) {
                    return Integer.MAX_VALUE - 1;
                }else {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (amount < 0 && flag == 1) {
                    return Integer.MAX_VALUE - 1;
                }else if (amount < 0 && flag == 0){
                    return Integer.MAX_VALUE;
                }
            }

            //分为当前位置要之后继续走,不要继续走,再要一次,但是这种方法没有办法处理边界条件
            return Math.min(1 + process1(counts, i + 1, amount - counts[i], 1),
                    Math.min(process1(counts, i + 1, amount, 0),
                            1 + process1(counts, i, amount - counts[i], 1)));
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
    }


}
