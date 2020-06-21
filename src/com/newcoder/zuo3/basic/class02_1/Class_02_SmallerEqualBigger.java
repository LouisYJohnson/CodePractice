package com.newcoder.zuo3.basic.class02_1;

public class Class_02_SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //将单向链表按某值划分成左边小、 中间相等、 右边大的形式
    //简单做法:引入辅助数组,做成partion的样子就行
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) return null;
        Node nodeCount = head;
        int countNode = 0;
        while (nodeCount != null) {//数出链表中有多少个数
            countNode++;
            nodeCount = nodeCount.next;
        }
        nodeCount = head;
        //将链表中的数字装到数组中
        int[] nodeVal = new int[countNode];
        for (int i = 0; i < countNode; i++) {
            nodeVal[i] = nodeCount.value;
            nodeCount = nodeCount.next;
        }
        //将数组进行partion操作
        arrPatrion(nodeVal, pivot, 0, countNode - 1);
        //将数组中的数字装回head的Node中去
        int i = 0;
        Node helpNode = head;
        while (helpNode != null) {
            helpNode.value = nodeVal[i++];
            helpNode = helpNode.next;

        }
        return head;

    }

    public static void arrPatrion(int[] arr, int pivot, int l, int r) {
        int minIndex = l - 1;
        int maxIndex = r + 1;
        int cur = l;
        while (cur < maxIndex) {
            if (arr[cur] == pivot) {
                cur++;
            } else if (arr[cur] > pivot) {
                swap(arr, --maxIndex, cur);
            } else if (arr[cur] < pivot) {
                swap(arr, ++minIndex, cur++);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition1(head1, 4);
        printLinkedList(head1);

    }

}
