package com.newcoder.zuo3.advanced.class07;

public class Code_01_Scramble_String {
    //����һ�����ȴ���1���ַ����� ���ǿ��԰�����ַ����ֳ������ǿյĲ��֣�
    //����ÿ�����ֻ���ϸ����ȥ��
    //���ҿ����ö���������ʽ���� ����
    //�ַ��� s1 = "great":
    //���Էֽ����ôһ�����ӣ���ֻ������һ�ַֽ�ṹ��
    //great
    /// \
    //gr eat
    /// \ / \
    //g r e at
    /// \
    //a t
    //����˵s1�Ľ��Ҵ��� ָ����������һ�ַֽ�ṹ�У� ���⽻��ĳ���ڵ����
    //�������������γɵ��ַ�����
    //�������ǿ���ѡ��������ķֽ�ṹ�У� ������gr������ڵ�ĺ��ӽڵ㣬 ��
    //�ɵ���Ϊ��
    //rgeat
    /// \
    //rg eat
    /// \ / \
    //r g e at
    /// \
    //a t
    //��ô��rgeat���� �ǡ�great���Ľ��Ҵ���
    //ͬ���� ���ǿ��Լ���������eat���ڵ�����Һ��ӣ� �γɣ�
    //rgtae
    /// \
    //rg tae
    /// \ / \
    //r g ta e
    /// \
    //t a
    //��ô��rgtae���� �ǡ�great���Ľ��Ҵ���
    //����һ���ַ����Ľ��Ҵ��Ƿǳ�֮��ģ� �ֽ�ṹ������кܶ��֣�
    //����ÿһ�ַֽ�ṹ���������⽻������һ���ڵ�����Һ��ӡ�
    //���������ַ���s1��s2�� �ж�s2�ǲ���s1�Ľ��Ҵ���

    //�ַ�����һ������һ����һ���ǽ��Ҵ�
    //����1234���ܱ��2413
    //��ʵ�ؼ����ڵ�һ����������,��һ������ȥ,�⵶���ߵ�Ԫ�ؾͿ�����㶯��.��Ϊ�ֽ�ṹ�ǹ̶���,���̶���ֻ�����й̶�Ԫ�صĽ���
    //���Ա�׼����:��:��������ַ���,���ֳ�7|5,�������������ֱַ��ǽ��Ҵ�,���������ַ������ǽ��Ҵ�,
    // ���һ���ֳ�7|5һ���ֳ�5|7,����5��5�ǽ��Ҵ�,7��7�ǽ��Ҵ�,���������ַ����ǽ��Ҵ�

    //�ݹ�Ϊ:�ݹ�Ϊ:�������ַ����Ϸֱ��L1,L2λ���Ͽ�ʼ,����ȡsize����,�ж������������Ƿ��ǽ��Ҵ�
    public static boolean isScramble1(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int size = s1.length();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        return process(chars1, chars2, 0, 0, size);
    }

    //size - part�����ұ�ʣ�Ĳ���
    public static boolean process(char[] chars1, char[] chars2, int l1, int l2, int size) {
        //base case
        if (size == 1) return chars1[l1] == chars2[l2];

        for (int part = 1; part < size; part++) {
            if (process(chars1, chars2, l1, l2, part) && process(chars1, chars2, l1 + part, l2 + part, size - part)
                    ||
                    process(chars1, chars2, l1, l2 + size - part, part) && process(chars1, chars2, l1 + part, l2, size - part)) {
                return true;
            }
        }
        return false;
    }

    //for test
    public static boolean isScramble2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int N = s1.length();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        boolean[][][] dp = new boolean[N][N][N + 1];
        for (int L1 = 0; L1 < N; L1++) {
            for (int L2 = 0; L2 < N; L2++) {
                dp[L1][L2][1] = chs1[L1] == chs2[L2];
            }
        }
        for (int size = 2; size <= N; size++) {
            for (int L1 = 0; L1 <= N - size; L1++) {
                for (int L2 = 0; L2 <= N - size; L2++) {
                    for (int p = 1; p < size; p++) {
                        if ((dp[L1][L2][p] && dp[L1 + p][L2 + p][size - p])
                                || (dp[L1][L2 + size - p][p] && dp[L1 + p][L2][size
                                - p])) {
                            dp[L1][L2][size] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][N];
    }

    public static void main(String[] args) {
        String test1 = "bcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcde";
        String test2 = "cebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebd";
        // System.out.println(isScramble1(test1, test2));
        System.out.println(isScramble2(test1, test2));
    }
}
