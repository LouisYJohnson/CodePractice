package leetcode.topinterview;

public class LC395LongestSubstringWithAtLeastKRepeatingCharacters {
    //找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
    //示例 1:
    //输入:
    //s = "aaabb", k = 3
    //输出:
    //3
    //最长子串为 "aaa" ，其中 'a' 重复了 3 次。
    //示例 2:
    //输入:
    //s = "ababbc", k = 2
    //输出:
    //5
    //最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int longestSubstring(String s, int k) {
            //递归做
            if (s == null || s.length() == 0) {
                return 0;
            } else if (k > s.length()) {
                return 0;
            }

            char[] s2Chars = s.toCharArray();
            return process(s2Chars, k, 0, s2Chars.length - 1);
        }

        //递归函数功能:
        //给定字符数组,每个字符必须出现的个数,这个字符数组的左范围与右范围,返回这个字符数组对应的最长字串T,这个T中每一个字符出现次数都不小于k
        public int process(char[] chars, int k, int leftP, int rightP) {
            //base case
            if (rightP - leftP + 1 < k) {
                return 0;   //字符数组中都没有k个元素了,肯定不满足条件,所以返回0
            }

            //统计这26个字母每个出现的个数(在范围内的)
            int[] num = new int[26];
            for (int i = leftP; i <= rightP; i++) {
                num[chars[i] - 'a']++;
            }

            //从左到右找到字符在范围内第一次出现不少于k次的位置
            while (rightP - leftP + 1 >= k && num[chars[leftP] - 'a'] < k) {
                leftP++;
            }
            //从右到左找字符在范围内第一次出现不少于k次的位置
            while (rightP - leftP + 1 >= k && num[chars[rightP] - 'a'] < k) {
                rightP--;
            }
            //如果挪完了发现中间元素的个数小于k了,说明不合法
            if (rightP - leftP + 1 < k) {
                return 0;
            }
            //如果合法,从这两个指针中间找到不合法的(那个位置的元素在范围内出现次数少于k)
            //注意这里其实不用再数一次了,因为只要是前面略过的,一定是出现次数小于k的,
            //在第一次合法的位置就停住了,所以合法的在这里肯定还合法,不合法的一直不合法
            for (int i = leftP; i <= rightP; i++) {

                //发现不合法,从这个中间拆开,变成两个子串,分别去判断
                if (num[chars[i] - 'a'] < k) {
                    return Math.max(process(chars, k, leftP, i - 1), process(chars, k, i + 1, rightP));
                }
            }
            //如果走到这里都没return,说明这段上的都合法,都出现了至少k次
            return rightP - leftP + 1;

        }
    }
}
