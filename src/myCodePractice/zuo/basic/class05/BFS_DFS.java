package myCodePractice.zuo.basic.class05;

import com.newcoder.zuo3.basic.class05.Edge;
import com.newcoder.zuo3.basic.class05.Node;

import java.util.*;

public class BFS_DFS {

    public class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    //�ֱ�ʵ��BFS(������ȱ���)��DFS(������ȱ���)
    //���߶��ǽ�ͷ�ڵ��ȷ�����л�ջ��
    //��ͬ����BFS���ڽڵ��뿪queue��ʱ���ӡ,DFS���ڽڵ���set�в����ڵ�ʱ���ӡ
    public static void BFS(Node head) {
        //BFSʹ�ö��к�HashSetʵ��
        if (head == null) return;
        //����ע���Ѿ����ֵ�Node
        HashSet<Node> regist = new HashSet<>();
        //���ڰ�˳��װҪ��ӡ��Ԫ��
        Queue<Node> queue = new LinkedList<>();
        regist.add(head);
        queue.add(head);
        Node temp = null;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.println(temp.value);
            for (Node next : temp.nexts) {
                if (!regist.contains(next)) {
                    queue.add(next);
                    regist.add(next);
                }
            }
        }
    }

    public static void DFS(Node head) {
        //DFSʹ��ջ��HashSetʵ��
        if (head == null) return;

        HashSet<Node> regist = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.push(head);
        regist.add(head);
        System.out.println(head.value);

        Node temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            for (Node next : temp.nexts) {
                if (next != null) {
                    if (!regist.contains(next)) {
                        stack.push(temp);
                        stack.push(next);
                        regist.add(next);
                        System.out.println(next.value);
                        break;
                    }
                }
            }
        }
    }
}
