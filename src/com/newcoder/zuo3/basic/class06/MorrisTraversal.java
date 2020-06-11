package com.newcoder.zuo3.basic.class06;

public class MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //morris遍历的实质为在树中对于任意一个有左子树的节点,会访问自己两次,
    // 对于任意一个没有左子树的节点,会访问自己一次
    public static void morrisIn(Node head) {
        if (head == null) return;
        //cur1为当前节点,cur2为当前节点左子树的最右节点
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                //找到cur1左子树的最右节点
                while (cur2.right != null) {
                    cur2 = cur2.right;
                }
                //most right的右孩子为null,则most right右孩子指向cur1,然后cur向左移动
                //说明第一次访问到cur1
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }
                //most right的右孩子已经指向cur1,则右孩子指向null,cur向右移动
                //第二次访问到cur1
                if (cur2.right == cur1) {
                    cur2.right = null;
                }
            }
            //因为当前节点没有左子树与most right右孩子已经指向cur1的时候,cur都会向右移动,所以cur1向右移动放在这里
            //并且中序遍历,要把打印行为放在第一次碰到没有左子树的节点与第二次碰到有左子树的节点上
            System.out.println(cur1.value + " ");
            cur1 = cur1.right;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    System.out.println(cur1.value + " ");
                    cur2.right = cur1;
                    cur1 = cur1.left;
                }else {
                    cur2.right = null;
                }
            }
            System.out.println(cur1.value + " ");
            cur1 = cur1.right;
        }
    }
}
