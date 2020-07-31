package leetcode.top100interview;

public class LC70ClimbingStairs {
    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    //注意：给定 n 是一个正整数。
    //示例 1：
    //输入： 2
    //输出： 2
    //解释： 有两种方法可以爬到楼顶。
    //1.  1 阶 + 1 阶
    //2.  2 阶
    //示例 2：
    //输入： 3
    //输出： 3
    //解释： 有三种方法可以爬到楼顶。
    //1.  1 阶 + 1 阶 + 1 阶
    //2.  1 阶 + 2 阶
    //3.  2 阶 + 1 阶
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/climbing-stairs
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int climbStairs(int n) {
//            return process(n);
            if (n < 2) return 1;
            return process1(n);
        }

        //递归改动态规划
        public int process1(int n) {
            int[] dp = new int[n + 1];
            for (int i = 0; i < 2; i++) {
                dp[i] = 1;
            }
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        //递归函数功能:
        //  给定剩余阶数,返回有多少种方法爬到楼顶
        public int process(int n) {
            //base case
            if (n < 2) {
                return 1;
            }

            return process(n - 1) + process(n - 2);
        }
    }
}
