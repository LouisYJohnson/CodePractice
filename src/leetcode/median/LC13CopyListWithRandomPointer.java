package leetcode.median;

import java.util.HashMap;

public class LC13CopyListWithRandomPointer {
    //现在有一个这样的链表：链表的每一个节点都附加了一个随机指针，随机指针可能指向链表中的任意一个节点或者指向空。
    //请对这个链表进行深拷贝。
    //A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
    //Return a deep copy of the list.

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public class Solution {
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) return null;
            //使用1个HashMap解决
            //Map拿到链表中的所有节点,并将复制的节点作为它的value
            //然后再从头遍历,按照遍历到的key将对应的value连接起来
            HashMap<RandomListNode, RandomListNode> helpMap1 = new HashMap<>();
            RandomListNode helpHead = head;
            while (helpHead != null) {
                helpMap1.put(helpHead, new RandomListNode(helpHead.label));
                helpHead = helpHead.next;
            }
            helpHead = head;
            while (helpHead != null) {
                helpMap1.get(helpHead).random = helpMap1.get(helpHead.random);
                helpMap1.get(helpHead).next = helpMap1.get(helpHead.next);
                helpHead = helpHead.next;
            }
            return helpMap1.get(head);
        }
    }
}
