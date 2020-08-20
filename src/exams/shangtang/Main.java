package exams.shangtang;

import java.util.Scanner;

public class Main {
    //使用一个HashMap记录Good出现的个数,其中o的个数/2,并比较G,o/2,d中最小的那个,就是能够组成的个数
    //不能调换顺序,那就把这个存到一个链表中,然后从头到尾遍历,看组成多少个Good
    public static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder helpSB = new StringBuilder();
        while (sc.hasNext()) {
            //捕获每一个字符串,并将其中的字符串变成字符数组,遍历后放入LinkedList中
            String s = sc.next();
            char[] helpChar = s.toCharArray();
            for (char c : helpChar) {
                helpSB.append(c);
            }
        }
        if (helpSB.length() == 0) {
            System.out.println(0);
            return;
        }

        int res = 0;
        boolean[] isUsed = new boolean[helpSB.length()];
        for (int i = 0; i < helpSB.length(); i++) {
            if (!isUsed[i] && helpSB.charAt(i) == 'G') {
                for (int j = i + 1; j < helpSB.length(); j++) {
                    if (!isUsed[j] && helpSB.charAt(j) == 'o') {
                        for (int k = j + 1; k < helpSB.length(); k++) {
                            if (!isUsed[k] && helpSB.charAt(k) == 'o') {
                                for (int l = k + 1; l < helpSB.length(); l++) {
                                    if (!isUsed[l] && helpSB.charAt(l) == 'd') {
                                        res++;
                                        isUsed[i] = true;
                                        isUsed[j] = true;
                                        isUsed[k] = true;
                                        isUsed[l] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(res);
    }

}
