package com.newcoder.zuo3.advanced.class03;

import java.util.LinkedList;
import java.util.Queue;

public class Code_02_SerializeAndReconstructTree {
    //二叉树的序列化和反序列化
    //【题目】
    //二叉树被记录成文件的过程叫作二叉树的序列化， 通过文件内
    //容重建原来二叉树的过程叫作二叉树的反序列化。 给定一棵二
    //叉树的头节点head， 并已知二叉树节点值的类型为32位整型。
    //请设计一种二叉树序列化和反序列化的方案， 并用代码实现。
    //【要求】
    //1， 实现先序遍历序列化与反序列化
    //2， 实现按层遍历序列化与反序列化
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //1， 实现先序遍历序列化与反序列化
    //如何序列化就如何反序列化
    //序列化:
    //此递归函数返回输入的head节点的序列化字符串
    public static String serialByPre(Node head) {
        //base case
        if (head == null) return "#!";

        String res = String.valueOf(head.value) + "!";
        //分别序列化左右子树,并加到结果上,确定了功能之后直接按照确定的功能去用,一定没错!
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //反序列化,怎么序列化的就怎么反序列化
    public static Node reconByPreString(String preStr) {
        String[] strings = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int index = 0; index < strings.length; index++) {
            queue.add(strings[index]);
        }
        return reconPreOrder(queue);
    }
    //递归函数:
    //功能:给一个队列,返回队列中的元素对应的反序列化结果
    private static Node reconPreOrder(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        Node head = new Node(Integer.valueOf(s));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    //2， 实现按层遍历序列化与反序列化
    //其实就是BFS的改写,因为这里是二叉树,不用考虑是否会回来的问题,就不需要set,只用一个队列即可
    public static String serialByLevel(Node head) {
        if (head == null) return "#!";
        String res = String.valueOf(head.value) + "!";
        Queue<Node> queue = new LinkedList<Node>();
        //先在队列中加入头节点,然后每次从队头弹出一个元素,并将弹出元素的左右子加入队列,加入队列的元素是要被打印的元素
        queue.add(head);
        while (!queue.isEmpty()) {
            Node help = queue.poll();
            if (help.left != null) {
                queue.add(help.left);
                res += String.valueOf(help.left.value) + "!";
            }else {
                res += "#!";
            }
            if (help.right != null) {
                queue.add(help.right);
                res += String.valueOf(help.right.value) + "!";
            }else {
                res += "#!";
            }
        }
        return res;
    }

    //如何序列化的就怎么反序列化
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
