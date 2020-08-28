package exams.wanmei;

import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] weight = new int[6][];

        String[] pointsStr = {"V1", "V2", "V3", "V4", "V5", "V6"};
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < pointsStr.length; i++) {
            String[] valuesStr = input.nextLine().split(" ");
            int[] values = new int[valuesStr.length];
            for (int j = 0; j < valuesStr.length; j++) {
                values[j] = Integer.parseInt(valuesStr[j]);
            }
            weight[i] = values;
        }
        input.close();
        HashMap<String, int[]> helpMap = new HashMap<>();
        helpMap.put("V1", weight[0]);
        helpMap.put("V2", weight[1]);
        helpMap.put("V3", weight[2]);
        helpMap.put("V4", weight[3]);
        helpMap.put("V5", weight[4]);
        helpMap.put("V6", weight[5]);

        method(weight, pointsStr, helpMap);
    }

    private static void method(int[][] weight, String[] pointsStr, HashMap<String, int[]> helpMap) {
        int[] resAll = new int[5];
        int index = 0;

        for (int i = 1; i < pointsStr.length; i++) {
            process("V1", pointsStr[i], helpMap, 0);
            resAll[index++] = res;
            res = Integer.MAX_VALUE;
        }
        for (int i = 0; i < resAll.length; i++) {
            if (resAll[i] == Integer.MAX_VALUE) {
                resAll[i] = 9999;
            }
        }
        for (int i = 0; i < resAll.length; i++) {
            System.out.println(resAll[i]);
        }
    }


    //回溯,给定出发位置与结束位置,返回二者之间的最小时间,如果不可达,返回9999
    public static void process(String from, String to, HashMap<String, int[]> helpMap, int curLen) {
        int[] curFrom = helpMap.get(from);
        //如果当前位置就是结束位置,更新res的最小值
        if (from.equals(to)) {
            res = Math.min(curLen, res);
            return;
        }
        //否则看当前是不是没有路走了,没有路了直接return
        int temp = 0;
        for (int i : curFrom) {
            temp += i;
        }
        if (temp == -5) {
            return;
        }
        //然后遍历当前的所有可以走的路径,只要不是自身且能走,就走
        for (int i = 0; i < curFrom.length; i++) {
            if (curFrom[i] > 0) {
                curLen += curFrom[i];
                process("V" + (i + 1), to, helpMap, curLen);
                curLen -= curFrom[i];
            }
        }

    }
}
