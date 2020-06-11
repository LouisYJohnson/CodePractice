package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Code_04_Group_Anagrams {
//    如果str1和str2包含的字符种类一样， 并且每种字符的个数也
//    一样， 那么str1和str2算作变形词。
//    给定一个字符类型的数组， 请把变形词分组。 比如
//    输入：
//            ["eat", "tea", "tan", "ate", "nat", "bat"]
//    输出：
//            [
//            ["ate", "eat","tea"],
//            ["nat","tan"],
//            ["bat"]
//            ]
//    注意： 所有的字符都是小写。
    //两种方法:
    //1.字符串按照字典排序后都是一样的,利用这个特性来制作一个字典,使用这个字典进行分组
    //2.字符串按照字符出现次数存成一个字符串,每个字符出现次数后面加上一个_,
    // 用这个来当作字典(用_来隔开元素出现次数,如果没有,两位数和一位数三位数就不好区分)

    //实现方法1:
    public static List<List<String >> groupAnagrams1(String[] strs) {
        HashMap<String,List<String>> hashMap = new HashMap<>();//用来装结果
        //拿出字符串数组中的每一个字符串排序后看是否能够放入hashMap中,key就是按字典序排序后,
        // value就是存储着和这个key字典序排序后一模一样的字符串们
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            //如果已经包含了这个key,则直接在value中的list中加入这个str,这段代码写完发现可以简化,但是为了便于理解,就这样了
            if (hashMap.containsKey(String.valueOf(chars))) {
                hashMap.get(String.valueOf(chars)).add(str);
            }else {//如果不包含这个key,则加入这个key并在value中的list中加入这个str
                hashMap.put(String.valueOf(chars),new ArrayList<String>());
                hashMap.get(String.valueOf(chars)).add(str);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (List<String> stringList : hashMap.values()) {
            result.add(stringList);
        }
        return result;
    }

    //实现方法2:


}
