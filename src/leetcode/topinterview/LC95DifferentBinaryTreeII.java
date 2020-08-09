package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC95DifferentBinaryTreeII {
    //给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
    //示例：
    //输入：3
    //输出：
    //[
    //  [1,null,3,2],
    //  [3,2,null,1],
    //  [3,1,null,null,2],
    //  [2,1,3],
    //  [1,null,2,null,3]
    //]
    //解释：
    //以上的输出对应以下 5 种不同结构的二叉搜索树：
    //
    //   1         3     3      2      1
    //    \       /     /      / \      \
    //     3     2     1      1   3      2
    //    /     /       \                 \
    //   2     1         2                 3
    // 
    //提示：
    //0 <= n <= 8
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
        public List<TreeNode> generateTrees(int n) {
            //列举每一个节点作为头节点,它的左右各自作为二叉树的两段,并各自返回自己的头节点
            if (n == 0) return new ArrayList<>();
            return process(1, n);
        }

        //输入最左边的TreeNode数值和最右边的TreeNode数值,形成搜索二叉树并返回头节点的集合
        public List<TreeNode> process(int left, int right) {
            List<TreeNode> allTrees = new ArrayList<>();
            //base case
            if (left > right) {
                allTrees.add(null);
                return allTrees;
            }
            for (int i = left; i <= right; i++) {
                //得到左边与右边的所有形成搜索二叉树的节点的头节点
                List<TreeNode> leftNodes = process(left, i - 1);
                List<TreeNode> rightNodes = process(i + 1, right);
                //将左边与右边的所有节点分别与当前节点进行拼接并返回
                for (TreeNode rightNode : rightNodes) {
                    for (TreeNode leftNode : leftNodes) {
                        TreeNode curNode = new TreeNode(i);
                        curNode.left = leftNode;
                        curNode.right = rightNode;
                        allTrees.add(curNode);
                    }
                }
            }
            return allTrees;
        }
    }
}
