package com.newcoder.zuo3.basic;

import java.util.HashMap;

public class Class_02_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }
    //深度拷贝
    //使用简单方法,加入辅助HashMap
    //思考:为什么使用HashMap而不是直接遍历,因为我们不知道有多少个节点,在不知道有几个节点的前提下,如果直接一步一步遍历的话
    //会创建多余节点,或者rand不知道指向谁
    //遍历两次,第一次拿到所有节点,第二次将这些指针空置的节点按照原结构进行连接
    public static Node copyListWithRand1(Node head) {
        HashMap<Node,Node> hm = new HashMap<Node,Node>();
        Node helpHead = head;
        //1
        while (helpHead != null) {
            hm.put(helpHead,new Node(helpHead.value));
            helpHead = helpHead.next;
        }
        helpHead = head;
        //2
        while (helpHead != null) {
            //hm.get(key)得到的是value
            //value就是被复制的节点
            hm.get(helpHead).next = hm.get(helpHead.next);
            hm.get(helpHead).rand = hm.get(helpHead.rand);
            helpHead = helpHead.next;
        }
        return hm.get(head);
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
//        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
