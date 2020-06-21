package com.newcoder.zuo3.basic.class02;

public class KMP {

    public static int getIndexOf(String s, String m) {
        //判断m是否在s中并返回m在s中开始的下标
        if (s == null || m == null || m.length() < 1 || m.length() > s.length()) return -1;
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {//说明当前已经是0了,而且0还不匹配,si直接下一个
                si++;
            } else {
                mi = next[mi];
            }
        }
        //判断是否成功匹配的标准是mi是否走到了尽头
        return mi == ms.length ? si - mi : -1;

    }

    public static int[] getNextArray(char[] ms) {
        //用于计算next数组
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;//起始值为pos=2时的前一个元素pos=1的前缀长度

        while (pos < ms.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {//说明没有可以使用的前缀了
                next[pos++] = 0;
            }
        }
        return next;
    }

}
