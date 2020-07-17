package myCodePractice.zuo.basic.class02;

import java.util.Stack;

public class KMP_T1SubTreeEqualsT2 {
    //给定两个二叉树T1和T2， 返回T1的某个子树结构是否与T2的结构相等。
    //将两个字符串以前中后序(自选)遍历并打印成字符串,看T2对应的字符串是否为T1的子串

    public static class Node {
        public int value;
        Node left;
        Node right;

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
    //      无左子树:cur右移(第1次也是最后一次访问没有左子树的节点cur)
    //      有左子树:找cur左子树的最右节点
    //          (1):如果cur左子树的最右节点的右孩子为null,说明第一次访问cur,将右孩子指向当前的cur,并将cur左移
    //          (2):如果cur左子树的最右节点的右孩子为cur,说明第二次访问到cur,将右孩子重新指向null,将cur右移
    //当cur指向null,整体流程结束
    //前序和中序都好办,分别在第一次与第二次碰见有左子树的节点进行输出即可
    //后序:第二次来到cur的时候,将cur左子树的右边界逆序打印,整棵树遍历完,单独逆序打印整棵树的右边界(包括head,因为
    // 有左子树的节点并不会打印节点自身)
    //注意,在使用Morris遍历的时候,节点与节点之间使用一个符号(自己选,此处选则!)作为标记,表示节点之间的区分符号
    //如果没有的话,2,3与23是同一个节点,没有办法用KMP做

    //前序
    public static String morrisPre(Node head) {
        if (head == null) return null;

        StringBuffer res = new StringBuffer();
        Node cur = head;
        Node help = null;

        while (cur != null) {
            //如果cur没有左子树,直接右移
            if (cur.left == null) {
                res.append(cur.value + "!");
                cur = cur.right;
            } else { //如果有左子树,找到左子树的最右节点并看最右节点的右孩子
                help = cur.left;
                while (help.right != null && help.right != cur) {   //第二次访问cur节点的时候就会指向cur,后面的条件避免成为一个死循环,
                    //而且能够停在左子树的最右节点上,而不是停在左子树最右节点的右孩子上
                    help = help.right;
                }
                //通过最右节点的右孩子指向null还是cur来判断第一次还是第二次访问有左子树的节点cur
                //如果是第一次,将右孩子指向cur,cur左移
                //如果是第二次,将右孩子重新指向null,cur右移
                if (help.right == null) {
                    res.append(cur.value + "!");
                    help.right = cur;
                    cur = cur.left;
                } else {
                    help.right = null;
                    cur = cur.right;
                }
            }
        }
        return String.valueOf(res);
    }

    //中序
    public static String morrisIn(Node head) {
        if (head == null) return null;

        StringBuffer res = new StringBuffer();
        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left == null) {
                res.append(cur.value + "!");
                cur = cur.right;
            } else {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == cur) {
                    res.append(cur.value + "!");
                    help.right = null;
                    cur = cur.right;
                } else {
                    help.right = cur;
                    cur = cur.left;
                }
            }
        }
        return String.valueOf(res);
    }

    //后序
    //后序:第二次来到cur的时候,将cur左子树的右边界逆序打印,整棵树遍历完,单独逆序打印整棵树的右边界(包括head,因为
    // 有左子树的节点并不会打印节点自身)
    public static String morrisPos(Node head) {
        if (head == null) return null;
        StringBuffer res = new StringBuffer();
        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == cur) {
                    help.right = null;
                    res.append(rightBorderReverse(cur.left));
                    cur = cur.right;
                }else {
                    help.right = cur;
                    cur = cur.left;
                }
            }
        }
        res.append(rightBorderReverse(head));
        return String.valueOf(res);
    }

    //打印一个节点的右边界
    public static String rightBorderReverse(Node head) {
        Stack<String> helpStack = new Stack<>();
        while (head != null) {
            helpStack.push(head.value + "!");
            head = head.right;
        }

        StringBuffer res = new StringBuffer();

        while (!helpStack.isEmpty()) {
            res.append(helpStack.pop());
        }
        return String.valueOf(res);
    }

    //实现KMP
    //判断str2是否在str1中
    //如果找到了,返回str2在str1中出现的坐标位置,如果没找到,返回-1
    public static int kmp(String str1, String str2) {
        if (str1.length() < str2.length() || str1 == null || str2 == null || str1.length() ==0 || str2.length() == 0) return -1;
        char[] str1Char = str1.toCharArray();
        char[] str2Char = str2.toCharArray();

        //对str2求next数组
        int[] next = new int[str2.length()];//不用str2.length+1是因为根本不会在str2.length+1的位置进行比较
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        for (int i = 2; i < next.length; i++) {
            cn = next[i - 1];
            while (cn != -1) {
                if (str2Char[cn] == str2Char[i - 1]) {
                    next[i] = cn + 1;
                    break;
                }else {
                    cn = next[cn];
                }
            }
        }
        //求完next数组,就可以对比较字符串进行加速了
        int pos1 = 0;
        int pos2 = 0;
        while (pos1 < str1.length() && pos2 < str2.length()) {
            if (str1Char[pos1] == str2Char[pos2]) {
                pos1++;
                pos2++;
            }else {
                pos2 = next[pos2];
                if (pos2 == -1) {
                    pos2 = 0;
                    pos1++;
                }
            }
        }
        //看str2对应的pos2走没走到头,如果没走到头,说明没有,返回-1,否则,返回第一次出现的位置
        if (pos2 == str2.length()) {
            return pos1 - pos2;
        }
        return -1;
    }

    //将二者结合在一起,得到Morris+KMP判断head1是否是另外一个树head2的子树
    public static boolean isSubTree(Node head1, Node head2) {
        String tree1 = morrisIn(head1);
        String tree2 = morrisIn(head2);
        return kmp(tree2, tree1) != -1;
    }


    //for Combinations
    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        Node head6 = new Node(6);
        head1.left = head2;
        head1.right = head3;
        head2.left = head4;
        head2.right = head5;
        head5.left = head6;
        String res = morrisPre(head1);
        System.out.println(res);
        String res1 = morrisIn(head1);
        System.out.println(res1);
        String res2 = morrisPos(head1);
        System.out.println(res2);

        String s1 = "abcdef";
        String s2 = "def";
        System.out.println(kmp(s1, s2));

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

        System.out.println(isSubTree(t2, t1));
    }
}
