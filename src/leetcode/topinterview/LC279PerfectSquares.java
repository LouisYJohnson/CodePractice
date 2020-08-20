package leetcode.topinterview;

public class LC279PerfectSquares {
    //给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
    //
    //示例 1:
    //
    //输入: n = 12
    //输出: 3
    //解释: 12 = 4 + 4 + 4.
    //示例 2:
    //
    //输入: n = 13
    //输出: 2
    //解释: 13 = 4 + 9.
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/perfect-squares
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int numSquares(int n) {
        //TODO 动态规划复杂度仍然过高
//            return process(n);
            return process1(n);
        }

        //递归改动态规划
        //由于可以变的参数只有n,而且n从0到输入的n
        public int process1(int n) {
            int[] dp = new int[n + 1];
            //根据base case填基本值
            dp[0] = 0;
            //根据递归函数中的写法,任意一个位置的值,取决于前面的所有值
            //根据递归确定任意一个位置依赖哪些其他位置,从而确定表该以什么顺序填
            //最外层,填表中所有值
            for (int i = 1; i < dp.length; i++) {
                //内层表示表中一个值依赖于哪些其他值
                int res = Integer.MAX_VALUE;
                //注意这里不是j <= n,因为在递归函数中n表示的是dp表中的坐标,所以这里写成i才对
                for (int j = 1; j <= i; j++) {
                    if (i - j >= 0 && isSquare(j)) {
                        res = Math.min(dp[i - j] + 1, res);
                    }
                }
                dp[i] = res;
            }
            return dp[n];
        }

        //递归函数功能:
        //  给定正整数n,返回组成和的完全平方数最少的个数
        public int process(int n) {
            //base case
            if (n == 0) {
                return 0;
            } else if (n < 0) {
                return Integer.MAX_VALUE;
            }

            int res = Integer.MAX_VALUE;
            //当前位置数字可以选小于n的完全平方数中的任意一个(0就不用加了,肯定不是完全平方数)
            for (int i = 1; i <= n; i++) {
                if (n - i >= 0 && isSquare(i)) {
                    res = Math.min(process(n - i) + 1, res);
                }
            }
            return res;
        }

        //数学技巧:所有的完全平方数都可以被表示成奇数和
        public boolean isSquare(int n) {
            int i = 1;
            while (n > 0) {
                n -= i;
                i += 2;
            }
            return n == 0;
        }
    }

    class Solution1 {
        public int numSquares(int n) {
            int[] dp = new int[n + 1]; // 默认初始化值都为0
            for (int i = 1; i <= n; i++) {
                dp[i] = i; // 最坏的情况就是每次+1
                for (int j = 1; i - j * j >= 0; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC279PerfectSquares().new Solution();
        Solution1 solution1 = new LC279PerfectSquares().new Solution1();
        System.out.println(solution.numSquares(13213));
        System.out.println(solution1.numSquares(13213));

    }

}
