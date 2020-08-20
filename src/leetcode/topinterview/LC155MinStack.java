package leetcode.topinterview;

import java.util.Stack;

public class LC155MinStack {
    //设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    //push(x) —— 将元素 x 推入栈中。
    //pop() —— 删除栈顶的元素。
    //top() —— 获取栈顶元素。
    //getMin() —— 检索栈中的最小元素。
    //示例:
    //输入：
    //["MinStack","push","push","push","getMin","pop","top","getMin"]
    //[[],[-2],[0],[-3],[],[],[],[]]
    //输出：
    //[null,null,null,null,-3,null,0,-2]
    //解释：
    //MinStack minStack = new MinStack();
    //minStack.push(-2);
    //minStack.push(0);
    //minStack.push(-3);
    //minStack.getMin();   --> 返回 -3.
    //minStack.pop();
    //minStack.top();      --> 返回 0.
    //minStack.getMin();   --> 返回 -2.
    //提示：
    //pop、top 和 getMin 操作总是在 非空栈 上调用。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/min-stack
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class MinStack {
        //设计两个栈,一个栈装当前数,另外一个栈装最小值
        //push:每次向里面压栈的时候,如果当前压栈元素比栈顶元素大,则一直重复压栈顶元素
        //  如果当前压栈元素比栈顶元素小,则压这个小的元素
        //pop的时候二者一起pop,但是只返回正常栈顶元素
        //getMin得到min栈栈顶元素即可
        private Stack<Integer> stack;
        private Stack<Integer> helpStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            this.stack = new Stack<>();
            this.helpStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (!helpStack.isEmpty() && helpStack.peek() > x) {
                helpStack.push(x);
            } else if (!helpStack.isEmpty() && helpStack.peek() <= x) {
                helpStack.push(helpStack.peek());
            }else {
                helpStack.push(x);
            }
        }

        public void pop() {
            helpStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return helpStack.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
