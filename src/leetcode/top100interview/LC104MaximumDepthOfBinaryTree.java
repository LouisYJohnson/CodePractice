package leetcode.top100interview;

public class LC104MaximumDepthOfBinaryTree {
    //给定一个二叉树，找出其最大深度。
    //二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
    //说明: 叶子节点是指没有子节点的节点。
    //示例：
    //给定二叉树 [3,9,20,null,null,15,7]，
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //返回它的最大深度 3 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
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
        public int maxDepth(TreeNode root) {
            int level = 1;
            return process(root, level);
        }

        //递归函数功能:
        //  给定一个节点与当前节点所在的深度,返回这个节点左右子树的最大深度
        //本质:左子树信息与右子树信息整合之后返回给它的上级,关键在于碰到叶节点该怎么办
        public int process(TreeNode node, int level) {
            //base case
            if (node == null) {
                //如果这一层是null,是不算作level的
                return level - 1;
            }
            //当前节点的左右节点都在这个节点level+1的位置
            int leftDeepLevel = process(node.left, level + 1);
            int rightDeepLevel = process(node.right, level + 1);
            return Math.max(leftDeepLevel, rightDeepLevel);
        }
    }
}
