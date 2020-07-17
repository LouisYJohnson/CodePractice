package myCodePractice.zuo.advance.class07;

public class IsBalancedTree {
    //给定一棵二叉树的头节点head， 判断该树是否是平衡二叉树
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public int level;
        public boolean isB;

        public ReturnData(int level, boolean isB) {
            this.level = level;
            this.isB = isB;
        }
    }

    public static boolean isBalanced(Node head) {
        if (head == null) return true;

        return process(head, 0).isB;
    }

    //递归函数功能:
    //  输入一个二叉树头节点以及该头节点所在的level(层),返回一个信息体
    //  包括该头节点对应二叉树是否是平衡二叉树,以及该头节点左右子树最深到哪个level
    public static ReturnData process(Node head, int level) {
        //base case
        if (head == null) return new ReturnData(level, true);

        ReturnData headLeft = process(head.left, level + 1);
        ReturnData headRight = process(head.right, level + 1);
        if (!headLeft.isB || !headRight.isB) return new ReturnData(level, false);
        if (Math.abs(headLeft.level - headRight.level) > 1) {
            return new ReturnData(level, false);
        }else {
            return new ReturnData(Math.max(headLeft.level, headRight.level), true);
        }
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
