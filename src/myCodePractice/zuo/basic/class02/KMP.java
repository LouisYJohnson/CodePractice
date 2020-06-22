package myCodePractice.zuo.basic.class02;

public class KMP {
    //KMP
    //判断str1是否在str2中,如果在,返回最开始出现的坐标,如果不在,返回-1

    public static int kmp(String str1, String str2) {
        if (str2.length() < str1.length() || str1 == null || str2 == null) return -1;

        char[] str1Chars = str1.toCharArray();
        int[] next = new int[str1.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;

        //next数组
        for (int i = 2; i < next.length; i++) {
            cn = next[i - 1];
            while (cn != -1) {
                if (str1Chars[cn] == str1Chars[i - 1]) {
                    next[i] = cn + 1;
                    break;//找到了,break
                } else {
                    cn = next[cn];
                }
            }
        }
        //找到next数组后就可以开始求结果了
        char[] str2Chars = str2.toCharArray();
        int pos1 = 0;
        int pos2 = 0;
        while (pos1 < str1.length() && pos2 < str2.length()) {
            if (str1Chars[pos1] == str2Chars[pos2]) {
                pos1++;
                pos2++;
            } else {
                pos1 = next[pos1];
                if (pos1 == -1) {
                    pos1 = 0;
                    pos2++;
                }
            }
        }

        if (pos1 == str1.length()) { //必须pos1走到了结尾,才说明str1在str2中
            return pos2 - str1.length();
        }
        return -1;
    }

//    public static int[] getNextArray1(String str1) {
//        char[] str1Chars = str1.toCharArray();
//        int[] next = new int[str1.length()];
//        next[0] = -1;
//        next[1] = 0;
//        int cn = 0;
//
//        for (int i = 2; i < next.length; i++) {
//            cn = next[i - 1];
//            while (cn != -1) {
//                if (str1Chars[cn] == str1Chars[i - 1]) {
//                    next[i] = cn + 1;
//                    break;
//                }else {
//                    cn = next[cn];
//                }
//            }
//        }
//        return next;
//    }
//
//
//    public static int[] getNextArray(char[] ms) {
//        if (ms.length  == 1) return new int[] {-1};
//        int[] next = new int[ms.length];
//        next[0] = -1;
//        next[1] = 0;
//        int pos = 2;
//        int cn = 0;
//
//        while (pos < ms.length) {
//            if (ms[pos-1] == ms[cn]) {
//                next[pos++] = ++cn;
//            }else if (cn > 0) {
//                cn = next[cn];
//            }else {//˵��û�п���ʹ�õ�ǰ׺��
//                next[pos++] = 0;
//            }
//        }
//        return next;
//    }

    //for test
    public static int getIndexOf(String s, String m) {
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
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }

        return mi == ms.length ? si - mi : -1;

    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;

        while (pos < ms.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "abcdef";
        String s2 = "def";
        System.out.println(getIndexOf(s1, s2));
        System.out.println(kmp(s2, s1));

    }
}
