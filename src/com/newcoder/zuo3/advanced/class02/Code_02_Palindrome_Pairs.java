package com.newcoder.zuo3.advanced.class02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code_02_Palindrome_Pairs {
    public static List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> wordset = new HashMap<>();
        for (int index = 0; index < words.length; index++) {
            wordset.put(words[index], index);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            res.addAll(findAll(words[i], i, wordset));
        }
        return res;
    }

    public static List<List<Integer>> findAll(String word, int index, HashMap<String, Integer> words) {
        List<List<Integer>> res = new ArrayList<>();
        String reverse = reverse(word);
        int[] manacher = manacherrs(word);
        List<List<Integer>> result = new ArrayList<>();
        Integer rest = words.get(reverse);
        if (rest != null && rest != index && word.equals(reverse)) {
            addRecord(res, index, rest);
        }
        int mid = manacher.length / 2;
        for (int i = 1; i < mid; i++) {
            if (i - manacher[i] == -1) {
                rest = words.get(reverse.substring(0, mid - i));
                if (rest != null && i != index) {
                    addRecord(res, i, rest);
                }
            }
        }
        for (int i = mid + 1; i < manacher.length; i++) {
            if (i + manacher[i] == manacher.length) {
                rest = words.get(reverse.substring((mid / 2) - i));
                if (rest != null && rest != index) {
                    addRecord(res, i, rest);
                }
            }
        }


        return res;
    }

    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        res.add(list);
    }

    public static char[] manachercs(String word) {
        char[] help = word.toCharArray();
        int helpIndex = 0;
        char[] chars = new char[word.length() * 2 + 1];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = (index & 1) == 1 ? help[helpIndex++] : '#';
        }
        return chars;
    }

    public static int[] manacherrs(String word) {
        char[] mchars = manachercs(word);
        int[] pArr = new int[mchars.length];
        int center = -1;
        int pR = -1;
        for (int index = 0; index < pArr.length; index++) {
            pArr[index] = pR > index ? Math.min(pArr[center * 2 - index], pR - index) : 1;
            while (index - pArr[index] > -1 && pArr[index] + index < pArr.length) {
                if (mchars[index - pArr[index]] == mchars[index + pArr[index]]) {
                    pArr[index]++;
                } else break;
            }
            if (index + pArr[index] > pR) {
                center = index;
                pR = index + pArr[index];
            }
        }
        return pArr;
    }

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
