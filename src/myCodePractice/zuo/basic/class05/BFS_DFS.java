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

    //分别实现BFS(宽度优先遍历)与DFS(深度优先遍历)
    //二者都是将头节点先放入队列或栈中
    //不同的是BFS是在节点离开queue的时候打印,DFS是在节点在set中不存在的时候打印
    public static void BFS(Node head) {
        //BFS使用队列和HashSet实现
        if (head == null) return;
        //用于注册已经出现的Node
        HashSet<Node> regist = new HashSet<>();
        //用于按顺序装要打印的元素
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
        //DFS使用栈和HashSet实现
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
