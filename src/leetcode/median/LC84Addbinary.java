package leetcode.median;

public class LC84Addbinary {
    //题目描述
    //给出两个用字符串表示的二进制数，返回他们的和（也用字符串表示）
    //例如：
    //a ="11"
    //b ="1"
    //返回"100".
    public class Solution {
        /**
         * @param a string字符串
         * @param b string字符串
         * @return string字符串
         */
        public String addBinary(String a, String b) {
            // write code here
            //进位在最长的那个字符串的下一个
            //比如111+111,得到的为1110,也就是两个3位的最多只能到四位了,
            // 所以构建的结果字符数组比a与b中最长的还要长1
            char[] a2Chars = a.toCharArray();
            char[] b2Chars = b.toCharArray();
            char[] res = new char[Math.max(a2Chars.length, b2Chars.length) + 1];
            res[0] = '0';
            char[] helpA = new char[res.length];
            char[] helpB = new char[res.length];
            //char[]系统初始化为0,数值上的0,不是字符0
            int helpIndex = a2Chars.length - 1;
            for (int i = helpA.length - 1; i >= 0; i--) {
                if (helpIndex >= 0) {
                    helpA[i] = a2Chars[helpIndex--];
                } else {
                    helpA[i] = '0';
                }
            }
            helpIndex = b2Chars.length - 1;
            for (int i = helpB.length - 1; i >= 0; i--) {
                if (helpIndex >= 0) {
                    helpB[i] = b2Chars[helpIndex--];
                } else {
                    helpB[i] = '0';
                }
            }

            int flag = 0;
            for (int i = res.length - 1; i >= 0; i--) {
                if (helpA[i] == helpB[i] && helpA[i] == '0') {
                    if (flag == 1) {
                        res[i] = '1';
                        flag = 0;   //进位被处理掉
                    } else {
                        res[i] = '0';
                    }
                } else if (helpA[i] == helpB[i] && helpA[i] == '1') {
                    if (flag == 1) {
                        res[i] = '1';   //如果前面有进位信息,这里是1,并传出进位信息(不改变进位信息)
                    } else {
                        res[i] = '0';   //如果前面没有进位信息,这里是0,并传出进位信息(改变进位信息)
                        flag = 1;
                    }
                } else if (helpA[i] != helpB[i]) {
                    if (flag == 1) {    //如果当前位置结果是1还要处理进位,则这个位置变成0,进位标值不变
                        res[i] = '0';
                    } else {
                        res[i] = '1';
                    }
                }
            }
            String res2Str = String.valueOf(res);
            return res[0] == '0' ? res2Str.substring(1) : res2Str;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC84Addbinary().new Solution();
        String res = solution.addBinary("0", "1");
        System.out.println(1);

    }
}
