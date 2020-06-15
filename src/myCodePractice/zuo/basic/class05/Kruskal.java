package myCodePractice.zuo.basic.class05;

import com.newcoder.zuo3.basic.class05.Edge;
import com.newcoder.zuo3.basic.class05.Graph;

import java.util.*;

public class Kruskal {
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
    //K算法
    public static Set<Edge> kruskalMST(Graph graph) {
        if (graph == null) return null;
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //小根堆,放边用
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(100, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        Edge tempEdge = null;
        Set<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            tempEdge = priorityQueue.poll();
            if (unionFind.findFather(tempEdge.from) != unionFind.findFather(tempEdge.to)) {
                unionFind.union(tempEdge.from, tempEdge.to);
                res.add(tempEdge);
            }
        }
        return res;
    }

    //实现并查集
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        //将所有Node重置为一个set一个Node
        public void makeSets(Collection<Node> nodeCollection) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodeCollection) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        //查找集合的父,并将沿途的Node的father都设置为最终的father
        public Node findFather(Node node) {
            Node father = node;
            while (fatherMap.get(father) != father) {
                father = fatherMap.get(father);
            }
            Node help = node;
            while (fatherMap.get(node) != father) {
                help = fatherMap.get(node);
                fatherMap.put(node, father);
                node = help;
            }
            return father;
        }

        //集合的合并
        public void union(Node node1, Node node2) {
            Node help1 = findFather(node1);
            Node help2 = findFather(node2);

            if (help1 == help2) return;

            int help1Size = sizeMap.get(help1);
            int help2Szie = sizeMap.get(help2);
            if (help1Size > help2Szie) {
                fatherMap.put(help2, help1);
                sizeMap.put(help1, help1Size + help2Szie);
            } else {
                fatherMap.put(help1, help2);
                sizeMap.put(help2, help1Size + help2Szie);
            }
        }
    }
}
