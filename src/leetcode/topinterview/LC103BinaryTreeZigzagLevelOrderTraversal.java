package leetcode.topinterview;

import java.util.LinkedList;
import java.util.List;

public class LC103BinaryTreeZigzagLevelOrderTraversal {
    //给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    //
    //例如：
    //给定二叉树 [3,9,20,null,null,15,7],
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //返回锯齿形层次遍历如下：
    //
    //[
    //  [3],
    //  [20,9],
    //  [15,7]
    //]
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            //按层遍历加个flag,flag表示向结果集中添加节点的顺序
            //1从左往右,0从右往左
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            //队列装着每一层要输出的节点
            //然后用一个辅助队列(在循环中)来决定如何输出每一层的数据
            LinkedList<TreeNode> helpQ = new LinkedList<>();
            int helpQSize = 0;
            helpQ.addLast(root);
            int flag = 1;
            while (!helpQ.isEmpty()) {
                helpQSize = helpQ.size();
                LinkedList<Integer> helpStockQ = new LinkedList<>();
                for (int i = 0; i < helpQSize; i++) {
                    TreeNode temp = helpQ.removeFirst();
                    if (flag == 1) {    //从左到右,正着加节点值
                        helpStockQ.addLast(temp.val);
                    }else {             //从右到左,反着加节点值
                        helpStockQ.addFirst(temp.val);
                    }
                    //然后将下一层的所有节点,放到这个队列中
                    if (temp.left != null) {
                        helpQ.addLast(temp.left);
                    }
                    if (temp.right != null) {
                        helpQ.addLast(temp.right);
                    }
                }
                //注意,这里是不用new的,因为每次向res中装的实际上是内存地址,而在55行的位置
                //每次都赋予了helpStockQ新的内存地址,所以已经在res中存储的地址对应堆中的值是不会被修改的
                //这一点和回溯法不同,因为回溯法中的helpList一直在操作同一个地址对应的数据,
                //并且res中保存的就是这个地址,必须要new,否则就是反复修改了
                res.add(helpStockQ);
                flag = flag == 1 ? 0 : 1;
            }
            return res;
        }
    }
}
