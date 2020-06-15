package com.newcoder.zuo3.basic.class05;


import java.util.HashSet;
import java.util.Stack;

public class Class_05_DFS {
    //深度优先遍历
    //使用一个栈与一个set实现,set表示node是否注册过
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
