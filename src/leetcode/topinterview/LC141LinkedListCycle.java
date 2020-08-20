package leetcode.topinterview;

public class LC141LinkedListCycle {
    //给定一个链表，判断链表中是否有环。
    //为了表示给定链表中的环，
    // 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    // 如果 pos 是 -1，则在该链表中没有环。
    //示例 1：
    //输入：head = [3,2,0,-4], pos = 1
    //输出：true
    //解释：链表中有一个环，其尾部连接到第二个节点。
    //示例 2：
    //输入：head = [1,2], pos = 0
    //输出：true
    //解释：链表中有一个环，其尾部连接到第一个节点。
    //示例 3：
    //输入：head = [1], pos = -1
    //输出：false
    //解释：链表中没有环。
    //进阶：
    //
    //你能用 O(1)（即，常量）内存解决此问题吗？
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/linked-list-cycle
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            //快慢指针法
            //慢指针在0,快指针在1,慢指针每次动1,快指针每次动2
            //如果有环,二者一定能碰上
            if (head == null || head.next == null) {
                return false;
            }

            ListNode slow = head;
            ListNode fast = head.next;
            //第一个和第二个都是保证fast能正常往前走,如果不能正常往前走,说明无环
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow.equals(fast)) {
                    return true;
                }
            }
            return false;
        }
    }
}
