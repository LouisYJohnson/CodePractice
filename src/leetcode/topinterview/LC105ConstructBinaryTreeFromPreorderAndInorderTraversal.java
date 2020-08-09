package leetcode.topinterview;

public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    //根据一棵树的前序遍历与中序遍历构造二叉树。
    //注意:
    //你可以假设树中没有重复的元素。
    //例如，给出
    //前序遍历 preorder = [3,9,20,15,7]
    //中序遍历 inorder = [9,3,15,20,7]
    //返回如下的二叉树：
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //递归
            if (preorder == null || inorder == null ||preorder.length != inorder.length)  {
                return null;
            } else if (preorder.length == 0 && inorder.length == 0) {
                return null;
            }
            return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        //递归函数功能:
        //  给定前序遍历与中序遍历数组与两个数组中为子树的范围,返回以这两个数组构造成的二叉树的头节点
        //因为元素都是不重复的,所以可以用前序遍历的第一个元素作为根节点的数值
        public TreeNode process(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
            if (pStart == pEnd) {
                return new TreeNode(preorder[pStart]);
            }else if (pStart > pEnd || iStart > iEnd) { //如果没有左子树或者右子树就返回null
                return null;
            }

            //用前序遍历范围内的第一个节点作为根节点去中序遍历中找这个节点,让他作为左右子树分割的依据
            int rootVal = preorder[pStart];
            TreeNode tempRoot = new TreeNode(rootVal);
            int rootInPos = 0;
            for (int i = iStart; i <= iEnd; i++) {
                if (rootVal == inorder[i]) {
                    rootInPos = i;
                    break;
                }
            }
            int leftNodeNum = rootInPos - iStart;
            int rightNodeNum = iEnd - rootInPos;
            //属于左子树的子过程
            tempRoot.left = process(preorder, pStart + 1, pStart + leftNodeNum,
                    inorder, iStart, rootInPos - 1);
            tempRoot.right = process(preorder, pStart + leftNodeNum + 1, pEnd,
                    inorder, rootInPos + 1, iEnd);
            return tempRoot;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int[] pre = new int[] {1, 2};
        int[] in = new int[] {2,1};
        TreeNode res = solution.buildTree(pre, in);
        System.out.println(1);
    }
}
