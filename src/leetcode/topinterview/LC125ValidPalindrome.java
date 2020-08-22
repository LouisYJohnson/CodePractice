package leetcode.topinterview;

public class LC125ValidPalindrome {
    //给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    //说明：本题中，我们将空字符串定义为有效的回文串。
    //示例 1:
    //输入: "A man, a plan, a canal: Panama"
    //输出: true
    //示例 2:
    //输入: "race a car"
    //输出: false
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/valid-palindrome
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean isPalindrome(String s) {
            //首先筛选掉字符串中的符号和空格
            StringBuilder helpSB = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                //是字母或者数字才放到字符串中,并且忽略大小写,都转成小写
                if (Character.isLetterOrDigit(ch)) {
                    helpSB.append(Character.toLowerCase(ch));
                }
            }
            String res = helpSB.toString();
            //双指针,一个在左一个在右,分别比较当前位置是否相同
            int left = 0;
            int right = res.length() - 1;
            while (left < right) {
                if (res.charAt(left) != res.charAt(right)) {
                    return false;
                }else {
                    left++;
                    right--;
                }
            }
            return true;
        }
    }
}
