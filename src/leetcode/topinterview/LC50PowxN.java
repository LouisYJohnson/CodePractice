package leetcode.topinterview;

public class LC50PowxN {
    //实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    //示例 1:
    //输入: 2.00000, 10
    //输出: 1024.00000
    //示例 2:
    //输入: 2.10000, 3
    //输出: 9.26100
    //示例 3:
    //输入: 2.00000, -2
    //输出: 0.25000
    //解释: 2-2 = 1/22 = 1/4 = 0.25
    //说明:
    //-100.0 < x < 100.0
    //n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/powx-n
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public double myPow(double x, int n) {
            //https://leetcode-cn.com/problems/powx-n/solution/50-powx-n-kuai-su-mi-qing-xi-tu-jie-by-jyd/
            //快速幂法,本质就是将n拆分为二进制,二进制中有1的位置才让x乘对应的值,否则不乘
            //n统一按照正数处理,如果是负数,结果取倒数即可
            if (x == 0.0d) {
                return 0;
            }
            int flag = n > 0 ? 1 : 0;
            long b = n;
            b = Math.abs(b);
            //初始值就是n为0时的结果
            double res = 1.0d;
            while (b > 0) {
                if ((b & 1) == 1) { //如果这个位上有1,就和对应的次幂相乘
                    res *= x;
                }
                x *= x; //每次 次幂都要更新成最新的,以2的幂增长
                b = b >> 1;
            }
            return flag == 1 ? res : 1 / res;
        }
    }
}
