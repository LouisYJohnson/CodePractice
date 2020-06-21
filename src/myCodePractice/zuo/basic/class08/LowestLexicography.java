package myCodePractice.zuo.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class LowestLexicography {
    //����һ���ַ������͵�����strs�� �ҵ�һ��ƴ�ӷ�ʽ�� ʹ�ð�������
    //����ƴ����֮���γɵ��ַ���������͵��ֵ���

    //̰��
    //�������Ӧ�ñ��,str1��str2ƴ��,���str1.str2<str2.str1,
    // ��str1��ǰ��,����,str1�ź���,��Ϊ�������д����ԵĶ��ҵõ��Ľ����Ψһ��.

    public static String lowestLixicongraphy(String[] strings) {
        if (strings == null) return null;
        if (strings.length < 2) return strings[0];

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //���str1.str2<str2.str1,��str1��ǰ��,����,str1�ź���
                //�Ƚ����Ĺ���:���ظ���,����Ĳ�����ǰ��ķ�ǰ��(����)������(��)
                //              ��������,����Ĳ����к���ķ�ǰ��(����)������(��)
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        //���ź����strings��ϳ�һ����
        StringBuffer res = new StringBuffer();

        for (int i = 0; i < strings.length; i++) {
            res.append(strings[i]);
        }

        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
        String[] strs2 = {"ba", "b"};

        System.out.println(lowestLixicongraphy(strs1));
        System.out.println(lowestLixicongraphy(strs2));
    }
}
