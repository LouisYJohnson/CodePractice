package myCodePractice.zuo.basic.class08;

public class RotateString {
    //给定一个字符串str， 和一个整数k， 返回str向右循环右移k位后的结
    //果

    //如果可以使用额外空间:str+str再截取
    //不可以使用额外辅助数组:左半部分右半部分分别逆序再整体逆序
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

    public static String rotateString(String str, int k) {
        k = str.length() - k;
        if (k < 0 || str == null || str.length() == 0 || k > str.length()) return null;
        if (k == 0 || k == str.length()) return str;

        char[] chars = str.toCharArray();
        reverse(chars, 0, k - 1);
        reverse(chars, k, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return String.valueOf(chars);
    }

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
