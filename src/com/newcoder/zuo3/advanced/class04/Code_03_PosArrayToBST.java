package com.newcoder.zuo3.advanced.class04;

public class Code_03_PosArrayToBST {
    //根据后序数组重建搜索二叉树
    //【题目】
    //给定一个整型数组arr， 已知其中没有重复值， 判断arr是否可能是节
    //点值类型为整型的搜索二叉树后序遍历的结果。
    //进阶： 如果整型数组arr中没有重复值， 且已知是一棵搜索二叉树的后
    //序遍历结果， 通过数组arr重构二叉树。
    //做这个题的时候是在考察抠边界的能力,有可能没有左子树或者右子树的情况该怎么办以及如何判断有没有左右子树
    //非进阶:
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        //递归函数,功能为返回在数组上从0到arr.length - 1范围上的这个数组是不是二叉树的后序遍历结果
        return process(arr, 0, arr.length - 1);
    }

    public static boolean process(int[] arr, int start, int end) {
        //base case
        if (start == end) return true;
        //搜索二叉树是左子小右子大,后序遍历应该是分为三个区域头节点在最后,小的节点在最前面,大的节点在中间
        //small与big表示小的节点的最右边界,与大的节点的最左边界
        //如果遍历完成后,小节点的最右边界与大节点的最左边界差1说明是对的,继续分别判断小节点与大节点对应的子树
        //如果小节点超过了大节点,直接返回false
        int small = -1;
        int big = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                small = i;
            } else {//因为数组中没有重复的数,所以else表示当前arr[i]比arr[end]大
                //big的值为第一次出现大值的左边界,后续出现大值不更新,不出现大值不更新
                big = big == end ? i : big;
            }
        }
        if (small > big) return false;
        if (small == -1 || end == big) {//没有左子树或者没有右子树
            return process(arr, start, end - 1);
        }
        //如果左右子树都正常,分别判断左右子树
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
    //进阶
//    进阶： 如果整型数组arr中没有重复值， 且已知是一棵搜索二叉树的后
//    序遍历结果， 通过数组arr重构二叉树。
    public static Node posArrayToBST(int[] posArr) {
        if (posArr == null || posArr.length == 0) return null;
        return process1(posArr,0,posArr.length - 1);
    }

    //递归函数功能:给一个表示搜索二叉树逆序数组和起始点与重点,返回这个数组中对应的搜索二叉树头节点
    public static Node process1(int[] arr,int start,int end) {
        //base case
        if (start == end) return new Node(arr[start]);
//        if (start > end) return null;

        Node head = new Node(arr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                less = i;
            }else {
                more = more == end ? i : more;
            }
        }
//        head.left = process1(arr,start,less);
//        head.right = process1(arr,more,end - 1);
        if (less == -1 && more != end) {//无左子树有右子树
            head.left = null;
            head.right = process1(arr,more,end - 1);
        }else if (less != -1 && more == end) {//无右子树有左子树
            head.left = process1(arr,start,end - 1);
            head.right = null;
        }else {//左右子树都有
            head.left = process1(arr,start,less);
            head.right = process1(arr,more,end - 1);
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
        int[] arr = { 2, 1, 3, 6, 5, 7, 4 };
        System.out.println(process(arr, 0, arr.length - 1));
        printTree(posArrayToBST(arr));

    }
}
