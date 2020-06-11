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
    //根据上面的规律， 调整一个任意长度的链表。
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    //快方法:
    // 如果是奇数:去掉最后一个,然后分两半,左边一个右边一个,最后加上去掉的最后那个
    //如果是偶数:直接分两半,然后左边一个右边一个
    public static void relocate(Node head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Node help = head;
        //Java中的数组必须指定大小,除非初始化的时候元素就装进去了
        int listLength = 0;
        while (help != null) {
            listLength++;
            help = help.next;
        }
        help = head;
        Node[] nodes = new Node[listLength];
        //将所有的Nodes节点都装到这个数组中
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = help;
            if (help.next == null) break;
            help = help.next;
        }
        //如果长度为奇数,拿出最后一个,在一个新数组中排列其中的节点,在交换结束后,加最后一个节点
        //左边的i在放完之后,为i*2,右边第n个数就放在第n个奇数上,为(n-(右边有多少个数))*2+1
        Node[] res = (nodes.length & 1) == 1 ? new Node[nodes.length - 1] : new Node[nodes.length];
        for (int i = 0; i < res.length; i++) {
            if (i < res.length/2) {
                res[i * 2] = nodes[i];
            }else {
                res[(i - (res.length / 2)) * 2 + 1] = nodes[i];
            }
        }
        //把偶数数组中的所有都串起来
        for (int i = 0; i < res.length - 1; i++) {
            res[i].next = res[i + 1];
        }
        //如果是奇数,要把前面的都穿起来后再把help指向的被排除的最后一个加到结尾
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

    //不使用额外空间的方法:快指针与慢指针


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
