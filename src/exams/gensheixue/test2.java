package exams.gensheixue;

import java.util.LinkedList;

public class test2 {
    class Node{
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Node mergeTwoLists(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return null;
        } else if (n1 == null) {
            return n2;
        }else if (n2 == null){
            return n1;
        }

        LinkedList<Node> helpList = new LinkedList<>();
        //使用merge的形式向列表中放节点
        Node p1 = n1;
        Node p2 = n2;
        while (n1 != null && n2 != null) {
            if (n1.data < n2.data) {
                helpList.addLast(n1);
                n1 = n1.next;
            }else {
                helpList.addLast(n2);
                n2 = n2.next;
            }
        }
        //有没走完的
        while (n1 != null) {
            helpList.addLast(n1);
            n1 = n1.next;
        }
        while (n2 != null) {
            helpList.addLast(n2);
            n2 = n2.next;
        }
        Node dummy = new Node(0, helpList.get(0));
        Node res = dummy;
        while (!helpList.isEmpty()) {
            dummy.next = helpList.removeFirst();
            dummy = dummy.next;
        }
        return res.next;
    }
}
