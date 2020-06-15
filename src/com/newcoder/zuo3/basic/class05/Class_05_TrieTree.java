package com.newcoder.zuo3.basic.class05;

public class Class_05_TrieTree {
    //前缀树
    //支持查询字符串,添加字符串,删除字符串,与查询以规定前缀开头的字符串有几个
    //由多个前缀节点TrieNode组成,其中头节点并不存储path,end的信息,只负责连接代表其他数据的节点
    //但是所有的操作都要从头节点做起,如果没有它,就没有其他连接
    public static class TrieNode {
        public int path;//表示有多少个字符串经过这个节点
        public int end;//表示有多少个字符串以这个节点作为结尾
        //如果是这种形式,可以装ASC码,定义成HashMap的形式,可以装任何字符
        public TrieNode[] map;//表示当前节点需要连接的下一个节点,如果元素都为null证明这条路往下没有节点了

        public TrieNode() {
            path = 0;
            end = 0;
            map = new TrieNode[26];//对应了26个英文字母
        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        //将字符串加入前缀树中:
        //将字符串拆分成字符串数组,并依次加入前缀树中,如果当前字符串在树中表示与下一个节点相连接的对象数组map
        //的索引上并没有对象,说明这条路还没有走过,在对应的索引上创建新数组,并将path++
        //如果走到了结尾,则在最后节点上end++
        public void insert(String word) {
            if (word == null) return;
            char[] wordCharArr = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            //遍历数组,并将字符数组放入树中,记得path++,与最后的end++
            for (int i = 0; i < wordCharArr.length; i++) {
                index = wordCharArr[i] - 'a';//将26个英文字母的ASC码值归为0-25
                //如果指向的下一个数组中没有TrieNode元素存在,说明路没被走过,要创建新的路
                if (node.map[index] == null) {
                    node.map[index] = new TrieNode();
                }
                //不管创没创建新路,都要将下一个TrieNode的path++
                node = node.map[index];
                node.path++;
            }
            //所有字符走完后,到了最后一个节点,这个节点的end++
            node.end++;
        }

        //查询完整的字符串(而不是前缀)是否存在于前缀树中
        public boolean search(String word) {
            if (word == null) return false;
            TrieNode node = root;
            char[] wordCharArr = word.toCharArray();
            int index = 0;
            for (int i = 0; i < wordCharArr.length; i++) {
                index = wordCharArr[i] - 'a';
                //一旦字符串中有一个在node中没有后续,就终结
                if (node.map[index] == null) {
                    return false;
                }
                //node下移
                node = node.map[index];
            }
            //都找到了,只能说明以这个字符串在这个前缀树中创建过,但是也有可能这只是个中间节点,往下还有别的字符
            //也就是说我要找"abc",但是存在一个"abcde"我找到了abc,但是实际上我找到的只是abcde中的一段
            //二者并不是同一个字符串,所以要看这个节点的end是否大于0,如果大于0,说明存在这个字符串,否则
            //不存在
            return node.end != 0;
        }

        //在前缀树中删除完整的字符串(而不是前缀)
        public void delete(String word) {
            //如果这个字符串存在于前缀树中才进行下一步操作
            if (search(word)) {
                TrieNode node = root;
                char[] wordCharArr = word.toCharArray();
                int index = 0;
                for (int i = 0; i <wordCharArr.length ; i++) {
                    index = wordCharArr[i] - 'a';
                    //因为字符串存在于前缀树中才能进行下一步判断
                    //所以如果当前字符串存在于前缀树中,并且遍历到的一个节点它的path为1,说明它下面必定
                    //是这个字符串,因为path为1说明在当前字符串存在于前缀树中并且只有这一个字符串通过了这个节点
                    //所以直接将该节点的下一个连接节点从这个连接中切断即可
                    //每次判断的时候都要对下一个节点的path--(if中的判断条件是会执行的)
                    // 因为前面的逻辑决定这个字符串必定存在于前缀树中,放心减就是了
                    if (node.map[index].path-- == 1) {//如果当前节点连接的节点的path为1,则将连接的节点去除
                        node.map[index] = null;
                        return;
                    }
                    node = node.map[index];
                }
                //如果字符串在前缀树中出现了不止一次,则只删除这一次就好了(沿途的已经在if中path--了)
                node.end--;
            }
        }

        //查找以该字符串pre作为前缀的字符串在前缀树中出现了几次
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] preCharArr = pre.toCharArray();
            int index = 0;
            TrieNode node = root;
            int num = 0;
            for (int i = 0; i < preCharArr.length; i++) {
                index = preCharArr[i] - 'a';
                //if (node.map[index].path != 0) {
                // 不能这么写,因为下一个连接的节点有可能为null,我这么写的原因是查找前缀,
                // 所以只要下一个节点有字符串通过就好了,所以我想只看path,
                // 但是不能拿一个可能为null的东西来取成员变量的,会报错
                //而且如果path为0的话,这个节点是根本不会存在的,所以我这个条件判断写的完全是多余又错误
                if (node.map[index] == null) {
                    return 0;
                }
                node = node.map[index];
            }
            return node.path;
        }
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNumber("zuo"));

    }
}
