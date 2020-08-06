package leetcode.top100interview;

public class LC148SortList {
    //在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
    //示例 1:
    //输入: 4->2->1->3
    //输出: 1->2->3->4
    //示例 2:
    //输入: -1->5->3->4->0
    //输出: -1->0->3->4->5
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/sort-list
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode sortList(ListNode head) {
            //解答1
            //https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
            //使用递归函数来做,递归函数功能:
            //  给定一个链表头节点,返回这个链表排序后的头节点
            if (head == null || head.next == null) {    //只有一个元素的链表没必要排序
                return head;
            }

            return process(head);
        }

        public ListNode process(ListNode head) {
            //base case
            if (head == null || head.next == null) {
                return head;
            }

            //链表的归并排序
            //首先将链表拆分为两个部分,然后分别做归并,然后再mergeSort

            //首先使用快慢指针法将链表分成两半
            ListNode helpSlow = head;
            ListNode helpFast = head.next;
            while (helpFast != null && helpFast.next != null) {
                helpSlow = helpSlow.next;
                helpFast = helpFast.next.next;
            }
            //fast到达结尾的时候,slow在中间,从slow处将列表断开
            ListNode helpMid = helpSlow.next;
            helpSlow.next = null;    //断开连接
            //进入子过程,得到左右两边排好序后的链表头部
            ListNode left = process(head);
            ListNode right = process(helpMid);

            //merge
            //升序排序
            //定义一个指向结果的头节点,相当于新开了一条路,所有结果都放到这条路上
            //这就像链表界的"新数组"
            ListNode help = new ListNode(0);
            ListNode res = help;

            while (left != null && right != null) {
                if (left.val < right.val) {
                    help.next = left;
                    left = left.next;
                }else {
                    help.next = right;
                    right = right.next;
                }
                help = help.next;
            }
            //至少一个走完了,那个没走完的直接连接到后面就可以了
            //必然有一个会没走完,因为每次指针只往下挪一个位置
            //所以肯定有一个至少剩一个数
            help.next = left == null ? right : left;

            return res.next;
        }
    }
}
