package leetcode.median;

public class LC126SwapNodesInPairs {
    //题目描述
    //将给定的链表中每两个相邻的节点交换一次，返回链表的头指针
    //例如,
    //给出1->2->3->4，你应该返回链表2->1->4->3。
    //你给出的算法只能使用常量级的空间。你不能修改列表中的值，只能修改节点本身。

    public class ListNode {
        int val;
        ListNode next = null;
    }

    public class Solution {
        /**
         * @param head ListNode类
         * @return ListNode类
         */
        //使用三个指针
        //前两个指针指向要进行交换的两个节点,最后一个指针指向要交换的第一个节点的前一个
        public ListNode swapPairs(ListNode head) {
            // write code here
            if (head == null) return null;

            ListNode pre = null;
            ListNode first = head;
            ListNode second = head.next;
            if (second == null) {   //如果链表只有一个节点,直接返回
                return head;
            } else if (second.next == null) { //如果只有两个节点,就换两个节点并返回
                second.next = first;
                first.next = null;
                return second;
            }
            //如果链表的节点有两个以上的话,进入下面的流程
            //如果后面的能换的话,真正的结果是second
            ListNode res = second;

            //必须下一次可以交换并前进(前面至少有一个节点的位置)才能进入while循环
            //while循环内部的逻辑为先交换再前进,如果遇到奇数个节点,交换后放弃前进直接返回结果
            while (second.next != null) {
                first.next = second.next;
                second.next = first;
                if (pre == null) {
                    pre = first;    //将pre设置为下一次要交换的第一个节点的前一个
                    first = second.next.next;   //更新下一次要交换的节点的第一个
                    if (first.next == null) {   //如果是奇数个节点,就不换了
                        return res;
                    } else {    //否则另外一个节点前进并进入下一次循环
                        second = first.next;
                    }
                }else { //如果pre不是null,证明已经进行过至少一次交换,pre要参与到交换中
                    pre.next = second;
                    pre = first;
                    first = second.next.next;
                    if (first.next == null) {
                        return res;
                    } else {
                        second = first.next;
                    }
                }
            }
            //最后一次没有前进在这里补上(这里不可能遇到奇数的情况,
            // 如果是奇数,就在while循环中直接返回结果不前进了)
            second.next = first;
            first.next = null;
            pre.next = second;
            return res;
        }
    }

}
