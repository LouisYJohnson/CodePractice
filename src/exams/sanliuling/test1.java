package exams.sanliuling;

import java.util.ArrayList;
import java.util.Scanner;

public class test1 {
    public static class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            //将所有字符串放到这个数组中
            ArrayList<String> allStrings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                allStrings.add(sc.next());
            }
            //然后挨个检查有效性
            int res = 0;
            for (String allString : allStrings) {
                if (isValid(allString)) {
                    res++;
                }
            }
            System.out.println(res);
        }

        public static boolean isValid(String s) {
            if (s.length() > 10) {
                return false;
            }

            char[] s2Chars = s.toCharArray();
            for (char s2Char : s2Chars) {
                if ((s2Char >= 'a' && s2Char <= 'z') || (s2Char >= 'A' && s2Char <= 'Z')) {
                    continue;
                }else {
                    return false;
                }
            }
            return true;
        }

    }

}
