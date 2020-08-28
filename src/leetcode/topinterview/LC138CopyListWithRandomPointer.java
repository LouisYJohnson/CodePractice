package leetcode.topinterview;

import java.util.HashMap;

public class LC138CopyListWithRandomPointer {
    //给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
    //要求返回这个链表的 深拷贝。 
    //我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
    //val：一个表示 Node.val 的整数。
    //random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
    //示例 1：
    //输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    //输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
    //示例 2：
    //输入：head = [[1,1],[2,1]]
    //输出：[[1,1],[2,1]]
    //示例 3：
    //输入：head = [[3,null],[3,0],[3,null]]
    //输出：[[3,null],[3,0],[3,null]]
    //示例 4：
    //输入：head = []
    //输出：[]
    //解释：给定的链表为空（空指针），因此返回 null。
    //提示：
    //-10000 <= Node.val <= 10000
    //Node.random 为空（null）或指向链表中的节点。
    //节点数目不超过 1000 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            //深拷贝:对于引用类型变量,就是要创建一个新的并且状态和旧的一模一样的对象
            //不仅值相同,还要有不同的地址
            //使用一个HashMap与两次遍历解决,Key为被复制节点,value为新节点
            //第一次将所有节点放入HashMap中,并创建复制节点
            //第二次遍历HashMap,将value的指针按照key的指针来指
            //为什么不能一次做完?
            //只有一次的话,当前key对应的指针可能还不在HashMap中,没有办法指
            Node helpHead = head;
            HashMap<Node, Node> helpMap = new HashMap<>();
            while (helpHead != null) {
                helpMap.put(helpHead, new Node(helpHead.val));
                helpHead = helpHead.next;
            }

            //遍历HashMap,将value的next与random按照key的指向来指向对应的value
            for (Node node : helpMap.keySet()) {
                helpMap.get(node).next = helpMap.get(node.next);
                helpMap.get(node).random = helpMap.get(node.random);
            }
            return helpMap.get(head);
        }
    }
}
