package myCodePractice.zuo.advance.class05;

import java.util.Stack;

public class ValidExpression {
    //爱编程的小易发现， 当自己代码中的括号较多时， 如果括号未
    //成对出现， 或者出现的顺序错误， 他的编辑器 总是能立马给出
    //错误提示。 好奇的小易决定自己尝试实现该功能。 对于一行代
    //码(字符串)， 里面可能出现大括号"{}"、 中括号"[]"和小括号
    //"()"， 请编程判断该行代码的括号嵌 套是否正确。
    //"()","({})","print ('Hello Netease')"等都是括号的正确使
    //用方法， "(]","print (Hello Netease]"则是错误的范例

    //遇到左括号就压栈,遇到右括号就弹栈,看弹出来的和右括号能不能配上,任何一步配不上false
    public static boolean isValid(String expression) {
        if (expression == null || expression.length() == 0) return false;

        char[] expression2Char = expression.toCharArray();
        Stack<Character> helpStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression2Char[i] == '(' || expression2Char[i] == '[' || expression2Char[i] == '{') {
                helpStack.push(expression2Char[i]);
            }else if (expression2Char[i] == ')') {
                if (!helpStack.isEmpty() && helpStack.peek() == '(') {
                    helpStack.pop();
                }else {
                    return false;
                }
            }else if (expression2Char[i] == ']') {
                if (!helpStack.isEmpty() && helpStack.peek() == '[') {
                    helpStack.pop();
                }else {
                    return false;
                }
            }else if (expression2Char[i] == '}') {
                if (!helpStack.isEmpty() && helpStack.peek() == '{') {
                    helpStack.pop();
                }else {
                    return false;
                }
            }
        }
        return helpStack.isEmpty();
    }

    public static void main(String[] args) {
        String test = "{1+(2+3)+[(1+3)+(4*5)]}";
        String test1 = "{1}+())]";
        System.out.println(isValid(test1));
    }
}
