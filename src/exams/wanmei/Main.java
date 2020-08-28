package exams.wanmei;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int knapsackCapacity = Integer.parseInt(input.nextLine().trim());

        String[] volumesStr = input.nextLine().split(" ");
        int[] volumes = new int[volumesStr.length];
        for (int i = 0; i < volumesStr.length; i++) {
            volumes[i] = Integer.parseInt(volumesStr[i].trim());
        }

        String[] valuesStr = input.nextLine().split(" ");
        int[] values = new int[valuesStr.length];
        for (int i = 0; i < valuesStr.length; i++) {
            values[i] = Integer.parseInt(valuesStr[i].trim());
        }

        if (volumes.length == values.length) {
            System.out.println(method(knapsackCapacity, volumes, values));
        }else {
            System.out.println("道具数量不一致。");
        }
        input.close();
    }

    public static int method(int knapSackCapacity, int[] volumes, int[] values) {
        return process(volumes, values, knapSackCapacity, 0,0);
    }

    public static int process(int[] volums, int[] values, int bag, int i, int curW) {
        if (curW > bag) {
            return Integer.MIN_VALUE;
        }

        if (i == values.length) {
            return 0;
        }

        return Math.max(process(volums, values, bag, i + 1, curW),
                values[i] + process(volums, values, bag, i + 1, curW + volums[i]));

    }
}
