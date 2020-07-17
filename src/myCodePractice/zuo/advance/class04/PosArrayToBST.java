package myCodePractice.zuo.advance.class04;

public class PosArrayToBST {
    //根据后序数组重建搜索二叉树
    //【题目】
    //给定一个整型数组arr， 已知其中没有重复值， 判断arr是否可能是节
    //点值类型为整型的搜索二叉树后序遍历的结果。
    //进阶： 如果整型数组arr中没有重复值， 且已知是一棵搜索二叉树的后
    //序遍历结果， 通过数组arr重构二叉树

    //根据后序中序先序数组结合是可以建出二叉树的,但是有重复值的话,是建不出来的

    //搜索二叉树的特点:相较于头节点,左子树中的元素一定是小于头节点的右子树中的元素一定是大于头节点的
    //根据这个特性,就可以使用递归判断出一个数组是否是后序遍历二叉树得到的
    //搜索二叉树后序遍历生成数组的结构(头代表一个点,区表示一个区间中都是对应的元素):
    //  1:小区|大区|头
    //  2:大区|头
    //  3:小区|头
    //  4:头
    //因为题目中说没有重复值,所以不存在等于区的情况
    //分为左子树区(如果有的话),右子树区(如果有的话)和头节点位置,左子树区中的数都小于头节点位置(数组结尾)
    //右子树区中的数都大于头节点位置的数
    //然后进入子过程判断左子树区和右子树区是否同样满足搜索二叉树后序遍历的顺序即可

    //进阶:
    //  首先数组要满足搜索二叉树后序排列,然后仍然是递归函数
    //  递归函数功能:给定一个二叉树后序排列形成的数组,和一个范围,返回数组中该范围上重建后的搜索二叉树的头节点

    //判断arr是否可能是节点类型值为整形的搜索二叉树的后序遍历结果
    public static boolean isBSTPos(int[] arr) {
        if (arr == null || arr.length == 0) return false;

        return process1(arr, 0, arr.length - 1);
    }
    //递归函数功能:
    //  输入一个数组和表示数组范围的两个下标,返回数组在这个范围内是否是由搜索二叉树后序遍历而来
    public static boolean process1(int[] arr, int l, int r) {
        //base case
        //到最后肯定只剩一个点,一个点一定能组成搜索二叉树
        //递归中的这种更新规则(传入的l和r)就决定了到最后的base case应该是什么样
        if (l == r) {
            return true;
        }
        //规定小于区域的右边界和大于区域的左边界
        //如果这个数组符合条件的化,小于区域的右边界+1就是大于区域的左边界
        //搜索二叉树有可能没有左子树,有可能没有右子树,也有可能只有头节点(base case)
        //为什么初始值是这两个无效值?
        //  答:因为有可能没有左子树或者没有右子树,设置初始值就是为了能够判断这两个情况
        int minAreaRight = -1;
        int maxAreaLeft = r;
        //遍历这个数组的范围内的数(除了最后一个,因为最后一个是头节点,是用来比较的)
        //更新小于区域的右边界和大于区域的左边界,然后看这两个边界是否符合条件
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[r]) {
                minAreaRight = i;
            }else {
                //因为是最大区域的左边,但是遍历是从左到右
                //如果第一次碰到一个数比最右边的数大,那就是左边界了
                //如果在这之后又碰到了,就不应该再更新左边界
                maxAreaLeft = maxAreaLeft == r ? i : maxAreaLeft;
            }
        }
        //得到左边界与右边界后,就根据左右边界来判断:
        //  1.是否符合后序遍历搜索二叉树的排列规矩(左右区不能重叠)
        //  2.左右边界如果仍然是初始值就说明对应的区不存在
        //  3.如果不是初始值,而且符合排列规矩,那就进入子过程,判断小区与大区是否是搜索二叉树后序遍历得到的
        if (minAreaRight >= maxAreaLeft) {
            return false;
        }else if (minAreaRight == -1 && maxAreaLeft != r) { //没有左子树
            return process1(arr, l, r - 1);
        }else if (maxAreaLeft == r && minAreaRight != -1) {  //没有右子树
            return process1(arr, l, r - 1);
        }else { //左右子树都有
            return process1(arr, l, minAreaRight) && process1(arr, maxAreaLeft, r - 1);
        }
    }

    //进阶:
    //  首先数组要满足搜索二叉树后序排列,然后仍然是递归函数
    //  递归函数功能:给定一个二叉树后序排列形成的数组,和一个范围,返回数组中该范围上重建后的搜索二叉树的头节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node posArrayToBST(int[] arr) {
        if (arr == null || arr.length == 0 || !isBSTPos(arr)){
            return null;
        }

        return process2(arr, 0, arr.length - 1);
    }
    //递归函数功能:给定一个二叉树后序排列形成的数组,和一个范围,返回数组中该范围上重建后的搜索二叉树的头节点
    public static Node process2(int[] arr, int l, int r) {
        //base case
        //根据下面传入l,r的规则,到最后一定会碰到l==r的时候(只剩下一个元素)这时候直接将这个元素作为搜索二叉树的头节点返回即可
        //(我认为写递归函数的顺序应该在最后写base case,因为base case是按照子过程的参数传入方法定的)
        if (l == r) {
            return new Node(arr[l]);
        }

        //查找左右区,左右区再去做递归
        Node head = new Node(arr[r]);
        int minAreaRight = -1;
        int maxAreaLeft = r;
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[r]) {
                minAreaRight = i;
            }else {
                maxAreaLeft = maxAreaLeft == r ? i : maxAreaLeft;
            }
        }
        //根据左右区,判断递归应该在哪些区域上做
        //因为已经判断过这个数组一定是搜索二叉树后序遍历得到的,所以此处不用加判断,得到的左右区边界一定是正确的
        if (minAreaRight == -1 && maxAreaLeft != r) {   //没有左子树
            head.right = process2(arr, l, r - 1);
        } else if (minAreaRight != -1 && maxAreaLeft == r) {    //没有右子树
            head.left = process2(arr, l, r - 1);
        }else { //左右子树都有
            head.left = process2(arr, l, minAreaRight);
            head.right = process2(arr, maxAreaLeft, r - 1);
        }
        return head;
    }

    // for Combinations -- print tree
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
        System.out.println(isBSTPos(arr));
        printTree(posArrayToBST(arr));

    }
}
