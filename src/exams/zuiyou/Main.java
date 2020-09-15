package exams.zuiyou;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //接收所有的数字
        String numbers = scanner.nextLine();
        HashSet<String> dic = new HashSet<>();//用来装所有的组合结果
        TreeSet<String> helpSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        boolean[] isUsed = new boolean[numbers.length()];
//        process(numbers, 0, dic, new StringBuilder(), isUsed);
        process(numbers, 0, helpSet, new StringBuilder(), isUsed);
        for (String s : helpSet) {
            System.out.print(s);
            System.out.print(" ");
        }
        //dic中装着所有的组合,将他们排个序就好了


    }

    //给定当前层数,将组合好的数字放到HashSet中(如果Set中没有的话)
    private static void process(String numbers, int i, HashSet<String> dic, StringBuilder helpBuilder, boolean[] isUsed) {
        if (i == numbers.length()) {
            String temp = String.valueOf(new StringBuilder(helpBuilder));
            dic.add(temp);
            return;
        }

        for (int j = 0; j < numbers.length(); j++) {
            if (!isUsed[j]) {
                isUsed[j] = true;
                helpBuilder.append(String.valueOf(numbers.charAt(j)));
                process(numbers, i + 1, dic, helpBuilder, isUsed);
                isUsed[j] = false;
                helpBuilder.deleteCharAt(helpBuilder.length() - 1);
            }
        }
    }

    private static void process(String numbers, int i, TreeSet<String> dic, StringBuilder helpBuilder, boolean[] isUsed) {
        if (i == numbers.length()) {
            String temp = String.valueOf(new StringBuilder(helpBuilder));
            dic.add(temp);
            return;
        }

        for (int j = 0; j < numbers.length(); j++) {
            if (!isUsed[j]) {
                isUsed[j] = true;
                helpBuilder.append(String.valueOf(numbers.charAt(j)));
                process(numbers, i + 1, dic, helpBuilder, isUsed);
                isUsed[j] = false;
                helpBuilder.deleteCharAt(helpBuilder.length() - 1);
            }
        }
    }


}
