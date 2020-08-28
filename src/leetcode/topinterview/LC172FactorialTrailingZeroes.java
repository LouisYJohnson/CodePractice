package leetcode.topinterview;

public class LC172FactorialTrailingZeroes {
    //给定一个整数 n，返回 n! 结果尾数中零的数量。
    //示例 1:
    //输入: 3
    //输出: 0
    //解释: 3! = 6, 尾数中没有零。
    //示例 2:
    //输入: 5
    //输出: 1
    //解释: 5! = 120, 尾数中有 1 个零.
    //说明: 你算法的时间复杂度应为 O(log n) 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int trailingZeroes(int n) {
            //https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/
            //本质是算5的因子个数,因为有0是因为有2*5,而且2的个数远多于5,所以只要计算5的个数就可以了
            //最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
            int count = 0;
            while (n > 0) {
                count += n / 5;
                n /= 5;
            }
            return count;
        }
    }
}
