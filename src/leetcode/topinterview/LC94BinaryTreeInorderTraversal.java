package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94BinaryTreeInorderTraversal {
    //给定一个二叉树，返回它的中序 遍历。
    //
    //示例:
    //
    //输入: [1,null,2,3]
    //   1
    //    \
    //     2
    //    /
    //   3
    //
    //输出: [1,3,2]
    //进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //注意:中序遍历是左中右
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();

            //使用递归
            //process(root, res);
            //使用栈辅助
//            res = useStack(root);
            //使用Morris遍历
            res = useMorris(root);
            return res;
        }

        //使用Morris遍历
        //Morris遍历是按照中左中右的顺序
        public List<Integer> useMorris(TreeNode node) {
            List<Integer> res = new ArrayList<>();
            TreeNode cur = node;
            TreeNode leftMostRight = null;

            while (cur != null) {
                //只会访问一次没有左子树的点
                if (cur.left == null) {
                    res.add(cur.val);
                    cur = cur.right;
                } else {    //会访问两次有左子树的节点,第一次,找到左子树的最右节点并将其右孩子指向cur,第二次,指向null,并将cur指向右孩子
                    //用最右节点是否指向cur作为第一次还是第二次访问的判断依据
                    leftMostRight = cur.left;
                    //找到左子树最右节点
                    //如果这里不加上!=cur的话,第二次来到这里就指错点了
                    while (leftMostRight.right != null && leftMostRight.right != cur) {
                        leftMostRight = leftMostRight.right;
                    }
                    if (leftMostRight.right == null) {  //第一次访问这个有左子树的点,要将其指向cur,然后将cur向左移动
                        leftMostRight.right = cur;
                        cur = cur.left;
                    }else if (leftMostRight.right == cur){  //第二次,要将其置为null然后将cur向右移动
                        leftMostRight.right = null;
                        res.add(cur.val);
                        cur = cur.right;
                    }
                }
            }
            return res;
        }

        //使用栈:
        public List<Integer> useStack(TreeNode node) {
            //建立栈
            Stack<TreeNode> helpStack = new Stack<>();
            List<Integer> res = new ArrayList<>();
            //如果栈不空或者node!=null:
            while (!helpStack.isEmpty() || node != null) {
                //如果node不为null,将node压栈并将node移向node的左节点
                if (node != null) {
                    helpStack.push(node);
                    node = node.left;
                } else {    //如果node为null,则弹栈,并将node移向弹出元素的右节点
                    TreeNode helpNode = helpStack.pop();
                    res.add(helpNode.val);
                    node = helpNode.right;
                }
            }
            return res;
        }


        //递归:
        //  使用递归函数完成中序遍历
        //递归函数功能:
        //  给定一个头节点,除了当前头节点其他位置都遍历完成的list,将当前节点对应的中序遍历结果放入list中
        public void process(TreeNode node, List<Integer> res) {
            //base case
            if (node == null) {
                return;
            }

            process(node.left, res);
            res.add(node.val);
            process(node.right, res);
        }
    }
}
