package leetcode.median;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LC131LetterCombinationsOfAPhoneNumber {
    //题目描述
    //给出一个仅包含数字的字符串，给出所有可能的字母组合。
    //数字到字母的映射方式如下:(就像电话上数字和字母的映射一样)
    //https://www.nowcoder.com/practice/5044a44afe6c40ec9b67b7531393e854?tpId=46&&tqId=29160&rp=1&ru=/ta/leetcode&qru=/ta/leetcode/question-ranking
    //Input:Digit string "23"Output:["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    //注意：虽然上述答案是按字典序排列的，但你的答案可以按任意的顺序给出
    public class Solution {
        /**
         * @param digits string字符串
         * @return string字符串ArrayList
         */
        public ArrayList<String> letterCombinations(String digits) {
            // write code here
            ArrayList<String> help = new ArrayList<String>();
            help.add("");
            if (digits == null || digits.length() == 0) return help;
            ArrayList<String> result = new ArrayList<>();
            char[] digits2Chars = digits.toCharArray();
            HashMap<Integer, String> helpMap = new HashMap<>();
            helpMap.put(2, "abc");
            helpMap.put(3, "def");
            helpMap.put(4, "ghi");
            helpMap.put(5, "jkl");
            helpMap.put(6, "mno");
            helpMap.put(7, "pqrs");
            helpMap.put(8, "tuv");
            helpMap.put(9, "wxyz");
            char[] helpChars = helpMap.get(digits2Chars[0] - '0').toCharArray();
            for (int i = 0; i < helpChars.length; i++) {
                result.add(String.valueOf(helpChars[i]));
            }

            process(digits2Chars, 1, result, helpMap);
            return result;
        }

        //递归
        //  递归功能:给一个表示数字按键与字母对应映射的map与表示输入按键的数组,
        //  还有一个表示结果的ArrayList,还有当前数字按键对应的在号码中的坐标i
        //  i坐标前面(不包括i)都已经排好了,i位置包括i位置之后自由组合,并将结果放入result中
        public void process(char[] digits2Chars,
                            int i,
                            ArrayList<String> result,
                            HashMap<Integer, String> helpMap) {
            //base case
            if (i == digits2Chars.length) return;

            //得到当前第i个号码对应的字符串
            String curNum2String = helpMap.get(digits2Chars[i] - '0');
            //字符串对应的字符数组
            char[] curNum2String2Chars = curNum2String.toCharArray();

//            StringBuffer helpSB = new StringBuffer();
            int helpSize = result.size();
            int j = 0;
            while (j < helpSize) {
                //将所有属于未加入本次拨号的所有结果拿出(其实就是父过程传入的result)
                // 并挨个添加当前数字对应的所有字母并放回result中去
                String temp = result.remove(0);
                for (int k = 0; k < curNum2String2Chars.length; k++) {
                    StringBuffer helpSB = new StringBuffer(temp);
                    result.add(helpSB.append(curNum2String2Chars[k]).toString());
                }
                j++;
            }
            process(digits2Chars, i + 1, result, helpMap);
        }

    }

    //for Combinations
    public static void main(String[] args) {
        String string = "432";
        LC131LetterCombinationsOfAPhoneNumber.Solution solution = new LC131LetterCombinationsOfAPhoneNumber().new Solution();
        ArrayList<String> arrayList = solution.letterCombinations(string);
        System.out.println("1");
    }
}
