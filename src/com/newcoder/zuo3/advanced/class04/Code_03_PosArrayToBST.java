package com.newcoder.zuo3.advanced.class04;

public class Code_03_PosArrayToBST {
    //���ݺ��������ؽ�����������
    //����Ŀ��
    //����һ����������arr�� ��֪����û���ظ�ֵ�� �ж�arr�Ƿ�����ǽ�
    //��ֵ����Ϊ���͵�������������������Ľ����
    //���ף� �����������arr��û���ظ�ֵ�� ����֪��һ�������������ĺ�
    //���������� ͨ������arr�ع���������
    //��������ʱ�����ڿ���ٱ߽������,�п���û���������������������������ô���Լ�����ж���û����������
    //�ǽ���:
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        //�ݹ麯��,����Ϊ�����������ϴ�0��arr.length - 1��Χ�ϵ���������ǲ��Ƕ������ĺ���������
        return process(arr, 0, arr.length - 1);
    }

    public static boolean process(int[] arr, int start, int end) {
        //base case
        if (start == end) return true;
        //����������������С���Ӵ�,�������Ӧ���Ƿ�Ϊ��������ͷ�ڵ������,С�Ľڵ�����ǰ��,��Ľڵ����м�
        //small��big��ʾС�Ľڵ�����ұ߽�,���Ľڵ������߽�
        //���������ɺ�,С�ڵ�����ұ߽����ڵ������߽��1˵���ǶԵ�,�����ֱ��ж�С�ڵ����ڵ��Ӧ������
        //���С�ڵ㳬���˴�ڵ�,ֱ�ӷ���false
        int small = -1;
        int big = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                small = i;
            } else {//��Ϊ������û���ظ�����,����else��ʾ��ǰarr[i]��arr[end]��
                //big��ֵΪ��һ�γ��ִ�ֵ����߽�,�������ִ�ֵ������,�����ִ�ֵ������
                big = big == end ? i : big;
            }
        }
        if (small > big) return false;
        if (small == -1 || end == big) {//û������������û��������
            return process(arr, start, end - 1);
        }
        //�����������������,�ֱ��ж���������
        return process(arr, start, small) && process(arr, big, end - 1);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //����
//    ���ף� �����������arr��û���ظ�ֵ�� ����֪��һ�������������ĺ�
//    ���������� ͨ������arr�ع���������
    public static Node posArrayToBST(int[] posArr) {
        if (posArr == null || posArr.length == 0) return null;
        return process1(posArr, 0, posArr.length - 1);
    }

    //�ݹ麯������:��һ����ʾ���������������������ʼ�����ص�,������������ж�Ӧ������������ͷ�ڵ�
    public static Node process1(int[] arr, int start, int end) {
        //base case
        if (start == end) return new Node(arr[start]);
//        if (start > end) return null;

        Node head = new Node(arr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
//        head.left = process1(arr,start,less);
//        head.right = process1(arr,more,end - 1);
        if (less == -1 && more != end) {//����������������
            head.left = null;
            head.right = process1(arr, more, end - 1);
        } else if (less != -1 && more == end) {//����������������
            head.left = process1(arr, start, end - 1);
            head.right = null;
        } else {//������������
            head.left = process1(arr, start, less);
            head.right = process1(arr, more, end - 1);
        }
        return head;
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
        int[] arr = {2, 1, 3, 6, 5, 7, 4};
        System.out.println(process(arr, 0, arr.length - 1));
        printTree(posArrayToBST(arr));

    }
}
