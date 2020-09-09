package leetcode.topinterview;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LC127WordLadder {
    //给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
    //
    //每次转换只能改变一个字母。
    //转换过程中的中间单词必须是字典中的单词。
    //说明:
    //
    //如果不存在这样的转换序列，返回 0。
    //所有单词具有相同的长度。
    //所有单词只由小写字母组成。
    //字典中不存在重复的单词。
    //你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    //示例 1:
    //
    //输入:
    //beginWord = "hit",
    //endWord = "cog",
    //wordList = ["hot","dot","dog","lot","log","cog"]
    //
    //输出: 5
    //
    //解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    //     返回它的长度 5。
    //示例 2:
    //
    //输入:
    //beginWord = "hit"
    //endWord = "cog"
    //wordList = ["hot","dot","dog","lot","log"]
    //
    //输出: 0
    //
    //解释: endWord "cog" 不在字典中，所以无法进行转换。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/word-ladder
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //https://leetcode-cn.com/problems/word-ladder/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you-2/
            //图的广度优先遍历(BFS)
            //使用set+队列实现广度优先遍历
            //使用广度优先遍历的思想是每一层都走,走到刚好符合标准的那一层就返回结果
            //如果每一层都走完了还没有找到结果的话,就return 0,说明没有路径
            //和之前不同的是,在这里每一层的数据使我们自己造的,而不是每一层的数据都是树形结构摆在那里

            //首先创建一个Set用来判断某个单词是否在wordList中(这个set不是广度优先遍历要用的set)
            HashSet<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }
            wordSet.remove(beginWord);

            //图的广度优先遍历,必须使用队列和一个记录是否访问过的set来实现
            //首先将头结点放入队列和set中
            LinkedList<String> q = new LinkedList<>();
            q.addLast(beginWord);

            HashSet<String> visited = new HashSet<>();
            visited.add(beginWord);
            int step = 1;   //因为包含了第一个节点,所以起始步数为1
            while (!q.isEmpty()) {
                int curQSize = q.size();
                for (int i = 0; i < curQSize; i++) {
                    String word = q.removeFirst();
                    char[] curWord2Chars = word.toCharArray();
                    //修改每一个字符,看是否有没有没碰到过,并且满足
                    //每一个字符修改为除了自身之外的25个字母中的一个,并且在一轮修改后再改回初始状态
                    for (int j = 0; j < word.length(); j++) {
                        char originChar = curWord2Chars[j];
                        for (char k = 'a'; k <= 'z'; k++) {
                            if (originChar == k) {
                                continue;
                            }
                            curWord2Chars[j] = k;
                            String nextWord = String.valueOf(curWord2Chars);

                            //如果字典中有下一个单词,说明可以走下一步
                            if (wordSet.contains(nextWord)) {
                                //如果下一步就是结束的词语,那么直接返回当前步+1
                                if (nextWord.equals(endWord)) {
                                    return step + 1;
                                }
                                //如果当前词语从没有被走过,而且这个词语还在可以走的路径中,那就注册并将其放入队列中,作为下一层的数据
                                if (!visited.contains(nextWord)) {
                                    q.add(nextWord);
                                    visited.add(nextWord);
                                }
                            }
                        }
                        curWord2Chars[j] = originChar;
                    }
                }
                //每走过一层,step++
                //使用广度优先遍历的思想是每一层都走,走到刚好符合标准的那一层就返回结果
                step++;
            }
            //如果一直到队列都弹没了,还是没有在内部return,那就返回0,说明没有这个路径
            return 0;
        }
    }
}
