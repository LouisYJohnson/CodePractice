package myCodePractice.zuo.advance.class07;

public class MaxDistance {
    //二叉树中由节点a往上或者往下走到节点b， 最短路径上节点的数量叫做a到b的距离。
    //给定一棵二叉树的头节点head， 求整棵树中的最大距离

    //分析可能性:
    //1.最大路径可能来自于左子树上的最大路径
    //2.同理最大路径可能来自于右子树上的最大路径
    //3.最大距离为左子树最深节点到当前节点到右子树最深节点

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //返回信息中包含两个信息
    //height:传入节点(包括自身)的最深深度
    //maxLength:传入节点(包括自身)对应左右子树的最大距离
    public static class ReturnData{
        public int height;
        public int maxLength;

        public ReturnData(int height, int maxLength) {
            this.height = height;
            this.maxLength = maxLength;
        }
    }

    public static int getMaxDistance(Node head) {
        if (head == null) return 0;

        return process(head).maxLength;
    }

    //递归函数功能:
    //给定一个二叉树的头节点,返回信息:
    //返回信息中包含两个信息
    //height:传入节点(包括自身)的最深深度
    //maxLength:传入节点(包括自身)对应左右子树的最大距离
    //  在流程中:包含当前头节点的左右节点的最深深度可能会成为父过程中的最大距离
    public static ReturnData process(Node head) {
        //base case
        if (head == null) return new ReturnData(0, 0);

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        int includeHeadLength = leftData.height + 1 + rightData.height;
        int maxLength = Math.max(includeHeadLength, Math.max(leftData.maxLength, rightData.maxLength));
        //更新最深深度
        int maxHeight = Math.max(leftData.height, rightData.height);
        //向父过程返回最深深度的时候要包含当前节点,所以深度height要+1
        return new ReturnData(maxHeight + 1, maxLength);
    }
}
