package myCodePractice.zuo.advance.class01;

import java.util.LinkedList;
import java.util.Queue;

public class BSTtoDoubleLinkedList {
    //把一棵搜索二叉树， 转化成有序的双向链表
    //套路:用递归做二叉树题目:
    //      传入头节点,左子树返回信息,右子树返回信息,将这两个信息整合,就是父过程需要的信息
    //      base case就是遇到了叶节点或者null该怎么办,搞定了这个就搞定了这个套路
    //递归方法去做
    //递归函数功能:输入一个二叉树的头节点,返回二叉树的变成双向链表的形式并返回头节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node convert1(Node head) {
        if (head == null) return null;

        return process(head);
    }

    //递归函数功能:传入头节点,返回二叉树变成双向列表形式的头节点
    public static Node process(Node head) {
        //base case
        if (head == null) {
            return null;
        }

        Node left = process(head.left);
        Node right = process(head.right);
        //因为left,right是两个二叉树转化成双向链表后的仍然是原来的头节点
        //有可能是在双向链表中间的,所以左边的要挪到最右边,右边的要挪到最左边然后和当前的head相连
        //整合好这个信息后,就是父过程需要的信息

        //走到叶节点的时候,左右返回的都是null,就不用找最左最右了
        if (left != null) {
            while (left.right != null) {
                left = left.right;
            }
        }
        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }
        }
        head.left = left;
        head.right = right;

        return head;
    }

    //for test
    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }

    public static Node convert2(Node head) {
        Queue<Node> queue = new LinkedList<Node>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert1(head);
        Node help = head;
        while (head.left != null) {
            head = head.left;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.right;
        }
        System.out.println();
        head = help;
        while (head.right != null) {
            head = head.right;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.left;
        }
        System.out.println();
        System.out.println();

        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);
//        Node head = new Node(2);
//        head.left = new Node(1);
//        head.right = new Node(3);
        printBSTInOrder(head);
        head = convert1(head);
        help = head;
        while (head.left != null) {
            head = head.left;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.right;
        }
        System.out.println();
        head = help;
        while (head.right != null) {
            head = head.right;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.left;
        }
//        printDoubleLinkedList(head);
//
//        head = new Node(5);
//        head.left = new Node(2);
//        head.right = new Node(9);
//        head.left.left = new Node(1);
//        head.left.right = new Node(3);
//        head.left.right.right = new Node(4);
//        head.right.left = new Node(7);
//        head.right.right = new Node(10);
//        head.left.left = new Node(1);
//        head.right.left.left = new Node(6);
//        head.right.left.right = new Node(8);
//
//        printBSTInOrder(head);
//        head = convert2(head);
//        printDoubleLinkedList(head);

    }
}
