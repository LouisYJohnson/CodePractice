package leetcode.median;

public class LC130RemoveNthNodeFromEndOfList {
    //题目描述
    //给定一个链表，删除链表的倒数第n个节点并返回链表的头指针
    //例如，
    //   给出的链表为:1->2->3->4->5, n= 2.↵↵   删除了链表的倒数第n个节点之后,链表变为1->2->3->5.
    //备注：
    //题目保证n一定是合法的
    //请尝试只用一步操作完成该功能

    public class ListNode {
        int val;
        ListNode next = null;
    }

    public class Solution {
        /**
         * @param head ListNode类
         * @param n    int整型
         * @return ListNode类
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // write code here
            //使用两个指针,第一个指针指向第n个节点,第二个指针指向第1个节点
            //两个指针一起向前移动,第一个指针移动到尽头的时候,第二个指针正好在倒数第n个节点上
            //但是这里我们需要找到的应该是到数第n+1个节点,所以再引入一个pre指针指向到数第n个节点的前一个节点上
            if (head == null || n < 1) return null;

            //pre默认指向firstNode的前一个,因为firstNode一开始指向第一个节点而且不用移动,所以这里指向null
            ListNode firstNodePre = null;
            ListNode secondNode = head;
            ListNode firstNode = head;

            //从第1个挪到第2个需要一次next操作,也就是2-1,而不是2-1+1,所以<n即可
            for (int i = 1; i < n; i++) {
                secondNode = secondNode.next;
            }

            //如果先向前走了n个位置的指针动不了,那另外两个指针也别想动
            //否则,pre每次都指向firstNode的上一个
            while (secondNode.next != null) {
                firstNodePre = firstNode;
                firstNode = firstNode.next;
                secondNode = secondNode.next;
            }
            //此时firstNode在到数第n + 1个节点上
            //注意如果删除节点为头节点时,secondNode是null
            if (firstNodePre == null) {
                if (head.next != null) {    //如果head不是唯一节点,只删除head
                    ListNode helpNode = head;
                    head = head.next;
                    helpNode.next = null;
                }else { //是唯一节点,直接置空
                    head = null;
                }
            }else if (firstNodePre.next.next == null){ //如果删除节点为最后一个节点
                firstNodePre.next = null;
            }else {
                ListNode helpNode = firstNode.next;
                firstNode.next = null;
                firstNodePre.next = helpNode;
            }
            return head;
        }
    }


}
