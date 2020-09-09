package leetcode.topinterview;

public class LC371SumOfTwoIntegers {
    //不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
    //
    //示例 1:
    //
    //输入: a = 1, b = 2
    //输出: 3
    //示例 2:
    //
    //输入: a = -2, b = 3
    //输出: 1
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/sum-of-two-integers
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int getSum(int a, int b) {
            //使用位运算进行加和运算
            //进位信息:按位与
            //非进位信息:按位异或
            //分为进位信息和非进位信息
            //每次进位信息左移一位后和非进位信息进行下一次加和
            int forwardInfo = a & b;
            int unForwardInfo = a ^ b;
            int helpUnForwardInfo = unForwardInfo;

            while (forwardInfo != 0) {
                forwardInfo = forwardInfo << 1;

                unForwardInfo = forwardInfo ^ unForwardInfo;
                forwardInfo = forwardInfo & helpUnForwardInfo;
                helpUnForwardInfo = unForwardInfo;
            }
            return unForwardInfo;
        }
    }
}
