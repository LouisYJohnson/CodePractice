package exams.vipkid;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    //题目描述：
    //实现一个基本的计算器，计算输入的字符串表达式的结果，字符串表达式中包含+,-,*,/四种运算符号和大于0的整数，除法仅保留整数部分。
    //
    //
    //
    //输入描述
    //四则混合运算的字符串表达式
    //
    //输出描述
    //表达式的整数运算结果
    //
    //
    //样例输入
    //3+8*2-6/4
    //样例输出
    //18
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String compute = sc.next();
        char[] compute2Chars = compute.toCharArray();
        int res = process(compute2Chars);
        System.out.println(res);
    }

    public static int process(char[] compute) {
        char lastOp = '+';
        int res = 0;
        Stack<Integer> helpStack = new Stack<>();

        for (int i = 0; i < compute.length; i++) {
            if (Character.isDigit(compute[i])) {
                int curNum = 0;
                while (i < compute.length && Character.isDigit(compute[i])) {
                    curNum *= 10;
                    curNum += compute[i] - '0';
                    i++;
                }
                i--;
                if (lastOp == '+') {
                    helpStack.push(curNum);
                } else if (lastOp == '-') {
                    helpStack.push(-curNum);
                } else if (lastOp == '*') {
                    helpStack.push(helpStack.pop() * curNum);
                } else if (lastOp == '/') {
                    helpStack.push(helpStack.pop() / curNum);
                }
            }else {
                lastOp = compute[i];
            }
        }
        for (Integer integer : helpStack) {
            res += integer;
        }
        return res;
    }
}
