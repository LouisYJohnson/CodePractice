package myCodePractice.zuo.basic.class07;

import java.util.HashSet;

public class PrintAllPermutations {
    //��ӡһ���ַ�����ȫ������
    //ǰ��Ķ��ź���,��ǰλ��ѡ��ʣ�µ��ַ��ŵ���ǰλ����
    //���ѡ��ʣ�µ��ַ��ŵ���ǰλ����?ʹ�ý������
    public static void prinAllPermutations(String str) {
        if (str == null || str.length() == 0) return;
        char[] chars = str.toCharArray();

        process1(chars, 0);
        System.out.println("============-===");
        process2(chars, 0);
    }

    //�ݹ麯������:
    //�����ַ���������λ��i,��ӡ��0-i-1λ�ÿ�ʼ�Ѿ��źõ��ַ���
    public static void process1(char[] chars, int i) {
        //base case
        //����Ѿ����˽�β,ֱ�Ӵ�ӡ��ǰ�õ����ַ����Ϳ�����
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }

        //iλ�ú�i��length-1λ���ϵİ���λ���ϱ���һ�鲢����
        //���������ǵû�����,�������������ڵݹ麯���еĸĶ���ȫ�ֵ�,����
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process1(chars, i + 1);
            swap(chars, i, j);
        }
    }


    //��ӡһ���ַ�����ȫ�����У� Ҫ��Ҫ�����ظ�������
    //ʹ��һ��Set��ͬһ��λ�ó��ֵ��ַ���������,���ͬһ��λ���ϵ��ַ��ظ�������,��Ҫ
    public static void process2(char[] chars, int i) {
        //base case
        //����Ѿ����˽�β,ֱ�Ӵ�ӡchars
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        HashSet<Character> helpSet = new HashSet<>();
        for (int j = i; j < chars.length; j++) {
            if (!helpSet.contains(chars[j])) {
                helpSet.add(chars[j]);
                swap(chars, i, j);
                process2(chars, i + 1);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "acc";
        prinAllPermutations(str);
    }
}
