package myCodePractice.zuo.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class LowestLexicography {
    //给定一个字符串类型的数组strs， 找到一种拼接方式， 使得把所有字
    //符串拼起来之后形成的字符串具有最低的字典序。

    //̰贪心
    //排序策略应该变成,str1与str2拼接,如果str1.str2<str2.str1,
    // 则str1放前面,否则,str1放后面,因为这样是有传递性的而且得到的结果是唯一的.

    public static String lowestLixicongraphy(String[] strings) {
        if (strings == null) return null;
        if (strings.length < 2) return strings[0];

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuffer res = new StringBuffer();

        for (int i = 0; i < strings.length; i++) {
            res.append(strings[i]);
        }

        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
        String[] strs2 = {"ba", "b"};

        System.out.println(lowestLixicongraphy(strs1));
        System.out.println(lowestLixicongraphy(strs2));
    }
}
