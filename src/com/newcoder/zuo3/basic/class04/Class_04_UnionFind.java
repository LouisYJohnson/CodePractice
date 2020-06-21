package com.newcoder.zuo3.basic.class04;

import java.util.HashMap;
import java.util.List;

public class Class_04_UnionFind {
    public static class Node {
        // whatever you like
    }

    //并查集
    //功能:
    //  1:isSameSet(Node a,Node b):查看两个节点是否属于同一个集合(代表节点是否是同一个),其实就是findFather返回对象内存地址一样
    //  2:union(Node a,Node b) :将两个节点所属的集合合并成同一个集合(如果代表节点不是同一个)
    //实现:两个HashMap
    //fatherMap:key为当前节点,value为该节点的父节点
    //rankMap:key为代表节点,value为以该节点为代表节点的集合中有多少个节点
    // 内部需要做到以下方法:
    //findFather:找到当前节点所在集合的代表节点,并在查找节点后将该节点通往代表节点的节点的父节点都设置为代表节点
    //makeSets:相当于初始化,将List中的所有Node各自成集合(即自己的父节点为自身,并将对应的rankMap值设为1)
    //union:将两个节点所属的集合合并成同一个集合(如果代表节点不是同一个)
    public static class DisjointSets {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> rankMap;

        public DisjointSets() {
            this.fatherMap = new HashMap<Node, Node>();
            this.rankMap = new HashMap<Node, Integer>();
        }

        //相当于初始化,将List中的所有Node各自成集合(即自己的父节点为自身,并将对应的rankMap值设为1)
        public void makeSets(List<Node> nodes) {
            if (nodes.isEmpty()) return;
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        //找到当前节点所在集合的代表节点,并在查找节点后将该节点通往代表节点的节点的父节点都设置为代表节点
        public Node findFather(Node n) {
            //获取输入节点的父节点
            Node father = fatherMap.get(n);
            //递归查找代表节点,并将代表节点返回
            if (father != n) {
                father = findFather(father);
            }
            //将代表节点作为每一个当前节点的父节点
            fatherMap.put(n, father);
            return father;
        }

        public Node findFather1(Node node) {
            Node father = node;
            Node help = null;
            while (fatherMap.get(father) != father) {
                father = fatherMap.get(father);
            }
            while (fatherMap.get(node) != father) {
                //help和node是两个节点
                //help表示当前节点的父节点
                //如果只使用一个变量来做,会找不到当前节点的父节点或无法设置当前节点的父节点为公共的父
                help = fatherMap.get(node);
                fatherMap.put(node, father);
                node = help;
            }
            return father;
        }

        //将两个节点所属的集合合并成同一个集合(如果代表节点不是同一个)
        public void union(Node a, Node b) {
            if (a == null || b == null) return;
            Node fatherA = findFather(a);
            Node fatherB = findFather(b);
            //如果代表节点不是同一个,将集合中节点数较小的代表节点挂在节点数较大的代表节点上
            if (fatherA != fatherB) {
                int fatherASize = rankMap.get(fatherA);
                int fatherBSize = rankMap.get(fatherB);
                if (fatherASize <= fatherBSize) {
                    //A尺寸小于B,A挂在B下面,并更新变大的集合的代表节点的尺寸
                    //之前的尺寸不用管了,因为findFather找不到那些已经存在rankMap中但是又不做代表节点的节点了
                    fatherMap.put(fatherA, fatherB);
                    rankMap.put(fatherB, fatherASize + fatherBSize);
                } else {
                    fatherMap.put(fatherB, fatherA);
                    rankMap.put(fatherA, fatherASize + fatherBSize);
                }
            }

        }
    }

}
