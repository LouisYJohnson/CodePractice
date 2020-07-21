package leetcode.top100interview;

public class LC108ConvertSortedArrayToBinarySearchTree {
    //将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
    //
    //本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
    //
    //示例:
    //
    //给定有序数组: [-10,-3,0,5,9],
    //
    //一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
    //
    //      0
    //     / \
    //   -3   9
    //   /   /
    // -10  5
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
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
        public TreeNode sortedArrayToBST(int[] nums) {

            return process(nums, 0, nums.length - 1);
        }
        //递归函数:
        //  传入数组的左边界与右边界,返回这个边界中的数转化成的高度平衡的二叉搜索树的头节点
        //套路:二叉树的题目都是左子树返回信息,右子树返回信息,当前位置整合后,整合的信息返回给上级
        public TreeNode process(int[] nums, int left, int right) {
            //这里必须是左边大于右边才行,如果写==的话有的点是直接变左边大于右边,永远不会到左边等于右边
            if (left > right) {
                return null;
            }

            //这种将数组一分为二的方式就保证了转化出来的搜索树一定是平衡的
            int mid = left + (right - left) / 2;
            TreeNode curHead = new TreeNode(nums[mid]);
            TreeNode leftSubHead = process(nums, left, mid - 1);
            TreeNode rightSubHead = process(nums, mid + 1, right);
            curHead.left = leftSubHead;
            curHead.right = rightSubHead;
            return curHead;
        }

    }
}
