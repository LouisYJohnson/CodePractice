package leetcode.topinterview;

public class LC328OddEvenLinkedList {
    //给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    //请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
    //示例 1:
    //输入: 1->2->3->4->5->NULL
    //输出: 1->3->5->2->4->NULL
    //示例 2:
    //输入: 2->1->3->5->6->4->7->NULL
    //输出: 2->3->6->7->1->5->4->NULL
    //说明:
    //应当保持奇数节点和偶数节点的相对顺序。
    //链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/odd-even-linked-list
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
        public ListNode oddEvenList(ListNode head) {
            //https://leetcode-cn.com/problems/odd-even-linked-list/solution/qi-ou-lian-biao-by-leetcode/
            //思路:原地拆分,两个指针,分别指向第一个奇数位置的Node与偶数位置的Node
            //每次将偶数指向的位置拆掉之后奇数指向下一个位置(也就是下一个奇数位置,中间的偶数位置被拆掉了)
            if (head == null) {
                return null;
            }

            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;
            //这种判断方法不管是奇数结尾(对应even!=null)还是偶数结尾(对应even.next!=null),都会在后面的odd.next=evenHead中返回正确结果(破坏最后那条链子)
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }
    }
}
