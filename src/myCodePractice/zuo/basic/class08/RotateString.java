package myCodePractice.zuo.basic.class08;

public class RotateString {
    //����һ���ַ���str�� ��һ������k�� ����str����ѭ������kλ��Ľ��
    //�������ʹ�ö���ռ�:str+str�ٽ�ȡ
    //������ʹ�ö��⸨������:��벿���Ұ벿�ֱַ���������������

    //ʹ�ö���ռ�ķ���
    public static String rotateStringUsingAdditionalSpace(String str, int k) {
        if (k < 0 || str == null || str.length() == 0 || k > str.length()) return null;
        String helpStr = str + str;
        char[] helpChar = helpStr.toCharArray();
        char[] res = new char[str.length()];
        int start = str.length() - k;
        int helpIndex = 0;

        for (int i = start; i < start + str.length(); i++) {
            res[helpIndex++] = helpChar[i];
        }

        return String.valueOf(res);
    }

    //��ʹ�ö���ռ�ķ���
    public static String rotateString(String str, int k) {
        //��һ��λ�ñ任��ϸ��,�ñ���ֽ��д����ʵ����֪��զ������
        k = str.length() - k;
        if (k < 0 || str == null || str.length() == 0 || k > str.length()) return null;
        if (k == 0 || k == str.length()) return str;

        char[] chars = str.toCharArray();
        reverse(chars, 0, k - 1);
        reverse(chars, k, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return String.valueOf(chars);
    }

    //��charsλ��l��r֮���Ԫ�ؽ��з�ת
    public static void reverse(char[] chars, int l, int r) {
        int mid = l + (r - l) / 2;
        for (int i = l; i <= mid; i++) {
            swap(chars, i, r--);
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(rotateStringUsingAdditionalSpace(str, 3));
        System.out.println(rotateString(str, 3));
    }
}
