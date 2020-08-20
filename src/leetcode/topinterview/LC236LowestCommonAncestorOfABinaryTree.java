package leetcode.topinterview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class LC236LowestCommonAncestorOfABinaryTree {
    //给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    //
    //百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    //
    //例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
    //
    //
    //
    // 
    //
    //示例 1:
    //
    //输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    //输出: 3
    //解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
    //示例 2:
    //
    //输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    //输出: 5
    //解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
    //说明:
    //所有节点的值都是唯一的。
    //p、q 为不同节点且均存在于给定的二叉树中
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    //思路
    //我们可以用哈希表存储所有节点的父节点，
    // 然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，
    // 并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，
    // 那么这个节点就是我们要找的最近公共祖先。
    //算法
    //从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
    //从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
    //同样，我们再从 q 节点开始不断往它的祖先移动，
    // 如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return root;
            }
            //key为子,value为父
            HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
            //首先遍历二叉树,并记录每一个节点的父节点与子节点
            //这里使用栈辅助的前序遍历
            Stack<TreeNode> helpStack = new Stack<>();
            helpStack.push(root);
            fatherMap.put(root, null);
            while (!helpStack.isEmpty()) {
                TreeNode tempNode = helpStack.pop();
                if (tempNode.right != null) {
                    fatherMap.put(tempNode.right, tempNode);
                    helpStack.push(tempNode.right);
                }
                if (tempNode.left != null) {
                    fatherMap.put(tempNode.left, tempNode);
                    helpStack.push(tempNode.left);
                }
            }
            //然后分别从p与q向上遍历
            //先让p遍历并找到所有经过的节点,然后q向上遍历,一边遍历一边找是否有p遍历到的节点
            //如果有,就是公共祖先
            HashSet<TreeNode> helpSet = new HashSet<>();
            TreeNode pFather = p;
            while (pFather != null) {
                helpSet.add(pFather);
                pFather = fatherMap.get(pFather);
            }
            TreeNode qFather = q;
            while (qFather != null) {
                if (helpSet.contains(qFather)) {
                    return qFather;
                }
                qFather = fatherMap.get(qFather);
            }
            return null;
        }
    }
}
