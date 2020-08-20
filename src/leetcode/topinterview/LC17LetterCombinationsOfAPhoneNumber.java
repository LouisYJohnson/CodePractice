package leetcode.topinterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC17LetterCombinationsOfAPhoneNumber {
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    //
    //
    //
    //示例:
    //
    //输入："23"
    //输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    //说明:
    //尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<>();
            }

            char[] digits2Char = digits.toCharArray();
            HashMap<Character, Character[]> helpMap = new HashMap<>();
            helpMap.put('2', new Character[]{'a', 'b', 'c'});
            helpMap.put('3', new Character[]{'d', 'e', 'f'});
            helpMap.put('4', new Character[]{'g', 'h', 'i'});
            helpMap.put('5', new Character[]{'j', 'k', 'l'});
            helpMap.put('6', new Character[]{'m', 'n', 'o'});
            helpMap.put('7', new Character[]{'p', 'q', 'r', 's'});
            helpMap.put('8', new Character[]{'t', 'u', 'v'});
            helpMap.put('9', new Character[]{'w', 'x', 'y', 'z'});
            StringBuilder helpSB = new StringBuilder();
            List<String> res = new ArrayList<>();
            process(digits2Char, 0, helpMap, helpSB, res);
            return res;
        }

        //回溯法
        //给定表示输入数字的字符串,还有数字对应的字母,以及当前为第几个位置上的数字
        //将最终的结果输入到res中
        public void process(char[] digits, int i, HashMap<Character, Character[]> helpMap, StringBuilder helpSB, List<String> res) {
            if (i == digits.length) {
                res.add(String.valueOf(helpSB));
                return;
            }

            //得到当前键位对应的所有字母
            Character[] num2ABC = helpMap.get(digits[i]);
            for (int j = 0; j < num2ABC.length; j++) {
                helpSB.append(num2ABC[j]);
                process(digits, i + 1, helpMap, helpSB, res);
                helpSB.deleteCharAt(helpSB.length() - 1);
            }
        }
    }

}
