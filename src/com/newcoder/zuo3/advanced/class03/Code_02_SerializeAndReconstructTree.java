package com.newcoder.zuo3.advanced.class03;

import java.util.LinkedList;
import java.util.Queue;

public class Code_02_SerializeAndReconstructTree {
    //�����������л��ͷ����л�
    //����Ŀ��
    //����������¼���ļ��Ĺ��̽��������������л��� ͨ���ļ���
    //���ؽ�ԭ���������Ĺ��̽����������ķ����л��� ����һ�ö�
    //������ͷ�ڵ�head�� ����֪�������ڵ�ֵ������Ϊ32λ���͡�
    //�����һ�ֶ��������л��ͷ����л��ķ����� ���ô���ʵ�֡�
    //��Ҫ��
    //1�� ʵ������������л��뷴���л�
    //2�� ʵ�ְ���������л��뷴���л�
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //1�� ʵ������������л��뷴���л�
    //������л�����η����л�
    //���л�:
    //�˵ݹ麯�����������head�ڵ�����л��ַ���
    public static String serialByPre(Node head) {
        //base case
        if (head == null) return "#!";

        String res = String.valueOf(head.value) + "!";
        //�ֱ����л���������,���ӵ������,ȷ���˹���֮��ֱ�Ӱ���ȷ���Ĺ���ȥ��,һ��û��!
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //�����л�,��ô���л��ľ���ô�����л�
    public static Node reconByPreString(String preStr) {
        String[] strings = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int index = 0; index < strings.length; index++) {
            queue.add(strings[index]);
        }
        return reconPreOrder(queue);
    }

    //�ݹ麯��:
    //����:��һ������,���ض����е�Ԫ�ض�Ӧ�ķ����л����
    private static Node reconPreOrder(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        Node head = new Node(Integer.valueOf(s));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    //2�� ʵ�ְ���������л��뷴���л�
    //��ʵ����BFS�ĸ�д,��Ϊ�����Ƕ�����,���ÿ����Ƿ�����������,�Ͳ���Ҫset,ֻ��һ�����м���
    public static String serialByLevel(Node head) {
        if (head == null) return "#!";
        String res = String.valueOf(head.value) + "!";
        Queue<Node> queue = new LinkedList<Node>();
        //���ڶ����м���ͷ�ڵ�,Ȼ��ÿ�δӶ�ͷ����һ��Ԫ��,��������Ԫ�ص������Ӽ������,������е�Ԫ����Ҫ����ӡ��Ԫ��
        queue.add(head);
        while (!queue.isEmpty()) {
            Node help = queue.poll();
            if (help.left != null) {
                queue.add(help.left);
                res += String.valueOf(help.left.value) + "!";
            } else {
                res += "#!";
            }
            if (help.right != null) {
                queue.add(help.right);
                res += String.valueOf(help.right.value) + "!";
            } else {
                res += "#!";
            }
        }
        return res;
    }

    //������л��ľ���ô�����л�
    public static Node reconByLevelString(String levelStr) {
        String[] value = levelStr.split("!");
        int index = 0;
        Queue<Node> queue = new LinkedList<>();
        Node head = genergateNodeByString(value[index++]);
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            Node help = queue.poll();
            help.left = genergateNodeByString(value[index++]);
            help.right = genergateNodeByString(value[index++]);
            if (help.left != null) {
                queue.add(help.left);
            }
            if (help.right != null) {
                queue.add(help.right);
            }
        }
        return head;
    }

    public static Node genergateNodeByString(String value) {
        if ("#".equals(value)) return null;
        return new Node(Integer.valueOf(value));
    }

}
