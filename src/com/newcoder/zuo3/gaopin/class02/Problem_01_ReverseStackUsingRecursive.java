package com.newcoder.zuo3.gaopin.class02;

import java.util.Stack;

public class Problem_01_ReverseStackUsingRecursive {
    //1��ջ����ѹ��1��2��3��4��5,��ô��ջ����ջ�׷ֱ�Ϊ5��4��
    //3��2��1�������ջת�ú󣬴�ջ����ջ��Ϊ1��2��3��4��5��
    //Ҳ����ʵ��ջ��Ԫ�ص����򣬵���ֻ���õݹ麯����ʵ�֣���
    //�����������ݽṹ��

    //��2������Ҫ��ϵͳ�к���ջ�ĽǶ������,�������۶��幦�ܵĽǶ�
    //��������:����ջ�е���ײ�Ԫ�ز�����
    public static int popAndReturnStackBottom(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) return result;

        int temp = popAndReturnStackBottom(stack);
        stack.push(result);
        return temp;
    }

    //���ܺ���:����ջ
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        //����ջ����ײ�Ԫ�ز�����,��Ϊ�˲���
        int temp = popAndReturnStackBottom(stack);
        //��һ������
        reverseStack(stack);
        //�ڵ���Ͳ��,һ��һ��Ľ��ò���ѹ��ջ��
        stack.push(temp);
    }

    //for Combinations
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
