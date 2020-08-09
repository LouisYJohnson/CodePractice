package leetcode.topinterview;

import java.util.Stack;

public class LC206ReverseLinkedList {
    //反转一个单链表。
    //示例:
    //输入: 1->2->3->4->5->NULL
    //输出: 5->4->3->2->1->NULL
    //进阶:
    //你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/reverse-linked-list
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            //使用三个指针,分别为pre,cur,next
            if (head == null) {
                return null;
            }
            ListNode pre = null;
            ListNode cur = head;
            ListNode next = head.next;

            while (cur.next != null) {
                cur.next = pre;
                pre = cur;
                cur = next;
                next = next.next;
            }
            //最后停下的那个节点并没有进行链表指针的反转工作,需要单独做
            cur.next = pre;
            return cur;
        }


        //解法不太行,复杂度太高
        //非进阶,但是这个解法不太行,复杂度太高
        public ListNode reverseList1(ListNode head) {
            //压栈然后弹栈
            if (head == null) return null;
            Stack<ListNode> helpStack = new Stack<>();
            ListNode helpNode = head;
            while (helpNode != null) {
                helpStack.push(helpNode);
                helpNode = helpNode.next;
            }
            helpNode = helpStack.pop();
            ListNode res = helpNode;

            while (!helpStack.isEmpty()) {
                helpNode.next = helpStack.peek();
                helpNode = helpStack.pop();
            }
            //最后一个弹出的ListNode指向还没有改变
            helpNode.next = null;
            return res;
        }

        //进阶
    }
}
