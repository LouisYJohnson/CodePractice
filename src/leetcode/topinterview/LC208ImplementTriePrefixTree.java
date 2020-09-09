package leetcode.topinterview;

public class LC208ImplementTriePrefixTree {
    //实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
    //
    //示例:
    //
    //Trie trie = new Trie();
    //
    //trie.insert("apple");
    //trie.search("apple");   // 返回 true
    //trie.search("app");     // 返回 false
    //trie.startsWith("app"); // 返回 true
    //trie.insert("app");
    //trie.search("app");     // 返回 true
    //说明:
    //
    //你可以假设所有的输入都是由小写字母 a-z 构成的。
    //保证所有输入均为非空字符串。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Trie {
        //每个节点上装的是上个节点进入这个节点时对应的map下标对应字符的对应path与end
        //map对应的是下一个字符
        class TrieNode {    //前缀树节点
            int path;   //表示有多少个字符串经过当前节点
            int end;    //表示有多少个字符串以当前节点为终点
            TrieNode[] map; //装着所有的分支

            TrieNode() {
                path = 0;
                end = 0;
                map = new TrieNode[26]; //对应26个字母
            }
        }

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() { //初始化的时候只初始化头结点就可以了
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }

            TrieNode help = root;
            char[] word2Chars = word.toCharArray();
            for (int i = 0; i < word2Chars.length; i++) {
                //如果对应节点不存在,创建该节点
                if (help.map[word2Chars[i] - 'a'] == null) {
                    help.map[word2Chars[i] - 'a'] = new TrieNode();
                }
                //转到该节点,然后在该节点对应的path++
                help = help.map[word2Chars[i] - 'a'];
                help.path++;
            }
            //在最后到达的节点上end++
            help.end++;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            //沿途path不为0的基础上(必然不可能为0),end不为0
            if (word == null) {
                return false;
            }

            char[] word2Chars = word.toCharArray();
            TrieNode help = root;
            for (int i = 0; i < word2Chars.length; i++) {
                if (help.map[word2Chars[i] - 'a'] == null) {
                    return false;
                }

                help = help.map[word2Chars[i] - 'a'];
            }
            return help.end != 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            //查看以该字符串结束位置的path为多少
            char[] prefix2Chars = prefix.toCharArray();
            TrieNode help = root;
            for (int i = 0; i < prefix2Chars.length; i++) {
                if (help.map[prefix2Chars[i] - 'a'] == null) {
                    return false;
                }
                help = help.map[prefix2Chars[i] - 'a'];
            }
            return help.path != 0;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
