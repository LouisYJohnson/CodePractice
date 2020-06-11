package com.newcoder.zuo3.advanced.class06;

public class Code_07_RegularExpressionMatch {
    //�ַ���ƥ������
    //����Ŀ��
    //�����ַ���str�� ���о��Բ������ַ�'.'��'*'�� \
    //�ٸ����ַ���exp�� ���п��Ժ���'.'��'*'�� '*'�ַ�������exp�����ַ��� ������������'*'�ַ������ڡ�
    // exp�е�'.'�����κ�һ���ַ���exp�е�'*'��ʾ'*'��ǰһ���ַ�������0�����߶����
    // ��дһ�������� �ж�str�Ƿ��ܱ�expƥ�䡣
    //��������
    //str="abc"�� exp="abc"�� ����true��
    //str="abc"�� exp="a.c"�� exp�е���'.'���Դ��������ַ��� ���Է���true��
    //str="abcd"�� exp=".*"�� exp��'*'��ǰһ���ַ���'.'�� ���Կɱ�ʾ����������'.'�ַ��� ��
    //exp��"...."ʱ��"abcd"ƥ�䣬 ����true��
    //str=""�� exp="..*"�� exp��'*'��ǰһ���ַ���'.'�� �ɱ�ʾ����������'.'�ַ��� ����".*"֮ǰ��
    //��һ��'.'�ַ��� ���ַ�����'*'��Ӱ�죬 ����str������һ���ַ����ܱ�expƥ�䡣 ���Է���
    //false��
    //�ݹ�:str��i��ʼ������ܷ�exp��j��ʼ�����ƥ�����,P(i,j)���������˼.
    //��ʼ���̵�P(0,0)
    //�ݹ��е������Ϊ:
    //exp��һ��λ����*����һ��λ�ò���*�ֱ����ô��

    //�����ж�str��exp�Ƿ���Ч
    //str:���Բ�����.��*
    //exp:���Ժ���'.'��'*'�� '*'�ַ�������exp�����ַ��� ������������'*'�ַ������ڡ�
    public static boolean isValid(char[] str, char[] exp) {
        //str:
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.' || str[i] == '*') {
                return false;
            }
        }
        //exp:
        if (exp[0] == '*') return false;
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] == '*' && exp[i - 1] == '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) return false;
        char[] strChars = str.toCharArray();
        char[] expChars = exp.toCharArray();
        if (!isValid(strChars, expChars)) return false;
        return process(strChars, expChars, 0, 0);
    }

    //�ݹ�:str��i��ʼ������ܷ�exp��j��ʼ�����ƥ�����,P(i,j)���������˼.
    //��ʼ���̵�P(0,0)
    //�ݹ��е������Ϊ:
    //exp��һ��λ����*����һ��λ�ò���*�ֱ����ô��
    //����*,�жϵ�ǰλ���Ƿ�ƥ��Ȼ������ӽ���
    //��*,��*�ܹ���Ӧ0~n����ͬ�ַ�,Ȼ������ӽ���
    public static boolean process(char[] str, char[] exp, int si, int ei) {
        //base case:
        //��Ϊÿ�ε��ӹ��̶�����ǰ��1,����==length-1��ʱ��,ʵ�����ǻ�����Ҫ�жϵ�,����base case������length��λ�ò���������
        if (ei == exp.length) {
            return si == str.length;
        }
        //exp��һ��λ�ò���*��ʱ��(Ҫôexp�Ѿ��������һ��,Ҫ������û����ͷ������һ��λ�ò���*)
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {  //exp���ܵ���ͷ����һ��λ�ò���*
            //str���ܵ���ͷ,��str���ַ���exp�ĵ�ǰλ���ַ���ͬ����exp��ǰλ���ַ���'.'
            return si != str.length && (str[si] == exp[ei] || exp[ei] == '.')
                    && process(str, exp, si + 1, ei + 1);
        }
        //exp��һ��λ����*��ʱ��,������ߵ�����֤�������if������������
        //Ҳ����˵expû�����һ��,������һ��λ����'*',����exp��ǰλ�õ��ַ����ܺ�exp��*ǰ����ַ���Ӧ��
        //�Ǿ�Ҫ��exp��*ǰ����ַ��������1�����ٸ��ܶ�Ӧ��str�е��Ӵ�,����aaaab,��Ӧ*ab�Ļ�,����*aҪ�����4��a����
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        //�����*����Ӧ����,*ֱ�ӵ���0���ַ�����
        return process(str, exp, si, ei + 2);
    }

    //for test
    //��̬�滮,�����ޱ�...
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));
    }
}
