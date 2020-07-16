package leetcode.median;

import java.util.List;

public class LC125ReverseNodesInKGroup {
    //题目描述
    //将给出的链表中的节点每k个一组翻转，返回翻转后的链表
    //如果链表中的节点数不是k的倍数，将最后剩下的节点保持原样
    //你不能更改节点中的值，只能更改节点本身。
    //只允许使用常数级的空间
    //例如：
    //给定的链表是1->2->3->4->5
    //
    //对于 k = 2, 你应该返回 2->1->4->3->5
    //
    //对于 k = 3, y你应该返回 3->2->1->4->5

    //TODO https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=46&&tqId=29154&rp=1&ru=/ta/leetcode&qru=/ta/leetcode/question-ranking
    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public class Solution {
        /**
         * @param head ListNode类
         * @param k    int整型
         * @return ListNode类
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            // write code here
            //解法:
            //反转链表,不是反转数组,不用从两头交换,将每个连接进行反转后返回新的头部即可
            //从两边进行交换就是把简单问题做复杂了
            if (head == null || k < 2) return head;

            int len = 0;
            ListNode helpNode = head;
            //首先计算出这个链表的长度
            while (helpNode != null) {
                len++;
                helpNode = helpNode.next;
            }
            if (k > len) {
                return head;
            }

            ListNode pre = null;
            ListNode cur = head;
            ListNode next = cur.next;
            ListNode lastAfterChange = null;   //交换后的每组最后一个节点
            ListNode firstAfterChange = null;  //交换后每组的第一个节点
            //找到表示结果的那个节点
            ListNode res = head;
            for (int i = 0; i < k - 1; i++) {
                res = res.next;
            }
            int count = 0;

            //每组k个,共能分为len / k组,也就是有k组需要反转,剩下的就不动了
            for (int i = 0; i < len / k; i++) {
                lastAfterChange = cur;
                //每组共k个,也就是说链表中最后一个元素在数到k的时候就表示这一组的结尾
                for (int j = 1; j < k; j++) {
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                    next = next.next;
                }
                firstAfterChange = cur;
                //找到下一次交换组的最后一个元素,也就是交换后的第一个元素,将当前组的最后一个的next设为他
                ListNode help = next;
                for (int j = 1; j < k; j++) {
                    if (help == null) {
                        return res;
                    }
                    help = help.next;
                }
                lastAfterChange.next = help;

                cur.next = pre;
                cur = next;
                if (cur == null){
                    return res;
                }
                next = cur.next;
                pre = null;
            }
            return res;
        }
    }

}
