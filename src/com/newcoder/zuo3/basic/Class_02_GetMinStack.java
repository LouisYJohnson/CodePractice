package com.newcoder.zuo3.basic;

import java.util.Stack;


public class Class_02_GetMinStack {
    //实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素的操作。
    //使用两个栈data,min,一个栈正常push与pop另外一个只push与pop当前栈中最小值
    //每次data入栈时,将数与min栈中栈顶数字比较,如果小于min栈顶数字,压入min,如果大于min重复压入min栈顶数字
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        //做题的时候,要有一个整体的思想,就是在设计的时候这两个栈是怎么对应的,那么在设计具体功能与内部判断的时候就要对应上
        //比如在push的时候,因为两个栈是相互联系的
        //(一个栈空了另外一个栈必空,而且一个栈中装正常值,另外一个栈中装最小值,而且两个栈大小相同)
        //所以在push中如果一个栈空了那另一个栈必为空所以这时候两个都push就是了
        //pop的时候,因为两个栈大小都一样,所以只要判断一个空了就可以抛出异常
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

