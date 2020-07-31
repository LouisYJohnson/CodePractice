package leetcode.top100interview;

import java.util.Stack;

public class LC20ValidParentheses {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //有效字符串需满足：
    //左括号必须用相同类型的右括号闭合。
    //左括号必须以正确的顺序闭合。
    //注意空字符串可被认为是有效字符串。
    //示例 1:
    //输入: "()"
    //输出: true
    //示例 2:
    //输入: "()[]{}"
    //输出: true
    //示例 3:
    //输入: "(]"
    //输出: false
    //示例 4:
    //输入: "([)]"
    //输出: false
    //示例 5:
    //输入: "{[]}"
    //输出: true
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/valid-parentheses
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean isValid(String s) {
            //思路:构建一个栈,遇到左边的括号就压栈,遇到右边的括号就弹栈,如果对应不上,就false,如果所有字符都走完了栈中还有剩的,false
            //最后才true
            char[] s2Chars = s.toCharArray();
            Stack<Character> helpStack = new Stack<>();

            for (int i = 0; i < s2Chars.length; i++) {
                if (s2Chars[i] == '('
                        || s2Chars[i] == '['
                        || s2Chars[i] == '{') {
                    helpStack.push(s2Chars[i]);
                } else {
                    if (!helpStack.isEmpty()) {
                        if ((s2Chars[i] == ')' && helpStack.peek() == '(')
                                || (s2Chars[i] == ']' && helpStack.peek() == '[')
                                || (s2Chars[i] == '}' && helpStack.peek() == '{')) {
                            helpStack.pop();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            return helpStack.isEmpty();
        }
    }
}
