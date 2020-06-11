package com.newcoder.zuo3.advanced.class01;

import java.util.LinkedList;
import java.util.Queue;

public class Code_04_BSTtoDoubleLinkedList {
    //把一棵搜索二叉树， 转化成有序的双向链表。
    //二叉树问题使用递归来做
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //方法1:
    public static Node convert1(Node head) {
        if (head == null) return null;
        return process1(head);
    }

    //递归函数:功能:给一个二叉树的头节点,返回这个二叉树变成双向链表后的头节点
    //确定递归函数的功能(这个部分是主观的,但是这个主观的行为如果递归函数的后续部分还按照这个主观的函数的功能的返回值继续做,那么这个递归就是对的),
    // 函数内部就认定了这个功能就返回这个返回值,后面的部分都按照这个返回值去编写,就一定是对的!
    public static Node process1(Node head) {
        //base case
        if (head == null) return null;
        //分别找到当前头节点的左子树的左头节点与右头节点
        Node leftHead = process1(head.left);
        Node rightHead = process1(head.right);
//确定递归函数的功能,函数内部就认定了这个功能就返回这个返回值,后面的部分都按照这个返回值去编写,就一定是对的!
        if (leftHead != null) {
            while (leftHead.right != null) {
                leftHead = leftHead.right;
            }
        }
        if (rightHead != null) {
            while (rightHead.left != null) {
                rightHead = rightHead.left;
            }
        }
        head.left = null;
        head.right = null;
        if (leftHead != null) {
            leftHead.right = head;
            head.left = leftHead;
        }
        if (rightHead != null) {
            rightHead.left = head;
            head.right = rightHead;
        }
        return head;
    }

    //方法2
    //刚才的方法找到中间点后还要往左右走,有一个遍历的过程,可以把这个遍历的过程省去
    //具体做法就是,每次都返回一个数组,里面有传入头节点对应的变成双向链表的最左节点和最右节点
    //套路:使用递归做二叉树的时候,左子树有信息右子树有信息,但是一边只要左一边只要右,这时候必须把左右子树的全部信息返回
    public static Node convert3(Node head) {
        if (head == null) return null;
        return process2(head)[0];
    }
    //这个递归函数,返回头节点对应的最左节点和最右节点
//    Node[0]:最左
//    Node[1]:最右
    public static Node[] process2(Node head) {
        //base case
        if (head == null) return new Node[] {null,null};
        //leftSub中有head的左子树的最左节点和最右节点
        Node[] leftSub = process2(head.left);
        Node[] rightSub = process2(head.right);
        head.left = null;
        head.right = null;
// 函数内部就认定了这个功能就返回这个返回值,后面的部分都按照这个返回值去编写,就一定是对的!
        if (leftSub[1] != null) {
            leftSub[1].right = head;
            head.left = leftSub[1];
        }

        if (rightSub[0] != null) {
            rightSub[0].left = head;
            head.right = rightSub[0];
        }
        Node left = leftSub[0] == null ? head : leftSub[0];
        Node right = rightSub[1] == null ? head : rightSub[1];
        return new Node[] {left,right};
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
