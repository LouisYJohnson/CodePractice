package com.newcoder.zuo3.basic;

public class Class_04_DescendantNode {
    //该结构比普通二叉树节点结构多了一个指向父节点的parent指针。 假设有一
    //棵Node类型的节点组成的二叉树， 树中每个节点的parent指针都正确地指向
    //自己的父节点， 头节点的parent指向null。 只给一个在二叉树中的某个节点
    //node， 请实现返回node的后继节点的函数。 在二叉树的中序遍历的序列中，
    //node的下一个节点叫作node的后继节点。
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }
    //分为有右子树,和没有右子树
    //有右子树:获取右子树的最左节点
    //无右子树:向上找,包括自身,如果当前节点的父节点的左孩子为当前节点,则该父节点为下一个节点
    public static Node getNextNode(Node node) {
        if (node == null) return null;
        if (node.right == null) {
            Node nodeHelp = node;
            while (nodeHelp.parent != null) {
                if (nodeHelp.parent.left == nodeHelp) return nodeHelp.parent;
                nodeHelp = nodeHelp.parent;
            }
            return null;
        }else if (node.right != null) {
            Node nodeHelp2 = node.right;
            while (nodeHelp2.left != null) {
                nodeHelp2 = nodeHelp2.left;
            }
            return nodeHelp2;
        }
        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getNextNode(test));
    }

}
//package com.newcoder.zuo.basic;
//
//public class Class_04_DescendantNode {
//
//    public static class Node {
//        public int value;
//        public Node left;
//        public Node right;
//        public Node parent;
//
//        public Node(int data) {
//            this.value = data;
//        }
//    }
//
//    public static Node getNextNode(Node node) {
//        if (node == null) {
//            return node;
//        }
//        if (node.right != null) {
//            return getLeftMost(node.right);
//        } else {
//            Node parent = node.parent;
//            while (parent != null && parent.left != node) {
//                node = parent;
//                parent = node.parent;
//            }
//            return parent;
//        }
//    }
//
//    public static Node getLeftMost(Node node) {
//        if (node == null) {
//            return node;
//        }
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//
//    public static void main(String[] args) {
//        Node head = new Node(6);
//        head.parent = null;
//        head.left = new Node(3);
//        head.left.parent = head;
//        head.left.left = new Node(1);
//        head.left.left.parent = head.left;
//        head.left.left.right = new Node(2);
//        head.left.left.right.parent = head.left.left;
//        head.left.right = new Node(4);
//        head.left.right.parent = head.left;
//        head.left.right.right = new Node(5);
//        head.left.right.right.parent = head.left.right;
//        head.right = new Node(9);
//        head.right.parent = head;
//        head.right.left = new Node(8);
//        head.right.left.parent = head.right;
//        head.right.left.left = new Node(7);
//        head.right.left.left.parent = head.right.left;
//        head.right.right = new Node(10);
//        head.right.right.parent = head.right;
//
//        Node test = head.left.left;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.left.left.right;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.left;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.left.right;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.left.right.right;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.right.left.left;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.right.left;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.right;
//        System.out.println(test.value + " next: " + getNextNode(test).value);
//        test = head.right.right; // 10's next is null
//        System.out.println(test.value + " next: " + getNextNode(test));
//    }
//
//}
