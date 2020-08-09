package leetcode.topinterview;

public class LC8StringToIntegerAtoi {
    //请你来实现一个 atoi 函数，使其能将字符串转换成整数。
    //首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
    //如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
    //假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
    //该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
    //注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
    //在任何情况下，若函数不能进行有效的转换时，请返回 0 。
    //提示：
    //本题中的空白字符只包括空格字符 ' ' 。
    //假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
    //示例 1:
    //输入: "42"
    //输出: 42
    //示例 2:
    //输入: "   -42"
    //输出: -42
    //解释: 第一个非空白字符为 '-', 它是一个负号。
    //     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
    //示例 3:
    //输入: "4193 with words"
    //输出: 4193
    //解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
    //示例 4:
    //输入: "words and 987"
    //输出: 0
    //解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
    //     因此无法执行有效的转换。
    //示例 5:
    //输入: "-91283472332"
    //输出: -2147483648
    //解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
    //     因此返回 INT_MIN (−231) 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/string-to-integer-atoi
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int myAtoi(String str) {
            //不管是正数还是负数,都按照负数来转,因为负数的最小值的绝对值比正数大1
            //如果按照正数来转,负数的最小值在转的时候就会溢出
            //首先判断字符串是否有效
            if (str == null || str.length() == 0) {
                return 0;
            }
            int firstNotSpace = 0;
            for (firstNotSpace = 0; firstNotSpace < str.length(); firstNotSpace++) {
                if (str.charAt(firstNotSpace) == ' ') {
                    continue;
                }else {
                    break;
                }
            }
            //拿到第一个不是空格的字符的位置,如果这个位置是字符的结尾,直接0
            if (firstNotSpace == str.length()) {
                return 0;
            }
            char firstNotSpaceChar = str.charAt(firstNotSpace);
            //如果第一个位置不是+,-,数字,直接0
            if (firstNotSpaceChar != '-' && firstNotSpaceChar != '+' && (firstNotSpaceChar < '0' || firstNotSpaceChar > '9')) {
                return 0;
            }
            //否则,找到数字的结尾
            int startIndex = firstNotSpaceChar == '-' || firstNotSpaceChar == '+' ? firstNotSpace + 1 : firstNotSpace;
            int endIndex = startIndex;
            while (endIndex < str.length() && (str.charAt(endIndex) >= '0' && str.charAt(endIndex) <= '9')) {
                endIndex++;
            }
            //endIndex为第一个不符合的位置
            int flag = firstNotSpaceChar == '-' ? 1 : 0;
            //flag为符号位,1表示负数,0表示正数
            char[] number = str.substring(startIndex, endIndex).toCharArray();
            if (number.length == 0) {
                return 0;
            }
            //先把最高位放进去
            int res = -(number[0] - '0');
            for (int i = 1; i < number.length; i++) {
                //比最小值/10还要小,下一位不用加肯定超了
                if (res < Integer.MIN_VALUE / 10) {
                    return flag == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                } else if (res == Integer.MIN_VALUE / 10) { //正好是最小值/10,那就要看是正还是负
                    //如果是正数,就必须小于等于7(因为负数最小值的个位为8,正数最大值个位为7)
                    //如果是负数,就必须小于等于8
                    //如果不满足,直接返回对应的值即可
                    if (flag == 0 && number[i] - '0' > 7) {
                        return Integer.MAX_VALUE;
                    } else if (flag == 1 && number[i] - '0' > 8) {
                        return Integer.MIN_VALUE;
                    }
                }
                res = res * 10 - (number[i] - '0');
            }
            //负数都没越界,正数更不能越界了
            return flag == 1 ? res : -res;
        }
    }

    public static void main(String[] args) {
        String test = "4193 with words";
        Solution solution = new LC8StringToIntegerAtoi().new Solution();
        int res = solution.myAtoi(test);
        System.out.println(res);
    }
}
