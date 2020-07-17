package com.newcoder.zuo3.advanced.class07;

public class Code_02_IsBalancedTree {
    //给定一棵二叉树的头节点head， 判断该树是否是平衡二叉树

    //二叉树中的一个节点需要信息:
    //左节点对应子树是不是平衡二叉树.与左节点对应子树达到的最大深度
    //右节点对应的子树是不是平衡二叉树与右节点对应子树达到的最大深度
    //自身返回给上级的应该是自己是不是平衡二叉树与自己对应的子树达到的最大深度

    ////返回的数据针对的是当前的Node
    //    //递归:传入头节点与头节点所在的层,返回两个数据,
    //    // 分别是该节点最深到哪一层level(如果是平衡二叉树的话,
    //    // 所以这里只计算是平衡二叉树的level如果不是,level保持不变)
    //    // 和以该节点为头节点对应的子树是不是平衡二叉树
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        private int level;
        private boolean isB;

        public ReturnData(int level, boolean isB) {
            this.level = level;
            this.isB = isB;
        }
    }


    public static ReturnData process(Node head, int level) {
        //base case
        if (head == null) return new ReturnData(level, true);

        ReturnData headLeft = process(head.left, level + 1);
        ReturnData headRight = process(head.right, level + 1);
        if (!headLeft.isB || !headRight.isB) return new ReturnData(level, false);
        if (Math.abs(headLeft.level - headRight.level) > 1) {
            return new ReturnData(level, false);
        }
        return new ReturnData(Math.max(headLeft.level, headRight.level), true);
    }

    //for Combinations
    public static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }

    public static boolean isBalance(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        ReturnData returnData = process(head, 1);
        System.out.println(isBalance(head));
        System.out.println(returnData.isB);
    }


}
