package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Code_04_Group_Anagrams {

    public static List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if (hashMap.containsKey(String.valueOf(chars))) {
                hashMap.get(String.valueOf(chars)).add(str);
            } else {
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
}
