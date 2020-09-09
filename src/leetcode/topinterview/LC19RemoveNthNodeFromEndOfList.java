package leetcode.topinterview;

public class LC19RemoveNthNodeFromEndOfList {
    //给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
    //示例：
    //给定一个链表: 1->2->3->4->5, 和 n = 2.
    //当删除了倒数第二个节点后，链表变为 1->2->3->5.
    //说明：
    //给定的 n 保证是有效的。
    //进阶：
    //你能尝试使用一趟扫描实现吗？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            //首先定义一个新的节点,这个节点指向链表的头部,返回的结果就是这个新节点的next
            //这么做是为了防止删除的是第一个节点
            //定义两个指针,都指向新节点,一个指针先走n+1步,然后两个指针一起走,当先走的那个指针走到为null
            //后面走的那个指针就在要删除的那个节点的前一个
            //为什么是前一个?这样更方便删除节点,否则就要再引入一个节点了,麻烦
            if (head == null) {
                return head;
            }


            ListNode help = new ListNode(0);
            ListNode slow = help;
            ListNode fast = help;
            help.next = head;

            for (int i = 0; i < n + 1; i++) {
                fast = fast.next;
            }

            //终结条件是fast==null,也就是说,会走到fast==null才会停止
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;
            return help.next;
        }
    }
}
