package myCodePractice.zuo.basic.class06;

import java.util.Stack;

public class Morris {
    //Morris����������
    //�������������Ľڵ�����2��
    //����û���������Ľڵ�����1��
    //Morris������Ϊ�ȷ��ʵ�ǰ�ڵ�,�ٷ��ʵ�ǰ�ڵ���������ٻص���ǰ�ڵ��ٷ��ʵ�ǰ�ڵ��������
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //ǰ��
    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        //�����ǰ�ڵ�û��������,cur������(Ψһ1�η���û���������Ľڵ�)
        //�����ǰ�ڵ���������,�������������ҽڵ㲢������ҽڵ���Һ��ӷ��������
        //  1.������ҽڵ���Һ���Ϊnull,����ָ��cur����cur����(��1�η���cur)
        //  2.������ҽڵ���Һ���ָ����cur,�����Ϊָ��null����cur����(��2�η���cur)
        //ֱ��curָ��null,����Morris����
        while (cur != null) {
            //���cur��������
            if (cur.left != null) {
                //�������������ҽڵ㲢�ж�
                help = cur.left;
                //һ��Ҫ���Ϻ����Ǹ�����,������ӵĻ�,�ҵ��ľ��Ǵ���������������ҽڵ�(��1�ν����ҽڵ���Һ�������Ϊcur��,�ڶ����Ҿͻ��)
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                //��������������ҽڵ���Һ���Ϊnull,˵����1�η���������������Ľڵ�(��ӡ)
                if (help.right == null) {
                    help.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                } else {    //����˵����ǰ�����������ҽڵ���Һ���Ϊָ����cur,˵����2�η��ʵ�������������Ľڵ�(����ӡ)
                    help.right = null;
                    cur = cur.right;
                }
            } else { //���curû��������,ֻ�����1��û���������Ľڵ�(��ӡ)
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }


    //����
    public static void morrisMid(Node head) {
        if (head == null) return;
        Node cur = head;
        Node help = null;
        //�����ǰ�ڵ�û��������,cur����(Ψһ1�η���û���������ĵ�)
        //�����ǰ�ڵ���������,��cur�����������ҽڵ㲢������ҽڵ���Һ��ӷ��������
        //  1.�����������ҽڵ���Һ���ָ��null,���Һ��Ӹ�Ϊָ��cur,����cur����(��1�η������������Ľڵ�)
        //  2.���������ҽڵ���Һ���ָ��cur,���Һ��Ӹ�Ϊָ��null,����cur����(��2�� �������������Ľڵ�)
        //Morris������Ϊ�ȷ��ʵ�ǰ�ڵ�,�ٷ��ʵ�ǰ�ڵ��������,�ٻص���ǰ�ڵ��ٷ��ʵ�ǰ�ڵ��������
        //ֱ��curָ��null��ʱ��ֹͣMorris����
        while (cur != null) {
            if (cur.left != null) {
                help = cur.left;
                while (help.right != null && help.right != cur) {
                    help = help.right;
                }
                if (help.right == null) {
                    help.right = cur;
                    cur = cur.left;
                } else {
                    System.out.println(cur.value);
                    cur = cur.right;
                    help.right = null;
                }
            } else {
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    //����
    //�����ǰ����,�����һ��
    //ÿ���ڶ��η���cur��ʱ��,��cur�����������ұ߽������ӡ(�ǵ��Ƚ�mostRight.right = nullȻ����ܴ�ӡ)
    //�������������ʱ��(curָ��null),�����������ұ߽絥����ӡ(������,��Ϊÿ�ε�2���������������Ľڵ��ʱ���Ǵ�ӡ���������ұ߽�
    // �������ӡ������������Ľڵ�)
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
                } else {
                    help.right = null;
                    printRightBoundReverse(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printRightBoundReverse(head);

    }

    public static void printRightBoundReverse(Node head) {
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
        head1.left = head2;
        head1.right = head3;
        head2.left = head4;
        head2.right = head5;
        head5.left = head6;
//        morrisPre(head1);
//        morrisMid(head1);
        morrisPos(head1);


    }
}
