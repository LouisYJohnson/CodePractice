package myCodePractice.zuo.advance.class02;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    //如果str1和str2包含的字符种类一样， 并且每种字符的个数也
    //一样， 那么str1和str2算作变形词。
    //给定一个字符类型的数组， 请把变形词分组。 比如
    //输入：
    //["eat", "tea", "tan", "ate", "nat", "bat"]
    //输出：
    //[
    //["ate", "eat","tea"],
    //["nat","tan"],
    //["bat"]
    //] 注
    //意： 所有的字符都是小写

    //进阶第二章1 28.09
    //方法1:
    //1.字符串按照字典排序后都是一样的,利用这个特性来制作一个字典
    //方法2:
    //2.字符串按照字符出现次数存成一个字符串,每个字符出现次数后面加上一个_,
    // 用这个来当作字典(用_来隔开元素出现次数,如果没有,两位数和一位数三位数就不好区分)
    //Arrays.sort()方法没有返回值,对数组自身进行改变

    //实现方法1:
    public static List<List<String>> groupAnagrams(String[] strings) {
        if (strings == null || strings.length == 0) return null;

        //构建字典,字典的key为按照字典序排序后的string,value为装着该string按照字典排序前的字符串的List
        HashMap<String,ArrayList<String>> dic = new HashMap<>();

        char[] help = null;
        for (int i = 0; i < strings.length; i++) {
            help = strings[i].toCharArray();
            Arrays.sort(help);
            if (dic.containsKey(String.valueOf(help))) {
                dic.get(String.valueOf(help)).add(strings[i]);
            }else {
                dic.put(String.valueOf(help), new ArrayList<>());
                dic.get(String.valueOf(help)).add(strings[i]);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> list : dic.values()) {
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = new String[] {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = groupAnagrams(strings);
        System.out.println();
    }
}
