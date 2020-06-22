package myCodePractice.zuo.basic.class02;

public class KMP_ShortestHaveTwice {
    //给定一个字符串str1， 只能往str1的后面添加字符变成str2。
    //要求1： str2必须包含两个str1， 两个str1可以有重合， 但是不
    //能以同一个位置开头。
    //要求2： str2尽量短
    //最终返回str2
    //举例：
    //str1 = 123， str2 = 123123 时， 包含两个str1， 且不以相同
    //位置开头， 且str2最短。
    //str1 = 123123， str2 = 123123123 时， 包含两个str1， 且不
    //以相同位置开头， 且str2最短。
    //str1 = 111， str2 = 1111 时， 包含两个str1， 且不以相同位
    //置开头， 且str2最短。

    //其实就是求next数组,看字符串最后一个元素的后一个的信息值是多少,是几就在后面补上原字符串从几开始
    //因为next数组上的值和当前位置上的字符无关,所以next数组的大小可以比字符数组长度大1

    public static String kmpShortestHaveTwice(String str1) {
        if (str1 == null || str1.length() == 0) return null;

        char[] str1Chars = str1.toCharArray();
        int[] next = getNextArray(str1Chars);
        int help = next[next.length - 1];
        StringBuffer res = new StringBuffer(str1);
        for (int i = help; i < str1.length(); i++) {
            res.append(str1Chars[i]);
        }
        return String.valueOf(res);
    }

    public static int[] getNextArray(char[] chars) {
        int[] next = new int[chars.length + 1];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;

        for (int i = 2; i < next.length; i++) {
            cn = next[i - 1];
            while (cn != -1) {
                if (chars[cn] == chars[i - 1]) {
                    next[i] = cn + 1;
                    break;
                } else {
                    cn = next[cn];
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String string = "123";
        System.out.println(kmpShortestHaveTwice(string));
        String string1 = "123123";
        System.out.println(kmpShortestHaveTwice(string1));
        String string2 = "111";
        System.out.println(kmpShortestHaveTwice(string2));
    }
}
