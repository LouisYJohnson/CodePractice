package leetcode.top100interview;

import myCodePractice.zuo.advance.class03.SerializeAndReconstructTree;

import java.util.LinkedList;

public class LC297SerializeAndDeserializeBinaryTree {
    //序列化是将一个数据结构或者对象转换为连续的比特位的操作，
    // 进而可以将转换后的数据存储在一个文件或者内存中，
    // 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
    //请设计一个算法来实现二叉树的序列化与反序列化。
    // 这里不限定你的序列 / 反序列化算法执行逻辑，
    // 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
    //示例: 
    //你可以将以下二叉树：
    //
    //    1
    //   / \
    //  2   3
    //     / \
    //    4   5
    //
    //序列化为 "[1,2,3,null,null,4,5]"
    //提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    //说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link SerializeAndReconstructTree}
     */
    //TODO 二叉树的序列化反序列化
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "#_";
            return process1(root);
        }

        private String process1(TreeNode root) {
            //base case
            if (root == null) {
                return "#_";
            }

            StringBuilder helpSB = new StringBuilder();
            helpSB.append(root.val + "_");
            helpSB.append(process1(root.left));
            helpSB.append(process1(root.right));
            return helpSB.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] helpData = data.split("_");
            LinkedList<String> helpList = new LinkedList<>();
            for (int i = 0; i < helpData.length; i++) {
                //addLast和push方法不是一回事,push每次都往队列头加一个数据,后面的数据一次后延
                helpList.addLast(helpData[i]);
            }
            return process2(helpList);
        }

        private TreeNode process2(LinkedList<String> helpList) {
            //怎么序列化的就怎么反序列化(加入一个队列)
            String root = helpList.pollFirst();
            if ("#".equals(root)) return null;

            TreeNode helpRoot = new TreeNode(Integer.valueOf(root));
            helpRoot.left = process2(helpList);
            helpRoot.right = process2(helpList);
            return helpRoot;
        }
    }

    // Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
    public static void main(String[] args) {
        Codec codec = new LC297SerializeAndDeserializeBinaryTree().new Codec();
        TreeNode root = new LC297SerializeAndDeserializeBinaryTree().new TreeNode(1);
        TreeNode root1 = new LC297SerializeAndDeserializeBinaryTree().new TreeNode(2);
        TreeNode root2 = new LC297SerializeAndDeserializeBinaryTree().new TreeNode(3);
        root.left = root1;
        root.right = root2;
        String temp = codec.serialize(root);
        codec.deserialize(temp);
        System.out.println(temp);
    }
}
