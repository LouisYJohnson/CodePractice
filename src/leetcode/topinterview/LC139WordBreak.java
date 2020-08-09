package leetcode.topinterview;

import java.util.HashSet;
import java.util.List;

public class LC139WordBreak {
    //给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
    // 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
    //说明：
    //拆分时可以重复使用字典中的单词。
    //你可以假设字典中没有重复的单词。
    //示例 1：
    //输入: s = "leetcode", wordDict = ["leet", "code"]
    //输出: true
    //解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
    //示例 2：
    //输入: s = "applepenapple", wordDict = ["apple", "pen"]
    //输出: true
    //解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
    //     注意你可以重复使用字典中的单词。
    //示例 3：
    //输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    //输出: false
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/word-break
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/word-break/solution/javaban-ben-de-2chong-xie-fa-by-sdwwld/
        public boolean wordBreak(String s, List<String> wordDict) {

            HashSet<String> helpHashSet = new HashSet<>(wordDict);
//            return process(s, 0, helpHashSet);
            return process1(s, helpHashSet);
        }

        //递归改动态规划:
        //能动的只有i,从0到s.length()
        public boolean process1(String s, HashSet<String> hashSet) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length()] = true;
            //看递归中都是依赖右边的,所以从右到左填表
            for (int index = dp.length - 2; index >= 0; index--) {
                for (int j = index + 1; j <= s.length(); j++) {
                    if (hashSet.contains(s.substring(index, j)) && dp[j]) {
                        dp[index] = true;
                        break;
                    }
                }
            }
            return dp[0];
        }

        //递归
        //  递归函数功能:
        //  给定字符数组与字符数组对应下标i
        //  下标之前的位置都已经拆分好了,后面的都没有拆
        public boolean process(String s, int i, HashSet<String> hashSet) {
            //base case
            if (i == s.length()) {
                return true;
            }

            //i位置上的判断,要看在哪个位置上能遇到字典中的字符串,然后才能进入下一层判断
            //j表示的是subString中的下标结束位置,是开区间,所以最大值为s.length()
            for (int j = i + 1; j <= s.length(); j++) {
                if (hashSet.contains(s.substring(i, j)) && process(s, j, hashSet)) {
                    return true;
                }
            }
            return false;
        }
    }
}
