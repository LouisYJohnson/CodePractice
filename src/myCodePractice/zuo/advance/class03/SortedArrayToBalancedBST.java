package myCodePractice.zuo.advance.class03;

public class SortedArrayToBalancedBST {
    //通过有序数组生成平衡搜索二叉树
    //【题目】
    //给定一个有序数组sortArr， 已知其中没有重复值， 用这个有序
    //数组生成一棵平衡搜索二叉树， 并且该搜索二叉树中序遍历的
    //结果与sortArr一致。

    //平衡:任何一个节点左子树右子树高度差距不大于1
    //思路:有序数组找到中点,左边生成一个二叉树,右边生成一个二叉树,然后返回这个二叉树的头节点
    //      同样是二叉树题目的套路,就是收集信息然后返回
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node sortedArrayToBalancedBST(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        return process(arr, 0, arr.length - 1);
    }

    //递归函数功能:
    //给定有序数组与左右范围,返回这个数组变成平衡搜索二叉树的头节点
    //递归中的这种子流程的建立方法就决定了构建出来的一定是平衡二叉树(每次都用中点作为头部)
    public static Node process(int[] arr, int l, int r) {
        //base case
        if (l > r) {
            return null;
        }

        int mid = l + (r - l) / 2;
        Node head = new Node(arr[mid]);
        Node left = process(arr, l, mid - 1);
        Node right = process(arr, mid + 1, r);
        head.left = left;
        head.right = right;
        return head;

    }

//implement bu zuo
    public static Node generateTree1(int[] sortArr) {
        if (sortArr == null) {
            return null;
        }
        return generate(sortArr, 0, sortArr.length - 1);
    }

    public static Node generate(int[] sortArr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node head = new Node(sortArr[mid]);
        head.left = generate(sortArr, start, mid - 1);
        head.right = generate(sortArr, mid + 1, end);
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
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        printTree(sortedArrayToBalancedBST(arr));
        printTree(generateTree1(arr));
    }

}
