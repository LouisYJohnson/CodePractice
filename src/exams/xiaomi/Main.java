package exams.xiaomi;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();
        char[][] dic = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(dic, s));
    }

    private static boolean exist(char[][] dic, String s) {
        boolean[][] visited = new boolean[dic.length][dic[0].length];
        for (int i = 0; i < dic.length; i++) {
            for (int j = 0; j < dic[0].length; j++) {
                if (s.charAt(0) == dic[i][j] && process(i, j, 0, s, visited, dic)) {
                    return true;
                }
            }
        }
        return false;

    }

    private static boolean process(int i, int j, int index, String s, boolean[][] visited, char[][] dic) {
        if (index == s.length()) {
            return true;
        }
        if (i >= dic.length || i < 0 || j >= dic[0].length || j < 0 || dic[i][j] != s.charAt(index) || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (process(i + 1, j, index + 1, s, visited, dic) || process(i - 1, j, index + 1, s, visited, dic) || process(i, j + 1, index + 1, s, visited, dic) || process(i, j - 1, index + 1, s, visited, dic)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}
