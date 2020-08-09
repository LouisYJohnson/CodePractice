package leetcode.topinterview;

import java.util.*;

public class LC102BinaryTreeLevelOrderTraversal {
    //给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    //示例：
    //二叉树：[3,9,20,null,null,15,7],
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //返回其层次遍历结果：
    //[
    //  [3],
    //  [9,20],
    //  [15,7]
    //]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            //宽度优先遍历,使用一个set和一个链表完成(这里是二叉树,所以不用set,因为没有交错关系,一定不会出现重复遍历某个节点的现想)
            //出队列的时候就是遍历到的时候
            //出队列不会再回来
            //这里要的是同一个层的都要放在一起,返回一个数组套数组的样子,所以不能用左神的方法
            //所以每次都将队列掏空(同时这也保证每次进队列的都是同一层的),并将掏空的那些元素的所有子节点进行扩展
            if (root == null) return new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> helpList = new ArrayList<>();
            LinkedList<TreeNode> helpQ = new LinkedList<>();
            //头节点手动加入,才能开启这个遍历的过程
            helpQ.addLast(root);

            while (!helpQ.isEmpty()) {
                int qLen = helpQ.size();
                int helpCount = 0;
                List<Integer> levelNodes = new ArrayList<>();
                while (helpCount < qLen) {
                    TreeNode sameLevelPopNodes = helpQ.pollFirst();
                    levelNodes.add(sameLevelPopNodes.val);
                    if (sameLevelPopNodes.left != null) {
                        helpQ.addLast(sameLevelPopNodes.left);
                    }
                    if (sameLevelPopNodes.right != null) {
                        helpQ.addLast(sameLevelPopNodes.right);
                    }
                    helpCount++;
                }
                res.add(levelNodes);    //将收集到的所有本层的nodes都放到结果中
            }
            return res;
        }
    }
}
