package com.newcoder.zuo3.advanced.class01;

public class Code_07_Find_the_Closest_Palindrome {
    public static String nearestPalindromic(String n) {
        if (n == null) return n;
        Long l = getRawPalindrome(n);
        Long num = Long.parseLong(n);
        if (num == l) {
            return getBigPalindrome(num) - num < num - getSmallPalindrome(num) ?
                    getBigPalindrome(num).toString() : getSmallPalindrome(num).toString();
        } else if (num < l) {
            return l - num < num - getSmallPalindrome(num) ?
                    l.toString() : getSmallPalindrome(num).toString();
        } else {
            return num - l < getBigPalindrome(num) - num ?
                    l.toString() : getBigPalindrome(num).toString();
        }
    }

    public static Long getRawPalindrome(String n) {
        char[] chars = n.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            chars[chars.length - 1 - i] = chars[i];
        }
        return Long.valueOf(String.valueOf(chars));
    }

    public static Long getBigPalindrome(Long raw) {
        char[] chars = new char[raw.toString().length() + 1];
        chars[0] = '0';
        char[] help = raw.toString().toCharArray();
        for (int i = 1; i < chars.length; i++) {
            chars[i] = help[i - 1];
        }
        if ((raw.toString().length() & 1) == 1) {
            for (int i = 1 + (chars.length - 1) / 2; i >= 0; i--) {
                if (++chars[i] > '9') {
                    chars[i] = '0';
                } else {
                    break;
                }
            }
        } else {
            for (int i = 1 + (chars.length - 1) / 2 + 1; i >= 0; i--) {
                if (++chars[i] > '9') {
                    chars[i] = '0';
                } else {
                    break;
                }
            }
        }
        int offset = chars[0] == '0' ? 1 : 0;
        char[] result = new char[chars.length - offset];
        int j = 0;
        for (int i = offset; i < result.length; i++) {
            result[j++] = chars[i];
        }
        int center = (result.length / 2);
        for (int i = 0; i <= center; i++) {
            result[result.length - 1 - i] = result[i];
        }
        //
        return Long.valueOf(String.valueOf(result));
    }

    public static Long getSmallPalindrome(Long raw) {
        char[] chars = raw.toString().toCharArray();
        int center = (chars.length - 1) / 2;
        for (int i = center; i >= 0; i--) {
            if (--chars[i] < '0') {
                chars[i] = '9';
            } else {
                break;
            }
        }

        if (chars[0] == '0') {
            char[] result = new char[chars.length - 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = '9';
            }
            return Long.parseLong(result.toString());
        } else {
            for (int i = 0; i < (chars.length - 1) / 2; i++) {
                chars[chars.length - 1 - i] = chars[i];
            }
            return Long.parseLong(chars.toString());
        }
    }

    //for test
    public static void main(String[] args) {
        String s = "199";
        System.out.println(nearestPalindromic(s));
    }
}
