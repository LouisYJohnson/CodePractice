package leetcode.topinterview;

public class LC101SymmetricTree {
    //给定一个二叉树，检查它是否是镜像对称的。
    //例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    //    1
    //   / \
    //  2   2
    // / \ / \
    //3  4 4  3
    //但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    //    1
    //   / \
    //  2   2
    //   \   \
    //   3    3
    //进阶：
    //你可以运用递归和迭代两种方法解决这个问题吗？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/symmetric-tree
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return process(root, root);
        }

        public boolean process(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }

            return left.val == right.val && process(left.left, right.right) && process(left.right, right.left);
        }
    }
}
