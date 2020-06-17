package myCodePractice.zuo.basic.class07;

import java.util.Stack;

public class ReverseStackUsingRecursive {
    //����һ��ջ�� �����������ջ�� ���������������ݽṹ�� ֻ��
    //ʹ�õݹ麯���� ���ʵ�֣�

    //��дһ����ջ��Ԫ�ص����ĺ���(���),��ʹ���������ʵ������ջ�ĺ���
    //��ջ��Ԫ�ص��������صĺ���:
    public static int popStackBottom(Stack<Integer> stack) {
        int result = stack.pop();
        //base case
        if (stack.isEmpty()) return result;

        int res = popStackBottom(stack);
        stack.push(result);
        return res;
    }

    //����ջ
    public static void reverseStack(Stack<Integer> stack) {
        //base case
        if (stack.isEmpty()) return;
        //ȡ��Ϊ�Ӵ���ջ��ջ�׵�ջ��
        //��ϵͳջ��Ϊ��ײ��Ԫ��Ϊԭջ����Ԫ��
        int i = popStackBottom(stack);
        reverseStack(stack);
        //��ԭջ�е�Ԫ����ϵͳջ�е�����(���ڵ���Ϊԭջ������)��Ԫ��ѹ��ջ��
        stack.push(i);
    }
}
