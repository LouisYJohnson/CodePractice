package com.newcoder.zuo3.basic.class02_1;

import java.util.Stack;

public class Class_02_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //判断链表是否是回文结构

    //使用栈实现:
    public static boolean isPalindrome1(Node head) {
        Node nodeStart = head;
        Stack<Node> s = new Stack<Node>();
        //将列表中所有数据压栈
        while (head != null) {
            s.push(head);
            head = head.next;
        }
        //依次弹栈并与链表中数据比较
        while (nodeStart != null) {
            if (!(nodeStart.value == s.pop().value)) return false;
            nodeStart = nodeStart.next;
        }
        return true;
    }

    //仍然使用栈,但是使用1/2的额外空间实现
    //慢指针右边的入栈即可,弹出直到为空并比较每个弹出数据与链表中数据的大小
    //注意,我们一开始定义的结构就证明了不可能出现栈遍历完了链表中在中间指针前(包括中间指针)还有大于1个元素的空余
    //要么只剩中间指针,要么中间指针都不剩了!!!所以放心弹栈直到空为止
    public static boolean isPalindrome2(Node node) {
        if (node == null || node.next == null) return true;
        Stack<Node> s = new Stack<Node>();
        Node nodeSlow = node;
        Node nodeFast = node;
        //定义一个快指针,一个慢指针
        while (nodeFast.next != null && nodeFast.next.next != null) {
            nodeSlow = nodeSlow.next;
            nodeFast = nodeFast.next.next;
        }
        Node nodeMid = nodeSlow.next;
        //慢指针右边的入栈
        while (nodeMid != null) {
            s.push(nodeMid);
            nodeMid = nodeMid.next;
        }
        //从头遍历链表并弹栈直至栈空
        while (!s.isEmpty()) {
            if (node.value != s.pop().value) return false;
            node = node.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);


        System.out.print(isPalindrome2(head) + " | ");
    }

}

