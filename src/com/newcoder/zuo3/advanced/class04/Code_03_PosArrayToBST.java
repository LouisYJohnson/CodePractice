package com.newcoder.zuo3.advanced.class04;

public class Code_03_PosArrayToBST {
    //根据后序数组重建搜索二叉树
    //【题目】
    //给定一个整型数组arr， 已知其中没有重复值， 判断arr是否可能是节
    //点值类型为整型的搜索二叉树后序遍历的结果。
    //进阶： 如果整型数组arr中没有重复值， 且已知是一棵搜索二叉树的后
    //序遍历结果， 通过数组arr重构二叉树。

    //根据后序中序先序数组结合是可以建出二叉树的,但是有重复值的话,是建不出来的
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        return process(arr, 0, arr.length - 1);
    }

    public static boolean process(int[] arr, int start, int end) {
        //base case
        if (start == end) return true;
        int small = -1;
        int big = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                small = i;
            } else {
                big = big == end ? i : big;
            }
        }
        if (small > big) return false;
        if (small == -1 || end == big) {
            return process(arr, start, end - 1);
        }
        return process(arr, start, small) && process(arr, big, end - 1);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node posArrayToBST(int[] posArr) {
        if (posArr == null || posArr.length == 0) return null;
        return process1(posArr, 0, posArr.length - 1);
    }

    public static Node process1(int[] arr, int start, int end) {
        //base case
        if (start == end) return new Node(arr[start]);
//        if (start > end) return null;

        Node head = new Node(arr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
//        head.left = process1(arr,start,less);
//        head.right = process1(arr,more,end - 1);
        if (less == -1 && more != end) {
            head.left = null;
            head.right = process1(arr, more, end - 1);
        } else if (less != -1 && more == end) {
            head.left = process1(arr, start, end - 1);
            head.right = null;
        } else {
            head.left = process1(arr, start, less);
            head.right = process1(arr, more, end - 1);
        }
        return head;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 6, 5, 7, 4};
        System.out.println(process(arr, 0, arr.length - 1));
        printTree(posArrayToBST(arr));

    }
}
