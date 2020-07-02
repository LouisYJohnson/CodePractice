package com.newcoder.zuo3.advanced.class05;

import java.util.Stack;

public class Code_01_Valid_Expression {
    //爱编程的小易发现， 当自己代码中的括号较多时， 如果括号未
    //成对出现， 或者出现的顺序错误， 他的编辑器 总是能立马给出
    //错误提示。 好奇的小易决定自己尝试实现该功能。 对于一行代
    //码(字符串)， 里面可能出现大括号"{}"、 中括号"[]"和小括号
    //"()"， 请编程判断该行代码的括号嵌 套是否正确。
    //"()","({})","print ('Hello Netease')"等都是括号的正确使
    //用方法， "(]","print (Hello Netease]"则是错误的范例

    //遇到左括号就压栈,遇到右括号就弹栈,看弹出来的和右括号能不能配上,任何一步配不上false
    public static boolean isValid(String expression) {
        if (expression == null || expression.length() == 0) return true;
        char[] chars = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '(' || chars[i] == '[') {
                stack.push(chars[i]);
            } else if (!stack.isEmpty() && chars[i] == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (!stack.isEmpty() && chars[i] == '}' && stack.peek() == '{') {
                stack.pop();
            } else if (!stack.isEmpty() && chars[i] == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (stack.isEmpty() && chars[i] == '}' || chars[i] == ')' || chars[i] == ']') {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test = "{1+(2+3)+[(1+3)+(4*5)]}";
        String test1 = "{1}+())]";
        System.out.println(isValid(test));
    }

}
