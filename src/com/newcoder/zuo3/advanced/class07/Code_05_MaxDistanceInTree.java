package com.newcoder.zuo3.advanced.class07;

public class Code_05_MaxDistanceInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //���������ɽڵ�a���ϻ��������ߵ��ڵ�b�� ���·���Ͻڵ����������a��b�ľ��롣
    //����һ�ö�������ͷ�ڵ�head�� ���������е������롣

    //����������:
    //1.���·�������������������ϵ����·��
    //2.ͬ�����·�������������������ϵ����·��
    //3.������Ϊ����������ڵ㵽��ǰ�ڵ㵽����������ڵ�

    //����������,maxDistan����ýڵ��Ӧ�������е�������,h��ʾ����ڵ㵽������Ͷ˵�������
    public static class ReturnData {
        private int maxDistance;
        private int h;

        public ReturnData(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }

    public static ReturnData process(Node head) {
        //base case
        if (head == null) return new ReturnData(0, 0);

        ReturnData left = process(head.left);
        ReturnData right = process(head.right);
        int includeHeadDistance = left.h + 1 + right.h;
        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        //���Ϸ��ظ��ϲ����ϢΪ�·�����������(��Ϊ����������,�������ϴ��ݵ�ʱ���������������������Ҳ����+1)
        int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
        int highest = Math.max(p1, p2) + 1;
        return new ReturnData(resultDistance, highest);
    }

    public static int maxDistance1(Node head) {
        if (head == null) return 0;
        return process(head).maxDistance;
    }

    public static int posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record);
        int maxfromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];
        int curNodeMax = maxfromLeft + maxFromRight + 1;
        record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }

    public static int maxDistance(Node head) {
        int[] record = new int[1];
        return posOrder(head, record);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(maxDistance(head1));
        System.out.println(maxDistance1(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(maxDistance(head2));
        System.out.println(maxDistance1(head2));

    }
}
