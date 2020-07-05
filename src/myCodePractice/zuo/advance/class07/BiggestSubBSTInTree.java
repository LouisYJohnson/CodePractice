package myCodePractice.zuo.advance.class07;

public class BiggestSubBSTInTree {
    //给定一棵二叉树的头节点head， 已知所有节点的值都不一样， 求最大
    //的搜索二叉子树的节点数量。

    //一个节点需要信息:
    //左树对应的左树上最大搜索二叉子树大小
    //右树对应的右树上最大搜索二叉子树大小
    //如果包括当前节点的话的最大搜索二叉树大小(本来左右各自返回最大就完事了正是因为有这个可能所以需要额外返回头,max,min)
    //这个大小有可能来自左子树,右子树,或者是包括当前节点
    //所以有三种可能,对应了三种可能,如果这个最大搜索二叉树包含了当前节点,那么就需要左子树和右子树返回以自身为头节点的子树是不是搜索二叉树,同时左子树是搜索二叉树的情况下左子树最大值比自身小,右子树是搜索二叉树的情况下最小值比自身大
    //所以要搜集的信息有:
    //size(最大搜索二叉树大小)
    //左最大搜索二叉树的头部(不一定是左子树的头节点)
    //右最大搜索二叉树的头部(不一定是右子树的头节点)
    //如果左右的最大搜索二叉树的头节点都是当前节点的左右子节点的话,并且左侧最大值小于自身,右侧最小值大于自身,那么就属于第三种可能

    //所以每个节点都要整合四个信息:最大搜索二叉树大小size,对应的最大搜索二叉树头部,节点对应的整个子树中的最小值和最大值
    //虽然左树只需要max右树只需要min但是这是递归过程,所有的东西都要返回

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public int size;
        public Node bigHead;
        public int min;
        public int max;

        public ReturnData(int size, Node bigHead, int min, int max) {
            this.size = size;
            this.bigHead = bigHead;
            this.min = min;
            this.max = max;
        }
    }

    public static int getBiggestSubBSTInTree(Node head) {
        if (head == null) return 0;


        return process(head).size;
    }

    //递归函数功能:
    //  传入头节点,返回该头节点对应的最大搜索二叉树大小size,该二叉树的头节点,以及传入节点对应的整个子树中的最大值和最小值
    //将情况分为当前节点可以连接与不能连接(将可以连接放在前面)是最好判断的,如果先判断不能连接,条件太多,反而是可以连接取个反
    //  也就是可以连接的else更容易
    public static ReturnData process(Node head) {
        //base case
        if (head == null) return new ReturnData(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);

        ReturnData leftReturn = process(head.left);
        ReturnData rightReturn = process(head.right);
        int includeHeadSize = 0;
        //返回的min和max一定要是包括当前节点,当前节点左子树,当前节点右子树的所有节点中最大或最小的
        int min = Math.min(head.value, Math.min(leftReturn.min, rightReturn.min));
        int max = Math.max(head.value, Math.max(leftReturn.max, rightReturn.max));

        //如果当前节点可以作为搜索树的Head向上提交
        if (leftReturn.max < head.value
                &&
                rightReturn.min > head.value
                &&
                leftReturn.bigHead == head.left
                &&
                rightReturn.bigHead == head.right) {
            includeHeadSize = leftReturn.size + rightReturn.size + 1;
            return new ReturnData(includeHeadSize, head, min, max);
        }

        //如果不行,选择左右子树中具有更大size的Node返回
        int maxSizeLR = Math.max(leftReturn.size, rightReturn.size);
        ReturnData bigReturn = leftReturn.size == maxSizeLR ? leftReturn : rightReturn;
        return new ReturnData(bigReturn.size, bigReturn.bigHead, min, max);
    }

    //for test
    public static Node biggestSubBST(Node head) {
        int[] record = new int[3]; // 0->size, 1->min, 2->max
        return posOrder(head, record);
    }

    public static Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
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

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printTree(head);
        Node bst = biggestSubBST(head);
        System.out.println("-------------------------");
        printTree(bst);
        ReturnData returnData = process(head);
        System.out.println(returnData.size);
    }
}
