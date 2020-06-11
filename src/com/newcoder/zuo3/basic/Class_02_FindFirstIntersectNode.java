package com.newcoder.zuo3.basic;

public class Class_02_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //两个单链表相交的一系列问题
    //三种情况:
    //1.两个不成环的单向列表:要么相交并合二为一,要么不相交
    //2.一个成环,一个不成环:不可能相交
    //3.两个都成环:分三种情况:两个环不相交,两个环在外合二为一后成环,两个在外没相交,共享一个环
    //所以要先从分环入手,先判断是否有环,再判断相交的情况

    //判断是否成环,如果成环,返回第一个入环节点loop:
    //判断是否成环有两种方法,1:构建哈希表并遍历,找到重复的,就是入环节点,否则,没环
    //2.快慢指针法,慢指针走一步,快指针走两步,如果快指针走到null,证明没有环,如果有环,
    //则快指针一定能和慢指针正好走到一起,此时让快指针回到头指针,并让快指针和慢指针都每次走一步,
    //则最终快慢指针相遇在第一个入环点
    public static Node getLoopNode(Node node) {
        if (node == null) return null;
        Node nodeStart = node;
        Node nodeSlow = node;
        Node nodeFast = node;

        while (nodeFast.next != null && nodeFast.next.next != null) {
            nodeSlow = nodeSlow.next;
            nodeFast = nodeFast.next.next;
            if (nodeSlow == nodeFast) {
                nodeFast = node;
                while (nodeFast != nodeSlow) {
                    nodeFast = nodeFast.next;
                    nodeSlow = nodeSlow.next;
                    if (nodeFast == nodeSlow) return nodeFast;
                }
            }
        }
        //不满足循环条件,证明无环
        return null;
    }

    //如果两个都没环:
    //如果相交,则分别定义head1,head2,end1,end2,len1,len2:
    //end1=end2,使用len1与len2之间的差值来判断相交点在哪里
    public static Node noLoop(Node head1, Node head2) {
        int len1 = 0;
        int len2 = 0;
        Node end1 = head1;
        Node end2 = head2;

        while (end1.next != null) {
            len1++;
            end1 = end1.next;
        }
        while (end2.next != null) {
            len2++;
            end2 = end2.next;
        }
        if (end1 != end2) return null;
        int indexSud = Math.abs(len1 - len2);
        //让较大的那个先走indexSud步
        Node firstGoNode = len1 > len2 ? head1 : head2;
        Node lastGoNode = len1 > len2 ? head2 : head1;
        for (int i = 0;i < indexSud;i ++) {
            firstGoNode = firstGoNode.next;
        }
        while (firstGoNode != lastGoNode) {
            firstGoNode = firstGoNode.next;
            lastGoNode = lastGoNode.next;
        }
        return firstGoNode;
    }

    //如果两个都有环,分为三种情况
    //两个环不相交,两个环在外合二为一后成环,两个在外没相交,共享一个环
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        //两个环在外合二为一是一种情况
        //另外两种情况实际上可以放在一起判断
        //先判断两个环在外合二为一:
        if (loop1 == loop2) {
            int len1 = 0;
            int len2 = 0;
            Node helpHead1 = head1;
            Node helpHead2 = head2;
            Node end1 = loop1;
            Node end2 = loop2;

            while (helpHead1 != end1) {
                len1++;
                helpHead1 = helpHead1.next;
            }
            while (helpHead2 != end2) {
                len2++;
                helpHead2 = helpHead2.next;
            }
            int indexSud = Math.abs(len1 - len2);
            //让较大的那个先走indexSud步
            Node firstGoNode = len1 > len2 ? head1 : head2;
            Node lastGoNode = len1 > len2 ? head2 : head1;
            for (int i = 0;i < indexSud;i ++) {
                firstGoNode = firstGoNode.next;
            }
            while (firstGoNode != lastGoNode) {
                firstGoNode = firstGoNode.next;
                lastGoNode = lastGoNode.next;
            }
            return firstGoNode;
        }
        //两个环不相交,两个在外没相交,共享一个环
        Node helpNode = loop1.next;
        while (helpNode != loop1 ) {
            if (helpNode == loop2) {
                return loop1;//共享一个环
            }
            helpNode = helpNode.next;
        }
        return null;//不相交
    }
    //用来判断是哪种情况
    public static Node getIntersectNode(Node head1, Node head2) {
        //先判断是否有环:
        Node loop1 = null;
        Node loop2 = null;
        loop1 = getLoopNode(head1);
        loop2 = getLoopNode(head2);
        //如果两个都无环:
        if (loop1 == null && loop2 == null) {
            return noLoop(head1,head2);
        }else if ((loop1 == null && loop2 != null) || (loop1 != null && loop2 == null)) {//如果一个有环一个无环
            return null;
        }else if (loop1 != null && loop2 != null) {//两个都有环
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
