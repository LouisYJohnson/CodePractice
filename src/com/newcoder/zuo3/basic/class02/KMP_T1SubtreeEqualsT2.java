package com.newcoder.zuo3.basic.class02;

public class KMP_T1SubtreeEqualsT2 {
    //给定两个二叉树T1和T2， 返回T1的某个子树结构是否与T2的结构相等。
    //其实就是判断序列化后的T2是否为T1序列化后的子串
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isSubtree(Node t1, Node t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str,t2Str) != -1;
    }

    public static String serialByPre(Node head) {
        //base case
        if (head == null) return "#!";
        //
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }


    //实现KMP
    //在s中找m,并返回m在s中的起始坐标
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length() || s == null || m == null || m.length() < 1) return -1;
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index = 0;
        int mi = 0;
        while (index < s.length() && mi < m.length()) {
            if (ss[index] == ms[mi]) {
                index++;
                mi++;
            }else if (nextArr[mi] == -1) {
                index++;
            }else {
                mi = nextArr[mi];
            }
        }
        //如果相等,会再++的,所以如果走完,会==m.length()
        return mi == m.length() ? index - mi : -1;
    }
    //next数组的计算
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) return new int[] {-1};
        int[] next = new int[ms.length];
        int pos = 2;
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        while (pos < ms.length) {
            if (ms[pos-1] == ms[cn]) {
                next[pos++] = ++cn;
            }else if (cn > 0) {
                cn = next[cn];
            }else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(isSubtree(t1, t2));

    }

}
