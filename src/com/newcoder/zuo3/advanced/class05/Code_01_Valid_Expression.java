package com.newcoder.zuo3.advanced.class05;

import java.util.Stack;

public class Code_01_Valid_Expression {
    //����̵�С�׷��֣� ���Լ������е����Ž϶�ʱ�� �������δ
    //�ɶԳ��֣� ���߳��ֵ�˳����� ���ı༭�� �������������
    //������ʾ�� �����С�׾����Լ�����ʵ�ָù��ܡ� ����һ�д�
    //��(�ַ���)�� ������ܳ��ִ�����"{}"�� ������"[]"��С����
    //"()"�� �����жϸ��д��������Ƕ ���Ƿ���ȷ��
    //"()","({})","print ('Hello Netease')"�ȶ������ŵ���ȷʹ
    //�÷����� "(]","print (Hello Netease]"���Ǵ���ķ���

    //˼·:
    //���������ž�ѹջ,���������ž͵�ջ,���������ĺ��������ܲ�������,�κ�һ���䲻��false
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
