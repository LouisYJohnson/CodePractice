package com.newcoder.zuo3.gaopin.class02;

import java.util.Stack;

public class Problem_01_ReverseStackUsingRecursive {
    //1个栈依次压入1、2、3、4、5,那么从栈顶到栈底分别为5、4、
    //3、2、1。将这个栈转置后，从栈顶到栈底为1、2、3、4、5，
    //也就是实现栈中元素的逆序，但是只能用递归函数来实现，不
    //能用其他数据结构。

    //这2个函数要用系统中函数栈的角度来理解,不是主观定义功能的角度
    //基础函数:弹出栈中的最底层元素并返回
    public static int popAndReturnStackBottom (Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) return result;

        int temp = popAndReturnStackBottom(stack);
        stack.push(result);
        return temp;
    }

    //功能函数:逆序栈
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        //弹出栈中最底层元素并返回,作为此层结果
        int temp = popAndReturnStackBottom(stack);
        //下一层的入口
        reverseStack(stack);
        //在到最低层后,一层一层的将该层结果压入栈中
        stack.push(temp);
    }

    //for test
    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverseStack(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
