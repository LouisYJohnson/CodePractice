package com.newcoder.zuo3.advanced.class01;

public class Code_03_RelocateLinkedList {
    //给定一个链表list，
    //如果：
    //list = 1 调整之后1。
    //list = 1->2 调整之后1->2
    //list = 1->2->3 调整之后1->2->3
    //list = 1->2->3->4 调整之后1->3->2->4
    //list = 1->2->3->4->5 调整之后1->3->2->4->5
    //list = 1->2->3->4->5->6 调整之后1->4->2->5->3->6
    //list = 1->2->3->4->5->6->7 调整之后1->4->2->5->3->6->7
    //根据上面的规律， 调整一个任意长度的链表
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void relocate(Node head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Node help = head;
        int listLength = 0;
        while (help != null) {
            listLength++;
            help = help.next;
        }
        help = head;
        Node[] nodes = new Node[listLength];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = help;
            if (help.next == null) break;
            help = help.next;
        }
        Node[] res = (nodes.length & 1) == 1 ? new Node[nodes.length - 1] : new Node[nodes.length];
        for (int i = 0; i < res.length; i++) {
            if (i < res.length / 2) {
                res[i * 2] = nodes[i];
            } else {
                res[(i - (res.length / 2)) * 2 + 1] = nodes[i];
            }
        }
        for (int i = 0; i < res.length - 1; i++) {
            res[i].next = res[i + 1];
        }
        if ((nodes.length & 1) == 1) {
            res[res.length - 1].next = help;
            help.next = null;
        }
        head = res[0];

    }

    public static void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }



    //for Test
    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    //for Test
    public static void main(String[] args) {
        Node head = null;
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        relocate(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        relocate(head);
        printLinkedList(head);

    }
}
