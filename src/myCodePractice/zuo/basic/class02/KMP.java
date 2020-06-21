package myCodePractice.zuo.basic.class02;

public class KMP {
    //KMP�㷨
    //����:�����ַ���:str1,str2
    //  ���str2�а���str1,����str1��str2�е���ʼλ��,���򷵻�-1

    //ʵ��:��Ҫnext����
    //  next����
    public static int kmp(String str1, String str2) {
        if (str2.length() < str1.length() || str1 == null || str2 == null) return -1;

        //��str1��next����
        char[] str1Chars = str1.toCharArray();
        int[] next = new int[str1.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;

        for (int i = 2; i < next.length; i++) {
            cn = next[i - 1];
            while (cn != -1) {
                if (str1Chars[cn] == str1Chars[i - 1]) {
                    next[i] = cn + 1;
                    break;//�õ����,break
                } else {
                    cn = next[cn];
                }
            }
        }
        //�õ�next�����,���Խ��������ַ���֮��ıȶ���
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

        if (pos1 == str1.length()) { //pos1�����ߵ�ͷ��,����str1��str2�ڲ�
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
//        //���ڼ���next����
//        if (ms.length  == 1) return new int[] {-1};
//        int[] next = new int[ms.length];
//        next[0] = -1;
//        next[1] = 0;
//        int pos = 2;
//        int cn = 0;//��ʼֵΪpos=2ʱ��ǰһ��Ԫ��pos=1��ǰ׺����
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
        //�ж�m�Ƿ���s�в�����m��s�п�ʼ���±�
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
            } else if (next[mi] == -1) {//˵����ǰ�Ѿ���0��,����0����ƥ��,siֱ����һ��
                si++;
            } else {
                mi = next[mi];
            }
        }
        //�ж��Ƿ�ɹ�ƥ��ı�׼��mi�Ƿ��ߵ��˾�ͷ
        return mi == ms.length ? si - mi : -1;

    }

    public static int[] getNextArray(char[] ms) {
        //���ڼ���next����
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;//��ʼֵΪpos=2ʱ��ǰһ��Ԫ��pos=1��ǰ׺����

        while (pos < ms.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {//˵��û�п���ʹ�õ�ǰ׺��
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
