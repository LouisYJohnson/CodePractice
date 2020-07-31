package leetcode.top100interview;

public class LC7ReverseInteger {
    //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    //示例 1:
    //输入: 123
    //输出: 321
    // 示例 2:
    //输入: -123
    //输出: -321
    //示例 3:
    //输入: 120
    //输出: 21
    //注意:
    //假设我们的环境只能存储得下 32 位的有符号整数，
    // 则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/reverse-integer
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int reverse(int x) {
            //-32768~32767
            //思路就是将x的尾部数拿出来,放到结果数的后面
            //具体实现就是
            //pop operation:
            //pop = x % 10;
            //x /= 10;
            //push operation:
            //temp = rev * 10 + pop;
            //rev = temp;
            //但是这个方法危险的地方在于push的时候会出现越界的情况
            //而这个越界情况是可以通过检测来进行处理的
            //如果x是正数:
            //  如果temp=rev*10+pop导致溢出的话,那么一定有rev>=IntMax/10
            //  如果rev>IntMax/10则一定溢出
            //  如果rev==IntMax/10则对于正数时pop<=7不溢出否则溢出
            //如果x是负数:
            //  如果temp=rev*10+pop导致溢出的话,那么一定有rev<=IntMin/10
            //  如果rev<IntMin/10则一定溢出
            //  如果rev==IntMin/10则对于负数时pop>=-8不会溢出,否则溢出
            int pop = 0;
            int push = 0;
            int res = 0;

            while (x != 0) {
                pop = x % 10;
                x /= 10;

                //正数
                if ((res > Integer.MAX_VALUE / 10) || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
                //负数
                if ((res < Integer.MIN_VALUE / 10) || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
                res = res * 10 + pop;
            }
            return res;
        }
    }
}
