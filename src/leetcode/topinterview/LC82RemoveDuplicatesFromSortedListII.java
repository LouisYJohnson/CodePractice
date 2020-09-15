package leetcode.topinterview;

public class LC82RemoveDuplicatesFromSortedListII {
    //给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
    //
    //示例 1:
    //
    //输入: 1->2->3->3->4->4->5
    //输出: 1->2->5
    //示例 2:
    //
    //输入: 1->1->1->2->3
    //输出: 2->3
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode help = new ListNode(-1);
            help.next = head;
            ListNode pre = help;
            ListNode cur = head;
            boolean flag = false;
            while (cur != null) {
                flag = false;
                while (cur.next != null && cur.val == cur.next.val) {
                    flag = true;
                    cur = cur.next;
                }
                if (flag) {
                    pre.next = cur.next;
                }else {
                    pre = cur;
                }
                cur = cur.next;
            }
            return help.next;
        }
    }

    //这种方法只是去除重复的数字,而不是把重复的所有的都删掉
//    class Solution {
//        public ListNode deleteDuplicates(ListNode head) {
//            ListNode help = new ListNode(-1);
//            help.next = head;
//            ListNode pre = help;
//            ListNode cur = head;
//
//            while (cur != null) {
//                if (pre.val == cur.val) {
//                    cur = cur.next;
//                    pre.next = cur;
//                }else {
//                    pre = pre.next;
//                    cur = cur.next;
//                }
//            }
//            return help.next;
//        }
//    }
}
