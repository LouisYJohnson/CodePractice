package com.newcoder.zuo3.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class LowestLexicography {
    //贪心
//    给定一个字符串类型的数组strs， 找到一种拼接方式， 使得把所有字
//    符串拼起来之后形成的字符串具有最低的字典序。
//排序策略应该变成,str1与str2拼接,
// 如果str1.str2<str2.str1,则str1放前面,否则,str1放后面,因为这样是有传递性的而且得到的结果是唯一的.
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }

        public static String lowestString(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            Arrays.sort(strs, new MyComparator());
            StringBuffer res = new StringBuffer();
            for (int i = 0; i < strs.length; i++) {
                res.append(strs[i]);
            }
            String result = res.toString();
            return result;
        }

        //zuo
        public static String lowestString1(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            Arrays.sort(strs, new MyComparator1());
            String res = "";
            for (int i = 0; i < strs.length; i++) {
                res += strs[i];
            }
            return res;
        }

        public static class MyComparator1 implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                return (a + b).compareTo(b + a);
            }
        }

        public static void main(String[] args) {
            String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
            System.out.println(lowestString(strs1));
            System.out.println(lowestString1(strs1));

            String[] strs2 = {"ba", "b"};
            System.out.println(lowestString(strs2));
            System.out.println(lowestString1(strs2));

        }
    }

}
