package com.newcoder.zuo3.advanced.class01;

public class Code_07_Find_the_Closest_Palindrome {
    //��һ���ַ���str�� ����һ�������� �ҵ����������֮�⣬ ����ֵ����������
    //��С�Ļ�������
    //���磺
    //str = ��123��
    //���ء�121��
    //ע�⣺
    //�����ַ���strһ���ܱ��long����
    //���ܰ���ԭ����������һ���������־�������,��������199,��������191ʵ������û��202����199����,
    // ������Ҫ��һ�������ִ�һ��������С���ܹ���������������������Ƚ�
    //    ������������Ƚ�ԭʼ������һ������,Ȼ����������Ǳȵ�ǰ�����ִ���С�������
    //    ���:�ֱ����������ִ�,С�Ļ�����
    //    ��:����������С�Ļ�����
    //    С:���������ִ�Ļ�����
    //    ���Ķ�����(�жϽ�λ���ǽ�λ֮���,��λҪ���ϲ�0)�Գ���ǰ��(�����ԳƵ����õ��������,Ϊʲô?����ͼ��֪����)����
    //          ����:offset + (chars.length -1 - offset) / 2 ����������������ֵ(��0��ʼ�Ľ�����ֵ),
    //          �����ż������,��Ӧ����λ,�������������,��Ӧ����
    //    ������:
    //      ������ֵĳ���(��ȥ�㷨��ӵ�0)��ż��,��ô������λ(��ȥ0������λ��λ�ü�1������,
    //      �������������,���м�λ�ü�1(ע������ļ�һ��һ˵�ľ������λ���ϵ�ֵ!)������(�㷨ʵ���ڴ����ʱ��,������ǰ�油һ��0(Ϊ�˽�λ��ʱ��÷���),
    //      ����ʵ�������ҶԳ����ʱ���Ǻ������0�����е����λ��)
    //    ��С����(����ʱ���ǲ���ҪԤ��0��,��Ϊֻ�н�λû�н�λ):
    //      ������ֵĳ�����ż��,��ô������λ��λ�ü�1������,�������������,���м�λ�ü�1������
    //      �����1֮�����λ���0,��ֱ�ӷ��س������λʣ��λ��Ϊ9����
    public static String nearestPalindromic(String n) {
        if (n == null) return n;
        Long l = getRawPalindrome(n);
        Long num = Long.parseLong(n);
        if (num == l) {
            return getBigPalindrome(num) - num < num - getSmallPalindrome(num) ?
                    getBigPalindrome(num).toString() : getSmallPalindrome(num).toString();
        } else if (num < l) {//��:����������С�Ļ�����
            return l - num < num - getSmallPalindrome(num) ?
                    l.toString() : getSmallPalindrome(num).toString();
        } else {//num > l С:���������ִ�Ļ�����
            return num - l < getBigPalindrome(num) - num ?
                    l.toString() : getBigPalindrome(num).toString();
        }
    }

    //��ԭʼ����
    public static Long getRawPalindrome(String n) {
        char[] chars = n.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            chars[chars.length - 1 - i] = chars[i];
        }
        return Long.valueOf(String.valueOf(chars));
    }

    //������
    public static Long getBigPalindrome(Long raw) {
        //Ϊ�˽�λ����,��char�ĵ�0��λ�ü���'0',��raw���μ������
        char[] chars = new char[raw.toString().length() + 1];
        chars[0] = '0';
        char[] help = raw.toString().toCharArray();
        for (int i = 1; i < chars.length; i++) {
            chars[i] = help[i - 1];
        }
        //�ȿ�Ҫ�����ĵ�λ���Ƿ���ΪҪ��λ�������ı�
        //��������λ�õļ��㷽��,ʵ���϶�������������ľ����ص�,ż���������������λ,if else���Ժϳ�һ��
        //������ֵĳ���(��ȥ�㷨��ӵ�0)������,�����м�λ��+1���Ƿ��λȻ����������
        if ((raw.toString().length() & 1) == 1) {
            for (int i = 1 + (chars.length - 1) / 2; i >= 0; i--) {
                if (++chars[i] > '9') {//������,ÿ��λ���϶�+1�˻���˳���ж�(�ж������е�����ǻ�ִ�е�,����++��ı������ֵ,Ů�ٿڰ�!)
                    chars[i] = '0';
                } else {
                    break;
                }
            }
        } else {//������ֵĳ���(��ȥ�㷨��ӵ�0)��ż��,��ô������λ(��ȥ0������λ��λ�ü�1������
            for (int i = 1 + (chars.length - 1) / 2 + 1; i >= 0; i--) {
                if (++chars[i] > '9') {//������,ÿ��λ���϶�+1�˻���˳���ж�(�ж������е�����ǻ�ִ�е�,����++��ı������ֵ,Ů�ٿڰ�!)
                    chars[i] = '0';
                } else {
                    break;
                }
            }
        }
        //������������,�����ĵ�ʱ��Ҫ�ж�������λ��0����û��,���û��,�����ĵ�ʱ��Ҫɾ��
        //���Ķ����öԳ���ǰ�����()
        int offset = chars[0] == '0' ? 1 : 0;
        char[] result = new char[chars.length - offset];
        int j = 0;
        for (int i = offset; i < result.length; i++) {
            result[j++] = chars[i];
        }
        int center = (result.length / 2);
        for (int i = 0; i <= center; i++) {
            result[result.length - 1 - i] = result[i];
        }
        //
        return Long.valueOf(String.valueOf(result));
    }

    //��С����(����ʱ���ǲ���ҪԤ��0��,��Ϊֻ�н�λû�н�λ):
    //      ������ֵĳ�����ż��,��ô������λ��λ�ü�1������,�������������,���м�λ�ü�1������
    //      �����1֮�����λ���0,��ֱ�ӷ��س������λʣ��λ��Ϊ9����
    public static Long getSmallPalindrome(Long raw) {
        char[] chars = raw.toString().toCharArray();
        //�������������,���м�λ�ü�1������
        //������ֵĳ�����ż��,��ô������λ��λ�ü�1������,
        //�ȿ�Ҫ�����ĵ�λ���Ƿ���ΪҪ��λ�������ı�
        int center = (chars.length - 1) / 2;
        for (int i = center; i >= 0; i--) {
            if (--chars[i] < '0') {
                chars[i] = '9';
            } else {
                break;
            }
        }

        //�����1֮�����λ���0,��ֱ�ӷ��س������λʣ��λ��Ϊ9����,����,�ڶԳ��ᴦ������
        if (chars[0] == '0') {
            char[] result = new char[chars.length - 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = '9';
            }
            return Long.parseLong(result.toString());
        } else {
            for (int i = 0; i < (chars.length - 1) / 2; i++) {
                chars[chars.length - 1 - i] = chars[i];
            }
            return Long.parseLong(chars.toString());
        }
    }

    //for test
    public static void main(String[] args) {
        String s = "199";
        System.out.println(nearestPalindromic(s));
    }
}
