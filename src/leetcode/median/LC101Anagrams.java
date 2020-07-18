package leetcode.median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LC101Anagrams {
    //题目描述
    //给出一个字符串数组，返回所有互为“换位词（anagrams）”的字符串的组合。
    // （换位词就是包含相同字母，但字母顺序可能不同的字符串）
    //备注：所有的输入都是小写字母
    //例如：
    //输入["tea","nat","ate","eat","tan"]
    //返回
    //[["ate", "eat","tea"],["nat","tan"]]
    public class Solution {
        //将每个字符串变成字符串数组并按照字典序排序
        //然后放入一个HashMap中
        //key为字典序排序后的字符串,value为所有符合条件的数组
        public ArrayList<String> anagrams(String[] strs) {
            HashMap<String, ArrayList<String>> helpMap = new HashMap<>();
            ArrayList<String> res = new ArrayList<>();

            for (int i = 0; i < strs.length; i++) {
                //每次拿到的字符串都经过字典排序后作为key
                char[] helpChar = strs[i].toCharArray();
                Arrays.sort(helpChar);
                String helpChar2String = String.valueOf(helpChar);
                //如果不存在这个key,创建这个key
                if (!helpMap.containsKey(helpChar2String)) {
                    helpMap.put(helpChar2String, new ArrayList<String>());
                }
                //将对应key的数据存入hashMap中
                helpMap.get(helpChar2String).add(strs[i]);
            }
            for (String s : helpMap.keySet()) {
                ArrayList<String> list = helpMap.get(s);
                //因为要所有互为换位词,单出来的词不要
                if (list.size() > 1) {
                    res.addAll(helpMap.get(s));
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC101Anagrams().new Solution();
        String[] test = new String[]{"tea"};
        ArrayList<String> res = new ArrayList<String>();
        System.out.println(1);
    }
}