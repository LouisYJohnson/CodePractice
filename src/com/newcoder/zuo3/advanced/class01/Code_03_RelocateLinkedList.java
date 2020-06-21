package com.newcoder.zuo3.advanced.class01;

public class Code_03_RelocateLinkedList {
    //����һ������list��
    //�����
    //list = 1 ����֮��1��
    //list = 1->2 ����֮��1->2
    //list = 1->2->3 ����֮��1->2->3
    //list = 1->2->3->4 ����֮��1->3->2->4
    //list = 1->2->3->4->5 ����֮��1->3->2->4->5
    //list = 1->2->3->4->5->6 ����֮��1->4->2->5->3->6
    //list = 1->2->3->4->5->6->7 ����֮��1->4->2->5->3->6->7
    //��������Ĺ��ɣ� ����һ�����ⳤ�ȵ�����
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //�췽��:
    // ���������:ȥ�����һ��,Ȼ�������,���һ���ұ�һ��,������ȥ��������Ǹ�
    //�����ż��:ֱ�ӷ�����,Ȼ�����һ���ұ�һ��
    public static void relocate(Node head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Node help = head;
        //Java�е��������ָ����С,���ǳ�ʼ����ʱ��Ԫ�ؾ�װ��ȥ��
        int listLength = 0;
        while (help != null) {
            listLength++;
            help = help.next;
        }
        help = head;
        Node[] nodes = new Node[listLength];
        //�����е�Nodes�ڵ㶼װ�����������
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = help;
            if (help.next == null) break;
            help = help.next;
        }
        //�������Ϊ����,�ó����һ��,��һ�����������������еĽڵ�,�ڽ���������,�����һ���ڵ�
        //��ߵ�i�ڷ���֮��,Ϊi*2,�ұߵ�n�����ͷ��ڵ�n��������,Ϊ(n-(�ұ��ж��ٸ���))*2+1
        Node[] res = (nodes.length & 1) == 1 ? new Node[nodes.length - 1] : new Node[nodes.length];
        for (int i = 0; i < res.length; i++) {
            if (i < res.length / 2) {
                res[i * 2] = nodes[i];
            } else {
                res[(i - (res.length / 2)) * 2 + 1] = nodes[i];
            }
        }
        //��ż�������е����ж�������
        for (int i = 0; i < res.length - 1; i++) {
            res[i].next = res[i + 1];
        }
        //���������,Ҫ��ǰ��Ķ����������ٰ�helpָ��ı��ų������һ���ӵ���β
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

    //��ʹ�ö���ռ�ķ���:��ָ������ָ��


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
