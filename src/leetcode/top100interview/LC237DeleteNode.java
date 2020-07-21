package leetcode.top100interview;

import java.util.List;

public class LC237DeleteNode {
    //请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
    //现有一个链表 -- head = [4,5,1,9]，它可以表示为:
    //示例 1:
    //输入: head = [4,5,1,9], node = 5
    //输出: [4,1,9]
    //解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
    //示例 2:
    //输入: head = [4,5,1,9], node = 1
    //输出: [4,5,9]
    //解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
    //说明:
    //链表至少包含两个节点。
    //链表中所有节点的值都是唯一的。
    //给定的节点为非末尾节点并且一定是链表中的一个有效节点。
    //不要从你的函数中返回任何结果。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public void deleteNode(ListNode node) {
            //这个node就是要被删除的节点,但是这里只给了这一个节点
            //也没说删掉节点后,必须当前节点指向的地址不在这个链表中
            //所以方法为,将当前节点的下一个节点的值给自己,然后这个要被删除的节点将下一个节点架空(替换掉)
            ListNode nodeNext = node.next;
            ListNode nodeNextNext = nodeNext.next;
            node.val = nodeNext.val;
            node.next = nodeNextNext;
            nodeNext.next = null;
            return;
        }
    }
}
