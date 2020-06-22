package com.newcoder.zuo3.basic.class02_1;

public class Class_02_Array_To_Stack_Queue {
    //用数组实现大小固定的队列和栈
    //以实现对应的类来作为结果
    public static class ArrayStack {
        private int stackSize;
        private int[] arr;
        private int index = 0;

        public ArrayStack(int stackSize) {
            if (stackSize < 0) throw new IllegalArgumentException("The init size is less than 0");
            this.stackSize = stackSize;
            this.arr = new int[stackSize];
        }

        public void push(int num) {
            if (index < stackSize) {
                arr[index++] = num;
            } else {
                throw new ArrayIndexOutOfBoundsException("stack is full");
            }
        }

        public int pop() {
            if (index > 0) {
                return arr[--index];
            } else {
                throw new ArrayIndexOutOfBoundsException("stack is empty");
            }
        }

        public int peek() {
            if (index > 0) {
                return arr[index - 1];
            } else throw new ArrayIndexOutOfBoundsException("stack is empty");
        }

    }

    //队列,功能:poll,push,peek
    //start,end分别代表要出队的数在哪里,和要入队的数应该放在哪里
    public static class ArrayQueue {
        private int start = 0;
        private int end = 0;
        private int size;
        private int queueSize = 0;
        private int[] arr;

        public ArrayQueue(int queueSize) {
            if (queueSize < 0) throw new IllegalArgumentException("The init size is less than 0");
            this.queueSize = queueSize;
            this.arr = new int[queueSize];
        }

        //push
        public void push(int num) {
            if (size >= queueSize) throw new IndexOutOfBoundsException("queue is full");
            arr[end++] = num;
            size++;
            end = end > queueSize - 1 ? 0 : end;
        }

        //poll
        public int poll() {
            if (size <= 0) throw new IndexOutOfBoundsException("queue is empty");
            int temp = arr[start++];
            start = start > queueSize - 1 ? 0 : start;
            size--;
            return temp;
        }

        //peek
        public int peek() {
            if (size < 1) throw new IndexOutOfBoundsException("queue is empty");
            return start - 1 < 0 ? arr[queueSize - 1] : arr[start - 1];
        }
    }

    public static void main(String[] args) {
        int num = 3;
//		ArrayStack as = new ArrayStack(num);
//		as.push(1);
//		as.push(2);
//		as.push(3);
//		as.pop();
//		as.pop();
////		as.pop();
//		System.out.println(as.peek());
        ArrayQueue aq = new ArrayQueue(num);
        aq.push(11);
        aq.push(11);
        aq.push(11);
        aq.poll();
        aq.poll();
        System.out.println(aq.peek());
        aq.poll();
        System.out.println(aq.peek());

    }

}
