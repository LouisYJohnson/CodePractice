package com.newcoder.zuo3.advanced.class03;

public class Code_03_ConvertStringToInteger {
    //�������ַ���ת������ֵ
    //����Ŀ��
    //����һ���ַ���str�� ���str�����ճ���д��������ʽ�� ��������32
    //λ�����ķ�Χ�� ����str�����������ֵ�� ���򷵻�0��
    //��������
    //str="123"�� ����123��
    //str="023"�� ��Ϊ"023"�������ճ�����дϰ�ߣ� ���Է���0��
    //str="A13"�� ����0��
    //str="0"�� ����0��
    //str="2147483647"�� ����2147483647��
    //str="2147483648"�� ��Ϊ����ˣ� ���Է���0��
    //str="-123"�� ����-123��
    //���ж��Ƿ�����Ч����,Ȼ����ת������
    //���κ�������,��������Сֵ�ľ���ֵ���Ǳ����������ֵ��1,
    // ��������ת������,���ǰ��ո���ת��,Ȼ�������Ҫת���������߸���,���˵���ڸ�������С��Ҫ����,
    // �Ǿ������,�����������ת�Ļ�,��������С��ת��������,
    // �����������������Ǹ�����С�ľ���ֵ,Ҳ��֪�������
    public static int convert(String str) {
        if (str == null || "".equals(str)) return 0;
        char[] chars = str.toCharArray();
        if (!isValid(chars)) return 0;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        boolean postiveFlag = chars[0] == '-' ? true : false;
        for (int i = postiveFlag ? 0 : 1; i < chars.length; i++) {
            cur = '0' - chars[i];
            if ((res < minq) || (res == minq && cur < minr)) return 0;
            //ȷ������Խ��,�ű�
            res = res * 10 + cur;
        }
        //���˵ת������Ǹ�����С�����ǻ�Ҫ��Ϊ����,�ǲ����ܵ�,��Ϊ��������ľ���ֵ�Ǳ���С�����ľ���ֵ��ҪС1��
        if (postiveFlag && res == Integer.MIN_VALUE) return 0;
        return postiveFlag ? -res : res;

    }

    //�˺������ڼ���ַ������Ӧ�����Ƿ���ϱ�׼
    public static boolean isValid(char[] chars) {
        //��Ե�һλ�����
        if (chars[0] == '0' && chars.length > 1) return false;
        if (chars[0] == '-' && chars[1] == '0') return false;
        if (chars.length == 1 && chars[0] == '-') return false;
        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) return false;
        //�������λ���ϵ�Ԫ�ص����
        for (int index = 1; index < chars.length; index++) {
            if (chars[index] < '0' || chars[index] > '9') return false;
        }
        return true;
    }
}
