package com.newcoder.zuo3.advanced.class06;

public class unsolved_Code_05_PalindromeMinCut {
    //�������ٷָ���
    //����Ŀ��
    //����һ���ַ���str�� ���ذ�strȫ���гɻ����Ӵ�����С�ָ�����
    //��������
    //str="ABA"��
    //����Ҫ�и str������ǻ��Ĵ��� ���Է���0��
    //str="ACDCDCDAD"��
    //������Ҫ��2�α��3�������Ӵ��� ����"A"�� "CDCDC"��"DAD"�� ���Է���2��
    //�ݹ�:
    //�ݹ麯������:��֤�ڵ�ǰλ�õ�ǰ�涼�ǻ���,���غ����ַ���Ҫ�ж��ٵ����ܶ���ɻ����Ӵ������ٵ���
    //���һ��ֻҪ��ǰ���ǻ��Ĵ�,��ô��һ���������ﶼ����,������Ҫ�����б����е��ǵݹ�,ö�����п�����,�ڱ�֤ǰ���ǻ��Ĵ���ǰ����
    //�ڸĶ�̬�滮��ʱ����ÿ��λ�õ�ֵ��Ҫ��������ֵ,�����ڵõ�ֵ֮ǰ����Ҫ�����Ƿ��ǻ��Ĵ����ж����ж��Ƿ�Ҫ���λ�õ�ֵ
    //��ô�������������Ż��ж�����λ�÷�Χ�ڵ��ַ����ǲ��ǻ���,��Ϊ���[l,r]�ǲ��ǻ��ĺ�Ƶ��,����ʹ��Ԥ����������������

    //�ж��ǲ��ǻ��Ĵ�
    public static boolean isP(char[] chars, int l, int r) {
        if (r - l == 0) {
            return true;
        }
        int mid = l + (r - l) / 2;

        for (int i = l; i <= mid; i++) {
            if (chars[i] != chars[r--]) return false;
        }
        return true;
    }

    public static int cut(char[] str, int i) {
        //base case
        if (i == str.length - 1) return 0;

        int min = Integer.MAX_VALUE;
        for (int j = i; j < str.length; j++) {
            if (isP(str, i, j)) {
                //��Ϊ�ݹ�ĺ����Ǵ�iλ��(������iλ��)֮ǰ�Ķ��ǻ��ĵ�ǰ����,��������ȷ����i��j�ǻ���,�´ξ�Ҫ��j+1��ʼ��
                min = Math.min(min, cut(str, j + 1));
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min + 1;
//        return min;
    }

    // for test
    public static int minCut(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[] dp = new int[len + 1];
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (chas[i] == chas[j] && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }


    public static String getRandomStringOnlyAToD(int len) {
        int range = 'D' - 'A' + 1;
        char[] charArr = new char[(int) (Math.random() * (len + 1))];
        for (int i = 0; i != charArr.length; i++) {
            charArr[i] = (char) ((int) (Math.random() * range) + 'A');
        }
        return String.valueOf(charArr);
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int testTimes = 5;
        String str = null;
        for (int i = 0; i != testTimes; i++) {
            str = getRandomStringOnlyAToD(maxLen);
            char[] strChar = str.toCharArray();
            System.out.print("\"" + str + "\"" + " : ");
            System.out.println(minCut(str));
            System.out.print(" ||||" + cut(strChar, 0));
            System.out.println();
        }
//        System.out.println(cut("BCC".toCharArray(), 0));

    }


}
