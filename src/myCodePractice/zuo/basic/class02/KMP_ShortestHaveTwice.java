package myCodePractice.zuo.basic.class02;

public class KMP_ShortestHaveTwice {
    //����һ���ַ���str1�� ֻ����str1�ĺ�������ַ����str2��
    //Ҫ��1�� str2�����������str1�� ����str1�������غϣ� ���ǲ�
    //����ͬһ��λ�ÿ�ͷ��
    //Ҫ��2�� str2������
    //���շ���str2
    //������
    //str1 = 123�� str2 = 123123 ʱ�� ��������str1�� �Ҳ�����ͬ
    //λ�ÿ�ͷ�� ��str2��̡�
    //str1 = 123123�� str2 = 123123123 ʱ�� ��������str1�� �Ҳ�
    //����ͬλ�ÿ�ͷ�� ��str2��̡�
    //str1 = 111�� str2 = 1111 ʱ�� ��������str1�� �Ҳ�����ͬλ
    //�ÿ�ͷ�� ��str2��̡�

    //�����������str1��next��������һ��Ԫ��(next���鳤�ȱ�str1��1)
    //str1�ĳ������Ԫ�صĲ�ֵ,����str1����Ҫ���伸��str1���Ⱥ����Ԫ��
    //next����ĺ���Ϊ�ǰ׺�����׺��ƥ�䳤��(ǰ׺���׺��˳����һ����,���ǻ��Ĺ�ϵ,���Manacher�㷨�����!)

    public static String kmpShortestHaveTwice(String str1) {
        if (str1 == null || str1.length() == 0) return null;

        char[] str1Chars = str1.toCharArray();
        //�������next����
        int[] next = getNextArray(str1Chars);
        //�õ�next��������һ��Ԫ��
        int help = next[next.length - 1];
        StringBuffer res = new StringBuffer(str1);
        for (int i = help; i < str1.length(); i++) {
            res.append(str1Chars[i]);
        }
        return String.valueOf(res);
    }

    public static int[] getNextArray(char[] chars) {
        int[] next = new int[chars.length + 1];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;

        for (int i = 2; i < next.length; i++) {
            cn = next[i - 1];
            while (cn != -1) {
                if (chars[cn] == chars[i - 1]) {
                    next[i] = cn + 1;
                    break;
                } else {
                    cn = next[cn];
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String string = "123";
        System.out.println(kmpShortestHaveTwice(string));
        String string1 = "123123";
        System.out.println(kmpShortestHaveTwice(string1));
        String string2 = "111";
        System.out.println(kmpShortestHaveTwice(string2));
    }
}
