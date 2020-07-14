package leetcode.median;

import java.nio.charset.Charset;
import java.util.Stack;

public class LC129ValidParentheses {
    //给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
    //括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
    public boolean isValid (String s) {
        // write code here
        //使用栈来做,遇到左边的就压栈,遇到右边的就弹栈并比较弹栈的和当前的右边的是否相同
        //如果不同直接返回false
        //如果弹光了右边还有,false,否则,true
        //如果左边没弹光,右边就没了,false
        if (s == null || s.length() == 0) return false;

        char[] s2Chars = s.toCharArray();
        Stack<Character> helpStack = new Stack<>();
        for (int i = 0; i < s2Chars.length; i++) {
            if (s2Chars[i] == '(' || s2Chars[i] == '[' || s2Chars[i] == '{') {
                helpStack.push(s2Chars[i]);
            }else if (!helpStack.isEmpty() &&
                    (
                    (s2Chars[i] == ')' && helpStack.peek() == '(')
                    ||
                    (s2Chars[i] == ']' && helpStack.peek() == '[')
                    ||
                    (s2Chars[i] == '}' && helpStack.peek() == '{'))
                    ) {
                helpStack.pop();
            }else {
                return false;
            }
        }
        return helpStack.isEmpty();
    }

    public static void main(String[] args) {
        LC129ValidParentheses lc129ValidParentheses = new LC129ValidParentheses();
        boolean res = lc129ValidParentheses.isValid("]");
        System.out.println(1);
    }
}
