package swordoffer;

public class SW46ShuZiFanYiZiFuChuan {
    //给定一个数字，我们按照如下规则把它翻译为字符串：
    // 0 翻译成 “a” ，
    // 1 翻译成 “b”，……，
    // 11 翻译成 “l”，……，
    // 25 翻译成 “z”。
    // 一个数字可能有多个翻译。请编程实现一个函数，
    // 用来计算一个数字有多少种不同的翻译方法。
    //示例 1:
    //输入: 12258
    //输出: 5
    //解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
    //提示：
    //0 <= num < 231
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int translateNum(int num) {
            String num2Str = String.valueOf(num);
//            return process(num2Str, 0);
            return process1(num2Str);
        }

        //递归改动态规划:
        //递归函数中只有一个变量,i,从0到num2Str.length()
        //根据递归函数中的递推关系,发现每一个位置都依赖他右边一个或者两个的位置,所以从右到左开始填表
        public int process1(String num2Str) {
            int[] dp = new int[num2Str.length() + 1];
            dp[num2Str.length()] = 1;
            dp[num2Str.length() - 1] = 1;
            for (int i = num2Str.length() - 2; i >= 0; i--) {
                int res = 0;
                char curChar = num2Str.charAt(i);
                if (curChar == '0') {
                    res += dp[i + 1];
                } else if (curChar == '1') {
                    res += dp[i + 1]+ dp[i + 2];
                } else if (curChar == '2') {
                    if (i + 1 < num2Str.length() && num2Str.charAt(i + 1) <= '5') {
                        res += dp[i + 1] + dp[i + 2];
                    }else {
                        res += dp[i + 1];
                    }
                }else {
                    res += dp[i + 1];
                }
                dp[i] = res;
            }
            return dp[0];
        }

        //递归函数功能:
        //  给定字符串与当前位置,当前位置的还没有翻译完,返回当前位置以及后面的总共有几种翻译方法
        //必须要走到最后才能算是一种翻译方法,如果中间就+1的话,就是多种翻译方法了,abc就对应了三种
        public int process(String num2Str, int i) {
            if (i >= num2Str.length() - 1) {    //如果走到最后一个位置或者更后面,就算是一种翻译方式
                return 1;
            }
            int res = 0;
            char curChar = num2Str.charAt(i);
            //每次选择一个或者两个字符来进行翻译(根据当前字符是什么来决定选择一个还是两个)
            if (curChar == '0') {   //如果当前位置是0,那么只能自己和自己
                res += process(num2Str, i + 1);
            } else if (curChar == '1') {    //如果当前位置是1可以自己,也可以和后面的一起
                res += process(num2Str, i + 1);
                res += process(num2Str, i + 2);
            } else if (curChar == '2') {  //如果当前位置是2,只有后面是0到5的时候才可以一起,否则只能自己
                if (i + 1 < num2Str.length() && num2Str.charAt(i + 1) <= '5') {
                    res += process(num2Str, i + 2);
                    res += process(num2Str, i + 1);
                } else {
                    res += process(num2Str, i + 1);
                }
            } else { //如果是2以上,那只能自己了
                res += process(num2Str, i + 1);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new SW46ShuZiFanYiZiFuChuan().new Solution();
        System.out.println(solution.translateNum(25));;

    }
}
