package leetcode.median;

import java.util.HashSet;

public class LC9LinkedListCycleII {
    //题目描述
    //对于一个给定的链表，返回环的入口节点，如果没有环，返回null
    //拓展：
    //你能给出不利用额外空间的解法么？
    //
    //Given a linked list, return the node where the cycle begins. If there is no cycle, returnnull.
    //Follow up:
    //Can you solve it without using extra space?

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //判断是否成环,如果成环,返回第一个入环节点loop:
    //判断是否成环有两种方法,1:构建哈希表并遍历,找到重复的,就是入环节点,否则,没环
    //2.快慢指针法,慢指针走一步,快指针走两步,如果快指针走到null,证明没有环,如果有环,
    //在判断是否有环的时候快慢指针的起点都在头部,如果是将链表分成两半,慢的在头部,快的在头部的下一个
    //则快指针一定能和慢指针正好走到一起,此时让快指针回到头指针,并让快指针和慢指针都每次走一步,
    //则最终快慢指针相遇在第一个入环点
    public class Solution {
        public ListNode detectCycle(ListNode head) {
//            if (head == null) return null;
//            //快慢指针法(不通过,超时):
//            ListNode slowNode = head;
//            ListNode fastNode = head;
//            while (slowNode.next != null && fastNode.next.next != null) {
//                slowNode = slowNode.next;
//                fastNode = fastNode.next.next;
//                if (slowNode == fastNode) {
//                    fastNode = head;
//                    while (fastNode != slowNode) {
//                        fastNode = fastNode.next;
//                        slowNode = slowNode.next;
//                        if (fastNode == slowNode) return fastNode;
//                    }
//                }
//            }
//            return null;
            //hashset法
            if (head == null) return null;
            HashSet<ListNode> helpSet = new HashSet<>();
            helpSet.add(head);
            while (head.next != null) {
                head = head.next;
                boolean isSuccess = helpSet.add(head);
                if (!isSuccess) return head;
            }
            return null;
        }
    }


}
