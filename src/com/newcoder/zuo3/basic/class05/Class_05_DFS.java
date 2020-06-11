package com.newcoder.zuo3.basic.class05;

import java.util.HashSet;
import java.util.Stack;

public class Class_05_DFS {
    //深度优先遍历
    //使用一个栈与一个set实现,set表示node是否注册过
    public static void dfs(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> set = new HashSet<Node>();
        stack.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node node1 = stack.pop();
            for (Node next : node1.nexts) {
                if (!set.contains(next)) {
                    stack.push(node1);
                    stack.push(next);
                    set.add(node1);
                    System.out.println(next.value);
                    break;
                    //node1 = stack.peek();不能这么写,这样写会造成修改冲突(在遍历的时候进行修改),而且与写while的初衷冲突,有了while就不需要这个了
                }
            }
        }

    }
}
