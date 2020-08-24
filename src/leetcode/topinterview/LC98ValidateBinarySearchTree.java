package leetcode.topinterview;

import java.util.LinkedList;
import java.util.Stack;

public class LC98ValidateBinarySearchTree {
    //给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    //假设一个二叉搜索树具有如下特征：
    //节点的左子树只包含小于当前节点的数。
    //节点的右子树只包含大于当前节点的数。
    //所有左子树和右子树自身必须也是二叉搜索树。
    //示例 1:
    //输入:
    //    2
    //   / \
    //  1   3
    //输出: true
    //示例 2:
    //输入:
    //    5
    //   / \
    //  1   4
    //     / \
    //    3   6
    //输出: false
    //解释: 输入为: [5,1,4,null,null,3,6]。
    //     根节点的值为 5 ，但是其右子节点值为 4
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/validate-binary-search-tree
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
        public boolean isValidBST(TreeNode root) {
            //中序遍历得到的是升序
            Stack<TreeNode> helpStack = new Stack<>();
            LinkedList<Integer> helpList = new LinkedList<>();
            while (root != null || !helpStack.isEmpty()) {
                if (root != null) {
                    helpStack.push(root);
                    root = root.left;
                } else {
                    root = helpStack.pop();
                    helpList.addLast(root.val);
                    root = root.right;
                }
            }
            for (int i = 1; i < helpList.size(); i++) {
                if (helpList.get(i) <= helpList.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }
}
