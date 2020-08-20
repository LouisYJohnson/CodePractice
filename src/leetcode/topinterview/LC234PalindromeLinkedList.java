package leetcode.topinterview;

public class LC234PalindromeLinkedList {
    //请判断一个链表是否为回文链表。
    //示例 1:
    //输入: 1->2
    //输出: false
    //示例 2:
    //输入: 1->2->2->1
    //输出: true
    //进阶：
    //你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/palindrome-linked-list
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            //我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，因为使用该函数的人不希望链表结构被更改。
            //算法：
            //我们可以分为以下几个步骤：
            //找到前半部分链表的尾节点。
            //反转后半部分链表。
            //判断是否为回文。
            //恢复链表。
            //返回结果。
            //作者：LeetCode
            //链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            if (head == null || head.next == null) {    //只有一个或者没有节点的链表一定是回文的
                return true;
            }
            //找到前半部分的链表结尾
            //不管节点是奇数个还是偶数个,真正要进行反转的的都是slow的下一个
            ListNode firstHalfEnd = endOfFirstHalf(head);
            //反转后半部分链表,并返回链表反转后的第一个节点
            ListNode endOfSecondHalf = reverseAndGetFirst(firstHalfEnd.next);
            ListNode helpEndOfSecondHalf = endOfSecondHalf;
            //检测是否为回文
            //右边链表的最后一个元素(反转后的最后一个元素)的next指向null,而左边链表最后一个元素指向右边链表的第一个元素
            //这个连接没有必要被断开,而是从右边链表反转后的第一个元素和左边链表的第一个元素比
            //而且不断开还能在恢复链表的时候中间位置不用连右边
            //什么时候右边链表的元素指向null,说明右半部分已经遍历完了
            //而且不管是奇数还是偶数,结果都是正确的,因为奇数个点不会遍历到中间位置
            //偶数个点一定正好两边全遍历完
            ListNode helpHead = head;
            while (endOfSecondHalf != null) {
                if (helpHead.val != endOfSecondHalf.val) {
                    return false;
                }else {
                    helpHead = helpHead.next;
                    endOfSecondHalf = endOfSecondHalf.next;
                }
            }
            //将链表还原
            reverseAndGetFirst(helpEndOfSecondHalf);
            return true;

        }

        //反转链表并返回反转后的第一个节点(即反转前的最后节点)
        public ListNode reverseAndGetFirst(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }


        //找到前半部分链表的结尾,如果是奇数,返回中间的,如果是偶数,返回中间位置靠前的
        public ListNode endOfFirstHalf(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Solution solution = new LC234PalindromeLinkedList().new Solution();
        solution.isPalindrome(node1);
        System.out.println(1);

    }
}
