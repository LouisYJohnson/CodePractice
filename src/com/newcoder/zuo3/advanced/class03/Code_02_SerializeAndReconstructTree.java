package com.newcoder.zuo3.advanced.class03;

import java.util.LinkedList;
import java.util.Queue;

public class Code_02_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static String serialByPre(Node head) {
        //base case
        if (head == null) return "#!";

        String res = String.valueOf(head.value) + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr) {
        String[] strings = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int index = 0; index < strings.length; index++) {
            queue.add(strings[index]);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        Node head = new Node(Integer.valueOf(s));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    public static String serialByLevel(Node head) {
        if (head == null) return "#!";
        String res = String.valueOf(head.value) + "!";
        Queue<Node> queue = new LinkedList<Node>();
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
