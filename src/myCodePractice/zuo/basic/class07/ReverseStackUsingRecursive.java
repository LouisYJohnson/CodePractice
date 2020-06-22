package myCodePractice.zuo.basic.class07;

import java.util.Stack;

public class ReverseStackUsingRecursive {
    //给你一个栈， 请你逆序这个栈， 不能申请额外的数据结构， 只能
    //使用递归函数。 如何实现？

    //先写一个将栈低元素弹出的函数(左侧),再使用这个函数实现逆序栈的函数
    //将栈底元素弹出并返回的函数:
    public static int popStackBottom(Stack<Integer> stack) {
        int result = stack.pop();
        //base case
        if (stack.isEmpty()) return result;

        int res = popStackBottom(stack);
        stack.push(result);
        return res;
    }

    //逆序栈
    public static void reverseStack(Stack<Integer> stack) {
        //base case
        if (stack.isEmpty()) return;
        //取出为从传入栈的栈底到栈顶
        //在系统栈中为最底层的元素为原栈最顶层的元素
        int i = popStackBottom(stack);
        reverseStack(stack);
        //将原栈中的元素在系统栈中的排列(从内到外为原栈顶到底)将元素压回栈中
        stack.push(i);
    }
}
