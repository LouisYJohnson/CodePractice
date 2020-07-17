package com.newcoder.zuo3.gaopin.class01;

public class Problem_01_ParenthesesProblem {
    //��·:�����������Ӵ�:�����Ͽ�����ÿ��λ�ÿ�ʼ��˼·,������i+1λ���ܲ�����iλ�õõ�
    //1����֪һ���ַ���������������(��������)��ɣ��жϸ��ַ����Ƿ�����Ч��������ϡ�
    //
    //���ӣ�
    //��Ч���������:()(),(()),(()())
    //��Ч���������:(,()),((),()(()

    //�ⷨ:��һ������count,ÿ����(��++��)��--,�����;����count��0����,��Ϊfalse(����м���ֳ���()֮���������ַ�,Ҳֱ�ӷ���false)
    public static boolean isValid(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            //��������˳���()֮��������ַ�,ֱ��false
            if (chars[i] != '(' && chars[i] != ')') return false;
            //�������'(' ++
            if (chars[i] == '(') count++;
            //�������')' --
            if (chars[i] == ')') count--;
            //�����;countС��0,ֱ��false
            if (count < 0) return false;
        }
        //����count�Ƿ�Ϊ0
        return count == 0;
    }

    //2����Ŀ���ף�
    //��֪һ���ַ���������������(��������)��ɣ��������Ч�����Ӵ��ĳ��ȡ�
    //�ⷨ:dp[i]�����Ը�λ�ý�β����������Ч�Ӵ������Ƕ���,dp[i-1]����dp[i]��ǰ������
    public static int maxLength(String str) {
        if (str == null || "".equals(str)) return 0;

        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int pre = 0;    //��ʾ��һ��λ�õ�����
        int result = 0; //��ʾ���
        for (int i = 1; i < chars.length; i++) {
            //��'('��λ����0,��Ϊ��'('��β��Ϊ��Ч�����Ӵ�,��Ϊ��ʼ����ʱ����0,���Բ�����
            if (chars[i] == ')') {
                //�����������һ����Ч�Ӵ���ǰһ��λ��,����Ϊ�˿����λ�����ǲ���(,�ܺ͸�����)����
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    //���λ������(,�ܺ͸�����)����,����+2,�����pre>0��ʾ�Ӵ�����ʣ��,�Ǿͺ�pre��һ��λ��,
                    //Ҳ�����ܱ�֤��������Ч�����Ӵ���Ӿ�������ĳ���
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //for Combinations
    //implement by zuo
    public static boolean isValid1(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        char[] chas = str.toCharArray();
        int status = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ')' && chas[i] != '(') {
                return false;
            }
            if (chas[i] == ')' && --status < 0) {
                return false;
            }
            if (chas[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int maxLength1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValid1(str1));
        System.out.println(isValid(str1));
        System.out.println(maxLength1(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid1(str2));
        System.out.println(isValid(str2));
        System.out.println(maxLength1(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid1(str3));
        System.out.println(isValid(str3));
        System.out.println(maxLength1(str3));
        System.out.println(maxLength(str3));

    }

}
