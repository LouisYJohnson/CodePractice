package leetcode.topinterview;

import myCodePractice.zuo.advance.class04.AddMinusMultiDivideByBit;

public class LC29DivideTwoIntegers {
    //给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
    //返回被除数 dividend 除以除数 divisor 得到的商。
    //整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
    //示例 1:
    //
    //输入: dividend = 10, divisor = 3
    //输出: 3
    //解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
    //示例 2:
    //
    //输入: dividend = 7, divisor = -3
    //输出: -2
    //解释: 7/-3 = truncate(-2.33333..) = -2
    //提示：
    //
    //被除数和除数均为 32 位有符号整数。
    //除数不为 0。
    //假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/divide-two-integers
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link AddMinusMultiDivideByBit}
     */
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            boolean k = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
            if (Math.abs(divisor) == 1) {
                if (k) {
                    return Math.abs(dividend);
                } else {
                    return -Math.abs(dividend);
                }
            }
            int result = 0;
            dividend = -Math.abs(dividend);
            divisor = -Math.abs(divisor);
            while (dividend <= divisor) {
                int temp = divisor;
                int c = 1;
                //核心就在于这个while中的判断条件
                while (dividend - temp <= temp) {
                    temp = temp << 1;
                    c = c << 1;
                }

                dividend -= temp;
                result += c;
            }
            return k ? result : -result;
        }

        //分别实现加减乘除(位运算)
        //+
        public int add(int a, int b) {
            int res = 0;
            int forwardInfo = a & b;    //进位信息
            int unForwardInfo = a ^ b;  //非进位信息
            int helpUnForwardInfo = forwardInfo;    //因为unForwardInfo是不需要移位的,所以最好对这个数字做备份

            //进位信息不为0,则将进位信息左右一位后将其与非进位信息进行相加
            while (forwardInfo != 0) {
                forwardInfo = forwardInfo << 1;

                unForwardInfo = forwardInfo ^ unForwardInfo;    //此时unForwardInfo已经变了
                forwardInfo = forwardInfo & helpUnForwardInfo;
                helpUnForwardInfo = unForwardInfo;
            }
            return unForwardInfo;
        }

        //-
        public int minus(int a, int b) {
            //a + (-b)
            //相反数:按位取反+1

            return add(a, getOppositeNumber(b));
        }

        //*
        public int multi(int a, int b) {
            //两个数中选一个数
            //这个数每次向右移一位(无符号右移)看最右边那一位是否有1,如果有,就让另外一个数左移对应的位数
            //将所有左移的数字相加,即为结果
            int res = 0;
            int leftMoveSteps = 0;

            while (b != 0) {
                if ((b & 1) == 1) {
                    res = add(res, a << leftMoveSteps);
                }
                b = b >>> 1;
                leftMoveSteps++;
            }
            return res;
        }

        // /
        public int div(int a, int b) {
            //https://leetcode-cn.com/problems/divide-two-integers/solution/yong-fu-shu-yun-suan-jian-hua-bian-jie-chu-li-by-g/
            return 0;
        }




        //相反数
        public int getOppositeNumber(int num) {
            num = ~num;
            num = add(num, 1);
            return num;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC29DivideTwoIntegers().new Solution();
        System.out.println(solution.add(1, 2));
        System.out.println(solution.getOppositeNumber(1));
        System.out.println(solution.minus(1, 2));
        System.out.println(solution.multi(2, 4));
        System.out.println(solution.divide(Integer.MAX_VALUE, 1));
        System.out.println(solution.divide(-1, -1));
    }
}
