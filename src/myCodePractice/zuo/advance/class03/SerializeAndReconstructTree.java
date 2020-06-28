package myCodePractice.zuo.advance.class03;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;
import org.omg.CORBA.NO_IMPLEMENT;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndReconstructTree {
    //二叉树的序列化和反序列化
    //【题目】
    //二叉树被记录成文件的过程叫作二叉树的序列化， 通过文件内
    //容重建原来二叉树的过程叫作二叉树的反序列化。 给定一棵二
    //叉树的头节点head， 并已知二叉树节点值的类型为32位整型。
    //请设计一种二叉树序列化和反序列化的方案， 并用代码实现。
    //【要求】
    //1， 实现先序遍历序列化与反序列化
    //2， 实现按层遍历序列化与反序列化

    //序列化反序列化:怎么序列化的就怎么反序列化
    //这个题说的是结构是如何保存的
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //序列化
    //前序序列化
    public static String serialByPre(Node head) {
        if (head == null) {
            return null;
        }

        return process1(head);
    }

    //递归函数功能,给头节点返回该节点对应的前序遍历结果的字符串
    public static String process1(Node head) {
        //base case
        if (head == null) {
            return "#_";
        }

        StringBuffer res = new StringBuffer();
        res.append(head.value + "_");
        res.append(process1(head.left));
        res.append(process1(head.right));
        return String.valueOf(res);
    }

    //中序序列化
    public static String serialByMid(Node head) {
        if (head == null) {
            return null;
        }

        return process2(head);
    }

    //递归函数功能:给头节点返回该节点对应的中序遍历结果的字符串
    public static String process2(Node head) {
        //base case
        if (head == null) {
            return "#_";
        }

        StringBuffer res = new StringBuffer();
        res.append(process2(head.left));
        res.append(head.value + "_");
        res.append(process2(head.right));
        return String.valueOf(res);
    }

    //后序序列化
    public static String serialByPos(Node head) {
        if (head == null) {
            return null;
        }

        return process3(head);
    }

    //递归函数功能:给头节点返回二叉树后序遍历的结果
    public static String process3(Node head) {
        //base case
        if (head == null) {
            return "#_";
        }

        StringBuffer res = new StringBuffer();
        res.append(process3(head.left));
        res.append(process3(head.right));
        res.append(head.value + "_");
        return String.valueOf(res);
    }

    //按层序列化
    public static String serialByLevel(Node head) {
        if (head == null) {
            return null;
        }

        StringBuffer res = new StringBuffer();
        LinkedList<Node> helpList = new LinkedList<>();
        helpList.addLast(head);
        res.append(head.value + "_");
        Node help = null;

        while (!helpList.isEmpty()) {
            //队列先进先出
            help = helpList.pollFirst();
            //分别检查左节点与右节点是否为空,如果不空,就加入到队列的后面与表示结果的字符串中
            //如果是空的,就不加到队列里面了将这两个空都放到表示结果的字符串中即可
            //这样就实现了按层遍历
            if (help.left != null) {
                res.append(help.left.value + "_");
                helpList.addLast(help.left);
            } else {
                res.append("#_");
            }
            if (help.right != null) {
                res.append(help.right.value + "_");
                helpList.addLast(help.right);
            } else {
                res.append("#_");
            }
        }
        return String.valueOf(res);
    }

    //反序列化
    //用前序序列化的字符串来还原这个序列
    public static Node reconstructTreeByPreString(String string) {
        if (string == null || string.length() == 0) return null;

        //将拿到的字符串按照之前序列化的分隔符还原成对应的元素
        String[] temp = string.split("_");
        //将数组中的元素放到一个队列中,然后开始递归
        LinkedList<String> helpQueue = new LinkedList<>();
        for (int i = 0; i < temp.length; i++) {
            helpQueue.addLast(temp[i]);
        }
        return process4(helpQueue);
    }

    //递归函数功能:
    //给一个表示前序序列化的队列,将反序列化的头节点返回
    public static Node process4(LinkedList<String> linkedList) {
        String temp = linkedList.pollFirst();
        if ("#".equals(temp)) return null;

        Node head = new Node(Integer.valueOf(temp));
        head.left = process4(linkedList);
        head.right = process4(linkedList);
        return head;
    }

    //将按层序列化的字符串反序列化
    public static Node reconstructTreeByLevel(String string) {
        if (string == null || string.length() == 0) return null;

        int index = 0;
        LinkedList<Node> helpQueue = new LinkedList<>();
        String[] helpStrs = string.split("_");
        Node head = generateNodeByStr(helpStrs[index++]);
        helpQueue.addLast(head);
        Node res = head;

        while (!helpQueue.isEmpty()) {
            head = helpQueue.pollFirst();
            Node left = generateNodeByStr(helpStrs[index++]);
            Node right = generateNodeByStr(helpStrs[index++]);
            if (left != null) {
                head.left = left;
                helpQueue.addLast(left);
            }
            if (right != null) {
                head.right = right;
                helpQueue.addLast(right);
            }
        }
        return res;
    }
    //将传来的字符串转换成对应的Node节点
    public static Node generateNodeByStr(String string) {
        if ("#".equals(string)) {
            return null;
        }else {
            return new Node(Integer.valueOf(string));
        }
    }

//    //用中序序列化的字符串来还原这个序列(不会)
//    public static Node reconstructTreeByMidString(String string) {
//        if (string == null || string.length() == 0) return null;
//
//        String[] temp = string.split("_");
//        LinkedList<String> helpQueue = new LinkedList<>();
//        for (int i = 0; i < temp.length; i++) {
//            helpQueue.addLast(temp[i]);
//        }
//        return process5(helpQueue);
//    }
//    //递归函数功能:
//    //给定使用中序序列化的队列,返回使用该队列重建的二叉树头节点
//    public static Node process5(LinkedList<String> linkedList) {
//        String help = linkedList.pollFirst();
//        if ("#".equals(help)) return null;
//
//        //从队列中出来的第一个是左子树?
//        Node headLeft = new Node(Integer.valueOf(help));
//        Node head = process4(linkedList);
//        Node headRight = process4(linkedList);
//        head.left = headLeft;
//        head.right = headRight;
//        return head;
//    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        head1.left = head2;
        head1.right = head3;
        head2.left = head4;
        head3.right = head5;

//        System.out.println(serialByPre(head1));
//        System.out.println(serialByMid(head1));
//        System.out.println(serialByPos(head1));
        System.out.println(serialByLevel(head1));
        Node help = reconstructTreeByLevel(serialByLevel(head1));
        System.out.println();
//        String serialByMid = serialByMid(head1);
//        System.out.println(serialByMid);
//        System.out.println();

    }
}
