package com.newcoder.zuo3.advanced.class01;

import java.util.LinkedList;
import java.util.Queue;

public class Code_04_BSTtoDoubleLinkedList {
    //��һ�������������� ת���������˫������
    //����������ʹ�õݹ�����
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //����1:
    public static Node convert1(Node head) {
        if (head == null) return null;
        return process1(head);
    }

    //�ݹ麯��:����:��һ����������ͷ�ڵ�,����������������˫��������ͷ�ڵ�
    //ȷ���ݹ麯���Ĺ���(������������۵�,����������۵���Ϊ����ݹ麯���ĺ������ֻ�����������۵ĺ����Ĺ��ܵķ���ֵ������,��ô����ݹ���ǶԵ�),
    // �����ڲ����϶���������ܾͷ����������ֵ,����Ĳ��ֶ������������ֵȥ��д,��һ���ǶԵ�!
    public static Node process1(Node head) {
        //base case
        if (head == null) return null;
        //�ֱ��ҵ���ǰͷ�ڵ������������ͷ�ڵ�����ͷ�ڵ�
        Node leftHead = process1(head.left);
        Node rightHead = process1(head.right);
//ȷ���ݹ麯���Ĺ���,�����ڲ����϶���������ܾͷ����������ֵ,����Ĳ��ֶ������������ֵȥ��д,��һ���ǶԵ�!
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

    //����2
    //�ղŵķ����ҵ��м���Ҫ��������,��һ�������Ĺ���,���԰���������Ĺ���ʡȥ
    //������������,ÿ�ζ�����һ������,�����д���ͷ�ڵ��Ӧ�ı��˫�����������ڵ�����ҽڵ�
    //��·:ʹ�õݹ�����������ʱ��,����������Ϣ����������Ϣ,����һ��ֻҪ��һ��ֻҪ��,��ʱ����������������ȫ����Ϣ����
    public static Node convert3(Node head) {
        if (head == null) return null;
        return process2(head)[0];
    }

    //����ݹ麯��,����ͷ�ڵ��Ӧ������ڵ�����ҽڵ�
//    Node[0]:����
//    Node[1]:����
    public static Node[] process2(Node head) {
        //base case
        if (head == null) return new Node[]{null, null};
        //leftSub����head��������������ڵ�����ҽڵ�
        Node[] leftSub = process2(head.left);
        Node[] rightSub = process2(head.right);
        head.left = null;
        head.right = null;
// �����ڲ����϶���������ܾͷ����������ֵ,����Ĳ��ֶ������������ֵȥ��д,��һ���ǶԵ�!
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
        return new Node[]{left, right};
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
