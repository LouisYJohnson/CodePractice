package leetcode.topinterview;

import java.util.Stack;

public class LC227BasicCalculatorII {
    //实现一个基本的计算器来计算一个简单的字符串表达式的值。
    //字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
    //示例 1:
    //输入: "3+2*2"
    //输出: 7
    //示例 2:
    //输入: " 3/2 "
    //输出: 1
    //示例 3:
    //输入: " 3+5 / 2 "
    //输出: 5
    //说明：
    //你可以假设所给定的表达式都是有效的。
    //请不要使用内置的库函数 eval。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/basic-calculator-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int calculate(String s) {
            //根据上一次遇到的符号判断入栈元素,然后将栈中元素相加即为结果
            //如果是加号'+'，说明前面的运算独立于以后的运算，可以将结果暂时放入栈；
            //如果是减号'-'，可以看成-1 * tempNum，然后将-tempNum入栈；
            //如果是乘号'*'或者除号'/'，由于前面的运算独立于此，可以先计算lastNum和tempNum积，然后结果入栈。
            //作者：chan-10
            //链接：https://leetcode-cn.com/problems/basic-calculator-ii/solution/li-yong-zhan-jiang-si-ze-yun-suan-hua-jian-cheng-j/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            Stack<Integer> helpStack = new Stack<>();
            char[] sWithNoSpace2Chars = s.toCharArray();
            //第一个有效操作符为'+',因为第一个数一定要直接入栈
            char lastOp = '+';
            for (int i = 0; i < sWithNoSpace2Chars.length; i++) {
                //如果当前位置是一个数字
                //不能使用trim去掉空格,trim只是去掉前面和后面的空格,中间有空格去不掉
                if (sWithNoSpace2Chars[i] == ' ') {
                    continue;
                } else if (Character.isDigit(sWithNoSpace2Chars[i])) {
                    int tempNum = 0;
                    while (i < sWithNoSpace2Chars.length && Character.isDigit(sWithNoSpace2Chars[i])) {
                        tempNum *= 10;
                        tempNum += sWithNoSpace2Chars[i] - '0';
                        i++;
                    }
                    i--;    //for循环中有i++了,因为i肯定是停在第一个不符合条件的位置
                    // 所以这里要i--才能到正确的位置
                    //然后看上一个有效操作符是什么
                    if (lastOp == '+') {
                        helpStack.push(tempNum);
                    } else if (lastOp == '-') {
                        helpStack.push(-tempNum);
                    } else if (lastOp == '*') {
                        helpStack.push(helpStack.pop() * tempNum);
                    } else if (lastOp == '/') {
                        helpStack.push(helpStack.pop() / tempNum);
                    }
                } else { //如果是符号
                    lastOp = sWithNoSpace2Chars[i];
                }
            }

            int res = 0;
            for (Integer integer : helpStack) {
                res += integer;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC227BasicCalculatorII().new Solution();
        solution.calculate(" 3 +5 / 2");

    }
}
