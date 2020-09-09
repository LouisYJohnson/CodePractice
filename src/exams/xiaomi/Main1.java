package exams.xiaomi;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(process(scanner.next()));
        }
    }

    public static int process(String string) {
        if (string.length() > 120 || string.length() < 8) {
            return 1;
        }
        int flag = 1;
        int[] flagArray = new int[4];
        for (int i = 0; i < string.length(); i++) {
            char tempChar = string.charAt(i);
            if (tempChar < 33 || tempChar >= 127 || tempChar == 60) {
                return 2;
            }

            if (tempChar >= '0' && tempChar <= '9') {
                flagArray[0] = 1;
            } else if (tempChar >= 'A' && tempChar <= 'Z') {
                flagArray[1] = 1;
            } else if (tempChar >= 'a' && tempChar <= 'z') {
                flagArray[2] = 1;
            }else {
                flagArray[3] = 1;
            }
        }
        for (int i = 0; i < flagArray.length; i++) {
            if (flagArray[i] == 0) {
                return 2;
            }
        }
        return 0;
    }
}
