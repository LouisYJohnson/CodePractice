package leetcode.median;

import java.util.ArrayList;
import java.util.Stack;

public class LC6BinaryTreePostorderTraversal {
    //题目描述
    //求给定的二叉树的后序遍历。
    //例如：
    //给定的二叉树为{1,#,2,3},
    //    1↵    ↵     2↵    /↵   3↵
    //返回[3,2,1].
    //备注；用递归来解这道题太没有新意了，可以给出迭代的解法么？
    //
    //Given a binary tree, return the postorder traversal of its nodes' values.
    //For example:
    //Given binary tree{1,#,2,3},
    //   1↵    ↵     2↵    /↵   3↵
    //
    //return[3,2,1].
    //
    //Note: Recursive solution is trivial, could you do it iteratively?

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public class Solution {
        /**
         * @param root TreeNode类
         * @return int整型ArrayList
         */
        //后序遍历,用前序遍历的中左右实现中右左,将结果放入栈中,然后再pop就是逆序了
        public ArrayList<Integer> postorderTraversal(TreeNode root) {
            // write code here
            if (root == null) return null;

            Stack<TreeNode> helpStack = new Stack<>();
            Stack<TreeNode> helpResRevStack = new Stack<>();


            helpStack.push(root);
            while (!helpStack.isEmpty()) {
                TreeNode helpNode = helpStack.pop();
                //得到结果逆序栈
                helpResRevStack.push(helpNode);
                if (helpNode.left != null) helpStack.push(helpNode.left);
                if (helpNode.right != null) helpStack.push(helpNode.right);
            }
            //将这个结果逆序栈pop出来放到List中,即为结果
            ArrayList<Integer> resultList = new ArrayList<>();
            while (!helpResRevStack.isEmpty()) {
                resultList.add(helpResRevStack.pop().val);
            }
            return resultList;

        }

    }


}
