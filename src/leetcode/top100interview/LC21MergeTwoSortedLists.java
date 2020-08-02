package leetcode.top100interview;

import java.util.ArrayList;
import java.util.List;

public class LC21MergeTwoSortedLists {
    //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
    //示例：
    //输入：1->2->4, 1->3->4
    //输出：1->1->2->3->4->4
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            } else if (l1 == null) {
                return l2;
            }else if (l2 == null){
                return l1;
            }
            //merge方法进行排序并组合链表
            //构建一个辅助ArrayList
            ArrayList<ListNode> helpList = new ArrayList<>();
            ListNode helpL1 = l1;
            ListNode helpL2 = l2;
            ListNode helpL1Next = l1.next;
            ListNode helpL2Next = l2.next;
            //这种放法,helpList中最后一个节点的next指针一定指向null,不必最后再去设置
            while (helpL1 != null && helpL2 != null) {
                if (helpL1.val < helpL2.val) {
                    helpList.add(helpL1);
                    helpL1 = helpL1.next;
                } else {
                    helpList.add(helpL2);
                    helpL2 = helpL2.next;
                }
            }
            if (helpL1 != null) {
                while (helpL1 != null) {
                    helpList.add(helpL1);
                    helpL1 = helpL1.next;
                }
            }else if (helpL2 != null){
                while (helpL2 != null) {
                    helpList.add(helpL2);
                    helpL2 = helpL2.next;
                }
            }
            //常用技巧,用一个dummy的Node来找到链表中的第一个节点
            ListNode dummy = new ListNode(0);
            ListNode res = dummy;
            while (helpList.size() != 0) {
                ListNode helpNode = helpList.remove(0);
                dummy.next = helpNode;
                dummy = dummy.next;
            }
            return res.next;
        }
    }
}
