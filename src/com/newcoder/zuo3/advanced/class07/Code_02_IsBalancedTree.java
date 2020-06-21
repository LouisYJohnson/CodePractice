package com.newcoder.zuo3.advanced.class07;

public class Code_02_IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //����һ�ö�������ͷ�ڵ�head�� �жϸ����Ƿ���ƽ�������
    public static class ReturnData {
        private int level;
        private boolean isB;

        public ReturnData(int level, boolean isB) {
            this.level = level;
            this.isB = isB;
        }
    }

    //���ص�������Ե��ǵ�ǰ��Node
    //�ݹ�:����ͷ�ڵ���ͷ�ڵ����ڵĲ�,������������,�ֱ��Ǹýڵ������һ��level(�����ƽ��������Ļ�,��������ֻ������ƽ���������level
    // �������,level���ֲ���)���Ըýڵ�Ϊͷ�ڵ��Ӧ�������ǲ���ƽ�������

    //�������е�һ���ڵ���Ҫ��Ϣ:
    //��ڵ��Ӧ�����ǲ���ƽ�������.����ڵ��Ӧ�����ﵽ��������
    //�ҽڵ��Ӧ�������ǲ���ƽ����������ҽڵ��Ӧ�����ﵽ��������
    //�����ظ��ϼ���Ӧ�����Լ��ǲ���ƽ����������Լ���Ӧ�������ﵽ��������
    public static ReturnData process(Node head, int level) {
        //base case
        if (head == null) return new ReturnData(level, true);

        //�ֱ�õ����������ڵ��Ӧ���������һ��level���������ӽڵ�Ϊͷ�ڵ�������ǲ���ƽ�������
        ReturnData headLeft = process(head.left, level + 1);
        ReturnData headRight = process(head.right, level + 1);
        //��������ӽڵ��Ӧ��������һ������ƽ�������,�Ǿ����嶼����ƽ�������
        if (!headLeft.isB || !headRight.isB) return new ReturnData(level, false);
        if (Math.abs(headLeft.level - headRight.level) > 1) {
            return new ReturnData(level, false);
        }
        return new ReturnData(Math.max(headLeft.level, headRight.level), true);
    }

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
