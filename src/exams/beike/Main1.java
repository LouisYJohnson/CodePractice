package exams.beike;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            String s0 = scanner.next();
            String s1 = scanner.next();
            String s2 = scanner.next();
            String s4 = scanner.next();
            String s5 = s0 + s1;
            String s6 = s2 + s4;
            System.out.println(process(s5.toCharArray(), s6.toCharArray()));
        }
    }

    public static String process(char[] chars1, char[] chars2) {
        int[] arr = new int[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (chars1[i] == 'S') {
                    if (chars2[j] == 'J') {
                        arr[i] = arr[i] + 1;
                    }
                } else if (chars1[i] == 'J') {
                    if (chars2[j] == 'B') {
                        arr[i] = arr[i] + 1;
                    }
                } else if (chars1[i] == 'B') {
                    if (chars2[j] == 'S') {
                        arr[i] = arr[i] + 1;
                    }
                }
            }
        }
        if (arr[0] > arr[1]) {
            return "left";
        } else if (arr[0] < arr[1]) {
            return "right";
        }else {
            return "same";
        }

    }
}
