package exams.huawei;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int k = Integer.parseInt(sc.nextLine());
            int[][] arr1 = new int[k][2];
            String[] s = sc.nextLine().split(" ");
            String[] s2 = sc.nextLine().split(" ");

            for (int i = 0; i < k; i++) {
                arr1[i][0] = Integer.parseInt(s[i]);
                arr1[i][1] = Integer.parseInt(s2[i]);
            }
            int n = Integer.parseInt(sc.nextLine());
            int[][] arr2 = new int[n][2];
            String[] s1 = sc.nextLine().split(" ");
            String[] s3 = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr2[i][0] = Integer.parseInt(s1[i]);
                arr2[i][1] = Integer.parseInt(s3[i]);
            }
            System.out.println(index(k, arr1, n, arr2));
        }
    }
    private static int index(int k, int[][] arr1, int n, int[][] arr2) {
        if (k == 0 || n == 0) {
            return 0;
        }

        int index1 = 0;
        int index2 = 0;
        while (index1 < k && index2 < n) {
            if (arr1[index1][0] == arr2[index2][0]&& arr1[index1][1] == arr2[index2][1]) {
                index1++;
                index2++;
            }else {
                index1 = 0;
                index2++;
            }
        }
        if (index1 == k) {
            return index2 -k + 1;
        }
        return 0;

    }

}
