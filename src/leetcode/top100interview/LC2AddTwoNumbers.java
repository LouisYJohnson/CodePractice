package leetcode.top100interview;

import java.util.ArrayList;

public class LC2AddTwoNumbers {
    //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    //如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    //您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    //示例：
    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    //原因：342 + 465 = 807
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/add-two-numbers
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        //再申请一个ListNode节点,然后根据这两个节点上的ListNode中的值确定加减结果与进位信息
        //原理和普通的进位加法一样
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //resPre是一个假节点,真正的结果节点在这个节点的后面,如果不这样做,没有办法将节点都连在一起
            ListNode nodeDummy = new ListNode(0);
            ListNode resPre = nodeDummy;
            int helpForward = 0;
            int helpCurNum = 0;

            while (l1 != null || l2 != null) {
                //注意,这里不能是l1.val = l1 == null ? 0 : l1.val
                //这种写法很蠢,因为如果到了l1是null的时候,还val,都null了哪有属性了???
                int l1Val = l1 == null ? 0 : l1.val;
                int l2Val = l2 == null ? 0 : l2.val;
                if (helpForward != 0) {
                    //如果不为0说明上一位包含了进位信息,需要参与当前位的数值相加
                    helpCurNum = l1Val + l2Val + helpForward;
                } else {
                    //如果为0,说明上一位不包含进位信息,不参与当前位的数值相加
                    helpCurNum = l1Val + l2Val;
                }

                helpForward = helpCurNum / 10;
                helpCurNum = helpCurNum % 10;
                ListNode helpNode = new ListNode(helpCurNum);
                resPre.next = helpNode;
                resPre = helpNode;
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
            }
            //处理进位信息
            if (helpForward != 0) {
                resPre.next = new ListNode(helpForward);
            }
            return nodeDummy.next;

        }

        //这种转化成整形的解法会溢出,是错误的解法
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return null;
            }
            //使用两个int类型得到ListNode对应的数字
            //相加之后,再用listNode装进去
            long l1ToNum = 0;
            long l2ToNum = 0;
            int weight = 1;

            while (l1 != null) {
                l1ToNum += l1.val * weight;
                weight *= 10;
                l1 = l1.next;
            }
            weight = 1;
            while (l2 != null) {
                l2ToNum += l2.val * weight;
                weight *= 10;
                l2 = l2.next;
            }
            long resNum = l1ToNum + l2ToNum;
            if (resNum == 0) {
                return new ListNode(0);
            }
            //将表示结果的数从低位到高位放到ArrayList中,然后链连接
            ArrayList<ListNode> helpList = new ArrayList<>();
            while (resNum != 0) {
                int tempNum = (int) resNum % 10;
                ListNode listNode = new ListNode(tempNum);
                helpList.add(listNode);
                resNum /= 10;
            }

            for (int i = 0; i < helpList.size() - 1; i++) {
                helpList.get(i).next = helpList.get(i + 1);
            }
            return helpList.get(0);

        }
    }

    public static void main(String[] args) {
        Solution solution = new LC2AddTwoNumbers().new Solution();
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(1);
        ListNode l2_1 = new ListNode(8);
        l2.next = l2_1;
        solution.addTwoNumbers(l1, l2);
        System.out.println(1);
//        ListNode l1 = new ListNode(9);
//        ListNode l2 = new ListNode(1);
//        ListNode l2_1 = new ListNode(9);
//        ListNode l2_2 = new ListNode(9);
//        ListNode l2_3 = new ListNode(9);
//        ListNode l2_4 = new ListNode(9);
//        ListNode l2_5 = new ListNode(9);
//        ListNode l2_6 = new ListNode(9);
//        ListNode l2_7 = new ListNode(9);
//        ListNode l2_8 = new ListNode(9);
//        ListNode l2_9 = new ListNode(9);
//        l2.next = l2_1;
//        l2_1.next = l2_2;
//        l2_2.next = l2_3;
//        l2_3.next = l2_4;
//        l2_4.next = l2_5;
//        l2_5.next = l2_6;
//        l2_6.next = l2_7;
//        l2_7.next = l2_8;
//        l2_8.next = l2_9;
//        solution.addTwoNumbers(l1, l2);
        System.out.println(1);

    }
}
