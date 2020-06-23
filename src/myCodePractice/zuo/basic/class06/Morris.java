package myCodePractice.zuo.basic.class06;

import java.util.Stack;

//具体的方法(注释可以去myCodePractice/zuo/basic/class02/KMP_T1SubTreeEqualsT2.java中查看,
// 该文件使用Morris对二叉树进行打印,使用KMP对二叉树序列化的结果进行判断子串)
public class Morris {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                } else {
                    help.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }



    public static void morrisMid(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    cur = cur.left;
                } else {
                    System.out.println(cur.value);
                    cur = cur.right;
                    help.right = null;
                }
            } else {
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    public static void morrisPos(Node head) {
        if (head == null) return;

        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    cur = cur.left;
                } else {
                    help.right = null;
                    printRightBoundReverse(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printRightBoundReverse(head);

    }

    public static void printRightBoundReverse(Node head) {
        if (head == null) return;

        Stack<Node> helpStack = new Stack<>();
        while (head != null) {
            helpStack.push(head);
            head = head.right;
        }
        while (!helpStack.isEmpty()) {
            System.out.println(helpStack.pop().value);
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        Node head6 = new Node(6);
        head1.left = head2;
        head1.right = head3;
        head2.left = head4;
        head2.right = head5;
        head5.left = head6;
//        morrisPre(head1);
//        morrisMid(head1);
        morrisPos(head1);


    }
}
