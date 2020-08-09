package leetcode.topinterview;

public class LC91DecodeWays {
    //一条包含字母 A-Z 的消息通过以下方式进行了编码：
    //'A' -> 1
    //'B' -> 2
    //...
    //'Z' -> 26
    //给定一个只包含数字的非空字符串，请计算解码方法的总数。
    //示例 1:
    //输入: "12"
    //输出: 2
    //解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
    //示例 2:
    //输入: "226"
    //输出: 3
    //解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/decode-ways
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/decode-ways/solution/jie-ma-fang-fa-dong-tai-gui-hua-by-samwu/
        public int numDecodings(String s) {
            //递归
            //首先要检查开头是否为0,如果为0,直接返回0
            if (s == null || s.length() == 0 || s.charAt(0) == '0') {
                return 0;
            }

            char[] s2Chars = s.toCharArray();
            return process(s2Chars, s2Chars.length - 1);
        }

        //递归函数功能:
        //返回必须以i结尾总共有多少个组合个数
        public int process(char[] chars, int i) {
            //base case
            if (i == 0) {
                return 1;
            }

            int num = chars[i] - '0';
            int numPre = chars[i - 1] - '0';
            if (num == 0 && (numPre == 1 || numPre == 2)) {
                if (i == 1) {   //当前在位置1,并且前面的数还不是0,当前的数还是0,那么只能和前面组合了,没别的办法
                    return 1;
                }else {     //当前不在位置1,当前位置数为0,前面数不是0,那么只能和前面的组合,并让位置i向前挪两个位置
                    return process(chars, i - 2);
                }
            }else if (num == 0 && (numPre == 0 || numPre > 2)){
                return 0;   //当前位置是0,但是前面的位置是0或者大于2,不能编码
            } else if (((num <= 6) && (numPre == 2)) ||
                    ((numPre == 1) && (num != 0))) {  //组合字符为21-26或11-19,可以单独编,也可以一起编
                if (i == 1) {   //如果当前在位置1,那么就两种编码数
                    return 2;
                }else { //在1后面的位置,可以组合一个,也可以组合两个
                    return process(chars, i - 1) + process(chars, i - 2);
                }
            }else { //余下的情况,只能自己组了
                return process(chars, i - 1);
            }
        }


    }
}
