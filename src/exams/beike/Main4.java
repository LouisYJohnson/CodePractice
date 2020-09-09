package exams.beike;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] a = new int[2 * n + 1];
        int[] b = new int[2 * n + 1];

        for (int i = 0; i < 2 * n + 1; i++) {
            if (i == n) {
                a[i] = 0;
                continue;
            }
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < 2 * n + 1; i++) {
            if (i == n) {
                b[i] = 0;
                continue;
            }
            b[i] = scanner.nextInt();
        }
        System.out.println(process(a, b));
    }

    private static int process(int[] t, int[] b) {
        int[] a = t;
        int[] dp = new int[a.length];
        int[] dpB = new int[a.length];
        int index = (a.length - 1) / 2;
        int pre = index;
        for (int i = 1; i < dp.length; i++) {
            a[index] = 0;
            int[] indexArray = getNextIndex(a, index);
            int temp;
            if (indexArray[1] >= 0 && indexArray[2] <= a.length - 1) {
                temp = a[indexArray[1]] == Math.min(a[indexArray[1]], a[indexArray[2]]) ? indexArray[1] : indexArray[2];
                dp[i] = dpB[i - 1] - a[temp];
                dpB[i] = dp[i] + b[temp];
                pre = temp;
                index = temp;
            } else if (indexArray[1] >= 0) {
                temp = indexArray[1];
                dp[i] = dpB[i - 1] - a[temp];
                dpB[i] = dp[i] + b[temp];
                pre = temp;
                index = temp;
            } else if (indexArray[2] <= a.length - 1) {
                temp = indexArray[2];
                dp[i] = dpB[i - 1] - a[temp];
                dpB[i] = dp[i] + b[temp];
                pre = temp;
                index = temp;
            }
        }
        dp[0] = Integer.MAX_VALUE;
        return getMin(dp);
    }

    private static int[] getNextIndex(int[] a, int index) {
        int temp = a[index];
        int indexLeft = index, indexRight = index;
        while (indexLeft >= 0 && a[indexLeft] == 0) {
            indexLeft--;
        }
        while (indexRight <= a.length - 1 && a[indexRight] == 0) {
            indexRight++;
        }
        return new int[]{temp, indexLeft, indexRight};

    }

    private static int getMin(int[] dp) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= 0) {
                continue;
            }
            minValue = minValue > dp[i] ? dp[i] : minValue;
        }
        return Math.abs(minValue) + 1;

    }




}
