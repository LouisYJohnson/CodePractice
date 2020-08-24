package leetcode.topinterview;

public class LC79WordSearch {
    //给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    //
    //单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    //示例:
    //board =
    //[
    //  ['A','B','C','E'],
    //  ['S','F','C','S'],
    //  ['A','D','E','E']
    //]
    //给定 word = "ABCCED", 返回 true
    //给定 word = "SEE", 返回 true
    //给定 word = "ABCB", 返回 false
    //提示：
    //board 和 word 中只包含大写和小写英文字母。
    //1 <= board.length <= 200
    //1 <= board[i].length <= 200
    //1 <= word.length <= 10^3
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/word-search
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
        public boolean exist(char[][] board, String word) {
            //使用回溯法解题,
            //思路为,四个方向,只有不越界并且下一层上的字符与我所需要的是一致的时候,才进入那一层
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    boolean[][] isUsed = new boolean[board.length][board[0].length];
                    if (dfs(board, i, j, word, 0, isUsed)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean dfs(char[][] board, int i, int j, String word, int level, boolean[][] isUsed) {
            //base case
            //这里要检查这个位置是否使用过,如果不检查,会出现重复使用一个字符的情况
            if (level == word.length() - 1 && !isUsed[i][j]) {
                return board[i][j] == word.charAt(level);
            }

            if (!isUsed[i][j] && board[i][j] == word.charAt(level)) {
                isUsed[i][j] = true;
                if (i + 1 < board.length && board[i + 1][j] == word.charAt(level + 1)) {
                    if (dfs(board, i + 1, j, word, level + 1, isUsed)) {
                        return true;
                    }
                }
                if (i - 1 >= 0 && board[i - 1][j] == word.charAt(level + 1)) {
                    if (dfs(board, i - 1, j, word, level + 1, isUsed)) {
                        return true;
                    }
                }
                if (j + 1 < board[0].length && board[i][j + 1] == word.charAt(level + 1)) {
                    if (dfs(board, i, j + 1, word, level + 1, isUsed)) {
                        return true;
                    }
                }
                if (j - 1 >= 0 && board[i][j - 1] == word.charAt(level + 1)) {
                    if (dfs(board, i, j - 1, word, level + 1, isUsed)) {
                        return true;
                    }
                }
                isUsed[i][j] = false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC79WordSearch().new Solution();
        char[][] test = new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}};
        String test1 = "abcb";
        System.out.println(solution.exist(test, test1));
    }
//        public boolean exist(char[][] board, String word) {
//            boolean[][] isUsed = new boolean[board.length][board[0].length];
//            StringBuilder helpSB = new StringBuilder();
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[0].length; j++) {
//
//                    if (board[i][j] == word.charAt(0)) {
//                        if (process(board, i, j, isUsed, helpSB, word)){
//                            return true;
//                        }
//                    }
//                }
//            }
//            return false;
//        }
//
//        //递归
//        //给定一个二维数组,与当前位置,和当前位置是否被使用的二维布尔表格还有当前组合到哪一步
//        //当前位置还没走,返回从这个位置开始是否能走出所要的单词
//        public boolean process(char[][] board, int i, int j, boolean[][] isUsed, StringBuilder helpSB, String word) {
//            boolean res = false;
//            if (!isUsed[i][j]) {
//                helpSB.append(board[i][j]);
//                isUsed[i][j] = true;
//                if (helpSB.toString().equals(word)) {
//                    return true;
//                }
//
//                if (i - 1 >= 0 && !isUsed[i - 1][j]) {
//                    res = process(board, i - 1, j, isUsed, helpSB, word);
//                    if (res) {
//                        return true;
//                    }
//                }
//                if (i + 1 < board.length && !isUsed[i + 1][j]) {
//                    res = process(board, i + 1, j, isUsed, helpSB, word);
//                    if (res) {
//                        return true;
//                    }
//                }
//                if (j + 1 < board[0].length && !isUsed[i][j + 1]) {
//                    res = process(board, i, j + 1, isUsed, helpSB, word);
//                    if (res) {
//                        return true;
//                    }
//                }
//                if (j - 1 >= 0 && !isUsed[i][j - 1]) {
//                    res = process(board, i, j - 1, isUsed, helpSB, word);
//                    if (res) {
//                        return true;
//                    }
//                }
//                helpSB.deleteCharAt(helpSB.length() - 1);
//                isUsed[i][j] = false;
//            }else {
//                return false;
//            }
//            return false;
//        }
//    }
}
