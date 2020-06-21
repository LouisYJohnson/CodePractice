package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Code_04_Group_Anagrams {
//    ���str1��str2�������ַ�����һ���� ����ÿ���ַ��ĸ���Ҳ
//    һ���� ��ôstr1��str2�������δʡ�
//    ����һ���ַ����͵����飬 ��ѱ��δʷ��顣 ����
//    ���룺
//            ["eat", "tea", "tan", "ate", "nat", "bat"]
//    �����
//            [
//            ["ate", "eat","tea"],
//            ["nat","tan"],
//            ["bat"]
//            ]
//    ע�⣺ ���е��ַ�����Сд��
    //���ַ���:
    //1.�ַ��������ֵ��������һ����,�����������������һ���ֵ�,ʹ������ֵ���з���
    //2.�ַ��������ַ����ִ������һ���ַ���,ÿ���ַ����ִ����������һ��_,
    // ������������ֵ�(��_������Ԫ�س��ִ���,���û��,��λ����һλ����λ���Ͳ�������)

    //ʵ�ַ���1:
    public static List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();//����װ���
        //�ó��ַ��������е�ÿһ���ַ���������Ƿ��ܹ�����hashMap��,key���ǰ��ֵ��������,
        // value���Ǵ洢�ź����key�ֵ��������һģһ�����ַ�����
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            //����Ѿ����������key,��ֱ����value�е�list�м������str,��δ���д�귢�ֿ��Լ�,����Ϊ�˱������,��������
            if (hashMap.containsKey(String.valueOf(chars))) {
                hashMap.get(String.valueOf(chars)).add(str);
            } else {//������������key,��������key����value�е�list�м������str
                hashMap.put(String.valueOf(chars), new ArrayList<String>());
                hashMap.get(String.valueOf(chars)).add(str);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (List<String> stringList : hashMap.values()) {
            result.add(stringList);
        }
        return result;
    }

    //ʵ�ַ���2:


}
