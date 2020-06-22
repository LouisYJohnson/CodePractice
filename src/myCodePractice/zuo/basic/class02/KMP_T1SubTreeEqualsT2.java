package myCodePractice.zuo.basic.class02;

import myCodePractice.zuo.basic.class06.Morris;

public class KMP_T1SubTreeEqualsT2 {
    //给定两个二叉树T1和T2， 返回T1的某个子树结构是否与T2的结构相等。
    //将两个字符串以前中后序(自选)遍历并打印成字符串,看T2对应的字符串是否为T1的子串

    public static class Node {
        public int value;
        Morris.Node left;
        Morris.Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //实现使用Morris遍历+KMP
    //实现Morris遍历
    //Morris遍历:
    //核心是有左子树的节点会访问两次,没有左子树的节点会访问一次
    //整体的顺序为:头节点,左子树,头节点,右子树
    //流程:
    //cur放在头节点上
    //  首先看当前节点有无左子树:
    //      无左子树:cur右移(第1次也是最后一次访问没有左子树的节点)
    //      有左子树:


    //前序
    public static String morrisPre() {
        StringBuffer res = new StringBuffer();


        return null;
    }

    //中序

    //后序

}
