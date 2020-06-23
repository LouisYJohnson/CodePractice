package myCodePractice.zuo.advance.class01;


public class RelocateLinkedList {
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

    //从中心位置将链表分为两半(奇数个节点中间节点算右边)
    public static Node relocate(Node head) {
        if (head == null) return null;

        Node helpHead = head;
        int listLen = 0;
        while (helpHead != null) {
            listLen++;
            helpHead = helpHead.next;
        }
//        if (listLen < 4) return head;

        Node[] help = new Node[listLen];
        int index = 0;
        helpHead = head;
        while (helpHead != null) {
            help[index++] = helpHead;
            helpHead = helpHead.next;
        }

        //链表长度为奇数与偶数的做法是不同的
        //奇数在l,r共同前进,l到头的时候r还剩一个,需要单独添加
        int mid = 0 + (listLen - 1 - 0) / 2;
        int l = 0;
        int r = (listLen & 1) == 1 ? mid : mid + 1;
        index = 0;
        Node[] res = new Node[help.length];
        //链表长度为偶数
        if ((listLen & 1) == 0) {
            while (r < help.length) {
                res[index++] = help[l++];
                res[index++] = help[r++];
            }
        }else { //链表长度为奇数
            while (r < help.length - 1) {
                res[index++] = help[l++];
                res[index++] = help[r++];
            }
            res[index] = help[r];
        }

        //将节点数组穿在一起
        Node helpNode1 = head;
        for (int i = 1; i < res.length; i++) {
            helpNode1.next = res[i];
            helpNode1 = helpNode1.next;
        }
        return helpNode1;
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
