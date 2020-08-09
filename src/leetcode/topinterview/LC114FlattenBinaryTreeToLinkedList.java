package leetcode.topinterview;

public class LC114FlattenBinaryTreeToLinkedList {
    //给定一个二叉树，原地将它展开为一个单链表。
    //例如，给定二叉树
    //    1
    //   / \
    //  2   5
    // / \   \
    //3   4   6
    //将其展开为：
    //1
    // \
    //  2
    //   \
    //    3
    //     \
    //      4
    //       \
    //        5
    //         \
    //          6
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public void flatten(TreeNode root) {
            //中序遍历顺带展开为列表
            //将整体的中→左→右进行遍历
            //https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
            while (root != null) {
                if (root.left == null) {
                    //如果当前节点没有左子树,直接右移
                    root = root.right;
                } else {
                    //找到当前节点左子树的最右节点,并将当前节点的右子树挂在这个节点后面
                    TreeNode helpNode = root.left;
                    while (helpNode.right != null) {
                        helpNode = helpNode.right;
                    }
                    //将当前节点的左子树挂在当前节点的右子树上
                    TreeNode helpNodeRight = root.right;
                    root.right = root.left;
                    root.left = null;
                    //将当前节点的原右子树挂在左子树的最右节点下边
                    helpNode.right = helpNodeRight;
                    //放完了之后当前节点右移(左边已经没有节点了)
                    root = root.right;
                }
            }
        }
    }
}
