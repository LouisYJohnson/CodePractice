package myCodePractice.zuo.basic.class02;

public class ManacherShortestEnd {
    //Manacher算法扩展题目
    //给定一个字符串str1， 只能往str1的后面添加字符变成str2， 要求str2
    //整体都是回文串且最短。
    //举例：
    //str1 = ABC12321, 返回ABC12321CBA
    //解:包含最后一个字符的最长回文串是多长(pR碰到str1.length的时候就返回长度即可)

    public static String getShotestEnd(String str) {
        if (str == null || str.length() == 0) return null;

        int lcpIncludeEndLen = getLcpIncludeStr1End(str);
        char[] strChars = str.toCharArray();
        char[] res = new char[str.length() + (str.length() - lcpIncludeEndLen)];
        int helpIndex = str.length() - lcpIncludeEndLen - 1;//此变量用于得到不属于包括最后一个字符的回文子串的时候用

        for (int i = 0; i < res.length; i++) {
            if (i > str.length() - 1) {
                res[i] = strChars[helpIndex--];
            } else {
                res[i] = strChars[i];
            }
        }
        return String.valueOf(res);
    }

    //找到包含最后一个字符的最长回文串的长度
    public static int getLcpIncludeStr1End(String str) {
        char[] manacherChars = getManacherChars(str);
        int c = -1;
        int pR = -1;
        int[] pArr = new int[manacherChars.length];
        int stringLength = 0;

        for (int i = 0; i < pArr.length; i++) {
            //根据i与pR的关系确定pArr[i]的初始值
            pArr[i] = pR > i ? Math.min(pR - i, pArr[2 * c - i]) : 1;
            //从初始值开始,往外扩
            while (i - pArr[i] >= 0 && i + pArr[i] < pArr.length) {
                if (manacherChars[i - pArr[i]] == manacherChars[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //扩完,看pR与c能不能更新
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                c = i;
            }
            //更新后看pR碰到最后一个坐标的下一个没有(pR表示的就是边界的下一个位置)
            if (pR == manacherChars.length) {
                stringLength = pArr[i] - 1;
                break;
            }
        }
        return stringLength;
    }


    public static char[] getManacherChars(String string) {
        char[] chars = string.toCharArray();
        char[] res = new char[string.length() * 2 + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            if ((i & 1) == 0) {
                res[i] = '#';
            } else {
                res[i] = chars[index++];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(getShotestEnd(str2));
    }
}
