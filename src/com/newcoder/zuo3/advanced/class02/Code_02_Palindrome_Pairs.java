package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code_02_Palindrome_Pairs {
    //进阶第二章1 2:00
//    链表words中都是不同的词， 如果其中str1加str2之后是回文串，
//    则str1的位置和str2的位置我们需要收集。
//    比如
//    words = ["bat", "tab", "cat"]
//    返回[[0, 1], [1, 0]]
//    words = ["abcd", "dcba", "lls", "s", "sssll"]
//    返回[[0, 1], [1, 0], [3, 2], [2, 4]]
    //每个字符进来:
    //1.直接找一个字符串的逆序,然后看逆序的在字典中有没有,如果有,二者在一起绝对是回文
    //2.manacher算法改写:找到所有必须包含第一个字符的回文子串(从头开始找必须包含第一个字符的回文子串,从处理后的字符串数组着手,最多也就到中点结束了),
    // 后面的逆序,然后去链表中找后面的逆序,比如121345,在链表中找:必须包含第一个字符的回文子串:1,121
    //21345的逆序54312
    //345的逆序543如果有,就证明可以:543121345
    //3.反过来,仍然是manacher算法改写:找到所有必须包含最后一个字符的回文子串,然后去链表中找前面不是回文子串的逆序
    public static List<List<Integer>> palindromePairs(String[] words) {
        //存储words中的每个字符串与字符串对应下标
        HashMap<String,Integer> wordset = new HashMap<>();
        for (int index = 0; index < words.length; index++) {
            wordset.put(words[index],index);
        }
        return null;
    }
    //传入index是为了不和自身做回文
    public static List<List<Integer>> findAll(String word,int index,HashMap<String,Integer> words) {
        List<List<Integer>> res = new ArrayList<>();
        //先找这个字符串的逆序
        String reverse = reverse(word);
        //manacher数组中每个位置的半径长度
        int[] manacher = manacherrs(word);
        //一个专门装结果的list
        List<List<Integer>> result = new ArrayList<>();
        //先在字符串数组中找有没有该字符串的逆序
        Integer rest = words.get(reverse);//反转字符串在字符串数组中的位置
        if (rest != null && rest != index && word.equals(reverse)) {//不能是自己,而且相等的时候
            addRecord(res,index,rest);
        }
        //manacher/2 的长度正好是初始字符数组的长度
        int mid = manacher.length / 2;
        //查前缀
        for (int i = 1; i < mid; i++) {//0不可能,所以从1开始
            if (i - manacher[i] == -1) {//满足这个条件的原始数组下标才是包括了第一个字符的回文字符串
                //找不是前缀部分的逆序
                rest = words.get(reverse.substring(0,mid - i));
                if (rest != null && i != index) {
                    addRecord(res,i,rest);
                }
            }
        }
        //查后缀
        for (int i = mid + 1; i < manacher.length; i++) {
            if (i + manacher[i] == manacher.length) {//满足这个条件的原始数组下标才是包括了最后一个字符的回文字符串
                rest = words.get(reverse.substring((mid / 2) - i));
                if (rest != null && rest != index) {
                    addRecord(res,i,rest);
                }
            }
        }


        return null;
    }
    //将坐标放入list中,再将这个list放到一个总的list中
    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        res.add(list);
    }

    //实现manacher算法:
    //先处理manacher字符串:
    //1 2 3 -> #1#2#3#
    public static char[] manachercs(String word) {
        char[] help = word.toCharArray();
        int helpIndex = 0;
        //就是在偶数位放"#",奇数位放"字符"
        char[] chars = new char[word.length() * 2 + 1];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = (index & 1) == 1 ? help[helpIndex++] : '#';
        }
        return chars;
    }
    //正式实现manacher算法,并返回一个数组,这个数组中装着每个位置上的回文半径
    public static int[] manacherrs(String word) {
        //得到处理后的字符数组:
        char[] mchars = manachercs(word);
        //用来装每一个位置上的回文半径(包括自身)
        int[] pArr = new int[mchars.length];
        int center = -1;//最大回文半径对应的回文中心
        int pR = -1;//最大回文直径的长度
        for (int index = 0; index < pArr.length; index++) {
            pArr[index] = pR > index ? Math.min(pArr[center * 2 - index],pR - index) : 1;
            //以初始半径(包括自身)开始向两边扩,先看扩了是否越界,然后再看能不能扩,这里是下标值
            while (index - pArr[index] > -1 && pArr[index] + index < pArr.length) {
                if (mchars[index - pArr[index]] == mchars[index + pArr[index]]) {
                    pArr[index]++;
                }else break;
            }
            //扩完了之后更新center与pR
            if (index + pArr[index] > pR) {
                center = index;
                pR = index + pArr[index];
            }
        }
        return pArr;
    }

    //实现一个字符串的reverse
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        for (int index = 0; index <= (chars.length - 1) / 2; index++) {
            swap(chars,index,chars.length - 1 - index);
        }
        return String.valueOf(chars);
    }

    public static void swap(char[] chars,int i,int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "msadwasdxc";
        System.out.println(reverse(s));
    }
}
