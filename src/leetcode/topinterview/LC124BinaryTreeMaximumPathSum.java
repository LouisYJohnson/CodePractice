package leetcode.topinterview;

public class LC124BinaryTreeMaximumPathSum {
    //给定一个非空二叉树，返回其最大路径和。
    //本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
    //示例 1:
    //输入: [1,2,3]
    //
    //       1
    //      / \
    //     2   3
    //输出: 6
    //示例 2:
    //输入: [-10,9,20,null,null,15,7]
    //   -10
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //输出: 42
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
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
        int res = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            //二叉树套路问题,左子树收集信息,右子树收集信息,然后返回给上级
            process(root);
            return res;
        }

        //递归函数功能:
        //  给定头节点,返回包含头节点在内的最大路径和
        //注意这题不用返回节点
        //如果返回节点上级实际上是不需要左右连接起来的,如果下级中左右连接起来的结果返回给了上级,上级就没有办法走了
        //因为下级已经把V型路线走完了
        //给题想复杂了!
        public int process(TreeNode treeNode){
            //base case
            if (treeNode == null) {
                return 0;
            }

            //本级只要左右节点中为正值的作为贡献点
            int leftMaxSum = Math.max(0, process(treeNode.left));
            int rightMaxSum = Math.max(0, process(treeNode.right));
            //两边都走最大路线,看结果
            res = Math.max(res, leftMaxSum + rightMaxSum + treeNode.val);
            //向上级返回的是左腿或者右腿中最大的,而不是一个V型的已经走好的路线,如果返回走好v型路线的路径和,上级没得走了
            return Math.max(leftMaxSum, rightMaxSum) + treeNode.val;
        }


//        public ReturnData process(TreeNode treeNode) {
//            //base case
//            if (treeNode == null) {
//                return new ReturnData(null, Integer.MIN_VALUE);
//            }
//
//            ReturnData leftReturn = process(treeNode.left);
//            ReturnData rightReturn = process(treeNode.right);
//            leftReturn = leftReturn.maxLen > 0 ? leftReturn : null;
//            rightReturn = rightReturn.maxLen > 0 ? rightReturn : null;
//
//
//            int maxVal = Math.max(treeNode.val, Math.max(leftReturn.maxLen, rightReturn.maxLen));
//            if (leftReturn.head == treeNode.left && rightReturn.head == treeNode.right) {
//                if (maxVal < 0) {
//                    if (maxVal == treeNode.val) {
//                        return new ReturnData(treeNode, treeNode.val);
//                    }else {
//                        return maxVal == leftReturn.maxLen ?
//                                new ReturnData(leftReturn.head, leftReturn.maxLen) :
//                                new ReturnData(rightReturn.head, rightReturn.maxLen);
//                    }
//                }else {
//                    int maxTempVal = Math.max(0, leftReturn.maxLen) + Math.max(0, rightReturn.maxLen);
//                    if (treeNode.val >= 0) {
//                        return new ReturnData(treeNode, maxTempVal);
//                    }else {
//                        return leftReturn.maxLen > rightReturn.maxLen ?
//                                new ReturnData(leftReturn.head, leftReturn.maxLen) :
//                                new ReturnData(rightReturn.head, rightReturn.maxLen);
//                    }
//                }
//            } else if (leftReturn.head == treeNode.left) {
//
//            }
//
//            //有可能最大的路径和需要加上当前节点,只有在左边节点是最大路径对应的头节点时才是
//        }
//
//        public class ReturnData {
//            public TreeNode head;
//            public int maxLen;
//
//            public ReturnData(TreeNode head, int maxLen) {
//                this.head = head;
//                this.maxLen = maxLen;
//            }
//        }
    }
}
