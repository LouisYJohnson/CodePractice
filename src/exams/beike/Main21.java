package exams.beike;

import java.util.Scanner;

public class Main21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();
        int sum = 0;
        int max = 0;
        String[] help = new String[1];
        help[0] = "";
        for (int i = 0; i < n; i++) {
            String a = s.substring(i, i + 1);
            help[0] = help[0] + a;
            int len = help[0].length();
            if (i + 1 + len > s.length()) {
                break;
            }
            String temp1 = s.substring(i + 1, i + 1 + len);
            if (help[0].equals(temp1)) {
                help[0] = help[0] + temp1.substring(0, temp1.length() - 1);
                sum = temp1.length();
                if (sum > max) {
                    max = sum;
                }
                i = i + len - 1;
            }
        }
        if (max == 0) {
            System.out.println(n);
        }else {
            System.out.println(n - max + 1);
        }
    }

}
