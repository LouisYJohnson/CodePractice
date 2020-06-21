package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code_02_Palindrome_Pairs {
    //���׵ڶ���1 2:00
//    ����words�ж��ǲ�ͬ�Ĵʣ� �������str1��str2֮���ǻ��Ĵ���
//    ��str1��λ�ú�str2��λ��������Ҫ�ռ���
//    ����
//    words = ["bat", "tab", "cat"]
//    ����[[0, 1], [1, 0]]
//    words = ["abcd", "dcba", "lls", "s", "sssll"]
//    ����[[0, 1], [1, 0], [3, 2], [2, 4]]
    //ÿ���ַ�����:
    //1.ֱ����һ���ַ���������,Ȼ����������ֵ�����û��,�����,������һ������ǻ���
    //2.manacher�㷨��д:�ҵ����б��������һ���ַ��Ļ����Ӵ�(��ͷ��ʼ�ұ��������һ���ַ��Ļ����Ӵ�,�Ӵ������ַ�����������,���Ҳ�͵��е������),
    // ���������,Ȼ��ȥ�������Һ��������,����121345,����������:���������һ���ַ��Ļ����Ӵ�:1,121
    //21345������54312
    //345������543�����,��֤������:543121345
    //3.������,��Ȼ��manacher�㷨��д:�ҵ����б���������һ���ַ��Ļ����Ӵ�,Ȼ��ȥ��������ǰ�治�ǻ����Ӵ�������
    public static List<List<Integer>> palindromePairs(String[] words) {
        //�洢words�е�ÿ���ַ������ַ�����Ӧ�±�
        HashMap<String, Integer> wordset = new HashMap<>();
        for (int index = 0; index < words.length; index++) {
            wordset.put(words[index], index);
        }
        return null;
    }

    //����index��Ϊ�˲�������������
    public static List<List<Integer>> findAll(String word, int index, HashMap<String, Integer> words) {
        List<List<Integer>> res = new ArrayList<>();
        //��������ַ���������
        String reverse = reverse(word);
        //manacher������ÿ��λ�õİ뾶����
        int[] manacher = manacherrs(word);
        //һ��ר��װ�����list
        List<List<Integer>> result = new ArrayList<>();
        //�����ַ�������������û�и��ַ���������
        Integer rest = words.get(reverse);//��ת�ַ������ַ��������е�λ��
        if (rest != null && rest != index && word.equals(reverse)) {//�������Լ�,������ȵ�ʱ��
            addRecord(res, index, rest);
        }
        //manacher/2 �ĳ��������ǳ�ʼ�ַ�����ĳ���
        int mid = manacher.length / 2;
        //��ǰ׺
        for (int i = 1; i < mid; i++) {//0������,���Դ�1��ʼ
            if (i - manacher[i] == -1) {//�������������ԭʼ�����±���ǰ����˵�һ���ַ��Ļ����ַ���
                //�Ҳ���ǰ׺���ֵ�����
                rest = words.get(reverse.substring(0, mid - i));
                if (rest != null && i != index) {
                    addRecord(res, i, rest);
                }
            }
        }
        //���׺
        for (int i = mid + 1; i < manacher.length; i++) {
            if (i + manacher[i] == manacher.length) {//�������������ԭʼ�����±���ǰ��������һ���ַ��Ļ����ַ���
                rest = words.get(reverse.substring((mid / 2) - i));
                if (rest != null && rest != index) {
                    addRecord(res, i, rest);
                }
            }
        }


        return null;
    }

    //���������list��,�ٽ����list�ŵ�һ���ܵ�list��
    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        res.add(list);
    }

    //ʵ��manacher�㷨:
    //�ȴ���manacher�ַ���:
    //1 2 3 -> #1#2#3#
    public static char[] manachercs(String word) {
        char[] help = word.toCharArray();
        int helpIndex = 0;
        //������ż��λ��"#",����λ��"�ַ�"
        char[] chars = new char[word.length() * 2 + 1];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = (index & 1) == 1 ? help[helpIndex++] : '#';
        }
        return chars;
    }

    //��ʽʵ��manacher�㷨,������һ������,���������װ��ÿ��λ���ϵĻ��İ뾶
    public static int[] manacherrs(String word) {
        //�õ��������ַ�����:
        char[] mchars = manachercs(word);
        //����װÿһ��λ���ϵĻ��İ뾶(��������)
        int[] pArr = new int[mchars.length];
        int center = -1;//�����İ뾶��Ӧ�Ļ�������
        int pR = -1;//������ֱ���ĳ���
        for (int index = 0; index < pArr.length; index++) {
            pArr[index] = pR > index ? Math.min(pArr[center * 2 - index], pR - index) : 1;
            //�Գ�ʼ�뾶(��������)��ʼ��������,�ȿ������Ƿ�Խ��,Ȼ���ٿ��ܲ�����,�������±�ֵ
            while (index - pArr[index] > -1 && pArr[index] + index < pArr.length) {
                if (mchars[index - pArr[index]] == mchars[index + pArr[index]]) {
                    pArr[index]++;
                } else break;
            }
            //������֮�����center��pR
            if (index + pArr[index] > pR) {
                center = index;
                pR = index + pArr[index];
            }
        }
        return pArr;
    }

    //ʵ��һ���ַ�����reverse
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        for (int index = 0; index <= (chars.length - 1) / 2; index++) {
            swap(chars, index, chars.length - 1 - index);
        }
        return String.valueOf(chars);
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "msadwasdxc";
        System.out.println(reverse(s));
    }
}
