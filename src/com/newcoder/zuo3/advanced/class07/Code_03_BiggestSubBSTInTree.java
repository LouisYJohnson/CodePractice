package com.newcoder.zuo3.advanced.class07;

public class Code_03_BiggestSubBSTInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //����һ�ö�������ͷ�ڵ�head�� ��֪���нڵ��ֵ����һ���� �����
    //���������������Ľڵ�������
    //һ���ڵ���Ҫ��Ϣ:
    //������Ӧ�������������������������С
    //������Ӧ�������������������������С
    //���������ǰ�ڵ�Ļ������������������С(�������Ҹ��Է�������������������Ϊ���������������Ҫ���ⷵ��ͷ,max,min)
    //�����С�п�������������,������,�����ǰ�����ǰ�ڵ�
    //���������ֿ���,��Ӧ�����ֿ���,������������������������˵�ǰ�ڵ�,��ô����Ҫ������������������������Ϊͷ�ڵ�������ǲ�������������,ͬʱ����������������������������������ֵ������С,���������������������������Сֵ�������
    //����Ҫ�Ѽ�����Ϣ��:
    //size(���������������С)
    //�����������������ͷ��(��һ������������ͷ�ڵ�)
    //�����������������ͷ��(��һ������������ͷ�ڵ�)
    //������ҵ����������������ͷ�ڵ㶼�ǵ�ǰ�ڵ�������ӽڵ�Ļ�,����������ֵС������,�Ҳ���Сֵ��������,��ô�����ڵ����ֿ���
    //����ÿ���ڵ㶼Ҫ�����ĸ���Ϣ:���������������Сsize,��Ӧ���������������ͷ��,�ڵ��Ӧ�����������е���Сֵ�����ֵ
    //��Ȼ����ֻ��Ҫmax����ֻ��Ҫmin�������ǵݹ����,���еĶ�����Ҫ����
    public static class ReturnData {
        private int max;
        private int min;
        private int size;
        private Node head;

        public ReturnData(int max, int min, int size, Node head) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.head = head;
        }
    }

    public static ReturnData process(Node head) {
        //base case
        if (head == null) return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, null);

        Node left = head.left;
        ReturnData leftData = process(left);
        Node right = head.right;
        ReturnData rightData = process(right);

        int includeItselfSize = 0;
        if (leftData.head == left
                && rightData.head == right
                && head.value > leftData.max
                && head.value < rightData.min) {
            includeItselfSize = leftData.size + 1 + rightData.size;
        }
        int p1 = leftData.size;
        int p2 = rightData.size;
        int maxSize = Math.max(Math.max(p1, p2), includeItselfSize);
        Node maxHead = p1 > p2 ? leftData.head : rightData.head;
        if (maxSize == includeItselfSize) maxHead = head;
        return new ReturnData(Math.max(Math.max(leftData.max, rightData.max), head.value),
                Math.min(Math.min(leftData.min, rightData.min), head.value),
                maxSize, maxHead);
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
        printTree(bst);
        ReturnData returnData = process(head);
        System.out.println(returnData.size);
    }
}
