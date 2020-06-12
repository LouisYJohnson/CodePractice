package com.newcoder.zuo3.basic;

import java.util.Stack;


public class Class_02_GetMinStack {
    //ʵ��һ�������ջ�� ��ʵ��ջ�Ļ������ܵĻ����ϣ� ��ʵ�ַ���ջ����СԪ�صĲ�����
    //ʹ������ջdata,min,һ��ջ����push��pop����һ��ֻpush��pop��ǰջ����Сֵ
    //ÿ��data��ջʱ,������minջ��ջ�����ֱȽ�,���С��minջ������,ѹ��min,�������min�ظ�ѹ��minջ������
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        //�����ʱ��,Ҫ��һ�������˼��,��������Ƶ�ʱ��������ջ����ô��Ӧ��,��ô����ƾ��幦�����ڲ��жϵ�ʱ���Ҫ��Ӧ��
        //������push��ʱ��,��Ϊ����ջ���໥��ϵ��
        //(һ��ջ��������һ��ջ�ؿ�,����һ��ջ��װ����ֵ,����һ��ջ��װ��Сֵ,��������ջ��С��ͬ)
        //������push�����һ��ջ��������һ��ջ��Ϊ��������ʱ��������push������
        //pop��ʱ��,��Ϊ����ջ��С��һ��,����ֻҪ�ж�һ�����˾Ϳ����׳��쳣
        //
        public void push(Integer num) {
            if (stackData.isEmpty()) {
                stackData.push(num);
                stackMin.push(num);
            } else if (stackMin.peek() > num) {
                stackData.push(num);
                stackMin.push(num);
            } else {
                stackMin.push(stackMin.peek());
                stackData.push(num);
            }
        }

        public Integer pop() throws Exception {
            if (stackData.isEmpty()) throw new Exception("stack is empty!");
            stackMin.pop();
            return stackData.pop();
        }

        public Integer peekMin() throws Exception {
            if (stackData.isEmpty()) throw new Exception("stack is empty!");
            return stackMin.peek();
        }

        public Integer peek() throws Exception {
            if (stackData.isEmpty()) throw new Exception("stack is empty!");
            return stackData.peek();
        }
    }

    public static void main(String[] args) throws Exception {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.peekMin());
        stack1.push(4);
        System.out.println(stack1.peekMin());
        stack1.push(1);
        System.out.println(stack1.peekMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.peekMin());

//		System.out.println("=============");
//
//		MyStack1 stack2 = new MyStack1();
//		stack2.push(3);
//		System.out.println(stack2.getmin());
//		stack2.push(4);
//		System.out.println(stack2.getmin());
//		stack2.push(1);
//		System.out.println(stack2.getmin());
//		System.out.println(stack2.pop());
//		System.out.println(stack2.getmin());
    }
}

