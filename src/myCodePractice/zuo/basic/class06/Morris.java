package myCodePractice.zuo.basic.class06;

import java.util.Stack;

public class Morris {
    //Morris遍历二叉树
    //对于有左子树的节点会访问2次
    //对于没有左子树的节点会访问1次
    //Morris的流程为先访问当前节点,再访问当前节点的左子树再回到当前节点再访问当前节点的右子树
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //前序
    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        //如果当前节点没有左子树,cur往右移(唯一1次访问没有左子树的节点)
        //如果当前节点有左子树,找左子树的最右节点并针对最右节点的右孩子分情况讨论
        //  1.如果最右节点的右孩子为null,则将其指向cur并让cur左移(第1次访问cur)
        //  2.如果最右节点的右孩子指向了cur,则将其改为指向null并让cur右移(第2次访问cur)
        //直到cur指向null,结束Morris过程
        while (cur != null) {
            //如果cur有左子树
            if (cur.left != null) {
                //找左子树的最右节点并判断
                help = cur.left;
                //一定要加上后面那个条件,如果不加的话,找到的就是错误的左子树的最右节点(第1次将最右节点的右孩子设置为cur了,第二次找就会错)
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                //如果左子树的最右节点的右孩子为null,说明第1次访问这个有左子树的节点(打印)
                if (help.right == null) {
                    help.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                } else {    //否则说明当前左子树的最右节点的右孩子为指向了cur,说明第2次访问到这个有左子树的节点(不打印)
                    help.right = null;
                    cur = cur.right;
                }
            } else { //如果cur没有左子树,只会访问1次没有左子树的节点(打印)
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }


    //中序
    public static void morrisMid(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        //如果当前节点没有左子树,cur右移(唯一1次访问没有左子树的点)
        //如果当前节点有左子树,找cur左子树的最右节点并针对最右节点的右孩子分情况讨论
        //  1.左子树的最右节点的右孩子指向null,则将右孩子改为指向cur,并将cur左移(第1次访问有左子树的节点)
        //  2.左子树最右节点的右孩子指向cur,则将右孩子改为指向null,并将cur右移(第2次 访问有左子树的节点)
        //Morris的流程为先访问当前节点,再访问当前节点的左子树,再回到当前节点再访问当前节点的右子树
        //直到cur指向null的时候停止Morris过程
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    cur = cur.left;
                }else {
                    System.out.println(cur.value);
                    cur = cur.right;
                    help.right = null;
                }
            }else {
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    //后序
    //相较于前两个,这个不一样
    //每当第二次访问cur的时候,将cur的左子树的右边界逆序打印(记得先将mostRight.right = null然后才能打印)
    //整棵树遍历完的时候(cur指向null),将整棵树的右边界单独打印(包括根,因为每次第2次碰到有左子树的节点的时候都是打印左子树的右边界
    // 并不会打印这个有左子树的节点)
    public static void morrisPos(Node head) {
        if (head == null) return;

        Node cur = head;
        Node help = null;
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    cur = cur.left;
                }else {
                    help.right = null;
                    printRightBoundReverse(cur.left);
                    cur = cur.right;
                }
            }else {
                cur = cur.right;
            }
        }
        printRightBoundReverse(head);

    }

    public static void printRightBoundReverse (Node head) {
        if (head == null) return;

        Stack<Node> helpStack = new Stack<>();
        while (head != null) {
            helpStack.push(head);
            head = head.right;
        }
        while (!helpStack.isEmpty()) {
            System.out.println(helpStack.pop().value);
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        Node head6 = new Node(6);
        head1.left = head2; head1.right = head3;
        head2.left = head4; head2.right = head5;
        head5.left = head6;
//        morrisPre(head1);
//        morrisMid(head1);
        morrisPos(head1);


    }
}
