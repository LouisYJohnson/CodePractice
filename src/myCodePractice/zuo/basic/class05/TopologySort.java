package myCodePractice.zuo.basic.class05;


import java.util.*;

public class TopologySort {
    //拓扑排序
    public static class Node {
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

    public static class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static ArrayList<Node> topologySort(Graph graph) {
        if (graph == null) return null;

        HashMap<Node, Integer> inMap = new HashMap<>();
        ArrayList<Node> result = new ArrayList<>();
        Queue<Node> zeroInQueue = new LinkedList<>();

        //遍历图结构,得到图中所有点的入度
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        Node temp = null;
        while (!zeroInQueue.isEmpty()) {
            temp = zeroInQueue.poll();
            result.add(temp);
            for (Node next : temp.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
